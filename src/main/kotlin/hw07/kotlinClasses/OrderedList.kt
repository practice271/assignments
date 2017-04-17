package hw07.kotlinClasses

import java.util.*

/* OrderedList made by Guzel Garifullina
   Estimated time  1 hour
   real time       1 hour
*/

data class OrderedList<E : Comparable<E>>
    public constructor(private var first: E) : Iterable<E>  {
    private var size = 1
    private var tail: OrderedList<E>? = null
    init {
        size = 1
        tail = null
    }
    private constructor(first: E, tail: OrderedList<E>?) : this(first) {
        this.tail = tail
        if (tail == null) {
            size = 1
        }
        else {
            size = tail.size() + 1
        }
    }
    private inner class LIterator : Iterator<E> {
        private var list: OrderedList<E>? = null
        init {
            if (size() == 0) {list = null}
            else{list = OrderedList(first, tail)}
        }
        override fun hasNext(): Boolean {
            return (list != null)
        }
        override fun next(): E {
            if (hasNext()) {
                // because we already checked for null
                val f = list!!.first
                list = list?.tail
                return f
            }
            else {
                throw NoSuchElementException()
            }
        }
    }
    override operator fun iterator(): Iterator<E> {
        return LIterator()
    }
    operator fun compareTo(other: OrderedList<out E>): Int{
        val size = size()
        if (size > other.size()) {return 1}
        if (size < other.size()) {return -1}

        val listIt = iterator()
        val otherIt = other.iterator()
        while (listIt.hasNext()) {
            val comp = listIt.next().compareTo(otherIt.next() as E)
            if (comp != 0) {return comp}
        }
        return 0
    }
    public fun isEmpty(): Boolean{
        return size == 0
    }
    public fun size(): Int {
        return size
    }
    private fun addLoc(l: OrderedList<E>?, elem: E): OrderedList<E> {
        if (l == null) {
            return OrderedList(elem, null)
        }
        val comp = l.first.compareTo(elem)
        if (comp >= 0) {
            return OrderedList(elem, l)
        }
        return OrderedList<E>(l.first, addLoc(l.tail, elem))
    }
    public fun add(elem: E) {
        if (size == 0) {
            first = elem
            size++
            return
        }
        if (first.compareTo(elem) >= 0) {
            tail = OrderedList(first, tail)
            first = elem
            size++
            return
        }
        tail = addLoc(tail, elem)
        size++
    }
    private fun getLoc(l: OrderedList<E>?, i: Int, index: Int): E {
        if (l == null){ throw VeryBadException()}
        if (i < index) {return getLoc(l.tail, i + 1, index)}
        return l.first
    }
    public fun get(index: Int): E {
        if ((index >= size) || (index < 0)) {
            throw OutOfBoundException()
        }
        if (index == 0) {return first}
        return getLoc(tail, 1, index)
    }
    private fun remLoc(l: OrderedList<E>?, i: Int, index: Int): OrderedList<E>? {
        if (l == null){ throw VeryBadException()}
        if (i < index) {return OrderedList(l.first, remLoc(l.tail, i + 1, index))}
        if (i == size() - 1) {//last elem
            return null
        }
        return l.tail
    }
    public fun removeAt(index: Int) {
        if ((index >= size) || (index < 0)) {
            throw OutOfBoundException()
        }
        if (index == 0) {
            first = tail?.first ?: throw VeryBadException()
            tail =  tail?.tail
            size--
            return
        }
        tail = remLoc(tail, 1, index)
        size--
    }
    public fun clear() {
        size = 0
        tail = null
    }
}