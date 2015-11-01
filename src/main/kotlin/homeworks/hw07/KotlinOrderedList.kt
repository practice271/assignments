package homeworks.hw07

public abstract class KotlinOrderedList<T : Comparable<T>> : Comparable<KotlinOrderedList<out T>> {
    abstract public fun get(index: Int): T
    abstract public fun size(): Int

    abstract public fun add(value: T)
    abstract public fun removeAt(index: Int)

    abstract override public fun hashCode(): Int
    abstract override public fun equals(other: Any?): Boolean

    override public fun compareTo(other: KotlinOrderedList<out T>): Int {
        val len = Math.min(size(), other.size())
        for (i in 0..len - 1) {
            val object1 = get(i)
            val object2 = other[i]
            val result = object1.compareTo(object2)
            if (result != 0) {
                return result
            }
        }
        return size() - other.size()
    }
}