package homework.hw09

import java.io.Reader
import java.io.Writer

/**
 * An interpreter for Brainfuck.
 * @author Kirill Smirenko
 */
public object BrainfuckInterpreter {
    private val memorySize = 30000

    public fun interpret(commands : String, reader : Reader, writer : Writer) {
        val byteZero : Byte = 0
        val memory = Array<Byte>(memorySize) { byteZero }
        var memPtr = 0
        var commPtr = 0
        while (commPtr < commands.length) {
            when (commands[commPtr]) {
                Tokens.NEXT.v ->
                    memPtr = (memPtr + 1) % memorySize
                Tokens.PREV.v ->
                    memPtr = (memPtr - 1 + memorySize) % memorySize
                Tokens.INC.v ->
                    memory[memPtr]++
                Tokens.DEC.v ->
                    memory[memPtr]--
                Tokens.WRITE.v ->
                    writer.write(memory[memPtr].toChar().toString())
                Tokens.READ.v ->
                    memory[memPtr] = reader.read().toByte()
                Tokens.LBRACKET.v ->
                    if (memory[memPtr] == byteZero) {
                        var leftBracketCount = 1;
                        while (leftBracketCount > 0) {
                            when (commands[++commPtr]) {
                                Tokens.LBRACKET.v -> leftBracketCount++
                                Tokens.RBRACKET.v -> leftBracketCount--
                            }
                        }
                    }
                Tokens.RBRACKET.v ->
                    if (memory[memPtr] != byteZero) {
                        var rightBracketCount = 1;
                        while (rightBracketCount > 0) {
                            when (commands[--commPtr]) {
                                Tokens.RBRACKET.v -> rightBracketCount++
                                Tokens.LBRACKET.v -> rightBracketCount--
                            }
                        }
                    }
            }
            commPtr++
        }
    }
}