package homeworks.hw07

import org.junit.Test
import kotlin.test.assertEquals

class JavaTests {

    fun createListArray(): JavaOrderedListArray<Int> {
        val list = JavaOrderedListArray<Int>()
        list.add(1)
        list.add(4)
        list.add(2)
        list.add(5)
        list.add(0)
        list.add(3)
        list.add(15)
        list.add(10)
        return list
    }

    fun createListATD(): JavaOrderedListATD<Int> {
        val list = JavaOrderedListATD<Int>()
        list.add(6)
        list.add(12)
        list.add(24)
        list.add(22)
        list.add(23)
        list.add(1)
        list.add(0)
        list.add(25)
        return list
    }

    @Test fun testAddArray() {
        val expected = arrayOf(0, 1, 2, 3, 4, 5, 10, 15)
        val list = createListArray()
        for (i in 0..expected.size - 1) {
            assertEquals(expected[i], list.get(i))
        }
    }

    @Test fun testRemoveAtArray() {
        val expected = arrayOf(0, 1, 2, 3, 5, 10, 15)
        val list = createListArray()
        list.removeAt(4)
        for (i in 0..expected.size - 1) {
            assertEquals(expected[i], list.get(i))
        }
    }

    @Test fun testEquals1Array() {
        val expected = createListArray()
        val list = createListArray()
        expected.add(20)
        list.add(20)
        assertEquals(list.equals(expected), true)
    }

    @Test fun testEquals2Array() {
        val expected = createListArray()
        val list = createListArray()
        expected.add(20)
        list.add(21)
        assertEquals(list.equals(expected), false)
    }

    @Test fun testAddATD() {
        val expected = arrayOf(0, 1, 6, 12, 22, 23, 24, 25)
        val list = createListATD()
        for (i in 0..expected.size - 1) {
            assertEquals(expected[i], list.get(i))
        }
    }

    @Test fun testRemoveAtATD() {
        val expected = arrayOf(0, 1, 6, 12, 23, 24, 25)
        val list = createListATD()
        list.removeAt(4)
        for (i in 0..expected.size - 1) {
            assertEquals(expected[i], list.get(i))
        }
    }

    @Test fun testEquals1ATD() {
        val expected = createListATD()
        val list = createListATD()
        expected.add(100)
        list.add(100)
        assertEquals(list.equals(expected), true)
    }

    @Test fun testEquals2ATD() {
        val expected = createListATD()
        val list = createListATD()
        expected.add(100)
        list.add(2)
        assertEquals(list.equals(expected), false)
    }
}