package hw09

import org.junit.Test
import java.io.InputStream
import java.io.OutputStream
import kotlin.test.assertEquals

public class brainfuckInterpreterTest {

    internal class TestOutputStream() : OutputStream() {
        public val output = StringBuilder()

        override fun write(byte: Int) {
            output.append(byte.toChar())
        }
    }

    internal class TestInputStream(private val input: Array<Int>) : InputStream() {
        private var index = 0

        override fun read(): Int {
            return input[index++]
        }

        private fun flush() {
        }
    }

    @Test fun wrongBracketsTest01() {
        val outStream = TestOutputStream()
        val program = "+[>[+>]"
        val bfInterpr = BrainfuckInterpreter(program, TestInputStream(arrayOf()), outStream)
        var error = ""
        try {
            bfInterpr.run()
        } catch(exception: CorrespondingRightBracketNotFound) {
            error = exception.message!!
        }
        assertEquals("No corresponding right bracket for left bracket", error)
    }

    @Test fun wrongBracketsTest02() {
        val outStream = TestOutputStream()
        val program = ">+[>]+]>[+"
        val bfInterpr = BrainfuckInterpreter(program, TestInputStream(arrayOf()), outStream)
        var error = ""
        try {
            bfInterpr.run()
        } catch(exception: UnexpectedRightBracket) {
            error = exception.message!!
        }
        assertEquals("Unexpected right bracket at 6", error)
    }

    @Test fun outOfBoundsTest() {
        val outStream = TestOutputStream()
        val program = "<"
        val bfInterpr = BrainfuckInterpreter(program, TestInputStream(arrayOf()), outStream)
        var error = ""
        try {
            bfInterpr.run()
        } catch(exception: ArrayIndexOutOfBoundsException) {
            error = exception.message!!
        }
        assertEquals("Data tape has been accessed with an negative index", error)
    }

    @Test fun incDecTest() {
        val outStream = TestOutputStream()
        val program = "+++-+++."
        val bfInterpr = BrainfuckInterpreter(program, TestInputStream(arrayOf()), outStream)
        bfInterpr.run()
        assertEquals(5.toByte().toChar().toString(), outStream.output.toString())
    }

    @Test fun InputOutputTest() {
        val outStream = TestOutputStream()
        val program = ",.>,.>,."
        val inputArray = arrayOf('a'.toInt(), 'b'.toInt(), 'c'.toInt())
        val bfInterpr = BrainfuckInterpreter(program, TestInputStream(inputArray), outStream)
        bfInterpr.run()
        assertEquals("abc", outStream.output.toString())
    }

    @Test fun bracketsTest() {
        val outStream = TestOutputStream()
        val program = "[ [] [ [] [ [] [ [] [[]] [] ] [] ] [] ] [] ],."
        val inputArray = arrayOf('a'.toInt())
        val bfInterpr = BrainfuckInterpreter(program, TestInputStream(inputArray), outStream)
        bfInterpr.run()
        assertEquals("a", outStream.output.toString())
    }

    @Test fun helloWorldTest() {
        val outStream = TestOutputStream()
        val helloWorldProgram = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>." +
                ">---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++."
        val bfInterpr = BrainfuckInterpreter(helloWorldProgram, TestInputStream(arrayOf()), outStream)
        bfInterpr.run()
        assertEquals("Hello World!\n", outStream.output.toString())
    }
}