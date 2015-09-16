/**
 * Created by Antropov Igor on 12.09.2015.
 * 271 Task 1 - 4
 * expected 5 hours
 * spent    4 hours
 */

fun Array<Int>.HeapSort() {
    var i = this.size()  / 2 - 1
    var temp: Int

    while (i >= 0)
    {
        shiftDown(this, i, this.size())
        i--
    }

    i = this.size() - 1

    while (i >= 1)
    {
        temp    = this[0]
        this[0] = this[i]
        this[i] = temp
        shiftDown(this, 0, i)
        i--
    }
}

fun shiftDown(a : Array<Int>, i: Int, j: Int) {
    var done = false
    var maxChild: Int
    var temp: Int
    var p = i

    while ((p * 2 + 1 < j) && (!done))
    {
        if (p * 2 + 1 == j - 1)
            maxChild = p * 2 + 1
        else if (a[p * 2 + 1] > a[p * 2 + 2])
            maxChild = p * 2 + 1
             else
            maxChild = p * 2 + 2

        if (a[p] < a[maxChild])
        {
            temp = a[p]
            a[p] = a[maxChild]
            a[maxChild] = temp
            p = maxChild
        }
        else
        {
            done = true
        }
    }
}


abstract class Tree {}
open class Empty() : Tree() {}
open class Leaf(val value : Int) : Tree() {}
open class Node(val value : Int, val l : Tree, val r : Tree) : Tree() {}

fun genTree(lValue : Int, rValue : Int) : Tree {
    if (lValue > rValue) { return Empty() }
    if (lValue == rValue - 1) { return Leaf(lValue) }

    val middle = lValue + (rValue - lValue) / 2
    return Node(middle, genTree(lValue, middle - 1), genTree(middle + 1, rValue))
}

fun <B> Tree.fold(fEmpty : B, fLeaf : (Int) -> B, fNode : (Int, B, B) -> B) : B {
    when (this) {
        is Empty -> return fEmpty
        is Leaf  -> return fLeaf(value)
        is Node  -> return fNode(value,
                l.fold(fEmpty, fLeaf, fNode),
                r.fold(fEmpty, fLeaf, fNode))
        else -> throw Exception("Unknown class")
    }
}

fun Tree.maxInWays(): Int {
    return fold(0, {it}, {(value, l, r) ->
        if (l > r) value + l
        else value + r
    })
}

abstract class Peano {}
open class Zero() : Peano() {}
open class S(val value : Peano) : Peano() {}


fun plus(a: Peano, b: Peano): Peano {
    when (a) {
        is Zero -> return b
        is S    -> return S(plus( a.value, b))
        else    -> throw Exception("Unknown class")
    }
}

fun minus(a: Peano, b: Peano): Peano{
    when (b) {
        is Zero -> return a
        is S    ->
               when (a) {
               is Zero -> return Zero()
               is S    -> return minus(a.value, b.value)
               else    -> throw Exception("Unknown class")
        }
        else    ->  throw Exception("Unknown class")
    }
}

fun mult(a: Peano, b: Peano): Peano{
    when (a){
        is Zero -> return Zero()
        is S    -> return plus(b, (mult(a.value, b)))
        else    -> throw Exception("Unknown class")
    }
}

fun pow(a: Peano, b: Peano): Peano{
    when (b){
        is Zero -> return S(Zero())
        is S    ->
                when (a){
                    is Zero -> return Zero()
                    is S    -> return mult ((a), (pow(a, b.value)))
                    else    -> throw Exception("Unknown class")
                }
        else    -> throw Exception("Unknown class")
    }
}

fun peanoToInt(a: Peano): Int{
    when (a){
        is Zero -> return 0
        is S    -> return peanoToInt(a.value) + 1
        else    -> throw Exception("Unknown class")
    }
}

fun intToPeano (a: Int): Peano{
    if (a == 0) return Zero()
    else if (a > 0) return  S(intToPeano(a - 1))
    else throw Exception("Unknown class")
}

fun main(args: Array<String>) {

    fun Array<out Any>.printArray() {
        this.forEach {
            print("$it ")
        }
        println()
    }

    var a = arrayOf (5,3,4,8,6,11,55,48,633,95,21,14,15,365,2,5,41,5,487)
    a.HeapSort()
    a.printArray()



    val tree = genTree(0, 8)
    println (tree.maxInWays())



    val first  = 5
    val second = 3

    println (peanoToInt(plus(intToPeano(first), intToPeano(second))))
    println (peanoToInt(minus(intToPeano(first), intToPeano(second))))
    println (peanoToInt(mult(intToPeano(first), intToPeano(second))))
    println (peanoToInt(pow(intToPeano(first), intToPeano(second))))

}