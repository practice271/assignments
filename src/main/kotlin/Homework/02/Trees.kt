package Homework

abstract class Tree {}
open class Empty() : Tree() {}
open class Leaf(val value : Int) : Tree() {}
open class Node(val value : Int, val l : Tree, val r : Tree) : Tree() {}

fun genTree (lValue : Int, rValue : Int) : Tree
{
    if (lValue > rValue)
    {
        return Empty()
    }
    if (lValue == rValue - 1)
    {
        return Leaf(lValue)
    }

    val middle = lValue + (rValue - lValue) / 2
    return Node(middle, genTree(lValue, middle - 1), genTree(middle + 1, rValue))
}

fun <A> Tree.MyFold (fEmpty : A, fLeaf : (Int) -> A, fNode : (A, A, A) -> A) : A
{
    when (this)
    {
        is Empty -> return fEmpty
        is Leaf  -> return fLeaf(value)
        is Node  -> return fNode(fLeaf(value),
                l.MyFold(fEmpty, fLeaf, fNode),
                r.MyFold(fEmpty, fLeaf, fNode))
        else     -> throw Exception("Unknown class")
    }
}

fun Tree.FindMaxInLine () : Int
{
    return MyFold(0, {it}, {(value, l, r) ->
        if (l > r)
        {
            value + l
        }
        else
        {
            value + r
        }})
}