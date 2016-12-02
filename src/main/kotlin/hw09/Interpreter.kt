package hw09

/* Interpreter  made by Guzel Garifullina
   Estimated time  1 hour
   real time       1 hour
*/

import java.util.*

public class Interpreter (private val code : Code) {
    private val memSize = 30000
    private val commands = code.getCode()
    private val arr = ByteArray(memSize)
    private var currentElem = memSize / 2
    private val commandsSize = commands.size

    public fun interpret (){
        if (! code.isCorrect()){
            print("Unbalanced brackets!\n")
            return
        }
        var comandInd = 0
        var braces = Stack<Int>()
        while (comandInd < commandsSize ){
            val e = commands[comandInd]
            when (e.getType()){
                Commands.SHIFT -> currentElem += e.getAmt()
                Commands.ADD -> arr[currentElem] = (arr[currentElem] + e.getAmt()).toByte()
                Commands.ZERO -> arr[currentElem] = 0
                Commands.OUT -> {
                    for ( i in 1 .. e.getAmt()){
                        print(arr[currentElem].toChar())
                    }
                }
                Commands.IN -> {
                    for ( i in 1 .. e.getAmt()){
                        arr[currentElem] = System.`in`.read().toByte()
                    }
                }
                Commands.WHILE ->{
                    if (arr[currentElem] == 0.toByte()){
                        comandInd = findEndBracket(comandInd + 1)
                    }
                    else {
                        braces.push (comandInd)
                    }
                }
                Commands.END -> {
                    comandInd = braces.pop() - 1
                }
            }
            comandInd ++
        }
    }
    private fun findEndBracket(comandInd : Int) : Int {
        var sum = 1
        for (i in comandInd .. commandsSize -1){
            when (commands[i].getType()){
                Commands.WHILE -> sum ++
                Commands.END -> {
                    sum --
                    if (sum == 0){
                        return i
                    }
                }
            }
        }
        return commandsSize
    }
}
