package homeworks.hw07

import java.util.Arrays

public class KotlinOrderedListArray<T : Comparable<T>> : JavaOrderedList<T>() {
    private var array: Array<T> = arrayOfNulls<Comparable<Any>>(0) as Array<T>
    private var size: Int = 0

    public override fun size(): Int {
        return size
    }

    public override fun get(index: Int): T {
        return array[index]
    }

    public override fun add(obj: T) {
        if (size == 0) {
            size++
            array = Arrays.copyOf(array, size)
            array[0] = obj
        } else {
            size++
            val index = binarySearch(obj)
            array = Arrays.copyOf(array, size)
            for (i in size - 1 downTo index + 1) {
                array[i] = array[i - 1]
            }
            array[index] = obj
        }
    }

    private fun binarySearch(key: T): Int {
        var low = 0
        var high = size - 1
        var result = 0
        var mid = (low + high) / 2
        while (low <= high && mid != size - 1) {
            val midVal = array[mid]
            val cmp = midVal.compareTo(key)
            when {
                cmp < 0 -> { low  = mid + 1; result = low }
                cmp > 0 -> { high = mid - 1; result = mid }
                else    -> { return mid }
            }
            mid = (low + high) / 2
        }
        return result //key not found, return position to add
    }

    public override fun removeAt(index: Int) {
        if ((index <= 0 || index >= size)) {
            return
        }
        size--
        val temp = array
        System.arraycopy(array, index + 1, temp, index, size - index)
        array = Arrays.copyOf(temp, size)
    }

    public override fun hashCode(): Int {
        var hash = 0
        for (i in 0..size() - 1) {
            hash = hash * 31 + get(i).hashCode()
        }
        return hash
    }

    public override fun equals(other: Any?): Boolean {
        if (other !is KotlinOrderedListArray<*>) {
            return false
        }
        if (size() != other.size()) {
            return false
        }
        for (i in 0..size() - 1) {
            if (get(i) !== other.get(i)) {
                return false
            }
        }
        return true
    }
}
