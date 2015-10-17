/* A parallel increment without synchronization,
a parallel merge sort
(expectation: 7 h; reality: 6 h)
by Sokolova Polina */

package hw05

public fun increment (a : Int, b : Int, threadNumber : Int) : Int {
    var newA = a
    val threadArray = Array(threadNumber, { _ -> Thread(fun() { newA += b }) })
    for (t in threadArray) { t.start() }
    for (t in threadArray) { t.join()  }
    return newA
}

public fun mergeSort(a : Array<Int>, threadNumber: Int) : Array<Int> {
    //val startTime = System.currentTimeMillis();
    if (threadNumber == 0) { return a }
    val maxThreadNumber = Math.min(a.size(), threadNumber) - 1
    val currentThread = 0
    var threadArray = Array(maxThreadNumber + 1, { })
    fun merge(a : Array<Int>, start : Int, middle : Int, end : Int) {
        var leftArray = a.copyOfRange(start, middle + 1);
        var rightArray = a.copyOfRange(middle + 1, end + 1);
        var i = 0;
        var j = 0;
        for (k in start..end) {
            if (i <= leftArray.size() - 1 && (j >= rightArray.size() ||
                    leftArray[i] <= rightArray[j])) {
                a[k] = leftArray[i];
                i++;
            }
            else {
                a[k] = rightArray[j];
                j++;
            }
        }
    }
    fun sort(a : Array<Int>, start : Int, end : Int, currentT : Int) {
        var size = end - start + 1
        var res = a
        if (size == 1) { return }
        var middle = (start + end) / 2
        var threadRemainder = maxThreadNumber - currentT
        when {
            threadRemainder >= 3 -> {
                threadArray[currentT + 1] = sort(a, start, middle, currentT + 1)
                threadArray[currentT + 2] = sort(a, middle + 1, end, currentT + 2)
                threadArray[currentT + 3] = merge(res, start, middle, end)
            }
            threadRemainder == 2 -> {
                threadArray[currentT + 1] = sort(a, start, middle, currentT + 1)
                threadArray[currentT + 2] = sort(a, middle + 1, end, currentT + 2)
                merge(res, start, middle, end)
            }
            threadRemainder == 1 -> {
                threadArray[currentT + 1] = sort(a, start, middle, currentT + 1)
                sort(a, middle + 1, end, currentT)
                merge(res, start, middle, end)
            }
            else -> {
                sort(a, start, middle, currentT)
                sort(a, middle + 1, end, currentT)
                merge(res, start, middle, end)}
        }
    }
    threadArray[currentThread] = sort(a, 0, a.size() - 1, currentThread)
    //val timeSpent = System.currentTimeMillis() - startTime
    //println("Size: ${a.size()}, Threads: $threadNumber, Time: $timeSpent мс")
    return a
}

public fun Array<Int>.arrayToString() : String {
    var res = ""
    for (i in 0..this.size() - 1) { res += "${this[i]} " }
    return res
}