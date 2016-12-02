package homeworks.hw09

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Ilya on 21.11.2015.
 */

public class InterpreterTest {
    @Test fun Test1() {
        val input      = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>."
        var inputArray = input.toCharArray()
        val actual     = interpreter().interpret(inputArray)
        val expected   = "Hello World!\n"
        assertEquals(expected, actual)
    }

    @Test fun Test2() {
        val input      = "+++[+++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>."
        var inputArray = input.toCharArray()
        val actual     = interpreter().interpret(inputArray)
        val expected   = null
        assertEquals(expected, actual)
    }

    @Test fun Test3() {
        val input      = "[-]>[-]<>+++++++++[<+++++++++>-]<--------.>+++++++[<+++++++>-]<------.>+++++++++[<--------->-]" +
                "<---.>+++++++++[<+++++++++>-]<--------.>+++[<+++>-]<+.>+++++++++[<--------->-]<--.>+++++++++[<+++++++++>-]" +
                "<----.>+++[<+++>-]<+++.>+++++++++[<--------->-]<--------.>++++[<++++>-]<+++.>++++[<---->-]<---.>+++++++++" +
                "[<+++++++++>-]<+++.>++++[<---->-]<+.>++++[<++++>-]<--.+."
        var inputArray = input.toCharArray()
        val actual     = interpreter().interpret(inputArray)
        val expected   = "It is my 3 test"
        assertEquals(expected, actual)
    }

    @Test fun Test4() {
        val input      = "[-]>[-]<>++++++++[<++++++++>-]<++++++++.>+++++[<+++++>-]<++++.>+++[<+++>-]<--..+++.>+++++++++" +
                "[<--------->-]<++.>+++++++[<+++++++>-]<++++++.>+++++[<+++++>-]<-.+++.>++[<-->-]<--.>+++[<--->-]" +
                "<+.>++++++++[<-------->-]<---.-.>++++++++[<++++++++>-]<+.>++[<++>-]<++.>++[<-->-]<--.>+++[<+++>-]" +
                "<-.>++[<++>-]<+."
        var inputArray = input.toCharArray()
        val actual     = interpreter().interpret(inputArray)
        val expected   = "Hello World! again"
        assertEquals(expected, actual)
    }
}