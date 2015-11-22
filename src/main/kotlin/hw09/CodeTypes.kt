package hw09

import java.util.*

/* Compiler for BrainFuck  made by Guzel Garifullina
   Estimated time  3 hours
   real time       3 hours
*/

public interface CodeTypes {
    public fun tokenize() : ArrayList<Operations>
}
public class BrainFuckCode : CodeTypes {
    private var codeStr = ""
    private var size = 0
    constructor ( str : String){
        codeStr = str
        size = codeStr.length
    }
    constructor ( file : FileL){
        codeStr = file.readFile()
        size = codeStr.length
    }
    override public fun tokenize(): ArrayList<Operations> {
        val arr : ArrayList<Operations> = ArrayList()
        var i = 0
        while (i <  size){
            when (codeStr[i]) {
                '>' -> arr.add(Operations(Commands.SHIFT))
                '<' -> arr.add(Operations(Commands.SHIFT, -1))
                '+' -> arr.add(Operations(Commands.ADD))
                '-' -> arr.add(Operations(Commands.ADD, -1))
                '.' -> arr.add(Operations(Commands.OUT))
                ',' -> arr.add(Operations(Commands.IN))
                '[' -> {
                    if ((i + 2 < size ) && (codeStr[i + 1] == '-' || codeStr[i + 1] == '+' )
                            && (codeStr[i + 2] == ']')){
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
}

public class PetoohCode  : CodeTypes {
    private var codeStr = ""
    private var size = 0
    constructor ( str : String){
        codeStr = str
        size = codeStr.length
    }
    constructor ( file : FileL){
        codeStr = file.readFile()
        size = codeStr.length
    }

    private fun isSubstring (index : Int, prototype : String) : Boolean {
        val len = prototype.length
        if (index + len > size){return false}
        for ( i in 0 .. len - 1){
            if (prototype[i] != codeStr[i + index]){return false}
        }
        return true
    }
    override public fun tokenize(): ArrayList<Operations> {
        val arr : ArrayList<Operations> = ArrayList()
        var i = 0
        while (i <  size){
            when {
                isSubstring(i, "Kudah")  -> {
                    arr.add(Operations(Commands.SHIFT))
                    i += "Kudah".length - 1
                }
                isSubstring(i, "kudah") -> {
                    arr.add(Operations(Commands.SHIFT, -1))
                    i += "kudah".length - 1
                }
                isSubstring(i, "Ko") -> {
                    arr.add(Operations(Commands.ADD))
                    i ++
                }
                isSubstring(i, "kO")-> {
                    arr.add(Operations(Commands.ADD, -1))
                    i ++
                }
                isSubstring(i, "Kukarek") -> {
                    arr.add(Operations(Commands.OUT))
                }
                isSubstring(i, "kukarek") -> {
                    arr.add(Operations(Commands.IN))
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