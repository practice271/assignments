package hw04

import org.junit.Test
import kotlin.test.assertEquals

class HashTable_Tests {
    @Test fun insertTest1() {
        var table = HashTable()
        table.insert(10)
        table.insert(1)
        table.insert(7)
        assertEquals(listOf(1,7,10), table.toList())
    }

    @Test fun insertTest2() {
        var table = HashTable()
        table.insert(10)
        table.insert(1)
        table.insert(7)
        table.insert(5)
        table.insert(19)
        table.insert(8)
        assertEquals(listOf(1,5,7,8,10,19), table.toList())
    }

    @Test fun deleteTest1() {
        var table = HashTable()
        table.insert(1)
        table.insert(2)
        table.insert(3)
        table.insert(4)
        table.delete(4)
        assertEquals(listOf(1,2,3), table.toList())
    }

    @Test fun deleteTest2() {
        var table = HashTable()
        table.insert(1)
        table.insert(2)
        table.insert(3)
        table.insert(4)
        table.insert(8)
        table.delete(3)
        table.delete(1)
        table.delete(2)
        assertEquals(listOf(4,8), table.toList())
    }

    @Test fun searchTest1() {
        var table = HashTable()
        table.insert(1)
        table.insert(2)
        table.insert(3)
        table.insert(4)
        val flag = table.search(1)
        assertEquals(true, flag)
    }


    @Test fun searchTest2() {
        var table = HashTable()
        table.insert(1)
        table.insert(2)
        table.insert(3)
        table.insert(4)
        val flag = table.search(10)
        assertEquals(false, flag)
    }

    @Test fun unionTable1() {
        var table1 = HashTable()
        var table2 = HashTable()
        table1.insert(1)
        table1.insert(2)
        table2.insert(4)
        table2.insert(8)
        assertEquals(listOf(1,2,4,8), table1.union(table2).toList())
    }

    @Test fun unionTable2() {
        var table1 = HashTable()
        var table2 = HashTable()
        table1.insert(1)
        table1.insert(2)
        table1.insert(3)
        table1.insert(4)
        table2.insert(5)
        table2.insert(6)
        assertEquals(listOf(1,2,3,4,5,6), table1.union(table2).toList())
    }

    @Test fun intersectionTest1() {
        var table1 = HashTable()
        var table2 = HashTable()
        table1.insert(1)
        table1.insert(2)
        table1.insert(3)
        table1.insert(4)
        table2.insert(4)
        table2.insert(6)
        assertEquals(listOf(4), table1.insersection(table2).toList())
    }

    @Test fun intersectionTest2() {
        var table1 = HashTable()
        var table2 = HashTable()
        table1.insert(1)
        table1.insert(2)
        table1.insert(3)
        table1.insert(8)
        table2.insert(4)
        table2.insert(9)
        table2.insert(1)
        table2.insert(8)
        assertEquals(listOf(1,8), table1.insersection(table2).toList())
    }

}