package hw08


import org.junit.Test
import kotlin.test.assertEquals

public class avltest {

    @Test
    fun insertion() {
        val t = AVLTree()
        assertEquals(emptyList<Int>(), t.toList())

        t.insert(4)
        assertEquals(listOf(4), t.toList())

        t.insert(2); t.insert(3)
        assertEquals(listOf(3, 2, 4), t.toList())

        t.insert(1)
        assertEquals(listOf(3, 2, 1, 4), t.toList())

        t.insert(4)
        assertEquals(listOf(3, 2, 1, 4), t.toList())
    }


    @Test
    fun deletion() {
        val t = AVLTree()
        t.delete(3)
        assertEquals(emptyList<Int>(), t.toList())

        t.insert(2); t.insert(10); t.insert(14); t.insert(6); t.insert(12); t.insert(100); t.insert(15); t.insert(17)
        t.delete(10)
        assertEquals(listOf(12, 2, 6, 15, 14, 17, 100), t.toList())

        t.delete(14)
        assertEquals(listOf(12, 2, 6, 17, 15, 100), t.toList())
    }


    @Test
    fun searching() {
        var t = AVLTree()
        assertEquals(false, t.contains(5))

        //t.insert(2); t.insert(10); t.insert(14); t.insert(6); t.insert(12); t.insert(100); t.insert(15); t.insert(17)
        t = AVLTree()
        t.fromList(listOf(2, 10, 14, 6, 12, 100, 15, 17))
        assertEquals(true, t.contains(10))
        assertEquals(true, t.contains(6))
        assertEquals(false, t.contains(101))
    }


    @Test
    fun emptyness() {
        val t = AVLTree()
        assertEquals(true, t.isEmpty())

        t.insert(5)
        assertEquals(false, t.isEmpty())

        t.delete(5)
        assertEquals(true, t.isEmpty())
    }

}