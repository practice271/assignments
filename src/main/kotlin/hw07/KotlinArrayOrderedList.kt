package hw07

import java.util.Arrays
import java.util.NoSuchElementException

data class KotlinArrayOrderedList<A : Comparable<A>>(val arr : Array<A>) : IKotlinOrderedList<A> {

    private var leng = 0

    private var allocated = 100
    private var array = Array<Any?>(100, { null })

    init {
        val initArrLength = arr.size
        allocated = Math.max(allocated, initArrLength)
        array = Arrays.copyOf(arr, allocated)
        leng = initArrLength
    }

    override public fun getLength() = leng

    private fun reallocate() {
        allocated += 100
        array = Arrays.copyOf(array, allocated)
    }

    private class ArrayIterator<A : Comparable<A>>(private var array: Array<Any?>, private val length: Int)
    : MutableIterator<A> {
        private var index = 0
        override public fun hasNext(): Boolean {
            return (index < length)
        }

        override public fun next(): A {
            if (index >= length) throw NoSuchElementException()
            return array[index++] as A
        }

        override fun remove() {
            throw UnsupportedOperationException()
        }
    }

    override public fun iterator(): MutableIterator<A> {
        return ArrayIterator<A>(array, getLength())
    }

    override public fun add(newElem: A) {
        if (allocated == leng) reallocate()
        var index: Int = 0
        while (index < leng && ((array[index] as A).compareTo(newElem) < 0))
            index++
        for (i in leng - 1 downTo index)
            array[i + 1] = array[i]
        array[index] = newElem as Object
        leng++
    }

    override public fun getByIndex(index: Int): A? {
        if (index < 0 || index >= leng)
            return null
        return array[index] as A
    }

    override public fun removeAt(index: Int): Boolean {
        if (index < 0 || index >= leng)
            return false
        for (i in index..leng - 2)
            array[i] = array[i + 1]
        leng--
        return true
    }

    override public fun compareTo(other: IOrderedList<out A>): Int {
        val thisLength: Int = leng
        val otherLength: Int = other.getLength()
        val minLength: Int = Math.min(thisLength, otherLength)
        var thisIterator = iterator()
        var otherIterator = other.iterator()
        for (i in 0..minLength - 1) {
            val compare = thisIterator.next().compareTo(otherIterator.next())
            if (compare != 0) return compare
        }
        if (thisLength > otherLength) return -1
        if (otherLength > thisLength) return 1
        return 0
    }

    override public fun equals(other: Any?): Boolean {
        if (other !is IOrderedList<*>) return false
        val otherList = other as IOrderedList<A>
        if (leng != otherList.getLength()) return false
        var thisIterator = iterator()
        var otherIterator = otherList.iterator()
        while (thisIterator.hasNext())
            if (!thisIterator.next().equals(otherIterator.next())) return false
        return true
    }

    override public fun hashCode(): Int {
        var hash: Int = 0
        var iterator = iterator()
        while (iterator.hasNext())
            hash = hash * 31 + iterator.next().hashCode()
        return hash
    }

    override public fun toArray(): Array<A> {
        return Arrays.copyOf(array, leng) as Array<A>
    }
}