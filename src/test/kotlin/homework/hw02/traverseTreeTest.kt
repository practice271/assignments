package homework.hw02

import org.junit.Test
import kotlin.test.assertEquals

public class TraverseTreeTest {
    Test fun maxPathTest01() {
        assertEquals(0, maxPath(Empty()))
    }

    Test fun maxPathTest02() {
        assertEquals(1, maxPath(Leaf(1)))
    }

    Test fun maxPathTest03() {
        val tree = Node(1, Node(8, Leaf(7), Leaf(5)), Node(5, Empty(), Node(4, Leaf(3), Leaf(2))))
        assertEquals(16, maxPath(tree))
    }

    Test fun foldTopDownTest01() {
        val tree = Node(1, Node(8, Leaf(7), Leaf(5)), Node(5, Empty(), Node(4, Leaf(3), Leaf(2))))
        var x1 = foldTopDown(0,
                { acc: Int, elem: Int -> acc + elem },
                { acc1: Int, acc2: Int -> if (acc1 > acc2) acc1 else acc2 },
                tree)
        assertEquals(16, x1)
    }

    Test fun foldTopDownTest02() {
        val tree = Node(1, Node(8, Leaf(7), Leaf(5)), Node(5, Empty(), Node(4, Leaf(3), Leaf(2))))
        var x2 = foldTopDown(0,
                { acc: Int, _: Int -> acc + 1 },
                { acc1: Int, acc2: Int -> if (acc1 > acc2) acc1 else acc2 },
                tree)
        assertEquals(4, x2)
    }
}