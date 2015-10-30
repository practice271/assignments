package homework.hw07.kotlinimp

import homework.hw07.Hw07Test
import homework.hw07.kotlinimp.ArrayOrderedList
import homework.hw07.kotlinimp.LinkedOrderedList
import org.junit.Test

class Hw07KotlinTest {
    @Test fun testKotlinArrayAdd() {
        val list = ArrayOrderedList<String>()
        Hw07Test.testAdd(list)
    }

    @Test fun testKotlinArrayRemove() {
        val list = ArrayOrderedList<String>()
        Hw07Test.testRemove(list)
    }

    @Test fun testKotlinArrayCompare() {
        val list1 = ArrayOrderedList<String>()
        val list2 = ArrayOrderedList<String>()
        Hw07Test.testCompare(list1, list2)
    }

    @Test fun testKotlinArrayEquals() {
        val list1 = ArrayOrderedList<String>()
        val list2 = ArrayOrderedList<String>()
        Hw07Test.testEquals(list1, list2)
    }

    @Test fun testKotlinLinkedAdd() {
        val list = LinkedOrderedList<String>()
        Hw07Test.testAdd(list)
    }

    @Test fun testKotlinLinkedRemove() {
        val list = LinkedOrderedList<String>()
        Hw07Test.testRemove(list)
    }

    @Test fun testKotlinLinkedCompare() {
        val list1 = LinkedOrderedList<String>()
        val list2 = LinkedOrderedList<String>()
        Hw07Test.testCompare(list1, list2)
    }

    @Test fun testKotlinLinkedEquals() {
        val list1 = LinkedOrderedList<String>()
        val list2 = LinkedOrderedList<String>()
        Hw07Test.testEquals(list1, list2)
    }
}