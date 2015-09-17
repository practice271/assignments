// Задания 01-04 от 08.09.2015
// Автор: Кирилл Смиренко, группа 271
package hw01

// 01. Пирамидальная сортировка (heapsort)
fun Array<Int>.heapsort() {
    // form the heap
    for (i in (this.size() - 2) / 2 downTo 0)
        sift(this, i, this.size() - 1)

    for (i in this.size() - 1 downTo 1) {
        val temp = this[0]
        this[0] = this[i]
        this[i] = temp
        sift(this, 0, i - 1)
    }
}

fun sift(array : Array<Int>, start : Int, end : Int) {
    var root = start

    while (root * 2 + 1 <= end) {
        var child = root * 2 + 1 // left child
        var sw = root

        if (array[sw] < array[child])
            sw = child

        if ((child + 1 <= end) && (array[sw] < array[child + 1]))
            sw = child + 1

        if (sw == root)
            return
        else {
            val temp = array[root]
            array[root] = array[sw]
            array[sw] = temp
            root = sw
        }
    }
}