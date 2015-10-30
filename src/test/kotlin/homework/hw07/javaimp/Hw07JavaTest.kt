package homework.hw07.javaimp;

import homework.hw07.Hw07Test
import org.junit.Test;

public class Hw07JavaTest {
    @Test fun testJavaArrayAdd() {
        val list = ArrayOrderedList<String>()
        Hw07Test.testAdd(list)
    }

    @Test fun testJavaArrayRemove() {
        val list = ArrayOrderedList<String>()
        Hw07Test.testRemove(list)
    }

    @Test fun testJavaArrayCompare() {
        val list1 = ArrayOrderedList<String>()
        val list2 = ArrayOrderedList<String>()
        Hw07Test.testCompare(list1, list2)
    }

    @Test fun testJavaArrayEquals() {
        val list1 = ArrayOrderedList<String>()
        val list2 = ArrayOrderedList<String>()
        Hw07Test.testEquals(list1, list2)
    }

    @Test fun testJavaLinkedAdd() {
        val list = LinkedOrderedList<String>()
        Hw07Test.testAdd(list)
    }

    @Test fun testJavaLinkedRemove() {
        val list = LinkedOrderedList<String>()
        Hw07Test.testRemove(list)
    }

    @Test fun testJavaLinkedCompare() {
        val list1 = LinkedOrderedList<String>()
        val list2 = LinkedOrderedList<String>()
        Hw07Test.testCompare(list1, list2)
    }

    @Test fun testJavaLinkedEquals() {
        val list1 = LinkedOrderedList<String>()
        val list2 = LinkedOrderedList<String>()
        Hw07Test.testEquals(list1, list2)
    }
}
