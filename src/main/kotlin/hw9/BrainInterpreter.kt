package hw9

import java.util.*

class BrainInterpreter: CodeRunner {

    var source = ""
    var input = ""
    var jumpTable = IntArray(0)

    override fun runCode(s: String, i: String): String {
        source = s
        input = i + '\u0000'
        setupJumps()
        return runCode()
    }

    private fun setupJumps() {
        jumpTable = IntArray(source.length)
        val openStack = Stack<Int>()

        for (i in source.indices)
            when (source[i]) {
                '[' -> openStack.push(i)
                ']' -> {
                    val j = openStack.pop()
                    jumpTable[i] = j - 1
                    jumpTable[j] = i
                }
                else -> { }
            }
    }

    public fun runCode(): String {
        var ip = 0
        var mp = 0
        var inp = 0
        var output = ""
        var memory = ByteArray(1024 * 32)
        while (ip < source.length) {
            when (source[ip]) {
                '>' -> mp++
                '<' -> mp--
                '+' -> memory[mp]++
                '-' -> memory[mp]--
                '.' -> output += (memory[mp].toInt() and 0xFF).toChar()
                ',' -> memory[mp] = input[inp++].toByte()
                '[' -> if (memory[mp].toInt() == 0) ip = jumpTable[ip]
                ']' -> ip = jumpTable[ip]
                else -> {
                }
            }
            ip++
        }
        return output
    }

}