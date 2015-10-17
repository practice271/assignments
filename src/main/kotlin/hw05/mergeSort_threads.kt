package hw05

import java.util.*

public fun mergeSort(array: Array<Int>,threadsNumber: Int): Array<Int>{
    var arr = array
    var helper = Array(arr.size(),{i -> 0})

    if (arr.size() <= 1){return arr}
    fun merge(low: Int, middle: Int, high: Int){
        for (i in low..high) {helper[i] = arr[i]}
        var i = low
        var j = middle + 1
        var k = low
        while (i <= middle && j <= high){
            if (helper[i] <= helper[j]){
                arr[k] = helper[i]
                i++
            }
            else{
                arr[k] = helper[j]
                j++
            }
            k++
        }
        while (i <= middle){
            arr[k] = helper[i]
            k++
            i++
        }
    }

    fun mergesort(low: Int, high: Int, threadsNumber: Int){
        if (low < high){
            val middle = low + (high - low)/ 2
            if (threadsNumber == 1){
                mergesort(low,middle,threadsNumber / 2) //left
                mergesort(middle + 1,high, threadsNumber / 2) //right
            }
            else{
                val arrThread = Thread(fun(){mergesort(low,middle,threadsNumber / 2)}) //left
                arrThread.start()
                mergesort(middle + 1,high, threadsNumber / 2) //right
                arrThread.join()
            }
            merge(low,middle,high)
        }
    }
    mergesort(0,array.size() - 1, threadsNumber)
    return arr
}

public fun Array<Int>.print(): String{
    var res = "[ "
    for (i in 0..this.size() - 1){
        res += "${this[i]} "
    }
    res += "]"
    return res
}



