package hw01

import org.junit.Test
import kotlin.test.assertEquals

public class hw01Test {
    Test fun heapsortTest() {
        val a = Array(5,{i -> 2 * i})
        val arrSort = a
        a.heapsort()
        assertEquals(arrSort,a)
    }

    Test fun maxWayTest1() {
        val tree = Node(3, Node(5, Leaf(1), Leaf(4)), Leaf(8))
        assertEquals(12, maxWay_(tree))
    }
    Test fun maxWayTest2() {
        val tree = Node(10, Leaf(0), Leaf(0))
        assertEquals(10, maxWay_(tree))
    }
    Test fun maxWayTest3() {
        val tree = Node(3, Node(5, Leaf(1), Leaf(-4)), Leaf(8))
        assertEquals(11, maxWay_(tree))
    }
    Test fun peanoSumTest() {
        val b : Peano = S(S(Zero()))
        val c : Peano = S(S(S(Zero())))
        assertEquals(5, peanoPrint((peanoSum(c,b))))
    }
    Test fun peanoSubTest() {
        val b : Peano = S(Zero())
        val c : Peano = Zero()
        assertEquals(0, peanoPrint((peanoSub(c,b))))
    }
    Test fun peanoMulTest() {
        val b : Peano = S(S(Zero()))
        val c : Peano = S(S(S(Zero())))
        assertEquals(6, peanoPrint((peanoMul(c,b))))
    }
    Test fun peanoPowTest1() {
        val b : Peano = S(S(Zero()))
        val c : Peano = S(S(S(Zero())))
        assertEquals(9, peanoPrint((peanoDeg(c,b))))
    }
    Test fun peanoPowTest2() {
        val b : Peano = Zero()
        val c : Peano = S(S(S(Zero())))
        assertEquals(1, peanoPrint((peanoDeg(c,b))))
    }

}