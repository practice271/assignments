package hw01
/** ex. 1 HeapSort*/
/** Expected time : 1h
 *  Real time : 4h
 */

fun Array<Int>.heapsort() {
    fun Array<Int>.ShiftDown(i: Int, j:Int){

        var flag:Boolean = false
        var maxChild: Int
        var temp: Int
        var a = i
        var b = j

        while ((a * 2 + 1 < b) && (!flag)) {
            if (a * 2 + 1 == b - 1) {
                maxChild = a * 2 + 1
            }
            else if (this[a * 2 + 1] > this[a * 2 + 2]) {
                maxChild = a * 2 + 1
            }
            else {
                maxChild = a * 2 + 2
            }

            if (this[a] < this[maxChild]) {
                temp = this[a]
                this[a] = this[maxChild]
                this[maxChild] = temp
                a = maxChild
            }
            else {
                flag = true
            }
        }
    }
    var idx = this.size() / 2 - 1
    var tmp : Int
    while (idx >= 0){
        ShiftDown(idx,this.size())
        idx--
    }
    idx = this.size() - 1
    while (idx >= 1) {
        tmp = this[0]
        this[0] = this[idx]
        this[idx] = tmp
        ShiftDown (0,idx)
        idx--
    }
}

/** ex.2 Max way in tree*/
/** Expected time : 2h
 *  Real time : 2h
 */

abstract class Tree {}
open class Empty() : Tree() {}
open class Leaf(val value : Int) : Tree() {}
open class Node(val value : Int, val l : Tree, val r : Tree) : Tree() {}

fun maxWay (max: (Int, Int) -> Int, tree: Tree, res: Int) : Int {
    when (tree){
        is Empty -> return res
        is Leaf  -> return (res + tree.value)
        is Node  ->
                    return max(maxWay(max, tree.l, res + tree.value), maxWay(max, tree.r, res + tree.value))
        else     -> throw Exception("Incorrect class")
    }
}
fun maxWay_ (tree:Tree): Int{
    return maxWay({a,b -> Math.max(a,b)},tree, 0)
}

/** ex.3 Fold for tree
 * from root to leaf */

fun foldT (f1: (Int,Int) -> Int,f2: (Int, Int) -> Int, acc: Int, tree: Tree): Int{
    when(tree){
        is Empty -> return acc
        is Leaf -> return f1(acc, tree.value)
        is Node -> return f2(foldT(f1,f2,f1(acc, tree.value), tree.l), foldT(f1,f2,f1(acc, tree.value), tree.r))
        else -> throw Exception("Unknown class")
    }
}

/** ex.4 Peano*/
/** Expected time : 3h
 *  Real time : 1h
 */
abstract class Peano {}
open class Zero() : Peano() {}
open class S(val p: Peano) : Peano()

fun peanoSum (a: Peano, b: Peano): Peano {
    when (a){
        is Zero -> return b
        is S    -> return S(peanoSum(a.p, b))
        else    -> throw Exception("Incorrect class")
    }

}

fun peanoPrint (a: Peano): Int{
    when (a){
        is Zero -> return 0
        is S    -> return 1 + peanoPrint(a.p)
        else    -> throw Exception("Incorrect class")
    }
}

fun peanoSub (a: Peano, b:Peano) : Peano{
    when (a){
        is Zero -> return Zero()
        is S    -> {
            when (b){
                is Zero -> return a
                is S    -> return (peanoSub(a.p,b.p))
                else    -> throw Exception("Incorrect class")
            }
        }
        else    ->  throw Exception("Incorrect class")
    }
}
fun peanoMul (a:Peano, b:Peano) : Peano{
    when (a){
        is Zero -> return Zero()
        is S    -> {
            when (b){
                is Zero -> return Zero()
                is S    -> return (peanoSum(peanoMul(a,b.p),a))
                else    -> throw Exception("Incorrect class")
            }
        }
        else    -> throw Exception("Incorrect class")
    }
}

fun peanoDeg (a:Peano, b:Peano): Peano{
    when (a){
        is Zero -> return Zero()
        is S    ->{
            when (b){
                is Zero -> return S(Zero())
                is S    -> return (peanoMul(peanoDeg(a,b.p),a))
                else    -> throw Exception("Incorrect class")
            }
        }
        else    -> throw Exception("Incorrect class")
    }
}

fun main(args: Array<String>) {
   /**
    * println("ex.1 Heapsort")
    val a = arrayOf(1,2,3,0)
    val a2 = arrayOf(2,2,2,2,-1,5,0)
    fun Array<out Any>.printArray() {
        this.forEach {
            print("$it ")
        }
        println()
    }
    println("Original array")
    a.printArray()
    a.heapsort()
    println("Sorted array")
    a.printArray()
    println("Original array")
    a2.printArray()
    a2.heapsort()
    println("Sorted array")
    a2.printArray()

    println("ex.2 Max way in tree")
    val tree = Node(3, Node(5,Leaf(1), Leaf(4)), Leaf(8))
    val res = maxWay_(tree)
    println ("Node(3, Node(5,Leaf(1), Leaf(4)), Leaf(8))")
    println(" max = ${res} ")

    println("ex.4 Peano")
    val b : Peano = S(S(Zero()))
    val c : Peano = S(S(S(Zero())))

    println(" 3 + 2 = ${peanoPrint((peanoSum(c,b)))} ")
    println(" 3 - 2 = ${peanoPrint((peanoSub(c,b)))} ")
    println(" 0 - 1 = ${peanoPrint((peanoSub(Zero(),S(Zero()))))} ")
    println(" 3 * 2 = ${peanoPrint((peanoMul(c,b)))} ")
    println(" 3 ^ 2 = ${peanoPrint((peanoDeg(c,b)))} ")
    println(" 2 ^ 0 = ${peanoPrint((peanoDeg(S(S(Zero())),Zero())))} ")
    println(" 0 ^ 2 = ${peanoPrint((peanoDeg(Zero(),S(S(Zero())))))} ")

    println("Ex. 3")
    val tree1 = Node(3, Node(5,Leaf(1), Leaf(4)), Leaf(8))
    val res1 = foldT({a,b -> a + b},{a,b -> Math.max(a,b)}, 0, tree1)
    println(" max = ${res1} ")
    val res2 = foldT({a,b -> a * b},{a,b -> a * b}, 1, tree1)
    println(" multiply = ${res2} ")*/

}