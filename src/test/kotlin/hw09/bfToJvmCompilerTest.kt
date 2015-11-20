package hw09

import org.junit.Test
import java.io.InputStream
import java.io.PrintStream
import kotlin.test.assertEquals

/**
 * Created by Alexander on 20.11.2015.
 */

class bfToJvmCompilerTest() {
    val size = 10000
    val name = "ClassTest"

    @Test fun helloWorldTest() {
        val helloWorld = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++."
        val input = ""

        val output = StringOutput()
        System.setIn(StringInput(input))
        System.setOut(output)

        val res = BFToJvm(helloWorld).toByteCode(name, size)
        hw09.loadClassAndRun(res, name)

        assertEquals("Hello World!\n", output.toString())
    }

    @Test fun simpleTest() {
        val code = "+++[+]."
        val input = ""

        val output = StringOutput()
        System.setIn(StringInput(input))
        System.setOut(output)

        val res = BFToJvm(code).toByteCode(name, size)
        hw09.loadClassAndRun(res, name)

        assertEquals("${0.toChar()}", output.toString())
    }

    @Test fun ioTest() {
        val code = ",."
        val input = "1"

        val output = StringOutput()
        System.setIn(StringInput(input))
        System.setOut(output)

        val res = BFToJvm(code).toByteCode(name, size)
        hw09.loadClassAndRun(res, name)

        val outs = output.toString()
        assertEquals("1", outs)//output.toString())
    }

    @Test fun noCodeTest() {
        val code = ""
        val input = ""

        val output = StringOutput()
        System.setIn(StringInput(input))
        System.setOut(output)

        val res = BFToJvm(code).toByteCode(name, size)
        hw09.loadClassAndRun(res, name)

        assertEquals("", output.toString())
    }

    class StringInput(private val input: String) : InputStream() {
        var ind = 0
        override fun read() = if (ind < input.length) input[ind++].toInt() else -1
    }

    class StringOutput() : PrintStream("DOESNTMATTER.txt") {
        private var sb = StringBuilder()
        override fun toString() = sb.toString()
        override fun print(c : Char) {
            sb.append(c)
        }
    }
}