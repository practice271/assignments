package hw07

/* OrderedLists tests made by Guzel Garifullina
   Estimated time  1 hour
   real time       1 hour
*/

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import hw07.kotlinClasses.*

class TestKotlin {
    private val arr = OrderedArray<String>(1, "500")
    private val list = OrderedList<String>("500")

    @Test
    fun isEmpty1() {
        assertFalse(arr.isEmpty())
        assertFalse(list.isEmpty())
    }

    @Test
    fun size1() {
        assertEquals(1, arr.size())
        assertEquals(1, list.size())
    }

    private fun addFirstElem() {
        arr.add("600")
        list.add("600")
    }

    @Test
    fun addSize() {
        addFirstElem()
        assertEquals(2, arr.size())
        assertEquals(2, list.size())
    }

    @Test
    fun addGetArray() {
        addFirstElem()
        arr.add("900")
        assertEquals("500", arr.get(0))
        assertEquals("600", arr.get(1))
        assertEquals("900", arr.get(2))
    }

    @Test
    fun addGetArray2() {
        addFirstElem()
        arr.add("4")
        assertEquals("4", arr.get(0))
        assertEquals("500", arr.get(1))
        assertEquals("600", arr.get(2))
    }

    @Test
    fun addGetArray3() {
        addFirstElem()
        arr.add("55")
        assertEquals("500", arr.get(0))
        assertEquals("55", arr.get(1))
        assertEquals("600", arr.get(2))
    }

    @Test
    fun addGetList() {
        addFirstElem()
        list.add("900")
        assertEquals("500", list.get(0))
        assertEquals("600", list.get(1))
        assertEquals("900", list.get(2))
    }

    @Test
    fun addGetList2() {
        addFirstElem()
        list.add("4")
        assertEquals("4", list.get(0));
        assertEquals("500", list.get(1))
        assertEquals("600", list.get(2))
    }

    @Test
    fun addGetList3() {
        addFirstElem()
        list.add("55")
        assertEquals("500", list.get(0))
        assertEquals("55", list.get(1))
        assertEquals("600", list.get(2))
    }


    // 500 600 9 10
    private fun init1() {
        addFirstElem()
        arr.add("9")
        list.add("9")
        arr.add("99")
        list.add("99")
    }

    @Test
    fun removeArrayFirst() {
        init1()
        arr.removeAt(0)
        assertEquals("600", arr.get(0))
        assertEquals("9", arr.get(1))
        assertEquals("99", arr.get(2))
        assertEquals(3, arr.size())
    }

    @Test
    fun removeArrayMiddle() {
        init1()
        arr.removeAt(1)
        assertEquals("500", arr.get(0))
        assertEquals("9", arr.get(1))
        assertEquals("99", arr.get(2))
        assertEquals(3, arr.size())
    }

    @Test
    fun removeArrayLast() {
        init1()
        arr.removeAt(3)
        assertEquals("500", arr.get(0))
        assertEquals("600", arr.get(1))
        assertEquals("9", arr.get(2))
        assertEquals(3, arr.size())
    }

    @Test
    fun removeListFirst() {
        init1()
        list.removeAt(0)
        assertEquals("600", list.get(0))
        assertEquals("9", list.get(1))
        assertEquals("99", list.get(2))
        assertEquals(3, list.size())
    }

    @Test
    fun removeListMiddle() {
        init1()
        list.removeAt(1)
        assertEquals("500", list.get(0))
        assertEquals("9", list.get(1))
        assertEquals("99", list.get(2))
        assertEquals(3, list.size())
    }

    @Test
    fun removeListLast() {
        init1()
        list.removeAt(3)
        assertEquals("500", list.get(0))
        assertEquals("600", list.get(1))
        assertEquals("9", list.get(2))
        assertEquals(3, list.size())
    }
    @Test
    fun clearArray() {
        init1()
        arr.clear()
        assertTrue(arr.isEmpty())
        arr.add("0")
        assertEquals("0", arr.get(0))
        assertEquals(1, arr.size())
    }

    @Test
    fun clearList() {
        init1()
        list.clear()
        assertTrue(list.isEmpty())
        list.add("0")
        assertEquals("0", list.get(0))
        assertEquals(1, list.size())
    }
    private val arr2 = OrderedArray<String>(1, "600")
    private val list2 = OrderedList<String>("600")
    private fun init2(){
        init1()
        arr2.add("9")
        arr2.add("99")
        arr2.add(("500"))
        list2.add("9")
        list2.add("99")
        list2.add(("500"))
    }
    @Test
    fun equal() {
        init2()
        assertTrue(arr2.equals(arr))
    }
    @Test
    fun equal2() {
        init2()
        assertTrue(list2.equals(list))
    }
    fun compare0() {
        init2()
        assertEquals(0, arr2.compareTo(arr))
        assertEquals(0, list2.compareTo(list))
    }

    fun compare1() {
        init2()
        arr.add("99")
        list.add("99")
        assertEquals(1, list.compareTo(list2))
        assertEquals(1, arr.compareTo(arr2))
    }

    fun compare2() {
        init2()
        arr2.add("99")
        list2.add("99")
        assertEquals(-1, list.compareTo(list2))
        assertEquals(-1, arr.compareTo(arr2))
    }

    fun compare3() {
        init2()
        arr.add("0")
        list.add("0")
        assertEquals(1, list.compareTo(list2))
        assertEquals(1, arr.compareTo(arr2))
    }

    fun hashCode1() {
        init2()
        assertTrue(arr.hashCode() == list.hashCode())
        assertTrue(arr2.hashCode() == arr.hashCode())
    }
}
