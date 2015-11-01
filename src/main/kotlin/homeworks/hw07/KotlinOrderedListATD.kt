package homeworks.hw07

public class KotlinOrderedListATD<T : Comparable<T>> : KotlinOrderedList<T>() {
    private var head: Node<T>? = null
    private var size: Int = 0

    data private class Node<T>(private var value_param: T) {
        private var next_param: Node<T>? = null

        public var value: T = value_param
            get() = field
            set(newValue) {
                field = newValue
            }
        public var next: Node<T>? = next_param
            get() = field
            set(newNext) {
                field = newNext
            }

        constructor(value: T, next: Node<T>?) : this(value) {
            this.value = value
            this.next = next
        }
    }

    public override fun size(): Int {
        return size
    }

    public override fun get(index: Int): T {
        var node = head
        for (i in 0..index - 1) {
            node = node?.next
        }
        return node!!.value // Could not to avoid !!
    }

    public override fun add(obj: T) {
        var tempHead = head
        if (tempHead == null) {
            head = Node(obj)
            size++
            return
        }

        if (obj.compareTo(tempHead.value) < 0) {
            tempHead.next = Node(tempHead.value, tempHead.next)
            tempHead.value = obj
            size++
            return
        }
        var node = tempHead
        var resultComp = node.next?.value?.compareTo(obj) ?: 0
        while ((node?.next != null) && (resultComp <= 0)) {
            node = node?.next
            resultComp = node?.next?.value?.compareTo(obj) ?: 0
        }
        node?.next = Node(obj, node?.next)
        size++
    }

    public override fun removeAt(index: Int) {
        if ((index <= 0 || index >= size)) {
            return
        }
        var node = head
        for (i in 0..index - 2) {
            node = node?.next
        }
        node?.next = node?.next?.next
        size--
    }

    public override fun hashCode(): Int {
        var hash = 0
        var node = head
        for (i in 0..size - 1) {
            val h = node?.value?.hashCode() ?: 0
            hash = hash * 31 + h
            node = node?.next
        }
        return hash
    }

    public override fun equals(other: Any?): Boolean {
        if (other !is KotlinOrderedListATD<*>) {
            return false
        }
        if (size() != other.size()) {
            return false
        }
        var node = head
        for (i in 0..size() - 1) {
            if (node?.value !== other.get(i)) {
                return false
            }
            node = node?.next
        }
        return true
    }
}