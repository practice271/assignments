package homework.Hw09
import java.lang.StringBuilder
import java.util.*

class Interpreter(){
    private var pointer = 0
    private var buffer = Array<Byte>(30000) {0}

    public fun Interpret(string:String):String{
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

class Respond(val status : String, val result : String, val length : Int){}

class Converter(var input:String){
    public fun setString(string:String){
        input = string
    }

    private fun method1():String{
        val sb  = StringBuilder()
        var arrTemp = Array<Char>(input.length) {'0'}
        var counter     = 0

        for (char in input){
            if (!arrTemp.contains(char)) arrTemp[counter++] = char
//            else if (!arrCopy.contains(char)) arrCopy[counterCopy++] = char
        }
//        var arrCopyTemp = Array<Char>(counterCopy) {'0'}
//        for (i in 0..counterCopy-1) arrCopyTemp[i] = arrCopy[i]
//        arrCopy = arrCopyTemp
        var arrChars = Array<Char>(counter-1) {'0'}
        for (i in 0..arrChars.size-1) arrChars[i] = arrTemp[i]
        for (i in 1..10) sb.append("+")
        sb.append("[>")
        var map = LinkedHashMap<Char, Int>()
        for (char in arrChars){
            val temp = char.toInt() / 10
            for (i in 1..temp) sb.append("+")
            map.put(char, temp*10)
            if (char != arrChars[arrChars.size-1])  sb.append(">")
            else for (char in arrChars){
                sb.append("<")
                if (char == arrChars[arrChars.size-1]) sb.append("-")

            }
        }
        sb.append("]>")
        val map2    = HashMap<Char, Int>()
        var indexCopy   = 0
        var pointer = 0
        var place  = 0
        //Creating result BF string
        var arrCopy = Array<Char>(input.length) {'0'}
        for (char in input){
            if (!arrCopy.contains(char)){
                val left = map?.get(char) ?: 0 // We're sure that elem exists.
                for (i in left..char.toInt()-1) sb.append("+")
                sb.append(".>")
                map2.put(char, place++)
                arrCopy[indexCopy++] = char
            }
            else {
                //ToDo: Compare 2 different steps
                var steps = map2?.get(char) ?: 0
//                println(char + "is" + steps + "and place is " + place)
                for (i in steps..place-1) sb.append('<')
                sb.append('.')
                for (i in steps..place-1) sb.append('>')
            }
        }
        //ToDo: return a respond instance instead string
        return sb.toString()
    }

    public fun convert():String{
       return method1()

    }
}

fun main(argv:Array<String>){
    val obj = Converter("Brainfuck");
//    obj.convert()
    val string = obj.convert()
//    var res = Interpreter().Interpret("+++++++[>+++++++++<-]>.")
    println(string.length)
}