/* Heapsort; Find a max sum of tree from node to leaf;
Fold for trees; Some operations (+, -, *, power) with Peano numbers
(expectation: 3 h; reality: 7,5 h)
by Sokolova Polina */

package hw01

//Heapsort
fun swap(a : Array<Int>, i : Int, j : Int) : Array<Int> {
    val temp = a[i]
    a[i] = a[j]
    a[j] = temp
    return a
}

fun siftDown(a : Array<Int>, start : Int, end : Int) : Array<Int> {
    var root = start
    while (root * 2 + 1 <= end) {
        var child = root * 2 + 1
        var swapNum = root
        if (a[swapNum] < a[child]) { swapNum = child }
        if ((child + 1 <= end) && (a[swapNum] < a[child + 1])) {
            swapNum = child + 1
        }
        if (swapNum == root) { return a }
        else {
            swap(a, root, swapNum)
            root = swapNum
        }
    }
    return a
}

fun heapify(a : Array<Int>) : Array<Int> {
    var start = (a.size() - 2) / 2
    while (start >= 0) {
        siftDown(a, start, a.size() - 1)
        start--
    }
    return a
}

fun heapsort(a : Array<Int>) : Array<Int> {
    heapify(a)
    var end = a.size() - 1
    while (end > 0) {
        swap(a, end, 0)
        end--
        siftDown(a, 0, end)
    }
    return a
}

abstract class Tree {}
class Empty() : Tree() {}
class Leaf(val value: Int): Tree() {}
class Node(val value: Int, val l: Tree, val r: Tree): Tree() {}

fun <B> Tree.fold(fEmpty: B, fLeaf: (Int) -> B, fNode: (Int, B, B) -> B) : B {
    when (this) {
        is Empty -> { return fEmpty }
        is Leaf  -> { return fLeaf(value) }
        is Node  -> { return fNode(value, l.fold(fEmpty, fLeaf, fNode),
                r.fold(fEmpty, fLeaf, fNode)) }
    }
    return fEmpty
}

fun genTree(lValue: Int, rValue: Int): Tree {
    if (lValue >= rValue    ) { return Empty() }
    if (lValue == rValue - 1) { return Leaf(lValue) }
    val middle = lValue + (rValue - lValue) / 2
    return Node(middle, genTree(lValue, middle), genTree(middle + 1, rValue))
}

fun Tree.toText_(): List<String> {
    return fold(listOf("-\n"), { listOf("$it\n") }) {(value, lRes, rRes) ->
        val lText = lRes.map { "| $it" }
        val rText = rRes.map { "| $it" }
        val vText = listOf("$value\n")
        lText + vText + rText
    }
}

fun Tree.toText(): String {
    val builder = StringBuilder()
    val lines = toText_()
    lines.forEach { builder.append(it)}
    return builder.toString()
}

//Fold for trees
fun Tree.myFold(f : (Int, Int) -> Int, acc : Int, fSide : (Int, Int) -> Int ) : Int {
    when (this) {
        is Empty -> { return acc }
        is Leaf  -> { return f(acc, value)}
        is Node  -> {
            val left = l.myFold(f, acc, fSide)
            val right = r.myFold(f, acc, fSide)
            return f(fSide(left, right), value)
        }
    }
    return acc
}

// Find max sum on a path from a root to leafs
fun Tree.findMaxSum(acc : Int) : Int {
    when (this) {
        is Empty -> { return acc }
        is Leaf  -> { return acc + value }
        is Node  -> {
            val left = l.findMaxSum(acc)
            val right = r.findMaxSum(acc)
            if (left > right) { return left + value }
            else { return right + value }
        }
    }
    return acc
}

//Peano numbers
abstract class Peano {}
class Zero() : Peano() {}
class S(val value : Peano) : Peano() {}

fun Peano.print(acc : String) : String {
    when (this) {
        is Zero -> { return acc + "Zero" }
        is S    -> { return value.print(acc + "S ") }
    }
    return acc
}

fun Peano.addition(a : Peano) : Peano {
    when (this) {
        is Zero -> { return a }
        is S    -> {
            when (a) {
                is Zero -> { return this }
                is S    -> { return value.addition(S(a)) }
            }

        }
    }
    return Zero()
}

fun Peano.substraction(a : Peano) : Peano {
    when (this) {
        is Zero -> { return Zero() }
        is S    -> {
            when (a) {
                is Zero -> { return this }
                is S    -> { return value.substraction(a.value) }
            }
        }
    }
    return Zero()
}

fun Peano.multiplication(a : Peano) : Peano {
    when (this) {
        is Zero -> { return Zero() }
        is S    -> {
            when (a) {
                is Zero -> { return Zero() }
                is S    -> { return this.addition(this.multiplication(a.value)) }
            }
        }
    }
    return Zero()
}

fun Peano.power(a : Peano) : Peano {
    when (this) {
        is Zero -> { return Zero() }
        is S -> {
            when (a) {
                is Zero -> { return S(Zero()) }
                is S    -> { return this.multiplication(this.power(a.value)) }
            }
        }
    }
    return Zero()
}

fun main() {}