package hw07

abstract class OrderedListKotlin<A : Comparable<A>> : Comparable<OrderedListKotlin<out A>> {
    abstract fun getVal(index: Int): A?
    abstract fun addVal(value: A)
    abstract fun delVal(index: Int)

    abstract internal fun getSize() : Int;
    val errorInAddingException = Exception("It is pretty impossible to get this exception, but if you do, " +
            "it means that something broke in some implementaiton if OrderedListKotlin.getVal()")
//    override public fun compareTo(other: OrderedListKotlin<out A>): Int {
//        val minSize = Math.min(getSize(), other.getSize())
//        for (i in 0..minSize - 1) {
//            //it's pretty impossible to have NPE here, but I'll still check.
//            val cur1 = getVal(i)
//            val cur2 = other.getVal(i)
//            if (cur1 == null && cur2 != null) return -1
//            if (cur1 != null && cur2 == null) return 1
//            if (cur1 != null && cur2 != null) {
//                val curCompare = cur1.compareTo(cur2)
//                if (curCompare != 0) return curCompare
//            }
//        }
//        if (getSize() < other.getSize()) return -1
//        if (getSize() > other.getSize()) return 1
//        return 0
//    }
}
