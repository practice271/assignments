package hw05

import java.util.ArrayList

internal class  mergeSortThread<A : Comparable<A>>(
        private val arr: ArrayList<A>, private val resArray: ArrayList<A>, private val left: Int,
        private val right: Int, private val lock : Object) : Runnable {

    override public fun run() {
        if (arr.isEmpty()) return
        val sortedArr = merge(arr, left, right)
        synchronized(lock) {
            val mergedArray = (mergeSortedArrays(sortedArr, resArray))
            resArray.clear()
            resArray.addAll(mergedArray)
        }
    }

    internal fun <A : Comparable<A>> merge(arr: ArrayList<A>, left: Int, right: Int): ArrayList<A> {
        val length = right - left + 1
        if (length == 1) return ArrayList<A>(listOf(arr[left]))
        val leftArr = merge(arr, left, left + length / 2 - 1)
        val rightArr = merge(arr, left + length / 2, left + length - 1)
        return mergeSortedArrays(leftArr, rightArr)
    }

    internal fun <A : Comparable<A>> mergeSortedArrays(leftArr: ArrayList<A>, rightArr: ArrayList<A>): ArrayList<A> {
        val lengthLeftArr = leftArr.size()
        val lengthRightArr = rightArr.size()
        var res = ArrayList<A>(lengthLeftArr + lengthRightArr)
        var l = 0
        var r = 0
        while (l < lengthLeftArr && r < lengthRightArr) {
            if (leftArr[l] < rightArr[r]) res.add (leftArr[l++])
            else res.add(rightArr[r++])
        }
        if (l < lengthLeftArr) for (i in l..lengthLeftArr - 1) res.add(leftArr[i])
        if (r < lengthRightArr) for (i in r..lengthRightArr - 1) res.add(rightArr[i])
        return res
    }
}

public fun  <A : Comparable<A>> parallelMergeSort(arr : ArrayList<A>, threads : Int) : ArrayList<A> {
    val length = arr.size()
    if (length == 0) return arr
    val threadNumber = Math.min(threads, length)
    val intervalPerThread = length / threadNumber
    var resArray = ArrayList<A>()
    val lock = Object()
    val threadArray = Array(threadNumber, { i ->
        val fromIndex = i * intervalPerThread
        val toIndex = if (i != threadNumber - 1) (i + 1) * intervalPerThread - 1 else length - 1
        val thread = mergeSortThread(arr, resArray, fromIndex, toIndex, lock)
        Thread(thread)
    })
    for (thread in threadArray) thread.start()
    for (thread in threadArray) thread.join()
    return resArray
}