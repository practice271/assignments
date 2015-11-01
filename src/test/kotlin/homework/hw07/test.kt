package homework.hw07

import org.junit.Test
import kotlin.test.assertEquals

class Hw07JavaTest {
    //Array tests
    @Test fun testAdd() {
        var list = arrList<Int>()
        list.add(1)
        list.add(2)
        list.add(3)
        list.add(4)
        list.add(5)
        list.add(6)
        list.add(7)
        for (i in 1..7) {
            assertEquals(i, list.get(i - 1))
        }

    }

    @Test fun testRemove() {
        var list = arrList<Int>()
        list.add(5)
        list.add(8)
        list.add(10)
        list.removeAt(0)

        assertEquals(8, list.get(0))
        assertEquals(10, list.get(1))

        list.removeAt(1)
        assertEquals(8, list.get(0))
    }

    @Test fun testEquals() {
        val list1 = arrList<String>()
        val list2 = arrList<String>()
        list1.add("bb")
        list1.add("gg")
        list2.add("bb")
        assertEquals(false, list1.equals(list2))
        list2.add("gg")
        assertEquals(true, list1.equals(list2))
        assertEquals(list1.hashCode(), list2.hashCode())
    }

    //Linked list tests
    @Test fun testOrdListAdd() {
        val list = ordList<Int>()
        list.add(1)
        list.add(2)
        list.add(3)
        list.add(4)
        list.add(5)
        for (i in 1..5) {
            assertEquals(i, list.get(i))
        }

    }

    @Test fun testOrdListRemove() {
        val list = ordList<Int>()
        for (i in 1..10)
            list.add(i)
        list.removeAt(10)
        assertEquals(9, list.get(9))
        list.removeAt(1)
        assertEquals(2, list.get(1))
    }

    @Test fun testOrdListEquals() {
        val list1 = ordList<Int>()
        val list2 = ordList<Int>()
        list1.add(2)
        list1.add(5)
        list2.add(2)
        assertEquals(false, list1.equals(list2))
        list2.add(5)
        assertEquals(true, list1.equals(list2))
        assertEquals(list1.hashCode(), list2.hashCode())
    }

}