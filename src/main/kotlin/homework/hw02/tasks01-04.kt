// Homework 2
// Alekseev Aleksei, group 271.

package homework.hw02

/*Task 1*/

fun heapSort(array : Array<Int>) {
    for (i in array.size() / 2 downTo 0) {
        siftDown(array, i, array.size())
    }
    for (i in array.size() - 1 downTo 1) {
        var temp = array[0]
        array[0] = array[i]
        array[i] = temp
        siftDown(array, 0, i)
    }
}

fun siftDown(array : Array<Int>, start : Int, end : Int) {
    var root = start
    while (root * 2 + 1 < end) {
        var child = root * 2 + 1
        if ((child + 1 != end) && (array[child] <= array[child + 1]))
            child =+ child + 1
        if (array[root] < array[child]) {
            var temp = array[root]
            array[root] = array[child]
            array[child] = temp
            root = child
        }
        else return
    }
}

/*Task 2*/

open class Tree {}
class Empty : Tree() {}
class Leaf(val value : Int) : Tree() {}
class Node(val value : Int, val left : Tree, val right : Tree) : Tree() {}

fun Tree.toText() : String {
    fun Tree.toText_() : List<String> {
        when (this) {
            is Empty -> return listOf("_\n")
            is Leaf  -> return listOf("${this.value}\n")
            is Node  -> {
                val lText = left.toText_().map { "| $it"}
                val rText = right.toText_().map { "| $it"}
                val vText = listOf("$value\n")
                return lText + vText + rText
            }
            else -> throw Exception("Unknown class")
        }
    }
    val builder = StringBuilder()
    val lines = this.toText_()
    lines.forEach { builder.append(it) }
    return builder.toString()
}

fun maxWay(f : (Int, Int) -> Int, acc : Int, tree : Tree) : Int {
    when (tree) {
        is Empty -> return acc
        is Leaf  -> return f(acc, tree.value)
        is Node  -> return Math.max(maxWay(f, f(acc, tree.value), tree.left), maxWay(f, f(acc, tree.value), tree.right))
        else     -> throw Exception("Error")
    }
}

/*Task 3*/

fun fold(f1 : (Int, Int) -> Int, f2 : (Int, Int) -> Int, acc : Int, tree : Tree) : Int {
    when (tree) {
        is Empty -> return acc
        is Leaf  -> return f1(acc, tree.value)
        is Node  -> return f1(f1(acc, tree.value), f2(fold(f1, f2, acc, tree.left), fold(f1, f2, acc, tree.right)))
        else -> throw Exception("Error")
    }
}

/*Task 4*/

open class Peano {}
class Zero : Peano() {}
class S(val value : Peano) : Peano() {}

fun intToPeano(n : Int) : Peano {
    when (n) {
        0    -> return Zero()
        else -> return S(intToPeano(n - 1))
    }
}

fun peanoToInt(p : Peano, acc : Int) : Int {
    when (p) {
        is Zero -> return acc
        is S    -> return peanoToInt(p.value, acc + 1)
        else    -> throw Exception("Error")
    }
}

fun peanoPlus(a : Peano, b : Peano) : Peano {
    when (a) {
        is Zero -> return b
        is S -> return (S(peanoPlus(a.value, b)))
        else -> throw Exception ("Error")
    }
}

fun peanoMinus(a : Peano, b : Peano) : Peano {
    when (a) {
        is Zero -> return b
        is S    -> {
            when (b) {
                is Zero -> return a
                is S    -> return peanoMinus(a.value, b.value)
                else    -> throw Exception ("Error")
            }
        }
        else -> throw Exception ("Error")
    }
}

fun peanoMult(a : Peano, b : Peano) : Peano {
    when (a) {
        is Zero -> return a
        is S    -> return peanoPlus(b, peanoMult(a.value, b))
        else    -> throw Exception ("Error")
    }
}

fun peanoExp(a : Peano, b : Peano) : Peano {
    when (b) {
        is Zero -> return S(b)
        is S    -> return peanoMult(a, peanoExp(a, b.value))
        else    -> throw Exception ("Error")
    }
}

fun Peano.print() {
    when (this) {
        is Zero -> print("Zero")
        is S    -> {
            print("S(")
            (this.value).print()
            print(")")
        }
    }
}

fun main(args: Array<String>) {
}