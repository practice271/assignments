/*
 * Homework 9 (17.11.2015)
 * Converter from ASCII text to Brainfuck program.
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw09

import java.util.*

/**
 * Converter from ASCII text to Brainfuck program.
 *
 * Contains four methods for converting: simple and three algorithms of optimisation.
 * Method [convert] returns best solution.
 *
 * First algorithm is effective for strings with medium length.
 * In the general case it reduces output in 3 times compared to simple method.
 *
 * Second algorithm is effective for long strings.
 * In the general case it reduces output in 4 times compared to simple method.
 *
 * Third algorithm is the most effective for literary text.
 * In the general case it reduces output in 6 times compared to simple method.
 */
public class Converter() {
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
        for (i in 0 .. text.length) program += '>'
        for (i in 0 .. value - 1)   program += '-'
        for (i in 0 .. text.length) program += '<'

        program += "[>"
        for (i in 0 .. value - 1) program += '+'
        program += "][<]>"

        for (c in text) {
            var sub = c.toInt() - value
            if (sub >= 0)
                for (i in 1 .. sub) program += '+'
            else {
                sub *= -1
                for (i in 1 .. sub) program += '-'
            }
            program += ".>"
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
        for (c in text) {
            val n = c.toInt() / x
            program += '>'
            for (i in 1 .. n) program += "+"

            val m = c.toInt() - n * x
            temp += '>'
            for (i in 1 .. m) temp += "+"
            temp += '.'
        }
        for (c in text) program += '<'
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