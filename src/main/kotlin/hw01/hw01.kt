package hw01

/**
 * Created by Mikhail on 19.09.2015.
 */
public object HeapSort {
    private var N: Int = 0
    public fun sort(arr: IntArray) {
        heapify(arr)
        for (i in N downTo 1) {
            swap(arr, 0, i)
            N = N - 1
            maxheap(arr, 0)
        }
    }

    public fun heapify(arr: IntArray) {
        N = arr.size() - 1
        for (i in N / 2 downTo 0)
            maxheap(arr, i)
    }

    public fun maxheap(arr: IntArray, i: Int) {
        val left = 2 * i
        val right = 2 * i + 1
        var max = i
        if (left <= N && arr[left] > arr[i])
            max = left
        if (right <= N && arr[right] > arr[max])
            max = right

        if (max != i) {
            swap(arr, i, max)
            maxheap(arr, max)
        }
    }

    public fun swap(arr: IntArray, i: Int, j: Int) {
        val tmp = arr[i]
        arr[i] = arr[j]
        arr[j] = tmp
    }

    public fun printArray(arr: IntArray) {
        var i: Int = 0
        while (i < arr.size()) {
            print(arr[i].toString() + " ")
            i++
        }
        println()
    }

    public fun main(args: Array<String>) {

        val arr: IntArray = intArrayOf(1, 3, 4, 2, 0)
        val arr1: IntArray = intArrayOf()
        val arr2: IntArray = intArrayOf(-1, 2, 0, 1000, -4)
        println("===HEAPSORT===\n")
        printArray(arr)
        printArray(arr1)
        printArray(arr2)
        sort(arr)
        sort(arr1)
        sort(arr2)
        println("\nElements after sorting ")
        printArray(arr)
        printArray(arr1)
        printArray(arr2)
        println()
    }
}

//Task 2, 3: clr and max sum

abstract class Tree {}
open class Empty(): Tree() {}
open class Leaf(val value: Int): Tree() {}
open class Node(val value: Int, val l: Tree, val r: Tree): Tree() {}

public fun genTree(lValue: Int, rValue: Int) : Tree {
    if (lValue > rValue) { return Empty() }
    if (lValue == rValue - 1) { return Leaf(lValue) }

    val middle = lValue + (rValue - lValue) / 2
    return Node(middle, genTree(lValue, middle - 1), genTree(middle + 1, rValue))
}

public fun Tree.toText(): String {
    fun Tree.toText_() : List<String> {
        when (this) {
            is Empty -> return listOf("_\n")
            is Leaf  -> return listOf("${this.value}\n")
            is Node  -> {
                val lText = l.toText_().map { "| $it"}
                val rText = r.toText_().map { "| $it"}
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

public fun <B> Tree.fold(fEmpty: B, fLeaf: (Int) -> B, fNode: (B, B, B) -> B): B {
    when (this) {
        is Empty -> return fEmpty
        is Leaf  -> return fLeaf(value)
        is Node  -> {
            return fNode(fLeaf(value),
                    l.fold(fEmpty, fLeaf, fNode),
                    r.fold(fEmpty, fLeaf, fNode))
        }
        else -> throw Exception("Unknown class")
    }
}

fun <B> foldTD(fEmpty: B, f1: (B, Int) -> B, f2: (B, B) -> B, tree: Tree): B {
    when (tree) {
        is Empty -> return fEmpty
        is Leaf -> return f1(fEmpty, tree.value)
        is Node -> return f2(foldTD(f1(fEmpty, tree.value), f1, f2, tree.l),
                foldTD(f1(fEmpty, tree.value), f1, f2, tree.r))
        else -> throw Exception("Unknown class")
    }
}

public fun Tree.maxWay() : Int {
    val max = {l:Int, r:Int, c:Int -> if (l > r) (c + l) else (c + r)}
    return fold(0, {it}, max)
}

/*public fun main(args:Array<String>) {
    val t = genTree(0, 7)
    val res = t.maxWay()
    println("===TREE===\n")
    println(t.toText())
    println("Sum of max way in tree is " + res)
}*/


//Task 4: Peano

abstract class Peano {}
open class Zero(): Peano() {}
open class S(val value: Peano) : Peano() {}

public fun toInt(a: Peano): Int {
    when (a) {
        is Zero -> return 0
        is S -> return (toInt(a.value) + 1)
        else -> throw Exception("Unknown class")
    }
}

public fun plus(a: Peano, b: Peano): Peano {
    when (a) {
        is Zero -> return b
        is S -> return S(plus(a.value, b))
        else -> throw Exception("Unknown class")
    }
}

public fun minus(a: Peano, b: Peano): Peano {
    when (a) {
        is Zero -> return Zero()
        is S ->
            when (b) {
                is Zero -> return a
                is S -> return minus(a.value, b.value)
                else -> throw Exception("Unknown class")
            }
        else -> throw Exception("Unknown class")
    }
}

public fun mult(a: Peano, b: Peano): Peano {
    when (a) {
        is Zero -> return Zero()
        is S -> return plus(b, mult(a.value, b))
        else -> throw Exception("Unknown class")
    }
}

public fun deg(a: Peano, b: Peano): Peano {
    when (a) {
        is Zero -> return Zero()
        is S ->
            when (b) {
                is Zero -> return S(Zero())
                is S -> return mult(S(a.value), deg(S(a.value), b.value))
                else -> throw Exception("Unknown class")
            }
        else -> throw Exception("Unknown class")
    }
}
/*public fun main(args: Array<String>) {
    val x1:Int = toInt(plus(S(S(S(Zero()))), Zero()))
    val x2:Int = toInt(plus(S(S(S(Zero()))), S(S(Zero()))))
    val x3:Int = toInt(minus(S(S(S(Zero()))), S(S(Zero()))))
    val x4:Int = toInt(mult(S(S(S(Zero()))), S(S(Zero()))))
    val x5:Int = toInt(deg(S(S(S(Zero()))), S(S(Zero()))))
    val x6:Int = toInt(minus(S(S(S(Zero()))), Zero()))
    val x7:Int = toInt(mult(S(S(S(Zero()))), Zero()))
    val x8:Int = toInt(deg(S(S(S(Zero()))), Zero()))
    println("\n===PEANO===\n")
    println("3+0=" + x1)
    println("3+2=" + x2)
    println("3-2=" + x3)
    println("3*2=" + x4)
    println("3^2=" + x5)
    println("3-0=" + x6)
    println("3*0=" + x7)
    println("3^0=" + x8)

}*/


fun main(args: Array<String>) {
    HeapSort.main(args)
    //TreeT.main(args)
    //PeanoNum.main(args)
}
