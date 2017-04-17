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

public fun sorting(arr:Array<Int>,  numberOfThreads: Int): Array<Int> {
    var tmpArray = Array(arr.size(), {i -> 0})
    fun mergeSort(l: Int, r: Int, numberOfThreads: Int) {
        val size = r - l + 1
        val middle = l + size / 2
        if (size >= 2) {
            if (numberOfThreads > 1) {
                val threadl = thread { mergeSort(l, middle - 1, numberOfThreads / 2) }
                threadl.join()
                mergeSort(middle, r, numberOfThreads / 2)
            }
            else {
                mergeSort(l, middle - 1, numberOfThreads)
                mergeSort(middle, r, numberOfThreads)
            }
            var i = l
            var j = middle
            while ((i < middle) && (j <= r)) {
                when {
                    arr[i] <= arr[j] -> {
                        tmpArray[i + j - middle] = arr[i]; i++
                    }
                    arr[i] > arr[j] -> {
                        tmpArray[i + j - middle] = arr[j]; j++
                    }
                }
            }
            if (i < middle) {
                for (x in i..middle - 1) {
                    tmpArray[i + j - middle] = arr[x]
                    i++
                }
            }
            if (j <= r) {
                for (x in j..r) {
                    tmpArray[i + j - middle] = arr[x]
                    j++
                }
            }
            for (x in l..r){
                arr[x] = tmpArray[x]
            }
        }
    }
    mergeSort(0, arr.size() - 1, numberOfThreads )
    return arr
}

private fun duration(size: Int, threadNum:Int):Long {
    val arr = Array(size, {i -> Random().nextInt()})
    return measureTimeMillis {sorting(arr, threadNum)  }
}

/*
Threads: 1	 Size: 10	     Time: 3
Threads: 2	 Size: 10	     Time: 4
Threads: 4	 Size: 10	     Time: 1
Threads: 8	 Size: 10	     Time: 2

Threads: 1	 Size: 100	     Time: 0
Threads: 2	 Size: 100	     Time: 3
Threads: 4	 Size: 100	     Time: 5
Threads: 8	 Size: 100	     Time: 5

Threads: 1	 Size: 1000	     Time: 0
Threads: 2	 Size: 1000	     Time: 7
Threads: 4	 Size: 1000	     Time: 2
Threads: 8	 Size: 1000	     Time: 7

Threads: 1	 Size: 10000	 Time: 7
Threads: 2	 Size: 10000	 Time: 4
Threads: 4	 Size: 10000	 Time: 5
Threads: 8	 Size: 10000	 Time: 6

Threads: 1	 Size: 100000	 Time: 57
Threads: 2	 Size: 100000	 Time: 66
Threads: 4	 Size: 100000	 Time: 70
Threads: 8	 Size: 100000	 Time: 88

Threads: 1	 Size: 1000000	 Time: 752
Threads: 2	 Size: 1000000	 Time: 589
Threads: 4	 Size: 1000000	 Time: 480
Threads: 8	 Size: 1000000	 Time: 500
*/
