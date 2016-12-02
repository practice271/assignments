package hw09

import org.junit.Test
import kotlin.test.assertEquals
import java.io.*

class PetoohCompilerTest {
    @Test fun testHelloWorld() {
        testPetoohCompiler(
                "KoKoKoKoKoKoKoKo Kud-Kudah KoKoKoKo Kud-Kudah KoKo Kudah KoKoKo " +
                "Kudah KoKoKo Kudah Ko kudah kudah kudah kudah kO kud-Kudah Ko Kudah Ko " +
                "Kudah kO Kudah Kudah Ko Kud-kudah kud-kudah kO kud Ko Kudah Kudah Kukarek " +
                "Kudah kO kO kO Kukarek KoKoKoKoKoKoKo Kukarek Kukarek KoKoKo Kukarek Kudah " +
                "Kudah Kukarek kudah kO Kukarek kudah Kukarek KoKoKo Kukarek kOkOkOkOkOkO " +
                "Kukarek kOkOkOkOkOkOkOkO Kukarek Kudah Kudah Ko Kukarek Kudah KoKo Kukarek",
                "", "Hello World!\n"
        )
    }
    @Test fun test2() {
        testPetoohCompiler(
                "KoKoKoKoKoKoKoKoKoKo Kud-Kudah KoKoKoKoKoKoKoKo kudah kO kud-Kudah " +
                "Kukarek kudah KoKoKo Kud-Kudah kOkOkOkO kudah kO kud-Kudah Ko Kukarek kudah " +
                "KoKoKoKo Kud-Kudah KoKoKoKo kudah kO kud-Kudah kO Kukarek kOkOkOkOkO Kukarek " +
                "Kukarek kOkOkOkOkOkOkO Kukarek",
                " ", "PETOOH")
        }

    private class ByteArrayClassLoader() : ClassLoader() {
        fun loadClass(name: String?, buf: ByteArray): Class<*>? {
            return super.defineClass(name, buf, 0, buf.size)
        }
    }

    private fun testPetoohCompiler(program: String, input: String, output: String) {
        val className = "test"
        val classByteArray = PetoohCompiler(program, "test").generateByteArray()
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