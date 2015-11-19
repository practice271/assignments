package homework.hw09

/**
 * Brainfuck tokens.
 * @author Kirill Smirenko
 */
enum class Tokens(val v : Char) {
    NEXT('>'),
    PREV('<'),
    INC('+'),
    DEC('-'),
    WRITE('.'),
    READ(','),
    LBRACKET('['),
    RBRACKET(']')
}