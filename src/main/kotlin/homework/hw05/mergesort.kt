// Homework 5
// Alekseev Aleksei, group 271.

package homework.hw05

public fun sort(array : Array<Int>, threadNum : Int) {
    var arr : Array<Int> = array
    var temp : Array<Int> = Array(array.size(), {i -> 0})
    fun merge(low : Int, middle : Int, high : Int) {
        for (i in low..high) {temp[i] = arr[i]}
        var i = low
        var j = middle + 1
        var k = low
        while (i <= middle && j <= high) {
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i]
                i++
            }
            else {
                arr[k] = temp[j]
                j++
            }
            k++
        }
        while (i <= middle) {
            arr[k] = temp[i]
            k++
            i++
        }
    }
    fun mergeSort(low : Int, high : Int, threadNum : Int) {
        if (low < high) {
            val middle = low + (high - low) / 2
            if (threadNum > 1) {
                val rThread = Thread(fun() {mergeSort(middle + 1, high, threadNum / 2)})
                rThread.start()
                mergeSort(low, middle, threadNum / 2)
                rThread.join()
            }
            else {
                mergeSort(low, middle, threadNum / 2)
                mergeSort(middle + 1, high, threadNum / 2)
            }
            merge(low, middle, high)
        }
    }
    mergeSort(0, array.size() - 1, threadNum)
}

/*
public fun measureTimeMillis(block : () -> Unit) : Long {
    val start = System.currentTimeMillis()
    block()
    return System.currentTimeMillis() - start
}

fun main (args : Array<String>){
    val array = Array<Int>(1000000, {i -> 999999 - i})
    println("Elapsed Time: " + measureTimeMillis(fun(){sort(array,1)})) //Elapsed Time: 273
    println("Elapsed Time: " + measureTimeMillis(fun(){sort(array,2)})) //Elapsed Time: 257
    println("Elapsed Time: " + measureTimeMillis(fun(){sort(array,3)})) //Elapsed Time: 248
    println("Elapsed Time: " + measureTimeMillis(fun(){sort(array,4)})) //Elapsed Time: 110
}
*/