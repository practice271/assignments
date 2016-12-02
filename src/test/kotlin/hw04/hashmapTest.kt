package hw04

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

public class hashmapTest {
    @Test fun insertion() {
        val a = HashMap<Int>(100)
        a.insert(1.toPair())
        org.junit.Assert.assertArrayEquals(a.toList().toTypedArray(), arrayOf(1.toPair()))
        //There is a way to assert Lists, but it's even longer than this.
    }
    @Test fun insertingDifferent() {
        val a = HashMap<Int>(100)
        a.insert(1.toPair())
        a.insert(2.toPair())
        a.insert((-4).toPair())
        org.junit.Assert.assertArrayEquals(a.toList().toTypedArray(),
                                          arrayOf(1.toPair(), 2.toPair(), (-4).toPair()))
    }
    @Test fun insertingSameKeys() {
        val a = HashMap<Int>(100)
        a.insert(Pair(1,1))
        a.insert(Pair(1,2))
        org.junit.Assert.assertArrayEquals(a.toList().toTypedArray(), arrayOf(1.toPair(), Pair(1,2)))
    }
    @Test fun insertingSameVals() {
        val a = HashMap<Int>(100)
        a.insert(Pair(1,1))
        a.insert(Pair(2,1))
        org.junit.Assert.assertArrayEquals(a.toList().toTypedArray(), arrayOf(1.toPair(), Pair(2, 1)))
    }
    @Test fun insertingSameEverything() {
        val a = HashMap<Int>(100)
        a.insert(1.toPair())
        a.insert(1.toPair())
        org.junit.Assert.assertArrayEquals(a.toList().toTypedArray(), arrayOf(1.toPair()))
    }
    @Test fun deletion() {
        var a = HashMap<Int>(100)
        a.insert(1.toPair())
        a.insert(2.toPair())
        a.delete(1)
        org.junit.Assert.assertArrayEquals(a.toList().toTypedArray(), arrayOf(2.toPair()))
    }
    @Test fun deletingNonexistentKey() {
        var a = HashMap<Int>(100)
        a.insert(1.toPair())
        a.delete(2)
        org.junit.Assert.assertArrayEquals(a.toList().toTypedArray(), arrayOf(1.toPair()))
    }
    @Test fun deletingFromEmpty() {
        var a = HashMap<Int>(100)
        a.delete(1)
        org.junit.Assert.assertArrayEquals(a.toList().toTypedArray(), arrayOf())
    }
    @Test fun search() {
        val a = HashMap<Int>(100)
        a.insert(1.toPair())
        a.insert(2.toPair())
        assertEquals(a.search(1), 1)
    }
    @Test fun searchNonexistentKey() {
        val a = HashMap<Int>(100)
        a.insert(1.toPair())
        a.insert(2.toPair())
        assertNull(a.search(3))
    }
    @Test fun searchEmpty() {
        val a = HashMap<Int>(100)
        assertNull(a.search(1))
    }
    @Test fun unite() {
        val a = HashMap<Int>(100)
        val b = NodeAvl(Pair(-2,-2), 0, NodeAvl(
                Pair(-3,-3), 1,
                NodeAvl(Pair(-4,-4), 0, null, null), null), NodeAvl(Pair(-1,-1), 0, null, null)
        )
        a.insert(1.toPair())
        val c = a.unite(b)
        org.junit.Assert.assertArrayEquals(c.toList().toTypedArray(),
                arrayOf(1.toPair(), (-1).toPair(), (-2).toPair(), (-3).toPair(), (-4).toPair()))
    }
    @Test fun uniteEmpty() {
        val a = HashMap<Int>(100)
        val b  : NodeAvl<Int>? = null
        val c = a.unite(b)
        org.junit.Assert.assertArrayEquals(c.toList().toTypedArray(), arrayOf())
    }
    @Test fun uniteHaveSomeSameVals() {
        val a = HashMap<Int>(100)
        val b = NodeAvl(Pair(-2,-2), 0, NodeAvl(
                Pair(-3,-3), 1,
                NodeAvl(Pair(-4,-4), 0, null, null), null), NodeAvl(Pair(-1,-1), 0, null, null)
        )
        a.insert(1.toPair())
        a.insert((-2).toPair())
        a.insert((-4).toPair())
        val c = a.unite(b)
        org.junit.Assert.assertArrayEquals(c.toList().toTypedArray(),
                arrayOf(1.toPair(), (-1).toPair(), (-2).toPair(), (-3).toPair(), (-4).toPair()))
    }

    @Test fun intersectHaveCommon() {
        val a = HashMap<Int>(100)
        val b = NodeAvl(Pair(-2,-2), 0, NodeAvl(
                Pair(-3,-3), 1,
                NodeAvl(Pair(-4,-4), 0, null, null), null), NodeAvl(Pair(-1,-1), 0, null, null)
        )
        a.insert(1.toPair())
        a.insert((-2).toPair())
        val c = a.intersect(b)
        org.junit.Assert.assertArrayEquals(c?.toList()?.toTypedArray(), arrayOf((-2).toPair()))
    }
    @Test fun intersectNoCommon() {
        val a = HashMap<Int>(100)
        val b = NodeAvl(Pair(-2,-2), 0, NodeAvl(
                Pair(-3,-3), 1,
                NodeAvl(Pair(-4,-4), 0, null, null), null), NodeAvl(Pair(-1,-1), 0, null, null)
        )
        a.insert(1.toPair())
        val c = a.intersect(b)
        assertNull(c)
    }
    @Test fun intersectSame() {
        val a = HashMap<Int>(100)
        val b = NodeAvl(Pair(2,2), 0, NodeAvl(
                Pair(1,1), 0,
                null, null), NodeAvl(Pair(3,3), 0, null, null)
        )
        a.insert(3.toPair())
        a.insert(1.toPair())
        a.insert(2.toPair())
        val c = a.intersect(b)
        org.junit.Assert.assertArrayEquals(c?.toList()?.toTypedArray(), arrayOf(1.toPair(), 2.toPair(), 3.toPair()))
    }

    @Test fun iteratorTest() {
        //Testing LCR iterator, others are practically the same
        //We basically test that we visit all nodes in right order and don't break
        val a = HashMap<Int>(10)
        a.insert(1.toPair())
        a.insert(2.toPair())
        a.insert(3.toPair())
        a.insert(Pair(1, 9))
        val res = "1 9 2 3 "
        val sb = StringBuilder()
        for (i in a) sb.append(i.second.toString() + " ")
        assertEquals(res, sb.toString())
    }
}