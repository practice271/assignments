package hw02

abstract class Peano {}
class S(val value: Peano): Peano() {}
class Zero(): Peano() {}

fun plus(a: Peano, b: Peano) : Peano {
    when (a) {
        is Zero -> return b
        is S -> return S(plus(a.value, b))
        else -> throw Exception("Unknown class")
    }
}

fun minus(a: Peano, b: Peano) : Peano {
    when (b) {
        is Zero -> return a
        is S ->
            when (a) {
                is Zero -> return Zero()
                is S -> return minus(a.value, b.value)
                else -> throw Exception("Unknown class")
            }
        else -> throw Exception("Unknown class")
    }
}

fun multiply(a: Peano, b: Peano) : Peano {
    when (b) {
        is Zero -> return Zero()
        is S -> return plus(a, multiply(a, b.value))
        else -> throw Exception("Unknown class")
    }
}

fun raising(a: Peano, b: Peano) : Peano {
    when (b) {
        is Zero -> return S(Zero())
        is S -> return multiply(a, raising(a, b.value))
        else -> throw Exception("Unknown class")
    }
}

fun peanoToInt(a: Peano) : Int {
    when (a) {
        is Zero -> return 0
        is S -> return peanoToInt(a.value) + 1
        else -> throw Exception("Unknown class")
    }
}