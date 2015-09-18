package Homework

abstract class Peano {
    object Zero : Peano() {}
}
class Some (val value : Peano) : Peano () {}

fun Peano.Plus (a : Peano): Peano
{
    when(this)
    {
        is Peano.Zero  -> return a
        is Some         -> return Some(this.value.Plus(a))
        else           -> throw Exception("Unknown class")
    }
}

fun Peano.Minus (a : Peano): Peano
{
    when(this)
    {
        is Peano.Zero -> return Peano.Zero
        is Some        ->
        {
            when (a)
            {
                is Some         -> return this.value.Minus(a.value)
                is Peano.Zero  -> return this
                else           -> throw Exception("Unknown class")
            }
        }
        else          -> throw Exception("Unknown class")
    }
}

fun Peano.Mult (a : Peano): Peano
{
    when(this)
    {
        is Peano.Zero -> return Peano.Zero
        is Some        ->
        {
            when (a)
            {
                is Peano.Zero -> return a
                is Some        ->
                {
                    when(this.value)
                    {
                        is Peano.Zero -> return a
                        is Some        -> return (a.Plus(this.value.Mult(a)))
                        else          -> throw Exception("Unknown class")
                    }
                }
                else          -> throw Exception("Unknown class")
            }
        }
        else          -> throw Exception("Unknown class")
    }
}

fun Peano.Pow (a : Peano): Peano
{
    when(a)
    {
        is Peano.Zero -> return Some(Peano.Zero)
        is Some        -> return this.Mult(this.Pow(a.value))
        else          -> throw Exception("Unknown class")
    }
}

fun Peano.ToInt (): Int
{
    when(this)
    {
        is Peano.Zero -> return 0
        is Some        -> return 1 + this.value.ToInt()
        else          -> throw Exception("Unknown class")
    }
}