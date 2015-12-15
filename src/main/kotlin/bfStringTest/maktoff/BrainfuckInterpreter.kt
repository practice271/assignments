/*
 * Homework 9 (17.11.2015)
 * Interpreter for Brainfuck language
 *
 * Author: Mikhail Kita, group 271
 */

package bfStringTest.maktoff

import java.util.*

/**
 * Interpreter for Brainfuck language.
 *
 * NB: it is not classic distribution of Brainfuck.
 * This implementation can resize array if necessary.
 */
public class BrainfuckInterpreter {
    /** Returns an array with the specified [size]. */
    private fun newArray(size : Int) = Array(size, { 0 })

    /**
     * Gets a [program] on Brainfuck and string with [input] data
     * and then tries to interpret it. Returns program output.
     */
    public fun interpret(program : String, input : String) : String {
        var array  = newArray(1)
        var index  = 0
        var output = ""

        val inputStream = input.iterator()
        val stack = Stack<Int>()

        var i = 0
        while (i < program.length) {
            when (program[i]) {
                '>'  -> {
                    index++

                    // resize of array
                    if (index >= array.size) {
                        val temp = newArray(array.size * 2)
                        System.arraycopy(array, 0, temp, 0, array.size)
                        array = temp
                    }
                }
                '<'  -> {
                    index--
                    if (index < 0) throw IndexOutOfBoundsException()
                }
                '+'  -> {
                    array[index]++
                    if (array[index] > 255) array[index] -= 256
                }
                '-'  -> {
                    array[index]--
                    if (array[index] < 0) array[index] += 256
                }
                '.'  -> output += (array[index]).toChar()
                ','  -> array[index] = inputStream.next().toInt()
                '['  -> stack.push(i)
                ']'  ->
                    if (array[index] == 0) stack.pop()
                    else i = stack.peek()
                else -> i += 0 // it is a comment. Ignore this symbol
            }
            i++
        }
        return output
    }
}