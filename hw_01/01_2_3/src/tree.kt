abstract class Tree {}
open class Empty() : Tree() {}
open class Leaf(val value : Int) : Tree() {}
open class Node(val value : Int, val left : Tree, val right : Tree) : Tree() {}


fun findMaxFold(acc:Int, tree:Tree ) : Int {
    when (tree) {
        is Empty -> return acc
        is Leaf -> return acc+tree.value
        is Node -> {
            return Math.max(findMaxFold(acc+tree.value, tree.left), findMaxFold(acc+tree.value , tree.right))
        }
        else -> throw Exception("Unknown class")
    }
}

fun fold (f1: (Int,Int) -> Int,f2: (Int, Int) -> Int, acc: Int, tree: Tree): Int{
    when(tree){
        is Empty -> return acc
        is Leaf  -> return f1(acc, tree.value)
        is Node  -> return f2(fold(f1,f2,f1(acc, tree.value), tree.left), fold(f1,f2,f1(acc, tree.value), tree.right))
        else     -> throw Exception("Unknown class")
    }
}



fun main(argv:Array<String>){
    val tree = Node(7, Node(10, Leaf(3),  Leaf(2)), Node(2, Leaf(5), Empty()))
    /* Tree:
              7
           10  2
          3  2  5 _
     */
    println ("Maximum sum is "  + findMaxFold(0, tree)) // Output: 20

    println("Maximum sum using fold func is " + fold({a,b -> a + b},{a,b -> Math.max(a,b)}, 0, tree)) // Output: 20

}
