package hw09.BrainFuck

/**
 * Created by guzel on 18.11.15.
 */

import java.io.*
import java.nio.charset.Charset
import java.nio.file.Path
import java.io.FileReader
import java.io.BufferedReader

import java.io.IOException
import java.util.*

public fun readFile(filename : String) : String {
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
    }catch (e : IOException) {
    }catch (e : FileNotFoundException){
    }finally {
        if (inn != null) {
            inn.close()
        }
        str = sb.toString()
        return str
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
public class Operations(private val type : Commands ) {
    private var amt = 1
    constructor(type : Commands , amount : Int) : this (type){
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
    override public fun toString(): String {
        val str = (when (type){
            Commands.SHIFT -> ">="
            Commands.ADD -> "+="
            Commands.ZERO -> "0="
            Commands.OUT -> ".="
            Commands.IN  -> ",="
            Commands.END -> "]="
            Commands.WHILE -> "while="
        })
        return "$str $amt\n"
    }
}
public fun tokenize (str : String) : ArrayList<Operations>{
    val arr : ArrayList<Operations> = ArrayList()
    var i = 0
    val n = str.length
    while (i <  n){
        when (str[i]) {
            '>' -> arr.add(Operations(Commands.SHIFT))
            '<' -> arr.add(Operations(Commands.SHIFT, -1))
            '+' -> arr.add(Operations(Commands.ADD) )
            '-' -> arr.add(Operations(Commands.ADD, -1) )
            '.' -> arr.add(Operations(Commands.OUT) )
            ',' -> arr.add(Operations(Commands.IN))
            '[' -> {
                if ((i + 2 < n ) && (str [i + 1] == '-' || str [i + 1] == '+' )
                    && (str[i + 2] == ']')){
                    arr.add(Operations(Commands.ZERO))
                    i +=2
                }
                else {
                    arr.add(Operations(Commands.WHILE))
                }
            }
            ']' -> arr.add(Operations(Commands.END))
        }
        i++
    }
    return arr
}

public fun optimize (oldArr : ArrayList<Operations>) : ArrayList<Operations>{
    val newArr = ArrayList<Operations>()
    var type : Commands? = null
    for (e in oldArr) {
        val eType = e.getType()
        when (type){
            null,Commands.WHILE, Commands.END -> {
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
public fun toString (arr : ArrayList<Operations>) : String {
    val sb = StringBuilder()
    for (e in arr){
        sb.append(e.toString())
    }
    return sb.toString()
}


public fun main(args : Array<String>) {
    val file = "/home/guzel/Programming/assignments/src/main/kotlin/hw09/BrainFuck/input"
    val str = "[+]>>><+++[[+-]]>----+"
    val res = optimize(tokenize(str))
    print(toString(res))

}