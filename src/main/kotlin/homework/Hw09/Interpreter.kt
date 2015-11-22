package homework.Hw09

class Interpreter(var string:String){
    private var pointer = 0
    private var charCounter = 0
    private var buffer = Array<Byte>(30000) {0}
    private var result = Array<Char>(1000) {'0'}

    //Support method to return value from @Interpret()
    private fun getResult():String{
        var temp = ""
        for (i in 0..charCounter-1){
            temp+=result[i].toChar()
        }
        return temp
    }

    public fun Interpret():String{
//        if (string.length == 0) return ""
        var bracketBalance = 0
        var i = 0
        while(i < string.length){
            when(string[i]){
                '>' -> pointer++
                '<' -> pointer--
                '+' -> buffer[pointer]++
                '-' -> buffer[pointer]--
                '.' -> {
                    print(buffer[pointer].toChar())
                    result[charCounter] = buffer[pointer].toChar()
                    charCounter++
                }
                ',' -> buffer[pointer] = System.`in`.read().toByte()
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
        return getResult()
    }
}
