/**
 * Created by z on 18/09/15.
 */

abstract class Peano() {}
class Zero(): Peano() {}
class S (val value: Peano): Peano() {}


fun sum(a: Peano, b: Peano): Peano {
    when (a) {
        is Zero -> return b
        is S -> return S(sum(a.value, b))
        else -> throw Exception("Error")
    }
}

fun mult(a: Peano, b: Peano): Peano {
    when (a) {
        is Zero -> return Zero()
        is S -> return sum(mult(a.value,b), b)
        else -> throw Exception("Error")
    }
}

fun sub(a: Peano, b:Peano): Peano {
    when(a) {
        is Zero -> return Zero()
        is S -> {
            when(b) {
                is Zero -> return a
                is S -> return sub (a.value, b.value)
                else -> throw Exception("Error")
            }

        }
        else -> throw Exception("Error")
    }

}

fun deg (a: Peano, b: Peano): Peano {
    when(a) {
        is Zero -> return Zero()
        is S -> {
            when(b) {
                is Zero -> return S(Zero())
                is S -> return mult(S(a.value), deg(S(a.value), b.value))
                else -> throw Exception("Error")
            }
        }
        else -> throw Exception("Error")
    }
}

fun toInt(a: Peano): Int {
    when(a) {
        is Zero -> return 0
        is S -> return toInt(a.value) +1
        else  -> throw Exception("Error")
    }
}

fun main(args: Array<String>) {
    println("5+3 = ${toInt(sum((S(S(S(S(S(Zero())))))), S(S(S(Zero())))))}")
    println("4-1 = ${toInt(sub(S(S(S(S(Zero())))),S(Zero())))}")
    println("2*6 = ${toInt(mult(S(S(Zero())), S(S(S(S(S(S(Zero()))))))))}")
    println("2^5 = ${toInt(deg(S(S(Zero())), S(S(S(S(S(Zero())))))))}")
}