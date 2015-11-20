package hw9

import org.junit.Test
import kotlin.test.assertEquals

public abstract class TestBrainfuckRunner: TestRunner() {

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

    @Test
    fun testLoopStart() {
        testCode("[>+<]++++++[>++++++++<-]>.", "", "0")
    }

}