package homework.hw04

import org.junit.Test
import kotlin.test.assertEquals


public class HW04Test {
    @Test fun insertingTest() {
        var tree : Set = AVLTree()
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

        var hash : Set = HashTable(10)
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
        var tree : Set = AVLTree()
        tree.insertion(5); tree.insertion(8); tree.insertion(1); tree.insertion(7)
        tree.deletion(2)
        assertEquals(listOf(5,1,8,7), tree.toList())
        tree.deletion(8); tree.deletion(5)
        assertEquals(listOf(7,1), tree.toList())

        var hash : Set = HashTable(10)
        hash.insertion(5); hash.insertion(8); hash.insertion(1); hash.insertion(7)
        hash.deletion(2)
        assertEquals(listOf(1,5,7,8), hash.toList())
        hash.deletion(8); hash.deletion(5)
        assertEquals(listOf(1,7), hash.toList())
    }
    @Test fun searchTest() {
        var tree : Set = AVLTree()
        tree.insertion(5); tree.insertion(8); tree.insertion(1); tree.insertion(7)
        assertEquals(true, tree.search(8))
        assertEquals(false, tree.search(2))

        var hash : Set = HashTable(10)
        hash.insertion(5); hash.insertion(8); hash.insertion(1); hash.insertion(7)
        assertEquals(true, hash.search(8))
        assertEquals(false, hash.search(2))
    }
    @Test fun emptynessTest() {
        var tree : Set = AVLTree()
        assertEquals(true, tree.isEmpty())
        tree.insertion(1)
        assertEquals(false, tree.isEmpty())
        tree.deletion(1)
        assertEquals(true, tree.isEmpty())

        var hash : Set = HashTable(10)
        assertEquals(true, hash.isEmpty())
        hash.insertion(1)
        assertEquals(false, hash.isEmpty())
        hash.deletion(1)
        assertEquals(true, hash.isEmpty())
    }
    @Test fun unionTest() {
        var tree1 : Set = AVLTree()
        var tree2 : Set = AVLTree()
        tree1.insertion(5); tree1.insertion(8); tree1.insertion(1); tree1.insertion(7)
        tree2.insertion(4); tree2.insertion(6)
        tree1 = tree1.union(tree2)
        assertEquals(listOf(5,4,1,7,6,8), tree1.toList())

        var hash1 : Set = HashTable(10)
        var hash2 : Set = HashTable(10)
        hash1.insertion(5); hash1.insertion(8); hash1.insertion(1); hash1.insertion(7)
        hash2.insertion(4); hash2.insertion(6)
        hash1 = hash1.union(hash2)
        assertEquals(listOf(1,4,5,6,7,8), hash1.toList())
    }
    @Test fun intersectionTest() {
        var tree1 : Set = AVLTree()
        var tree2 : Set = AVLTree()
        tree1.insertion(5); tree1.insertion(8); tree1.insertion(1); tree1.insertion(7)
        tree2.insertion(5); tree2.insertion(2); tree2.insertion(1)
        tree1 = tree1.intersection(tree2)
        assertEquals(listOf(5,1), tree1.toList())

        var hash1 : Set = HashTable(10)
        var hash2 : Set = HashTable(10)
        hash1.insertion(5); hash1.insertion(8); hash1.insertion(1); hash1.insertion(7)
        hash2.insertion(5); hash2.insertion(2); hash2.insertion(1)
        hash1 = hash1.intersection(hash2)
        assertEquals(listOf(1,5), hash1.toList())
    }
}