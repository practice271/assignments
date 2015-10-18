package homework5

import java.util.*
import kotlin.util.measureTimeMillis
import kotlin.concurrent.thread

public fun incWithoutSync(numberOfThreads : Int) : Int{
    var count = 0
    val threadArray = Array(numberOfThreads, {thread {count++}})
    threadArray.forEach {
        it.join()
    }
    return count
}


public fun mergeSort(arr:Array<Int>, numberOfThreads: Int): Array<Int> {
        val size = arr.size()
        when {
            (size < 2) -> return arr
            else -> {
                val middle = size / 2
                var left = Array(middle, { _ -> 0 })
                var right = Array(size - middle, { _ -> 0 })
                for (x in 0..middle - 1) {
                    left[x] = arr[x]
                }
                for (x in middle..size - 1) {
                    right[x - middle] = arr[x]
                }
                if (numberOfThreads > 1) {
                    val threadl = thread { left = mergeSort(left, numberOfThreads - 1)}
                    threadl.join()
                    right = mergeSort(right, numberOfThreads - 1)
                }
                else {
                    left = mergeSort(left, numberOfThreads)
                    right = mergeSort(right, numberOfThreads)
                }
                var result = merge(left, right)
                return result
            }
        }
}
internal  fun merge(left: Array<Int>, right: Array<Int>): Array<Int>{
    var result = Array(left.size() + right.size(), {_ -> 0})
    var l = 0
    var r = 0
    while ((l < left.size()) && (r < right.size())) {
        when {
            left[l] <= right[r] -> {result[l + r] = left[l]; l++}
            left[l] > right[r] -> {result[l + r] = right[r]; r++}
        }
    }
    if (l < left.size()) {
        for (x in l..left.size() - 1) {
            result[l + r] = left[x]
            l++
        }
    }
    if (r < right.size()) {
        for (x in r..right.size() - 1) {
            result[l + r] = right[x]
            r++
        }
    }
    return result
}

private fun duration(size: Int, threadNum:Int):Long {
    val arr = Array(size, {_ -> Random().nextInt()})
    return measureTimeMillis {mergeSort(arr, threadNum)  }
}

/*
Threads: 1	 Size: 10	     Time: 1
Threads: 1	 Size: 100	     Time: 1
Threads: 1	 Size: 1000	     Time: 6
Threads: 1	 Size: 10000	 Time: 25
Threads: 1	 Size: 100000	 Time: 163
Threads: 1	 Size: 1000000	 Time: 1082

Threads: 2	 Size: 10	     Time: 0
Threads: 2	 Size: 100	     Time: 3
Threads: 2	 Size: 1000	     Time: 12
Threads: 2	 Size: 10000	 Time: 11
Threads: 2	 Size: 100000	 Time: 150
Threads: 2	 Size: 1000000	 Time: 1035

Threads: 4	 Size: 10	     Time: 3
Threads: 4	 Size: 100	     Time: 5
Threads: 4	 Size: 1000	     Time: 35
Threads: 4	 Size: 10000	 Time: 15
Threads: 4	 Size: 100000	 Time: 138
Threads: 4	 Size: 1000000	 Time: 754

Threads: 6	 Size: 10	     Time: 2
Threads: 6	 Size: 100	     Time: 8
Threads: 6	 Size: 1000	     Time: 39
Threads: 6	 Size: 10000	 Time: 71
Threads: 6	 Size: 100000	 Time: 79
Threads: 6	 Size: 1000000	 Time: 774
 */
