package hw08

import org.junit.Assert
import org.junit.Test

public class IterstorsTests
{
    @Test fun AVLTree_IteratorTest()
    {
        val tree = AVLTree()
        tree.insert(3)
        tree.insert(7)
        tree.insert(10)
        tree.insert(2)
        tree.insert(5)
        tree.insert(15)
        val list = linkedListOf(0)
        val treeList = tree.toList()
        for (t in treeList) list.add(t)
        Assert.assertArrayEquals(arrayOf(0, 7, 3, 2, 5, 10, 15), list.toArray())
    }

    @Test fun HashTable_IteratorTest()
    {
        val table = HashTable(512)
        table.insert(5)
        table.insert(2)
        table.insert(1)
        table.insert(15)
        table.insert(9)
        val list  = linkedListOf(0)
        val hashList = table.toList()
        for (t in hashList) list.add(t)
        Assert.assertArrayEquals(arrayOf(0, 1, 2, 5, 9, 15), list.toArray())
     }
}