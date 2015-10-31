package hw07

import java.util.Arrays
import kotlin.properties.Delegates

class OrderedListATDKotlin<A : Comparable<A>> (array: Array<A>?, private val isAscending: Boolean) : OrderedListKotlin<A>() {
    private var vals: List by Delegates.notNull<List>();
    private var size: Int = 0
    override fun getSize() : Int {
        return size;
    }
    protected abstract inner class List
    protected inner class ListEmpty : List()

    init {
        if (array == null) {
            size = 0
            vals = ListEmpty()
        } else {
            size = array.size
            vals = ListNotEmpty(array)
        }
    }

    protected inner class ListNotEmpty : List {
        internal var head: A
        internal var tail: List

        internal constructor(data: Array<A>) {
            Arrays.sort(data) { a, b -> isInOrderInt(a, b) * (-1) }
            //we should sort in the wrong order 'cause pushing will reverse the array
            head = data[0]
            tail = ListEmpty()
            for (i in 1..data.size - 1) push(data[i])
        }

        internal constructor(headIn: A, tailIn: List) {
            head = headIn
            tail = tailIn
        }

        private fun push(value: A) {
            //without regard for demanded order.
            tail = ListNotEmpty(head, tail)
            head = value
        }
    }

    private fun isInOrderInt(fst: A, snd: A): Int {
        if (isInOrder(fst, snd)) return -1
        return 1
    }

    private fun isInOrder(fst: A, snd: A): Boolean {
        return ((fst.compareTo(snd) <= 0) == isAscending)
        /*ascending == ascending
        descending != ascending
        ascending != descending
        descending == descending
         */
    }

    private fun addVal(value: A, l: List): ListNotEmpty {
        if (l is ListEmpty) return (ListNotEmpty(value, l))
        val listGood = (l as ListNotEmpty)
        if (isInOrder(value, listGood.head)) return (ListNotEmpty(value, l))
        return (ListNotEmpty(listGood.head, addVal(value, listGood.tail)))
    }

    override fun addVal(value: A) {
        vals = addVal(value, vals)
        size++
    }

    private fun getFromDepth(depth: Int, l: List): A? {
        if (l is ListEmpty) return null
        if (depth == 0) {
            return (l as ListNotEmpty).head
        }
        return getFromDepth(depth - 1, (l as ListNotEmpty).tail)
    }

    override fun getVal(index: Int): A? {
        if (index >= size) return null
        return getFromDepth(index, vals)
    }

    private fun delFromDepth(depth: Int, l: List): List {
        if (l is ListEmpty) return l
        val listGood = l as ListNotEmpty
        if (depth == 0) {
            return listGood.tail
        }
        return ListNotEmpty(listGood.head, delFromDepth(depth - 1, listGood.tail))
    }

    override fun delVal(index: Int) {
        if (index < size && index > -1) {
            vals = delFromDepth(index, vals)
            size--
        }
    }

    private fun calcHashCode(hash: Int, l: List): Int {
        if (l is ListEmpty) return hash
        val listGood = (l as ListNotEmpty)
        return calcHashCode(hash * 31 + listGood.head.hashCode(), listGood.tail)
    }

    override fun hashCode(): Int {
        return calcHashCode(1, vals)
    }

    private fun checkEquality(l: List, other: OrderedListKotlin<A>, ind: Int): Boolean {
        if (other.getVal(ind) == null) {
            return (l is ListEmpty)
        }
        if (l is ListEmpty) return false
        val listGood = (l as ListNotEmpty)
        return listGood.head == other.getVal(ind) && checkEquality(listGood.tail, other, ind + 1)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is OrderedListKotlin<*>) return false
        if (other.getSize() == 0) {
            return vals is ListEmpty
        }
        return size == other.getSize() && checkEquality(vals, other as OrderedListKotlin<A>, 0)
    }
}