package hw01

import org.junit.Test
import kotlin.test.assertEquals

public class Task2Test {

    @Test fun general() {
        val tr = Node(5, Node(9, Leaf(7), Leaf(10)), Node(4, Leaf(100), Leaf(12)))
        assertEquals(tr.maxpath(), 109)
    }

    @Test fun innode() {
        val tr = Node(4, Leaf(13), Leaf(12))
        assertEquals(tr.maxpath(), 17)
    }

    @Test fun inleaf() {
        val tr = Leaf(17)
        assertEquals(tr.maxpath(), 17)
    }

    @Test fun inempty() {
        val tr = Empty()
        assertEquals(tr.maxpath(), 0)
    }
}