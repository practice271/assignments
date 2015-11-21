package homework.hw09

public class Interpreter(private val input : String) {

    private var arr = ByteArray(100)
    private var pointer = 0
    private val tokens = Parser().parse(input)
    private var level = 0
    private var i = 0

    public fun interpret() {

        while (i < tokens.size) {
            when (tokens[i]) {
                Parser.Tokens.rshift -> pointer++
                Parser.Tokens.lshift -> pointer--
                Parser.Tokens.INC -> arr[pointer]++
                Parser.Tokens.DEC -> arr[pointer]--
                Parser.Tokens.write  -> arr[pointer] = System.`in`.read().toByte()
                Parser.Tokens.print  -> print(arr[pointer].toChar())
                Parser.Tokens.lb ->
                    if (arr[pointer].toInt() == 0) {
                        i++
                        while (level > 0 || tokens[i] != Parser.Tokens.rb) {
                            if (tokens[i] == Parser.Tokens.lb) level++
                            if (tokens[i] == Parser.Tokens.rb) level--
                            i++
                        }
                    }
                Parser.Tokens.rb ->
                    if (arr[pointer].toInt() != 0) {
                        i--
                        while (level > 0 || tokens[i] != Parser.Tokens.lb) {
                            if (tokens[i] == Parser.Tokens.rb) level++
                            if (tokens[i] == Parser.Tokens.lb) level--
                            i--
                        }
                        i--
                    }
            }
            i++
        }
    }
}

public fun main(args : Array<String>)
{
    //val file = args[0]
    //val string = file.reader().readText()
    Interpreter("++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.").interpret()
}