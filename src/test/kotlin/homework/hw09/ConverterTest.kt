/*
 * Homework 9 (03.11.2015)
 * Tests for converter.
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw09

import org.junit.Test
import kotlin.test.assertEquals

internal class ConverterTest {
    private val interpreter = BrainfuckInterpreter()
    private val converter   = Converter()

    /**
     * Simple:     935
     * Algorithm1: 231
     * Algorithm2: 229
     * Algorithm3: 162
     * Best:       162
     */
    @Test fun test01() {
        val text = "Brainfuck"
        test(text)
    }

    /**
     * Simple:     1109
     * Algorithm1:  416
     * Algorithm2:  385
     * Algorithm3:  201
     * Best:        179
     */
    @Test fun test02() {
        val text = "Hello World!"
        test(text)
    }

    /**
     * Simple:     12340
     * Algorithm1:  3590
     * Algorithm2:  3022
     * Algorithm3:  1960
     * Best:        1960
     */
    @Test fun test03() {
        val text = "Brainfuck is an esoteric programming language created in 1993 by Urban Muller, " +
                   "and notable for its extreme minimalism. (c) Wikipedia"
        test(text)
    }

    /**
     * Simple:     148
     * Algorithm1: 103
     * Algorithm2:  97
     * Algorithm3:  51
     * Best:        36
     */
    @Test fun test04() {
        val text = "NB"
        test(text)
    }

    /**
     * Simple:     5208
     * Algorithm1:  261
     * Algorithm2:  261
     * Algorithm3:   81
     * Best:         71
     */
    @Test fun test05() {
        var text = ""
        for (i in 1 .. 42) text += 'z'
        test(text)
    }


    /**
     * Simple:     4179
     * Algorithm1:  257
     * Algorithm2:  257
     * Algorithm3:  502
     * Best:        257
     */
    @Test fun test06() {
        var text = ""
        for (i in 1 .. 21) text += "ab"
        test(text)
    }

    /**
     * Gets a [text] and checks that outputs of all
     * converting methods are equal to this [text].
     */
    private fun test(text: String) {
        val simple     = converter.simpleMethod(text)
        val algorithm1 = converter.algorithm1(text)
        val algorithm2 = converter.algorithm2(text)
        val algorithm3 = converter.algorithm3(text, 16) // 16 is optimal x
        val best       = converter.convert(text)

        println(simple.length)
        println(algorithm1.length)
        println(algorithm2.length)
        println(algorithm3.length)
        println(best.length)

        assertEquals(text, interpreter.interpret(simple, ""))
        assertEquals(text, interpreter.interpret(algorithm1, ""))
        assertEquals(text, interpreter.interpret(algorithm2, ""))
        assertEquals(text, interpreter.interpret(algorithm3, ""))
        assertEquals(text, interpreter.interpret(best, ""))
    }
}