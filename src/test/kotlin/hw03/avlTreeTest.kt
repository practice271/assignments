package hw03

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * Created by Alexander on 24.09.2015.
 */
public class avlTreeTest {
    fun <A>treeEquals(a : NodeAvl<A>?, b : NodeAvl<A>?) : Boolean
    {
        if ((a== null) && (b == null)) return true
        if ((a== null) || (b == null)) return false
        if (a.value == b.value)
            return (treeEquals(a.leftChild, b.leftChild) && treeEquals(a.rightChild, b.rightChild))
        else return false
    }
    fun <A>treeAssertEquals(a : NodeAvl<A>?, b : NodeAvl<A>?)
    {
        val res = treeEquals(a,b)
        assertEquals(res, true)
    }
    @Test fun addToEmpty()
    {
        val t = addInt(1, null)
        treeAssertEquals(t, NodeAvl(Pair(1,1), 0, null, null))
    }
    @Test fun addIncreasingSec()
    {
        var t = addInt(1, null)
        t = addInt(2, t)
        t = addInt(3, t)
        treeAssertEquals(t, NodeAvl(Pair(2,2), 0, NodeAvl(Pair(1,1), 0, null, null), NodeAvl(Pair(3,3), 0, null, null)))
    }
    @Test fun addDecreasingSec()
    {
        var t = addInt(-1, null)
        t = addInt(-2, t)
        t = addInt(-3, t)
        t = addInt(-4, t)
        treeAssertEquals(t, NodeAvl(Pair(-2,-2), 0, NodeAvl(Pair(-3,-3), 1, NodeAvl(Pair(-4,-4), 0, null, null), null), NodeAvl(Pair(-1,-1), 0, null, null)))
    }
    @Test fun delFromEmpty()
    {
        treeAssertEquals(del(Pair(1,1), null), null)
    }
    @Test fun delRoot()
    {
        var t = addInt(1, null)
        t = addInt(2, t)
        t = addInt(3, t)
        var nt = del(Pair(2,2), t)
        treeAssertEquals(nt, NodeAvl(Pair(1,1), -1, null, NodeAvl(Pair(3,3), 0, null, null)))
    }
    @Test fun delMiddleNode()
    {
        var t = addInt(1, null)
        t = addInt(2, t)
        t = addInt(3, t)
        t = addInt(4, t)
        var nt = del(Pair(3,3), t)
        treeAssertEquals(nt, NodeAvl(Pair(2,2), 0, NodeAvl(Pair(1,1), 0, null, null), NodeAvl(Pair(4, 4), 0, null, null)))
    }
    @Test fun delLeaf()
    {
        var t = addInt(1, null)
        t = addInt(2, t)
        t = addInt(3, t)
        var nt = del(Pair(1,1), t)
        treeAssertEquals(nt, NodeAvl(Pair(2,2), -1, null, NodeAvl(Pair(3, 3), 0, null, null)))
    }
    @Test fun searchLeaf()
    {
        var t = addInt(1, null)
        t = addInt(2, t)
        t = addInt(3, t)
        assertEquals(search(3, t), 3)//second '3' is value, not a key
    }
    @Test fun searchNode()
    {
        var t = addInt(1, null)
        t = addInt(2, t)
        t = addInt(3, t)
        t = addInt(4, t)
        assertEquals(search(3, t), 3)//second '3' is value, not a key
    }
    @Test fun searchEmpty()
    {
        assertNull(search(3, null : NodeAvl<Int>?))
    }
    @Test fun searchAfterDeletion()
    {
        var t = addInt(1, null)
        t = addInt(2, t)
        t = addInt(3, t)
        t = addInt(4, t)
        var nt = del(Pair(3,3), t)
        val res = search(3, nt)
        assertNull(res)
    }
}