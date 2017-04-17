package homework.hw07.kotlinimp

import homework.hw07.AbstractOrderedList

/**
 * Array implementation of AbstractOrderedList

 * @author Kirill Smirenko, group 271
 */
class ArrayOrderedList<T : Comparable<T>>
constructor() : AbstractOrderedList<T>() {
    private val CLUSTER_SIZE = 10

    private var items : Array<T> = arrayOfNulls<Comparable<Any>>(CLUSTER_SIZE) as Array<T>
    private var size : Int = 0

    override fun add(value : T) {
        // expanding the array when needed
        if (size + 1 >= items.size) {
            val newItems = arrayOfNulls<Comparable<Any>>(items.size + CLUSTER_SIZE) as Array<T>
            System.arraycopy(items, 0, newItems, 0, size)
            items = newItems
        }
        // finding a spot to put the new value
        var index = 0
        while ((index < size) && (items[index].compareTo(value) <= 0)) {
            index++
        }
        // inserting new value
        if ((size > 0) && (index < size)) {
            System.arraycopy(items, index, items, index + 1, size - index)
        }
        items[index] = value
        size++
    }

    override fun get(index : Int) : T = items[index]

    override fun removeAt(index : Int) {
        if ((index <= 0) || (index >= size)) {
            return
        }
        System.arraycopy(items, index + 1, items, index, size - index - 1)
        size--
    }

    override fun size() : Int {
        return size
    }

    override fun hashCode() : Int {
        var h = 0
        for (i in 0..size - 1) {
            h = 23 * h + items[i].hashCode()
        }
        return h
    }

    override fun iterator() : MutableIterator<T> = LinkedOrderedListIterator<T>(this)

    private class LinkedOrderedListIterator<T : Comparable<T>>(private val list : ArrayOrderedList<T>) : MutableIterator<T> {
        private var index = 0

        override fun hasNext() = index < list.size

        override fun next() : T = list.items[index++]

        override fun remove() {
        }
    }
}