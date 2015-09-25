package homework.hw03

import org.junit.Test
import kotlin.test.assertEquals

public class Hw03Test {
    /**
     * Generates simplified node representation (for testing).
     */
    internal fun <K : Comparable<K>, V> AvlNode<K, V>.toStringCLR() : String {
        fun AvlNode<K, V>.toStringCLR_() : List<String> {
            when (this) {
                is Empty -> return listOf("-")
                is Node  -> {
                    val lText = left.toStringCLR_()
                    val rText = right.toStringCLR_()
                    val vText = listOf("$value")
                    return vText + lText + rText
                }
                else -> throw Exception("Unknown class")
            }
        }
        val builder = StringBuilder()
        val lines = this.toStringCLR_()
        lines.forEach { builder.append(it) }
        return builder.toString()
    }

    @Test fun testInsert() {
        var tree = AVLTree<Int, String>()
        tree.insert(3, "A")
        assertEquals("A--", tree.root.toStringCLR())
        tree.insert(2, "B")
        assertEquals("AB---", tree.root.toStringCLR())
        tree.insert(5, "C")
        assertEquals("AB--C--", tree.root.toStringCLR())
        tree.insert(4, "D")
        assertEquals("AB--CD---", tree.root.toStringCLR())
    }

    // testing rotations
    private fun genTreeForRotation() : AvlNode<Int, Int> = Node(1, 1,
        Node(2, 2,
            Node(4, 4, Empty(), Empty()),
            Node(5, 5,
                Node(8, 8, Empty(), Empty()),
                Node(9, 9, Empty(), Empty()))),
        Node(3, 3,
            Node(6, 6,
                Node(0, 0, Empty(), Empty()),
                Node(1, 1, Empty(), Empty())),
            Node(7, 7, Empty(), Empty())))

    @Test fun testRotationSmallLeft() {
        val tree = genTreeForRotation()
        assertEquals("124--58--9--360--1--7--", tree.toStringCLR())
    }

    // TODO: search test

    // TODO: deletion test (with all kinds of rotations)

    // TODO: pretty printer test
}