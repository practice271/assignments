open class Peano {}
class S(val value:Peano): Peano() {}
class Zero():Peano() {}

fun plus (a:Peano, b:Peano):Peano{
    when (a) {
        is Zero -> return b
        is S -> {
            when (b) {
                is Zero -> return a
                is S    -> return plus(a.value, S(b))
                else    -> throw Exception("Unknown class")
            }
        }
        else -> throw Exception("Unknown class")
    }
}


fun sub (a:Peano, b:Peano):Peano{
    when (a){
        is Zero -> return Zero()
        is S    -> {
            when (b){
                is S    -> return sub(a , b)
                is Zero -> return a
                else -> throw Exception("Unknown class")
            }
        }
        else -> throw Exception("Unknown class")
    }
}


fun mult(a:Peano, b:Peano):Peano{
    when(a){
        is Zero-> return Zero()
        is S -> return plus(b, (mult(a.value, b)))
        else -> throw Exception("Unknown class")
    }
}

fun exp(a:Peano, b: Peano):Peano{
    when(b){
        is Zero -> return S(Zero())
        is S    -> return mult(a, exp(a , b.value ))
        else    -> throw Exception("Unknown class")
    }
}

fun toInt(p:Peano) : Int {
    when (p) {
        is Zero -> return 0
        is S    -> return 1 + toInt(p.value)
        else    -> throw Exception("Error")
    }
}


fun main(args: Array<String>) {
    var a = S(S(S(Zero())))
    var b = S(S(Zero()))
    var c = Zero()

    println("${toInt(a)} " + "+ " + "${toInt(b)}" + " = " + "${toInt(plus(a,b))}") //3 + 2 = 5
    println("${toInt(a)} " + "- " + "${toInt(c)}" + " = " + "${toInt(sub(a,c))}") // 3 - 0 = 3
    println("${toInt(b)} " + "* " + "${toInt(c)}" + " = " + "${toInt(mult(b,c))}") // 2 * 0 = 0
    println("${toInt(b)} " + "^ " + "${toInt(a)}" + " = " + "${toInt(exp(b,a))}")


}