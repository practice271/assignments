abstract class Peano() {}
class Zero(): Peano() {}
class Succ(val x: Peano): Peano() {}

fun fromInt(n: Int): Peano =
    when (n) {
        0 -> Zero()
        else -> Succ(fromInt(n - 1))
    }

fun Peano.Int() : Int =
    when (this) {
        is Succ -> x.Int() + 1
        else -> 0
    }

fun Peano.String(): String =
    this.Int().toString()

fun Peano.plus(p: Peano): Peano =
    when (this) {
        is Succ -> x + Succ(p)
        else -> p
    }

fun Peano.minus(p: Peano): Peano =
    when {
        this is Succ && p is Succ -> x - p.x
        p is Zero -> this
        else -> Zero()
    }

fun Peano.times(p: Peano): Peano =
    when (p) {
        is Succ -> this * p.x + this
        else -> Zero()
    }

fun Peano.pow(p: Peano): Peano =
    when (p) {
        is Succ -> pow(p.x) * this
        else -> Succ(Zero())
    }

fun main(args: Array<String>) {
    println((fromInt(2) + fromInt(2)).String());
    println((fromInt(10) - fromInt(3)).String());
    println((fromInt(5) * fromInt(5)).String());
    println((fromInt(2).pow(fromInt(6))).String());
}