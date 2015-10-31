/*
 * Homework 7 (27.10.2015)
 * Implementation of list using arrays on Kotlin.
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw07

import java.util.Arrays

/** Implementation of IOrderedList using arrays. */
data class KotlinArrayList<T : Comparable<T>>(private var currentSize : Int) : IOrderedList<T> {
    private var list = newArray()
    private var currentIndex = 0

    /** Creates new array. */
    private fun newArray() : Array<T> {
        return arrayOfNulls<Comparable<Any>>(currentSize) as Array<T>
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
        var temp = newArray()
        currentIndex++

        if (currentIndex > currentSize - 1) {
            currentSize += 10
            temp = newArray()
        }

        var pos = 0
        for (i in 0 .. currentIndex - 2) {
            if (elem.compareTo(list[i]) > 0) break
            else pos++
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
        System.arraycopy(list, index + 1, list, index, currentSize - 1 - index)
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

        for (i in 0 .. currentIndex - 1)
            temp.push(list[i])
        for (i in 0 .. newList.length() - 1)
            temp.push(newList.take(i))

        currentIndex = temp.length()
        if (currentSize < temp.length()) {
            currentSize = temp.length()
            list = newArray()
        }
        for (i in 0 .. temp.length() - 1)
            list[i] = temp.take(i)
    }

    /** Prints the list. */
    override fun print() {
        println(Arrays.toString(list))
    }

    /** Returns hash code of the list. */
    override fun hashCode() : Int {
        var hash = 0
        for (i in 0 .. currentIndex - 1)
            hash = Math.abs((hash + list[i].hashCode()) * 31)
        return hash
    }

    /** Checks equality between given object and the list. */
    override fun equals(other : Any?) : Boolean {
        if (other is IOrderedList<*> && other.length() == currentIndex) {
            for (i in 0 .. currentIndex - 1)
                if (list[i] != other.take(i)) return false
            return true
        }
        return false
    }
}