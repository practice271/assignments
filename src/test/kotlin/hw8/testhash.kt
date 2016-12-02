package hw8


import org.junit.Test
import kotlin.test.assertEquals

public class TestHash {

    fun <T>compareArrays(a: Array<out T>, b: Array<out T>) {
        assertEquals(a.size, b.size)
        a.indices.forEach { i -> assertEquals(a[i], b[i]) }
    }

    fun <T>compareIterators(a: Iterator<out T>, b: Iterator<out T>) {
        while (a.hasNext() && b.hasNext())
            assertEquals(a.next(), b.next())
        assertEquals(a.hasNext(), b.hasNext())
    }

    @Test fun iteratorTest() {
        val s = HashTable<Int>(100)
        arrayOf(1, 2, 3, 4, 5, 6, 7).forEach { v -> s.insert(v) }
        compareIterators(arrayOf(7, 6, 5, 4, 3, 2, 1).iterator(), s.iterator())
    }

    @Test fun removeTest() {
        val s = HashTable<Int>(100)
        arrayOf(5, 4, 6, 2, 1, 3, 7).forEach { v -> s.insert(v) }
        s.remove(2)
        s.remove(4)
        val a: Array<out Int> = s.toTypedArray().sortedArray()
        compareArrays(arrayOf(1, 3, 5, 6, 7), a)
    }

    @Test fun intersectionTest() {
        val s = HashTable<Int>(100)
        val t = HashTable<Int>(100)
        arrayOf(5, 2, 3, 1, 4).forEach { v -> s.insert(v) }
        arrayOf(6, 5, 10, 3).forEach { v -> t.insert(v) }
        val u = s.intersect(t)
        val a: Array<out Int> = u.toTypedArray().sortedArray()
        compareArrays(arrayOf(3, 5), a)
    }

    @Test fun unionTest() {
        val s = HashTable<Int>(100)
        val t = HashTable<Int>(100)
        arrayOf(1, 3, 5).forEach { v -> s.insert(v) }
        arrayOf(2, 4, 6).forEach { v -> t.insert(v) }
        val u = s.union(t)
        val a: Array<out Int> = u.toTypedArray().sortedArray()
        compareArrays(arrayOf(1, 2, 3, 4, 5, 6), a)
    }
}