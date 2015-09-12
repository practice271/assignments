fun main(args: Array<String>) {
    val a = Array(10, {i -> (0)})
    a[0] = 8
    a[1] = 1
    a[2] = 4
    a[3] = 6
    a[4] = 7
    a[5] = 2
    a[6] = 9
    a[7] = 10
    a[8] = 3
    a[9] = 5
    heapSort(a)
    printArray(a)
}

fun printArray(a: Array<Int>) {
    for (v in a) {
        print(v.toString() + " ")
    }
    println()
}

fun heapSort(a: Array<Int>) {
    var sz = a.size()

    fun swap(i: Int, j: Int) {
        var t = a[i]
        a[i] = a[j]
        a[j] = t
    }

    fun heapify(a: Array<Int>, i: Int) {
        fun fix(i : Int, j: Int) = when {
            j >= sz -> i
            else -> j
        }

        fun left(i: Int) = fix(i, i * 2 + 1)
        fun right(i: Int) = fix(i, i * 2 + 2)
        fun max(i: Int, j: Int) = if (a[i] > a[j]) i else j

        val m = max(max(left(i), right(i)), i)
        if (m != i) {
            swap(i, m)
            heapify(a, m)
        }
    }

    fun buildHeap() {
        for (i in a.size() - 1 downTo 0) heapify(a, i)
    }

    buildHeap()
    for (i in a.size() - 1 downTo 0) {
        swap(0, i)
        sz--
        heapify(a, 0)
    }
}
