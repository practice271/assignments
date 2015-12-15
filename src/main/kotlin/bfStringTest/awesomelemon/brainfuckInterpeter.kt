package bfStringTest.awesomelemon

import java.util.*
import kotlin.properties.Delegates

public class brainfuckInterpeter() {
    var size = 10
    var tape = Array<Byte>(size, {0})
    var tapeInd = size / 2

    private fun enlargeArray() {
        val newAr = Array<Byte>(size * 2, {0})
        System.arraycopy(tape, 0, newAr, size / 2, size)
        tapeInd += size / 2
        size *= 2
        tape = newAr
    }

    public fun interpete(code: String, resToStdout: Boolean = true, input : Stack<Byte>? = null) : String?{
        var i = 0
        var res : StringBuilder? = null
        if (!resToStdout) res = StringBuilder()
        while (i < code.length) {
            when (code[i]) {
                '>' -> {
                    if (tapeInd == size - 1) enlargeArray()
                    tapeInd++
                }
                '<' -> {
                    if (tapeInd == 0) enlargeArray()
                    tapeInd--
                }
                '+' -> tape[tapeInd]++
                '-' -> tape[tapeInd]--
                '.' ->
                    if (resToStdout) print("${tape[tapeInd].toChar()}")
                    else res?.append(tape[tapeInd].toChar())
                ',' -> if (input == null) tape[tapeInd] = System.`in`.read().toByte()
                        else {
                    //tape[tapeInd] = input.reader().read().toByte()
                    tape[tapeInd] = input.pop().toByte()
                }
                '[' -> {
                    var bracketCounter = 1
                    val cycle = StringBuilder()
                    i++
                    while(true){
                        if (code[i] == '[') bracketCounter++
                        if (code[i] == ']') bracketCounter--
                        if (bracketCounter == 0) {
                            break
                        }
                        cycle.append(code[i])
                        i++
                    }
                    while (tape[tapeInd] != 0.toByte()) {
                        val curRes = interpete(cycle.toString(), resToStdout)
                        if (res != null) res.append(curRes)
                    }
                }
                ']' -> throw Exception("Incorrect input")
            }
            i++
        }
        return res?.toString() ?: null
    }
}

//public fun main(args : Array<String>) {
//    val helloWorld = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++."
//    val simple = "+++[>+<-]>."
//    val hw = "Hello World!"
//    val a= Byte.MAX_VALUE
//    val br_hw = asciiToBrainfuck().translate(hw)
//    //val br_hw = asciiToBrainfuck().interprete("'Cause the dead don't shuffle - they run! Brush through streets of slaughter, clutching your gun!")
//      //  asciiToBrainfuck().interprete("abba ")
//    brainfuckInterpeter().interpete(br_hw)
//}