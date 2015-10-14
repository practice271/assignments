package hw05

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.util.measureTimeMillis

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
    @Test fun manyElementsTest1Thread() {
        val rnd = Random()
        var l = ArrayListInit(10000000, {rnd.nextInt()})
        println("${measureTimeMillis { l = mergesort(l).toArrayList() }}")
        assertEquals(l, l.sorted())
    }//about 11.5s
    @Test fun manyElementsTest4Threads() {
        val rnd = Random()
        var l = ArrayListInit(10000000, {rnd.nextInt()})
        var res = arrayListOf(1)
        println("${measureTimeMillis { res = mergesort(l, 4).toArrayList() }}")
        assertEquals(res, l.sorted())
    }//about 7.5s
    @Test fun manyElementsTest8Threads() {
        val rnd = Random()
        var l = ArrayListInit(10000000, {rnd.nextInt()})
        var res = arrayListOf(1)
        println("${measureTimeMillis { res = mergesort(l, 8).toArrayList() }}")
        assertEquals(res, l.sorted())
    }//about 14s
    @Test fun manyElementsTest17Threads() {
        val rnd = Random()
        var l = ArrayListInit(10000000, {rnd.nextInt()})
        var res = arrayListOf(1)
        println("${measureTimeMillis { res = mergesort(l, 17).toArrayList() }}")
        assertEquals(res, l.sorted())
    }//about 11s
    @Test fun manyElementsTest2Threads() {
        val rnd = Random()
        var l = ArrayListInit(10000000, {rnd.nextInt()})
        var res = arrayListOf(1)
        println("${measureTimeMillis { res = mergesort(l, 2).toArrayList() }}")
        assertEquals(res, l.sorted())
    }//about 10.5s
    @Test fun kotlinSort() {//just to compare
        val rnd = Random()
        var l = ArrayListInit(10000000, {rnd.nextInt()})
        println("${measureTimeMillis { l.sorted()}}")
    }//about 5s
}