package hw08

import org.junit.Test
import kotlin.test.assertEquals

class AVLTree_Test {

    @Test fun testInsert1() {
        var tree = AVLTreeSet()
        tree.insert(7)
        tree.insert(9)
        assertEquals(listOf(7,9), tree.toList())
    }

    @Test fun testInsert2() {
        var tree = AVLTreeSet()
        tree.insert(7)
        tree.insert(9)
        tree.insert(11)
        tree.insert(13)
        assertEquals(listOf(7,9,11,13), tree.toList())
    }

    @Test fun testSearch1() {
        var tree = AVLTreeSet()
        tree.insert(7)
        tree.insert(9)
        tree.insert(8)
        tree.insert(12)
        assertEquals(false, tree.search(4))
    }

    @Test fun testSearch2() {
        var tree = AVLTreeSet()
        tree.insert(7)
        tree.insert(9)
        tree.insert(8)
        tree.insert(4)
        assertEquals(true, tree.search(4))
    }

    @Test fun unionTest1() {
        var tree1 = AVLTreeSet()
        var tree2 = AVLTreeSet()
        tree1.insert(1)
        tree1.insert(6)
        tree1.insert(8)
        tree2.insert(9)
        tree2.insert(5)
        tree2.insert(18)
        assertEquals(listOf(1,5,6,8,9,18),tree1.union(tree2).toList())
    }

    @Test fun unionTest2() {
        var tree1 = AVLTreeSet()
        var tree2 = AVLTreeSet()
        tree1.insert(9)
        tree1.insert(14)
        tree1.insert(7)
        tree2.insert(15)
        tree2.insert(6)
        tree2.insert(18)
        assertEquals(listOf(6,7,9,14,15,18),tree1.union(tree2).toList())
    }

    @Test fun intersectionTest1() {
        var tree1 = AVLTreeSet()
        var tree2 = AVLTreeSet()
        tree1.insert(9)
        tree1.insert(14)
        tree1.insert(18)
        tree2.insert(9)
        tree2.insert(6)
        tree2.insert(18)
        assertEquals(listOf(9,18),tree1.insersection(tree2).toList())
    }

    @Test fun intersectionTest2() {
        var tree1 = AVLTreeSet()
        var tree2 = AVLTreeSet()
        tree1.insert(8)
        tree1.insert(14)
        tree1.insert(18)
        tree1.insert(18)
        tree2.insert(9)
        tree2.insert(8)
        tree2.insert(12)
        assertEquals(listOf(8),tree1.insersection(tree2).toList())
    }

    @Test fun iteratorTest1() {
        var tree = AVLTreeSet()
        tree.insert(7)
        tree.insert(9)
        tree.insert(11)
        tree.insert(13)

        var test = ""
        for (i in tree) test+="$i "
        assertEquals("7 9 11 13 ", test)
    }

    @Test fun iteratorTest2() {
        var tree = AVLTreeSet()
        tree.insert(7)
        tree.insert(9)

        var test = ""
        for (i in tree) test+="$i "
        assertEquals("7 9 ", test)
    }
}