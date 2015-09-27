package hw03

import org.junit.Test
import kotlin.test.assertEquals

public class hw03Test {
    Test fun insertTest1() {
        val tree1 = Node(7,Node(5,Empty(),Empty(),1),Empty(),2)
        val res1 = printAVL(insertAVL(tree1,8))
        println()
        val res2 = printAVL(Node(7,Node(5,Empty(),Empty(),1),Node(8,Empty(),Empty(),1),2))
        assertEquals(res1, res2)
    }
    Test fun insertTest2() {
        val tree1 = Node(7,Node(5,Empty(),Empty(),1),Empty(),2)
        val res1 = printAVL(insertAVL(tree1,2))
        println()
        val res2 = printAVL(Node(7,Node(5,Node(2,Empty(),Empty(),1),Empty(),2),Empty(),3))
        assertEquals(res1, res2)
    }
    Test fun insertTest3() {
        val tree1 = Empty()
        val res1 = printAVL(insertAVL(tree1,2))
        println()
        val res2 = printAVL(Node(2,Empty(),Empty(),1))
        assertEquals(res1, res2)
    }
    Test fun removeTest1() {
        val tree1 = Node(7,Node(5,Empty(),Empty(),1),Empty(),2)
        val res1 = printAVL(removeAVL(tree1,5))
        println()
        val res2 = printAVL(Node(7,Empty(),Empty(),1))
        assertEquals(res1, res2)
    }
    Test fun removeTest2() {
        val tree = Node (6,Empty(),Node(7,Node(3,Empty(),Empty(),1),Node(10,Empty(),Empty(),1),2),3)
        val res1 = printAVL(removeAVL(tree,6))
        println()
        val res2 = printAVL(Node(7,Node(3,Empty(),Empty(),1),Node(10,Empty(),Empty(),1),2))
        assertEquals(res1, res2)
    }
    Test fun removeTest3() {
        val tree = Node (60,Empty(),Node(70,Node(3,Empty(),Empty(),1),Node(100,Empty(),Empty(),1),2),3)
        val res1 = printAVL(removeAVL(tree,1000))
        println()
        val res2 = printAVL(Node (60,Empty(),Node(70,Node(3,Empty(),Empty(),1),Node(100,Empty(),Empty(),1),2),3))
        assertEquals(res1, res2)
    }
    Test fun searchTest1() {
        val tree = Node (60,Empty(),Node(70,Node(3,Empty(),Empty(),1),Node(100,Empty(),Empty(),1),2),3)
        val res1 = searchAVL(tree,7)
        assertEquals(res1, false)
    }
    Test fun searchTest2() {
        val tree = Node (60,Empty(),Node(70,Node(3,Empty(),Empty(),1),Node(100,Empty(),Empty(),1),2),3)
        val res1 = searchAVL(tree,70)
        assertEquals(res1, true)
    }
    Test fun searchTest3() {
        val tree = Empty()
        val res1 = searchAVL(tree,70)
        assertEquals(res1, false)
    }
}