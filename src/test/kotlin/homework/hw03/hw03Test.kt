package homework.hw03

import org.junit.Test
import kotlin.test.assertEquals

public class hw03Test {

    val tree1 = Node(3,Node(2, Empty(), Empty()), Node(6,Empty(), Empty()))
    val res1 = arrayOf(3,2,1,6)
    val res2 = arrayOf(3,2,6,5)
    val res3 = arrayOf(3,2,6)

    Test fun insertTest01()
    {
        val temp = tree1.Insert(1)
        org.junit.Assert.assertArrayEquals(temp.toArray().reversedArray(), res1)
    }


    Test fun insertTest02()
    {
        val temp = tree1.Insert(5)
        org.junit.Assert.assertArrayEquals(temp.toArray().reversedArray(), res2)
    }


    Test fun insertTest03()
    {
        val temp = tree1.Insert(2)
        org.junit.Assert.assertArrayEquals(temp.toArray().reversedArray(), res3)
    }

    val tree2 = Node (3,Empty(),Node(4,Empty(), Empty()) )
    val rotRes01 = arrayOf(4,3,5)

    Test fun rotateTest01()
    {
        val temp = tree2.Insert(5)
        org.junit.Assert.assertArrayEquals(temp.toArray().reversedArray(), rotRes01)

    }

    val tree3 = Node(3,Node(2,Empty(), Empty()), Empty())
    val rotRes02 = arrayOf(2,1,3)

    Test fun rotateTest02()
    {
        val temp = tree3.Insert(1)
        org.junit.Assert.assertArrayEquals(temp.toArray().reversedArray(), rotRes02)
    }

    val tree4 = Node(5,Node(3, Node(2,Node(1,Empty(), Empty()),Empty()),
            Node(4,Empty(),Empty())),Node(8,Node(7,Empty(),Empty()),Empty()))
    val rotRes03 = arrayOf(3,2,1,5,4,7)

    Test fun rotateTest03()
    {
        val temp = tree4.Delete(8)
        org.junit.Assert.assertArrayEquals(temp.toArray().reversedArray(), rotRes03)
    }

    Test fun searchTest01()
    {
        assertEquals(true,tree4.Search(8))
    }

    Test fun searchTest02()
    {
        assertEquals(true, tree4.Search(4))
    }

    Test fun searchTest03()
    {
        assertEquals(false, tree4.Search(0))
    }








}