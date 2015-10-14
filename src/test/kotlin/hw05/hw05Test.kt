package hw05

import org.junit.Test
import kotlin.test.assertEquals

/*    Merge Sort Tests

      1 Thread
      1000 elements  =>       3 ms
      2000 elements  =>       5 ms
      4000 elements  =>       2 ms
      8000 elements  =>       5 ms
      16000 elements  =>       5 ms
      32000 elements  =>      10 ms
      64000 elements  =>      22 ms
      128000 elements  =>      26 ms
      256000 elements  =>      73 ms
      512000 elements  =>     152 ms
      1024000 elements  =>     288 ms

      2 Threads
      1000 elements  =>       4 ms
      2000 elements  =>       2 ms
      4000 elements  =>      15 ms
      8000 elements  =>       3 ms
      16000 elements  =>       4 ms
      32000 elements  =>       9 ms
      64000 elements  =>      23 ms
      128000 elements  =>      21 ms
      256000 elements  =>      40 ms
      512000 elements  =>     107 ms
      1024000 elements  =>     164 ms

      4 Threads
      1000 elements  =>       4 ms
      2000 elements  =>       2 ms
      4000 elements  =>       4 ms
      8000 elements  =>       7 ms
      16000 elements  =>       7 ms
      32000 elements  =>      11 ms
      64000 elements  =>      18 ms
      128000 elements  =>      19 ms
      256000 elements  =>      35 ms
      512000 elements  =>      59 ms
      1024000 elements  =>     167 ms

      8 Threads
      1000 elements  =>       6 ms
      2000 elements  =>       3 ms
      4000 elements  =>      10 ms
      8000 elements  =>       7 ms
      16000 elements  =>       6 ms
      32000 elements  =>      10 ms
      64000 elements  =>      18 ms
      128000 elements  =>      19 ms
      256000 elements  =>      30 ms
      512000 elements  =>      66 ms
      1024000 elements  =>     151 ms
 */

public class HW05Test {
    @Test fun mergeSortTest() {
        fun toString(arr: IntArray):String {
                var i: Int = 0
                var s: String = ""
                while (i < arr.size()) {
                        s = s + arr[i].toString() + " "
                        i++
                }
                return s
        }
        val arr = intArrayOf(5, 1, 6, 22, -3)
        val arr1 = intArrayOf()
        val arr2 = intArrayOf(25, 14, 15, 22)
        MergeSort.parallelMergeSort(arr, 4)
        MergeSort.parallelMergeSort(arr1, 4)
        MergeSort.parallelMergeSort(arr2, 1)
        val res = toString(arr)
        val res1 = toString(arr1)
        val res2 = toString(arr2)
        assertEquals("-3 1 5 6 22 ", res)
        assertEquals("", res1)
        assertEquals("14 15 22 25 ", res2)
    }
}