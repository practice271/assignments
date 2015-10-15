package hw05

public fun mergeSort (countThreads : Int, arr : Array<Int>) : Array<Int>
{
    fun merge (leftArr : Array<Int>, rightArr : Array<Int>) : Array<Int>
    {
        var i = 0
        var j = 0

        val lengthLeft = leftArr.count()
        val lengthRight = rightArr.count()

        val resArr = Array(lengthLeft + lengthRight, {i -> 0})

        while (i < lengthLeft && j < lengthRight)
        {
            if (leftArr[i] <= rightArr[j])
            {
                resArr[i + j] = leftArr[i]
                i++
            }
            else
            {
                resArr[i + j] = rightArr[j]
                j++
            }
        }

        while (i < lengthLeft)
        {
            resArr[i + j] = leftArr[i]
            i++
        }

        while (j < lengthRight)
        {
            resArr[i + j] = rightArr[j]
            j++
        }

        return resArr
    }

    fun sort (baseArr : Array<Int>, countThread : Int) : Array<Int>
    {
        if (baseArr.count() == 1) return baseArr

        val mid = baseArr.count() / 2
        var leftArr = Array (mid, {i -> 0})
        var rightArr = Array (baseArr.count() - mid, {i -> 0})

        for (i in 0..(mid - 1))
            leftArr[i] = baseArr[i]
        for (i in mid..(baseArr.count() - 1))
            rightArr[i - mid] = baseArr[i]

        if (countThread > 1)
        {
            val thr = countThread / 2
            val lThread = Thread {leftArr = sort(leftArr, thr)}
            val rThread = Thread {rightArr = sort(rightArr, countThread - thr)}

            lThread.start()
            rThread.start()

            lThread.join()
            rThread.join()

            return merge(leftArr, rightArr)
        }

        leftArr = sort(leftArr, countThread)
        rightArr = sort(rightArr, countThread)

        return merge(leftArr, rightArr)
    }

    return sort(arr, countThreads)
}
