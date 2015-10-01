package hw04

import org.junit.Test
import kotlin.test.assertEquals
import java.util.LinkedList

public class HashTableTest {

    fun getPairList(): LinkedList<Pair<Int, String>> = linkedListOf<Pair<Int, String>>(Pair(1, "100"),
            Pair(5, "500"), Pair(9, "900"), Pair(6, "600"), Pair(4, "400"), Pair(3, "300"), Pair(10, "1000"),
            Pair(18, "1800"), Pair(8, "800"), Pair(23, "2300"), Pair(21, "2100"), Pair(24, "2400"),
            Pair(27, "2700"), Pair(7, "700"), Pair(13, "1300"), Pair(12, "1200"), Pair(16, "1600"),
            Pair(15, "1500"), Pair(28, "2800"), Pair(22, "2200"), Pair(26, "2600"), Pair(25, "2500"),
            Pair(2, "200"), Pair(20, "2000"), Pair(11, "1100"), Pair(14, "1400"), Pair(19, "1900"), Pair(17, "1700"))

    fun getTestingHashTable(): HashTable<Int, String> {
        val hashTable = HashTable<Int, String>()
        for (elem in getPairList()) hashTable.insert(elem.first, elem.second)
        return hashTable
    }

    fun getTestingHashTableAsList() = linkedListOf<Pair<Int, String>>(Pair(1, "100"), Pair(2, "200"), Pair(3, "300"),
            Pair(4, "400"), Pair(5, "500"), Pair(6, "600"), Pair(7, "700"), Pair(8, "800"), Pair(9, "900"),
            Pair(10, "1000"), Pair(11, "1100"), Pair(12, "1200"), Pair(13, "1300"), Pair(14, "1400"),
            Pair(15, "1500"), Pair(16, "1600"), Pair(17, "1700"), Pair(18, "1800"), Pair(19, "1900"),
            Pair(20, "2000"), Pair(21, "2100"), Pair(22, "2200"), Pair(23, "2300"), Pair(24, "2400"),
            Pair(25, "2500"), Pair(26, "2600"), Pair(27, "2700"), Pair(28, "2800"))

    Test fun emptyHashTableTest() {
        val hashTable = HashTable<Int, String>()
        assertEquals(linkedListOf<Pair<Int, String>>(), hashTable.toList())
    }

    Test fun insertKeyHashTableTest() {
        var hashTable = HashTable<Int, String>()
        hashTable.insert(1, "100")
        assertEquals(linkedListOf(Pair(1, "100")), hashTable.toList())

        hashTable = getTestingHashTable()
        assertEquals(getTestingHashTableAsList(), hashTable.toList())

        hashTable.insert(1, "200"); hashTable.insert(1, "300");
        hashTable.insert(1, "1000");  hashTable.insert(1, "100")
        assertEquals(getTestingHashTableAsList(), hashTable.toList())
    }

    Test fun findKeyEmptyHashTableTest() {
        var hashTable = HashTable<Int, String>()
        assertEquals(null, hashTable.search(1))
    }

    Test fun findKeyHashTableTest() {

        var hashTable = HashTable<Int, String>()
        hashTable.insert(1, "100"); hashTable.insert(5, "500"); hashTable.insert(9, "900");
        assertEquals("100", hashTable.search(1))
        assertEquals("500", hashTable.search(5))
        assertEquals("900", hashTable.search(9))

        hashTable = getTestingHashTable()
        assertEquals("600", hashTable.search(6))
        assertEquals("1300", hashTable.search(13))
        assertEquals("2000", hashTable.search(20))
        assertEquals("1400", hashTable.search(14))
        assertEquals("1900", hashTable.search(19))
    }

    Test fun removeKeyEmptyHashTable() {
        val hashTable = HashTable<Int, String>()
        hashTable.delete(1)
        assertEquals(linkedListOf<Pair<Int, String>>(), hashTable.toList())
    }

    Test fun removeKeyNonExistentHashTableTest() {
        val hashTable = HashTable<Int, Int>()
        hashTable.insert(3, 300); hashTable.insert(7, 700); hashTable.insert(5, 500)
        hashTable.delete(1); hashTable.delete(4); hashTable.delete(6); hashTable.delete(8);
        assertEquals(linkedListOf<Pair<Int, Int>>(Pair(3, 300), Pair(5, 500), Pair(7, 700)), hashTable.toList())
    }

    Test fun removeKeyHashTableTest() {
        var hashTable = HashTable<Int, String>()
        hashTable.insert(1, "100")
        hashTable.delete(1)
        assertEquals(linkedListOf<Pair<Int, String>>(), hashTable.toList())

        hashTable = getTestingHashTable()
        hashTable.delete(1); hashTable.delete(25); hashTable.delete(23); hashTable.delete(21);
        hashTable.delete(19); hashTable.delete(17); hashTable.delete(15); hashTable.delete(13);
        hashTable.delete(11); hashTable.delete(9); hashTable.delete(7); hashTable.delete(5);
        hashTable.delete(3); hashTable.delete(27);
        val ansList = linkedListOf<Pair<Int, String>>(Pair(2, "200"), Pair(4, "400"), Pair(6, "600"), Pair(8, "800"),
                Pair(10, "1000"), Pair(12, "1200"), Pair(14, "1400"), Pair(16, "1600"), Pair(18, "1800"),
                Pair(20, "2000"), Pair(22, "2200"), Pair(24, "2400"), Pair(26, "2600"), Pair(28, "2800"))
        assertEquals(ansList, hashTable.toList())
    }

    Test fun hashTableUnionHashTable() {
        var hashTable1 = HashTable<Int, Int>()
        var hashTable2 = HashTable<Int, Int>()
        assertEquals(linkedListOf<Pair<Int, String>>(), hashTable1.union(hashTable2).toList())

        hashTable1.insert(3, 300); hashTable1.insert(7, 700); hashTable1.insert(5, 500)
        assertEquals(linkedListOf(Pair(3, 300), Pair(5, 500), Pair(7, 700)), hashTable1.union(hashTable2).toList())
        assertEquals(linkedListOf(Pair(3, 300), Pair(5, 500), Pair(7, 700)), hashTable2.union(hashTable1).toList())

        hashTable2.insert(3, 300); hashTable2.insert(7, 700); hashTable2.insert(10, 1000)
        assertEquals(linkedListOf(Pair(3, 300), Pair(5, 500), Pair(7, 700), Pair(10, 1000)),
                hashTable1.union(hashTable2).toList())
    }

    Test fun HashTableUnionTreeTest() {
        var tree = AVLtree<Int, Int>()
        var hashTable = HashTable<Int, Int>()
        assertEquals(linkedListOf<Pair<Int, String>>(), hashTable.union(tree).toList())

        hashTable.insert(3, 300); hashTable.insert(7, 700); hashTable.insert(5, 500)
        assertEquals(linkedListOf(Pair(3, 300), Pair(5, 500), Pair(7, 700)), hashTable.union(tree).toList())

        tree.insert(3, 300); tree.insert(7, 700); tree.insert(10, 1000)
        assertEquals(linkedListOf(Pair(3, 300), Pair(5, 500), Pair(7, 700), Pair(10, 1000)),
                hashTable.union(tree).toList())

        tree = AVLtree<Int, Int>()
        assertEquals(linkedListOf(Pair(3, 300), Pair(5, 500), Pair(7, 700)), hashTable.union(tree).toList())
    }

    Test fun hashTableIntersectionHashTable() {
        var hashTable1 = HashTable<Int, Int>()
        var hashTable2 = HashTable<Int, Int>()
        assertEquals(linkedListOf<Pair<Int, String>>(), hashTable1.intersection(hashTable2).toList())

        hashTable1.insert(3, 300); hashTable1.insert(7, 700); hashTable1.insert(5, 500)
        assertEquals(linkedListOf<Pair<Int, String>>(), hashTable1.intersection(hashTable2).toList())
        assertEquals(linkedListOf<Pair<Int, String>>(), hashTable2.intersection(hashTable1).toList())

        hashTable2.insert(3, 300); hashTable2.insert(7, 700); hashTable2.insert(10, 1000)
        assertEquals(linkedListOf(Pair(3, 300), Pair(7, 700)), hashTable1.intersection(hashTable2).toList())
    }

    Test fun HashTableIntersectionTreeTest() {
        var tree = AVLtree<Int, Int>()
        var hashTable = HashTable<Int, Int>()
        assertEquals(linkedListOf<Pair<Int, String>>(), hashTable.intersection(tree).toList())

        hashTable.insert(3, 300); hashTable.insert(7, 700); hashTable.insert(5, 500)
        assertEquals(linkedListOf<Pair<Int, String>>(), hashTable.intersection(tree).toList())

        tree.insert(3, 300); tree.insert(7, 700); tree.insert(10, 1000)
        assertEquals(linkedListOf(Pair(3, 300), Pair(7, 700)), hashTable.intersection(tree).toList())
    }
}