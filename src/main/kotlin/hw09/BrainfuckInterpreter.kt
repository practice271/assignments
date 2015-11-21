package hw09
import java.util.*

public class BrainfuckInterpreter {
    private val sc = Scanner(System.`in`)
    private val LENGTH = 10000
    private fun newArray(size : Int) = Array(size, { 0 })
    private val memory = newArray(LENGTH)
    private var dataPointer: Int = 0
    public  var word = ""

    fun interpret(code: String): String {
        var l = 0
        var i = 0
        var ind=0
        while (i < code.length) {
            when (code[i]) {
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
                    ind++
                }
                ',' -> {
                    memory[dataPointer] = sc.next().toInt()
                }
                '[' -> {
                    if (memory[dataPointer] == 0) {
                        i++
                        while (l > 0 || code[i] != ']') {
                            if (code[i] == '[') l++
                            if (code[i] == ']') l--
                            i++
                        }
                    }
                }
                ']' -> {
                    if (memory[dataPointer] != 0) {
                        i--
                        while (l > 0 || code[i] != '[') {
                            if (code[i] == ']') l++
                            if (code[i] == '[') l--
                            i--
                        }
                        i--
                    }
                }
            }
            i++
        }
        return word
    }
}

