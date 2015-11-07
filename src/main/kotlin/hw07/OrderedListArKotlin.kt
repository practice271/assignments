package hw07

import java.util.*
import kotlin.properties.Delegates

class OrderedListArKotlin<A : Comparable<A>>(array: Array<A>?, private val isAscending : Boolean) : OrderedListKotlin<A>() {
    private var vals: Array<A?> by Delegates.notNull<Array<A?>>()
    private var size: Int = 0
    private var trueSize: Int = 0
    val minArraySize: Int
        get() = MIN_ARRAY_SIZE

    private fun enlargeArray() {
        size *= 2
        val newAr = arrayOfNulls<Comparable<Any>>(size) as Array<A?>
        System.arraycopy(vals, 0, newAr, 0, size / 2)
        vals = newAr
    }

    override fun iterator(): Iterator<A> {
        return arrayIterator()
    }

    inner class arrayIterator internal constructor() : Iterator<A> {
        private var count: Int = 0

        init {
            count = 0
        }

        override fun hasNext(): Boolean {
            return count < trueSize
        }

        override fun next() : A {
            return vals[count++] ?: throw NoSuchElementException()
        }
    }

    init {
        if (array == null) {
            trueSize = 0
        } else {
            trueSize = array.size
            size = array.size * 2
            vals = arrayOfNulls<Comparable<Any>>(size) as Array<A?>
            Arrays.sort(array, { fst, snd -> this.isInOrderInt(fst, snd) })
            System.arraycopy(array, 0, vals, 0, array.size)
        }
    }

    private fun isInOrder(fst: A, snd: A): Boolean {
        return ((fst.compareTo(snd) <= 0) == isAscending)
        /*ascending == ascending
        descending != ascending
        ascending != descending
        descending == descending
         */
    }

    private fun isInOrderInt(fst: A, snd: A): Int {
        if (isInOrder(fst, snd)) return -1
        return 1
    }

    override fun getSize(): Int {
        return trueSize
    }

    override fun getVal(index: Int): A? {
        if (index >= trueSize || index < 0) return null
        return vals[index]
    }

    private fun moveR(ind: Int) {
        if (ind == size) {
            enlargeArray()

            vals[ind + 1] = vals[ind]
            vals[ind] = null
            return
        }
        if (vals[size - 1] == null) {
            System.arraycopy(vals, ind, vals, ind + 1, trueSize - ind)
            vals[ind] = null
            return
        }
        enlargeArray()

        System.arraycopy(vals, ind, vals, ind + 1, size / 2 - ind)
        vals[ind] = null
    }

    private fun moveL(ind: Int) {
        if (ind <= 0) return
        System.arraycopy(vals, ind + 1, vals, ind, trueSize - 1 - ind)
        vals[trueSize - 1] = null
    }

    private fun addValInRange(value: A, l: Int, r: Int) {
        val mid = (r - l) / 2 + l
        val midval = vals[mid] ?: return
        //I'm sure this can't happen
        if (r - l <= 1) {
            if (isInOrder(value, midval)) {
                moveR(mid)//including mid
                vals[mid] = value
                return
            }
            moveR(mid + 1)
            vals[mid + 1] = value
            return
        }
        if (value == vals[mid]) {
            moveR(mid)//logically, it'd be `mid + 1`, but it's possible it does not exist.
            vals[mid] = value
            return
        }
        if (isInOrder(value, midval))
            addValInRange(value, l, mid)
        else
            addValInRange(value, mid + 1, r)
    }

    override fun addVal(value: A) {
        if (trueSize == 0) {
            size = MIN_ARRAY_SIZE
            vals = arrayOfNulls<Comparable<Any>>(size) as Array<A?>
            vals[0] = value
            trueSize++
            return
        }
        addValInRange(value, 0, trueSize - 1)
        trueSize++
    }

    override fun delVal(index: Int) {
        if (index > -1 && index < trueSize) {
            moveL(index)
            trueSize--
        }
    }

    override fun hashCode(): Int {
        var hashCode = 1
        for (i in 0..trueSize - 1) {
            val curVal = vals[i]
            hashCode = 31 * hashCode + (if (curVal == null) 0 else curVal.hashCode())
        }
        return hashCode
    }
    
    private val MIN_ARRAY_SIZE = 16
}