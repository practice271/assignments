package homework.hw05

import java.util.*
import kotlin.concurrent.thread

fun wrongIncrementor(value : Int, threadsNumber : Int ) : Int
{
    var x = 0

    fun inc(value: Int){
        for(i in 0..value - 1) x+=1
    }

    val perThread = value/threadsNumber
    var tail = value % threadsNumber
    val threadArray = Array(threadsNumber, {Thread( { inc(perThread)})} )
    x += tail
    for (item in threadArray) item.start()
    for (item in threadArray) item.join()
    return x
}


fun Array<Int>.parallelMergeSort(threadsNumber: Int) : Array<Int> {
    var arr = Array(this.size(), { 0 })

    fun merge(h1: Pair<Int, Int>, h2: Pair<Int, Int>) {
        for (i in h1.first..h2.second) arr[i] = this[i]

        var i = h1.first
        var j = h1.second + 1
        var k = h1.first

        while (i <= h1.second && j <= h2.second) {
            if (arr[i] <= arr[j]) {
                this[k] = arr[i]
                i++
            } else {
                this[k] = arr[j]
                j++
            }
            k++
        }

        while (i <= h1.second) {
            this[k] = arr[i]
            k++
            i++
        }

    }

    fun sort_in_range(threadsNumber: Int, pair: Pair<Int, Int>) {
        if (pair.second > pair.first ) {

            val middle = pair.first + (pair.second - pair.first) / 2

            if (threadsNumber > 1) {
                val rthread = Thread({ sort_in_range(threadsNumber / 2, Pair(pair.first, middle)) })
                rthread.start()
                sort_in_range(threadsNumber / 2, Pair(middle + 1, pair.second))
                rthread.join()
                merge(Pair(pair.first, middle), Pair(middle + 1, pair.second))
            } else {
                sort_in_range(0, Pair(pair.first, middle))
                sort_in_range(0, Pair(middle + 1, pair.second))
                merge(Pair(pair.first, middle), Pair(middle + 1, pair.second))
            }
        }
    }

    if (threadsNumber >= 1) {
        sort_in_range(threadsNumber, Pair(0, this.size() - 1))
    }
    return this

}

fun Array<Int>.print()
{
    for (i in this) {
        print(i)
        print(",")
    }
}

fun main(args : Array<String>)
{
    val t = Array(1000, {i -> 1000 - i})
    t.print()
    println()
    t.parallelMergeSort(1)
    t.print()
    println(wrongIncrementor(10000, 100))

}