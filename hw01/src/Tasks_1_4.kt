/*
Homework 1 (08.09.2015)
Tasks 1 - 4

Author: Mikhail Kita, group 271
*/

//The 1st task
fun heapSort(array : Array<Int>) {
    //builds the heap in array
    for (i in array.size() / 2 downTo 0) {
        shiftDown(array, i, array.size())
    }

    //sorts array in ascending order
    for (i in array.size() - 1 downTo 1) {
        var temp = array[0]
        array[0] = array[i]
        array[i] = temp
        shiftDown(array, 0, i)
    }
}

//repair the heap whose root element is at index 'start'
fun shiftDown(array : Array<Int>, start : Int, end : Int) {
    var done = false
    var root = start

    while ((root * 2 + 1 < end) && (!done)) {
        var child = root * 2 + 1

        if ((child + 1 != end) && (array[child] <= array[child + 1]))
            child++

        if (array[root] < array[child]) {
            var temp = array[root]
            array[root] = array[child]
            array[child] = temp
            root = child
        }
        else
            done = true
    }
}

//The 2nd task
open class Tree {}

class Empty : Tree() {}
class Leaf(val value : Int) : Tree() {}
class Node(val value : Int, val left : Tree, val right : Tree) : Tree() {}

//prints tree
fun Tree.toText() : String {
    fun Tree.toText_() : List<String> {
        when (this) {
            is Empty -> return listOf("_\n")
            is Leaf  -> return listOf("${this.value}\n")
            is Node  -> {
                val lText = left.toText_().map { "  $it"}
                val rText = right.toText_().map { "  $it"}
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

fun max(x : Int, y : Int) : Int {
    if (x > y) return x
    else return y
}

//finds way in tree from root to leafs with maximum sum of nodes
fun maxWay(f : (Int, Int) -> Int, acc : Int, tree : Tree) : Int {
    when (tree) {
        is Empty -> return acc
        is Leaf  -> return f(acc, tree.value)
        is Node  -> {
            val temp = f(acc, tree.value)
            return max(maxWay(f, temp, tree.left), maxWay(f, temp, tree.right))
    }
        else -> throw Exception("Error")
    }
}

//The 3rd task
fun fold(f : (Int, Int) -> Int, acc : Int, tree : Tree) : Int {
    when (tree) {
        is Empty -> return acc
        is Leaf  -> return f(acc, tree.value)
        is Node  -> {
            val temp = f(acc, tree.value)
            return fold(f, fold(f, temp, tree.left), tree.right)
        }
        else -> throw Exception("Error")
    }
}

//The 4th task
open class Peano {}

class Zero : Peano() {}
class S(val value : Peano) : Peano() {}

//generates a peano number
fun peanoGen(n : Int, res : Peano) : Peano {
    when (n) {
        0    -> return res
        else -> return peanoGen(n - 1, S(res))
    }
}

//prints a peano number
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

//converts a peano number to integer
fun peanoToInt(res : Int, p : Peano) : Int {
    when (p) {
        is Zero -> return res
        is S    -> return peanoToInt(res + 1, p.value)
        else    -> throw Exception("Error")
    }
}

//sum for peano numbers
fun peanoSum(a : Peano, b : Peano) : Peano {
    when (a) {
        is Zero -> return b
        is S    -> {
            when (b) {
                is Zero -> return a
                is S    -> return peanoSum(a.value, S(b))
                else    -> throw Exception ("Error")
            }
        }
        else -> throw Exception ("Error")
    }
}

//subtraction for peano numbers
fun peanoSub(a : Peano, b : Peano) : Peano {
    when (a) {
        is Zero -> return b
        is S    -> {
            when (b) {
                is Zero -> return a
                is S    -> return peanoSub(a.value, b.value)
                else    -> throw Exception ("Error")
            }
        }
        else -> throw Exception ("Error")
    }
}

//multiplication for peano numbers
fun peanoMult(a : Peano, b : Peano) : Peano {
    when (a) {
        is Zero -> return a
        is S    -> return peanoSum(b, peanoMult(a.value, b))
        else    -> throw Exception ("Error")
    }
}

//exponentiation for peano number
fun peanoExp(x : Peano, y : Peano) : Peano {
    when (y) {
        is Zero -> return S(y)
        is S    -> return peanoMult(x, peanoExp(x, y.value))
        else    -> throw Exception ("Error")
    }
}

fun main (args: Array<String>)
{
    //prints array
    fun Array<out Any>.printArray() {
        this.forEach {
            print("$it ")
        }
        println()
    }

    //test for heapSort
    val array = arrayOf(8, 3, 6, 5, 2, 10, 7, 9, 1, 4)
    print ("Before sorting : ")
    array.printArray()

    heapSort(array)
    print ("After sorting  : ")
    array.printArray()


    val tree = Node(2, Node(3, Leaf(4), Empty()), Node(3, Leaf(100), Leaf(7)))
    println("\nTree: ");

    //test for maxWay function
    println (tree.toText())
    println ("Maximum way  = ${maxWay({x, y -> x + y}, 0, tree)}")

    //test for fold: sum of all elements in tree
    println ("Sum of nodes = ${fold({x, y -> x + y}, 0, tree)}")

    //test for arithmetics with peano numbers
    val a = peanoGen(3, Zero())
    val b = peanoGen(2, Zero())

    print("\na = ${peanoToInt(0, a)} = ")
    a.print()
    print("\nb = ${peanoToInt(0, b)} = ")
    b.print()

    val sum = peanoSum(a, b)
    print("\n\na + b = ${peanoToInt(0, sum)} = ")
    sum.print()

    val sub = peanoSub(a, b)
    print("\na - b = ${peanoToInt(0, sub)} = ")
    sub.print()

    val mult = peanoMult(a, b)
    print("\na * b = ${peanoToInt(0, mult)} = ")
    mult.print()

    val exp = peanoExp(a, b)
    print("\na ^ b = ${peanoToInt(0, exp)} = ")
    exp.print()
    println()
}