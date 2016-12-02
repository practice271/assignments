package hw09

import java.io.InputStream
import java.io.OutputStream
import java.util.*

public class UnexpectedRightBracket(message : String) : RuntimeException(message)
public class CorrespondingRightBracketNotFound(message : String) : RuntimeException(message)

public class BrainfuckInterpreter(
        private val program : String,
        private val inputStream : InputStream = System.`in`,
        private val outputStream : OutputStream = System.`out`) {

    private var instructionPointer: Int = 0
    private var dataPointer: Int = 0
    private var dataSize: Int = 128
    private var data = Array(dataSize, { 0.toByte() })

    //stores pointers to cells right after left brackets
    private var loopBeginnings = LinkedList<Int>()

    private fun getPointerToLoopBeginning(): Int? {
        if (loopBeginnings.isEmpty()) return null
        return loopBeginnings.getFirst()
    }

    private fun dataReallocate() {
        dataSize *= 2
        data = Arrays.copyOf(data, dataSize)
    }

    private fun getCorrespondingRightBracketIndex(fromIndex: Int): Int? {
        var leftBrackets = 0
        for (i in fromIndex..program.length - 1)
            when (program[i]) {
                '[' -> leftBrackets++
                ']' -> {
                    if (leftBrackets == 0) return i
                    leftBrackets--
                }
            }
        return null
    }

    private fun checkBrackets() {
        var leftBrackets = 0
        for (index in 0..program.length - 1)
            when (program[index]) {
                '[' -> leftBrackets++
                ']' -> {
                    if (leftBrackets == 0)
                        throw UnexpectedRightBracket("Unexpected right bracket at $index")
                    leftBrackets--
                }
            }
        if (leftBrackets != 0)
            throw CorrespondingRightBracketNotFound(
                    "No corresponding right bracket for left bracket")
    }

    public fun run() {
        checkBrackets()
        while (instructionPointer < program.length) {
            when (program[instructionPointer]) {
                '>' -> {
                    if (dataPointer == dataSize - 1)
                        dataReallocate()
                    dataPointer++
                }
                '<' -> {
                    if (dataPointer == 0)
                        throw ArrayIndexOutOfBoundsException(
                                "Data tape has been accessed with an negative index")
                    dataPointer--
                }
                '+' -> data[dataPointer]++
                '-' -> data[dataPointer]--
                '.' -> {
                    outputStream.write(data[dataPointer].toInt())
                    outputStream.flush()
                }
                ',' -> data[dataPointer] = inputStream.read().toByte()
                '[' -> {
                    if (data[dataPointer] != 0.toByte())
                        loopBeginnings.push(instructionPointer + 1)
                    else {
                        val index = getCorrespondingRightBracketIndex(instructionPointer + 1)
                        instructionPointer = index ?: throw CorrespondingRightBracketNotFound(
                                "No corresponding right bracket for left bracket at $instructionPointer")
                    }
                }
                ']' -> {
                    val pointer = getPointerToLoopBeginning() ?:
                            throw UnexpectedRightBracket("Unexpected right bracket at $instructionPointer")
                    if (data[dataPointer] != 0.toByte())
                        instructionPointer = pointer - 1
                    else
                        loopBeginnings.removeFirst()
                }
            }
            instructionPointer++
        }
    }
}