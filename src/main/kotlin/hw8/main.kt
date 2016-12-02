package hw8

import java.io.*
import java.util.*

abstract class Tree<T: Comparable<T>>() {
    var height: Int = 0
    var diff: Int = 0
    var size: Int = 0

    abstract operator fun iterator(): Iterator<T>
}

class Nil<T: Comparable<T>>(): Tree<T>() {
    override fun iterator(): Iterator<T> = NilIterator()

    inner class NilIterator: Iterator<T> {
        override fun next(): T {
            throw NoSuchElementException()
        }

        override fun hasNext(): Boolean {
            return false
        }

    }
}

class Node<T: Comparable<T>>(val v: T, val l: Tree<T>, val r: Tree<T>): Tree<T>() {
    override fun iterator(): Iterator<T> = NodeIterator(this)

    init {
        height = Math.max(l.height, r.height) + 1
        diff = l.height - r.height
    }

    inner class NodeIterator(val n: Node<T>): Iterator<T> {
        val leftIt = n.l.iterator()
        val rightIt = n.r.iterator()
        var yielded = false

        override fun next(): T {
            if (!hasNext())
                throw NoSuchElementException()
           return when {
               leftIt.hasNext() -> leftIt.next()
               !yielded -> { yielded = true; v }
               else -> rightIt.next()
           }
        }

        override fun hasNext(): Boolean {
            return leftIt.hasNext() || !yielded || rightIt.hasNext()
        }

    }
}

fun <T : Comparable<T>> Tree<T>.rotLeft(): Tree<T> =
        if (this is Node && r is Node)
            Node(r.v, Node(v, l, r.l), r.r)
        else
            Nil()

fun <T : Comparable<T>> Tree<T>.rotRight(): Tree<T> =
        if (this is Node && l is Node)
            Node(l.v, l.l, Node(v, l.r, r))
        else
            Nil()

fun <T : Comparable<T>> Tree<T>.rotLeftRight(): Tree<T> =
        if (this is Node)
            Node(v, l.rotLeft(), r).rotRight()
        else
            Nil()

fun <T : Comparable<T>> Tree<T>.rotRightLeft(): Tree<T> =
        if (this is Node)
            Node(v, l, r.rotRight()).rotLeft()
        else
            Nil()

fun <T : Comparable<T>> Tree<T>.insert(x: T): Tree<T> =
        if (this is Node) (
            when {
                x < v -> { size++; Node(v, l.insert(x), r) }
                x > v -> { size++; Node(v, l, r.insert(x)) }
                else -> this
            }).fix()
        else
            Node(x, Nil(), Nil())

fun <T : Comparable<T>> Tree<T>.removeRightmost(): Pair<T, Tree<T>>? =
        if (this is Node)
            if (r is Node) {
                val t = r.removeRightmost()!!
                Pair(t.first, Node(v, l, t.second))
            } else
                Pair(v, l)
        else
            null /* Never reached, !! always OK */

fun <T : Comparable<T>> Tree<T>.remove(x: T): Tree<T> =
        if (this is Node) (
                if (x < v)
                    Node(v, l.remove(x), r)
                else if (x > v)
                    Node(v, l, r.remove(x))
                else {
                    size--
                    when {
                        l is Node && r is Node -> {
                            val t = l.removeRightmost()!!
                            Node(t.first, t.second, r)
                        }
                        l is Node -> r
                        r is Node -> l
                        else -> this
                    }}).fix()
        else
            Nil()

fun <T : Comparable<T>> Tree<T>.fix(): Tree<T> =
        if (this is Node)
            when {
                diff == -2 ->
                    if (r.diff <= 0) this.rotLeft()
                    else this.rotRightLeft()
                diff == 2 ->
                    if (l.diff >= 0) this.rotRight()
                    else this.rotLeftRight()
                else ->
                    this
            }
        else
            Nil()

fun <T : Comparable<T>> Tree<T>.find(x: T): Boolean =
        if (this is Node)
            when {
                x < v -> l.find(x)
                x > v -> r.find(x)
                else -> true
            }
        else
            false

fun <T : Comparable<T>> Tree<T>.print(w: Writer) {
    if (!(this is Node))
        w.write("x")
    else {
        if (r is Node) {
            r.printRec(true, w, "");
        }
        w.write(v.toString() + "\n");
        if (l is Node) {
            l.printRec(false, w, "");
        }
    }
}

fun <T : Comparable<T>> Tree<T>.printRec(isr: Boolean, w: Writer, pre: String) {
    if (!(this is Node))
        return
    if (r is Node)
        r.printRec(true, w, pre + (if (isr) "     " else " |   "))
    w.write(pre + (if (isr) " /" else " \\") + "--- " + v.toString() + "\n")
    if (l is Node)
        l.printRec(false, w, pre + (if (isr) " |   " else "     "))
}

fun <T : Comparable<T>> Tree<T>.text(): String =
        if (this is Node)
            "[" + v.toString() + "," + l.text() + "," + r.text() + "]"
        else
            "[]"

fun <T : Comparable<T>> Tree<T>.forAll(f: (T) -> Unit): Unit =
    if (this is Node) {
        l.forAll(f)
        f(v)
        r.forAll(f)
    } else {

    }

class TreeSet<T: Comparable<T>>(var t: Tree<T>) : Set<T> {
    override val size: Int
        get() = t.size

    override fun isEmpty(): Boolean = t.size == 0

    override fun contains(element: T): Boolean = t.find(element)

    override fun iterator(): Iterator<T> = t.iterator()

    override fun containsAll(elements: Collection<T>): Boolean {
        var c = true
        forEach { v ->  c = c && contains(v) }
        return c
    }

    fun insert(x: T) {
        t = t.insert(x)
    }

    fun remove(x: T) {
        t = t.remove(x)
    }

    fun text() = t.text()

    fun intersect(s: Set<T>): TreeSet<T> {
        val x: TreeSet<T> = TreeSet(Nil())
        forEach { v -> if (s.contains(v)) x.insert(v) }
        return x;
    }

    fun union(s: Set<T>): TreeSet<T> {
        val x: TreeSet<T> = TreeSet(Nil())
        forEach { v -> x.insert(v) }
        s.forEach { v -> x.insert(v) }
        return x
    }

    fun forEach(f: (T) -> Unit) {
        t.forAll(f);
    }

}

fun main(args: Array<String>) {
    var t: Tree<Int> = Nil()
    val w = OutputStreamWriter(System.out)
    val a = arrayOf(5, 4, 6, 2, 1, 3, 7)
    for (x in a) {
        t = t.insert(x)
        t.print(w)
    }
    println("**")
    for (x in t) {
        println(x)
    }
    println("**")
    t = t.remove(4)
    t.print(w)
    t = t.remove(3)
    t.print(w)
    t = t.remove(2)
    t.print(w)
    w.close()
}