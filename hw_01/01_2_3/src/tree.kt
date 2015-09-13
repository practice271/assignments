abstract class Tree {}
open class Empty() : Tree() {}
open class Leaf(val value : Int) : Tree() {}
open class Node(val value : Int, val left : Tree, val right : Tree) : Tree() {}


fun findMaxFold(f: (Int,Int)->Int, acc:Int, tree:Tree ) : Int {
    when (tree) {
        is Empty -> return acc
        is Leaf -> return f (acc, tree.value)
        is Node -> {
            val tmp = f(acc, tree.value)
            return Math.max(findMaxFold(f, tmp, tree.left), findMaxFold(f, tmp, tree.right))
        }
        else -> throw Exception("Unknown class")
    }
}

fun fold(f:(Int, Int)->Int, acc:Int, tree:Tree ) : Int {
    when (tree) {
        is Empty -> return acc
        is Leaf -> return f (acc , tree.value)
        is Node -> {
            val tmp = f(acc , tree.value)
            return fold(f,fold(f, tmp, tree.left), tree.right)
        }
        else -> throw Exception("Unknown class")
    }
}


fun main(argv:Array<String>){
    val tree = Node(7, Node(10, Leaf(3),  Leaf(2)), Node(2, Leaf(5), Empty()))
    /* Tree:
              7
           10  2
          3  2  5 _
     */
    println ("Maximum sum  is "  + findMaxFold( {x , y -> x + y}, 0, tree)) // Output: 20

    println ("Maximun value in tree is " + fold( {x , acc -> if (x>acc) x else acc }, Int.MIN_VALUE, tree) ) //output: 10
}