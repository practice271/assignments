package hw05

/**
 * Created by Mikhail on 14.10.2015.
 */
import java.util.*

public object MergeSort {
    var helper = Array(0, {0})
    public fun parallelMergeSort(a: IntArray, threadCount: Int) {
        helper = Array(a.size(), {0})
        fun parallelMergeSort_helper(left: Int, right: Int, a: IntArray, threadCount: Int) {
            if (threadCount <= 1) {
                mergeSort(a, left, right)
            } else if (a.size() >= 2) {
                val mid = (left + right) / 2
                val lThread = Thread { parallelMergeSort_helper(left, mid, a, threadCount / 2)}
                val rThread = Thread { parallelMergeSort_helper(mid + 1, right, a, threadCount / 2)}
                lThread.start()
                rThread.start()
                lThread.join()
                rThread.join()

                merge(left, mid, right, a)
            }
        }
        parallelMergeSort_helper(0, a.size() - 1, a, threadCount)
    }

    private fun mergeSort(a: IntArray, l: Int, r: Int) {
        if (l < r) {
            val m = l + (r - l) / 2
            mergeSort(a, l, m)
            mergeSort(a, m + 1, r)
            merge(l, m, r, a)
        }
    }

    private fun merge(left: Int, middle: Int, right: Int, a: IntArray) {
        for (i in left..right) helper[i] = a[i]

        var start = left
        var j = middle + 1
        var i = left
        while(start <= middle && j <= right) {
            if(helper[start] <= helper[j]) {
                a[i] = helper[start]
                start++
            } else {
                a[i] = helper[j]
                j++
            }
            i++
        }
        while(start <= middle) {
            a[i] = helper[start]
            i++
            start++
        }
    }
}

/*
fun main(args: Array<String>) {
    var LENGTH = 1000
    val RUNS = 16
    val RAND = Random(42)

    fun createRandomArray(length: Int): IntArray {
        val a = IntArray(length)
        for (i in a.indices) {
            a[i] = RAND.nextInt(1000)
        }
        return a
    }
    for (i in 1..RUNS) {
        val a = createRandomArray(LENGTH)
        val startTime1 = System.currentTimeMillis()
        MergeSort.parallelMergeSort(a, 2)
        val endTime1 = System.currentTimeMillis()

        System.out.printf("%10d elements  =>  %6d ms \n", LENGTH, endTime1 - startTime1)
        LENGTH *= 2
    }
}

*/


