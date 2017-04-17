fun Array<out Any>.printArray() {
    this.forEach {
        print("$it ")
    }
    println()
}

fun Heapify(arr:Array<Int>, i:Int, heapSize:Int) {
    var max = Int.MIN_VALUE
    if (2 * i + 1 <= heapSize && arr[2*i + 1]>arr[i]) {
        max = 2 * i + 1
    }
    else {
        max = i
    }
    if (2 * i + 2 <= heapSize && arr[2*i+2]>arr[max]) {
        max = 2 * i + 2
    }
    if (max!=i) {
        var el = arr[max]
        arr[max]  = arr[i]
        arr[i] = el
        Heapify (arr, max, heapSize)
    }
}

fun HeapSort (arr:Array<Int>) {
    var size = arr.size() - 1
    for (i in 0..size) {
        Heapify (arr, size - i, size)
    }
    //arr.printArray()
    for (i in 1..arr.size()- 1) {
        var el = arr[arr.size()  - i]
        arr[arr.size()  - i] = arr[0]
        arr[0] = el
        size--
        Heapify(arr, 0, size)
    }
}



fun main(args: Array<String>) {
    var arr = arrayOf(3,1,0,-2,1,-2,-555,220,11,75,0,11111,-5221,7,125,-66)
    HeapSort(arr)
    arr.printArray() // Output: -5221 -555 -66 -2 -2 0 0 1 1 3 7 11 75 125 220 11111
}
