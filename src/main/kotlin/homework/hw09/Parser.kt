package homework.hw09

import java.util.*

public class Parser() {

    public class IncorrectInputException() : Throwable()
    public class IncorrectBracketsSequence() : Throwable()

    enum class Tokens { lshift, rshift, INC, DEC, print, write, lb, rb }

    private val tokenList = ArrayList<Tokens>()

    public fun parse(input: String) : ArrayList<Tokens> {
        var balance = 0
        for (char in input) when (char) {
            '>' -> tokenList.add(Tokens.rshift)
            '<' -> tokenList.add(Tokens.lshift)
            '+' -> tokenList.add(Tokens.INC)
            '-' -> tokenList.add(Tokens.DEC)
            ',' -> tokenList.add(Tokens.write)
            '.' -> tokenList.add(Tokens.print)
            '[' -> {tokenList.add(Tokens.lb); balance -= 1}
            ']' -> {tokenList.add(Tokens.rb); if(balance>=0) throw IncorrectBracketsSequence() else balance += 1 }
            ' ' -> true
            else -> throw IncorrectInputException()
        }
        if(balance != 0) throw IncorrectBracketsSequence()
        return tokenList
    }
}