// Homework #04 (29.09 - 13.10)
// Author: Kirill Smirenko, group 271
package homework.hw04

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Testing map union and intersection
 */
class UnionIntersectionTest {
    @Test fun testTreeTree() {
        testUnion(TreeMap<Int, String>(), TreeMap<Int, String>())
        testIntersection(TreeMap<Int, String>(), TreeMap<Int, String>())
    }

    @Test fun testTreeHash() {
        testUnion(TreeMap<Int, String>(), HashMap<Int, String>())
        testIntersection(TreeMap<Int, String>(), HashMap<Int, String>())
    }

    @Test fun testHashTree() {
        testUnion(HashMap<Int, String>(), TreeMap<Int, String>())
        testIntersection(HashMap<Int, String>(), TreeMap<Int, String>())
    }

    @Test fun testHashHash() {
        testUnion(HashMap<Int, String>(), HashMap<Int, String>())
        testIntersection(HashMap<Int, String>(), HashMap<Int, String>())
    }

    fun testUnion(emptyMap1 : AbstractMap<Int, String>, emptyMap2 : AbstractMap<Int, String>) {
        val map1 = emptyMap1
        val map2 = emptyMap2
        map1.insert(0, "00")
        map1.insert(1, "11")
        map1.insert(3, "31")
        map1.insert(2, "21")
        // case when one is empty
        assertEquals(listOf(0 to "00", 1 to "11", 2 to "21", 3 to "31"), map1.uniteWith(map2).toSortedList())
        assertEquals(listOf(0 to "00", 1 to "11", 2 to "21", 3 to "31"), map2.uniteWith(map1).toSortedList())

        map2.insert(0, "00")
        map2.insert(4, "42")
        map2.insert(5, "52")
        map2.insert(2, "22")
        map2.insert(1, "12")
        // case when both are non-empty (collisions possible)
        assertEquals(listOf(0 to "00", 1 to "11", 2 to "21", 3 to "31", 4 to "42", 5 to "52"),
            map1.uniteWith(map2).toSortedList())
        assertEquals(listOf(0 to "00", 1 to "12", 2 to "22", 3 to "31", 4 to "42", 5 to "52"),
            map2.uniteWith(map1).toSortedList())
    }

    fun testIntersection(emptyMap1 : AbstractMap<Int, String>, emptyMap2 : AbstractMap<Int, String>) {
        val map1 = emptyMap1
        val map2 = emptyMap2
        map1.insert(0, "00")
        map1.insert(1, "11")
        map1.insert(3, "31")
        map1.insert(2, "21")
        // case when one is empty - intersection is always empty
        assertEquals(listOf<Pair<Int, String>>(), map1.intersectWith(map2).toSortedList())
        assertEquals(listOf<Pair<Int, String>>(), map2.intersectWith(map1).toSortedList())

        map2.insert(0, "00")
        map2.insert(4, "42")
        map2.insert(5, "52")
        map2.insert(2, "22")
        map2.insert(1, "12")
        // case when both are non-empty (collisions possible)
        assertEquals(listOf(0 to "00", 1 to "11", 2 to "21"), map1.intersectWith(map2).toSortedList())
        assertEquals(listOf(0 to "00", 1 to "12", 2 to "22"), map2.intersectWith(map1).toSortedList())
    }
}