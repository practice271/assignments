package hw9

import org.junit.Test
import kotlin.test.assertEquals

public class TestGen {

    fun testCode(source: String, input: String, output: String) {
        val res = BrainRun().runCode(source, input)
        assertEquals(output, res)
    }

    @Test
    fun testHello() {
        testCode("++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++" +
                ".>+.+++++++..+++.>++.<<+++++++++++++++.>.+++." +
                "------.--------.>+.>.", "", "Hello World!\n")
    }

    @Test
    fun testEcho() {
        testCode(",..", "a", "aa")
    }

    @Test
    fun testReverse() {
        testCode(">,[>,]<[.<]", "abcdef", "fedcba")
    }

    @Test
    fun testAdd() {
        testCode(",>,>++++++++[>++++++<-]"+
                 ">[<+<-<->>>-]<<[<+>-]>[<<+>>-]<<.",
                 "23", "5")
    }

}