package hw01

/**
 * Created by Jinx on 20.09.2015.
 */

import org.junit.Test
import kotlin.test.assertEquals


public class hw01Test {
    Test fun sortTest1() {
        var a = arrayOf (4,2,6,8,1)
        val b = arrayOf (1,2,4,6,8)
        var i = 0
        a.HeapSort()
        while (i < a.size()) {
            assertEquals(b[i], a[i])
            i++
        }
    }
    fun sortTest2() {
        var c = arrayOf(-2, -1, 0, 1, 2)
        var d = arrayOf(0, 2, -2, -1, 1)
        var i = 0
        c.HeapSort()
        while (i < c.size()) {
            assertEquals(d[i], c[i])
            i++
        }
    }
    fun sortTest3() {
        var e: Array<Int> = emptyArray()
        var f: Array<Int> = emptyArray()
        e.HeapSort()
        assertEquals(e, f)
    }

    Test fun treeTest() {
        val tree = genTree(0, 8)
        assertEquals(17, tree.maxInWays())
    }
    Test fun peanoTest() {
        val first  = 5
        val second = 3
        assertEquals(8  , peanoToInt(plus(intToPeano(first), intToPeano(second))))
        assertEquals(2  , peanoToInt(minus(intToPeano(first), intToPeano(second))))
        assertEquals(15 , peanoToInt(mult(intToPeano(first), intToPeano(second))))
        assertEquals(125, peanoToInt(pow(intToPeano(first), intToPeano(second))))
        assertEquals(0, peanoToInt(pow(intToPeano(0),     intToPeano(10))))
        assertEquals(0, peanoToInt(mult(intToPeano(123), intToPeano(0))))
        assertEquals(1, peanoToInt(pow(intToPeano(123), intToPeano(0))))
        assertEquals(100, peanoToInt(mult(minus(intToPeano(42), intToPeano(32)), intToPeano(10))))
    }
}
