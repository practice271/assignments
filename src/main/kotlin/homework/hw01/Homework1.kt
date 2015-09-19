/*
Homework 1 (08.09.2015)
Tasks 1 - 4

Author: Mikhail Kita, group 271
*/

package homework.hw01

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

//finds way in tree from root to leafs with maximum sum of nodes
fun maxWay(f : (Int, Int) -> Int, acc : Int, tree : Tree) : Int {
    when (tree) {
        is Empty -> return acc
        is Leaf  -> return f(acc, tree.value)
        is Node  -> {
            val temp = f(acc, tree.value)
            return Math.max(maxWay(f, temp, tree.left), maxWay(f, temp, tree.right))
        }
        else -> throw Exception("Error")
    }
}

//The 3rd task
fun fold(f : (Int, Int) -> Int, g : (Int, Int) -> Int, acc : Int, tree : Tree) : Int {
    when (tree) {
        is Empty -> return acc
        is Leaf  -> return f(acc, tree.value)
        is Node  -> {
            val temp = f(acc, tree.value)
            return f(temp, g(fold(f, g, acc, tree.left), fold(f, g, acc, tree.right)))
        }
        else -> throw Exception("Error")
    }
}

//The 4th task
open class Peano {}

class Zero : Peano() {}
class S(val value : Peano) : Peano() {}

//generates a peano number
fun peanoGen(n : Int) : Peano {
    when (n) {
        0    -> return Zero()
        else -> return S(peanoGen(n - 1))
    }
}

//converts a peano number to integer
fun peanoToInt(p : Peano) : Int {
    when (p) {
        is Zero -> return 0
        is S    -> return 1 + peanoToInt(p.value)
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

fun main (args: Array<String>) {
}