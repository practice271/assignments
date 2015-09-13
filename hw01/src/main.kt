// task 1
fun Array<Int>.stoke(first : Int, last : Int) {
    val middle : Int
    if (first == 0) { middle = (last - first + 1) div 2}
    else { middle = this.size() div 2 }
    var i = first
    val max : Int
    while (i != middle) {
        if ((2 * i + 2) <= last) {
            if (this[2 * i + 1] < this[2 * i + 2]) {
                max = 2 * i + 2
            } else {
                max = 2 * i + 1
            }
        } else {
            max = 2 * i + 1
        }
        if (this[i] < this[max]) {
            val ch = this[i]
            this[i] = this[max]
            this[max] = ch
        }
        i++
    }
}

fun Array<Int>.heapsort() {
    val middle = this.size() div 2
    var beg = middle - 1
    while (beg != -1) {
        this.stoke(beg, this.size() - 1)
        beg--
    }
    var s = this.size() - 1
    while (s != 0) {
        val ch = this[s]
        this[s] = this[0]
        this[0] = ch
        this.stoke(0, s - 1)
        s--
    }
}

fun Array<out Any>.printArray() {
    this.forEach {
        print("$it ")
    }
    println()
}

//tasks 2-3
abstract class Tree {}
open class Empty() : Tree() {}
open class Leaf(val value : Int) : Tree() {}
open class Node(val value : Int, val l : Tree, val r : Tree) : Tree() {}

fun Tree.maxSum() : Int {
    var sum = 0
    when(this) {
        is Empty -> return sum
        is Leaf -> return sum + this.value
        is Node -> {
            val max: Int
            if (l.maxSum() > r.maxSum()) {max = l.maxSum()}
            else {max = r.maxSum()}
            return sum + this.value + max
        }
        else -> throw Exception("Unknown class")
    }
}

fun <B> Tree.fold(fLeaf : (Int, B) -> B, fNode : (Int, B, B) -> B, acc : B) : B {
    when (this) {
        is Empty -> return acc
        is Leaf  -> return fLeaf(value, acc)
        is Node  -> return fNode(value,
                l.fold(fLeaf, fNode, acc),
                r.fold(fLeaf, fNode, acc))
        else -> throw Exception("Unknown class")
    }
}

//task4
abstract class Peano {}
open class Zero() : Peano() {}
open class S(val value : Peano) : Peano() {}

fun plus(a : Peano, b : Peano) : Peano {
    when (a) {
        is Zero -> return b
        is S -> return S (plus (a.value, b))
        else -> throw Exception("Unknown class")
    }
}

fun minus(a : Peano, b : Peano) : Peano {
    when (a) {
        is Zero -> return Zero()
    }
    when (b) {
        is Zero -> return a
        is S -> when (a) {
            is S -> return minus (a.value, b.value)
            else -> throw Exception("Unknown class")
        }
        else -> throw Exception("Unknown class")
    }
}

fun multi(a : Peano, b : Peano) : Peano {
    when (a) {
        is Zero -> return Zero()
    }
    when (b) {
        is Zero -> return Zero()
        is S -> return plus (multi (a, b.value), a)
        else -> throw Exception("Unknown class")
    }
}

fun deg(a : Peano, b : Peano) : Peano {
    when (a) {
        is Zero -> return Zero()
    }
    when (b) {
        is Zero -> return S (Zero())
        is S -> return multi (deg (a, b.value), a)
        else -> throw Exception("Unknown class")
    }
}

fun Peano.toInt() : Int {
    when (this) {
        is Zero -> return 0
        is S -> return 1 + this.value.toInt()
        else -> throw Exception("Unknown class")
    }
}

fun main(args: Array<String>) {
    println ("heapsort")
    print ("10,9,8,7,6,5,4,3,2,1,0    -      ")
    val a = arrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
    a.heapsort()
    a.printArray()
    print ("1,-1,1,-1,1,-1            -      ")
    val b = arrayOf(1, -1, 1, -1, 1, -1)
    b.heapsort()
    b.printArray()
    print ("2,5,2,4,8,12,3,4,1,0,0,9  -      ")
    val c = arrayOf(2, 5, 2, 4, 8, 12, 3, 4, 1, 0, 0, 9, 4, 2)
    c.heapsort()
    c.printArray()
    println()

    println("Trees")
    val tree0 = Empty()
    val tree1 = Leaf(1)
    val tree2 = Node(0, Node(0, Leaf(0), Leaf(0)), Node(0, Leaf(99999), Leaf(0)))
    val tree3 = Node(1, Node(1, Node(1, Leaf(1), Leaf(1)), Node(1, Leaf(1), Leaf(1))), Node(1, Empty(), Leaf(1)))
    print (tree0.maxSum())
    println (" - макс. сумма в пустом дереве ")
    print (tree1.maxSum())
    println (" - макс. сумма в дереве только из единицы")
    print (tree2.maxSum())
    println (" - макс. сумма в дереве 0, 0 ... 99999 ... 0")
    print (tree0.fold({ a, b -> a + b }, { a, b, c -> a + b + c }, 0))
    println (" - fold на пустом дереве")
    print (tree1.fold({ a, b -> a + b }, { a, b, c -> a + b + c }, 0))
    println (" - fold на дереве только из единицы")
    print (tree2.fold({ a, b -> a + b }, { a, b, c -> a + b + c }, 0))
    println (" - fold на дереве 0, 0 ... 99999 ... 0")
    print (tree3.fold({ a, b -> a + b }, { a, b, c -> a + b + c }, 0))
    println (" - fold на дереве из 10 единиц")
    println()

    println("Peano")
    println ("1 and 0:")
    print(plus (S(Zero()), Zero()).toInt())
    println (" - сложение")
    print(minus (S(Zero()), Zero()).toInt())
    println (" - вычитание")
    print(multi (S(Zero()), Zero()).toInt())
    println (" - умножение")
    print(deg (S(Zero()), Zero()).toInt())
    println (" - степень")
    println ("2 and 3:")
    print(plus (S(S(Zero())), S(S(S(Zero())))).toInt())
    println (" - сложение")
    print(minus (S(S(Zero())), S(S(S(Zero())))).toInt())
    println (" - вычитание")
    print(multi (S(S(Zero())), S(S(S(Zero())))).toInt())
    println (" - умножение")
    print(deg (S(S(Zero())), S(S(S(Zero())))).toInt())
    println (" - степень")
}