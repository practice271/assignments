package hw09

import java.util.*

public class BrainFInterpreter(private val tapeLen: Int) {
    private val tape = Array<Byte>(tapeLen) { 0 }
    private var curCell = 0

    public fun interpret(code: String, input: Stack<Char>): String {
        var resOut = ""
        var i = 0
        val cLen = code.length
        while(i < cLen) {
            when (code[i]) {
                BFTokens.INC.ch ->
                    tape[curCell]++
                BFTokens.DEC.ch ->
                    tape[curCell]--
                BFTokens.RMOVE.ch ->
                    if(curCell == tapeLen-1) curCell = 0 else curCell++
                BFTokens.LMOVE.ch ->
                    if(curCell == 0) curCell = tapeLen-1 else curCell--
                BFTokens.IN.ch ->
                    if(input.isNotEmpty()) tape[curCell] = input.pop().toByte()
                    else throw NoSuchElementException()
                BFTokens.OUT.ch ->
                    resOut += tape[curCell].toChar()
                BFTokens.LBRT.ch -> {
                    var inCycle = ""
                    var brtCount = 1
                    i++
                    while (true) {
                        when (code[i]) {
                            BFTokens.LBRT.ch -> brtCount++
                            BFTokens.RBRT.ch -> brtCount--
                        }
                        if (brtCount == 0) break
                        inCycle += code[i++]
                    }
                    var cycleRes: String
                    while(tape[curCell] != 0.toByte()) {
                        cycleRes = interpret(inCycle, input)
                        resOut += cycleRes
                    }
                }
                else -> i++
            }
            i++
        }
        return resOut
    }
}