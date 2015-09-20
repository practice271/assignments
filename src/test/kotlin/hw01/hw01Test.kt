package hw01

/**
 * Created by Jinx on 20.09.2015.
 */

import org.junit.Test
import kotlin.test.assertEquals


public class hw01Test {
    Test fun sortTest() {
        var a = arrayOf (4,2,6,8,1)
        val b = arrayOf (1,2,4,6,8)
        var i = 0
        a.HeapSort()
        while (i < a.size()) {
            assertEquals(b[i], a[i])
            i++
        }
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
    }
}
