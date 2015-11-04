package hw07.kotlinClasses

import java.util.*

/* OrderedArray made by Guzel Garifullina
   Estimated time  1 hour
   real time       1 hour
*/

data class OrderedArray<E : Comparable<E>>
                (private val size_: Int,private val defaultElem: E): Iterable<E> {
    private  class Elem<E : Comparable<E>> (private val elem : E){
        fun get () : E = elem;
    }
    private var size_basic = Math.max(100, size_)
    private var size = size_
    private var arr = Array<Elem<E>>(size_basic, { Elem(defaultElem) })

    private inner class AIterator : Iterator<E> {
        private var index = 0
        override fun hasNext(): Boolean {
            return (index < size())
        }
        override fun next(): E {
            if (hasNext()) {
                return arr[index++].get()
            } else {
                throw NoSuchElementException()
            }
        }
    }
    override operator fun iterator(): Iterator<E> {
        return AIterator()
    }
    //because my array consists of classes
    fun equals(other: OrderedArray<E>): Boolean {
        val size = size()
        if (size != other.size()) {
            return false
        }
        if (size == 0) {
            return true
        }
        val listIt = iterator()
        val otherIt = other.iterator()
        while (listIt.hasNext()) {
            if (listIt.next() != otherIt.next()) {
                return false
            }
        }
        return true
    }
    operator fun compareTo(other:  OrderedArray<out E>): Int {
        val size = size()
        if (size > other.size()) {
            return 1
        }
        if (size < other.size()) {
            return -1
        }
        val arrIt = iterator()
        val otherIt = other.iterator()
        while (arrIt.hasNext()) {
            val comp = arrIt.next().compareTo(otherIt.next())
            if (comp != 0) {
                return comp
            }
        }
        return 0
    }
    public fun isEmpty(): Boolean{
        return size == 0
    }
    public fun size(): Int {
        return size
    }
    private fun resize() {
        size_basic *= 2
        arr = Array<Elem<E>>(size_basic, {i -> arr[i]})
    }
    public fun add(elem: E) {
        if (size == size_basic) {
            resize()
        }
        for (i in size - 1 downTo 0) {
            if (arr[i].get().compareTo(elem) > 0) {
                arr[i + 1] = arr[i]
            }
            else {
                arr[i + 1] = Elem(elem)
                size++
                return
            }
        }
        arr[0] = Elem(elem)
        size++
        return
    }
    public fun get(index: Int): E {
        if ((index < size) && (0 <= index)) {
            return arr[index].get()
        }
        throw ArrayIndexOutOfBoundsException()
    }
    public fun removeAt(index: Int) {
        if ((index >= size) && (0 > index)) {
            throw ArrayIndexOutOfBoundsException()
        }
        for (i in index..size - 2) {
            arr[i] = arr[i + 1]
        }
        size--
    }
    public fun clear() {
        size = 0
    }
}
