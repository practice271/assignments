package hw09

import org.junit.Test
import kotlin.test.assertEquals
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream

class CompilerTests {
    public fun testCompiler(program: String, result: String) {
        val name = "Test"
        val classByteArray = Compiler(program, "Test").generateClassByteArray()

        val out = TestPrintStream()
        System.setIn(TestInputStream(""))
        System.setOut(out)

        val cl = ByteArrayClassLoader()
        val exprClass = cl.loadClass(name, classByteArray)
        val methods = exprClass?.methods
        if (methods == null || methods.isEmpty())
            throw AssertionError("No main method was found.")
        for (method in methods) {
            if (method.name != "main") { continue }
            method.invoke(null, arrayOf<String>())
            assertEquals(result, out.toString())
        }
    }

    private class ByteArrayClassLoader(): ClassLoader() {
        fun loadClass(name: String?, buf: ByteArray): Class<*>? {
            return super.defineClass(name, buf, 0, buf.size)
        }
    }

    private class TestPrintStream() : PrintStream(EmptyStream()) {
        private var output = StringBuilder()

        override fun print(c : Char) {
            output.append(c)
        }
        override fun toString() = output.toString()
    }

    private class TestInputStream(private val input: String) : InputStream() {
        private var index = 0

        override fun read(): Int {
            if (index < input.length) {
                return input[index++].toInt()
            }
            else return -1
        }
    }

    private class EmptyStream() : OutputStream() {
        override fun write(b : Int) {
        }
    }

    @Test fun simpleTest() {
        testCompiler(">>>>++++---<<<<","")
    }
    @Test fun helloWorldTest() {

        val str = "+++++++++++++++++++++++++++++++++++++++++++++" +
                  "+++++++++++++++++++++++++++.+++++++++++++++++" +
                  "++++++++++++.+++++++..+++.-------------------" +
                  "---------------------------------------------" +
                  "---------------.+++++++++++++++++++++++++++++" +
                  "++++++++++++++++++++++++++.++++++++++++++++++" +
                  "++++++.+++.------.--------.------------------" +
                  "---------------------------------------------" +
                  "----.-----------------------."
        testCompiler(str, "Hello World!\n")
    }

    @Test fun helloWorldWithBracketsTest() {
         val str = "++++++++++[>+++++++>++++++++++>+++>++++<" +
                   "<<<-]>++.>+.+++++++..+++.>>++++.<++.<+++" +
                   "+++++.--------.+++.------.--------.>+."
        testCompiler(str, "Hello, world!")
    }
}