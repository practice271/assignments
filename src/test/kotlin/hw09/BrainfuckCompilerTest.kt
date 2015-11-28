package hw09

import org.junit.Test
import kotlin.test.assertEquals
import java.io.*

class BrainfuckCompilerTest {

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

    private class ByteArrayClassLoader() : ClassLoader() {
        fun loadClass(name: String?, buf: ByteArray): Class<*>? {
            return super.defineClass(name, buf, 0, buf.size)
        }
    }

    private fun testCompiler(program: String, input: String, output: String) {
        val className = "test"
        val classByteArray = BrainfuckCompiler(program, "test").generateByteArray()
        if (input != "") System.setIn(ByteArrayInputStream(output.toByteArray()))

        val cl = ByteArrayClassLoader()
        val exprClass = cl.loadClass(className, classByteArray)
        val methods = exprClass?.methods
        if (methods == null || methods.isEmpty()) { throw Exception() }
        for (method in methods) {
            if (method.name != "main") { continue }
            method.invoke(null, arrayOf<String>())
            assertEquals(output, out.toString())
        }
    }

    private var out = ""
    private var printTest: PrintStream

    init {
        printTest = PrintStream(object: OutputStream() {
            override fun write(c: Int) {
                out += (c.toChar())
            }
        })
        System.setOut(printTest)
    }
}