package hw9

import java.util.*

class BrainInterpreter: CodeRunner {

    override fun runCode(source: String, input: String): String {
        var ip = 0
        var mp = 0
        var inp = 0
        val ins = input + '\u0000'
        var output = ""
        var memory = ByteArray(1024 * 32)
        val jumpStack = Stack<Int>()
        while (ip < source.length) {
            when (source[ip]) {
                '>' -> mp++
                '<' -> mp--
                '+' -> memory[mp]++
                '-' -> memory[mp]--
                '.' -> output += (memory[mp].toInt() and 0xFF).toChar()
                ',' -> memory[mp] = ins[inp++].toByte()
                '[' -> jumpStack.push(ip)
                ']' ->  {
                    if (memory[mp].toInt() == 0)
                        jumpStack.pop()
                    else
                        ip = jumpStack.peek()
                }
                else -> {}
            }
            ip++
        }
        return output
    }

}