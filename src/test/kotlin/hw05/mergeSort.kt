package hw05

import org.junit.Test
import kotlin.test.assertEquals

public class mergeSort()
{
    @Test fun Test0()
    {
        var arr = arrayOf(9, 6, 1, 5, 10, 3, 7, 4, 2, 8)
        val resArr = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        arr = mergeSort(8, arr)

        org.junit.Assert.assertArrayEquals(arr, resArr)
    }

    @Test fun Test1()
    {
        var arr = Array (100500, {i -> 100499 - i})
        val resArr = Array(100500, {i -> i})
        
        arr = mergeSort(32, arr)

        org.junit.Assert.assertArrayEquals(arr, resArr)
    }
}