package hw07.kotlinClasses

/* OrderedArray made by Guzel Garifullina
   Estimated time  1 hour
   real time       1 hour
*/

data class OrderedArray<E : Comparable<E>>
                (private val size_: Int,private val defaultElem: E)  {
    private  class Elem<E : Comparable<E>> (private val elem : E){
        fun get () : E = elem;
    }
    //because my array consists of classes
    public fun equals(other: OrderedArray<E>): Boolean {
        val size = size()
        if (size != other.size()) {
            return false
        }
        if (size == 0) {
            return true
        }
        for (i in 0..size - 1) {
            if (get(i) != (other.get(i))) {
                return false
            }
        }
        return true
    }
    private var size_basic = Math.max(100, size_)
    private var size = size_
    private var arr =
            Array<Elem<E>>(size_basic, { Elem(defaultElem) })

    fun compareTo(other: OrderedArray<out E>): Int {
        val size = size()
        if (size > other.size()) {
            return 1
        }
        if (size < other.size()) {
            return -1
        }

        for (i in 0..size - 1) {
            val comp = get(i).compareTo(other[i])
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
    private fun resize() {
        size_basic *= 2
        val arr2 = Array<Elem<E>>(size_basic, {i -> arr[i]})
        arr = arr2
    }
    fun add(elem: E) {
        if (size == size_basic) {
            resize()
        }
        for (i in size - 1 downTo 0) {
            if (arr[i].get().compareTo(elem) > 0) {
                arr[i + 1] = arr[i]
            }
            else {
                arr[i + 1] = Elem(elem)
                size++
                return
            }
        }
        arr[0] = Elem(elem)
        size++
        return
    }

    fun get(index: Int): E {
        if ((index < size) && (0 <= index)) {
            return arr[index].get()
        }
        throw ArrayIndexOutOfBoundsException()
    }

    fun removeAt(index: Int) {
        if ((index >= size) && (0 > index)) {
            throw ArrayIndexOutOfBoundsException()
        }
        for (i in index..size - 2) {
            arr[i] = arr[i + 1]
        }
        size--
    }

    fun clear() {
        size = 0
    }
}
