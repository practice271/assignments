package hw01

/**
 * Created by Mikhail on 19.09.2015.
 */
import org.junit.Test
import kotlin.test.assertEquals

public class hw01Test {

    Test fun HeapSortTest() {
        fun toString(arr: IntArray):String {
            var i: Int = 0
            var s: String = ""
            while (i < arr.size()) {
                s = s + arr[i].toString() + " "
                i++
            }
            return s
        }

        val arr = intArrayOf(2, 1, 3, -100)
        val arr1 = intArrayOf()
        HeapSort.sort(arr)
        HeapSort.sort(arr1)
        val s = toString(arr)
        val s1 = toString(arr1)
        assertEquals(s, "-100 1 2 3 ")
        assertEquals(s1, "")
    }

    Test fun MaxWayTest() {
        val t = genTree(0, 7)
        val res = t.maxWay()
        assertEquals(res, 14)
    }

    Test fun PeanoTest() {
        val x1:Int = toInt(plus(S(S(S(Zero()))), Zero()))
        val x2:Int = toInt(plus(S(S(S(Zero()))), S(S(Zero()))))
        val x3:Int = toInt(minus(S(S(S(Zero()))), S(S(Zero()))))
        val x4:Int = toInt(mult(S(S(S(Zero()))), S(S(Zero()))))
        val x5:Int = toInt(deg(S(S(S(Zero()))), S(S(Zero()))))
        val x6:Int = toInt(minus(S(S(S(Zero()))), Zero()))
        val x7:Int = toInt(mult(S(S(S(Zero()))), Zero()))
        val x8:Int = toInt(deg(S(S(S(Zero()))), Zero()))
        assertEquals(3, x1)
        assertEquals(5, x2)
        assertEquals(1, x3)
        assertEquals(6, x4)
        assertEquals(9, x5)
        assertEquals(3, x6)
        assertEquals(0, x7)
        assertEquals(1, x8)
    }
}