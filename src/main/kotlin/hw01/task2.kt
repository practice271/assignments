package hw01

abstract class Tree {}
open class Empty() : Tree() {}
open class Leaf(val value : Int) : Tree() {}
open class Node(val value : Int, val l : Tree, val r : Tree) : Tree() {}

fun Tree.fold(fEmpty : Int, fLeaf : (Int) -> Int, fNode : (Int, Int, Int) -> Int) : Int {
    return when (this) {
        is Empty -> fEmpty
        is Leaf  -> fLeaf(value)
        is Node  -> fNode(value,
                l.fold(fEmpty, fLeaf, fNode),
                r.fold(fEmpty, fLeaf, fNode))
        else -> throw Exception("Unknown class")
    }
}

fun Tree.maxpath(): Int {
    return this.fold(0, {it}, {
        c: Int, a: Int, b: Int ->
        if(a > b)
            (c + a)
        else
            (c + b)
    })
}

