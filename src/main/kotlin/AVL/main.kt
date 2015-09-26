package AVL

/* AVL trees - functional-like style */

abstract class Tree<T: Comparable<T>>() {
    var h: Int = 0
    var d: Int = 0
}

class Nil<T: Comparable<T>>(): Tree<T>()

class Node<T: Comparable<T>>(val v: T, val l: Tree<T>, val r: Tree<T>): Tree<T>() {
    init {
        h = Math.max(l.h, r.h) + 1
        d = l.h - r.h
    }
}

fun <T : Comparable<T>>Tree<T>.rotLeft(): Tree<T> =
    if (this is Node && r is Node)
        Node(r.v, Node(v, l, r.l), r.r)
    else
        Nil()

fun <T : Comparable<T>>Tree<T>.rotRight(): Tree<T> =
    if (this is Node && l is Node)
        Node(l.v, l.l, Node(v, l.r, r))
    else
        Nil()

fun <T : Comparable<T>>Tree<T>.rotLeftRight(): Tree<T> =
    if (this is Node)
        Node(v, l.rotLeft(), r).rotRight()
    else
        Nil()

fun <T : Comparable<T>>Tree<T>.rotRightLeft(): Tree<T> =
    if (this is Node)
        Node(v, l, r.rotRight()).rotLeft()
    else
        Nil()

fun <T : Comparable<T>>Tree<T>.insert(x: T): Tree<T> =
    if (this is Node) (
        if (x < v)
            Node(v, l.insert(x), r)
        else
            Node(v, l, r.insert(x))
            ).fix()
    else
        Node(x, Nil(), Nil())

fun <T : Comparable<T>>Tree<T>.removeRightmost(): Pair<T, Tree<T>>? =
    if (this is Node)
        if (r is Node) {
            val t = r.removeRightmost()!!
            Pair(t.first, Node(v, l, t.second))
        } else
            Pair(v, l)
    else
        null /* Never reached, !! always OK */

fun <T : Comparable<T>>Tree<T>.remove(x: T): Tree<T> =
    if (this is Node) (
        if (x < v)
            Node(v, l.remove(x), r)
        else if (x > v)
            Node(v, l, r.remove(x))
        else
            when {
                l is Node && r is Node -> {
                    val t = l.removeRightmost()!!
                    Node(t.first, l, r)
                }
                l is Node -> r
                r is Node -> l
                else -> this
            }).fix()
    else
        Nil()

fun <T : Comparable<T>>Tree<T>.fix(): Tree<T> =
    if (this is Node)
        when {
            d == -2 ->
                if (r.d <= 0) this.rotLeft()
                else this.rotRightLeft()
            d == 2 ->
                if (r.d >= 0) this.rotRight()
                else this.rotLeftRight()
            else ->
                this
        }
    else
        Nil()

fun <T : Comparable<T>>Tree<T>.find(x: T): Boolean =
    if (this is Node)
        when {
            x < v -> l.find(x)
            x > v -> r.find(x)
            else -> true
        }
    else
        false