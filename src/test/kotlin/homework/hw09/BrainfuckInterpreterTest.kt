/*
 * Homework 9 (03.11.2015)
 * Tests for Brainfuck interpreter
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw09

import org.junit.Test
import kotlin.test.assertEquals

internal class BrainfuckInterpreterTest() {
    private val interpreter = BrainfuckInterpreter()

    /** Prints "Hello World!". */
    @Test fun test01() {
        val program = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]" +
                      ">>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++."
        test(program, "", "Hello World!\n")
    }

    /** Gets two numbers and returns their sum. */
    @Test fun test02() {
        val program = ",>,<[->+<]>."
        test(program, 100.toChar().toString() + 25.toChar().toString(), 125)
    }

    /** Prints 13 first numbers of Fibonacci sequence. */
    @Test fun test03() {
        val program = "+++++++++++>+.>+.<<[>[->>+<<]>[-<+>>+<]>[-<+>]<.<<-]"
        test(program, "", arrayOf(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233))
    }

    /**
     * Gets a [program] on Brainfuck, string with [input] data and [result].
     * Checks that program output is equal to [result].
     */
    private fun test(program : String, input : String, result : Any) {
        val output = interpreter.interpret(program, input)
        var temp   = ""
        var res    = result.toString()

        when(result) {
            is Int      -> for (c in output) temp += c.toInt().toString()
            is String   -> temp = output
            is Array<*> -> {
                for (c in output) temp += c.toInt().toString() + ' '
                res = ""
                for (i in result) res += i.toString() + ' '
            }
        }
        assertEquals(res, temp)
    }
}