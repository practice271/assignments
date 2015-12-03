package homeworks.hw09

/**
 * Created by Ilya on 18.11.2015.
 */

public class interpreter () {
    val maxSize       = 32767
    var tape          = ByteArray(maxSize)
    var positionTape = 0
    var positionInput    = 0
    var countPairsBrackets = 0

    private fun checkSyntax(input: CharArray): Boolean {
        var flag = 0 //count pairs of brackets

        input.forEach {
            if (flag < 0) {
                return false
            } else if (it == '[') {
                flag++
            } else if (it == ']') {
                flag--
            }
        }
        return flag == 0
    }

    private fun interpretWork(input: CharArray): String {
        var result: String = ""
        while (positionInput < input.size) {
            when (input[positionInput]) {
                '>' -> {
                    if (++positionTape > maxSize) {
                        throw Exception("Tape over!")
                    }
                }
                '<' -> {
                    if (--positionTape < 0) {
                        throw Exception("Tape over!")
                    }
                }
                '+' -> tape[positionTape]++
                '-' -> tape[positionTape]--
                '.' -> {
                    result += tape[positionTape].toChar()
                }
                ',' -> {
                    val valueString = readLine() ?: throw Exception("Error input")
                    val valueByte = valueString.toByteArray().first()
                    tape[positionTape] = valueByte
                }
                '[' -> {
                    if (tape[positionTape] == 0.toByte()) {
                        positionInput++
                        while (countPairsBrackets > 0 || input[positionInput] != ']') {
                            when (input[positionInput]) {
                                '[' -> countPairsBrackets++
                                ']' -> countPairsBrackets--
                            }
                            positionInput++
                        }
                    }
                }
                ']' -> {
                    positionInput--
                    while (countPairsBrackets > 0 || input[positionInput] != '[') {
                        when (input[positionInput]) {
                            '[' -> countPairsBrackets--
                            ']' -> countPairsBrackets++
                        }
                        positionInput--
                    }
                    positionInput--
                }
            }
            positionInput++
        }
        return result
    }

    public fun interpret(input: CharArray): String? {
        if (checkSyntax(input)) {
            return interpretWork(input)
        } else {
            return null
        }
    }
}