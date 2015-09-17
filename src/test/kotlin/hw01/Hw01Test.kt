package hw01

import org.junit.Test
import kotlin.test.assertEquals
import org.junit.Assert.assertArrayEquals;

public class Hw01Test {
    Test fun task01Test_lib() {
        val sortedArr = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val unsortedArr = arrayOf(1, 4, 8, 7, 5, 2, 6, 10, 3, 9)
        unsortedArr.heapsort()
        assertArrayEquals(sortedArr, unsortedArr)
    }

    Test fun task02Test() {
        val tree = Node(4, Node(3, intLeaf(1), Node(2, intLeaf(1), Empty())), intLeaf(5))
        assertEquals(10, tree.maxPath())
    }

    Test fun task03Test() {
        val tree = Node(4, Node(3, intLeaf(1), Node(2, intLeaf(1), Empty())), intLeaf(5))
        assertEquals(16, foldDown({_, b -> b}, {a, b -> a + b}, 0, tree))
    }

    Test fun task04Test() {
        assertEquals(5, peanoSub(peanoPow(2.toPeano(), 3.toPeano()), 3.toPeano()).toInt())
    }
}