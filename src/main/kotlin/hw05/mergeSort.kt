package hw05

public fun mergeSort (countThreads : Int, arr : Array<Int>) : Array<Int>
{

    fun merge (left : Int, mid : Int, right : Int)
    {
        val resArr = Array(right - left + 1, {i -> 0})

        var i = 0
        var j = 0

        val lengthLeft = mid - left + 1
        val lengthRight = right - mid

        while (i < lengthLeft && j < lengthRight)
        {
            if (arr[left + i] <= arr[j + mid + 1])
            {
                resArr[i + j] = arr[left + i]
                i++
            }
            else
            {
                resArr[i + j] = arr[mid + j + 1]
                j++
            }
        }

        while (i < lengthLeft)
        {
            resArr[i + j] = arr[left + i]
            i++
        }

        while (j < lengthRight)
        {
            resArr[i + j] = arr[mid + 1 + j]
            j++
        }

        for (i in 0..resArr.count() - 1)
            arr[left + i] = resArr[i]
    }

    fun sort (left : Int, right : Int, countThread : Int)
    {
        if (right - left == 0) return

        val mid = left + (right - left) / 2

        if (countThread > 1)
        {
            val thr = countThread / 2
            val lThread = Thread {sort(left, mid, thr)}
            val rThread = Thread {sort(mid + 1, right, countThread - thr)}

            lThread.start()
            rThread.start()

            lThread.join()
            rThread.join()

            merge(left, mid, right)
            return
        }

        sort(left,  mid, countThread)
        sort(mid + 1, right, countThread)

        merge(left, mid, right)
    }

    sort(0, arr.count() - 1, countThreads)

    return arr
}

fun main (args : Array<String>)
{
    var arr = arrayOf(9, 6, 1, 5, 10, 3, 7, 4, 2, 8)
    arr = mergeSort(8, arr)
    for (i in 0..(arr.count() - 1))
        println(arr[i])
}

