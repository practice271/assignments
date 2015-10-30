package homework.hw07.kotlinimp

/**
 * Hw07: a generic abstract ordered list.
 *
 * @author Kirill Smirenko, group 271
 */
abstract class AbstractOrderedList<T : Comparable<T>>
: Comparable<AbstractOrderedList<out T>> {
    abstract fun add(value : T)

    abstract operator fun get(index : Int) : T

    abstract fun removeAt(index : Int)

    abstract fun size() : Int

    override operator fun compareTo(other : AbstractOrderedList<out T>) : Int {
        // compares lexicographically
        val lim = Math.min(size(), other.size())
        var i = 0
        while (i < lim) {
            val v1 = get(i)
            val v2 = other[i]
            val res = v1.compareTo(v2)
            if (res != 0) {
                return res
            }
            i++
        }
        return size() - other.size()
    }
}