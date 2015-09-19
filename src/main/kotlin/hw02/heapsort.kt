/**
 * Created by Alexander Chebykin on 08.09.2015.
 * Estimated time: 1hr. Actual: 2 hrs.
 * Task 1: Heapsort
 */
package hw02
private fun sink (heap : Array<Int>, ind : Int, last : Int) : Array<Int>
{
    var ind = ind
    while (2 * ind + 1 <= last)
    {
        var j = 2 * ind + 1
        if ((j < last) && (heap[j] < heap[j+1]))
            j++
        if (heap[ind] < heap[j])
        {
            val temp = heap[ind]
            heap[ind] = heap[j]
            heap[j] = temp

            ind = j
        }
        else break
    }
    return heap
}

private fun Array<Int>.heapify () : Array<Int>
{
    var cur = lastIndex / 2
    while (cur >= 0)
    {
        if (((cur * 2 + 1 <= lastIndex) && (this[cur * 2 + 1] > this[cur]))
                || ((cur * 2 + 1 < lastIndex) && (this[cur * 2 + 2] > this[cur])))
            sink(this, cur, lastIndex)
        cur--
    }
    return this
}

private fun Array<Int>.sort () : Array<Int>
{
    var last = lastIndex
    while (last > 0)
    {
        val temp = get(0)
        set(0, get(last))
        set(last, temp)

        last--
        sink(this, 0, last)
    }
    return this
}

fun Array<Int>.heapsort() : Array<Int>
{
    return heapify().sort()
}
/*
fun main (args : Array<String>)
{
    val ar = arrayOf(1, -64, 3, 2, 6, 5, 14, 3)
    val res = ar.heapify().sort()
    for (i in res) println("$i")
}
*/