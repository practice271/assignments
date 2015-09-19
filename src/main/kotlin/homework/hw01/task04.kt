// Задания 01-04 от 08.09.2015
// Автор: Кирилл Смиренко, группа 271
package homework.hw01

// 04. Числа Пеано в объектно-ориентированной модели. Операции сложения, вычитания, умножения и возведения в степень.

abstract class Peano {}
open class Z() : Peano() {}
open class S(val v : Peano) : Peano() {}

fun Int.toPeano() : Peano {
    fun toPeano_(n : Int, acc : Peano) : Peano {
        when (n) {
            0 -> return acc
            else -> return toPeano_(n - 1, S(acc))
        }
    }
    return toPeano_(this, Z())
}

fun Peano.toInt() : Int {
    fun toInt_(p : Peano, acc : Int) : Int {
        when (p) {
            is S -> return toInt_(p.v, acc + 1)
            else -> return acc
        }
    }
    return toInt_(this, 0)
}

fun peanoAdd(a : Peano, b : Peano) : Peano {
    when (a) {
        is S -> return S(peanoAdd(a.v, b))
        else -> return b
    }
}

fun peanoSub(a : Peano, b: Peano) : Peano {
    when (a) {
        is S ->
            when (b) {
                is S -> return peanoSub(a.v, b.v)
                else -> return a
            }
        else -> return Z()
    }
}

fun peanoMul(a: Peano, b: Peano) : Peano {
    when (a) {
        is S ->
            when (b) {
                is S -> return peanoAdd(peanoMul(a, b.v), a)
                else -> return Z()
            }
        else -> return Z()
    }
}

fun peanoPow(a: Peano, b: Peano) : Peano {
    when (a) {
        is S ->
            when (b) {
                is S -> return peanoMul(peanoPow(a, b.v), a)
                else -> return S(Z())
            }
        else -> return Z()
    }
}