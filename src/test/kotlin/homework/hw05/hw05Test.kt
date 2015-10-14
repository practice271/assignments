/*
 * Homework 5 (13.10.2015)
 * Tests for tasks with threads
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw05

import org.junit.Test
import kotlin.test.assertEquals
import org.junit.Assert.assertArrayEquals

public class hw05Test {

    /** Tests for a parallel increment with synchronisation. */
    @Test fun incrementTest01() {
        var inc = ParallelIncrement(1000, 1000)
        assertEquals(1000, inc.increment())

        for (i in 1 .. 10) {
            inc = ParallelIncrement(i, 1000)
            assertEquals(1000, inc.increment())
        }
    }

    @Test fun incrementTest02() {
        var inc = ParallelIncrement(10000, 10000)
        assertEquals(10000, inc.increment())

        for (i in 1 .. 10) {
            inc = ParallelIncrement(i, 10000)
            assertEquals(10000, inc.increment())
        }
    }

    @Test fun incrementTest03() {
        var inc = ParallelIncrement(100000, 100000)
        assertEquals(100000, inc.increment())

        for (i in 1 .. 10) {
            inc = ParallelIncrement(i, 100000)
            assertEquals(100000, inc.increment())
        }
    }


    /** Tests for a parallel merge sort. */
    @Test fun mergeSortTest01() {
        val array  = arrayOf(-1, 4, -17, 3, 8)
        val result = arrayOf(-17, -1, 3, 4, 8)

        for (i in 1 .. 5) {
            val temp = array.copyOf()
            temp.mergeSort(i)
            assertArrayEquals(result, temp)
        }
    }

    @Test fun mergeSortTest02() {
        val array  = arrayOf(9, 2, 3, 8, 5, 7, 4, 6, 1)
        val result = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

        for (i in 1 .. 9) {
            val temp = array.copyOf()
            temp.mergeSort(i)
            assertArrayEquals(result, temp)
        }
    }

    @Test fun mergeSortTest03() {
        val array  = Array(10000, { i -> i })
        val result = Array(10000, { i -> i })

        for (i in 0 .. 5) {
            val temp = array.copyOf()
            temp.mergeSort(Math.pow(2.0, i.toDouble()).toInt())
            assertArrayEquals(result, temp)
        }
    }

    @Test fun mergeSortTest04() {
        val array  = Array(500000, { i -> 500000 - i })
        val result = Array(500000, { i -> i + 1 })

        for (i in 0 .. 5) {
            val temp = array.copyOf()
            temp.mergeSort(Math.pow(2.0, i.toDouble()).toInt())
            assertArrayEquals(result, temp)
        }
    }

    @Test fun mergeSortTest05() {
        val array  = Array(1000000, { i -> i })
        val result = Array(1000000, { i -> i })

        for (i in 0 .. 5) {
            val temp = array.copyOf()
            temp.mergeSort(Math.pow(2.0, i.toDouble()).toInt())
            assertArrayEquals(result, temp)
        }
    }

    /**
     * | Test             | 1 thread | 2 threads | 4 threads | 8 threads |
     * +==================+==========+===========+===========+===========+
     * | mergeSortTest01  |   24 ms  |    28 ms  |    25 ms  |    32 ms  |
     * | mergeSortTest02  |   26 ms  |    24 ms  |    24 ms  |    26 ms  |
     * | mergeSortTest03  |   37 ms  |    38 ms  |    38 ms  |    38 ms  |
     * | mergeSortTest04  |  357 ms  |   360 ms  |   236 ms  |   326 ms  |
     * | mergeSortTest05  | 1540 ms  |  1500 ms  |  1470 ms  |  1670 ms  |
     */
}