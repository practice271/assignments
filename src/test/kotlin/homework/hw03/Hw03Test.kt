package homework.hw03

import org.junit.Test
import kotlin.test.assertEquals

public class Hw03Test {
    /**
     * Generates simplified node representation (for testing).
     */
    internal fun <K : Comparable<K>, V> Node<K, V>.toStringCLR() : String {
        fun Node<K, V>.toStringCLR_() : List<String> {
            val lText = left?.toStringCLR_() ?: listOf("-")
            val rText = right?.toStringCLR_() ?: listOf("-")
            val vText = listOf("$value")
            return vText + lText + rText
        }
        val builder = StringBuilder()
        val lines = this.toStringCLR_()
        lines.forEach { builder.append(it) }
        return builder.toString()
    }

    @Test fun testInsert() {
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

    @Test fun testNoRotation() {
        val tree = Node(6, 6,
            Node(3, 3,
                Node(1, 1, null, null),
                Node(5, 5, null, null)),
            Node(8, 8, null, Node(9, 9, null, null))).toTree()
        tree.insert(4, 4)
        assertEquals("631--54---8-9--", tree.root?.toStringCLR())
    }

    @Test fun testRotationSmallLeft() {
        val tree = Node(5, 5,
            Node(3, 3, null, null),
            Node(7, 7,
                Node(6, 6, null, null),
                Node(8, 8, null, null))).toTree()
        tree.insert(9, 9)
        assertEquals("753--6--8-9--", tree.root?.toStringCLR())
    }

    @Test fun testRotationBigLeft() {
        val tree = Node(5, 5,
            Node(3, 3, null, null),
            Node(8, 8,
                Node(6, 6, null, null),
                Node(9, 9, null, null))).toTree()
        tree.insert(7, 7)
        assertEquals("653---87--9--", tree.root?.toStringCLR())
    }

    @Test fun testRotationSmallRight() {
        val tree = Node(6, 6,
            Node(3, 3,
                Node(1, 1, null, null),
                Node(5, 5, null, null)),
            Node(8, 8, null, null)).toTree()
        tree.insert(0, 0)
        assertEquals("310---65--8--", tree.root?.toStringCLR())
    }

    @Test fun testRotationBigRight() {
        val tree = Node(6, 6,
            Node(3, 3,
                Node(0, 0, null, null),
                Node(5, 5, null, null)),
            Node(8, 8, null, null)).toTree()
        tree.insert(4, 4)
        assertEquals("530--4--6-8--", tree.root?.toStringCLR())
    }

    @Test fun testSearch() {
        val tree = Node(4, "A",
            Node(2, "B",
                Node(1, "C", null, null),
                Node(3, "D", null, null)),
            Node(8, "E",
                    Node(5, "F", null, null),
                    Node(9, "G", null, null))).toTree()
        assertEquals("F", tree.search(5))
        assertEquals(null, tree.search(7))
    }

    @Test fun testPrettyPrinter() {
        val tree1 = AVLTree<Int, String>()
        val str1 = "_\n"
        assertEquals(str1, tree1.toText())
        val tree2 = Node(2, "A", Node(1, "B", null, null), null).toTree()
        val str2 = "|  |  _\n|  (1,B)\n|  |  _\n(2,A)\n|  _\n"
        assertEquals(str2, tree2.toText())
        val tree3 = Node(7, "A",
            Node(5, "B", null, Node(6, "C", null, null)),
            Node(8, "E", Node(9, "F", null, null), null)).toTree()
        val str3 =  "|  |  _\n|  (5,B)\n|  |  |  _\n|  |  (6,C)\n" +
                    "|  |  |  _\n(7,A)\n|  |  |  _\n|  |  (9,F)\n" +
                    "|  |  |  _\n|  (8,E)\n|  |  _\n"
        assertEquals(str3, tree3.toText())
    }

    // TODO: deletion test (with all kinds of rotations)
}