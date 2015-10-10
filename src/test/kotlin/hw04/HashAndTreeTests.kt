package hw04

import org.junit.Test
import kotlin.test.assertEquals

public class HashAndTree_union
{
    var table : HashTable = HashTable(512)
    var tree : AVLTree = AVLTree()

    @Test fun Test0()
    {
        table.insert(2)
        tree.insert(4)

        tree = tree.union(table)

        assertEquals(listOf(2, 4), tree.toList())
    }

    @Test fun Test1()
    {
        table.insert(2)
        tree.insert(4)

        table = table.union(tree)

        assertEquals(listOf(2, 4), table.toList())
    }
}

public class HashAndTree_intersection
{
    var table : HashTable = HashTable(512)
    var tree : AVLTree = AVLTree()

    @Test fun Test0()
    {
        table.insert(2)
        tree.insert(4)
        tree.insert(2)

        tree = tree.intersection(table)

        assertEquals(listOf(2), tree.toList())
    }

    @Test fun Test1()
    {
        table.insert(2)
        tree.insert(4)

        table = table.intersection(tree)

        assertEquals(emptyList<Int>(), table.toList())
    }
}
