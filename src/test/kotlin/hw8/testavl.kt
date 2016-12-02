package hw8


import org.junit.Test
import kotlin.test.assertEquals

public class TestAVL {

    fun <T>compareArrays(a: Array<out T>, b: Array<out T>) {
        assertEquals(a.size, b.size)
        a.indices.forEach { i -> assertEquals(a[i], b[i]) }
    }


    @Test fun iteratorTest() {
        val s = TreeSet<Int>(Nil())
        arrayOf(5, 4, 6, 2, 1, 3, 7).forEach { v -> s.insert(v) }
        val a: Array<out Int> = s.toTypedArray().sortedArray()
        compareArrays(arrayOf(1, 2, 3, 4, 5, 6, 7), a)
    }

    @Test fun removeTest() {
        val s = TreeSet<Int>(Nil())
        arrayOf(5, 4, 6, 2, 1, 3, 7).forEach { v -> s.insert(v) }
        s.remove(2)
        s.remove(4)
        val a: Array<out Int> = s.toTypedArray().sortedArray()
        compareArrays(arrayOf(1, 3, 5, 6, 7), a)
    }

    @Test fun intersectionTest() {
        val s = TreeSet<Int>(Nil())
        val t = TreeSet<Int>(Nil())
        arrayOf(5, 2, 3, 1, 4).forEach { v -> s.insert(v) }
        arrayOf(6, 5, 10, 3).forEach { v -> t.insert(v) }
        val u = s.intersect(t)
        val a: Array<out Int> = u.toTypedArray().sortedArray()
        compareArrays(arrayOf(3, 5), a)
    }

    @Test fun unionTest() {
        val s = TreeSet<Int>(Nil())
        val t = TreeSet<Int>(Nil())
        arrayOf(1, 3, 5).forEach { v -> s.insert(v) }
        arrayOf(2, 4, 6).forEach { v -> t.insert(v) }
        val u = s.union(t)
        val a: Array<out Int> = u.toTypedArray().sortedArray()
        compareArrays(arrayOf(1, 2, 3, 4, 5, 6), a)
    }
}