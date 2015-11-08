package homework.hw08

import org.junit.Test
import kotlin.test.assertEquals
import java.util.*


public class HW08Test {
    @Test fun insertingTest() {
        var tree : Set<Int> = AVLTree()
        assertEquals(emptyList<Int>(), tree.toList())
        tree.insertion(1)
        assertEquals(listOf(1), tree.toList())
        tree.insertion(4)
        assertEquals(listOf(1,4), tree.toList())
        tree.insertion(2)
        assertEquals(listOf(2,1,4), tree.toList())
        tree.insertion(3)
        assertEquals(listOf(2,1,4,3), tree.toList())
        tree.insertion(2)
        assertEquals(listOf(2,1,4,3), tree.toList())

        var hash : Set<Int> = HashTable(10)
        assertEquals(emptyList<Int>(), hash.toList())
        hash.insertion(1)
        assertEquals(listOf(1), hash.toList())
        hash.insertion(4)
        assertEquals(listOf(1,4), hash.toList())
        hash.insertion(2)
        assertEquals(listOf(1,2,4), hash.toList())
        hash.insertion(3)
        assertEquals(listOf(1,2,3,4), hash.toList())
    }
    @Test fun deletionTest() {
        var tree : Set<Int> = AVLTree()
        tree.insertion(5); tree.insertion(8); tree.insertion(1); tree.insertion(7)
        tree.deletion(2)
        assertEquals(listOf(5,1,8,7), tree.toList())
        tree.deletion(8); tree.deletion(5)
        assertEquals(listOf(7,1), tree.toList())

        var hash : Set<Int> = HashTable(10)
        hash.insertion(5); hash.insertion(8); hash.insertion(1); hash.insertion(7)
        hash.deletion(2)
        assertEquals(listOf(1,5,7,8), hash.toList())
        hash.deletion(8); hash.deletion(5)
        assertEquals(listOf(1,7), hash.toList())
    }
    @Test fun searchTest() {
        var tree : Set<Int> = AVLTree()
        tree.insertion(5); tree.insertion(8); tree.insertion(1); tree.insertion(7)
        assertEquals(true, tree.search(8))
        assertEquals(false, tree.search(2))

        var hash : Set<Int> = HashTable(10)
        hash.insertion(5); hash.insertion(8); hash.insertion(1); hash.insertion(7)
        assertEquals(true, hash.search(8))
        assertEquals(false, hash.search(2))
    }
    @Test fun emptynessTest() {
        var tree : Set<Int> = AVLTree()
        assertEquals(true, tree.isEmpty())
        tree.insertion(1)
        assertEquals(false, tree.isEmpty())
        tree.deletion(1)
        assertEquals(true, tree.isEmpty())

        var hash : Set<Int> = HashTable(10)
        assertEquals(true, hash.isEmpty())
        hash.insertion(1)
        assertEquals(false, hash.isEmpty())
        hash.deletion(1)
        assertEquals(true, hash.isEmpty())
    }
    @Test fun unionTest() {
        var tree1 : Set<Int> = AVLTree()
        var tree2 : Set<Int> = AVLTree()
        tree1.insertion(5); tree1.insertion(8); tree1.insertion(1); tree1.insertion(7)
        tree2.insertion(4); tree2.insertion(6)
        tree1 = tree1.union(tree2)
        assertEquals(listOf(5,4,1,7,6,8), tree1.toList())

        var hash1 : Set<Int> = HashTable(10)
        var hash2 : Set<Int> = HashTable(10)
        hash1.insertion(5); hash1.insertion(8); hash1.insertion(1); hash1.insertion(7)
        hash2.insertion(4); hash2.insertion(6)
        hash1 = hash1.union(hash2)
        assertEquals(listOf(1,4,5,6,7,8), hash1.toList())
    }
    @Test fun intersectionTest() {
        var tree1 : Set<Int> = AVLTree()
        var tree2 : Set<Int> = AVLTree()
        tree1.insertion(5); tree1.insertion(8); tree1.insertion(1); tree1.insertion(7)
        tree2.insertion(5); tree2.insertion(2); tree2.insertion(1)
        tree1 = tree1.intersection(tree2)
        assertEquals(listOf(5,1), tree1.toList())

        var hash1 : Set<Int> = HashTable(10)
        var hash2 : Set<Int> = HashTable(10)
        hash1.insertion(5); hash1.insertion(8); hash1.insertion(1); hash1.insertion(7)
        hash2.insertion(5); hash2.insertion(2); hash2.insertion(1)
        hash1 = hash1.intersection(hash2)
        assertEquals(listOf(1,5), hash1.toList())
    }
    @Test fun iteratorTest() {
        var tree : Set<Int> = AVLTree()
        var arr = arrayListOf(4, 2, 8, 5, 7)
        for (i in arr) tree.insertion(i)
        var list : LinkedList<Int> = linkedListOf()
        for (i in tree.toList()) list.add(i)
        assertEquals(listOf(4, 2, 7, 5, 8), list.toList())

        var hash : Set<Int> = HashTable(10)
        list = linkedListOf()
        var arr1 = arrayListOf(2, 10, 9, 32, 7)
        for (i in arr1 ) hash.insertion(i)
        for (i in hash.toList()) list.add(i)
        assertEquals(listOf(10, 2, 32, 7, 9), list.toList())
    }
}