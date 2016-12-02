package homework.Hw09
import java.io.Reader

class Interpreter(){
    private var pointer = 0
    private var buffer = Array<Byte>(30000) {0}

    public fun Interpret(string:String, reader : Reader):String{
        var bracketBalance = 0
        var i = 0
        var result = ""
        while(i < string.length){
            when(string[i]){
                '>' -> pointer++
                '<' -> pointer--
                '+' -> buffer[pointer]++
                '-' -> buffer[pointer]--
                '.' -> result += buffer[pointer].toChar()
                ',' -> buffer[pointer] = reader.read().toByte()
                '[' -> if (buffer[pointer] == 0.toByte()) {
                            i++
                            while (bracketBalance > 0 || string[i] != ']') {
                                if (string[i] == '[') bracketBalance++
                                if (string[i] == ']') bracketBalance--
                                i++
                            }
                       }
                ']' -> if (buffer[pointer] != 0.toByte()){
                        i--
                        while(bracketBalance > 0 || string[i] != '['){
                            if (string[i] == ']') bracketBalance++
                            if (string[i] == '[') bracketBalance--
                            i--
                       }
                       i--
                     }
            }
            i++
        }
        return result
    }
}
