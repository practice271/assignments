package hw09

import classroom.c07.ByteArrayClassLoader
import org.junit.Test
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class EmptyStream() : OutputStream() {
    override fun write(b : Int) {
    }
}

class TestPrintStream() : PrintStream(EmptyStream()) {
    private var output = StringBuilder()

    override fun print(c : Char) {
        output.append(c)
    }

    override fun toString() = output.toString()
}

public class brainfuckCompilerTest {

    public fun testCompiler(program: String, input: Array<Int>, output: String) {
        val name = "test"
        val classByteArray = BrainfuckCompiler(program, "test").compile()

        val out = TestPrintStream()
        System.setIn(TestInputStream(input))
        System.setOut(out)

        val cl = ByteArrayClassLoader()
        val exprClass = cl.loadClass(name, classByteArray)
        val methods = exprClass?.methods
        if (methods == null || methods.isEmpty())
            throw AssertionError("No main method was found.")
        for (method in methods) {
            if (method.name != "main") {
                continue
            }
            method.invoke(null, arrayOf<String>())
            assertEquals(output, out.toString())
        }
    }

    @Test fun incDecTest() = testCompiler(
            "+++-+++.",
            arrayOf(),
            5.toByte().toChar().toString()
    )

    @Test fun inputOutputTest() = testCompiler(
            ",.>,.>,.",
            arrayOf('a'.toInt(), 'b'.toInt(), 'c'.toInt()),
            "abc"
    )

    @Test fun bracketsTest() = testCompiler(
            "[ [] [ [] [ [] [ [] [[]] [] ] [] ] [] ] [] ],.",
            arrayOf('a'.toInt()),
            "a"
    )

    @Test fun helloWorldTest() = testCompiler(
            "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>." +
                    ">---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.",
            arrayOf(),
            "Hello World!\n"
    )
}
