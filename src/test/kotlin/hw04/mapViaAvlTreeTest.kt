package hw04

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * Created by Alexander on 24.09.2015.
 */
public class avlTreeTest {
    fun <A>treeEquals(a : NodeAvl<A>?, b : NodeAvl<A>?) : Boolean
    {
        if ((a == null) && (b == null)) return true
        if ((a == null) || (b == null)) return false
        if (a.value == b.value)
            return (treeEquals(a.leftChild, b.leftChild) && treeEquals(a.rightChild, b.rightChild))
        else return false
    }
    fun <A>treeAssertEquals(a : NodeAvl<A>?, b : NodeAvl<A>?)
    {
        val res = treeEquals(a,b)
        assertEquals(res, true)
    }
    @Test fun addIncreasingSec()
    {
        var t = NodeAvl(1.toPair(), 0, null, null)
        t = t.insert(2.toPair())
        t = t.insert(3.toPair())
        treeAssertEquals(t, NodeAvl(
                Pair(2,2), 0,
                NodeAvl(Pair(1,1), 0, null, null), NodeAvl(Pair(3,3), 0, null, null)
        ))
    }
    @Test fun addDecreasingSec()
    {
        var t = NodeAvl((-1).toPair(), 0, null, null)
        t = t.insert((-2).toPair())
        t = t.insert((-3).toPair())
        t = t.insert((-4).toPair())
        treeAssertEquals(t, NodeAvl(Pair(-2,-2), 0, NodeAvl(
                Pair(-3,-3), 1,
                NodeAvl(Pair(-4,-4), 0, null, null), null), NodeAvl(Pair(-1,-1), 0, null, null)
        ))
    }
    @Test fun delRoot()
    {
        var t = NodeAvl(1.toPair(), 0, null, null)
        t = t.insert(2.toPair())
        t = t.insert(3.toPair())
        var nt = t.delete(2)
        treeAssertEquals(nt, NodeAvl(Pair(1,1), -1, null, NodeAvl(Pair(3,3), 0, null, null)))
    }
    @Test fun delMiddleNode()
    {
        var t = NodeAvl(1.toPair(), 0, null, null)
        t = t.insert(2.toPair())
        t = t.insert(3.toPair())
        t = t.insert(4.toPair())
        var nt = t.delete(3)
        treeAssertEquals(nt, NodeAvl(
                Pair(2,2), 0,
                NodeAvl(Pair(1,1), 0, null, null), NodeAvl(Pair(4, 4), 0, null, null)
        ))
    }
    @Test fun delLeaf()
    {
        var t = NodeAvl(1.toPair(), 0, null, null)
        t = t.insert(2.toPair())
        t = t.insert(3.toPair())
        var nt = t.delete(1)
        treeAssertEquals(nt, NodeAvl(Pair(2,2), -1, null, NodeAvl(Pair(3, 3), 0, null, null)))
    }
    @Test fun searchLeaf()
    {
        var t = NodeAvl(1.toPair(), 0, null, null)
        t = t.insert(2.toPair())
        t = t.insert(3.toPair())
        assertEquals(t.search(3), 3)
    }
    @Test fun searchNode()
    {
        var t = NodeAvl(1.toPair(), 0, null, null)
        t = t.insert(2.toPair())
        t = t.insert(3.toPair())
        t = t.insert(4.toPair())
        assertEquals(t.search(3), 3)
    }
    @Test fun searchAfterDeletion()
    {
        var t = NodeAvl(1.toPair(), 0, null, null)
        t = t.insert(2.toPair())
        t = t.insert(3.toPair())
        t = t.insert(4.toPair())
        var nt = t.delete(3)
        val res = nt!!.search(3)
        assertNull(res)
    }
}