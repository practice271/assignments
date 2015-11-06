package hw08

import org.junit.Test
import org.junit.Assert.*
import java.util.LinkedList
import java.util.Arrays

public class AVLtreeIteratorTest {
    @Test fun hasNextTest() {
        var tree = AVLtree<Int, Int>()
        assertTrue(!tree.iterator().hasNext())
        tree.insert(1, 1)
        assertTrue(tree.iterator().hasNext())
        tree.delete(1)
        assertTrue(!tree.iterator().hasNext())
    }

    @Test fun testIterator() {
        var tree = AVLtree<String, Int>()
        for (i in 0..1001)
            tree.insert(i.toString(), i)
        var resultList = LinkedList<Int>()
        for (i in 0..1001)
            resultList.add(i)
        var treeToList = LinkedList<Int>()
        for (elem in tree)
            treeToList.add(elem.second)
        val array = treeToList.toArray()
        Arrays.sort(array)
        assertArrayEquals(resultList.toArray(), array)
    }
}