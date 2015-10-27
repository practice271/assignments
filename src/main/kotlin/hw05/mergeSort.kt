package hw05

public fun mergeSort (countThreads : Int, arr : Array<Int>) : Array<Int>
{
<<<<<<< HEAD

    fun merge (left : Int, mid : Int, right : Int)
=======
    fun merge (leftArr : Array<Int>, rightArr : Array<Int>) : Array<Int>
>>>>>>> parent of 603807e... - Maked changes in mergeSort. But I can't test.
    {
        val resArr = Array(right - left + 1, {i -> 0})

        var i = 0
        var j = 0

<<<<<<< HEAD
        val lengthLeft = mid - left + 1
        val lengthRight = right - mid

        while (i < lengthLeft && j < lengthRight)
        {
            if (arr[left + i] <= arr[j + mid + 1])
            {
                resArr[i + j] = arr[left + i]
=======
        val lengthLeft = leftArr.count()
        val lengthRight = rightArr.count()

        val resArr = Array(lengthLeft + lengthRight, {i -> 0})

        while (i < lengthLeft && j < lengthRight)
        {
            if (leftArr[i] <= rightArr[j])
            {
                resArr[i + j] = leftArr[i]
>>>>>>> parent of 603807e... - Maked changes in mergeSort. But I can't test.
                i++
            }
            else
            {
<<<<<<< HEAD
                resArr[i + j] = arr[mid + j + 1]
=======
                resArr[i + j] = rightArr[j]
>>>>>>> parent of 603807e... - Maked changes in mergeSort. But I can't test.
                j++
            }
        }

        while (i < lengthLeft)
        {
<<<<<<< HEAD
            resArr[i + j] = arr[left + i]
=======
            resArr[i + j] = leftArr[i]
>>>>>>> parent of 603807e... - Maked changes in mergeSort. But I can't test.
            i++
        }

        while (j < lengthRight)
        {
<<<<<<< HEAD
            resArr[i + j] = arr[mid + 1 + j]
            j++
        }

        for (i in 0..resArr.count() - 1)
            arr[left + i] = resArr[i]
=======
            resArr[i + j] = rightArr[j]
            j++
        }

        return resArr
>>>>>>> parent of 603807e... - Maked changes in mergeSort. But I can't test.
    }

    fun sort (baseArr : Array<Int>, countThread : Int) : Array<Int>
    {
        if (baseArr.count() == 1) return baseArr

<<<<<<< HEAD
        val mid = left + (right - left) / 2
=======
        val mid = baseArr.count() / 2
        var leftArr = Array (mid, {i -> 0})
        var rightArr = Array (baseArr.count() - mid, {i -> 0})

        for (i in 0..(mid - 1))
            leftArr[i] = baseArr[i]
        for (i in mid..(baseArr.count() - 1))
            rightArr[i - mid] = baseArr[i]
>>>>>>> parent of 603807e... - Maked changes in mergeSort. But I can't test.

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

<<<<<<< HEAD
        sort(left,  mid, countThread)
        sort(mid + 1, right, countThread)
=======
        leftArr = sort(leftArr, countThread)
        rightArr = sort(rightArr, countThread)
>>>>>>> parent of 603807e... - Maked changes in mergeSort. But I can't test.

        return merge(leftArr, rightArr)
    }

<<<<<<< HEAD
    sort(0, arr.count() - 1, countThreads)

    return arr
}

fun main (args : Array<String>)
{
    var arr = arrayOf(9, 6, 1, 5, 10, 3, 7, 4, 2, 8)
    arr = mergeSort(8, arr)
    for (i in 0..(arr.count() - 1))
        println(arr[i])
=======
    return sort(arr, countThreads)
>>>>>>> parent of 603807e... - Maked changes in mergeSort. But I can't test.
}
