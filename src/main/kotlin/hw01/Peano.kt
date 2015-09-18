/* Numbers of Peano:
   sum, sub, multiply and power
   made by Guzel Garifullina
   Estimated time 2 hours
   real time      1.30
*/
package hw01

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
            is Zero -> return S(Zero())
            is S -> return peanoMult(a, peanoPower_(a, b.peano))
            else -> throw Exception("Incorrect class")
        }
    }
    return peanoPower_(a, b)
}
