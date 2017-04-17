package homeworks.hw05

/**
 * Created by Ilya on 17.10.2015.
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

public fun sort(array: Array<Int>, threadNum: Int = 1): Array<Int> {
    if (array.size() == 1) {
        return array
    }

    var workArray = array
    var helper = Array(workArray.size(), {0})

    fun merge(start: Int, end: Int){
        for (i in start..end) {
            helper[i] = workArray[i]
        }
        val middle = (end - start) / 2 + start
        var i = start
        var j = middle + 1
        var k = start

        while (i <= middle && j <= end) {
            if (helper[i] <= helper[j]) {
                workArray[k] = helper[i]
                i++
            } else {
                workArray[k] = helper[j]
                j++
            }
            k++
        }
        while (i <= middle) {
            workArray[k] = helper[i]
            k++
            i++
        }

    }

    fun mergeSort(start: Int, end: Int, threadNum: Int = 1) {

        if (start == end) {
            return
        }
        val middle = (end - start) / 2 + start
        if (threadNum > 1) {
            val threadLeft = thread { mergeSort(start, middle, threadNum / 2) }
            mergeSort(middle + 1, end, threadNum / 2)
            threadLeft.join()
        } else {
            mergeSort(start, middle)
            mergeSort(middle + 1, end)
        }
        merge(start, end)
    }

    mergeSort(0, workArray.size() - 1, threadNum)
    return workArray
}
