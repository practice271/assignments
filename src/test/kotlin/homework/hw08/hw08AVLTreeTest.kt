/*
 * Homework 8 (03.11.2015)
 * Tests for AVLTree
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw08

import org.junit.Test
import kotlin.test.assertEquals
import homework.hw08.AVLTree.Empty
import homework.hw08.AVLTree.Leaf
import homework.hw08.AVLTree.Node
import org.junit.Assert

public class hw08AVLTreeTest {

    /**
     * Test for small left rotation.
     *
     *     5                      10
     *   _     10      -->    5      100
     *       9    100       _   9
     */
    @Test fun balanceTest01() {
        val tree   = Node(5, Empty(), Node(10, Leaf(9), Leaf(100)))
        val result = Node(10, Node(5, Empty(), Leaf(9)), Leaf(100))

        test(result, tree.balance())
    }

    /**
     * Test for small right rotation.
     *
     *        5           2
     *    2     _  -->  1     5
     *  1   3               3   _
     */
    @Test fun balanceTest02() {
        val tree   = Node(5, Node(2, Leaf(1), Leaf(3)), Empty())
        val result = Node(2, Leaf(1), Node(5, Leaf(3), Empty()))

        test(result, tree.balance())
    }

    /**
     * Test for balanced tree.
     *
     *        5                      5
     *    2       10      -->    2       10
     *  1   3   9    100       1   3   9    100
     */
    @Test fun balanceTest03() {
        val tree   = Node(5, Node(2, Leaf(1), Leaf(3)), Node(10, Leaf(9), Leaf(100)))
        val result = Node(5, Node(2, Leaf(1), Leaf(3)), Node(10, Leaf(9), Leaf(100)))

        test(result, tree.balance())
    }

    /**
     * Test for big left rotation.
     *
     *    5                        7
     *  3       10             5       10
     *        7    100  -->  3   6   9    100
     *      6   9
     */
    @Test fun balanceTest04() {
        val tree   = Node(5, Leaf(3), Node(10, Node(7, Leaf(6), Leaf(9)), Leaf(100)))
        val result = Node(7, Node(5, Leaf(3), Leaf(6)), Node(10, Leaf(9), Leaf(100)))

        test(result, tree.balance())
    }

    /**
     * Test for big right rotation.
     *
     *            10                7
     *    5          17         5       10
     *  2     7          -->  2   6   9    17
     *      6   9
     */
    @Test fun balanceTest05() {
        val tree   = Node(10, Node(5, Leaf(2), Node(7, Leaf(6), Leaf(9))), Leaf(17))
        val result = Node(7, Node(5, Leaf(2), Leaf(6)), Node(10, Leaf(9), Leaf(17)))

        test(result, tree.balance())
    }


    /**
     * Insert 7 in tree.
     *
     *        5                      5
     *    2       10      -->    2           10
     *  1   3   9    100       1   3     9      100
     *                                 7   _
     */
    @Test fun insertTest01() {
        val tree   = Node(5, Node(2, Leaf(1), Leaf(3)), Node(10, Leaf(9), Leaf(100)))
        val result = Node(5, Node(2, Leaf(1), Leaf(3)),
                Node(10, Node(9, Leaf(7), Empty()), Leaf(100)))

        test(result, tree.insert(7))
    }

    /**
     * Insert 2 in tree.
     *
     *        5                            5
     *    3         10      -->        3           10
     *  1   4     9    100         1     4     9      100
     *          7   _            _   2       7   _
     */
    @Test fun insertTest02() {
        val tree   = Node(5, Node(3, Leaf(1), Leaf(4)),
                Node(10, Node(9, Leaf(7), Empty()), Leaf(100)))
        val result = Node(5, Node(3, Node(1, Empty(), Leaf(2)), Leaf(4)),
                Node(10, Node(9, Leaf(7), Empty()), Leaf(100)))

        test(result, tree.insert(2))
    }

    /**
     * Insert 9 in tree.
     *
     *        5                      5
     *    2       10      -->    2       10
     *  1   3   _    100       1   3   9    100
     */
    @Test fun insertTest03() {
        val tree   = Node(5, Node(2, Leaf(1), Leaf(3)), Node(10, Empty(), Leaf(100)))
        val result = Node(5, Node(2, Leaf(1), Leaf(3)), Node(10, Leaf(9), Leaf(100)))

        test(result, tree.insert(9))
    }

    /**
     * Insert 6 in tree.
     *
     *    3                 3
     *  2     4    -->    2     5
     *      _   5             4   6
     */
    @Test fun insertTest04() {
        val tree   = Node(3, Leaf(2), Node(4, Empty(), Leaf(5)))
        val result = Node(3, Leaf(2), Node(5, Leaf(4), Leaf(6)))

        test(result, tree.insert(6))
    }

    /**
     * Insert 42 in tree.
     *
     *  _ --> 42
     */
    @Test fun insertTest05() {
        val tree   = Empty<Int>()
        val result = Leaf(42)

        test(result, tree.insert(42))
    }


    /**
     * Remove 9 from tree.
     *
     *        5                      5
     *    2       10      -->    2       10
     *  1   3   9    100       1   3   _    100
     */
    @Test fun removeTest01() {
        val tree   = Node(5, Node(2, Leaf(1), Leaf(3)), Node(10, Leaf(9), Leaf(100)))
        val result = Node(5, Node(2, Leaf(1), Leaf(3)), Node(10, Empty(), Leaf(100)))

        test(result, tree.remove(9))
    }

    /**
     * Remove 5 from tree.
     *
     *        5                      3
     *    2       10      -->    2       10
     *  1   3   9    100       1   _   9    100
     */
    @Test fun removeTest02() {
        val tree   = Node(5, Node(2, Leaf(1), Leaf(3)), Node(10, Leaf(9), Leaf(100)))
        val result = Node(3, Node(2, Leaf(1), Empty()), Node(10, Leaf(9), Leaf(100)))

        test(result, tree.remove(5))
    }

    /**
     * Remove 7 from tree.
     *
     *        5                      5
     *    2       10      -->    2       10
     *  1   3   9    100       1   3   9    100
     */
    @Test fun removeTest03() {
        val tree   = Node(5, Node(2, Leaf(1), Leaf(3)), Node(10, Leaf(9), Leaf(100)))
        val result = Node(5, Node(2, Leaf(1), Leaf(3)), Node(10, Leaf(9), Leaf(100)))

        test(result, tree.remove(7))
    }

    /**
     * Remove 7 from tree.
     *
     *        5              2
     *    2       10  -->  1     5
     *  1   3                  3   _
     */
    @Test fun removeTest04() {
        val tree   = Node(5, Node(2, Leaf(1), Leaf(3)), Leaf(10))
        val result = Node(2, Leaf(1), Node(5, Leaf(3), Empty()))

        test(result, tree.remove(10))
    }

    /**
     * Remove 42 from tree.
     *
     *  42 --> _
     */
    @Test fun removeTest05() {
        val tree   = Leaf(42)
        val result = Empty<Int>()

        test(result, tree.remove(42))
    }


    /**
     * Tests for union.
     */
    @Test fun unionTest01() {
        val tree1  = Node(10, Leaf(7), Leaf(15))
        val tree2  = Leaf(7)
        val result = Node(10, Leaf(7), Leaf(15))

        test(result, tree1.unite(tree2))
    }

    @Test fun unionTest02() {
        val tree1  = Node(10, Leaf(7), Leaf(15))
        val tree2  = Node(3, Leaf(2), Leaf(5))
        val result = Node(7, Node(3, Leaf(2), Leaf(5)), Node(10, Empty(), Leaf(15)))

        test(result, tree1.unite(tree2))
    }

    @Test fun unionTest03() {
        val tree1  = Leaf(7)
        val tree2  = Node(3, Leaf(2), Leaf(5))
        val result = Node(3, Leaf(2), Node(7, Leaf(5), Empty()))

        test(result, tree1.unite(tree2))
    }

    @Test fun unionTest04() {
        val tree1  = Node(10, Leaf(7), Leaf(15))
        val tree2  = Empty<Int>()
        val result = Node(10, Leaf(7), Leaf(15))

        test(result, tree1.unite(tree2))
    }

    @Test fun unionTest05() {
        val tree1  = Node(3, Leaf(2), Leaf(5))
        val tree2  = Node(3, Leaf(2), Leaf(5))
        val result = Node(3, Leaf(2), Leaf(5))

        test(result, tree1.unite(tree2))
    }


    /**
     * Tests for intersection.
     */
    @Test fun intersectionTest01() {
        val tree1  = Node(10, Leaf(7), Leaf(15))
        val tree2  = Leaf(7)
        val result = Leaf(7)

        test(result, tree1.intersect(tree2))
    }

    @Test fun intersectionTest02() {
        val tree1  = Node(10, Leaf(7), Leaf(15))
        val tree2  = Node(3, Leaf(2), Leaf(5))
        val result = Empty<Int>()

        test(result, tree1.intersect(tree2))
    }

    @Test fun intersectionTest03() {
        val tree1  = Node(3, Leaf(2), Leaf(5))
        val tree2  = Node(3, Leaf(2), Leaf(5))
        val result = Node(3, Leaf(2), Leaf(5))

        test(result, tree1.intersect(tree2))
    }

    @Test fun intersectionTest04() {
        val tree1  = Node(10, Leaf(7), Leaf(15))
        val tree2  = Empty<Int>()
        val result = Empty<Int>()

        test(result, tree1.intersect(tree2))
    }

    @Test fun intersectionTest05() {
        val tree1  = Empty<Int>()
        val tree2  = Node(3, Leaf(2), Leaf(5))
        val result = Empty<Int>()

        test(result, tree1.intersect(tree2))
    }


    fun test(result : AVLTree<Int>, tree : AVLTree<Int>) {
        org.junit.Assert.assertArrayEquals(result.toList().toIntArray(), tree.toList().toIntArray())
    }


    /**
     * Tests for search function.
     */
    @Test fun searchTest01() {
        val tree = Node(5, Node(2, Leaf(1), Leaf(3)), Leaf(10))
        assertEquals(true, tree.contains(1))
    }

    @Test fun searchTest02() {
        val tree = Node(5, Node(2, Leaf(1), Leaf(3)), Leaf(10))
        assertEquals(false, tree.contains(17))
    }

    @Test fun searchTest03() {
        val tree = Empty<Int>()
        assertEquals(false, tree.contains(1))
    }

    @Test fun searchTest04() {
        val tree = Node(5, Node(3, Node(1, Empty(), Leaf(2)), Leaf(4)),
                Node(10, Node(9, Leaf(7), Empty()), Leaf(100)))
        assertEquals(true, tree.contains(100))
    }

    @Test fun searchTest05() {
        val tree = Leaf(42)
        assertEquals(true, tree.contains(42))
    }

    /**
     * Test for iterator.
     */
    @Test fun iteratorTest() {
        val tree = Node(7, Node(3, Leaf(2), Leaf(5)), Node(10, Empty(), Leaf(15)))
        val list = linkedListOf(0)
        for (t in tree) list.add(t)
        Assert.assertArrayEquals(arrayOf(0, 7, 3, 2, 5, 10, 15), list.toArray())
    }
}