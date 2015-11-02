/*
 * Homework 7 (27.10.2015)
 * Implementation of list using arrays on Kotlin.
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw07

import java.util.Arrays

/** Implementation of IOrderedList using arrays. */
class KotlinArrayList<T : Comparable<T>>() : IOrderedList<T>() {
    private var list = newArray(10)
    private var currentIndex = 0

    /** Creates new array. */
    private fun newArray(size : Int) : Array<T> {
        return arrayOfNulls<Comparable<Any>>(size) as Array<T>
    }

    /** Checks if the list is empty. */
    override fun isEmpty() : Boolean {
        return (currentIndex == 0)
    }

    /** Returns length of the list. */
    override fun length() : Int {
        return currentIndex
    }

    /** Puts element into the list. */
    override fun push(elem : T) {
        var size = list.size
        currentIndex++

        if (currentIndex > list.size) size += 10
        val temp = newArray(size)

        var pos = 0
        for (i in 0 .. currentIndex - 2) {
            if (elem.compareTo(list[i]) > 0) break
            pos++
        }

        if (pos > 0) System.arraycopy(list, 0, temp, 0, pos)
        temp[pos] = elem
        System.arraycopy(list, pos, temp, pos + 1, currentIndex - 1 - pos)
        list = temp
    }

    /** Returns element on given index. */
    override fun take(index : Int) : T {
        return list[index]
    }

    /** Deletes element on given index. */
    override fun remove(index : Int) {
        currentIndex--
        System.arraycopy(list, index + 1, list, index, list.size - 1 - index)
    }

    /** Returns element on given index and deletes it from the list. */
    override fun pop(index : Int) : T {
        val temp = take(index)
        remove(index)
        return temp
    }

    /** Adds elements from given list into current list. */
    override fun concat(newList : IOrderedList<T>) {
        val temp = ArrayList<T>()

        for (i in 0 .. currentIndex - 1) temp.push(list[i])
        for (t in newList) temp.push(t)

        currentIndex = temp.length()
        if (list.size < temp.length()) list = newArray(temp.length())

        val iter = temp.iterator()
        for (i in 0 .. temp.length() - 1) list[i] = iter.next()
    }

    /** Prints the list. */
    override fun print() {
        println(Arrays.toString(list))
    }

    /** Iterator for KotlinArrayList. */
    public override fun iterator() : MutableIterator<T> {
        return object : MutableIterator<T> {
            var index = 0

            public override fun hasNext() : Boolean {
                return (index < currentIndex)
            }

            public override fun next() : T {
                index++
                return list[index - 1]
            }

            public override fun remove() {}
        }
    }
}