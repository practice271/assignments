package hw07

abstract class OrderedListKotlin<A : Comparable<A>> : Comparable<OrderedListKotlin<out A>> {
    abstract fun getVal(index: Int): A?
    abstract fun addVal(value: A)
    abstract fun delVal(index: Int)

    abstract internal fun getSize() : Int;
    val errorInAddingException = Exception("It is pretty impossible to get this exception, but if you do, " +
            "it means that something broke in some implementation if OrderedListKotlin.getVal()")
}
