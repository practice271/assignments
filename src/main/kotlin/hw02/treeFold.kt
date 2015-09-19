/**
 * Created by Alexander Chebykin on 10.09.2015.
 * Estimated time: 30 min. Actual: 1 hr
 */
package hw02
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
    return fold(0, {acc, value -> acc + value},
            {l, r -> if (l > r) l else r
    })
}
//Task 3: write descending fold.
fun <A> Tree.fold(acc : A, f : (Int, A) -> A, fNode : (A, A) -> A) : A {
    when (this) {
        is Empty -> return acc
        is Leaf  -> return f(value, acc)
        is Node  -> {
            val newAcc = f(value, acc)
            val lres = l.fold(newAcc, f, fNode)
            val rres = r.fold(newAcc, f, fNode)
            return fNode(lres, rres)
        }
        else -> throw Exception("Unknown class")
    }
}
fun Tree.print () : Unit
{
    return fold(println("\n"), {value, acc -> println("$value")},
            {l, r -> Unit})
}
/*
fun main(args : Array<String>)
{
    var tree = genTree(0, 5)
    val sum = tree.findMaxSum()
    println("$sum")
    tree.print()
}
*/