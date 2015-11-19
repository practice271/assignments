/*
 * Homework 9 (03.11.2015)
 * Tests for converter
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw09

import org.junit.Test
import kotlin.test.assertEquals

internal class ConverterTest() {
    private val interpreter = BrainfuckInterpreter()
    private val converter   = Converter()

    /**
     * Simple:     935
     * Algorithm1: 337
     * Algorithm2: 339
     * Algorithm3: 162
     * Best:       162
     */
    @Test fun test01() {
        val text = "Brainfuck"
        test(text)
    }

    /**
     * Simple:     1109
     * Algorithm1:  514
     * Algorithm2:  494
     * Algorithm3:  222
     * Best:        191
     */
    @Test fun test02() {
        val text = "Hello World!"
        test(text)
    }

    /**
     * Simple:     12340
     * Algorithm1:  3809
     * Algorithm2:  3253
     * Algorithm3:  2018
     * Best:        2018
     */
    @Test fun test03() {
        val text = "Brainfuck is an esoteric programming language created in 1993 by Urban Muller, " +
                   "and notable for its extreme minimalism. (c) Wikipedia"
        test(text)
    }

    /**
     * Simple:     148
     * Algorithm1: 173
     * Algorithm2: 161
     * Algorithm3:  51
     * Best:        36
     */
    @Test fun test04() {
        val text = "NB"
        test(text)
    }

    /**
     * Simple:     5208
     * Algorithm1:  421
     * Algorithm2:  421
     * Algorithm3:  901
     * Best:        316
     */
    @Test fun test05() {
        var text = ""
        for (i in 1 .. 42) text += 'z'
        test(text)
    }

    /**
     * Gets a [program] on Brainfuck and checks that outputs of
     * all converting methods is equal to this [program].
     */
    private fun test(program : String) {
        val simple     = converter.simpleMethod(program)
        val algorithm1 = converter.algorithm1(program)
        val algorithm2 = converter.algorithm2(program)
        val algorithm3 = converter.algorithm3(program, 16) // 16 is optimal x
        val best       = converter.convert(program)

        println(simple.length)
        println(algorithm1.length)
        println(algorithm2.length)
        println(algorithm3.length)
        println(best.length)

        assertEquals(program, interpreter.interpret(simple, ""))
        assertEquals(program, interpreter.interpret(algorithm1, ""))
        assertEquals(program, interpreter.interpret(algorithm2, ""))
        assertEquals(program, interpreter.interpret(algorithm3, ""))
        assertEquals(program, interpreter.interpret(best, ""))
    }
}