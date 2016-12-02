package homework9

import org.junit.Test
import kotlin.test.assertEquals

class HW09 {
    //Tests for Interpreter
    var i = BFInterpreter()

    @Test
    fun testIHello1() {
        assertEquals(i.interpret("+++++++++++++++++++++++++++++++++++++++++++++" +
                "+++++++++++++++++++++++++++.+++++++++++++++++" +
                "++++++++++++.+++++++..+++.-------------------" +
                "---------------------------------------------" +
                "---------------.+++++++++++++++++++++++++++++" +
                "++++++++++++++++++++++++++.++++++++++++++++++" +
                "++++++.+++.------.--------.------------------" +
                "---------------------------------------------" +
                "----.-----------------------.", ""), "Hello World!\n")
    }

    @Test
    fun testIHello2() {
        assertEquals(i.interpret("++++++++++[>+++++++>++++++++++>+++>+<<<<-]" +
                ">++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.", ""), "Hello World!\n")
    }

    @Test
    fun testIHello3() {
        assertEquals(i.interpret(",.,.,.,.,.,.,.,.,.,.,.", "Hello World"), "Hello World")
    }

    @Test
    fun testIcopy() {
        assertEquals(i.interpret(",[>+>+<<-]>.", "H"), "H")
    }

    //Tests for Compiler
    var c = BFCompiler()

    @Test
    fun testCHello1() {
        assertEquals(c.compile("+++++++++++++++++++++++++++++++++++++++++++++" +
                "+++++++++++++++++++++++++++.+++++++++++++++++" +
                "++++++++++++.+++++++..+++.-------------------" +
                "---------------------------------------------" +
                "---------------.+++++++++++++++++++++++++++++" +
                "++++++++++++++++++++++++++.++++++++++++++++++" +
                "++++++.+++.------.--------.------------------" +
                "---------------------------------------------" +
                "----.-----------------------.", ""), "Hello World!\n")
    }

    @Test
    fun testCHello2() {
        assertEquals(c.compile("++++++++++[>+++++++>++++++++++>+++>+<<<<-]" +
                ">++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.", ""), "Hello World!\n")
    }

    @Test
    fun testCHello3() {
        assertEquals(c.compile(",.,.,.,.,.,.,.,.,.,.,.", "Hello World"), "Hello World")
    }

    @Test
    fun testCcopy() {
        assertEquals(c.compile(",[>+>+<<-]>.", "H"), "H")
    }
}