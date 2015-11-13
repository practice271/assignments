package hw8

import java.util.*

class HashTable<T>(val cap: Int): Set<T> {

    override var size: Int = 0; private set

    override fun isEmpty(): Boolean =
        size == 0

    override fun contains(x: T): Boolean =
        x != null && t[getHash(x)].any { n -> x.equals(n.v) }

    override fun iterator(): Iterator<T> =
        HashTableIterator(head)

    inner class HashTableIterator(var node: HashNode?) : Iterator<T> {
        override fun next(): T {
            if (node != null) {
                val r = node!!.v
                node = node!!.next
                return r
            } else {
                throw NoSuchElementException()
            }
        }

        override fun hasNext() = node != null

    }

    override fun containsAll(xs: Collection<T>): Boolean =
        xs.fold(true, { r, x -> r && contains(x) })

    inner class HashNode(val v: T, var prev: HashNode?, var next: HashNode?);

    val t = Array<List<HashNode>>(cap, { i -> LinkedList() })

    var head: HashNode? = null

    /* We checked that x is not null, !! always OK */
    private fun getHash(x: T) = Math.abs(x!!.hashCode() % cap)

    fun insert(x: T) {
        if (x != null) {
            var l : List<HashNode> = t[getHash(x)]
            if (l.all { n -> !x.equals(n.v) }) {
                val newHead = HashNode(x, null, head)
                t[getHash(x)] = l + newHead
                head = newHead
                size++
            }
        }
    }

    fun remove(x: T) {
        if (x != null) {
            val l : List<HashNode> = t[getHash(x)]
            val node = l.find { n -> x.equals(n.v) }
            if (node != null) {
                val next = node.next
                val prev = node.prev
                if (next != null)
                    next.prev = node.prev

                if (prev != null)
                    prev.next = node.next
                else
                    head = node.next
                size--
            }
        }
    }

    fun intersect(s: Set<T>): Set<T> {
        val x = HashTable<T>(cap)
        forEach { v -> if (s.contains(v)) x.insert(v) }
        return x;
    }

    fun union(s: Set<T>): Set<T> {
        val x = HashTable<T>(cap)
        forEach { v -> x.insert(v) }
        s.forEach { v -> x.insert(v) }
        return x
    }

    fun forEach(f: (T) -> Unit) {
        var cur = head
        while (cur != null) {
            f(cur.v);
            cur = cur.next
        }
    }
}