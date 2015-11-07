package hw07

abstract class OrderedListKotlin<A : Comparable<A>> : Comparable<OrderedListKotlin<out A>>, Iterable<A> {
    abstract fun getVal(index: Int): A?
    abstract fun addVal(value: A)
    abstract fun delVal(index: Int)

    abstract internal fun getSize() : Int;
    val errorInAddingException = Exception("It is pretty impossible to get this exception, but if you do, " +
            "it means that something broke in some implementation if OrderedListKotlin.getVal()")

    override fun equals(other: Any?): Boolean {
        if (other !is OrderedListKotlin<*>) return false

        val thisIter = iterator()
        val otherIter = other.iterator()

        var res = true
        if (getSize() != other.getSize()) return false

        while (thisIter.hasNext()) {
            val curThis = thisIter.next()
            val curOther = otherIter.next()
            res = res && curThis == curOther
        }
        return res
    }

    override public operator fun compareTo(other: OrderedListKotlin<out A>): Int {
        val thisSize = getSize()
        val minSize = Math.min(thisSize, other.getSize())

        val thisIter = iterator()
        val otherIter = other.iterator()

        for (i in 0..minSize - 1) {
            val curCompare = thisIter.next().compareTo(otherIter.next())
            if (curCompare != 0) return curCompare
        }
        if (thisSize < other.getSize()) return -1
        if (thisSize > other.getSize()) return 1
        return 0
    }
}
