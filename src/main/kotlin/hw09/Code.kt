package hw09

import java.io.FileInputStream
import java.util.*

/* Code translator for BrainFuck like languages
   made by Guzel Garifullina
   Estimated time  3 hours
   real time       3 hours
*/

public abstract class  Code {
    protected var codeArr = ArrayList<Operations>()
    protected fun optimize (oldArr : ArrayList<Operations>) : ArrayList<Operations> {
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
    public fun isCorrect() : Boolean {
        var br = 0
        for (e in codeArr) {
            when (e.getType()){
                Commands.WHILE -> br ++
                Commands.END   -> {
                    br--
                    if (br < 0) { return false}
                }
            }
        }
        return br == 0
    }
    override public fun toString() : String {
        val sb = StringBuilder()
        for (e in codeArr){
            sb.append(e.toString())
        }
        return sb.toString()
    }
}
public class BrainFuckCode : Code {
    private var codeStr = ""
    private var size = 0
    constructor (str : String){
        codeStr = str
        size = codeStr.length
        codeArr = optimize(tokenize())
    }
    constructor (file : FileL){
        codeStr = file.readFile()
        size = codeStr.length
        codeArr = optimize(tokenize())
    }
    private fun tokenize () : ArrayList<Operations> {
        val arr : ArrayList<Operations> = ArrayList()
        var i = 0
        while (i <  size) {
            when (codeStr[i]) {
                '>' -> arr.add(Operations(Commands.SHIFT))
                '<' -> arr.add(Operations(Commands.SHIFT, -1))
                '+' -> arr.add(Operations(Commands.ADD))
                '-' -> arr.add(Operations(Commands.ADD, -1))
                '.' -> arr.add(Operations(Commands.OUT))
                ',' -> arr.add(Operations(Commands.IN))
                '[' -> {
                    if ((i + 2 < size ) && (codeStr[i + 1] == '-' || codeStr[i + 1] == '+')
                            && (codeStr[i + 2] == ']')){
                        arr.add(Operations(Commands.ZERO))
                        i += 2
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
}

public class PetoohCode : Code {
    private var codeStr = ""
    private var size = 0
    constructor ( str : String) {
        codeStr = str
        size = codeStr.length
        codeArr = optimize(tokenize())
    }
    constructor (file : FileL){
        codeStr = file.readFile()
        size = codeStr.length
        codeArr = optimize(tokenize())
    }
    private fun isSubstring (index : Int, prototype : String) : Boolean {
        val len = prototype.length
        if (index + len > size) {return false}
        for (i in 0 .. len - 1){
            if (prototype[i] != codeStr[i + index]) {return false}
        }
        return true
    }
    private fun tokenize() : ArrayList<Operations> {
        val arr : ArrayList<Operations> = ArrayList()
        var i = 0
        while (i <  size) {
            when {
                isSubstring(i, "Kudah") -> {
                    arr.add(Operations(Commands.SHIFT))
                    i += 4 //"Kudah".length - 1
                }
                isSubstring(i, "kudah") -> {
                    arr.add(Operations(Commands.SHIFT, -1))
                    i += 4 //"kudah".length - 1
                }
                isSubstring(i, "Ko") -> {
                    arr.add(Operations(Commands.ADD))
                    i ++
                }
                isSubstring(i, "kO") -> {
                    arr.add(Operations(Commands.ADD, -1))
                    i ++
                }
                isSubstring(i, "Kukarek") -> {
                    arr.add(Operations(Commands.OUT))
                    i += 6
                }
                isSubstring(i, "kukarek") -> {
                    arr.add(Operations(Commands.IN))
                    i += 6
                }
                isSubstring(i, "Kud") -> {
                    arr.add(Operations(Commands.WHILE))
                    i += 2
                    }

                isSubstring(i, "kud") -> {
                    arr.add(Operations(Commands.END))
                    i += 2
                }
            }
            i++
        }
        return arr
    }
}

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