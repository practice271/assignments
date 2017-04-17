package homework.hw07.kotlinimp

import homework.hw07.AbstractOrderedList

/**
 * Linked list implementation of AbstractOrderedList

 * @author Kirill Smirenko, group 271
 */
class LinkedOrderedList<T : Comparable<T>> : AbstractOrderedList<T>() {
    private var head : Node<T>? = null
    private var size : Int = 0

    override fun add(value : T) {
        val headSnap = head // to avoid using "!!"
        if (headSnap == null) {
            head = Node(value, null)
            size++
            return
        }
        if (value.compareTo(headSnap.value) < 0) {
            // TODO: CHECK
            headSnap.next = Node(headSnap.value, headSnap.next)
            headSnap.value = value
            //head = headSnap
            size++
            return
        }
        var curNode = headSnap
        while ((curNode?.next != null) && (curNode?.next!!.value.compareTo(value) <= 0)) {
            curNode = curNode?.next
        }
        curNode?.next = Node(value, curNode?.next)
        size++
    }

    override fun get(index : Int) : T {
        if ((index < 0) || (index >= size)) {
            throw IndexOutOfBoundsException()
        }
        var i = 0
        var curNode = head
        while (i < index) {
            curNode = curNode?.next
            i++
        }
        return curNode!!.value
    }

    override fun removeAt(index : Int) {
        if ((index <= 0) || (index >= size)) {
            return
        }
        var i = 0
        var curNode = head
        while (i < index - 1) {
            curNode = curNode?.next
            i++
        }
        curNode?.next = curNode?.next?.next
        size--
    }

    override fun size() : Int {
        return size
    }

    override fun hashCode() : Int {
        var h = 0
        var curNode = head
        while (curNode != null) {
            h = 23 * h + curNode.value.hashCode()
            curNode = curNode.next
        }
        return h
    }

    override fun iterator() : MutableIterator<T> = LinkedOrderedListIterator<T>(head)

    private class LinkedOrderedListIterator<T : Comparable<T>>(private var node : Node<T>?) : MutableIterator<T> {
        override fun hasNext() : Boolean = node != null

        override fun next() : T {
            val nodeSnap = node
            if (nodeSnap != null) {
                val value = nodeSnap.value
                node = node?.next
                return value
            }
            throw IllegalStateException()
        }

        override fun remove() {
        }
    }

    data class Node<T>(var value : T, var next : Node<T>?)
}
