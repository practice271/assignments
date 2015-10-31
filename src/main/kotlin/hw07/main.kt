package hw07

import java.util.*

class OrderedListKt<T : Comparable<T>> : AbstractCollection<T>(),
                                         Comparable<OrderedListKt<T>> {

    private inner class ListNode(val value: T, var next: ListNode?)

    private inner class ListIterator(var currentNode: ListNode?) :
            MutableIterator<T> {
        override fun hasNext(): Boolean {
            return currentNode != null
        }

        override fun next(): T {
            if (currentNode == null)
                throw NoSuchElementException()

            val res = currentNode!!.value
            currentNode = currentNode!!.next
            return res
        }

        override fun remove() {
            throw UnsupportedOperationException()
        }
    }

    private var head: ListNode? = null
    private var sz = 0

    override fun compareTo(other: OrderedListKt<T>): Int {
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

    override fun equals(other: Any?): Boolean {
        return (other is OrderedListKt<*> &&
                compareTo(other as OrderedListKt<T>) == 0)
    }

    override fun hashCode(): Int {
        var h = 1777
        val it = iterator()
        while (it.hasNext()) {
            h = h * 31 + it.next().hashCode()
        }
        return h
    }

    override val size: Int
        get() = sz

    override fun iterator(): MutableIterator<T> {
        return ListIterator(head)
    }

    override fun add(element: T): Boolean {
        var current = head
        var last: ListNode? = null
        while (current != null && current.value.compareTo(element) < 0) {
            last = current
            current = current.next
        }
        val node = ListNode(element, current)
        if (current === head) {
            head = node
        } else {
            last!!.next = node
        }
        sz++
        return true
    }

}

fun main(args: Array<String>) {

}
