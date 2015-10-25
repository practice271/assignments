package hw05

public fun mergeSort (countThreads : Int, arr : Array<Int>) : Array<Int>
{
    val resArr = Array(arr.count(), {i -> 0})

    fun merge (left : Int, mid : Int, right : Int)
    {
        var i = 0
        var j = 0

        val lengthLeft = mid - left
        val lengthRight = right - mid + 1

        while (i < lengthLeft && j < lengthRight)
        {
            if (arr[i + left] <= arr[j + mid + 1])
            {
                resArr[left + i + j] = arr[left + i]
                i++
            }
            else
            {
                resArr[left + i + j] = arr[mid + j + 1]
                j++
            }
        }

        while (i < lengthLeft)
        {
            resArr[left + i + j] = arr[left + i]
            i++
        }

        while (j < lengthRight)
        {
            resArr[left + i + j] = arr[mid + 1 + j]
            j++
        }
    }

    fun sort (left : Int, right : Int, countThread : Int)
    {
        if (right - left == 0) return

        val mid = (right - left) / 2

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

        sort(left, mid, countThread)
        sort(mid + 1, right, countThread)

        merge(left, mid, right)
    }

    sort(0, arr.count() - 1, countThreads)

    return resArr
}

