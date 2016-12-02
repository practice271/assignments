/*
 * Homework 9 (03.11.2015)
 * Tests for Brainfuck interpreter and compiler.
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw09

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

internal class BrainfuckTest {
    private val interpreter = BrainfuckInterpreter()
    private val compiler    = BrainfuckCompiler()

    /** Prints "Hello World!". */
    @Test fun test01() {
        val program = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]" +
                      ">>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++."
        testInterpreter(program, "", "Hello World!\n")
        testCompiler(program, "", "Hello World!\n")
    }

    /** Gets two numbers and returns their sum. */
    @Test fun test02() {
        val program = ",>,<[->+<]>."
        testInterpreter(program, 100.toChar().toString() + 25.toChar().toString(), 125)
        testCompiler(program, 100.toChar().toString() + 25.toChar().toString(), 125)
    }

    /** Prints 13 first numbers of Fibonacci sequence. */
    @Test fun test03() {
        val program = "+++++++++++>+.>+.<<[>[->>+<<]>[-<+>>+<]>[-<+>]<.<<-]"
        testInterpreter(program, "", arrayOf(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233))
        testCompiler(program, "", arrayOf(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233))
    }

    /**
     * Gets a [program] on Brainfuck, string with [input] data and [result].
     * Interprets this [program] and checks that output is equal to [result].
     */
    private fun testInterpreter(program : String, input : String, result : Any) {
        val output = interpreter.interpret(program, input)
        test(output, result)
    }

    /**
     * Gets a [program] on Brainfuck, string with [input] data and [result].
     * Compiles this [program] and checks that output is equal to [result].
     */
    private fun testCompiler(program : String, input : String, result : Any) {
        val byteArray = compiler.compile(program, input)
        ClassRunner().loadClassAndRun(byteArray, "BrainfuckCompiler")

        val output = File("programOutput.out").readText()
        test(output, result)
    }

    /**
     * Gets an [output] of Brainfuck program and [result].
     * Checks that [output] is equal to [result].
     */
    private fun test(output : String, result : Any) {
        var temp = ""
        var res  = result.toString()

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