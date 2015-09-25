/*
Homework 3 (22.09.2015)

Author: Mikhail Kita, group 271
*/

package homework.hw03

import org.junit.Test
import kotlin.test.assertEquals

public class hw03Test {

    //converts tree to list
    fun AVLTree.toList() : List<Int> {
        when(this) {
            is Empty -> return listOf()
            is Leaf  -> return listOf(this.value)
            is Node  -> {
                val left  = (this.left).toList()
                val right = (this.right).toList()

                // CLR
                val list = listOf(this.value).toLinkedList()
                for (i in left)  list.add(i)
                for (i in right) list.add(i)

                return list
            }
            else     -> throw Exception("Error")
        }
    }


    // test for small left rotation
    //
    //    5                      10
    //  _     10      -->    5      100
    //      9    100       _   9
    //
    @Test fun balanceTest01() {
        val tree   = Node(5, 3, Empty(), Node(10, 2, Leaf(9), Leaf(100)))
        val result = Node(10, 2, Node(5, 1, Empty(), Leaf(9)), Leaf(100))

        test(result, tree.balance())
    }

    // test for small right rotation
    //
    //       5           2
    //   2     _  -->  1     5
    // 1   3               3   _
    //
    @Test fun balanceTest02() {
        val tree   = Node(5, 3, Node(2, 2, Leaf(1), Leaf(3)), Empty())
        val result = Node(2, 2, Leaf(1), Node(5, 1, Leaf(3), Empty()))

        test(result, tree.balance())
    }

    // test for balanced tree
    //
    //       5                      5
    //   2       10      -->    2       10
    // 1   3   9    100       1   3   9    100
    //
    @Test fun balanceTest03() {
        val tree   = Node(5, 3, Node(2, 2, Leaf(1), Leaf(3)), Node(10, 2, Leaf(9), Leaf(100)))
        val result = Node(5, 3, Node(2, 2, Leaf(1), Leaf(3)), Node(10, 2, Leaf(9), Leaf(100)))

        test(result, tree.balance())
    }

    // test for big left rotation
    //
    //   5                        7
    // 3       10             5       10
    //       7    100  -->  3   6   9    100
    //     6   9
    //
    @Test fun balanceTest04() {
        val tree   = Node(5, 4, Leaf(3), Node(10, 3, Node(7, 2, Leaf(6), Leaf(9)), Leaf(100)))
        val result = Node(7, 3, Node(5, 2, Leaf(3), Leaf(6)), Node(10, 2, Leaf(9), Leaf(100)))

        test(result, tree.balance())
    }

    // test for big right rotation
    //
    //           10                7
    //   5          17         5       10
    // 2     7          -->  2   6   9    17
    //     6   9
    //
    @Test fun balanceTest05() {
        val tree   = Node(10, 4, Node(5, 3, Leaf(2), Node(7, 2, Leaf(6), Leaf(9))), Leaf(17))
        val result = Node(7, 3, Node(5, 2, Leaf(2), Leaf(6)), Node(10, 2, Leaf(9), Leaf(17)))

        test(result, tree.balance())
    }


    // insert 7 in tree
    //
    //       5                      5
    //   2       10      -->    2           10
    // 1   3   9    100       1   3     9      100
    //                                7   _
    //
    @Test fun insertTest01() {
        val tree   = Node(5, 3, Node(2, 2, Leaf(1), Leaf(3)), Node(10, 2, Leaf(9), Leaf(100)))
        val result = Node(5, 4, Node(2, 2, Leaf(1), Leaf(3)),
                        Node(10, 3, Node(9, 2, Leaf(7), Empty()), Leaf(100)))

        test(result, tree.insert(7))
    }

    // insert 2 in tree
    //
    //       5                            5
    //   3         10      -->        3           10
    // 1   4     9    100         1     4     9      100
    //         7   _            _   2       7   _
    //
    @Test fun insertTest02() {
        val tree   = Node(5, 4, Node(3, 2, Leaf(1), Leaf(4)),
                        Node(10, 3, Node(9, 2, Leaf(7), Empty()), Leaf(100)))
        val result = Node(5, 4, Node(3, 3, Node(1, 2, Empty(), Leaf(2)), Leaf(4)),
                        Node(10, 3, Node(9, 2, Leaf(7), Empty()), Leaf(100)))

        test(result, tree.insert(2))
    }

    // insert 9 in tree
    //
    //       5                      5
    //   2       10      -->    2       10
    // 1   3   _    100       1   3   9    100
    //
    @Test fun insertTest03() {
        val tree   = Node(5, 3, Node(2, 2, Leaf(1), Leaf(3)), Node(10, 2, Empty(), Leaf(100)))
        val result = Node(5, 3, Node(2, 2, Leaf(1), Leaf(3)), Node(10, 2, Leaf(9), Leaf(100)))

        test(result, tree.insert(9))
    }

    // insert 6 in tree
    //
    //   3                 3
    // 2     4    -->    2     5
    //     _   5             4   6
    //
    @Test fun insertTest04() {
        val tree   = Node(3, 3, Leaf(2), Node(4, 2, Empty(), Leaf(5)))
        val result = Node(3, 3, Leaf(2), Node(5, 2, Leaf(4), Leaf(6)))

        test(result, tree.insert(6))
    }

    // insert 42 in tree
    //
    // _ --> 42
    //
    @Test fun insertTest05() {
        val tree   = Empty()
        val result = Leaf(42)

        test(result, tree.insert(42))
    }


    // remove 9 from tree
    //
    //       5                      5
    //   2       10      -->    2       10
    // 1   3   9    100       1   3   _    100
    //
    @Test fun removeTest01() {
        val tree   = Node(5, 3, Node(2, 2, Leaf(1), Leaf(3)), Node(10, 2, Leaf(9), Leaf(100)))
        val result = Node(5, 3, Node(2, 2, Leaf(1), Leaf(3)), Node(10, 2, Empty(), Leaf(100)))

        test(result, tree.remove(9))
    }

    // remove 5 from tree
    //
    //       5                      3
    //   2       10      -->    2       10
    // 1   3   9    100       1   _   9    100
    //
    @Test fun removeTest02() {
        val tree   = Node(5, 3, Node(2, 2, Leaf(1), Leaf(3)), Node(10, 2, Leaf(9), Leaf(100)))
        val result = Node(3, 3, Node(2, 2, Leaf(1), Empty()), Node(10, 2, Leaf(9), Leaf(100)))

        test(result, tree.remove(5))
    }

    // remove 7 from tree
    //
    //       5                      5
    //   2       10      -->    2       10
    // 1   3   9    100       1   3   9    100
    //
    @Test fun removeTest03() {
        val tree   = Node(5, 3, Node(2, 2, Leaf(1), Leaf(3)), Node(10, 2, Leaf(9), Leaf(100)))
        val result = Node(5, 3, Node(2, 2, Leaf(1), Leaf(3)), Node(10, 2, Leaf(9), Leaf(100)))

        test(result, tree.remove(7))
    }

    // remove 7 from tree
    //
    //       5              2
    //   2       10  -->  1     5
    // 1   3                  3   _
    //
    @Test fun removeTest04() {
        val tree   = Node(5, 3, Node(2, 2, Leaf(1), Leaf(3)), Leaf(10))
        val result = Node(2, 3, Leaf(1), Node(5, 2, Leaf(3), Empty()))

        test(result, tree.remove(10))
    }

    // remove 42 from tree
    //
    // 42 --> _
    //
    @Test fun removeTest05() {
        val tree   = Leaf(42)
        val result = Empty()

        test(result, tree.remove(42))
    }

    fun test(result : AVLTree, tree : AVLTree) {
        org.junit.Assert.assertArrayEquals(result.toList().toIntArray(), tree.toList().toIntArray())
    }


    //tests for search function
    @Test fun searchTest01() {
        val tree = Node(5, 3, Node(2, 2, Leaf(1), Leaf(3)), Leaf(10))
        assertEquals(true, tree.search(1))
    }

    @Test fun searchTest02() {
        val tree = Node(5, 3, Node(2, 2, Leaf(1), Leaf(3)), Leaf(10))
        assertEquals(false, tree.search(17))
    }

    @Test fun searchTest03() {
        val tree = Empty()
        assertEquals(false, tree.search(1))
    }

    @Test fun searchTest04() {
        val tree = Node(5, 4, Node(3, 3, Node(1, 2, Empty(), Leaf(2)), Leaf(4)),
                Node(10, 3, Node(9, 2, Leaf(7), Empty()), Leaf(100)))
        assertEquals(true, tree.search(100))
    }

    @Test fun searchTest05() {
        val tree = Leaf(42)
        assertEquals(true, tree.search(42))
    }
}