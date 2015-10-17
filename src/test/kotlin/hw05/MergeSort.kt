package hw05

/* Tests for merge sort made by Guzel Garifullina
   Estimated time 15 minutes
   real time      15 minutes
*/
import org.junit.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse
public class MergeSort {
    fun isInc(a: Array<Int>): Boolean {
        for (i in 0 .. (a.size() - 2) ){
            if (a[i + 1] < a[i]){
                return false
            }
        }
        return true
    }
    fun isStrictInc (a: Array<Int>): Boolean {
        for (i in 0 .. (a.size() - 2) ){
            if (a[i + 1] <= a[i]){
                return false
            }
        }
        return true
    }
    @Test fun TestThread1() {
        val mergeClass = MergeParallel(Array (100, {i -> 100 - i}), 1)
        val array = mergeClass.parallelMergeSort()
        assertTrue (isStrictInc(array))
    }

    @Test fun TestThread2() {
        val mergeClass = MergeParallel(Array (100, {i -> 100 - i}), 2)
        val array = mergeClass.parallelMergeSort()
        assertTrue (isStrictInc(array))
    }

    @Test fun TestThread8() {
        val mergeClass = MergeParallel(Array (100, {i -> 100 - i}), 8)
        val array = mergeClass.parallelMergeSort()
        assertTrue (isStrictInc(array))
    }
    @Test fun Test2() {
        val mergeClass = MergeParallel(Array (100, {i -> (100 - i) % 10}), 1)
        val array = mergeClass.parallelMergeSort()
        assertFalse (isStrictInc(array))
    }
    @Test fun Test3() {
        val mergeClass = MergeParallel(Array (100, {i -> (100 - i) % 10}), 1)
        val array = mergeClass.parallelMergeSort()
        assertTrue (isInc(array))
    }
    @Test fun Test4() {
        val mergeClass = MergeParallel(Array (100, {i -> (100 - i) % 10}), 10)
        val array = mergeClass.parallelMergeSort()
        assertFalse (isStrictInc(array))
    }
    @Test fun Test5() {
        val mergeClass = MergeParallel(Array (100, {i -> (100 - i) % 10}), 10)
        val array = mergeClass.parallelMergeSort()
        assertTrue (isInc(array))
    }
}