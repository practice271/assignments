package homework.hw05

import org.junit.Test
import kotlin.test.assertEquals


public class HW05Test {
    @Test fun mergeSortTest() {
        var array : Array<Int>
        var resArray : Array<Int>
        for (i in 1..4) {
            array = arrayOf(9, 6, 1, 5, 10, 3, 7, 4, 2, 8)
            resArray = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            sort(array, i)
            org.junit.Assert.assertArrayEquals(resArray, array)
        }
        for (i in 1..4) {
            array = Array<Int>(1000000, { i -> 999999 - i })
            resArray = Array<Int>(1000000, { i -> i })
            sort(array, i)
            org.junit.Assert.assertArrayEquals(resArray, array)
        }
    }
}