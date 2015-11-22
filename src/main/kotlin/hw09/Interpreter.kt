package hw09

/* Interpreter  made by Guzel Garifullina
   Estimated time  1 hour
   real time       1 hour
*/

import java.io.*
import java.util.*

public class FileL (private val filename : String){
    //throws IOException FileNotFoundException
    private fun readFile(filename : String) : String {
        var inn : FileInputStream? = null
        var str : String = ""
        val sb : StringBuilder = StringBuilder()
        try {
            inn = FileInputStream(filename)
            var c = 1
            while (c != -1) {
                c = inn.read()
                sb.append(c.toChar())
            }
        }finally {
            if (inn != null) {
                inn.close()
            }
            str = sb.toString()
            return str
        }
    }
    public fun readFile() : String {
        return readFile(filename)
    }
}

public enum class Commands{
    SHIFT,
    ADD,
    ZERO,
    OUT,
    IN,
    WHILE,
    END
}
public class Operations(private val type : Commands) {
    private var amt = 1
    constructor(type : Commands, amount : Int) : this (type){
        amt = amount
    }
    public fun getType() : Commands {
        return type
    }
    public fun getAmt() : Int {
        return amt
    }
    public fun addAmt(value : Int) {
        amt += value
    }
    override public fun toString() : String {
        val str = (when (type){
            Commands.SHIFT -> ">="
            Commands.ADD -> "+="
            Commands.ZERO -> "0="
            Commands.OUT -> ".="
            Commands.IN -> ",="
            Commands.END -> "]="
            Commands.WHILE -> "while="
        })
        return "$str $amt\n"
    }
}
public  class Code (private  val codeType : CodeTypes) {
    private var codeArr = ArrayList<Operations>()
    init {
        codeArr = optimize(tokenize())
    }
    private fun tokenize () : ArrayList<Operations> {
        return codeType.tokenize()
    }
    private fun optimize (oldArr : ArrayList<Operations>) : ArrayList<Operations> {
        val newArr = ArrayList<Operations>()
        var type : Commands? = null
        for (e in oldArr) {
            val eType = e.getType()
            when (type) {
                null, Commands.WHILE, Commands.END -> {
                    type = eType
                    newArr.add(e)
                }
                eType -> {
                    if (type != Commands.ZERO) {
                        newArr.last().addAmt(e.getAmt())
                    }
                }
                else -> {
                    type = eType
                    newArr.add(e)
                }
            }
        }
        return newArr
    }
    public fun getCode () : ArrayList<Operations> {
        return codeArr
    }
    override public fun toString() : String {
        val sb = StringBuilder()
        for (e in codeArr){
            sb.append(e.toString())
        }
        return sb.toString()
    }
}

public class Interpreter (private val code : Code) {
    private val memSize = 30000
    private val commands = code.getCode()
    private val arr = ByteArray(memSize)
    private var currentElem = memSize / 2
    private val commandsSize = commands.size

    public fun interpret (){
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
