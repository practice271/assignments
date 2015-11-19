package hw09

/**
 * Created by Alexander on 19.11.2015.
 */
class Opcode(val type : Opcode.Type) {
    enum class Type {
        SHL,
        SHR,
        ADD,
        SUB,
        OUT,
        IN,
        WHILE,
        END
    }

    fun clone(): Opcode {
        return Opcode(type)
    }
}