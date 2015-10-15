package hw05

import org.junit.Test
import kotlin.test.assertEquals
import java.util.ArrayList

public class parallelMergeSortTest {
    @Test fun emptyArrayTest() {
        assertEquals(ArrayList<Int>(), parallelMergeSort(ArrayList<Int>(), 1))
        assertEquals(ArrayList<Int>(), parallelMergeSort(ArrayList<Int>(), 2))
        assertEquals(ArrayList<Int>(), parallelMergeSort(ArrayList<Int>(), 16))
    }

    @Test fun ArrayTestOneThread() {
        var intArr = ArrayList<Int>(listOf(1))
        assertEquals(intArr, parallelMergeSort(intArr, 1))

        var strArr = ArrayList<String>(listOf("abc"))
        assertEquals(strArr, parallelMergeSort(strArr, 1))

        intArr = ArrayList<Int>(listOf(1, 20, 2, 19, 3, 18, 4, 17, 5, 16, 6, 15, 7, 14, 8, 13, 9, 12, 10, 11, 10))
        val intRes = ArrayList<Int>(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20))
        assertEquals(intRes, parallelMergeSort(intArr, 1))

        strArr = ArrayList<String>(listOf("abc", "abC", "aBc", "ab", "a"))
        val strRes = ArrayList<String>(listOf("a", "aBc", "ab", "abC", "abc"))
        assertEquals(strRes, parallelMergeSort(strArr, 1))
    }

    @Test fun ArrayTestTwoThread() {
        var intArr = ArrayList<Int>(listOf(1))
        assertEquals(intArr, parallelMergeSort(intArr, 2))

        var strArr = ArrayList<String>(listOf("abc"))
        assertEquals(strArr, parallelMergeSort(strArr, 2))

        intArr = ArrayList<Int>(listOf(1, 20, 2, 19, 3, 18, 4, 17, 5, 16, 6, 15, 7, 14, 8, 13, 9, 12, 10, 11, 10))
        val intRes = ArrayList<Int>(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20))
        assertEquals(intRes, parallelMergeSort(intArr, 2))

        strArr = ArrayList<String>(listOf("abc", "abC", "aBc", "ab", "a"))
        val strRes = ArrayList<String>(listOf("a", "aBc", "ab", "abC", "abc"))
        assertEquals(strRes, parallelMergeSort(strArr, 2))
    }

    @Test fun ArrayTestSixteenThread() {
        var intArr = ArrayList<Int>(listOf(1))
        assertEquals(intArr, parallelMergeSort(intArr, 16))

        var strArr = ArrayList<String>(listOf("abc"))
        assertEquals(strArr, parallelMergeSort(strArr, 16))

        intArr = ArrayList<Int>(listOf(1, 20, 2, 19, 3, 18, 4, 17, 5, 16, 6, 15, 7, 14, 8, 13, 9, 12, 10, 11, 10))
        val intRes = ArrayList<Int>(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20))
        assertEquals(intRes, parallelMergeSort(intArr, 16))

        strArr = ArrayList<String>(listOf("abc", "abC", "aBc", "ab", "a"))
        val strRes = ArrayList<String>(listOf("a", "aBc", "ab", "abC", "abc"))
        assertEquals(strRes, parallelMergeSort(strArr, 16))
    }
}