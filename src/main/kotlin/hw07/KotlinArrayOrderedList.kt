package hw07

import java.util.Arrays

data class KotlinArrayOrderedList<A : Comparable<A>>(val arr : Array<A>) : IKotlinOrderedList<A> {

    public var leng = 0
        private set

    private var allocated = 100
    private var array = Array<Any?>(100, { null })

    init {
        val initArrLength = arr.size
        allocated = Math.max(allocated, initArrLength)
        array = Arrays.copyOf(arr, allocated)
        leng = initArrLength
    }

    override public fun getLength() = leng

    private fun reallocate() {
        allocated += 100
        array = Arrays.copyOf(array, allocated)
    }

    override public fun add(newElem: A) {
        if (allocated == leng)
            reallocate()
        var index: Int = 0
        while (index < leng && ((array[index] as A).compareTo(newElem) < 0))
            index++
        for (i in leng - 1 downTo index)
            array[i + 1] = array[i]
        array[index] = newElem as Object
        leng++
    }

    override public fun getByIndex(index: Int): A? {
        if (index < 0 || index >= leng)
            return null;
        return array[index] as A
    }

    override public fun removeAt(index: Int): Boolean {
        if (index < 0 || index >= leng)
            return false;
        for (i in index..leng - 2)
            array[i] = array[i + 1];
        leng--
        return true
    }

    override public fun compareTo(other: IOrderedList<out A>): Int {
        val thisLength: Int = leng
        val otherLength: Int = other.getLength()
        val minLength: Int = Math.min(thisLength, otherLength)
        for (i in 0..minLength - 1) {
            val compare = (getByIndex(i) as A).compareTo(other.getByIndex(i));
            if (compare != 0) return compare;
        }
        if (thisLength > otherLength) return -1;
        if (otherLength > thisLength) return 1;
        return 0;
    }

    override public fun equals(other: Any?): Boolean {
        if (other !is IOrderedList<*>)
            return false;
        val otherList = other as IOrderedList<A>
        if (leng != otherList.getLength())
            return false;
        for (i in 0..leng - 1)
            if (!(getByIndex(i) as A).equals(otherList.getByIndex(i))) return false;
        return true;
    }

    override public fun hashCode(): Int {
        var hash: Int = 0
        for (i in 0..leng - 1)
            hash = hash * 31 + (getByIndex(i) as A).hashCode();
        return hash;
    }

    override public fun toArray(): Array<A> {
        return Arrays.copyOf(array, leng) as Array<A>
    }
}