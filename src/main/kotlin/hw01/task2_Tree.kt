package hw01

/**
 * Created by z on 19.09.2015.
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

// task 2

fun  Tree.maxPath(): Int {
    var path = 0
    when(this) {
        is Empty -> return path
        is Leaf -> return this.value + path
        is Node -> {
            path = path + this.value
            return Math.max(l.maxPath(), r.maxPath()) + path
        }
        else -> throw Exception("Error")
    }
}

/*
fun main(args: Array<String>) {



    var tree = Node(5, Node(8,Leaf(4),Leaf(3)),Leaf(7))

    println(tree.toText())

    println(fold({x,y -> x + y}, 0, tree))

    println(maxPath({x,y -> x + y}, 0, tree))

}*/
