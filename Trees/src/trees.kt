abstract class Tree<T>() {}
class Nil<T>(): Tree<T>()
class Node<T>(val l: Tree<T>, val r: Tree<T>, val v: T): Tree<T>()

fun main(args: Array<String>) {
    val t = Node(
                Node(Node(Nil(), Nil(), 2),
                     Node(Nil(), Nil(), 1),
                     3),
                Node(Nil(), Nil(), 4),
                5)
    println(t.maxPath())
    t.traverse()
}

fun Tree<Int>.maxPath(): Int =
    when (this) {
        is Node -> v + Math.max(l.maxPath(), r.maxPath())
        else -> 0
    }

fun <T>Tree<T>.traverse() {
    if (this is Node) {
        l.traverse()
        println(v)
        r.traverse()
    }
}