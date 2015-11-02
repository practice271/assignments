package hw07.kotlinClasses

/* OrderedList made by Guzel Garifullina
   Estimated time  1 hour
   real time       1 hour
*/

data class OrderedList<E : Comparable<E>>
    public constructor(private var last: E)  {
    private var size = 1
    private var tail: OrderedList<E>? = null
    init {
        size = 1
        tail = null
    }
    private constructor(last: E, tail: OrderedList<E>?) : this(last) {
        this.tail = tail
        if (tail == null) {
            size = 1
        }
        else {
            size = tail.size() + 1
        }
    }
    public fun compareTo(other: OrderedList<out E>): Int {
        val size = size()
        if (size > other.size()) {return 1 }
        if (size < other.size()) {return -1 }
        for (i in 0..size - 1) {
            val comp = get(i).compareTo(other.get(i))
            if (comp != 0) {
                return comp
            }
        }
        return 0
    }
    public fun isEmpty(): Boolean{
        return size == 0
    }
    public fun size(): Int {
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
        return OrderedList<E>(l.last, addLoc(l.tail, elem))
    }
    public fun add(elem: E) {
        if (size == 0) {
            last = elem
            size++
            return
        }
        if (last.compareTo(elem) < 0) {
            tail = OrderedList(last, tail)
            last = elem
            size++
            return
        }
        tail = addLoc(tail, elem)
        size++
    }
    private fun getLoc(l: OrderedList<E>?, i: Int, index: Int): E {
        if (l == null){ throw Exception ("System is broken")}
        if (i < index) {
            return getLoc(l.tail, i + 1, index)
        }
        return l.last
    }
    public fun get(index: Int): E {
        if ((index >= size) || (index < 0)) {
            throw ArrayIndexOutOfBoundsException()
        }
        if (index == size - 1) {
            return last
        }
        //our traversal from last to first
        return getLoc(tail, 0, size - index - 2)
    }
    private fun remLoc(l: OrderedList<E>?, i: Int, index: Int): OrderedList<E>? {
        if (l == null){ throw Exception ("System is broken")}
        if (i < index) {
            return OrderedList(l.last, remLoc(l.tail, i + 1, index))
        }
        if (i == size - 1) {
            //first elem
            return null
        }
        return l.tail
    }
    public fun removeAt(index: Int) {
        if ((index >= size) || (index < 0)) {
            throw ArrayIndexOutOfBoundsException()
        }
        if (index == size - 1) {
            last = tail?.last ?: throw Exception ("System is broken")
            tail = tail?.tail ?: throw Exception ("System is broken")
            size--
            return
        }
        tail = remLoc(tail, 0, size - index - 2)
        size--
    }
    public fun clear() {
        size = 0
        tail = null
    }
}