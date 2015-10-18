package hw05

/**
 * Created by Antropov Igor 18.10.15
 */

import kotlin.concurrent.thread
import kotlin.util.measureTimeMillis

public fun incrementer (threadNumbers : Int): Int {
    var res = 0
    val threadArray = Array(threadNumbers, {thread{res++}})
    for (x in threadArray) x.join()
    return res
}

public fun parallelMergeSort(a: IntArray, threadCount: Int) {
    if (threadCount <= 1) {
        mergeSort(a)
    } else if (a.size() >= 2) {
        var left: IntArray = a.copyOfRange(a.size() - a.size(), a.size() / 2)
        var right: IntArray = a.copyOfRange(a.size() / 2, a.size())
        var lthread = thread { parallelMergeSort(left, threadCount / 2) }
        parallelMergeSort(right, threadCount / 2)
        lthread.join()
        merge(left, right, a)
    }
}

internal fun mergeSort(a: IntArray){
    if (a.size() >= 2){
        var left: IntArray = a.copyOfRange(a.size() - a.size(), a.size()  / 2)
        var right: IntArray = a.copyOfRange(a.size() / 2, a.size())
        mergeSort(left)
        mergeSort(right)
        merge(left, right, a)
    }
}

internal fun merge (right: IntArray, left: IntArray, a: IntArray){
    var i1 = 0
    var i2 = 0
    var i = 0
    while (i < a.size()) {
        if (i2 >= right.size() || (i1 < left.size() && left[i1] < right[i2])) {
            a[i] = left[i1]
            i1++
        } else {
            a[i] = right[i2]
            i2++
        }
        i++
    }
}