package homework.hw04

import org.junit.Test
import kotlin.test.assertEquals

public class AVLTree_deletion{
    @Test fun Test1(){
        var tree1 = AVLTree<Int>()
        tree1.insertion(5)
        tree1.insertion(1)
        tree1.insertion(2)

        tree1.deletion(2)
        assertEquals(listOf(1,5),tree1.toList())

        tree1.deletion(5)
        assertEquals(listOf(1),tree1.toList())

        tree1.deletion(1)
        assertEquals(listOf<Int>(),tree1.toList())

    }
}
public class AVLTree_union
{
    var tree1 : AVLTree<Int> = AVLTree<Int>()
    var tree2 : AVLTree<Int> = AVLTree<Int>()

    @Test fun Test1()
    {
        tree2.insertion(2)
        tree2.insertion(1)
        tree2.insertion(5)

        tree1.insertion(4)
        tree1.insertion(5)
        tree1.insertion(10)
        tree1 = tree1.union(tree2)
        assertEquals(listOf(1, 4, 2, 10, 5), tree1.toList())
    }

    @Test fun Test2()
    {
        tree2.insertion(2)
        tree2.insertion(3)
        tree2.insertion(4)

        tree1.insertion(4)

        tree1 = tree1.union(tree2)

        assertEquals(listOf(2,4,3), tree1.toList())
    }
}

public class AVLTree_intersection
{
    var tree1 : AVLTree<Int> = AVLTree<Int>()
    var tree2 : AVLTree<Int> = AVLTree<Int>()

    @Test fun Test1()
    {
        tree2.insertion(2)
        tree2.insertion(3)
        tree2.insertion(4)

        tree1.insertion(5)
        tree1 = tree1.intersection(tree2)
        assertEquals(listOf<Int>(), tree1.toList())
    }

    @Test fun Test2()
    {
        tree2.insertion(2)
        tree2.insertion(3)
        tree2.insertion(4)

        tree1.insertion(4)

        tree1 = tree1.intersection(tree2)

        assertEquals(listOf(4), tree1.toList())
    }
}

public class AVLTree_iterator{
    @Test fun Test(){
        var tree : AVLTree<Int> = AVLTree<Int>()
        tree.insertion(1);
        tree.insertion(2);
        tree.insertion(3);
        tree.insertion(4);
        tree.insertion(5);
        var check = ""
        for (t in tree.toList()) check+=t.toString() + " "
        assertEquals(check, "1 3 5 4 2 ")
    }
}