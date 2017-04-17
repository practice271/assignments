/*
 * Homework 5 (13.10.2015)
 * Work with threads
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw05

import java.util.*
import kotlin.synchronized

/** Parallel incrementing. */
class ParallelIncrement(private val threadsNumber : Int, private val maxValue : Int) {

    /** Wrapper for reference. */
    private class Ref(private var value : Int) {
        public fun get() : Int {
            return value
        }

        public fun set(newValue : Int) {
            value = newValue
        }
    }

    /** A parallel increment without synchronisation. */
    private class Bad_Increment(private val value : Ref, private val step : Int) : Thread() {
        public override fun run() {
            value.set(value.get() + step)
        }
    }

    /** A parallel increment with synchronisation. */
    private class Increment(private val value : Ref, private val step : Int) : Thread() {
        public override fun run() {
            synchronized(value) {
                value.set(value.get() + step)
            }
        }
    }

    private var threadArray = Array(0, { i -> Thread()})
    private val step        = maxValue / threadsNumber
    private var result      = Ref(maxValue - step * threadsNumber)

    private fun start() : Int {
        for (i in threadArray) i.start()
        for (i in threadArray) i.join()
        return result.get()
    }

    public fun increment() : Int {
        threadArray = Array(threadsNumber, { i -> Increment(result, step) })
        return start()
    }

    public fun badIncrement() : Int {
        threadArray = Array(threadsNumber, { i -> Bad_Increment(result, step) })
        return start()
    }

    public fun clear() {
        result = Ref(maxValue - step * threadsNumber)
    }
}


/** A parallel merge sort. */
fun Array<Int>.mergeSort(threadsNumber : Int) {

    /** Merges two parts of array. */
    fun merge(start1 : Int, start2 : Int, n : Int) {
        var p1     = start1
        var p2     = start2
        val result = Stack<Int>()

        for (i in 0 .. n - 1) {
            if (p2 >= start1 + n || (this[p1] < this[p2] && p1 < start2)) {
                result.push(this[p1])
                p1++
            }
            else {
                result.push(this[p2])
                p2++
            }
        }

        var i = n - 1 + start1
        while (!result.isEmpty()) {
            this[i] = result.pop()
            i--
        }
    }

    /** Sorts array in given range. */
    fun sortInRange(left : Int, right : Int) {
        val n   = right - left + 1
        var pow = 2

        while (pow <= n) {
            val m = n / pow

            for (i in 0 .. m - 1)
                merge(i * pow + left, i * pow + pow / 2 + left,  pow)

            // if there are some elements, which weren't merged
            if (m * pow + left <= right)
                merge((m - 1) * pow + left, m * pow + left, pow + n - m * pow)
            pow *= 2
        }
    }

    private class Sort(private val step : Int, private val i : Int) : Thread() {
        public override fun run() {
            sortInRange(i * step, (i + 1) * step - 1)
        }
    }

    val n    = this.size()
    val step = n / threadsNumber

    val threadArray = Array<Thread>(threadsNumber, { i ->
        Sort(step, i)
    })

    for (i in threadArray) i.start()
    for (i in threadArray) i.join()

    for (i in 0 .. threadsNumber - 2)
        merge(0, (i + 1) * step, (i + 2) * step)

    if (threadsNumber * step < n) {
        sortInRange(threadsNumber * step, n - 1)
        merge(0, threadsNumber * step, n)
    }
}

fun main (args : Array<String>) {
    val inc = ParallelIncrement(10000, 10000)
    println(inc.increment()) // always prints 10000

    inc.clear()
    println(inc.badIncrement())
}