fun main(args: Array<String>) {
    val a = arrayOf(8, 1, 4, 6, 7, 2, 9, 10, 3, 5)
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
