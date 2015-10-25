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

/*
Input: 10000
Output: 9997-10000

Input: 100000
Output: 99994-99998
 */