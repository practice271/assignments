package hw09

fun parser (str : String) : String {
    var array = Array(400, {i -> 0});
    var result = ""
    var arrPointer = 0
    var strPointer = 0
    var beginning = 0
    var inBrackets = 0
    while (strPointer < str.length ) {
        when (str[strPointer]) {
            '+' -> array[arrPointer]++
            '-' -> array[arrPointer]--
            '<' -> arrPointer--
            '>' -> arrPointer++
            '.' -> result = result + array[arrPointer].toChar()
            ',' -> array[arrPointer] = readLine()?.toInt() ?: 0
            '[' -> {
                inBrackets++
                if (array[arrPointer] == 0) {
                    strPointer++
                    while (str[strPointer + 1] != ']' && strPointer < str.length) {
                        strPointer++
                    }
                }
                else {
                    beginning = strPointer
                }
            }
            ']' -> {
                inBrackets--
                if (inBrackets < 0) {
                    result = "Not enough opening brackets"
                }
                else {
                    if (array[arrPointer] != 0) {
                        strPointer = beginning - 1
                    }
                }
            }
        }
        strPointer++
    }
    if (inBrackets > 0) {return "Not enough closing brackets"}
    else {return result}
}
