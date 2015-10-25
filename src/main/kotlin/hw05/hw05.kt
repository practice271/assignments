package hw05

/**
 * Created by Antropov Igor 18.10.15
 *
 * 1000001  1 - 16    2 - 13   4 - 12    8 - 4
 * 10000001  1 - 105    2 - 45   4 - 32    8 - 32
 * 100000001  1 - 895    2 - 569   4 - 442   8 - 359
 */

import kotlin.concurrent.thread
import kotlin.util.measureTimeMillis

public fun incrementer (threadNumbers : Int): Int {
    var res = 0
    val threadArray = Array(threadNumbers, {thread{res++}})
    for (x in threadArray) x.join()
    return res
}

var temp = IntArray(0)

public fun parallelMergeSort(a: IntArray, threadCount: Int) {
    temp = IntArray(a.size)

    fun sort(l: Int, r: Int, a: IntArray, threadCount: Int ) {
        if (threadCount <= 1) {
            mergeSort(l, r, a)
        }else if (a.size >= 2) {
            val middle = (r + l) / 2
            var lthread = thread { sort(l, middle, a, threadCount / 2) }
            sort(middle + 1, r, a, threadCount / 2)
            lthread.join()
            merge(l, middle, r, a)
        }
    }
    sort(0, a.size - 1, a, threadCount)
}

internal fun mergeSort(l: Int, r: Int, a: IntArray){
    if (l < r){
        val mid = l + (r - l) / 2
        mergeSort(l, mid, a)
        mergeSort(mid + 1, r, a)
        merge(l, mid, r, a)
    }
}

internal fun merge (l: Int, middle: Int, r: Int, a: IntArray){
    for (i in l..r) temp[i] = a[i]
    var i1 = l
    var i2 = middle + 1
    var i = l
    while (i1 <= middle && i2 <= r) {
        if (temp[i1] <= temp [i2]) {
            a[i] = temp[i1]
            i1++
        } else {
            a[i] = temp[i2]
            i2++
        }
        i++
    }
    while(i1 <= middle){
        a[i] = temp[i1]
        i++
        i1++
    }
}



