
/**
 * Created by Zarina Kurbatova on 19.09.2015.
 */

package hw02
import org.junit.Test
import kotlin.test.assertEquals

public class task1_sortTest {
    fun equal(arr1: Array<Int>, arr2: Array<Int>): Boolean {

        if (arr1.size() != arr2.size()) return false

        var i: Int = arr1.size()-1
        while(i>=0) {
            if (arr1[i] != arr2[i]) {return false}
            i--
        }

        return true
    }

    @Test fun test1() {
        var arr1 = arrayOf(7,2,3,9,1,6,11,17,5)
        val arr2 = arrayOf(1,2,3,5,6,7,9,11,17)
        heapSort(arr1)
        assertEquals(true, equal(arr1,arr2))
    }

    @Test fun test2() {
        var arr1 = arrayOf(19,3,24,4,5,9,0,1)
        val arr2 = arrayOf(0,1,3,4,5,9,19,24)
        heapSort(arr1)
        assertEquals(true, equal(arr1,arr2))
    }

    @Test fun test3() {
        var arr1 = arrayOf(6,16,1,25,7,4,8,2,0)
        val arr2 = arrayOf(0,1,2,4,6,7,8,16,25)
        heapSort(arr1)
        assertEquals(true, equal(arr1,arr2))
    }

    @Test fun test4() {
        var arr1 = arrayOf(10,9,8,7,6,5,4,3,2,1,0)
        val arr2 = arrayOf(0,1,2,3,4,5,6,7,8,9,10)
        heapSort(arr1)
        assertEquals(true, equal(arr1,arr2))
    }
}