/**
 * Created by Alexander Chebykin on 09.09.2015.
 * Estimated time: 30 min. Actual: 1 hr.
 * Task 4: Peano numbers
 */
package hw02

abstract class Peano {}
class Zero : Peano () {}
class S (val value : Peano) : Peano() {}

fun Int.ToPeano () : Peano
{
    if (this == 0) return Zero()
    else
        return S ((this - 1).ToPeano())
}

fun Peano.ToInt () : Int
{
    when (this)
    {
        is Zero -> return 0
        is S    -> return (1 + value.ToInt())
        else    -> throw Exception("Unknown class")
    }
}

fun Peano.Subtract(sub : Peano) : Peano
{
    when (sub)
    {
        is Zero -> return this
        is S    ->
                when (this)
                {
                    is Zero -> return Zero()
                    is S    -> return value.Subtract(sub.value)
                    else    -> throw Exception("Unknown class")
                }
        else    -> throw Exception("Unknown class")
    }
}

fun Peano.Add(add : Peano) : Peano
{
    when (this)
    {
        is Zero -> return add
        is S    -> return S (value.Add(add))
        else    -> throw Exception("Unknown class")
    }
}

fun Peano.Mul(mul : Peano) : Peano
{
    when (this)
    {
        is Zero -> return Zero()
        is S    ->
                when (mul)
                {
                    is Zero -> return Zero()
                    is S    -> return (this.Mul(mul.value).Add(this))
                    else    -> throw Exception("Unknown class")
                }
        else    -> throw Exception("Unknown class")
    }
}

fun Peano.Pow (pow : Peano) : Peano
{
    when (this)
    {
        is Zero -> return Zero()
        is S    ->
                when (pow)
                {
                    is Zero -> return S(Zero())
                    is S -> return this.Pow(pow.value).Mul(this)
                    else    -> throw Exception("Unknown class")
                }
        else    -> throw Exception("Unknown class")
    }
}
/*
fun main (args : Array<String>)
{
    val input = 2
    val sub = 10
    val output = input.ToPeano().Pow(sub.ToPeano()).ToInt()
    println("$output")
    //val max = Int.MAX_VALUE
    //println("$max")

}
*/