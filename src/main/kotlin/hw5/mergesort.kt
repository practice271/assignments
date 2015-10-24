package hw5

import java.util.*
import kotlin.concurrent.thread

fun <T: Comparable<T>>Array<T>.mergesort() {
    sort(this, 0, this.size - 1)
}

fun <T: Comparable<T>>sort(a: Array<T>, lo: Int, hi: Int) {
    if (lo < hi) {
        val mid = (lo + hi) / 2
        val t = thread(true) { sort(a, lo, mid) }
        sort(a, mid + 1, hi)
        t.join()
        merge(a, lo, mid, hi)
    }
}

fun <T: Comparable<T>>merge(a: Array<T>, lo: Int, mid: Int, hi: Int) {
    val b = ArrayList<T>()
    var i = lo
    var j = mid + 1
    while (i <= mid || j <= hi) {
        when {
            i > mid -> b.add(a[j++])
            j > hi -> b.add(a[i++])
            a[i] < a[j] -> b.add(a[i++])
            else -> b.add(a[j++])
        }
    }
    i = lo
    b.forEach { t -> a[i++] = t }
}