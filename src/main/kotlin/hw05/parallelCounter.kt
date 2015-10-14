package hw05

import kotlin.concurrent.thread
import kotlin.synchronized

internal fun parallelCounterWrong(threadCount : Int) : Int{
    var x = 0
    val threadArray = Array(threadCount, {thread {x++}})

    for (thread in threadArray)
        thread.join()

    return x
}

internal fun parallelCounter(threadCount : Int) : Int{
    var x = 0
    val lock = Object()
    val threadArray = Array(threadCount, {thread {synchronized(lock, {x++})}})
    for (thread in threadArray)
        thread.join()

    return x
}

//internal fun main(args : Array<String>) {
//    println("${parallelCounterWrong(10000)}")
//    println("${parallelCounterWrong(10000)}")
//    println("${parallelCounterWrong(10000)}")
//    println("${parallelCounterWrong(10000)}")
//    println("${parallelCounterWrong(10000)}")
//
//    println("${parallelCounter(10000)}")
//    println("${parallelCounter(10000)}")
//    println("${parallelCounter(10000)}")
//    println("${parallelCounter(10000)}")
//    println("${parallelCounter(10000)}")
//}
