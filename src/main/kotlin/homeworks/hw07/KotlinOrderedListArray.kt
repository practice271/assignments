package homeworks.hw07

import java.util.Arrays

public class KotlinOrderedListArray<T : Comparable<T>> : KotlinOrderedList<T>() {
    private var array: Array<T> = arrayOfNulls<Comparable<Any>>(0) as Array<T>
    private var size: Int = 0
    private var currentIndex: Int = 0

    public override fun size(): Int {
        return size
    }

    public override fun get(index: Int): T {
        return array[index]
    }

    public override fun add(obj: T) {
        fun resize() {
            size *= 2
            array = Arrays.copyOf(array, size)
        }

        fun binarySearch(key: T): Int {
            var low = 0
            var high = currentIndex
            var result = 0
            var mid = (low + high) / 2
            while (low <= high && mid != currentIndex) {
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

        if (size == 0) {
            size++
            array = Arrays.copyOf(array, size)
            array[0] = obj
        } else {
            currentIndex++
            val index = binarySearch(obj)
            if (currentIndex == size) {
                resize()
            }
            System.arraycopy(array, index, array, index + 1, currentIndex - index)
            array[index] = obj
        }
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

    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            internal var index = 0

            override fun hasNext(): Boolean {
                return (index <= currentIndex)
            }

            override fun next(): T {
                index++
                return array[index - 1]
            }
        }
    }
}
