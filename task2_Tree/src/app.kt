/**
 * Created by Zarina Kurbatova on 11/09/15.
 */

abstract class Tree {}
open class Empty() : Tree() {}
open class Leaf(val value : Int) : Tree() {}
open class Node(val value : Int, val l : Tree, val r : Tree) : Tree() {}

fun Tree.toText() : String {

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

fun fold(f : (Int, Int) -> Int, acc : Int, tree : Tree) : Int  {
        when (tree) {

                is Empty -> return acc
                is Leaf -> return f(acc, tree.value)
                is Node  -> return fold(f, fold(f, f(acc, tree.value), tree.l), tree.r)
                else -> throw Exception("Unknown class")
            }
    }

fun  maxPath( f : (Int,Int) -> Int, acc : Int, tree : Tree) : Int
{
    var  tmp = 0
    when(tree)

       {
                is Empty -> return acc
                is Leaf -> return  f (acc, tree.value)
                is Node -> {

                    tmp = f(acc, tree.value)
                    return Math.max(maxPath(f, tmp , tree.l),maxPath(f, tmp, tree.r))

                }
                else -> throw Exception("Error")
            }
    }

fun main(args: Array<String>) {



       var tree = Node(5, Node(8,Leaf(4),Leaf(3)),Leaf(7))

        println(tree.toText())

        println(fold({x,y -> x + y}, 0, tree))

        println(maxPath({x,y -> x + y}, 0, tree))

}
