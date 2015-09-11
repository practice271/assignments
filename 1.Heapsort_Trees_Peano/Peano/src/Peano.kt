/* Numbers of Peano:
   sum, sub, multiply and power
   made by Guzel Garifullina
   Estimated time 2 hours
   real time      1.30
*/

abstract  class  Peano {}
open class Zero() : Peano(){}
open class  S (var peano : Peano) : Peano(){} //succeeding

fun peanoCreate(num : Int): Peano{
    if (num <= 0) return Zero()
    else return S(peanoCreate(num - 1))
}

fun peanoToNum( peano: Peano) : Int{
    when (peano){
        is Zero -> return 0
        is S    -> return 1 +  peanoToNum(peano.peano)
        else    -> throw Exception("Incorrect class")
    }
}
fun peanoSum(a : Peano, b : Peano): Peano{
    when (a){
        is Zero -> return b
        is S    ->  return S(peanoSum(a.peano, b))
        else    ->  throw Exception("Incorrect class")
    }
}

fun peanoSub (a : Peano, b : Peano): Peano{
    when (a){
        is Zero -> return Zero()
        is S    -> {
            when (b) {
                is Zero -> return a
                is S -> return (peanoSub(a.peano, b.peano))
                else -> throw Exception("Incorrect class")
            }
        }
        else    ->  throw Exception("Incorrect class")
    }
}

fun peanoMult (a : Peano, b : Peano): Peano {
    if (( a is Zero) || (b is Zero)) return Zero()

    fun peanoMult_(a: Peano, b: Peano): Peano {
        when (b) {
            is Zero -> return Zero()
            is S -> return peanoSum(a, peanoMult_(a, b.peano))
            else -> throw Exception("Incorrect class")
        }
    }
    return peanoMult_(a, b)
}

fun peanoPower (a: Peano, b : Peano): Peano{
    if (a is Zero)  return Zero()
    if (b  is Zero) return S(Zero())

    fun peanoPower_(a: Peano, b: Peano): Peano {
        when (b) {
            is Zero -> return a
            is S -> return peanoMult(a, peanoPower_(a, b.peano))
            else -> throw Exception("Incorrect class")
        }
    }
    return peanoPower_(a, b)
}
fun main(args: Array<String>) {
    println("Initial peano number is 3")
    val a : Peano = S (S (S(Zero())))
    println("Check peanoToInt ${peanoToNum(a)}")

    val b : Peano = Zero()
    val c : Peano = S(Zero())
    val d : Peano = peanoCreate(5)
    println(" 3 + 0 = ${peanoToNum(peanoSum(a,b))} ")
    println(" 3 + 5 = ${peanoToNum(peanoSum(a,d))} ")

    println(" 3 - 0 = ${peanoToNum(peanoSub(a,b))}")
    println(" 1 - 3 = ${peanoToNum(peanoSub(c,a))}")
    println(" 5 - 3 = ${peanoToNum(peanoSub(d,a))}")


    println(" 3 * 0 = ${peanoToNum(peanoMult(a,b))}")
    println(" 3 * 5 = ${peanoToNum(peanoMult(a,d))}")
    println(" 0 * 5 = ${peanoToNum(peanoMult(b,d))}")

    println(" 3 ^ 0 = ${peanoToNum(peanoPower(a,b))}")
    println(" 3 ^ 1 = ${peanoToNum(peanoPower(a,c))}")
    println(" 0 ^ 5 = ${peanoToNum(peanoPower(b,d))}")
    println(" 5 ^ 3 = ${peanoToNum(peanoPower(d,a))}")
}
