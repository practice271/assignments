package hw05


object MergeSort {

    var helper = Array(0, {0})

    public fun doSort(arr: Array<Int>, threadNum: Int) {
        val len = arr.size()
        helper = Array(len, {0})

        fun doSort_(low: Int, high: Int, arr: Array<Int>, threadNum: Int) {
            if (threadNum <= 1) {
                mergesort(low, high, arr)
                return
            }
            if (arr.size() >= 2) {
                val mid = (low + high) / 2
                val left = Thread { doSort_(low, mid, arr, threadNum / 2) }
                val right = Thread { doSort_(mid + 1, high, arr, threadNum / 2) }

                left.start()
                right.start()
                left.join()
                right.join()

                merge(low, mid, high, arr)
            }
        }

        doSort_(0, len - 1, arr, threadNum)
    }

    private fun mergesort(low: Int, high: Int, arr: Array<Int>) {
        if (low < high) {
            val mid = low + (high - low) / 2
            mergesort(low, mid, arr)
            mergesort(mid + 1, high, arr)
            merge(low, mid, high, arr)
        }
    }

    private fun merge(low: Int, middle: Int, high: Int, arr: Array<Int>) {
        for(i in low..high)
            helper[i] = arr[i]

        var start = low
        var mid = middle + 1
        var i = low
        while(start <= middle && mid <= high) {
            if(helper[start] <= helper[mid]) {
                arr[i] = helper[start]
                start++
            } else {
                arr[i] = helper[mid]
                mid++
            }
            i++
        }
        while(start <= middle) {
            arr[i] = helper[start]
            i++
            start++
        }
    }
}