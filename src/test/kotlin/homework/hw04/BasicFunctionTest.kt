// Homework #04 (29.09 - 13.10)
// Author: Kirill Smirenko, group 271
package homework.hw04

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests basic functions of each of two implementations: insert, remove, search.
 */
class BasicFunctionTest {
    @Test fun testTreeMap() {
        testInsert(TreeMap<Int, String>())
        testSearch(TreeMap<Int, String>())
        testSearch(TreeMap<Int, String>())
    }

    @Test fun testHashMap() {
        testInsert(HashMap<Int, String>())
        testSearch(HashMap<Int, String>())
        testSearch(HashMap<Int, String>())
    }

    private fun testInsert(emptyMap : AbstractMap<Int, String>) {
        val map = emptyMap
        map.insert(2, "020")
        map.insert(5, "050")
        map.insert(0, "000")
        map.insert(5, "051")
        assertEquals(listOf(0 to "000", 2 to "020", 5 to "051"), map.toSortedList())
    }

    private fun testRemove(emptyMap : AbstractMap<Int, String>) {
        val map = emptyMap
        map.insert(2, "020")
        map.insert(5, "050")
        map.insert(0, "000")
        map.insert(5, "051")
        map.remove(2)
        map.remove(4)
        assertEquals(listOf(0 to "000", 5 to "051"), map.toSortedList())
    }

    private fun testSearch(emptyMap : AbstractMap<Int, String>) {
        val map = emptyMap
        map.insert(2, "020")
        map.insert(5, "050")
        map.insert(0, "000")
        assertEquals("000", map.search(0))
        assertEquals(null, map.search(1))
        assertEquals("050", map.search(5))
        map.insert(5, "051")
        assertEquals("051", map.search(5))
        map.remove(5)
        assertEquals(null, map.search(5))
    }
}