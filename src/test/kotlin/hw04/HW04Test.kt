package hw04

import org.junit.Test
import kotlin.test.assertEquals

public class HW04Test {
    val tree1 : Tree = Node(17, Node(12, Node(11, Empty(), Empty()), Node(14,
            Node(13, Empty(), Empty()), Node(15, Empty(), Empty()))),
            Node(21, Node(19, Empty(), Empty()), Node(23, Empty(), Empty())))

    Test fun balanceFactor1() {
        assertEquals(1, tree1.balanceFactor())
    }
    Test fun balanceFactor2() {
        val tree = Node(5, Node(3, Empty(), Empty()), Node(7, Empty(), Empty()))
        assertEquals(0, tree.balanceFactor())
    }
    Test fun balanceFactor3() {
        val tree = Node(4, Empty(), Node(2, Empty(), Empty()))
        assertEquals(-1, tree.balanceFactor())
    }

    Test fun insert3() {
        var tree = Empty()
        assertEquals("Node(3, Empty, Empty)", tree.insert(3).setToString())
    }
    Test fun insert5() {
        val tree = Empty()
        assertEquals("Node(5, Empty, Empty)", tree.insert(5).setToString())
    }
    Test fun insert7() {
        val tree = Node(5, Empty(), Empty())
        assertEquals("Node(5, Empty, Node(7, Empty, Empty))",
                tree.insert(7).setToString())
    }
    Test fun insert9() {
        val tree = Node(15, Empty(), Empty())
        assertEquals("Node(15, Node(9, Empty, Empty), Empty)",
                tree.insert(9).setToString())
    }
    Test fun insert11() {
        val tree = Node(8, Node(7, Empty(), Empty()), Node(9, Empty(), Empty()))
        assertEquals("Node(8, Node(7, Empty, Empty), Node(9, Empty, Node(11, Empty, Empty)))",
                tree.insert(11).setToString())
    }
    Test fun insert13SmallRight() {
        val tree = Node(15, Node(14, Empty(), Empty()), Empty())
        assertEquals("Node(14, Node(13, Empty, Empty), Node(15, Empty, Empty))",
                tree.insert(13).setToString())
    }
    Test fun insert15SmallLeft() {
        val tree = Node(11, Empty(), Node(13, Empty(), Empty()))
        assertEquals("Node(13, Node(11, Empty, Empty), Node(15, Empty, Empty))",
                tree.insert(15).setToString())
    }
    Test fun insert17BigLeft() {
        val tree = Node(15, Node(13, Empty(), Empty()), Node(21,
                Node(19, Empty(), Empty()), Node(23, Empty(), Empty())))
        assertEquals("Node(19, Node(15, Node(13, Empty, Empty), " +
                "Node(17, Empty, Empty)), Node(21, Empty, Node(23, Empty, Empty)))",
                tree.insert(17).setToString())
    }
    Test fun insert22BigRight() {
        val tree = Node(25, Node(21, Node(19, Empty(), Empty()),
                Node(23, Empty(), Empty())), Node(27, Empty(), Empty()))
        assertEquals("Node(23, Node(21, Node(19, Empty, Empty), Node(22, Empty, Empty)), " +
                "Node(25, Empty, Node(27, Empty, Empty)))",
                tree.insert(22).setToString())
    }
    Test fun searchYes() {
        val tree = Node(5, Node(3, Empty(), Empty()), Node(7, Node(6,
                Empty(), Empty()), Node(8, Empty(), Node(9, Empty(), Empty()))))
        assertEquals(true, tree.search(9))
    }
    Test fun searchNo() {
        val tree = Node(5, Node(3, Empty(), Empty()), Node(7, Empty(), Empty()))
        assertEquals(false, tree.search(9))
    }
    Test fun searchNoInEmpty() {
        assertEquals(false, Empty(). search(7))
    }
    Test fun remove14() {
        assertEquals("Node(17, Node(12, Node(11, Empty, Empty), " +
                "Node(15, Node(13, Empty, Empty), Empty)), " +
                "Node(21, Node(19, Empty, Empty), Node(23, Empty, Empty)))",
                tree1.delete(14).setToString())
    }
    Test fun remove21() {
        assertEquals("Node(17, Node(12, Node(11, Empty, Empty), " +
                "Node(14, Node(13, Empty, Empty), Node(15, Empty, Empty))), " +
                "Node(23, Node(19, Empty, Empty), Empty))",
                tree1.delete(21).setToString())
    }
    Test fun remove11() {
        assertEquals("Node(17, Node(14, Node(12, Empty, Node(13, Empty, Empty)), " +
                "Node(15, Empty, Empty)), Node(21, Node(19, Empty, Empty), " +
                "Node(23, Empty, Empty)))",
                tree1.delete(11).setToString())
    }
    Test fun remove20() {
        val tree = Node(17, Node(12, Node(11, Empty(), Empty()), Node(14,
                Node(13, Empty(), Empty()), Node(15, Empty(), Empty()))),
                Node(20, Node(19, Empty(), Empty()), Empty()))
        assertEquals("Node(14, Node(12, Node(11, Empty, Empty), Node(13, Empty, Empty)), " +
                "Node(17, Node(15, Empty, Empty), Node(19, Empty, Empty)))",
                tree.delete(20).setToString())
    }
    Test fun unionOfEmpty() {
        val tree  = Empty()
        assertEquals("Empty", tree.union(tree).setToString())
    }
    Test fun union1() {
        val tree = Node(5, Empty(), Empty())
        assertEquals("Node(5, Empty, Empty)", tree.union(Empty()).setToString())
    }
    Test fun union2() {
        val tree = Node(5, Empty(), Empty())
        assertEquals("Node(5, Empty, Empty)", Empty().union(tree).setToString())
    }
    val tree2 : MySet = Node(5, Node(3, Empty(), Empty()), Node(7, Empty(), Empty()))
    val tree3 : MySet = Node(4, Node(2, Node(1, Empty(), Empty()), Node(3, Empty(), Empty())),
            Node(5, Empty(), Node(6, Empty(), Empty())))
    Test fun union3() {
        val s = "Node(4, Node(2, Node(1, Empty, Empty), Node(3, Empty, Empty)), " +
                        "Node(6, Node(5, Empty, Empty), Node(7, Empty, Empty)))"
        assertEquals(s, tree2.union(tree3).setToString())
    }
    Test fun union4() {
        val s = "Node(3, Node(2, Node(1, Empty, Empty), Empty), " +
                        "Node(5, Node(4, Empty, Empty), " +
                                "Node(7, Node(6, Empty, Empty), Empty)))"
        assertEquals(s, tree3.union(tree2).setToString())
    }
    Test fun interEmpty1() {
        assertEquals("Empty", Empty().intersection(Empty()).setToString())
    }
    Test fun interEmpty2() {
        assertEquals("Empty", Empty().intersection(tree2).setToString())
    }
    Test fun interEmpty3() {
        assertEquals("Empty", tree2.intersection(Empty()).setToString())
    }
    Test fun inter1() {
        assertEquals("Node(3, Empty, Node(5, Empty, Empty))",
                tree2.intersection(tree3).setToString())
    }
    Test fun inter2() {
        assertEquals("Node(5, Node(3, Empty, Empty), Empty)",
                tree3.intersection(tree2).setToString())
    }
    Test fun insertHash1() {
        var hashTable = HashTable(5)
        hashTable.insert(6)
        hashTable.insert(11)
        hashTable.insert(6)
        hashTable.insert(12)
        val res = "null\n11 6 \n12 \nnull\nnull\n"
        assertEquals(res, hashTable.setToString())
    }
    Test fun insertHash2() {
        var hashTable = HashTable(5)
        hashTable.insert(0)
        hashTable.insert(1)
        hashTable.insert(2)
        hashTable.insert(3)
        hashTable.insert(4)
        hashTable.insert(5)
        hashTable.insert(6)
        hashTable.insert(7)
        hashTable.insert(8)
        hashTable.insert(9)
        hashTable.insert(9)
        hashTable.insert(0)
        val res = "5 0 \n6 1 \n7 2 \n8 3 \n9 4 \n"
        assertEquals(res, hashTable.setToString())
    }
    Test fun deleteHash1() {
        var hashTable = HashTable(1)
        hashTable.delete(7)
        assertEquals("null\n", hashTable.setToString())
    }
    Test fun deleteHash2() {
        var hashTable = HashTable(1)
        hashTable.insert(7)
        hashTable.delete(7)
        assertEquals("null\n", hashTable.setToString())
    }
    Test fun searchHashY() {
        var hashTable = HashTable(1)
        hashTable.insert(7)
        assertEquals(true, hashTable.search(7))
    }
    Test fun searchHashN1() {
        var hashTable = HashTable(1)
        assertEquals(false, hashTable.search(7))
    }
    Test fun searchHashN2() {
        var hashTable = HashTable(5)
        hashTable.insert(6)
        hashTable.insert(11)
        hashTable.insert(6)
        hashTable.insert(12)
        assertEquals(false, hashTable.search(7))
    }
    Test fun unionHash1() {
        var h1 = HashTable(5)
        var h2 = HashTable(5)
        h1.insert(6)
        h1.insert(11)
        h1.insert(12)
        var h3 = h1.union(h2)
        val res = "null\n6 11 \n12 \nnull\nnull\n"
        assertEquals(res, h3.setToString())
    }
    Test fun unionHash2() {
        var h1 = HashTable(5)
        var h2 = HashTable(5)
        h1.insert(6)
        h1.insert(11)
        h1.insert(12)
        var h3 = h2.union(h1)
        val res = "null\n6 11 \n12 \nnull\nnull\n"
        assertEquals(res, h3.setToString())
    }
    Test fun unionHash3() {
        var h1 = HashTable(5)
        var h2 = HashTable(5)
        var h3 = h1.union(h2)
        val res = "null\nnull\nnull\nnull\nnull\n"
        assertEquals(res, h3.setToString())
    }
    var h1 = HashTable(2)
    var h2 = HashTable(2)
    Test fun interHash1() {
        var h3 = h1.intersection(h2)
        assertEquals("null\nnull\n", h3.setToString())
    }
    Test fun interHash2() {
        var h3 = h2.intersection(h1)
        assertEquals("null\nnull\n", h3.setToString())
    }
    Test fun interHash3() {
        h1.insert(7)
        var h3 = h2.intersection(h1)
        assertEquals("null\nnull\n", h3.setToString())
    }
    Test fun interHash4() {
        h2.insert(7)
        var h3 = h2.intersection(h1)
        assertEquals("null\nnull\n", h3.setToString())
    }
    Test fun interHash5() {
        var h1 = HashTable(5)
        var h2 = HashTable(5)
        h1.insert(9)
        h1.insert(7)
        h1.insert(3)
        h1.insert(11)
        h2.insert(11)
        h2.insert(9)
        h2.insert(7)
        h2.insert(1)
        val h3 = h1.intersection(h2)
        assertEquals("null\n11 \n7 \nnull\n9 \n", h3.setToString())
    }
    Test fun interHash6() {
        var h1 = HashTable(5)
        var h2 = HashTable(5)
        h1.insert(9)
        h1.insert(7)
        h1.insert(3)
        h1.insert(11)
        h2.insert(11)
        h2.insert(9)
        h2.insert(7)
        h2.insert(1)
        val h3 = h2.intersection(h1)
        assertEquals("null\n11 \n7 \nnull\n9 \n", h3.setToString())
    }
    Test fun unionTH1() {
        val t = Node(5, Node(3, Empty(), Empty()), Empty())
        var h = HashTable(5)
        h.insert(9)
        h.insert(7)
        h.insert(3)
        h.insert(11)
        val resTree  = t.union(h)
        assertEquals("Node(7, Node(3, Empty, Node(5, Empty, Empty)), " +
                             "Node(11, Node(9, Empty, Empty), Empty))",
                resTree.setToString())
    }
    Test fun unionTH2() {
        val t = Empty()
        var h = HashTable(5)
        h.insert(9)
        h.insert(7)
        h.insert(3)
        h.insert(11)
        val resTree  = t.union(h)
        assertEquals("Node(7, Node(3, Empty, Empty), " +
                             "Node(11, Node(9, Empty, Empty), Empty))",
                resTree.setToString())
    }
    Test fun unionHT1() {
        val t = Node(5, Node(3, Empty(), Empty()), Empty())
        var h = HashTable(5)
        h.insert(9)
        h.insert(7)
        h.insert(3)
        h.insert(11)
        val resHash  = h.union(t)
        assertEquals("5 \n11 \n7 \n3 \n9 \n", resHash.setToString())
    }
    Test fun unionHT2() {
        val t = Empty()
        var h = HashTable(5)
        h.insert(9)
        h.insert(7)
        h.insert(3)
        h.insert(11)
        val resHash  = h.union(t)
        assertEquals(h.setToString(), resHash.setToString())
    }
    Test fun interTH1() {
        val t = Node(5, Node(3, Empty(), Empty()), Node(7, Empty(), Empty()))
        var h = HashTable(5)
        h.insert(9)
        h.insert(7)
        h.insert(3)
        h.insert(11)
        val resTree  = t.intersection(h)
        assertEquals("Node(7, Node(3, Empty, Empty), Empty)", resTree.setToString())
    }
    Test fun interTH2() {
        val t = Empty()
        var h = HashTable(5)
        h.insert(9)
        h.insert(7)
        h.insert(3)
        h.insert(11)
        val resTree  = t.intersection(h)
        assertEquals("Empty", resTree.setToString())
    }
    Test fun interHT1() {
        val t = Node(5, Node(3, Empty(), Empty()), Node(7, Empty(), Empty()))
        var h = HashTable(5)
        h.insert(9)
        h.insert(7)
        h.insert(3)
        h.insert(11)
        val resHash  = h.intersection(t)
        assertEquals("null\nnull\n7 \n3 \nnull\n", resHash.setToString())
    }
    Test fun interHT2() {
        val t = Empty()
        var h = HashTable(5)
        h.insert(9)
        h.insert(7)
        h.insert(3)
        h.insert(11)
        val resHash  = h.intersection(t)
        assertEquals("null\nnull\nnull\nnull\nnull\n", resHash.setToString())
    }
}