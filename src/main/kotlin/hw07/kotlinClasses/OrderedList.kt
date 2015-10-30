package hw07.kotlinClasses

/* OrderedList made by Guzel Garifullina
   Estimated time  1 hour
   real time       1 hour
*/
data class OrderedList<E : Comparable<E>>
    public constructor(private var last: E)  {
    private var size = 1
    private var list: OrderedList<E>? = null

    init {
        size = 1
        list = null
    }

    private constructor(last: E, list: OrderedList<E>?) : this(last) {
        this.list = list
        if (list == null) {
            size = 1
        }
        else {
            size = list.size() + 1
        }
    }
    public fun equals(other: OrderedList<E>): Boolean {
        val size = size()
        if (size != other.size()) {
            return false
        }
        if (size == 0) {
            return true
        }
        for (i in 0..size - 1) {
            if (get(i) != other.get(i)) {
                return false
            }
        }
        return true
    }

    fun compareTo(other: OrderedList<out E>): Int {
        val size = size()
        if (size > other.size()) {
            return 1
        }
        if (size < other.size()) {
            return -1
        }

        for (i in 0..size - 1) {
            val comp = get(i).compareTo(other.get(i))
            if (comp != 0) {
                return comp
            }
        }
        return 0
    }

    fun isEmpty(): Boolean{
        return size == 0
    }
    fun size(): Int {
        return size
    }

    private fun addLoc(l: OrderedList<E>?, elem: E): OrderedList<E> {
        if (l == null) {
            return OrderedList(elem, null)
        }
        val comp = l.last.compareTo(elem)
        if (comp <= 0) {
            return OrderedList(elem, l)
        }
        return OrderedList<E>(l.last, addLoc(l.list, elem))
    }

    fun add(elem: E) {
        if (size == 0) {
            last = elem
            size++
            return
        }
        if (last.compareTo(elem) < 0) {
            list = OrderedList(last, list)
            last = elem
            size++
            return
        }

        list = addLoc(list, elem)
        size++
    }

    private fun getLoc(l: OrderedList<E>?, i: Int, index: Int): E {
        if (l == null){ throw Exception ("System is broken")}
        if (i < index) {
            return getLoc(l.list, i + 1, index)
        }
        return l.last
    }

    fun get(index: Int): E {
        if ((index >= size) || (index < 0)) {
            throw ArrayIndexOutOfBoundsException()
        }
        if (index == size - 1) {
            return last
        }
        //our traversal from last to first
        return getLoc(list, 0, size - index - 2)
    }

    private fun remLoc(l: OrderedList<E>?, i: Int, index: Int): OrderedList<E>? {
        if (l == null){ throw Exception ("System is broken")}
        if (i < index) {
            return OrderedList(l.last, remLoc(l.list, i + 1, index))
        }
        if (i == size - 1) {
            //first elem
            return null
        }
        return l.list
    }

    fun removeAt(index: Int) {
        if ((index >= size) || (index < 0)) {
            throw ArrayIndexOutOfBoundsException()
        }

        if (index == size - 1) {
            last = list?.last ?: throw Exception ("System is broken")
            list = list?.list ?: throw Exception ("System is broken")
            size--
            return
        }
        list = remLoc(list, 0, size - index - 2)
        size--
    }

    fun clear() {
        size = 0
        list = null
    }
}

