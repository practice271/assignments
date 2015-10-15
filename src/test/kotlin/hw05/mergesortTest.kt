package hw05

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.util.measureTimeMillis

public class mergesortTest {
    @Test fun emptyTest1Thread() {
        val l = listOf() : List<Int>
        assertEquals(mergesort(l, 0, l.size() - 1), listOf() : List<Int>)
    }
    @Test fun emptyTest4Threads() {
        val l = listOf() : List<Int>
        assertEquals(mergesort(l, 0, l.size() - 1, 4), listOf() : List<Int>)
    }
    @Test fun oneElementTest1Thread() {
        val l = listOf(1)
        assertEquals(mergesort(l, 0, l.size() - 1), listOf(1))
    }
    @Test fun oneElementTest4Threads() {
        val l = listOf(1)
        assertEquals(mergesort(l, 0, l.size() - 1, 4), listOf(1))
    }
    @Test fun severalElementsTest1Thread() {
        val l = listOf(1, 2, 4, 3, 2, -1, 5)
        assertEquals(mergesort(l, 0, l.size() - 1), listOf(-1, 1, 2, 2, 3, 4, 5))
    }
    @Test fun severalElementsTest4Threads() {
        val l = listOf(1, 2, 4, 3, 2, -1, 5)
        assertEquals(mergesort(l, 0, l.size() - 1, 4), listOf(-1, 1, 2, 2, 3, 4, 5))
    }
    val rnd = Random()
    val l = Array(10000000, {rnd.nextInt()}).toArrayList()
    var res = arrayListOf() : ArrayList<Int>
    val l_sorted = l.sorted()
    internal fun testMany (threadNum : Int) {
        println("$threadNum threads: ${measureTimeMillis { res = mergesort(l, 0, l.size() - 1, threadNum).toArrayList() }} ms")
        assertEquals(res, l_sorted)
    }
    @Test fun manyElementsTest1Thread() {
        testMany(1)
    }
    @Test fun manyElementsTest2Threads() {
        testMany(2)
    }
    @Test fun manyElementsTest4Threads() {
        testMany(4)
    }
    @Test fun manyElementsTest8Threads() {
        testMany(8)
    }
    @Test fun manyElementsTest150Threads() {
        testMany(150)
    }
}