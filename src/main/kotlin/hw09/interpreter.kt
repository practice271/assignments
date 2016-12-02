/* Brainfuck interpreter
(expectation: 4 h; reality: 6 h)
by Sokolova Polina */

package hw09

public class Interpreter() {
    var i = 0
    var array = Array(30000, { 0 })
    public fun interpret(s : String) {
        val length = s.length
        var counter = 0
        var j = 0
        fun findEnd(start : Int) : Int {
            var pos = start
            while (pos < length) {
                when (s[pos]) {
                    '[' -> { counter++ }
                    ']' -> {
                        counter--
                        if (counter == 0) { return pos }
                    }
                }
                pos++
            }
            println("Not enough ']'")
            return length
        }
        fun findStart(end : Int) : Int {
            var pos = end - 1
            while (pos >= 0) {
                when (s[pos]) {
                    ']' -> { counter++ }
                    '[' -> {
                        counter--
                        if (counter == 0) { return pos - 1 }
                    }
                }
                pos--
            }
            println("Not enough '['")
            return length
        }
        while (j < length) {
            when(s[j]) {
                '>' -> { i++ }
                '<' -> { i-- }
                '+' -> { array[i]++ }
                '-' -> { array[i]-- }
                '.' -> { print(array[i].toChar()) }
                ',' -> { array[i] = readLine()?.toInt() ?: array[i] }
                '[' -> {
                    counter++
                    if (array[i] == 0) { j = findEnd(j + 1) }
                    }
                ']' -> {
                    if (array[i] != 0) { j = findStart(j) }
                    else {
                        if (counter > 0) { counter-- }
                        else { println("Not enough '['"); j = length }
                    }
                }
            }
            j++
        }
    }
}
