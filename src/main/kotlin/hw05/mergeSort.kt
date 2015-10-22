package hw05

public fun sort( array: Array<Int>, threadNumber: Int): Array<Int> {
    var arr: Array< Int> = array
    var arr0: Array<Int> = Array(arr.size(), {i -> 0})

    fun merge(low: Int, middle: Int, high: Int) {
        for (i in low..high) {arr0[i] = arr[i]}
        var i: Int = low
        var j: Int = middle + 1
        var k: Int = low
        while (( i<= middle) &&(j <= high)) {
            if (arr0[i] <= arr0[j]) {
                arr[k] = arr0[i]
                i++
            }
            else {
                arr[k] = arr0[j]
                j++
            }
            k++
        }
        while (i<= middle) {
            arr[k] = arr0[i]
            k++
            i++
        }
    }

    fun mergeSort(low: Int, high: Int, threadsNumber: Int){
        if (low < high) {
            var middle = low + (high - low)/2
            if(threadsNumber > 1) {
                val arrayThread = Thread(fun(){mergeSort(low, middle, threadsNumber/2)})
                arrayThread.start()
                mergeSort(middle + 1, high, threadsNumber/2)
                arrayThread.join()

            }
            else {
                mergeSort(low, middle, threadsNumber/2)
                mergeSort(middle + 1, high, threadsNumber/2)

            }
            merge(low, middle, high)
        }
    }
    mergeSort(0, array.size() - 1, threadNumber)
    return arr
}
