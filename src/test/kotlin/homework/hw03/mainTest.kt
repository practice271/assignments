package homework.hw03

import org.junit.Test
import kotlin.test.assertEquals
public class hw03Test {
    @Test fun insert(){
        var tree:Node?   = null
        tree = insert(9, tree)
        assertEquals("null,9,null", tree.treeToText())
        tree = insert(10,tree)
        assertEquals("null,9,(null,10,null)", tree.treeToText())
        tree = insert(5,tree)
        assertEquals("(null,5,null),9,(null,10,null)", tree.treeToText())
        tree = insert(100,tree)
        assertEquals("(null,5,null),9,(null,10,(null,100,null))", tree.treeToText())
        tree = insert(2,tree)
        assertEquals("((null,2,null),5,null),9,(null,10,(null,100,null))", tree.treeToText())

    }

    @Test fun find(){
        var tree: Node? = null
        val array = arrayOf(9,10,5,100,2)
        for (x in array){
            tree = insert(x,tree)
        }
        assertEquals(false,  find(55, tree))
        assertEquals(false, find(1, tree))
        assertEquals(true,  find(5, tree))
        assertEquals(true,  find(100, tree))
        assertEquals(true,  find(2, tree))
        assertEquals(true, find(9, tree))
    }

    @Test fun remove(){
        var tree: Node? = null
        val array = arrayOf(9,10,5,100,2)
        for (x in array){
            tree = insert(x,tree)
        }
        tree = remove(2, tree)
        assertEquals("(null,5,null),9,(null,10,(null,100,null))", tree.treeToText())
        tree = remove(100, tree)
        assertEquals("(null,5,null),9,(null,10,null)", tree.treeToText())
        tree = remove(5,tree)
        assertEquals("null,9,(null,10,null)", tree.treeToText())
        tree = remove(10,tree)
        assertEquals("null,9,null", tree.treeToText())
        tree = remove(9,tree)
        assertEquals("null", tree.treeToText())
        //Removing non-existent element
        tree = remove(412222,tree)
        assertEquals("null", tree.treeToText())
    }
}