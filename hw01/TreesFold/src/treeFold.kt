/**
 * Created by Alexander Chebykin on 10.09.2015.
 * Estimated time: 30 min. Actual: 1 hr
 */
abstract class Tree {}
open class Empty() : Tree() {}
open class Leaf(val value : Int) : Tree() {}
open class Node(val value : Int, val l : Tree, val r : Tree) : Tree() {}

fun genTree(lValue : Int, rValue : Int) : Tree {
    if (lValue >= rValue) {
        return Empty()
    }
    if (lValue == rValue - 1) {
        return Leaf(lValue)
    }

    val middle = lValue + (rValue - lValue) / 2
    return Node(middle, genTree(lValue, middle), genTree(middle + 1, rValue))
}
//Task 2: the goal is to find max sum on the way from root to leaf.
fun Tree.findMaxSum () : Int
{
    return fold(0, {it}, {(c, l, r) ->
        if (l > r) (c + l)
        else (c + r)
    })
}
//Task 3: write descending fold.
fun <B> Tree.fold(fEmpty : B, f : (Int) -> B, fNode : (B, B, B) -> B) : B {
    when (this) {
        is Empty -> return fEmpty
        is Leaf  -> return f(value)
        is Node  -> {
            val cur = f(value)
            return fNode(cur,
                    l.fold(fEmpty, f, fNode),
                    r.fold(fEmpty, f, fNode))
        }
        else -> throw Exception("Unknown class")
    }
}

fun main(args : Array<String>)
{
    var tree = genTree(0, 5)
    val sum = tree.fold(0, {it}, {(v, f, g) ->
        if (f > g) (v + f)
        else (v + g)
    })
    println("$sum")
    1
}