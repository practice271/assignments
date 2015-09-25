package hw02


/**
 * Created by Zarina Kurbatova on 11/09/15.
 */

fun heapSort(arr: Array<Int>) {
    for(i in arr.size()/2 downTo 0) {
        pushDown(arr,i, arr.size())
    }

    for (i in arr.size() - 1 downTo 1) {
        swap(arr, 0, i)
        pushDown(arr, 0, i)
    }
}

fun pushDown(arr: Array<Int>, start: Int, last: Int) {
    var r  = start
    var check = true

    while ((r * 2 + 1 < last) && (check)) {
        var child = r * 2 + 1

        if ((child + 1 != last)&&(arr[child] <= arr[child+1])) {
            child++
        }

        if (arr[child] > arr[r]) {
            swap(arr, r, child)
            r=child
        }
        else {
            check = false
        }
    }
}

fun swap(arr: Array<Int>, ind1: Int, ind2: Int) {
    var tmp: Int = arr[ind1]
    arr[ind1] = arr[ind2]
    arr[ind2] = tmp
}

/*fun main(args: Array<String>) {

    fun Array<out Any>.printArray() {
        this.forEach {
            print("$it ")
        }
        println()
    }

    val array = arrayOf(4, 1, 3, 2, 43, 12, 78)

    array.printArray()
    heapSort(array)
    array.printArray()
}*/
