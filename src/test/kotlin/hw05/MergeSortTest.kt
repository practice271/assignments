package hw05

import org.junit.Test
import kotlin.test.assertEquals
import java.util.Random

/*    Merge Sort Tests
      elements            |  1 thread in ms | 2 threads | 4 threads | 8 threads
      1000 elements       |  34             | 3         | 5         | 5
      10000 elements      |  52             | 10        | 5         | 5
      100000 elements     |  93             | 33        | 17        | 18
      1000000 elements    |  408            | 178       | 153       | 180
 */

class MergeSortTest {

    @Test
    public fun thread1gen() {
        mergeSortTest(1000, 1)
        mergeSortTest(1000, 1)
        mergeSortTest(1000, 1)
    }

    @Test
    public fun thread2gen() {
        mergeSortTest(1000, 2)
        mergeSortTest(1000, 2)
        mergeSortTest(1000, 2)
    }

    @Test
    public fun thread8HugeNum() {
        mergeSortTest(100000, 1)
        mergeSortTest(100000, 1)
    }

    private fun mergeSortTest(asize: Int, threadNum: Int) {
        val arr = Array(asize, { Random().nextInt() })
        MergeSort.doSort(arr, threadNum)
        assertEquals(arr.isSorted(), true)
    }

    private fun Array<Int>.isSorted(): Boolean {
        for(i in 0..size() - 2)
            if(this[i] > this[i + 1]) return false
        return true
    }

}