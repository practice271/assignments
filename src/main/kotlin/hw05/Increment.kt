package hw05

/**
 * Created by Mikhail on 16.10.2015.
 */

public fun parallelIncrement(threadCount: Int): Int{
    var x = 0
    val threadArray = Array(threadCount, {Thread {x++}})
    for (thread in threadArray) thread.start()
    for (thread in threadArray) thread.join()
    return x
}
