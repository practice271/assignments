package hw01

abstract class Peano {}
open class Z(): Peano() {}
open class S(val prev: Peano): Peano() {}

fun Peano.ToInt(): Int {
    return when(this) {
        is Z -> 0
        is S -> this.prev.ToInt() + 1
        else -> throw Exception("Wrong class")
    }
}

fun Int.ToPn(): Peano {
    return when(this) {
        0 -> Z()
        else -> pnSum((this - 1).ToPn(), S(Z()))
    }
}

fun pnSum(a: Peano, b: Peano): Peano {
    return when(a) {
        is Z -> b
        is S -> S(pnSum(a.prev, b))
        else -> throw Exception("Wrong class")
    }
}

fun pnSub(a: Peano, b: Peano): Peano {
    return when(a) {
        is Z -> Z()
        is S ->
            when(b) {
                is Z -> a
                is S -> pnSub(a.prev, b.prev)
                else -> throw Exception("Wrong class of the 2nd argument")
            }
        else -> throw Exception("Wrong class of the 1st argument")
    }
}

fun pnMult(a: Peano, b: Peano): Peano {
    return when(a) {
        is Z -> Z()
        is S -> pnSum(b, pnMult(a.prev, b))
        else -> throw Exception("Wrong class of the 1st argument")
    }
}

fun pnPow(a: Peano, b: Peano): Peano {
    return when(a) {
        is Z -> Z()
        a ->
            when(b) {
                is Z -> S(Z())
                is S -> pnMult(pnPow(a, b.prev), a)
                else -> throw Exception("Wrong class of the 2nd argument")
            }
        else -> throw Exception("Wrong class of the 1st argument")
    }
}