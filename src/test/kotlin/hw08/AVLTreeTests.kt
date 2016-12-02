package hw08

import org.junit.Test
import kotlin.test.assertEquals

public class AVLTree_insert
{
    var tree : AVLTree = AVLTree()

    @Test fun Test0()
    {
        assertEquals(emptyList<Int>(), tree.toList())
    }

    @Test fun Test1()
    {
        tree.insert(2)

        assertEquals(listOf(2), tree.toList())
    }

    @Test fun Test2()
    {
        tree.insert(4)
        tree.insert(2)
        tree.insert(3)
        tree.insert(5)

        assertEquals(listOf(4, 2, 3, 5), tree.toList())
    }
}

public class AVLTree_delete
{
    var tree : AVLTree = AVLTree()

    @Test fun Test0()
    {
        assertEquals(emptyList<Int>(), tree.toList())
    }

    @Test fun Test1()
    {
        tree.insert(2)
        tree.delete(2)

        assertEquals(emptyList<Int>(), tree.toList())
    }

    @Test fun Test2()
    {
        tree.insert(2)
        tree.delete(3)

        assertEquals(listOf(2), tree.toList())
    }

    @Test fun Test3()
    {
        tree.insert(4)
        tree.insert(2)
        tree.insert(3)
        tree.insert(5)

        tree.delete(2)

        assertEquals(listOf(4, 3, 5), tree.toList())
    }
}

public class AVLTree_search
{
    var tree : AVLTree = AVLTree()

    @Test fun Test0()
    {
        assertEquals(false, tree.search(2))
    }

    @Test fun Test1()
    {
        tree.insert(2)

        assertEquals(true, tree.search(2))
    }

    @Test fun Test2()
    {
        tree.insert(4)
        tree.insert(2)
        tree.insert(3)
        tree.insert(5)

        assertEquals(true, tree.search(3))
    }
}

public class AVLTree_intersection
{
    var tree1 : AVLTree = AVLTree()
    var tree2 : AVLTree = AVLTree()

    @Test fun Test0()
    {
        tree2.insert(2)

        tree1 = tree1.intersection(tree2)

        assertEquals(emptyList<Int>(), tree1.toList())
    }

    @Test fun Test1()
    {
        tree2.insert(2)
        tree1.insert(2)

        tree1 = tree1.intersection(tree2)

        assertEquals(listOf(2), tree1.toList())
    }
}

public class AVLTree_union
{
    var tree1 : AVLTree = AVLTree()
    var tree2 : AVLTree = AVLTree()

    @Test fun Test0()
    {
        tree2.insert(2)

        tree1 = tree1.union(tree2)

        assertEquals(listOf(2), tree1.toList())
    }

    @Test fun Test1()
    {
        tree2.insert(2)
        tree1.insert(4)

        tree1 = tree1.union(tree2)

        assertEquals(listOf(2, 4), tree1.toList())
    }
}
