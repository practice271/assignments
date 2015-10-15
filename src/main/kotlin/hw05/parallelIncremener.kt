package hw05

public class WrappedInteger(public var value : Int)

fun notSynchronizedParallelIncrement(incNumber : Int, threadNumber : Int) : Int {
    class incThread(private val number: Int, private var numberToBeIncreased: WrappedInteger) : Runnable {
        override public fun run() {
            for (i in 1..number) numberToBeIncreased.value++
        }
    }

    if (incNumber < 0) throw RuntimeException("Incrementing number cannot be smaller than zero")
    if (threadNumber <= 0) throw RuntimeException("Number of threads must be greater than zero")
    val numberPerThread = incNumber / threadNumber
    var result = WrappedInteger(0)
    val threadArray = Array(threadNumber, { i ->
        val number = if (i != threadNumber - 1) numberPerThread else incNumber - numberPerThread * (threadNumber - 1)
        val thread = incThread(number, result)
        Thread(thread)
    })
    for (thread in threadArray) thread.start()
    for (thread in threadArray) thread.join()
    return result.value
}