package hw09

import kotlin.properties.Delegates

internal class brainfuckInterpeter() {
    var size = 10
    var tape = Array(size, {0})
    var tapeInd = size / 2

    private fun enlargeArray() {
        val newAr = Array(size * 2, {0})
        System.arraycopy(tape, 0, newAr, size / 2, size)
        tapeInd += size / 2
        size *= 2
        tape = newAr
    }

    public fun interpete(input: String) {
        var i = 0
        while (i < input.length) {
            when (input[i]) {
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
                '.' -> print("${tape[tapeInd].toChar()} ")
                ',' -> tape[tapeInd] = System.`in`.read()
                '[' -> {
                    var bracketCounter = 1
                    val cycle = StringBuilder()
                    i++
                    while(true){
                        if (input[i] == '[') bracketCounter++
                        if (input[i] == ']') bracketCounter--
                        if (bracketCounter == 0) {
                            break
                        }
                        cycle.append(input[i])
                        i++
                    }
                    while (tape[tapeInd] != 0) interpete(cycle.toString())
                }
            }
            i++
        }
    }
}

public fun main(args : Array<String>) {
    val helloWorld = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++."
    val simple = "+++[>+<-]>."
    val hw = "Hello World!"
    val br_hw = asciiToBrainfuck().interprete(hw)
    //val br_hw = asciiToBrainfuck().interprete("'Cause the dead don't shuffle - they run! Brush through streets of slaughter, clutching your gun!")
      //  asciiToBrainfuck().interprete("abba ")
    brainfuckInterpeter().interpete(br_hw)
}