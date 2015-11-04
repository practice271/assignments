package classroom.c01

/*
type Tree = Empty
          | Leaf Int
          | Node Int Tree Tree
*/
abstract class Tree {}
class Empty() : Tree() {}
class Leaf(val value: Int): Tree() {}
class Node(val value: Int, val l: Tree, val r: Tree): Tree() {}

fun genTree(lValue: Int, rValue: Int): Tree {
    if (lValue >= rValue    ) { return Empty() }
    if (lValue == rValue - 1) { return Leaf(lValue) }
    val middle = lValue + (rValue - lValue) / 2
    return Node(middle, genTree(lValue, middle), genTree(middle + 1, rValue))
}

fun Tree.toText_old(): List<String> {
    when (this) {
        is Empty -> return listOf("_\n")
        is Leaf  -> return listOf("$value\n")
        is Node  -> {
            val lText = l.toText_old().map { "| $it"}
            val rText = r.toText_old().map { "| $it"}
            val vText = listOf("$value\n")
            return lText + vText + rText
        }
        else -> throw Exception("Unknown class")
    }
}

fun Tree.toText_(): List<String> {
    return fold(listOf("_\n"), { listOf("$it\n") }) { value, lRes, rRes ->
      val lText = lRes.map { "| $it"}
      val rText = rRes.map { "| $it"}
      val vText = listOf("$value\n")
      lText + vText + rText
    }
}

fun <B> Tree.fold(fEmpty: B, fLeaf: (Int) -> B, fNode: (Int, B, B) -> B) : B {
    when (this) {
        is Empty -> return fEmpty
        is Leaf  -> return fLeaf(value)
        is Node  -> return fNode(value,
                                 l.fold(fEmpty, fLeaf, fNode),
                                 r.fold(fEmpty, fLeaf, fNode))
        else -> throw Exception("Unknown class")
    }
}

fun Tree.toText(): String {
    val builder = StringBuilder()
    val lines = toText_()
    lines.forEach { builder.append(it) }
    return builder.toString()
}

fun Array<Int>.quicksort() {
    fun Array<Int>.quicksort_(l: Int, r: Int) {
        val middle = l + (r - l) / 2
        val mval = this[middle]

        var i = l
        var j = r
        while (i < j) {
            while (this[i] < mval) {
                i++
            }
            while (this[j] > mval) {
                j--
            }
            if (i <= j) {
                val tmp = this[i]
                this[i] = this[j]
                this[j] = tmp
                i++; j--
            }
        }
        if (j > l) {
            quicksort_(l, j)
        }
        if (i < r) {
            quicksort_(i, r)
        }
    }
    quicksort_(0, size - 1)
}

fun main(args: Array<String>) {
    val tree = genTree(0, 4)
    print(tree.toText())

    /*
    val a = arrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
    fun Array<out Any>.printArray() {
        this.forEach {
            print("$it, ")
        }
        println()
    }

    a.printArray()
    a.quicksort()
    a.printArray()
    */
}
