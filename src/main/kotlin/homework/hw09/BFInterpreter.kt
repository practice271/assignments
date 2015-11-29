// Homework 9
// Alekseev Aleksei, group 271.

package homework.hw09

import java.util.*

public class BFInterpreter(private val arraySize : Int) {
    private var array = Array(arraySize, {0})
    private var index = 0
    public var output = ""
    private var stack = Stack<Int>()
    private var leftBr = 0; private var rightBr = 0
    public fun handle(code : String, input : String) {
        index = 0
        output = ""
        var inputIterator = input.iterator()
        stack = Stack<Int>()
        leftBr = 0; rightBr = 0
        for (j in 0 .. code.length - 1) {
            if (code[j] == '[') leftBr++
            if (code[j] == ']') rightBr++
        }
        if (leftBr != rightBr) output = "Incorrect data"
        else {
            var i = 0
            while (i < code.length) {
                when (code[i]) {
                    '>' -> if (index == arraySize - 1) index = 0 else index++
                    '<' -> if (index == 0) index = arraySize - 1 else index--
                    '+' -> array[index]++
                    '-' -> array[index]--
                    '.' -> output += (array[index]).toChar()
                    ',' -> array[index] = inputIterator.next().toInt()
                    '[' -> stack.push(i)
                    ']' -> if (array[index] == 0) stack.pop() else i = stack.peek()
                    else -> i += 0
                }
                i++
            }
        }
    }
}