package homeworks

fun main(args: Array<String>) {
    println("1 task")
    val heap = arrayOf(123, 5, 44, 3, 32, 65, 76, 67, 86, 34)
    for (element in heap)
    {
        print("$element ")
    }
    heapSort(heap)
    println()
    for (element in heap)
    {
        print("$element ")
    }
    println()

    println("2 task")
    val tree = Node(15,
            Node(27,
                    Node(4,
                            Leaf(5),
                            Empty()),
                    Leaf(10)),
            Node(34,
                    Leaf(12),
                    Node(1,
                            Leaf(8),
                            Leaf(6))))
    //15 + 34 + 12
    println(tree.searchMaximumSum())

    println("4 task")
    println("result ${toInt(addition((S(S(S(S(S(Zero())))))), S(S(S(Zero())))))}")
    println("result ${toInt(subtraction((S(S(S(S(S(Zero())))))), S(S(S(Zero())))))}")
    println("result ${toInt(multiplication((S(S(S(S(S(Zero())))))), S(S(S(Zero())))))}")
    println("result ${toInt(pow((S(S(S(S(S(Zero())))))), S(S(S(Zero())))))}")
}

//1 task
fun heapSort(array: Array<Int>) {
    fun shiftDown(array: Array<Int>, i: Int, j: Int) {
        var done = false
        var maxChild: Int
        var k = i

        while ((k * 2 + 1 < j) && (!done))
        {
            if (k * 2 + 1 == j - 1)
                maxChild = k * 2 + 1
            else if (array[k * 2 + 1] > array[k * 2 + 2])
                maxChild = k * 2 + 1
            else
                maxChild = k * 2 + 2

            if (array[k] < array[maxChild])
            {
                var temp = array[k]
                array[k] = array[maxChild]
                array[maxChild] = temp
                k = maxChild
            }
            else
            {
                done = true
            }
        }
    }

    var i = array.size() / 2 - 1
    while (i >= 0)
    {
        shiftDown(array, i, array.count())
        i--
    }
    i = array.size() - 1
    while (i >= 1)
    {
        var temp = array[0]
        array[0] = array[i]
        array[i] = temp
        shiftDown(array, 0, i)
        i--
    }
}

//2 task
abstract class Tree() {}
class Empty(): Tree() {}
class Leaf(val value: Int): Tree() {}
class Node(val value: Int, val left: Tree, val right: Tree): Tree() {}

fun Tree.searchMaximumSum() : Int {
    var max = 0
    var temp = 0

    fun dfs(tree: Tree) {
        when (tree) {
            is Empty -> return
            is Leaf -> {
                if (max < temp + tree.value) {
                    max = temp + tree.value
                }
            }
            is Node -> {
                temp += tree.value
                dfs(tree.left)
                dfs(tree.right)
                temp -= tree.value
            }
            else -> throw java.lang.Exception("Unknown class")
        }
    }
    dfs(this)
    return max
}

//3 task
//func(B, B) -> B
fun <B> Tree.fold(fEmpty : B, function1: (B, Int) -> B, function2: (B, B) -> B) : B {
    when (this) {
        is Empty -> return fEmpty
        is Leaf  -> return function1(fEmpty, value)
        is Node  -> return function2(left.fold(function1(fEmpty, value), function1, function2),
                                    right.fold(function1(fEmpty, value), function1, function2))
        else     -> throw Exception("Unknown class")
    }
}

//4 task
abstract class Peano() {}
class Zero(): Peano() {}
class S(val value: Peano): Peano() {}

fun addition(a: Peano, b: Peano): Peano {
    when (a) {
        is Zero -> return b
        is S -> return S(addition(a.value, b))
        else -> throw Exception("Unknown class")
    }
}

fun subtraction(a: Peano, b: Peano): Peano {
    when (a) {
        is Zero -> return Zero()
        is S -> {
            when (b) {
                is Zero -> return a
                is S -> return subtraction(a.value, b.value)
                else -> throw Exception("Unknown class")
            }
        }
        else -> throw Exception("Unknown class")
    }
}

fun multiplication(a: Peano, b: Peano): Peano {
    when (a) {
        is Zero -> return Zero()
        is S -> return addition(b, multiplication(a.value, b))
        else -> throw Exception("Unknown class")
    }
}

fun pow(a: Peano, b: Peano): Peano {
    when (a) {
        is Zero -> return Zero()
        is S -> {
            when (b) {
                is Zero -> return S(Zero())
                is S -> return multiplication(S(a.value), pow(S(a.value), b.value))
                else -> throw Exception("Unknown class")
            }
        }
        else -> throw Exception("Unknown class")
    }
}

fun toInt(a: Peano): Int {
    when (a) {
        is Zero -> return 0
        is S -> return toInt(a.value) + 1
        else -> throw Exception("Unknown class")
    }
}