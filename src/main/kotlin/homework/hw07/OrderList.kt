package homework.hw07


abstract  class OrderList<T : Comparable<T>> : Comparable<OrderList<out T>> {

    override fun hashCode(): Int {
        var i = size() - 1
        var answer = 0
        while (i > 0) {
            answer =  (answer + get(i).hashCode()) * 31
            i--
        }
        return Math.abs(answer)
    }

    override fun equals(other: Any?): Boolean {

        if (other !is OrderList<*>) return false

        if (size() != other.size()) return false

        var answer = true
        var i = 0
        while (i < size() && answer) {
            answer = get(i) == other[i]
            i++
        }
        return answer
    }

    override fun compareTo(other: OrderList<out T>): Int {
        if (size() > other.size()) return 1
        if (size() < other.size()) return -1

        var i = 0
        while (i < size()) {
            val comprassion = get(i).compareTo(other[i])
            if (comprassion != 0) return comprassion
            i++
        }

        return 0
    }

    abstract fun add(value: T)

    abstract fun remove(value : T)

    abstract fun size(): Int

    abstract operator fun get(index: Int): T
}


class KotlinAtdOrderList<T : Comparable<T>>() : OrderList<T>() {

    private var count = 0
    private var list: Node<T>? = null

    internal inner class Node<T : Comparable<T>>(var value: T, var next: Node<T>?)

    override fun add(value: T) {
        list = insert(value, list)
        count++
    }

    private fun insert(value: T, node: Node<T>?): Node<T> {
        when (node) {
            null -> return Node(value, null)
            else -> {
                if (node.value.compareTo(value) >= 0)
                    return Node(value, Node(node.value, node.next))
                return Node(node.value, insert(value, node.next))
            }
        }
    }


    override fun remove(value: T) {
        if (list != null) list = delete(value, list)
    }

    private fun delete(value: T, node: Node<T>?): Node<T>? {
        when (node) {
            null -> return null
            else -> {

                if (node.value.equals(value)) return node.next
                when (node.next) {
                    null -> return null
                    else -> {
                        if (node.next!!.value != value) return delete(value, node.next)
                        return Node(node.value, node.next!!.next)
                    }
                }
            }
        }
    }

    override fun size(): Int {
        return count
    }

    override fun get(index: Int): T {
        var index = index
        var temp: Node<T>? = list
        while (index > 0) {
            temp = temp!!.next
            index--;
        }
        return temp!!.value
    }

    fun print() {
        var temp: Node<T>? = list
        while (temp != null) {
            println(temp.value)
            temp = temp.next
        }
    }
}


class KotlinArrayOrderList<T : Comparable<T>> : OrderList<T>() {

    private var arr: Array<T> = arrayOfNulls<Comparable<Any>>(10) as Array<T>
    private var count = 0

    override fun add(value: T) {
        fun toRight(index: Int) {

            if (count === 0)
                arr[1] = arr[0]
            else
                for (i in count downTo index + 1) {
                    arr[i] = arr[i - 1]
                }
        }

        if (count == arr.size - 1) bigger()
        var i = 0
        while (i < count && arr[i].compareTo(value) == -1) i++
        toRight(i)
        arr[i] = value
        count++
    }

    private fun bigger() {

        val newarr = arrayOfNulls<Comparable<Any>>(arr.size + 10) as Array<T>
        for (i in arr.indices) {
            newarr[i] = arr[i]
        }
        arr = newarr
    }

    override fun remove(value: T) {
        fun toLeft(index: Int) {
            for (i in index..count) {
                arr[i] = arr[i + 1]
            }
        }
        var i = 0
        while (i < count && arr[i] != value) i++
        toLeft(i)
    }


    override fun size(): Int {
        return count
    }

    override fun get(index: Int): T {
        return arr[index]
    }

    fun print() {
        var i = 0
        while (i < size()) {
            println(arr[i])
            i++
        }
    }
}
