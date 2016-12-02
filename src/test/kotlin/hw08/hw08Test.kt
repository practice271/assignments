package hw08

import org.junit.Test
import kotlin.test.assertEquals


public class hw08Test {
    @Test fun insertTest1() {
        val tree1 = AVLTree_set(Node(9,Empty(),Empty()))
        assertEquals(listOf(9,8), tree1.insert(8).toList())
    }
    @Test fun insertTest2() {
        val tree1 = AVLTree_set(Node(7,Node(5,Empty(),Empty()),Empty()))
        assertEquals(listOf(5,2,7), tree1.insert(2).toList())
    }
    @Test fun insertTest3() {
        val tree1 = AVLTree_set(Empty())
        assertEquals(listOf(3), tree1.insert(3).toList())
    }
    @Test fun removeTest1() {
        val tree1 = AVLTree_set(Node(7,Node(5,Empty(),Empty()),Empty()))
        assertEquals(listOf(7,0), tree1.remove(5).toList())
    }
    @Test fun removeTest2() {
        val tree1 = AVLTree_set(Node(9,Empty(),Empty()))
        assertEquals(listOf(0), tree1.remove(9).toList())
    }
    @Test fun removeTest3() {
        val tree1 = AVLTree_set(Node (6,Empty(),Node(7,Node(3,Empty(),Empty()),Node(10,Empty(),Empty()))))
        assertEquals(listOf(7,6,3,0), tree1.remove(10).toList())
    }
    @Test fun searchTest1() {
        val tree1 = AVLTree_set(Node (6,Empty(),Node(7,Node(3,Empty(),Empty()),Node(10,Empty(),Empty()))))
        assertEquals(true, tree1.search(6))
    }
    @Test fun searchTest2() {
        val tree1 = AVLTree_set(Node (6,Empty(),Node(7,Node(3,Empty(),Empty()),Node(10,Empty(),Empty()))))
        assertEquals(false, tree1.search(15))
    }
    @Test fun searchTest3() {
        val tree1 = AVLTree_set(Empty())
        assertEquals(false, tree1.search(3))
    }
    @Test fun unionTest1() {
        val tree1 = AVLTree_set(Node(7,Node(5,Empty(),Empty()),Empty()))
        val tree2 = AVLTree_set(Node(7,Node(5,Empty(),Empty()),Empty()))
        assertEquals(listOf(7,5), tree1.union(tree2).toList().delRep())
    }
    @Test fun unionTest2() {
        val tree1 = AVLTree_set(Node(7,Node(5,Empty(),Empty()),Empty()))
        val tree2 = AVLTree_set(Node (6,Empty(),Node(7,Node(3,Empty(),Empty()),Node(10,Empty(),Empty()))))
        assertEquals(listOf(7,5,3,6,10), tree1.union(tree2).toList().delRep())
    }
    @Test fun intersectionTest1() {
        val tree1 = AVLTree_set(Node(7,Node(5,Empty(),Empty()),Empty()))
        val tree2 = AVLTree_set(Node (6,Empty(),Node(7,Node(3,Empty(),Empty()),Node(10,Empty(),Empty()))))
        assertEquals(listOf(7), tree1.intersection(tree2).toList())
    }
    @Test fun intersectionTest2() {
        val tree1 = AVLTree_set(Node (6,Empty(),Node(7,Node(3,Empty(),Empty()),Node(10,Empty(),Empty()))))
        val tree2 = AVLTree_set(Node (6,Empty(),Node(7,Node(3,Empty(),Empty()),Node(10,Empty(),Empty()))))
        assertEquals(listOf(6,3,7,10), tree1.intersection(tree2).toList())
    }
    @Test fun insertTest_HT1() {
        val table1 = HashTable_set(4)
        table1.insert(1)
        table1.insert(5)
        table1.insert(4)
        table1.insert(9)
        assertEquals(listOf(4,1,5,9), table1.toList())
    }
    @Test fun removeTest_HT1() {
        val table1 = HashTable_set(4)
        table1.insert(1)
        table1.insert(5)
        table1.insert(4)
        table1.insert(9)
        table1.remove(4)
        assertEquals(listOf(1,5,9), table1.toList())
    }
    @Test fun searchTest_HT1() {
        val table1 = HashTable_set(4)
        table1.insert(1)
        table1.insert(5)
        table1.insert(4)
        table1.insert(9)
        assertEquals(true, table1.search(9))
    }
    @Test fun searchTest_HT2() {
        val table1 = HashTable_set(4)
        table1.insert(1)
        table1.insert(5)
        table1.insert(4)
        table1.insert(9)
        assertEquals(false, table1.search(100))
    }
    @Test fun unionTest_HT1() {
        val table1 = HashTable_set(4)
        val table2 = HashTable_set(2)
        table1.insert(1)
        table1.insert(5)
        table1.insert(4)
        table1.insert(9)
        table2.insert(5)
        table2.insert(10)
        assertEquals(listOf(4,5,1,9,10), table1.union(table2).toList().delRep())
    }
    @Test fun unionTest_HT2() {
        val table1 = HashTable_set(2)
        val table2 = HashTable_set(2)
        table1.insert(1)
        table1.insert(5)
        table2.insert(5)
        table2.insert(1)
        assertEquals(listOf(5,1), table1.union(table2).toList().delRep())
    }
    @Test fun intersectionTest_HT1() {
        val table1 = HashTable_set(2)
        val table2 = HashTable_set(2)
        table1.insert(1)
        table1.insert(5)
        table2.insert(5)
        table2.insert(1)
        assertEquals(listOf(5,1), table1.intersection(table2).toList())
    }
    @Test fun intersectionTest_HT2() {
        val table1 = HashTable_set(3)
        val table2 = HashTable_set(2)
        table1.insert(1)
        table1.insert(5)
        table1.insert(9)
        table2.insert(5)
        table2.insert(0)
        assertEquals(listOf(5), table1.intersection(table2).toList())
    }

}
