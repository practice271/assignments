package hw4

import java.util.*

class HashTable<T>(val cap: Int): Set<T> {
    val t = Array<LinkedList<T>>(cap, { i -> LinkedList() })

    private fun getHash(x: T) = x!!.hashCode() % cap

    override fun insert(x: T) {
        if (x != null) {
            val l : MutableList<T> = t[getHash(x)]
            l.add(x)
        }
    }

    override fun remove(x: T) {
        if (x != null) {
            val l : MutableList<T> = t[getHash(x)]
            l.remove(x)
        }
    }

    override fun find(x: T): Boolean {
        if (x != null) {
            val l : MutableList<T> = t[getHash(x)]
            return l.contains(x)
        }
        return false
    }

}
