package hw05

import org.junit.Test
import kotlin.test.assertEquals

/*    Merge Sort Tests

      elements            |  1 thread in ms | 2 threads | 4 threads | 8 threads
      1000 elements       |  3              | 4         | 4         | 6
      2000 elements       |  5              | 2         | 2         | 3
      4000 elements       |  2              | 15        | 4         | 10
      8000 elements       |  5              | 3         | 7         | 7
      16000 elements      |  5              | 4         | 7         | 6
      32000 elements      |  10             | 9         | 11        | 10
      64000 elements      |  22             | 23        | 18        | 18
      128000 elements     |  26             | 21        | 19        | 19
      256000 elements     |  73             | 40        | 35        | 30
      512000 elements     |  152            | 107       | 59        | 66
      1024000 elements    |  288            | 164       | 167       | 151
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