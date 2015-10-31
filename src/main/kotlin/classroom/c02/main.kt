package classroom.c02

import java.util.*

/*
abstract class Tree() {}
class Empty(): Tree() {}
class Leaf(val value: Int) {}
*/

class Tree(
        val value: Int,
        left_param : Tree?,
        right_param: Tree?
) {
    public var left: Tree? = left_param
        get() = field
        set(newLeft: Tree?) {
            field = newLeft
            height_f = calcHeight()
        }

    public var right: Tree? = right_param
        get() = field
        set(newRight: Tree?) {
            field = newRight
            height_f = calcHeight()
        }
    private fun calcHeight() : Int =
            1 + Math.max(left?.calcHeight() ?: 0, right?.calcHeight() ?: 0)
    private var height_f : Int = calcHeight()
    fun height(): Int = height_f

    fun <B> fold(fLeaf: (Int) -> B, fOneChild: (Int, B) -> B, fTwoChildren: (Int, B, B) -> B): B {
        val lRes = left ?.fold(fLeaf, fOneChild, fTwoChildren)
        val rRes = right?.fold(fLeaf, fOneChild, fTwoChildren)

        if (lRes == null && rRes != null) { return fOneChild(value, rRes) }
        if (lRes != null && rRes == null) { return fOneChild(value, lRes) }
        if (lRes != null && rRes != null) { return fTwoChildren(value, lRes, rRes) }
        return fLeaf(value)
    }

    fun <A, B> foldTopDown(
            acc: A, calcAcc: (A, Int) -> A,
            fLeaf: (A, Int) -> B, fOneChild: (Int, B) -> B, fTwoChildren: (Int, B, B) -> B
    ): B {
        val newAcc = calcAcc(acc, value)
        val lRes = left ?.foldTopDown(newAcc, calcAcc, fLeaf, fOneChild, fTwoChildren)
        val rRes = right?.foldTopDown(newAcc, calcAcc, fLeaf, fOneChild, fTwoChildren)

        if (lRes == null && rRes != null) { return fOneChild(value, rRes) }
        if (lRes != null && rRes == null) { return fOneChild(value, lRes) }
        if (lRes != null && rRes != null) { return fTwoChildren(value, lRes, rRes) }
        return fLeaf(acc, value)
    }
}

fun getTree(v: Int): Tree? {
    if (v == 0) {
        return null
    }
    return Tree(v, null, null)
}

fun asdf(/*a: Tree?*/): Pair<Int, Int> {
    if (true) {
        val left = 5 //...
        val right = 5 //...
        return  Pair(left, right)
    }
    val left = 6 //...
    val right = 6 //...
    return  Pair(left, right)


    //val array: Array<Empty<Int>> = Array(100500, {ArrayList<Int>()})
}

fun main(args: Array<String>) {
    var tree: Tree? = getTree(1)
    val tree_val = tree
    if (tree_val != null) {
        val value: Int = tree_val.value
        println(value)
    }


    print(hello())
}

fun hello() = "Hello"
