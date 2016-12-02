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
     * Algorithm1: 230
     * Algorithm2: 228
     * Algorithm3: 145
     * Best:       143
     */
    @Test fun test01() {
        val text = "Brainfuck"
        test(text)
    }

    /**
     * Simple:     1109
     * Algorithm1:  396
     * Algorithm2:  376
     * Algorithm3:  161
     * Best:        158
     */
    @Test fun test02() {
        val text = "Hello World!"
        test(text)
    }

    /**
     * Simple:     12340
     * Algorithm1:  3517
     * Algorithm2:  2961
     * Algorithm3:  1684
     * Best:        1631
     */
    @Test fun test03() {
        val text = "Brainfuck is an esoteric programming language created in 1993 by Urban Muller, " +
                   "and notable for its extreme minimalism. (c) Wikipedia"
        test(text)
    }

    /**
     * Simple:     80
     * Algorithm1: 93
     * Algorithm2: 93
     * Algorithm3: 30
     * Best:       26
     */
    @Test fun test04() {
        val text = "N"
        test(text)
    }

    /**
     * Simple:     5208
     * Algorithm1:  219
     * Algorithm2:  219
     * Algorithm3:   78
     * Best:         71
     */
    @Test fun test05() {
        var text = ""
        for (i in 1 .. 42) text += 'z'
        test(text)
    }

    /**
     * Simple:     4179
     * Algorithm1:  256
     * Algorithm2:  256
     * Algorithm3:  502
     * Best:        256
     */
    @Test fun test06() {
        var text = ""
        for (i in 1 .. 21) text += "ab"
        test(text)
    }

    /**
     * Stress test
     *
     * Simple: 159250
     * Best:    14290
     */
    @Test fun test07() {
        var text = ""
        for (i in 1 .. 250) text += "Kotlin"

        val simple = converter.simpleMethod(text)
        val best   = converter.algorithm3(text, 37)

        println(simple.length)
        println(best.length)

        assertEquals(text, interpreter.interpret(simple, ""))
        assertEquals(text, interpreter.interpret(best, ""))
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