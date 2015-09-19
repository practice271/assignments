package homework.hw02

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.*

public class heapsortTest {
    Test fun heapsortEmptyArrayTest() {
        var emptyArray: Array<Int> = arrayOf()
        emptyArray.heapsort()
        org.junit.Assert.assertArrayEquals(arrayOf(), emptyArray)
    }

    Test fun heapsortIntArrayTest02() {
        var intArray: Array<Int> = arrayOf(9, 2, 3, 2, 8, 7, 6)
        intArray.heapsort()
        org.junit.Assert.assertArrayEquals(arrayOf(2, 2, 3, 6, 7, 8, 9), intArray)
    }

    Test fun heapsortCharArrayTest03() {
        var charArray: Array<Char> = arrayOf('z', 'a', 'y', 'b', 'x', 'c')
        charArray.heapsort()
        org.junit.Assert.assertArrayEquals(arrayOf('a', 'b', 'c', 'x', 'y', 'z'), charArray)
    }

    Test fun heapsortStringArrayTest04() {
        var strArray: Array<String> = arrayOf("Abc", "abc", "ab", "ba", "bA")
        strArray.heapsort()
        org.junit.Assert.assertArrayEquals(arrayOf("Abc", "ab", "abc", "bA", "ba"), strArray)
    }
}