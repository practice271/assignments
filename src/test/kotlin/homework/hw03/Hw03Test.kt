package homework.hw03

import org.junit.Test
import kotlin.test.assertEquals

public class Hw03Test {
    @Test fun testInsertBasic() {
        val tree = AVLTree<Int, String>()
        tree.insert(3, "A")
        assertEquals("A--", tree.root?.toStringCLR())
        tree.insert(2, "B")
        assertEquals("AB---", tree.root?.toStringCLR())
        tree.insert(5, "C")
        assertEquals("AB--C--", tree.root?.toStringCLR())
        tree.insert(4, "D")
        assertEquals("AB--CD---", tree.root?.toStringCLR())
    }

    @Test fun testInsertNoRotation() {
        val tree = Node(6, 6,
            Node(3, 3,
                leaf(1, 1),
                leaf(5, 5)),
            Node(8, 8, null, leaf(9, 9))).toTree()
        tree.insert(4, 4)
        assertEquals("631--54---8-9--", tree.root?.toStringCLR())
    }

    @Test fun testInsertRotationSmallLeft() {
        val tree = Node(5, 5,
            leaf(3, 3),
            Node(7, 7,
                leaf(6, 6),
                leaf(8, 8))).toTree()
        tree.insert(9, 9)
        assertEquals("753--6--8-9--", tree.root?.toStringCLR())
    }

    @Test fun testInsertRotationBigLeft() {
        val tree = Node(5, 5,
            leaf(3, 3),
            Node(8, 8,
                leaf(6, 6),
                leaf(9, 9))).toTree()
        tree.insert(7, 7)
        assertEquals("653---87--9--", tree.root?.toStringCLR())
    }

    @Test fun testInsertRotationSmallRight() {
        val tree = Node(6, 6,
            Node(3, 3,
                leaf(1, 1),
                leaf(5, 5)),
            leaf(8, 8)).toTree()
        tree.insert(0, 0)
        assertEquals("310---65--8--", tree.root?.toStringCLR())
    }

    @Test fun testInsertRotationBigRight() {
        val tree = Node(6, 6,
            Node(3, 3,
                leaf(0, 0),
                leaf(5, 5)),
            leaf(8, 8)).toTree()
        tree.insert(4, 4)
        assertEquals("530--4--6-8--", tree.root?.toStringCLR())
    }

    @Test fun testInsertWorstCase() {
        val tree =
            Node(8, "8",
                Node(5, "5",
                    Node(3, "3",
                        Node(2, "2", leaf(1, "1"), null),
                        leaf(4, "4")),
                    Node(7, "7", leaf(6, "6"), null)),
                Node(11, "B",
                        Node(10, "A", leaf(9, "9"), null),
                        leaf(12, "C"))).toTree()
        tree.insert(0, "0")
        assertEquals("85310--2--4--76---BA9---C--", tree.root?.toStringCLR())
        tree.insert(-1, "X")
        assertEquals("8510X---32--4--76---BA9---C--", tree.root?.toStringCLR())
    }

    @Test fun testSearch() {
        val tree = Node(4, "A",
            Node(2, "B",
                leaf(1, "C"),
                leaf(3, "D")),
            Node(8, "E",
                leaf(5, "F"),
                leaf(9, "G"))).toTree()
        assertEquals("F", tree.search(5))
        assertEquals(null, tree.search(7))
    }

    @Test fun testPrettyPrinter() {
        val tree1 = AVLTree<Int, String>()
        val str1 = "_\n"
        assertEquals(str1, tree1.toText())
        val tree2 = Node(2, "A", leaf(1, "B"), null).toTree()
        val str2 = "|  |  _\n|  (1,B)\n|  |  _\n(2,A)\n|  _\n"
        assertEquals(str2, tree2.toText())
        val tree3 = Node(7, "A",
            Node(5, "B", null, leaf(6, "C")),
            Node(8, "E", leaf(9, "F"), null)).toTree()
        val str3 =  "|  |  _\n|  (5,B)\n|  |  |  _\n|  |  (6,C)\n" +
                    "|  |  |  _\n(7,A)\n|  |  |  _\n|  |  (9,F)\n" +
                    "|  |  |  _\n|  (8,E)\n|  |  _\n"
        assertEquals(str3, tree3.toText())
    }

    fun genTreeForDeletion() = Node(8, 8,
            Node(6, 6, leaf(5, 5), leaf(7, 7)),
            leaf(9, 9)).toTree()

    @Test fun testDeleteNoRotation() {
        val tree = genTreeForDeletion()
        tree.remove(5)
        assertEquals("86-7--9--", tree.root?.toStringCLR())
    }

    @Test fun testDeleteRotationSmallRight() {
        val tree = genTreeForDeletion()
        tree.remove(9)
        assertEquals("65--87---", tree.root?.toStringCLR())
    }

    @Test fun testDeleteRoot() {
        val tree = genTreeForDeletion()
        tree.remove(8)
        assertEquals("765---9--", tree.root?.toStringCLR())
    }

    @Test fun testDeleteWorstCase() {
        val tree =
                Node(8, "8",
                        Node(5, "5",
                                Node(3, "3",
                                        Node(2, "2", leaf(1, "1"), null),
                                        leaf(4, "4")),
                                Node(7, "7", leaf(6, "6"), null)),
                        Node(11, "B",
                                Node(10, "A", leaf(9, "9"), null),
                                leaf(12, "C"))).toTree()
        tree.insert(0, "0")
        tree.insert(-1, "X")
        tree.remove(12)
        assertEquals("510X---32--4--876---A9--B--", tree.root?.toStringCLR())
    }
}