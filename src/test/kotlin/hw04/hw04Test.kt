package hw04

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Mikhail on 03.10.2015.
 */

public class HW04Test {
    @Test fun AvlTreeUnionTest(){
        val avt1 = AvlTree()
        val arr = arrayListOf(5, 3, 7, 6, 8)
        for (x in arr) {
            avt1.insert(x)
        }
        val avt2 = AvlTree()
        val arr2 = arrayListOf(4, 11, 12, 13, 14, 17, 19)
        for (x in arr2) {
            avt2.insert(x)
        }
        val res = (avt1.union(avt2)).toList()
        var res1 = (avt1.union(avt1)).toList()
        assertEquals(res, listOf(7, 5, 3, 4, 6, 13, 11, 8, 12, 17, 14, 19))
        assertEquals(res1, listOf(5, 3, 7, 6, 8))
    }

    @Test fun AvlTreeIntersectionTest() {
        val avt1 = AvlTree()
        val arr = arrayListOf(5, 3, 7, 6, 8)
        for (x in arr) {
            avt1.insert(x)
        }
        val avt2 = AvlTree()
        val arr2 = arrayListOf(4, 11, 12, 13, 14, 17, 19)
        for (x in arr2) {
            avt2.insert(x)
        }
        val res = (avt1.intersection(avt2)).toList()
        var res1 = (avt1.intersection(avt1)).toList()
        assertEquals(res, emptyList<Int>())
        assertEquals(res1, listOf(5, 3, 7, 6, 8))
    }

    @Test fun HashTableInsertTest() {
        val ht1 = HashTable(10)
        var arr = arrayListOf(5, 55, 12, 3)
        for (x in arr ) {
            ht1.insert(x)
        }
        val res = ht1.toList()
        assertEquals(res, listOf(12, 3, 5, 55))
    }

    @Test fun HashTableDeleteTest() {
        val ht1 = HashTable(10)
        var arr = arrayListOf(5, 55, 12, 3)
        for (x in arr ) {
            ht1.insert(x)
        }
        ht1.delete(5)
        ht1.delete(1)
        val res = ht1.toList()
        assertEquals(listOf(12, 3, 55), res)
    }

    @Test fun HashTableSearchTest() {
        val ht1 = HashTable(10)
        var arr = arrayListOf(5, 55, 12, 3)
        for (x in arr ) {
            ht1.insert(x)
        }
        val res_t = ht1.search(5)
        val res_f = ht1.search(1)
        assertEquals(true, res_t)
        assertEquals(false, res_f)
    }

    @Test fun HashTableUnionTest() {
        val ht1 = HashTable(10)
        var arr1 = arrayListOf(5, 55, 12, 3)
        for (x in arr1 ) {
            ht1.insert(x)
        }
        val ht2 = HashTable(10)
        var arr2 = arrayListOf(13, 54, 17, 31)
        for (x in arr2 ) {
            ht2.insert(x)
        }
        val res1 = ht1.union(ht2).toList()
        val res2 = ht1.union(ht1).toList()
        assertEquals(res1, listOf(31, 12, 3, 13, 54, 5, 55, 17))
        assertEquals(res2, listOf(12, 3, 5, 55))
    }

    @Test fun HashTableIntersectionTest() {
        val ht1 = HashTable(10)
        var arr1 = arrayListOf(5, 55, 12, 3, 13)
        for (x in arr1 ) {
            ht1.insert(x)
        }
        val ht2 = HashTable(10)
        var arr2 = arrayListOf(13, 54, 17, 31)
        for (x in arr2 ) {
            ht2.insert(x)
        }
        val res1 = ht1.intersection(ht2).toList()
        val res2 = ht1.intersection(ht1).toList()
        assertEquals(res1, listOf(13))
        assertEquals(res2, listOf(12, 3, 13, 5, 55))
    }
}
