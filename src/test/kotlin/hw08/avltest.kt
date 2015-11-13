package hw08

import org.junit.Test
import java.util.LinkedList
import kotlin.test.assertEquals

public class avltest {

    @Test
    fun searching() { // uses iterators in function 'contains'
        val t = AVLTree()
        assertEquals(false, t.contains(5))

        t.insert(2); t.insert(10); t.insert(14); t.insert(6); t.insert(12); t.insert(100); t.insert(15); t.insert(17)
        assertEquals(true, t.contains(10))
        assertEquals(true, t.contains(6))
        assertEquals(false, t.contains(13))
    }

    @Test
    fun iteratorTest() {
        val t = AVLTree()
        var hashIter = t.iterator()
        assertEquals(!t.isEmpty(), hashIter.hasNext())

        t.insert(1); t.insert(18)
        val iter = t.iterator()
        assertEquals(true, iter.hasNext())
        assertEquals(1, iter.next())
        assertEquals(18, iter.next())
        assertEquals(false, iter.hasNext())
    }

    @Test
    fun forEachTest() {
        val t = AVLTree()
        t.insert(2); t.insert(10); t.insert(14); t.insert(6); t.insert(12); t.insert(100); t.insert(15); t.insert(17)
        val list = LinkedList<Int>()
        t.forEach { list.add(it) }
        assertEquals(listOf(10, 2, 6, 14, 12, 17, 15, 100), list)
    }

}
