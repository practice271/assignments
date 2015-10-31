/*
 * Homework 7 (27.10.2015)
 * Implementation of list using ADT on Kotlin.
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw07

/** Implementation of IOrderedList using abstract data type. */
data class KotlinADTList<T : Comparable<T>>(private var currentSize : Int) : IOrderedList<T> {

    /** ADT for list. */
    private inner class Cons(var head : T, var tail : Cons?)

    private var list : Cons? = null

    /** Checks if the list is empty. */
    override fun isEmpty() : Boolean {
        return (currentSize == 0)
    }

    /** Returns length of the list. */
    override fun length() : Int {
        return currentSize
    }

    /** Inserts element into given ADT list. */
    private fun insert(l : Cons?, elem : T) : Cons {
        if ((l != null) && (elem.compareTo(l.head) < 0))
            return Cons(l.head, insert(l.tail, elem))
        return Cons(elem, l)
    }

    /** Puts element into the list. */
    override fun push(elem : T) {
        currentSize++
        list = insert(list, elem)
    }

    /** Returns element on given index in ADT list. */
    private fun takeElem(l : Cons?, n : Int) : T? {
        if (n == 0)
            if (l == null) return null
            else return l.head
        return takeElem(l?.tail, n - 1)
    }

    /** Returns element on given index. */
    override fun take(index : Int) : T {
        return takeElem(list, index) ?: throw Exception("Incorrect index")
    }

    /** Deletes element on given index from ADT list. */
    private fun delete(l : Cons?, n : Int) : Cons? {
        if (l == null) return null
        if (n == 0)
            if (l.tail == null) return null
            else return Cons((l.tail)!!.head, (l.tail)?.tail)
        return Cons(l.head, delete(l.tail, n - 1))
    }

    /** Deletes element on given index. */
    override fun remove(index : Int) {
        currentSize--
        list = delete(list, index)
    }

    /** Returns element on given index and deletes it from the list. */
    override fun pop(index : Int) : T {
        val temp = take(index)
        remove(index)
        return temp
    }

    /** Adds elements from given list into current list. */
    override fun concat(newList : IOrderedList<T>) {
        val temp = ADTList<T>()

        for (i in 0 .. currentSize - 1) {
            temp.push(list?.head)
            list = list?.tail
        }
        for (i in 0 .. newList.length() - 1)
            temp.push(newList.take(i))

        currentSize += newList.length()
        for (i in 0 .. temp.length() - 1) {
            list = insert(list, temp.take(i))
        }
    }

    /** Gets string from given ADT list. */
    private fun getString(l : Cons?) : String {
        if (l != null)
            return (l.head).toString() + ", " + getString(l.tail)
        return "null"
    }

    /** Prints the list. */
    override fun print() {
        println("[" + getString(list) + "]")
    }

    /** Returns hash code of the list. */
    override fun hashCode() : Int {
        var hash = 0
        var newList : Cons? = list
        for (i in 0 .. currentSize - 1) {
            if (newList != null)
                hash = Math.abs((hash + newList.head.hashCode()) * 31)
            newList = newList?.tail
        }
        return hash
    }

    /** Checks equality between given object and the list. */
    override fun equals(other : Any?) : Boolean {
        if (other is IOrderedList<*> && other.length() == currentSize) {
            var newList : Cons? = list
            for (i in 0 .. currentSize - 1) {
                if (newList?.head != other.take(i)) return false
                newList = newList?.tail
            }
            return true
        }
        return false
    }
}