package homework.hw07.kotlinimp

import homework.hw07.javaimp.AbstractOrderedList

/**
 * Array implementation of AbstractOrderedList

 * @author Kirill Smirenko, group 271
 */
class ArrayOrderedList<T : Comparable<T>>
constructor() : AbstractOrderedList<T>() {
    private val CLUSTER_SIZE = 10

    private var items : Array<T?> = arrayOfNulls<T>(CLUSTER_SIZE)
    private var size : Int = 0

    override fun add(value : T) {
        // expanding the array when needed
        if (size + 1 >= items.size) {
            val newItems = arrayOfNulls<T>(items.size + CLUSTER_SIZE) as Array<T>
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

    override fun get(index : Int) : T {
        if ((index < 0) || (index >= size)) {
            throw IndexOutOfBoundsException()
        }
        return items[index]
    }

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

    override fun equals(other : Any?) : Boolean {
        if (other !is ArrayOrderedList<*>) {
            return false
        }
        if (other.size() != size) {
            return false
        }
        for (i in 0..size - 1) {
            if (items[i] !== other.get(i)) {
                return false
            }
        }
        return true
    }

    override fun hashCode() : Int {
        var h = 0
        for (i in 0..size - 1) {
            h = 23 * h + items[i].hashCode()
        }
        return h
    }
}