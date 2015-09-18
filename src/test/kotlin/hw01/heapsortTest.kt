/* Tests for heap sort made by Guzel Garifullina
   Estimated time 30 minutes
   real time      30 minutes
*/
package hw01

import org.junit.Test
import kotlin.test.assertEquals

public class heapsortTest {
    fun compare<T : Comparable<T>>(a: Array<T>, b: Array<T>): Boolean {
        if (a.size() != b.size())
            return false
        for (i in a.indices){
            if (a[i] != b[i])
                return false
        }
        return true

    }
    @Test fun Test1() {
        val array = Array(20, {i -> i })
        val arraySorted =  array
        heapSort( array)
        assertEquals(true, compare(array, arraySorted))
    }
    @Test fun Test2() {
        val array = arrayOf (3, 1, -1, 3, 4, 5, 10)
        val arraySorted = arrayOf (-1, 1, 3, 3, 4, 5, 10)
        heapSort( array)
        assertEquals(true, compare(array, arraySorted))
    }
    @Test fun Test3() {
        val array = arrayOf('e', 'm', 'n', 'A', 'l', 'e')
        val arraySorted = arrayOf ('A', 'e', 'e', 'l', 'm', 'n')
        heapSort( array)
        assertEquals(true, compare(array, arraySorted))
    }
    @Test fun Test4() {
        val array = arrayOf(1)
        val arraySorted = arrayOf (1)
        heapSort( array)
        assertEquals(true, compare(array, arraySorted))
    }
}