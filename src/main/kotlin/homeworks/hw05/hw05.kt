package homeworks.hw05

/**
 * Created by Илья on 17.10.2015.
 */

import kotlin.concurrent.thread

public fun badIncrement(threadCount: Int): Int {
    var x = 0
    val threadArray = Array(threadCount, {thread {x++}})
    for (thread in threadArray) {
        thread.join()
    }
    return x
}

public class MergeSort {

    private fun merge(arrayFirst: Array<Int>, arraySecond: Array<Int>): Array<Int> {
        var i = 0 // go to the first array
        var j = 0 // go to the second array
        val length = arrayFirst.size() + arraySecond.size()
        val merged: Array<Int> = Array(length, { i -> 0 })
        /*
        for (i in 0..length - 1) {
            if (b < arrayRight.size() && a < arrayLeft.size()) {
                if (arrayLeft[a] > arrayRight[b] && b < arrayRight.size()) {
                    merged[i] = arrayRight[b++]
                }
                else {
                    merged[i] = arrayLeft[a++]
                }
            }
            else {
                if (b < arrayRight.size()) {
                    merged[i] = arrayRight[b++]
                }
                else {
                    merged[i] = arrayLeft[a++]
                }
            }
        }*/
        //It may be better
        for (k in 0..length - 1) {
            if (i < arrayFirst.size() && j < arraySecond.size())
                if (arrayFirst[i] > arraySecond[j] && j < arraySecond.size())
                    merged[k] = arraySecond[j++]
                else
                    merged[k] = arrayFirst[i++]
            else
                if (j < arraySecond.size())
                    merged[k] = arraySecond[j++]
                else
                    merged[k] = arrayFirst[i++]
        }
        return merged
    }

    public fun sort(array: Array<Int>, threadNum: Int = 1): Array<Int> {
        if (array.size() == 1) {
            return array
        }
        return mergeSort(array, 0, array.size() - 1, threadNum)
    }

    private fun mergeSort(array: Array<Int>, leftInt: Int, rightInt: Int, threadNum: Int = 1): Array<Int> {
        if (leftInt == rightInt) {
            return arrayOf(array[leftInt])
        }

        val middle = (rightInt - leftInt) / 2 + leftInt//+ 1
        var leftArray = Array(middle, { 0 })
        var rightArray: Array<Int>

        if (threadNum > 1) {
            val threadLeft = thread { leftArray = mergeSort(array, leftInt, middle, threadNum / 2) }
            rightArray = mergeSort(array, middle + 1, rightInt, threadNum / 2)
            threadLeft.join()
        } else {
            leftArray = mergeSort(array, leftInt, middle)
            rightArray = mergeSort(array, middle + 1, rightInt)
        }
        return merge(leftArray, rightArray)
    }
}
