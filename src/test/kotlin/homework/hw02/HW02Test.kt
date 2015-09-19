package homework.hw02

import org.junit.Test
import kotlin.test.assertEquals

public class HW02Test {
    Test fun peanoTest() {
        var p1 = intToPeano(3)
        var p2 = intToPeano(5)
        var p3 = intToPeano(2)
        var res = peanoMult(peanoPlus(peanoExp(peanoMinus(p2, p1), p3), p1), p3)
        assertEquals(14, peanoToInt(res, 0))
    }
    Test fun heapSortTest() {
        val array = arrayOf(9, 6, 1, 5, 10, 3, 7, 4, 2, 8)
        val resArray = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        heapSort(array)
        org.junit.Assert.assertArrayEquals(resArray, array)
    }
    Test fun maxWayTest() {
        val tree = Node(8, Node(5, Empty(), Leaf(14)), Node(1, Leaf(10), Leaf(22)))
        assertEquals(31, maxWay({ a, b -> a + b }, 0, tree))
    }
}