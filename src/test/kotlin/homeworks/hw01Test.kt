package homeworks

import org.junit.Test
import org.junit.Assert;
import kotlin.test.assertEquals

public class hw01Test {
//Heapsort
    @Test
    fun TestHeapSort1() {
        val heap   = arrayOf(123, 5, 44, 3, 32, 65, 76, 67, 86, 34)
        heapSort(heap)
        var expect = arrayOf(3, 5, 32, 34, 44, 65, 67, 76, 86, 123)
        Assert.assertArrayEquals(expect, heap)
    }

    @Test
    fun TestHeapSort2() {
        val heap   = arrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
        heapSort(heap)
        var expect = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        Assert.assertArrayEquals(expect, heap)
    }

    @Test
    fun TestHeapSort3() {
        val heap   = arrayOf(0, 0, 0, 1, 0)
        heapSort(heap)
        var expect = arrayOf(0, 0, 0, 0, 1)
        Assert.assertArrayEquals(expect, heap)
    }

    @Test
    fun TestHeapSort4() {
        val heap   = arrayOf(-1, -2, -4, -3)
        heapSort(heap)
        var expect = arrayOf(-4, -3, -2, -1)
        Assert.assertArrayEquals(expect, heap)
    }
//Sum
    @Test
    fun TestMaximumSum1() {
        val tree = Node(15,
                Node(27,
                        Node(4,
                                Leaf(5),
                                Empty()),
                        Leaf(10)),
                Node(34,
                        Leaf(12),
                        Node(1,
                                Leaf(8),
                                Leaf(6))))
        val result = tree.searchMaximumSum()
        val expect = 61
        assertEquals(expect, result)
    }

    @Test
    fun TestMaximumSum2() {
        val tree = Node(15,
                Node(27,
                        Node(4,
                                Node(5,
                                        Leaf(6),
                                        Empty()),
                                Empty()),
                        Empty()),
                Leaf(34))
        val result = tree.searchMaximumSum()
        val expect = 57
        assertEquals(expect, result)
    }

    @Test
    fun TestMaximumSum3() {
        val tree = Node(15,
                Node(27,
                        Node(4,
                                Node(5,
                                        Leaf(6),
                                        Empty()),
                                Empty()),
                        Empty()),
                Leaf(79))
        val result = tree.searchMaximumSum()
        val expect = 94
        assertEquals(expect, result)
    }

    @Test
    fun TestMaximumSum4() {
        val tree = Leaf(15)
        val result = tree.searchMaximumSum()
        val expect = 15
        assertEquals(expect, result)
    }
//Peano
    @Test
    fun TestAddPeano1() {
        val result = toInt(addition((S(S(S(S(S(Zero())))))), S(S(S(Zero())))))
        val expect = 8
        assertEquals(expect, result)
    }

    @Test
    fun TestAddPeano2() {
        val result = toInt(addition(Zero(), S(S(S(Zero())))))
        val expect = 3
        assertEquals(expect, result)
    }

    @Test
    fun TestSubPeano1() {
        val result = toInt(subtraction((S(S(S(S(S(Zero())))))), S(S(S(Zero())))))
        val expect = 2
        assertEquals(expect, result)
    }

    @Test
    fun TestSubPeano2() {
        val result = toInt(subtraction(Zero(), S(S(S(Zero())))))
        val expect = 0
        assertEquals(expect, result)
    }

    @Test
    fun TestMultPeano1() {
        val result = toInt(multiplication((S(S(S(S(S(Zero())))))), S(S(S(Zero())))))
        val expect = 15
        assertEquals(expect, result)
    }

    @Test
    fun TestMultPeano2() {
        val result = toInt(subtraction(Zero(), S(S(S(Zero())))))
        val expect = 0
        assertEquals(expect, result)
    }

    @Test
    fun TestPowPeano1() {
        val result = toInt(pow((S(S(S(S(S(Zero())))))), S(S(S(Zero())))))
        val expect = 125
        assertEquals(expect, result)
    }

    @Test
    fun TestPowPeano2() {
        val result = toInt(pow(Zero(), S(S(S(Zero())))))
        val expect = 0
        assertEquals(expect, result)
    }
}