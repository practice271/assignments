package hw05

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.util.measureTimeMillis
import utilities.ArrayListInit

public class mergesortTest {
    @Test fun emptyTest1Thread() {
        val l = listOf() : List<Int>
        assertEquals(mergesort(l), listOf() : List<Int>)
    }
    @Test fun emptyTest4Threads() {
        val l = listOf() : List<Int>
        assertEquals(mergesort(l, 4), listOf() : List<Int>)
    }
    @Test fun oneElementTest1Thread() {
        val l = listOf(1)
        assertEquals(mergesort(l), listOf(1))
    }
    @Test fun oneElementTest4Threads() {
        val l = listOf(1)
        assertEquals(mergesort(l, 4), listOf(1))
    }
    @Test fun severalElementsTest1Thread() {
        val l = listOf(1, 2, 4, 3, 2, -1, 5)
        assertEquals(mergesort(l), listOf(-1, 1, 2, 2, 3, 4, 5))
    }
    @Test fun severalElementsTest4Threads() {
        val l = listOf(1, 2, 4, 3, 2, -1, 5)
        assertEquals(mergesort(l, 4), listOf(-1, 1, 2, 2, 3, 4, 5))
    }
    val rnd = Random()
    val l = ArrayListInit(10000000, {rnd.nextInt()})
    var res = arrayListOf(1)
    val l_sorted = l.sorted()
    internal fun testMany (threadNum : Int) {
        println("$threadNum threads: ${measureTimeMillis { res = mergesort(l, threadNum).toArrayList() }} ms")
        assertEquals(res, l_sorted)
    }
    //While results of most tests are approximately stable 11s,
    //4 threads and 8 threads are kinda strange: they're taking times from 5.5 to 12.5 randomly.
    //The only reason I can come up with is that array matters:
    //one is sorted faster on 4 threads, another on 8. But that's improbable
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
    @Test fun manyElementsTest17Threads() {
        testMany(13)
    }
    @Test fun manyElementsTest150Threads() {
        testMany(150)
    }
}