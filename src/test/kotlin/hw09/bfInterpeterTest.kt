package hw09

import org.junit.Assert
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

/**
 * Created by Alexander on 20.11.2015.
 */
class bfInterpeterTest {
    @Test fun helloWorldTest() {
        //program from wikipedia
        val helloWorld = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++."
        val res = brainfuckInterpeter().interpete(helloWorld, false)
        assertEquals("Hello World!\n", res)
    }

    @Test fun simpleTest() {
        val code = "+++[+]."
        val res = brainfuckInterpeter().interpete(code, false)
        assertEquals("${0.toChar()}", res)
    }

    @Test fun ioTest() {
        val code = ",.,."
        val input = Stack<Byte>()
        input.push(-10)
        input.push(3)
        val res = brainfuckInterpeter().interpete(code, false, input)
        val expected = "${3.toChar()}${(-10).toChar()}"
        assertEquals(expected, res)
    }

    @Test fun allFunctionsTest() {
        //my program; writes on tape and prints as many '1's, as number typed in
        val code = ",[[>]++-.[<]>-]"
        val input = Stack<Byte>()
        input.push(3)
        val res = brainfuckInterpeter().interpete(code, false, input)
        val ch = 1.toChar()
        val expected = StringBuilder().append(ch).append(ch).append(ch).toString()
        assertEquals(expected, res)
    }

    @Test fun noCodeTest() {
        val res = brainfuckInterpeter().interpete("", false)
        assertEquals("", res)
    }

    @Test fun incorrectCodeTest() {
        val code = "+[+]]."
        try {
            brainfuckInterpeter().interpete(code)
        }
        catch (e : Exception) {
           assertEquals("Incorrect input", e.message)
           return
        }
        Assert.fail("This test should fail for input is incorrect")
    }
}