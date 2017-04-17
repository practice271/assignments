package hw07

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Created by Mikhail on 01.11.2015.
 */
public class HW07TestKT {

    @Test fun HashCodeTest() {
        val arr1 = arrayOf(5, 4, 3);
        val list1: OrderedListKT<Int> = OrderedListKT()
        for (i in 0..arr1.size - 1) {
            list1.add(arr1[i]);
        }
        assertEquals(list1.hashCode(), 1684)
    }

    @Test fun OrderedListKTAddTest() {
        val arr1 = arrayOf(5, 44, 3);
        val list1: OrderedListKT<Int> = OrderedListKT()
        for (i in 0..arr1.size - 1) {
            list1.add(arr1[i]);
        }
        var str: String = ""
        for (i in 0..list1.size() - 1) {
            str += "${list1.get(i)}"
            str += " "
        }
        assertEquals("3 5 44 ", str)
    }
    @Test fun OrderedListKTContainsTest() {
        val arr1 = arrayOf(5, 44, 3);
        val list1: OrderedListKT<Int> = OrderedListKT()
        for (i in 0..arr1.size - 1) {
            list1.add(arr1[i]);
        }
        assertTrue { list1.contains(5) }
        assertFalse { list1.contains(66) }
    }
    @Test fun OrderedListKTSizeTest() {
        val arr1 = arrayOf(5, 44, 3);
        val list1: OrderedListKT<Int> = OrderedListKT()
        for (i in 0..arr1.size - 1) {
            list1.add(arr1[i]);
        }
        assertEquals(list1.size(), 3)
    }
    @Test fun ArrayOrderedListKTAddTest() {
        val arr1 = arrayOf(5, 44, 3);
        val array1: ArrayOrderedListKT<Int> = ArrayOrderedListKT()
        for (i in 0..arr1.size - 1) {
            array1.add(arr1[i]);
        }
        var str: String = ""
        for (i in 0..array1.size() - 1) {
            str += "${array1.get(i)}"
            str += " "
        }
        assertEquals("3 5 44 ", str)
    }
    @Test fun ArrayOrderedListKTContainsTest() {
        val arr1 = arrayOf(5, 44, 3);
        val array1: ArrayOrderedListKT<Int> = ArrayOrderedListKT()
        for (i in 0..arr1.size - 1) {
            array1.add(arr1[i]);
        }
        assertTrue { array1.contains(5) }
        assertFalse { array1.contains(66) }
    }
    @Test fun ArrayOrderedListKTSizeTest() {
        val arr1 = arrayOf(5, 44, 3);
        val array1: ArrayOrderedListKT<Int> = ArrayOrderedListKT()
        for (i in 0..arr1.size - 1) {
            array1.add(arr1[i]);
        }
        assertEquals(array1.size(), 3)
    }
}
