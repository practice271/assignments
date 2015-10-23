package homeworks.hw05

/**
 * Created by Ilya on 17.10.2015.
 */

import org.junit.Test
import kotlin.test.assertEquals
import org.junit.Assert

class hw05Test {

    @Test fun TestBadIncrement1() {
        var result = 0
        val last = 9
        for (i in 0..last)
            result += badIncrement(10000)
        assert(result < (last + 1) * 10000)
    }

    @Test fun TestBadIncrement2() {
        var result = 0
        val last = 19
        for (i in 0..last)
            result += badIncrement(20000)
        assert(result < (last + 1) * 20000)
    }

    @Test fun mergeSortTest1() {
        val array = arrayOf(1)
        assertEquals(array, sort(array, 2))
    }

    @Test fun mergeSortTest2() {
        val array    = arrayOf(3, 2, 5, 1, 3, 6, 8, 4, 9)
        val expected = arrayOf(1, 2, 3, 3, 4, 5, 6, 8, 9)
        val result = sort(array, 4)
        Assert.assertArrayEquals(expected, result)
    }

    @Test fun mergeSortTest3() {
        val array    = arrayOf(3, 2, 5, 1, 3, 6, 8, 4, 9)
        val expected = arrayOf(1, 2, 3, 3, 4, 5, 6, 8, 9)
        val result   = sort(array, 8)
        Assert.assertArrayEquals(expected, result)
    }

    val max = 10000000
    val expected_Array = Array(max, {i -> i + 1})
    val manyElements_Array = Array(max, {i -> max - i})

    private fun manyElementsTest(threadNum: Int) {
        val manyElementsArray = manyElements_Array
        val expected = expected_Array
        val result = sort(manyElementsArray, threadNum)
        Assert.assertArrayEquals(expected, result)
        1+1
    }

    @Test fun manyElementsTimeTest1() {
        manyElementsTest(1)
    }

    @Test fun manyElementsTimeTest2() {
        manyElementsTest(2)
    }

    @Test fun manyElementsTimeTest3() {
        manyElementsTest(4)
    }

    @Test fun manyElementsTimeTest4() {
        manyElementsTest(8)
    }

    @Test fun manyElementsTimeTest5() {
        manyElementsTest(25)
    }
}