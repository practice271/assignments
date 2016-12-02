package hw09

import org.junit.Test
import kotlin.test.assertEquals

class InterpreterTests {
    @Test fun simpleTest() {
        assertEquals(parser(">>>>++++---<<<<"), "")
    }
    @Test fun helloWorldTest() {
        assertEquals(parser(
                "+++++++++++++++++++++++++++++++++++++++++++++" +
                "+++++++++++++++++++++++++++.+++++++++++++++++" +
                "++++++++++++.+++++++..+++.-------------------" +
                "---------------------------------------------" +
                "---------------.+++++++++++++++++++++++++++++" +
                "++++++++++++++++++++++++++.++++++++++++++++++" +
                "++++++.+++.------.--------.------------------" +
                "---------------------------------------------" +
                "----.-----------------------.")
                ,"Hello World!\n")
    }
    @Test fun helloWorldWithBracketsTest() {
        assertEquals(parser(
                "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++" +
                ".>+.+++++++..+++.>++.<<+++++++++++++++.>.+++." +
                "------.--------.>+.>."),"Hello World!\n")
    }
    @Test fun notEnoughOpeningBracketsTest() {
        assertEquals(parser(
                "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++" +
                        ".>+.+++++++..+++.>++.<<+++++++++++++++.>.+++." +
                        "------.--------.>+.>.]]]]]]]"),"Not enough opening brackets")
    }
    @Test fun notEnoughClosingBracketsTest() {
        assertEquals(parser(
                "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++" +
                        ".>+.+++++++..+++.>++.<<+++++++++++++++.>.+++." +
                        "------.--------.>+.>.[[[[[["),"Not enough closing brackets")
    }
}