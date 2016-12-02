package hw05

import kotlin.concurrent.thread
import kotlin.synchronized

public fun parallelCounterWrong(threadCount : Int) : Int{
    var x = 0
    val threadArray = Array(threadCount, {thread {x++}})

    for (thread in threadArray)
        thread.join()

    return x
}

// After I wrote function below, I realised that it can't possibly be parallel. Well, let it be.
public  fun parallelCounter(threadCount : Int) : Int{
    var x = 0
    val lock = Object()
    val threadArray = Array(threadCount, {thread {synchronized(lock, {x++})}})
    for (thread in threadArray)
        thread.join()

    return x
}
