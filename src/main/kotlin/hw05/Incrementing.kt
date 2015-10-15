package hw05

public fun badIncrement(countThreads : Int) : Int
{
    var x = 0

    val threadArray = Array (countThreads,{ Thread {x++}} )

    for (thread in threadArray)
        thread.start()
    for (thread in threadArray)
        thread.join()

    return x
}

fun main (args : Array<String>)
{
    println (badIncrement(10000))
}