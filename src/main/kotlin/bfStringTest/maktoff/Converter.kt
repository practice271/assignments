/*
 * Homework 9 (17.11.2015)
 * Converter from ASCII text to Brainfuck program.
 *
 * Author: Mikhail Kita, group 271
 */

package bfStringTest.maktoff

import java.util.*

/**
 * Converter from ASCII text to Brainfuck program.
 *
 * Contains four methods for converting: simple and three algorithms of optimisation.
 * Method [convert] returns best solution.
 *
 * First algorithm is effective for strings with medium length (but no longer than 256 symbols).
 * In the general case it reduces output in 3 times compared to simple method.
 *
 * Second algorithm is effective for long strings (but no longer than 256 symbols).
 * In the general case it reduces output in 4 times compared to simple method.
 *
 * Third algorithm is the most effective for literary text with arbitrary length
 * In the general case it reduces output in 6 times compared to simple method.
 */
public class Converter {
    /** Simple solution. */
    public fun simpleMethod(text : String) : String {
        var program = ""
        for (c in text) {
            val n = c.toInt()
            for (i in 1 .. n) program += '+'
            program += ".>"
        }
        return program
    }

    /** Returns arithmetic mean of symbols of given [text]. */
    private fun arithmeticMean(text : String) : Int {
        var result = 0
        for (c in text) result += c.toInt()
        return result / text.length
    }

    /** Returns median of symbols of given [text]. */
    private fun median(text : String) : Int {
        val q = PriorityQueue<Int>()
        for (i in 0 .. text.length - 1) {
            q.offer(text[i].toInt())
            if (i > text.length / 2) q.poll()
        }
        return q.peek()
    }

    /** Contains common part for first and second algorithms. */
    private fun convertWith(text : String, value : Int) : String {
        var program = ""
        for (i in 1 .. text.length) program += '+'

        program += "[>[>]"
        for (i in 1 .. value) program += '+'
        program += "[<]<-]>>"

        for (i in 0 .. text.length - 1) {
            val c = text[i]
            if (i > 0 && c == text[i - 1]) program += '.'
            else {
                if (i > 0) program += '>'
                val sub = c.toInt() - value
                if (sub >= 0) (1 .. sub).forEach { program += '+' }
                else (1 .. -sub).forEach { program += '-' }
                program += '.'
            }
        }
        return program
    }

    /** Converts given [text] to Brainfuck program using arithmetic mean. */
    public fun algorithm1(text : String) = convertWith(text, arithmeticMean(text))

    /** Converts given [text] to Brainfuck program using median. */
    public fun algorithm2(text : String) = convertWith(text, median(text))

    /** Converts given [text] to Brainfuck program. */
    public fun algorithm3(text : String, x : Int) : String {
        var program = ""
        for (i in 1 .. x) program += '+'
        program += '['
        var temp = ""
        var counter = 0
        for (i in 0 .. text.length - 1) {
            val c = text[i]
            if (i > 0 && c == text[i - 1]) temp += '.'
            else {
                counter++
                var n = c.toInt() / x
                if (c.toInt() - n * x > (n + 1) * x - c.toInt()) n++
                program += '>'
                (1 .. n).forEach { program += "+" }

                val m = c.toInt() - n * x
                temp += '>'
                if (m >= 0) (1 .. m).forEach { temp += "+" }
                else (1 .. -m).forEach { temp += "-" }
                temp += '.'
            }
        }
        for (i in 1 .. counter) program += '<'
        program += "-]" + temp
        return program
    }

    /** Returns best solution. */
    public fun convert(text : String) : String {
        val temp   = simpleMethod(text)
        var min    = temp.length
        var result = temp

        fun check(program : String) {
            if (program.length < min) {
                min = program.length
                result = program
            }
        }

        check(algorithm1(text))
        check(algorithm2(text))
        for (x in 1 .. 255) check(algorithm3(text, x))
        return result
    }
}