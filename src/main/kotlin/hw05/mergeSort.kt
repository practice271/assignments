package hw05

import java.util.ArrayList
import kotlin.concurrent.thread

// It'd be logical to use Array<Int>, but 'take' for Array returns List.
// I can't imagine why it is so, 'cause it makes it inconvenient to work with Arrays.
internal fun merge (first : List<Int>, second : List<Int>) : List<Int>{
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

public fun mergesort(list: List<Int>, threadNum : Int = 1) : List<Int> {
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
            var left = listOf() : List<Int>
              //the value in 'left' is required to compile; it's useless, however, for it'll definitely be overwritten
            var right : List<Int>


            if (threadNum > 1) {
                val threadLeft = thread {left = mergesort(list.take(mid), threadNum / 2)}
                right = mergesort(list.takeLast(size - mid), threadNum / 2)
                threadLeft.join()
            }
            else {
                left  = mergesort(list.take(mid))
                right = mergesort(list.takeLast(size - mid))
            }

            return merge(left, right)
        }
    }
}

internal fun main(args : Array<String>) {
    val l = listOf(1, 43, 12, 231231, 2, 0, -4)
    println("${mergesort(l)}")
}