package hw09

internal enum class BFTokens(val ch: Char) {
    INC('+'), DEC('-'),
    RMOVE('>'), LMOVE('<'),
    IN(','), OUT('.'),
    LBRT('['), RBRT(']')
}