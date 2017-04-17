package homeworks.hw07

public abstract class KotlinOrderedList<T : Comparable<T>> : Comparable<KotlinOrderedList<out T>>, Iterable<T> {
    abstract public fun get(index: Int): T
    abstract public fun size(): Int

    abstract public fun add(value: T)
    abstract public fun removeAt(index: Int)

    override fun hashCode(): Int {
        var hash = 0
        for (t in this) {
            hash = Math.abs((hash + t.hashCode()) * 31)
        }
        return hash
    }

    override public fun equals(other: Any?): Boolean {
        if (other !is KotlinOrderedList<*>) {
            return false
        }

        val size = size()
        if (size != other.size()) {
            return false
        }

        val listIter = iterator()
        val otherIter = other.iterator()
        while (listIter.hasNext()) {
            if (listIter.next() != otherIter.next()) {
                return false
            }
        }
        return true
    }

    override public fun compareTo(other: KotlinOrderedList<out T>): Int {
        val size = size()
        val minSize = Math.min(size, other.size())

        if (size > other.size()) {
            return 1
        }
        if (size < other.size()) {
            return -1
        }

        val listIter = iterator()
        val otherIter = other.iterator()

        for (i in 0..minSize - 1) {
            val result = listIter.next().compareTo(otherIter.next())
            if (result != 0) {
                return result
            }
        }
        return 0
    }
}