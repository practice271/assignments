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

public fun mergesort(list: List<Int>, l : Int, r : Int, threadNum : Int = 1) : List<Int> {
    val size = r - l + 1 //size of a part being sorted
    when (size) {
        0 -> return emptyList<Int>()
        1 -> return listOf(list[l])
        2    -> {
            //seems easier than writing 'return listOf(Math.min(list[0], list[1]), Math.max(list[0], list[1])'
            if (list[l] > list[r]) {
                return listOf(list[r], list[l])
            }
            return listOf(list[l], list[r])
        }
        else -> {
            val mid   = (r - l) / 2 + l
            var left = listOf() : List<Int>
              //the value in 'left' is required to compile; it's useless, however, for it'll definitely be overwritten
            var right : List<Int>


            if (threadNum > 1) {
                val threadLeft = thread {left = mergesort(list, l, mid, threadNum / 2)}
                right = mergesort(list, mid + 1, r, threadNum / 2)
                threadLeft.join()
            }
            else {
                left  = mergesort(list, l, mid)
                right = mergesort(list, mid + 1, r)
            }

            return merge(left, right)
        }
    }
}