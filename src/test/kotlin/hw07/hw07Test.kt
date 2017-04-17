package hw07

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Created by Mikhail on 01.11.2015.
 */
public class HW07Test {
    @Test fun CompareToTest() {
        val arr1 = arrayOf(5, 4, 3, 11, 22);
        val arr2 = arrayOf(7, 6, 4, 33, 55, 44);
        val list1: OrderedList<Int> = OrderedList()
        val list2: OrderedList<Int> = OrderedList()
        for (i in 0..arr1.size - 1) {
            list1.add(arr1[i]);
            list2.add(arr1[i]);
        }
        assertTrue { list1.compareTo(list2) == 0}
        for (i in 0..arr2.size - 1) {
            list2.add(arr2[i]);
        }
        assertTrue { list1.compareTo(list2) < 0 }
    }

    @Test fun EqualsTest() {
        val arr1 = arrayOf('b', 'c', 'w' , 'q');
        val list1: OrderedList<Char> = OrderedList()
        val array1: ArrayOrderedList<Char> = ArrayOrderedList()
        val list2: OrderedList<Char> = OrderedList()
        for (i in 0..arr1.size - 1){
            list1.add(arr1[i])
            array1.add(arr1[i])
        }
        assertTrue  { list1.equals(array1) }
        assertFalse { list1.equals(list2) }
    }

    @Test fun HashCodeTest() {
        val arr1 = arrayOf(5, 4, 3);
        val list1: OrderedList<Int> = OrderedList()
        for (i in 0..arr1.size - 1) {
            list1.add(arr1[i]);
        }
        assertEquals(list1.hashCode(), 1684)
    }

    @Test fun OrderedListAddTest() {
        val arr1 = arrayOf(5, 44, 3);
        val list1: OrderedList<Int> = OrderedList()
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
    @Test fun OrderedListContainsTest() {
        val arr1 = arrayOf(5, 44, 3);
        val list1: OrderedList<Int> = OrderedList()
        for (i in 0..arr1.size - 1) {
            list1.add(arr1[i]);
        }
        assertTrue { list1.contains(5) }
        assertFalse { list1.contains(66) }
    }
    @Test fun OrderedListSizeTest() {
        val arr1 = arrayOf(5, 44, 3);
        val list1: OrderedList<Int> = OrderedList()
        for (i in 0..arr1.size - 1) {
            list1.add(arr1[i]);
        }
        assertEquals(list1.size(), 3)
    }
    @Test fun ArrayOrderedListAddTest() {
        val arr1 = arrayOf(5, 44, 3);
        val array1: ArrayOrderedList<Int> = ArrayOrderedList()
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
    @Test fun ArrayOrderedListContainsTest() {
        val arr1 = arrayOf(5, 44, 3);
        val array1: ArrayOrderedList<Int> = ArrayOrderedList()
        for (i in 0..arr1.size - 1) {
            array1.add(arr1[i]);
        }
        assertTrue { array1.contains(5) }
        assertFalse { array1.contains(66) }
    }
    @Test fun ArrayOrderedListSizeTest() {
        val arr1 = arrayOf(5, 44, 3);
        val array1: ArrayOrderedList<Int> = ArrayOrderedList()
        for (i in 0..arr1.size - 1) {
            array1.add(arr1[i]);
        }
        assertEquals(array1.size(), 3)
    }
}
