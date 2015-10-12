package hw4

import java.util.*

class HashTable<T>(val cap: Int): Set<T> {
    class HashNode<T>(val v: T, var prev: HashNode<T>?, var next: HashNode<T>?);

    val t = Array<LinkedList<HashNode<T>>>(cap, { i -> LinkedList() })

    var head: HashNode<T>? = null

    /* We checked that x is not null, !! always OK */
    private fun getHash(x: T) = x!!.hashCode() % cap

    override fun insert(x: T) {
        if (x != null) {
            val l : MutableList<HashNode<T>> = t[getHash(x)]
            if (!l.contains(x)) {
                val nhead = HashNode(x, null, head)
                l.add(nhead)
                head = nhead
            }
        }
    }

    override fun remove(x: T) {
        if (x != null) {
            val l : MutableList<HashNode<T>> = t[getHash(x)]
            val node = l.find { (n) -> n.v == x }
            if (node != null) {
                val next = node.next
                val prev = node.prev
                if (next != null)
                    next.prev = node.prev

                if (prev != null)
                    prev.next = node.next
                else
                    head = node.next
            }
        }
    }

    override fun find(x: T): Boolean {
        if (x != null) {
            val l : MutableList<HashNode<T>> = t[getHash(x)]
            return (l.find { (n) -> n.v == x }) != null
        }
        return false
    }

    override fun intersect(s: Set<T>): Set<T> {
        val x = HashTable<T>(cap)
        forAll { (v) -> if (s.find(v)) x.insert(v) }
        return x;
    }

    override fun union(s: Set<T>): Set<T> {
        val x = HashTable<T>(cap)
        forAll { (v) -> x.insert(v) }
        s.forAll { (v) -> x.insert(v) }
        return x
    }

    override fun forAll(f: (T) -> Unit) {
        var cur = head
        while (cur != null) {
            f(cur.v);
            cur = cur.next
        }
    }
}
