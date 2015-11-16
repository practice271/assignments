package hw8


import org.junit.Test
import kotlin.test.assertEquals

public class TestAVL {

    fun <T>compareIterators(a: Iterator<out T>, b: Iterator<out T>) {
        while (a.hasNext() && b.hasNext())
            assertEquals(a.next(), b.next())
        assertEquals(a.hasNext(), b.hasNext())
    }


    @Test fun iteratorTest() {
        val s = TreeSet<Int>(Nil())
        arrayOf(5, 4, 6, 2, 1, 3, 7).forEach { v -> s.insert(v) }
        compareIterators(arrayOf(1, 2, 3, 4, 5, 6, 7).iterator(),
                         s.iterator())
    }

    @Test fun removeTest() {
        val s = TreeSet<Int>(Nil())
        arrayOf(5, 4, 6, 2, 1, 3, 7).forEach { v -> s.insert(v) }
        s.remove(2)
        s.remove(4)
        compareIterators(arrayOf(1, 3, 5, 6, 7).iterator(), s.iterator())
    }

    @Test fun intersectionTest() {
        val s = TreeSet<Int>(Nil())
        val t = TreeSet<Int>(Nil())
        arrayOf(5, 2, 3, 1, 4).forEach { v -> s.insert(v) }
        arrayOf(6, 5, 10, 3).forEach { v -> t.insert(v) }
        val u = s.intersect(t)
        compareIterators(arrayOf(3, 5).iterator(), u.iterator())
    }

    @Test fun unionTest() {
        val s = TreeSet<Int>(Nil())
        val t = TreeSet<Int>(Nil())
        arrayOf(1, 3, 5).forEach { v -> s.insert(v) }
        arrayOf(2, 4, 6).forEach { v -> t.insert(v) }
        val u = s.union(t)
        compareIterators(arrayOf(1, 2, 3, 4, 5, 6).iterator(), u.iterator())
    }
}