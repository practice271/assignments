package hw07

/**
 * Created by Mikhail on 01.11.2015.
 */
abstract class AOrderedListKT<A : Comparable<A>> : Comparable<AOrderedList<out A>> {
    abstract operator fun get(index: Int): A
    abstract fun add(item: A)
    abstract fun size(): Int
    abstract operator fun contains(item: A): Boolean

    override fun compareTo(other: AOrderedList<out A>): Int {
        if (size() > other.size()) return 1
        if (size() < other.size()) return -1

        var i = 0
        while (i < size()) {
            val compare = get(i).compareTo(other.get(i))
            if (compare != 0) return compare
            i++
        }
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOrderedList<*>) return false
        if (size() != other.size()) return false
        for (i in 0..size() - 1) {
            if (get(i) !== other.get(i)) return false
        }
        return true
    }

    override fun hashCode(): Int {
        var res = 0
        for (i in 0..size() - 1) {
            res = res * 23 + get(i).hashCode()
        }
        return res
    }
}
