package hw09

import java.util.ArrayList

fun parser (str : String) : String {
    var array = Array(400, {i -> 0});
    var result = ""
    var arrPointer = 0
    var strPointer = 0
    var beginning : ArrayList<Int> = arrayListOf()
    var inBrackets = 0
    while (strPointer < str.length ) {
        when (str[strPointer]) {
            '+' -> array[arrPointer]++
            '-' -> array[arrPointer]--
            '<' -> arrPointer--
            '>' -> arrPointer++
            '.' -> result += array[arrPointer].toChar()
            ',' -> array[arrPointer] = readLine()?.toInt() ?: 0
            '[' -> {
                inBrackets++
                if (array[arrPointer] == 0) {
                    while (strPointer + 1 < str.length && str[strPointer + 1] != ']') {
                        strPointer++
                    }
                }
                else {
                    beginning.add(beginning.size, strPointer)
                }
            }
            ']' -> {
                if (--inBrackets < 0) {
                    return "Not enough opening brackets"
                }
                else {
                    if (array[arrPointer] != 0) {
                        strPointer = beginning[beginning.size - 1] - 1
                    }
                    else {
                        beginning.removeAt(beginning.size - 1)
                    }
                }
            }
        }
        strPointer++
    }
    if (inBrackets > 0) {return "Not enough closing brackets"}
    else {return result}
}