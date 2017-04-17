package hw07

/**
 * Created by Mikhail on 01.11.2015.
 */
abstract class AOrderedListKT<A : Comparable<A>> : Comparable<AOrderedList<out A>>, Iterable<A> {
    abstract operator fun get(index: Int): A
    abstract fun add(item: A)
    abstract fun size(): Int
    abstract operator fun contains(item: A): Boolean

    override fun compareTo(other: AOrderedList<out A>): Int {
        if (size() > other.size()) return 1
        if (size() < other.size()) return -1
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOrderedList<*>) return false
        val otherOrderedList = other
        val iterateThis = iterator()
        val iterateOther = (other as AOrderedListKT<*>).iterator()
        if (size() != otherOrderedList.size()) return false
        while (iterateThis.hasNext()) {
            while (iterateThis.next() != iterateOther.next()) {
                return false
            }
        }
        return true
    }

    override fun hashCode(): Int {
        var res = 0
        for (item in this) res = res * 23 + item.hashCode()
        return res
    }
}
