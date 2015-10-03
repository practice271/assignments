package hw03;

/**
 * Created by Jinx on 27.09.2015.
 */

import org.junit.Test
import kotlin.test.assertEquals

public class hw03Test {


    Test fun addingTest (){
        var t: Node? = null
        t = insert(t, 3)
        assertEquals("null,3,null",          t.treeToText())
        t = insert(t, 4)
        assertEquals("null,3,(null,4,null)", t.treeToText())
        t = insert(t, 5)
        assertEquals("(null,3,null),4,(null,5,null)", t.treeToText())
        t = insert(t, 7)
        assertEquals("(null,3,null),4,(null,5,(null,7,null))", t.treeToText())
        t = insert(t, 2)
        assertEquals("((null,2,null),3,null),4,(null,5,(null,7,null))", t.treeToText())
        t = insert(t, 1)
        assertEquals("((null,1,null),2,(null,3,null)),4,(null,5,(null,7,null))", t.treeToText())
    }

    Test fun findingTest() {
        var t: Node? = null
        t = insert(t, 4)
        t = insert(t, 7)
        t = insert(t, 1)
        t = insert(t, 5)
        assertEquals(true,  find(t, 4))
        assertEquals(false, find(t, 6))
        assertEquals(true,  find(t, 7))
        assertEquals(true,  find(t, 1))
        assertEquals(true,  find(t, 5))
    }

    Test fun removingTest (){
        var t: Node? = null
        t = insert(t, 4)
        t = insert(t, 7)
        t = insert(t, 1)
        t = insert(t, 5)
        t = remove(t, 5)
        assertEquals("(null,1,null),4,(null,7,null)", t.treeToText())
        t = remove(t, 7)
        assertEquals("(null,1,null),4,null", t.treeToText())
        t = remove(t, 4)
        assertEquals("null,1,null", t.treeToText())
    }


    Test fun deletingAndFindingFromEmptyTree(){
        var q: Node? = null
        assertEquals(false, find(q, 2))
        q = remove(q,2)
        assertEquals("null", q.treeToText())
    }
}
