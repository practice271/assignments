package homework.hw09

public class Brainfuck(private val program: String)  {

    private var arr = ByteArray(30000)
    private var pointer = 0
    private val tokens = Parser().parse(program)

    // string output is just for testing
    public fun interpret(input: String?): String {
        var i = 0
        var braketsLevel = 0
        var inputPointer = 0
        var output = ""

        while (i < tokens.size) {
            when (tokens[i]) {
                Parser.Tokens.RS -> if (pointer < 30000) pointer++ else pointer = 0
                Parser.Tokens.LS -> if (pointer > 0) pointer-- else pointer = 29999
                Parser.Tokens.INC -> arr[pointer]++
                Parser.Tokens.DEC -> arr[pointer]--
                Parser.Tokens.WRITE -> {
                    arr[pointer] =
                            if (input != null && inputPointer < input.length)
                                input[inputPointer].toByte()
                            else
                                System.`in`.read().toByte()
                }

                Parser.Tokens.PRINT -> {
                    output += arr[pointer].toChar()
                    print(arr[pointer].toChar())
                }
                Parser.Tokens.LB ->
                    if (arr[pointer].toInt() == 0) {
                        i++
                        while (braketsLevel > 0 || tokens[i] != Parser.Tokens.RB) {
                            if (tokens[i] == Parser.Tokens.LB) braketsLevel++
                            if (tokens[i] == Parser.Tokens.RB) braketsLevel--
                            i++
                        }
                    }
                Parser.Tokens.RB ->
                    if (arr[pointer].toInt() != 0) {
                        i--
                        while (braketsLevel > 0 || tokens[i] != Parser.Tokens.LB) {
                            if (tokens[i] == Parser.Tokens.RB) braketsLevel++
                            if (tokens[i] == Parser.Tokens.LB) braketsLevel--
                            i--
                        }
                        i--
                    }
            }
            i++
        }
        return output
    }
}
