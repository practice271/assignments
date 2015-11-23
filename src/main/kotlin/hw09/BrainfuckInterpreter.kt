package hw09
import java.util.*

public class BrainfuckInterpreter {
    private val sc = Scanner(System.`in`)
    private val LENGTH = 10000
    private val memory = newArray(LENGTH)
    private var dataPointer: Int = 0
    public  var word = ""

    private fun newArray(size: Int) = Array(size, { 0 })

    fun interpret(code: String): String {
        var levelLoop = 0
        var index = 0

        while (index < code.length) {
            when (code[index]) {
                '>' -> {
                    dataPointer = if ((dataPointer == LENGTH - 1)) 0 else dataPointer + 1
                }
                '<' -> {
                    dataPointer = if ((dataPointer == 0)) LENGTH - 1 else dataPointer - 1
                }
                '+' -> {
                    memory[dataPointer]++
                }
                '-' -> {
                    memory[dataPointer]--
                }
                '.' -> {
                    word+= memory[dataPointer].toChar()
                }
                ',' -> {
                    memory[dataPointer] = sc.next().toInt()
                }
                '[' -> {
                    if (memory[dataPointer] == 0) {
                        index++
                        while (levelLoop > 0 || code[index] != ']') {
                            if (code[index] == '[') levelLoop++
                            if (code[index] == ']') levelLoop--
                            index++
                        }
                    }
                }
                ']' -> {
                    if (memory[dataPointer] != 0) {
                        index--
                        while (levelLoop > 0 || code[index] != '[') {
                            if (code[index] == ']') levelLoop++
                            if (code[index] == '[') levelLoop--
                            index--
                        }
                        index--
                    }
                }
            }
            index++
        }
        return word
    }
}

