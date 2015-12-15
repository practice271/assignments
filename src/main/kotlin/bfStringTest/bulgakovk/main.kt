package bfStringTest.bulgakovk
import java.lang.StringBuilder


class Converter(var input:String){
    //Classic size of tape
    private val buffer_size = 30000

    public fun setString(string:String){
        input = string
    }
    //Both functions round numbers to closest
    private fun floorUp(a: Int):Int{
        return ((a+10) / 10) * 10
    }

    private fun floourDown(a:Int):Int{
        return (a / 10) * 10
    }

    //@checkNums return true when arr has "close" numbers
    private fun checkNums(arr:Array<Int>, num : Int, compareValue:Int):Boolean{
        for (i in num-compareValue..num+compareValue)
            if (arr.contains(i)) return true
        return false
    }

    public fun convert():String{
        return method2()
    }

    private fun method2():String{
        var buffer = Array<Byte>(buffer_size) {0}
        val sb  = StringBuilder()
        var temp = Array<Int>(input.length) {0}
        var tempCounter = 0
        //Initiate
        buffer[0] = 10
        sb.append("++++++++++[>")
        for (char in input){
            if (!checkNums(temp, char.toInt(), 10)){
                temp[tempCounter++] = char.toInt()
            }
        }
        val cells = Array<Int>(tempCounter){0}
        for (i in 0..tempCounter-1) {
            // Just checking the remainder after dividing
            if (temp[i] % 10 > 5){
                cells[i] = floorUp(temp[i])
                buffer[i+1] = cells[i].toByte()
            }
            else {
                cells[i] = floourDown(temp[i])
                buffer[i + 1] = cells[i].toByte()
            }
        }
        for (i in cells){
            for (j in 0..i/10 - 1) sb.append("+")
            if (i == cells[cells.size-1]){
                for (ind in 1..cells.size) sb.append("<")
                sb.append("-]")
            }
            else sb.append(">")
        }
        sb.append(">")
        var place = 1
        for (char in input){
            var min      = 0
            var minPlace = 0
            var minDif   = 360
            for (i in 1..tempCounter) {
                if (Math.abs(buffer[i].toInt() - char.toInt()) < minDif) {
                    min      = buffer[i].toInt()
                    minDif   = Math.abs(buffer[i].toInt() - char.toInt())
                    minPlace = i
                }
            }
            if (min == char.toInt()){
                if (place > minPlace){
                    for (i in 1..place-minPlace) sb.append("<")
                    sb.append(".")
                    for (i in 1..place-minPlace) sb.append(">")
                }
                else{
                    for (i in 1..minPlace - place) sb.append(">")
                    sb.append(".")
                    for (i in 1..minPlace - place) sb.append("<")
                }
            }
            else {
                if (place > minPlace)
                    for (i in 1..place - minPlace) sb.append("<")
                else
                    for (i in 1..minPlace - place) sb.append(">")

                if (char.toInt() - min > 0){
                    for (i in 1..char.toInt() - min) {
                        sb.append("+")
                        buffer[minPlace]++
                    }
                }
                else {
                    for (i in 1..min - char.toInt()){
                        sb.append("-")
                        buffer[minPlace]--
                    }
                }
                place = minPlace
                sb.append(".")
            }
        }
        return sb.toString()
    }
}

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
