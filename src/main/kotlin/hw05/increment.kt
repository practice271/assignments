package hw05

public fun incrementWithoutSynchronization(countThreads: Int): Int {
    var x = 0
    val threadArray = Array(countThreads, {Thread{x++}})
    for (thread in threadArray) thread.start()
    for (thread in threadArray) thread.join()
    return x
}

fun main(args: Array<String>) {
    println(incrementWithoutSynchronization(1000))
}

