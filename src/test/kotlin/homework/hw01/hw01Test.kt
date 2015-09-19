/*
Homework 1 (08.09.2015)
Tests for tasks 1 - 4

Author: Mikhail Kita, group 271
*/

package homework.hw01

import org.junit.Test
import kotlin.test.assertEquals

public class hw01Test {

    //compares two array
    fun Array<out Any>.areEqual(arr : Array<out Any>) : Boolean {
        if (this.size() != arr.size()) return false
        for (i in 0 .. this.size() - 1) {
            if (this[i] != arr[i]) return false
        }
        return true
    }

    //tests for heapSort
    Test fun heapSortTest01() {
        val array  = arrayOf(8, 3, 6, 5, 2, 10, 7, 9, 1, 4)
        val result = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        heapSort(array)
        assertEquals(true, array.areEqual(result))
    }

    Test fun heapSortTest02() {
        val array  = arrayOf(9, 4, 6, 3, 2, 1, 5, 7, 10, 8)
        val result = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        heapSort(array)
        assertEquals(true, array.areEqual(result))
    }

    Test fun heapSortTest03() {
        val array  = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val result = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        heapSort(array)
        assertEquals(true, array.areEqual(result))
    }

    Test fun heapSortTest04() {
        val array  = Array(1000, {i -> 1000 - i}) // [1000, 999, 998 ... ]
        val result = Array(1000, {i -> i + 1})    // [1, 2, 3 ... ]
        heapSort(array)
        assertEquals(true, array.areEqual(result))
    }

    //tests for maxWay function
    Test fun maxWayTest01() {
        val tree = Node(2, Node(3, Leaf(4), Empty()), Node(3, Leaf(100), Leaf(7)))
        assertEquals(105, maxWay({x, y -> x + y}, 0, tree))
    }

    Test fun maxWayTest02() {
        val tree = Node(5, Node(3, Leaf(7), Empty()), Node(2, Leaf(10), Leaf(2)))
        assertEquals(17, maxWay({x, y -> x + y}, 0, tree))
    }

    //test for fold: sum of all elements in tree
    Test fun foldTest01() {
        val tree = Node(2, Node(3, Leaf(4), Empty()), Node(3, Leaf(100), Leaf(7)))
        assertEquals(119, fold({x, y -> x + y}, {x, y -> x + y}, 0, tree))
    }

    Test fun foldTest02() {
        val tree = Node(2, Node(3, Leaf(4), Empty()), Node(3, Leaf(100), Leaf(7)))
        assertEquals(100, fold({x, y -> Math.max(x, y)}, {x, y -> Math.max(x, y)}, 0, tree))
    }

    Test fun foldTest03() {
        val tree = Node(2, Node(3, Leaf(4), Empty()), Node(3, Leaf(100), Leaf(7)))
        assertEquals(105, fold({x, y -> x + y}, {x, y -> Math.max(x, y)}, 0, tree))
    }

    //test for arithmetics with peano numbers
    Test fun peanoTest01() {
        val a = peanoGen(2)
        val b = peanoGen(2)

        assertEquals(peanoToInt(S(S(S(S(Zero()))))), peanoToInt(peanoSum(a, b)))
        assertEquals(peanoToInt(Zero()), peanoToInt(peanoSub(a, b)))
        assertEquals(peanoToInt(S(S(S(S(Zero()))))), peanoToInt(peanoMult(a, b)))
        assertEquals(peanoToInt(S(S(S(S(Zero()))))), peanoToInt(peanoExp(a, b)))
    }

    Test fun peanoTest02() {
        val a = peanoGen(3)
        val b = peanoGen(2)

        assertEquals(peanoToInt(peanoGen(5)), peanoToInt(peanoSum(a, b)))
        assertEquals(peanoToInt(S(Zero())), peanoToInt(peanoSub(a, b)))
        assertEquals(peanoToInt(peanoGen(6)), peanoToInt(peanoMult(a, b)))
        assertEquals(peanoToInt(peanoGen(9)), peanoToInt(peanoExp(a, b)))
    }

    Test fun peanoTest03() {
        val a = peanoGen(7)
        val b = peanoGen(3)

        assertEquals(peanoToInt(peanoGen(10)), peanoToInt(peanoSum(a, b)))
        assertEquals(peanoToInt(S(S(S(S(Zero()))))), peanoToInt(peanoSub(a, b)))
        assertEquals(peanoToInt(peanoGen(21)), peanoToInt(peanoMult(a, b)))
        assertEquals(peanoToInt(peanoGen(343)), peanoToInt(peanoExp(a, b)))
    }
}