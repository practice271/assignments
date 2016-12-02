package hw07

import java.util.*

interface OrderedKt<T> : Iterable<T> {

    fun equals(x: OrderedKt<T>): Boolean
    operator fun compareTo(x: OrderedKt<T>): Int
    override fun hashCode(): Int

}

class OrderedListKt<T : Comparable<T>> : AbstractCollection<T>(),
                                         OrderedKt<T> {

    private inner class ListIterator : Iterator<T> {

        var current = 0

        override fun hasNext(): Boolean {
            return current >= size
        }

        override fun next(): T {
            if (!hasNext())
                throw NoSuchElementException()

            return array[current++]
        }
    }

    private var array = arrayOfNulls<Comparable<Any>>(8) as Array<T>
    private var sz = 0
    private var cap = 8

    override fun compareTo(other: OrderedKt<T>): Int {
        val itA = iterator()
        val itB = other.iterator()
        while (itA.hasNext() && itB.hasNext()) {
            val a = itA.next()
            val b = itB.next()
            val compareResult = a.compareTo(b)
            if (compareResult != 0)
                return compareResult
        }

        if (!itA.hasNext() && !itB.hasNext()) {
            return 0
        } else if (!itA.hasNext()) {
            return -1
        } else {
            return 1
        }
    }

    override fun equals(x: OrderedKt<T>): Boolean {
        return (compareTo(x) == 0)
    }

    override fun hashCode(): Int {
        var h = 1777
        val it = iterator()
        while (it.hasNext()) {
            h = h * 31 + it.next().hashCode()
        }
        return h
    }

    override var size: Int = 0
        get() = sz

    override fun iterator(): MutableIterator<T> =
            ListIterator() as MutableIterator<T>

    override fun add(t: T): Boolean {
        if (size == cap) {
            cap *= 2
            val newa = arrayOfNulls<Comparable<Any>>(cap) as Array<T>
            for (i in 0..size - 1)
                newa[i] = array[i]
            array = newa
        }
        var place = 0
        while (place < size && array[place].compareTo(t) < 0)
            place++
        for (i in size - 1 downTo place)
            array[i + 1] = array[i]
        array[place] = t
        size++
        return true
    }

}

fun main(args: Array<String>) {

}
