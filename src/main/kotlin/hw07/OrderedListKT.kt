package hw07

/**
 * Created by Mikhail on 01.11.2015.
 */

class OrderedListKT<A : Comparable<A>> : AOrderedListKT<A>() {

    inner class Node(var item: A) {
        var next: Node? = null

        init {
            next = null
        }
    }

    private var head: Node? = null
    private var size: Int = 0

    init {
        head = null
        size = 0
    }

    override fun add(item: A) {
        val newNode = Node(item)
        val temp = head?.item
        if (head == null) {
            head = newNode
            size++
            return
        } else if (temp != null && item.compareTo(temp) < 0) {
                    newNode.next = head
                    head = newNode
                    size++
                }

        else {
            var after = head?.next
            var before: Node? = head
            while (after != null) {
                if (item.compareTo(after.item) < 0) break
                before = after
                after = after.next
            }
            newNode.next = before?.next
            before?.next = newNode
            size++
        }
    }

    override fun contains(item: A): Boolean {
        var n = head
        while (n != null) {
            if (item.compareTo(n.item) == 0) return true
            n = n.next
        }
        return false
    }

    override fun size(): Int {
        return size
    }

    override fun get(index: Int): A {
        var i = 0
        var curNode: Node? = head
        while (i < index) {
            curNode = curNode?.next
            i++
        }
        val res = curNode?.item
        if (res == null) throw Exception()
        else return res
    }

}

