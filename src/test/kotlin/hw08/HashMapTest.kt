package hw08

import org.junit.Test
import kotlin.test.assertEquals
import java.util.LinkedList

public class HashMapTest {

    fun getPairList(): LinkedList<Pair<Int, String>> = linkedListOf<Pair<Int, String>>(Pair(1, "100"),
            Pair(5, "500"), Pair(9, "900"), Pair(6, "600"), Pair(4, "400"), Pair(3, "300"), Pair(10, "1000"),
            Pair(18, "1800"), Pair(8, "800"), Pair(23, "2300"), Pair(21, "2100"), Pair(24, "2400"),
            Pair(27, "2700"), Pair(7, "700"), Pair(13, "1300"), Pair(12, "1200"), Pair(16, "1600"),
            Pair(15, "1500"), Pair(28, "2800"), Pair(22, "2200"), Pair(26, "2600"), Pair(25, "2500"),
            Pair(2, "200"), Pair(20, "2000"), Pair(11, "1100"), Pair(14, "1400"), Pair(19, "1900"), Pair(17, "1700"))

    fun getTestingHashMap(): HashMap<Int, String> {
        val hashMap = HashMap<Int, String>()
        for (elem in getPairList()) hashMap.insert(elem.first, elem.second)
        return hashMap
    }

    fun getTestingHashMapAsList() = linkedListOf<Pair<Int, String>>(Pair(1, "100"), Pair(2, "200"), Pair(3, "300"),
            Pair(4, "400"), Pair(5, "500"), Pair(6, "600"), Pair(7, "700"), Pair(8, "800"), Pair(9, "900"),
            Pair(10, "1000"), Pair(11, "1100"), Pair(12, "1200"), Pair(13, "1300"), Pair(14, "1400"),
            Pair(15, "1500"), Pair(16, "1600"), Pair(17, "1700"), Pair(18, "1800"), Pair(19, "1900"),
            Pair(20, "2000"), Pair(21, "2100"), Pair(22, "2200"), Pair(23, "2300"), Pair(24, "2400"),
            Pair(25, "2500"), Pair(26, "2600"), Pair(27, "2700"), Pair(28, "2800"))

    @Test fun emptyHashTMapTest() {
        val hashMap = HashMap<Int, String>()
        assertEquals(linkedListOf<Pair<Int, String>>(), hashMap.toList())
    }

    @Test fun insertKeyHashMapTest() {
        var hashMap = HashMap<Int, String>()
        hashMap.insert(1, "100")
        assertEquals(linkedListOf(Pair(1, "100")), hashMap.toList())

        hashMap = getTestingHashMap()
        assertEquals(getTestingHashMapAsList(), hashMap.toList())

        hashMap.insert(1, "200"); hashMap.insert(1, "300");
        hashMap.insert(1, "1000");  hashMap.insert(1, "100")
        assertEquals(getTestingHashMapAsList(), hashMap.toList())
    }

    @Test fun findKeyEmptyHashMapTest() {
        var hashMap = HashMap<Int, String>()
        assertEquals(null, hashMap.search(1))
    }

    @Test fun findKeyHashMapTest() {

        var hashMap = HashMap<Int, String>()
        hashMap.insert(1, "100"); hashMap.insert(5, "500"); hashMap.insert(9, "900");
        assertEquals("100", hashMap.search(1))
        assertEquals("500", hashMap.search(5))
        assertEquals("900", hashMap.search(9))

        hashMap = getTestingHashMap()
        assertEquals("600", hashMap.search(6))
        assertEquals("1300", hashMap.search(13))
        assertEquals("2000", hashMap.search(20))
        assertEquals("1400", hashMap.search(14))
        assertEquals("1900", hashMap.search(19))
    }

    @Test fun removeKeyEmptyHashMap() {
        val hashMap = HashMap<Int, String>()
        hashMap.delete(1)
        assertEquals(linkedListOf<Pair<Int, String>>(), hashMap.toList())
    }

    @Test fun removeKeyNonExistentHashMapTest() {
        val hashMap = HashMap<Int, Int>()
        hashMap.insert(3, 300); hashMap.insert(7, 700); hashMap.insert(5, 500)
        hashMap.delete(1); hashMap.delete(4); hashMap.delete(6); hashMap.delete(8);
        assertEquals(linkedListOf<Pair<Int, Int>>(Pair(3, 300), Pair(5, 500), Pair(7, 700)), hashMap.toList())
    }

    @Test fun removeKeyHashMapTest() {
        var hashMap = HashMap<Int, String>()
        hashMap.insert(1, "100")
        hashMap.delete(1)
        assertEquals(linkedListOf<Pair<Int, String>>(), hashMap.toList())

        hashMap = getTestingHashMap()
        hashMap.delete(1); hashMap.delete(25); hashMap.delete(23); hashMap.delete(21);
        hashMap.delete(19); hashMap.delete(17); hashMap.delete(15); hashMap.delete(13);
        hashMap.delete(11); hashMap.delete(9); hashMap.delete(7); hashMap.delete(5);
        hashMap.delete(3); hashMap.delete(27);
        val ansList = linkedListOf<Pair<Int, String>>(Pair(2, "200"), Pair(4, "400"), Pair(6, "600"), Pair(8, "800"),
                Pair(10, "1000"), Pair(12, "1200"), Pair(14, "1400"), Pair(16, "1600"), Pair(18, "1800"),
                Pair(20, "2000"), Pair(22, "2200"), Pair(24, "2400"), Pair(26, "2600"), Pair(28, "2800"))
        assertEquals(ansList, hashMap.toList())
    }

    @Test fun hashMapUnionHashMap() {
        var hashMap1 = HashMap<Int, Int>()
        var hashMap2 = HashMap<Int, Int>()
        assertEquals(linkedListOf<Pair<Int, String>>(), hashMap1.union(hashMap2).toList())

        hashMap1.insert(3, 300); hashMap1.insert(7, 700); hashMap1.insert(5, 500)
        assertEquals(linkedListOf(Pair(3, 300), Pair(5, 500), Pair(7, 700)), hashMap1.union(hashMap2).toList())
        assertEquals(linkedListOf(Pair(3, 300), Pair(5, 500), Pair(7, 700)), hashMap2.union(hashMap1).toList())

        hashMap2.insert(3, 300); hashMap2.insert(7, 700); hashMap2.insert(10, 1000)
        assertEquals(linkedListOf(Pair(3, 300), Pair(5, 500), Pair(7, 700), Pair(10, 1000)),
                hashMap1.union(hashMap2).toList())
    }

    @Test fun HashMapUnionTreeTest() {
        var tree = AVLtree<Int, Int>()
        var hashMap = HashMap<Int, Int>()
        assertEquals(linkedListOf<Pair<Int, String>>(), hashMap.union(tree).toList())

        hashMap.insert(3, 300); hashMap.insert(7, 700); hashMap.insert(5, 500)
        assertEquals(linkedListOf(Pair(3, 300), Pair(5, 500), Pair(7, 700)), hashMap.union(tree).toList())

        tree.insert(3, 300); tree.insert(7, 700); tree.insert(10, 1000)
        assertEquals(linkedListOf(Pair(3, 300), Pair(5, 500), Pair(7, 700), Pair(10, 1000)),
                hashMap.union(tree).toList())

        tree = AVLtree<Int, Int>()
        assertEquals(linkedListOf(Pair(3, 300), Pair(5, 500), Pair(7, 700)), hashMap.union(tree).toList())
    }

    @Test fun hashMapIntersectionHashMap() {
        var hashMap1 = HashMap<Int, Int>()
        var hashMap2 = HashMap<Int, Int>()
        assertEquals(linkedListOf<Pair<Int, String>>(), hashMap1.intersection(hashMap2).toList())

        hashMap1.insert(3, 300); hashMap1.insert(7, 700); hashMap1.insert(5, 500)
        assertEquals(linkedListOf<Pair<Int, String>>(), hashMap1.intersection(hashMap2).toList())
        assertEquals(linkedListOf<Pair<Int, String>>(), hashMap2.intersection(hashMap1).toList())

        hashMap2.insert(3, 300); hashMap2.insert(7, 700); hashMap2.insert(10, 1000)
        assertEquals(linkedListOf(Pair(3, 300), Pair(7, 700)), hashMap1.intersection(hashMap2).toList())
    }

    @Test fun HashMapIntersectionTreeTest() {
        var tree = AVLtree<Int, Int>()
        var hashMap = HashMap<Int, Int>()
        assertEquals(linkedListOf<Pair<Int, String>>(), hashMap.intersection(tree).toList())

        hashMap.insert(3, 300); hashMap.insert(7, 700); hashMap.insert(5, 500)
        assertEquals(linkedListOf<Pair<Int, String>>(), hashMap.intersection(tree).toList())

        tree.insert(3, 300); tree.insert(7, 700); tree.insert(10, 1000)
        assertEquals(linkedListOf(Pair(3, 300), Pair(7, 700)), hashMap.intersection(tree).toList())
    }
}