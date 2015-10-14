package hw05

/**
 * Created by Mikhail on 14.10.2015.
 */
import java.util.*

public object MergeSort {

    public fun parallelMergeSort(a: IntArray, threadCount: Int) {
        if (threadCount <= 1) {
            mergeSort(a)
        } else if (a.size() >= 2) {
            val left = Arrays.copyOfRange(a, 0, a.size() / 2)
            val right = Arrays.copyOfRange(a, a.size() / 2, a.size())

            val lThread = sorter(left, threadCount / 2)
            val rThread = sorter(right, threadCount / 2)
            lThread.start()
            rThread.start()

            try {
                lThread.join()
                rThread.join()
            } catch (ie: InterruptedException) {}

            merge(left, right, a)
        }
    }

    private fun sorter(a: IntArray, threadCount: Int): Thread {
        return object: Thread() {
            override fun run() {
                parallelMergeSort(a, threadCount)
            }
        }
    }

    private fun mergeSort(a: IntArray) {
        if (a.size() >= 2) {
            val left = Arrays.copyOfRange(a, 0, a.size() / 2)
            val right = Arrays.copyOfRange(a, a.size() / 2, a.size())

            mergeSort(left)
            mergeSort(right)
            merge(left, right, a)
        }
    }

    private fun merge(left: IntArray, right: IntArray, a: IntArray) {
        var i1 = 0
        var i2 = 0
        a.indices.forEach {
            if (i2 >= right.size() || (i1 < left.size() && left[i1] < right[i2])) {
                a[it] = left[i1]
                i1++
            } else {
                a[it] = right[i2]
                i2++
            }
        }
    }
}


