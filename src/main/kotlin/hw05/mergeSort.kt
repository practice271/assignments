package hw05

import java.util.*
import kotlin.concurrent.thread

internal fun merge (first : List<Int>, second : List<Int>) : List<Int> {
    val res = arrayListOf() : ArrayList<Int>
    var i = 0
    var j = 0
    while (i < first.size() && j < second.size()) {
        if (first[i] < second[j]) {
            res.add(first[i])
            i++
        }
        else {
            res.add(second[j])
            j++
        }
    }
    while (i < first.size()) {
        res.add(first[i])
        i++
    }
    while (j < second.size()) {
        res.add(second[j])
        j++
    }
    return res
}

public fun mergesort(list: List<Int>) : List<Int> {
    val size = list.size()
    when (size) {
        0, 1 -> return list
        2    -> {
            if (list[0] > list[1]) {
                return listOf(list[1], list[0])
            }
            return listOf(list[0], list[1])
        }
        else -> {
            val mid   = size / 2
         //   val threadLeft = thread {mergesort(list.take(mid))}
            val left  = mergesort(list.take(mid))
            val right = mergesort(list.takeLast(size - mid))
            return merge(left, right)
        }
    }
}

internal fun main(args : Array<String>) {
    val l = listOf<Int>(1, 43, 12, 231231, 2, 0, -4)
    println("${mergesort(l)}")
}
