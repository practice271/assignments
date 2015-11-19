package classroom.c05

import java.util.*

class Tree<A: Comparable<A>>(): Iterable<A> {
    private var root: Node<A>? = null

    private class Node<A: Comparable<A>>(
            val value: A, val l: Node<A>?, val r: Node<A>?
    ): Iterable<A> {
        override fun iterator(): Iterator<A> = LCRNodeIterator(this)
    }

    private class EmptyIterator<A>(): Iterator<A> {
        override fun hasNext(): Boolean = false
        override fun next(): A { throw NoSuchElementException() }
    }

    private abstract class NodeIterator<A: Comparable<A>>(
            protected val node: Node<A>
    ): Iterator<A> {
        protected val lIterator: Iterator<A> =
                node.l?.iterator() ?: EmptyIterator()
        protected val rIterator: Iterator<A> =
                node.r?.iterator() ?: EmptyIterator()
        protected var  observed: Boolean = false
        protected var lHasNext: Boolean = true
            get() =
                if (field) { field = lIterator.hasNext(); field } else false
        protected var rHasNext: Boolean = true
            get() =
                if (field) { field = rIterator.hasNext(); field } else false

        override fun hasNext(): Boolean {
            if (!observed) { return true }
            if (lHasNext ) { return true }
            if (rHasNext ) { return true }
            return false
        }
        //        !observed || lIterator.hasNext() || rIterator.hasNext()
    }

    private class LCRNodeIterator<A: Comparable<A>>(
            node: Node<A>
    ): NodeIterator<A>(node) {
        override fun next(): A {
            if (lHasNext ) { return lIterator.next() }
            if (!observed) { observed = true; return node.value }
            if (rHasNext ) { return rIterator.next() }
            throw NoSuchElementException()
        }
    }

    private class CLRNodeIterator<A: Comparable<A>>(
            node: Node<A>
    ): NodeIterator<A>(node) {
        override fun next(): A {
            if (!observed) { observed = true; return node.value }
            if (lHasNext ) { return lIterator.next() }
            if (rHasNext ) { return rIterator.next() }
            throw NoSuchElementException()
        }
    }

    private class LRCNodeIterator<A: Comparable<A>>(
            node: Node<A>
    ): NodeIterator<A>(node) {
        override fun next(): A {
            if (lHasNext ) { return lIterator.next() }
            if (rHasNext ) { return rIterator.next() }
            if (!observed) { observed = true; return node.value }
            throw NoSuchElementException()
        }
    }

    private fun Node<A>?.size(): Int {
        this ?: return 0
        return 1 + l.size() + r.size()
    }

    private fun Node<A>?.contains(elem: A): Boolean {
        this ?: return false
        return value.compareTo(elem).let {
            when {
                it < 0 -> r.contains(elem)
                it > 0 -> l.contains(elem)
                else -> true
            }
        }
    }
    private fun Node<A>?.insert(elem: A): Node<A> {
        this ?: return Node(elem, null, null)
        return value.compareTo(elem).let {
            when {
                it < 0 -> Node(value, l, r.insert(elem))
                it > 0 -> Node(value, l.insert(elem), r)
                else -> this ?: Node(value, l, r)
            }
        }
    }

    private fun Node<A>?.getMin(): A? = this?.let { l.getMin() ?: value }
            //if (this != null) (l.getMin() ?: value) else null
    private fun Node<A>?.getMax(): A? = this?.let { r.getMax() ?: value }
            //if (this != null) (r.getMax() ?: value) else null

    private fun Node<A>?.remove(elem: A): Node<A>? {
        this ?: return null
        return value.compareTo(elem).let {
            when {
                it < 0 -> Node(value, l, r.remove(elem))
                it > 0 -> Node(value, l.remove(elem), r)
                else -> {
                    if (l == null) {
                        r.getMin()?.let { Node(it, null, r.remove(it)) }
                                ?: null
                    } else {
                        l.getMax()?.let { Node(it, l.remove(it), r) }
                                ?: null
                    }
                }
            }
        }
    }

    public fun insert(elem: A) { root = root.insert(elem) }
    public fun remove(elem: A) { root = root.remove(elem) }
    public fun contains(elem: A): Boolean = root.contains(elem)
    public fun size(): Int = root.size()

    override fun iterator(): Iterator<A> =
            root?.iterator() ?: EmptyIterator()

    override fun toString(): String =
        StringBuilder().let { sb ->
            this forEach { sb.append("$it ") }
            sb.append("\n")
        }.toString()

    public fun toList(): List<A> {
        val list: MutableList<A> = ArrayList<A>()
        for (e in this) {
            list.add(e)
        }
        return list
    }
}

public fun main(args: Array<String>) {
    val tree = Tree<Int>()
    val range = 1..5
    for (i in range) {
        tree.insert(i)
    }

    tree.remove(3)
    print(tree)

    for (e in tree.toList()) {
        //
    }

    println("3: ${tree.contains(3)}")
    println("5: ${tree.contains(5)}")
}
