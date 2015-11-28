package homework.hw09

import java.util.*

public class Parser() {

    public class IncorrectInputException() : Throwable()
    public class IncorrectBracketsSequence() : Throwable()

    enum class Tokens {LS, RS, INC, DEC, PRINT, WRITE, LB, RB }

    private val tokenList = ArrayList<Tokens>()

    public fun parse(input: String) : ArrayList<Tokens> {
        var balance = 0
        for (char in input) when (char) {
            '>' -> tokenList.add(Tokens.RS)
            '<' -> tokenList.add(Tokens.LS)
            '+' -> tokenList.add(Tokens.INC)
            '-' -> tokenList.add(Tokens.DEC)
            ',' -> tokenList.add(Tokens.WRITE)
            '.' -> tokenList.add(Tokens.PRINT)
            '[' -> {tokenList.add(Tokens.LB); balance -= 1}
            ']' -> {tokenList.add(Tokens.RB); if(balance >= 0)
                throw IncorrectBracketsSequence() else balance += 1 }
            ' ' -> true
            else -> throw IncorrectInputException()
        }
        if(balance != 0) throw IncorrectBracketsSequence()
        return tokenList
    }
}