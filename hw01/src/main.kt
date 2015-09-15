/* Heapsort; Find a max sum of tree from node to leaf;
Fold for trees; Some operations (+, -, *, power) with Peano numbers
(expectation: 3 h; reality: 7,5 h)
by Sokolova Polina */

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
        if ((child + 1 <= end) && (a[swapNum] < a[child + 1])) { swapNum = child + 1 }
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
fun Tree.myFold(f : (Int, Int) -> Int, acc : Int) : Int {
    when (this) {
        is Empty -> { return acc }
        is Leaf  -> { return f(acc, value)}
        is Node  -> {
            val left = l.myFold(f, acc)
            val right = r.myFold(f, acc)
            return f(f(left, right), value)
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

fun Peano.print()  {
    when (this) {
        is Zero -> { print("Zero\n") }
        is S    -> {
            print("S ")
            value.print()
        }
    }
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

fun main(args: Array<String>) {
    val array1 = arrayOf(4, 6, 78, 0, 14, 1, 56, 0, 19)
    var arrayRes = heapsort(array1)
    print("array1 = ")
    for (c in arrayRes) {
        print("$c ")
    }
    println()

    val array2 = arrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
    arrayRes = heapsort(array2)
    print("array2 = ")
    for (c in arrayRes) {
        print("$c ")
    }
    println()

    val array3 = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
    arrayRes = heapsort(array3)
    print("array3 = ")
    for (c in arrayRes) {
        print("$c ")
    }
    println("\n")

    val a = 1
    val b = 10
    val c = -4
    val d = 18
    val t1 = genTree(a, b)
    val t2 = genTree(c, d)

    println(t1.toText())
    print("Tree.findMaxSum = ")
    println(t1.findMaxSum(0))
    print("Tree.myFold (max element) = ")
    println(t1.myFold({ (a, b) -> if (a < b) b else a }, 0))
    print("Tree.myFold (multiplication) = ")
    println(t1.myFold({ (a, b) -> a * b }, 1))
    println()

    println(t2.toText())
    print("Tree.findMaxSum = ")
    println(t2.findMaxSum(0))
    print("Tree.myFold (min element) = ")
    println(t2.myFold({ (a, b) -> if (a > b) b else a }, 0)) 
    print("Tree.myFold (sum) = ")
    println(t2.myFold({ (a, b) -> a + b }, 0)) 
    println()

    val p1 = S(S(Zero()))
    val p2 = S(S(S(Zero())))
    val p3 = Zero()

    print("Peano.addition (2 + 3) = ")
    p1.addition(p2).print()
    print("Peano.addition (3 + 2) = ")
    p2.addition(p1).print()
    print("Peano.addition (2 + 0) = ")
    p1.addition(p3).print()
    print("Peano.addition (0 + 3) = ")
    p3.addition(p2).print()
    print("Peano.addition (0 + 0) = ")
    p3.addition(p3).print()
    println()

    print("Peano.substraction (3 - 2) = ")
    p2.substraction(p1).print()
    print("Peano.substraction (0 - 3) = ")
    p3.substraction(p2).print()
    print("Peano.substraction (2 - 0) = ")
    p1.substraction(p3).print()
    print("Peano.substraction (2 - 3) = ")
    p1.substraction(p2).print()
    print("Peano.substraction (0 - 0) = ")
    p3.substraction(p3).print()
    println()

    print("Peano.multiplication (3 * 2) = ")
    p2.multiplication(p1).print()
    print("Peano.multiplication (2 * 3) = ")
    p1.multiplication(p2).print()
    print("Peano.multiplication (0 * 3) = ")
    p3.multiplication(p2).print()
    print("Peano.multiplication (2 * 0) = ")
    p1.multiplication(p3).print()
    print("Peano.multiplication (0 * 0) = ")
    p3.multiplication(p3).print()
    println()
    
    print("Peano.power (3 ^ 2) = ")
    p2.power(p1).print()
    print("Peano.power (2 ^ 3) = ")
    p1.power(p2).print()
    print("Peano.power (0 ^ 3) = ")
    p3.power(p2).print()
    print("Peano.power (2 ^ 0) = ")
    p1.power(p3).print()
}