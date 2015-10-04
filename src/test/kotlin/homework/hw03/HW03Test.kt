package homework.hw03

import org.junit.Test
import kotlin.test.assertEquals

public class HW03Test {
    Test fun insertingTest() {
        var tree: Node? = null
        tree = insert(5, tree)
        assertEquals("null,5,null", tree.treeToText())
        tree = insert(6, tree)
        assertEquals("null,5,(null,6,null)", tree.treeToText())
        tree = insert(7, tree)
        assertEquals("(null,5,null),6,(null,7,null)", tree.treeToText())
        tree = insert(9, tree)
        assertEquals("(null,5,null),6,(null,7,(null,9,null))", tree.treeToText())
        tree = insert(4, tree)
        assertEquals("((null,4,null),5,null),6,(null,7,(null,9,null))", tree.treeToText())
        tree = insert(1, tree)
        assertEquals("((null,1,null),4,(null,5,null)),6,(null,7,(null,9,null))", tree.treeToText())
    }
    Test fun findingTest() {
        var tree: Node? = null
        assertEquals(false, find(4, tree))
        tree = insert(2, tree)
        tree = insert(5, tree)
        tree = insert(1, tree)
        tree = insert(3, tree)
        assertEquals(true, find(2, tree))
        assertEquals(false, find(6, tree))
        assertEquals(true, find(5, tree))
        assertEquals(true, find(1, tree))
        assertEquals(true, find(3, tree))
    }
    Test fun removingTest() {
        var tree: Node? = null
        tree = remove(7, tree)
        assertEquals("null", tree.treeToText())
        tree = insert(4, tree)
        tree = insert(9, tree)
        tree = insert(1, tree)
        tree = insert(7, tree)
        tree = remove(7, tree)
        assertEquals("(null,1,null),4,(null,9,null)", tree.treeToText())
        tree = remove(9, tree)
        assertEquals("(null,1,null),4,null", tree.treeToText())
        tree = remove(4, tree)
        assertEquals("null,1,null", tree.treeToText())
    }
}