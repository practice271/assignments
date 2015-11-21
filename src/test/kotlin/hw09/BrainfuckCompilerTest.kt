package hw09

import org.junit.Test
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

public class BrainfuckCompilerTest {

    @Test fun testHelloWorld() {
        testCompiler( "++++++++++[>+++++++>++++++++++>+++>++++<" +
                "<<<-]>++.>+.+++++++..+++.>>++++.<++.<+++" +
                "+++++.--------.+++.------.--------.>+.",
                "",
                "Hello, world!" )
    }

    @Test fun test2() {
        testCompiler("++-+++++++++++.",
                "",
                "${12.toByte().toChar()}")
    }

    @Test fun test3() {
        testCompiler(",.,.,.,.",
                     "meow",
                     "meow")
    }

    public fun testCompiler(program: String, input: String, output: String) {
        val name = "Test"
        val classByteArray = BrainfuckCompiler(program, "Test").generateByteArray()

        val out = TestPrintStream()
        System.setIn(TestInputStream(input))
        System.setOut(out)

        val cl = ByteArrayClassLoader()
        val exprClass = cl.loadClass(name, classByteArray)
        val methods = exprClass?.methods
        if (methods == null || methods.isEmpty())
            throw AssertionError("No main method was found.")
        for (method in methods) {
            if (method.name != "main") { continue }
            method.invoke(null, arrayOf<String>())
            assertEquals(output, out.toString())
        }
    }

    internal class ByteArrayClassLoader(): ClassLoader() {
        fun loadClass(name: String?, buf: ByteArray): Class<*>? {
            return super.defineClass(name, buf, 0, buf.size)
        }
    }

    internal class TestPrintStream() : PrintStream(EmptyStream()) {
        private var output = StringBuilder()

        override fun print(c : Char) {
            output.append(c)
        }
        override fun toString() = output.toString()
    }

    internal class TestInputStream(private val input: String) : InputStream() {
        private var index = 0

        override fun read(): Int {
            if (index < input.length) {
                return input[index++].toInt()
            }
            else return -1
        }
    }

    internal class EmptyStream() : OutputStream() {
        override fun write(b : Int) {
        }
    }
}