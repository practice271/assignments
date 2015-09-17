abstract class Tree {}
open class Empty() : Tree() {}
open class Leaf(val value : Int) : Tree() {}
open class Node(val value : Int, val l : Tree, val r : Tree) : Tree() {}

fun <B> foldTopDown(acc: B, f1: (B, Int) -> B, f2: (B, B) -> B, tree: Tree) : B {
    when (tree) {
        is Empty -> return acc
        is Leaf -> return f1(acc, tree.value)
        is Node -> return f2(foldTopDown(f1(acc, tree.value), f1, f2, tree.l),
                foldTopDown(f1(acc, tree.value), f1, f2, tree.r))
        else -> throw Exception("Unknown class")
    }
}

fun <B> foldTree(acc: B, f: (B, B, Int) -> B, tree: Tree) : B {
    when (tree) {
        is Empty -> return acc
        is Leaf -> return f(acc, acc, tree.value)
        is Node -> return f(foldTree(acc, f, tree.l), foldTree(acc, f, tree.r), tree.value)
        else -> throw Exception("Unknown class")
    }
}

fun maxPath(tree: Tree) : Int {
    val f = { left: Int, right: Int, elem: Int ->
        if (left > right) (left + elem) else (right + elem)
    }
    return foldTree(0, f, tree)
}