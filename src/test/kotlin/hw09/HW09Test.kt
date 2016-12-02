package hw09

import org.junit.Test;
import kotlin.test.assertEquals

public class HW09Test : OutputTest() {
    //Tests for Interpreter
    @Test fun helloWorld1() {
        val s = " ++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++" +
                ".>+.+++++++..+++.>++.<<+++++++++++++++.>.+++." +
                "------.--------.>+.>."
        val res = "Hello World!\n"
        Interpreter().interpret(s)
        assertEquals(res, output.toString())
    }
    @Test fun noBalance1() {
        val s = "]]]"
        val res = "Not enough '['\n"
        Interpreter().interpret(s)
        assertEquals(res, output.toString())
    }
    @Test fun noBalance2() {
        val s = "[[[[]]]"
        val res = "Not enough ']'\n"
        Interpreter().interpret(s)
        assertEquals(res, output.toString())
    }
    @Test fun noBalance3() {
        val s = "[]]"
        val res = "Not enough '['\n"
        Interpreter().interpret(s)
        assertEquals(res, output.toString())
    }
    @Test fun findEmpty() {
        val s = "+>+><<[>]>+++[<+++++++++++>-]<."
        val res = "!"
        Interpreter().interpret(s)
        assertEquals(res, output.toString())
    }
    @Test fun mul8_8() {
        val s = ">++++++++[<++++++++++>-]<.+++."
        val res = "PS"
        Interpreter().interpret(s)
        assertEquals(res, output.toString())
    }

    //Tests for Converter
    @Test fun helloWorld2() {
        val s = "Hello World!\n"
        val res = Converter(s).convert()
        Interpreter().interpret(res)
        assertEquals(s, output.toString())
    }
    @Test fun empty() {
        val s = ""
        val res = Converter(s).convert()
        Interpreter().interpret(res)
        assertEquals(s, output.toString())
    }
    @Test fun song1() {
        val s = "You can check out any time you like\n" +
                "But you can never leave!\n"

        val res = Converter(s).convert()
        Interpreter().interpret(res)
        assertEquals(s, output.toString())
    }
    @Test fun song2() {
        val s = "Empty spaces what are we living for\n" +
                "Abandoned places - I guess we know the score\n"
        val res = Converter(s).convert()
        Interpreter().interpret(res)
        assertEquals(s, output.toString())
    }
    @Test fun trash() {
        val s = "!*&545678-+=32>32&ˆ§/?}[))982@[%$'<12"
        val res = Converter(s).convert()
        Interpreter().interpret(res)
        assertEquals(s, output.toString())
    }
}