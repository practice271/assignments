package hw07

abstract class OrdList_Kotlin<A : Comparable<A>> : Comparable<OrdList_Kotlin<out A>> {
    abstract fun size(): Int
    abstract operator fun get(index: Int): A
    abstract fun print()
    abstract fun add(elem: A)
    abstract fun remove(index: Int)

    override fun compareTo(other: OrdList_Kotlin<out A>): Int {
        if (size() > other.size()) return 1
        if (size() < other.size()) return -1
        for (i in 0..size() - 1) {
            val compare = get(i).compareTo(other[i])
            if (compare != 0) return compare
        }
        return 0
    }

    fun equals(other: OrdList_Kotlin<A>): Boolean {
        if (size() != other.size()) return false
        for (i in 0..size() - 1) {
            if (get(i) != other[i]) return false
        }
        return true
    }

    fun hashcode(): Int {
        var hash = 0
        for (i in 0..size() - 1) {
            hash = get(i).hashCode() + hash * 31
        }
        return hash
    }
}



