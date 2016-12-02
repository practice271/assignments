package hw9

import java.util.*

class BrainInterpreter: CodeRunner {

    private var source = ""
    private var input = ""
    private var jumpTable = IntArray(0)

    private val memSize = 32 * 1024

    override public fun runCode(s: String, i: String): String {
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
            }
    }

    public fun runCode(): String {
        var ip = 0
        var mp = 0
        var inp = 0
        var output = ""
        var memory = ByteArray(memSize)
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
            }
            ip++
        }
        return output
    }

}