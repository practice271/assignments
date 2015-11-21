package hw09

import java.util.*

public class Interpreter {

    private val sc = Scanner(System.`in`)
    private val LENGTH = 65535
    private val mem = ByteArray(LENGTH)
    private var pointer = 0

    public fun interpret(code: String) {
        var l = 0
        var i = 0
        while (i < code.length) {
            when (code[i]) {
                '>' -> pointer = if (pointer == LENGTH - 1) 0 else pointer + 1
                '<' -> pointer = if (pointer == 0) LENGTH - 1 else pointer - 1
                '+' -> mem[pointer]++
                '-' -> mem[pointer]--
                '.' -> print(mem[pointer].toChar())
                ',' -> mem[pointer] = sc.next()[0].toByte()
                '[' ->
                if (mem[pointer] == 0.toByte()) {
                    i++
                    while (l > 0 || code[i] != ']') {
                        if (code[i] == '[') l++
                        if (code[i] == ']') l--
                        i++
                    }
                }
                ']' ->
                if (mem[pointer] != 0.toByte()) {
                    i--
                    while (l > 0 || code[i] != '[') {
                        if (code[i] == ']') l++
                        if (code[i] == '[') l--
                        i--
                    }
                    i--
                }
            }
            i++
        }
    }
}

/*
fun main(args: Array<String>) {
    var code1 = "++++++ [ > ++++++++++ < - ] > +++++ ."
    val interpreter = Interpreter
    interpreter.interpret(code1)
}*/
