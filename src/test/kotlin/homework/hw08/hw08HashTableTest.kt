/*
 * Homework 8 (03.11.2015)
 * Tests for HashTable
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw08

import org.junit.Test
import kotlin.test.assertEquals
import org.junit.Assert.assertArrayEquals

public class hw08HashTableTest {

    /**
     * Insert 5 in table.
     *
     * Current state: []
     */
    @Test fun insertTest01() {
        val table = HashTable<Int>(10)
        test(listOf(5), table.insert(5))
    }

    /**
     * Insert 7 in table.
     *
     * Current state: [10, 100, 1, 2, 3, 5, 9]
     */
    @Test fun insertTest02() {
        val table = HashTable<Int>(10).insert(5).insert(2).insert(1).insert(3).insert(10)
                .insert(9).insert(100)
        test(listOf(10, 100, 1, 2, 3, 5, 7, 9), table.insert(7))
    }

    /**
     * Insert 1001 in table.
     *
     * Current state: [10, 100, 1, 2, 3, 5, 7, 9]
     */
    @Test fun insertTest03() {
        val table = HashTable<Int>(10).insert(5).insert(2).insert(1).insert(3).insert(10)
                .insert(9).insert(100).insert(7)
        test(listOf(10, 100, 1, 1001, 2, 3, 5, 7, 9), table.insert(1001))
    }


    /**
     * Remove 7 from table.
     *
     * Current state: [10, 100, 1, 1001, 2, 3, 5, 7, 9]
     */
    @Test fun removeTest01() {
        val table = HashTable<Int>(10).insert(5).insert(2).insert(1).insert(3).insert(10)
                .insert(9).insert(100).insert(7).insert(1001)
        test(listOf(10, 100, 1, 1001, 2, 3, 5, 9), table.remove(7))
    }

    /**
     * Remove 42 from table.
     *
     * Current state: [10, 100, 1, 1001, 2, 3, 5, 9]
     */
    @Test fun removeTest02() {
        val table = HashTable<Int>(10).insert(5).insert(2).insert(1).insert(3).insert(10)
                .insert(9).insert(100).insert(1001)
        test(listOf(10, 100, 1, 1001, 2, 3, 5, 9), table.remove(42))
    }

    /**
     * Remove 10 from table.
     *
     * Current state: [10, 100, 1, 1001, 2, 3, 5, 9]
     */
    @Test fun removeTest03() {
        val table = HashTable<Int>(10).insert(5).insert(2).insert(1).insert(3).insert(10)
                .insert(9).insert(100).insert(1001)
        test(listOf(100, 1, 1001, 2, 3, 5, 9), table.remove(10))
    }


    /**
     * Tests for union.
     */
    @Test fun unionTest01() {
        val table = HashTable<Int>(10).insert(5).insert(2).insert(1).insert(3)
                .insert(9).insert(100).insert(1001)
        val newTable = HashTable<Int>(10).insert(5)
        test(listOf(100, 1, 1001, 2, 3, 5, 9), table.unite(newTable))
    }

    @Test fun unionTest02() {
        val table = HashTable<Int>(10).insert(5).insert(2).insert(1).insert(3)
                .insert(9).insert(100).insert(1001)
        val newTable = HashTable<Int>(10).insert(17).insert(500)
        test(listOf(100, 500, 1, 1001, 2, 3, 5, 17, 9), table.unite(newTable))
    }

    @Test fun unionTest03() {
        val table = HashTable<Int>(10).insert(5).insert(2).insert(1).insert(3)
                .insert(9).insert(100).insert(1001).insert(17).insert(500)
        val newTable = HashTable<Int>(10)
        test(listOf(100, 500, 1, 1001, 2, 3, 5, 17, 9), table.unite(newTable))
    }


    /**
     * Tests for intersection.
     */
    @Test fun intersectionTest01() {
        val table    = HashTable<Int>(10).insert(5).insert(2).insert(1).insert(3).insert(9)
        val newTable = HashTable<Int>(10)
        test(listOf(), table.intersect(newTable))
    }

    @Test fun intersectionTest02() {
        val table    = HashTable<Int>(10).insert(5).insert(2).insert(1).insert(3).insert(9)
        val newTable = HashTable<Int>(10).insert(7).insert(17)
        test(listOf(), table.intersect(newTable))
    }

    @Test fun intersectionTest03() {
        val table    = HashTable<Int>(10).insert(3).insert(17).insert(1)
        val newTable = HashTable<Int>(10).insert(3).insert(17).insert(1)
        test(listOf(1, 3, 17), table.intersect(newTable))
    }

    fun test(result : List<Int>, table : HashTable<Int>) {
        org.junit.Assert.assertArrayEquals(result.toIntArray(), table.toList().toIntArray())
    }


    /**
     * Tests for search function.
     */
    @Test fun searchTest01() {
        val table = HashTable<Int>(10).insert(3).insert(17).insert(1)
        assertEquals(true, table.contains(1))
    }

    @Test fun searchTest02() {
        val table = HashTable<Int>(10).insert(3).insert(17).insert(1)
        assertEquals(true, table.contains(3))
    }

    @Test fun searchTest03() {
        val table = HashTable<Int>(10).insert(3).insert(17).insert(1)
        assertEquals(false, table.contains(42))
    }


    /**
     * Test for iterator.
     */
    @Test fun iteratorTest() {
        val table = HashTable<Int>(10).insert(5).insert(2).insert(1).insert(15).insert(9)
        val list  = linkedListOf(0)
        for (t in table) list.add(t)
        assertArrayEquals(arrayOf(0, 1, 2, 5, 15, 9), list.toArray())
    }
}