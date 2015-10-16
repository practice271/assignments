package homework.hw05

import java.util.*

/**
 * Hw 05 task 2: a parallel mergesort.
 * Author: Kirill Smirenko, group 271
 * Expected time: 1 h
 * Actual time: 1.66 h
 */

/**
 * Sorts [arr] via MergeSort algorythm using up to [maxThreadsNumber] threads.
 */
fun mergeSort(arr : Array<Int>, maxThreadsNumber : Int) {
    /**
     * Sorts a part of [arr] from [begin] to ([end] - 1).
     * @param curThreadsNumber Number of threads currently used.
     */
    fun splitMergeConcurrent(begin : Int, end : Int, curThreadsNumber : Int) {
        // parts of length 1 are considered sorted
        if (end - begin < 2) return
        // splitting the array part into two halves and sorting them
        val middle = (end + begin) / 2
        if (curThreadsNumber < maxThreadsNumber) {
            // sorting left half asynchronously
            val th = Thread({ splitMergeConcurrent(begin, middle, curThreadsNumber + 1) })
            th.start()
            splitMergeConcurrent(middle, end, curThreadsNumber + 1)
            th.join()
        } else {
            // sorting left half synchronously
            splitMergeConcurrent(begin, middle, curThreadsNumber + 1)
            splitMergeConcurrent(middle, end, curThreadsNumber + 1)
        }
        // merging the two halves
        val stack = Stack<Int>()
        var pos1 = begin
        var pos2 = middle
        (begin..(end - 1)).forEach {
            // If left run head exists and is <= existing right run head.
            if (pos1 < middle && (pos2 >= end || arr[pos1] <= arr[pos2])) {
                stack.push(arr[pos1])
                pos1++
            } else {
                stack.push(arr[pos2])
                pos2++
            }
        }
        for (i in (end - 1) downTo begin) {
            arr[i] = stack.pop()
        }
    }

    splitMergeConcurrent(0, arr.size(), 1)
}