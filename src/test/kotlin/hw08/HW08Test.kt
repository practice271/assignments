package hw08

import org.junit.Test
import kotlin.test.assertEquals

public class HW08Test {
    @Test fun iteratorTree1() {
        val tree = Node(5, Node(3, Empty(), Node(4, Empty(), Empty())), Node(7, Empty(), Empty()))
        val stringTree = tree.toString()
        val res = "3 4 5 7 \n"
        assertEquals(res, stringTree)
    }
    @Test fun iteratorTree2() {
        val tree = Node(5, Node(3, Empty(), Empty()), Empty())
        val next = tree.iterator().next()
        assertEquals(next, 3)
    }
    @Test fun iteratorTree3() {
        val tree = Node(5, Empty(), Empty())
        val hasNext = tree.iterator().hasNext()
        assertEquals(hasNext, true)
    }
    @Test fun iteratorEmptyTree() {
        val tree = Empty()
        val hasNext = tree.iterator().hasNext()
        assertEquals(hasNext, false)
    }

    val hashTable = HashTable(3)
    @Test fun iteratorHashTable1() {
        hashTable.insert(2)
        hashTable.insert(1)
        hashTable.insert(3)
        hashTable.insert(4)
        val stringTable = hashTable.toString()
        val res = "[3] [4, 1] [2] \n"
        assertEquals(res, stringTable)
    }
    @Test fun iteratorHashTable2() {
        hashTable.insert(0)
        hashTable.insert(3)
        val next = hashTable.iterator().next()
        assertEquals(next, 3)
    }
    @Test fun iteratorHashTable3() {
        hashTable.insert(0)
        hashTable.insert(3)
        val hasNext = hashTable.iterator().hasNext()
        assertEquals(hasNext, true)
    }
    @Test fun iteratorHashTable4() {
        hashTable.insert(0)
        hashTable.insert(1)
        val next = hashTable.iterator().next()
        assertEquals(next, 0)
    }
    @Test fun iteratorHashTable5() {
        hashTable.insert(0)
        hashTable.insert(1)
        val hasNext = hashTable.iterator().hasNext()
        assertEquals(hasNext, true)
    }
    @Test fun iteratorEmptyHashTable() {
        val hasNext = hashTable.iterator().hasNext()
        assertEquals(hasNext, false)
    }
}