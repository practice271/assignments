package classroom.c02

/*
abstract class Tree() {}
class Empty(): Tree() {}
class Leaf(val value: Int) {}
*/

class Tree(
        val value: Int,
        val left : Tree?,
        val right: Tree?
) {
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

fun main(args: Array<String>) {
    val tree: Tree? = getTree(1)
    val value: Int = tree?.value ?: 0
    print(hello())
}
fun hello(): String {
   return "Hello"
}