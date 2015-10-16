package homework.hw05

import java.util.ArrayList

/**
 * Hw 05 task 1: a parallel increment without synchronization.
 * Author: Kirill Smirenko, group 271
 * Expected time: 0.5 h
 * Actual time spent: 0.5 h
 */

fun dumbIncremennt(numberTimes : Int, numberThreads : Int) : Int {
    var sum = 0;
    val countPerThread = numberTimes / numberThreads
    val threads = ArrayList<Thread>()
    for (i in 2..numberThreads) {
        threads.add(Thread({
            (1..countPerThread).forEach { i -> sum++ }
        }))
    }
    for (th in threads)
        th.start()
    for (i in 1..(countPerThread + numberTimes % numberThreads))
        sum++
    for (th in threads)
        th.join()
    return sum;
}

fun main(args : Array<String>) {
    println("Demonstrating dumb increment:")
    println("  1000,  1 thread:  ${dumbIncremennt(1000, 1)}")
    println("  1000,  2 threads: ${dumbIncremennt(1000, 2)}")
    println("  1000,  4 threads: ${dumbIncremennt(1000, 4)}")
    println("100000,  8 threads: ${dumbIncremennt(100000, 8)}")
    println("100000, 16 threads: ${dumbIncremennt(100000, 16)}")
}