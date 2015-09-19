package homework.hw01

import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals

public class hw01Test
{
    fun arrayEqual(arr1 : Array<Int>, arr2 : Array<Int> ) : Boolean
    {
        if(arr1.size() == arr2.size())
        {
            for( i in 0 .. arr1.size() - 1)
            {
                if(arr1[i] != arr2[i]) return false
            }
            return true
        }
        else
        {
            return false
        }
    }

    Test fun HeapsortTest()
    {
        val res = arrayOf(-1,0,1,2,3,4,5,6,7,8,9)
        val arr = arrayOf(4,1,9,6,5,8,0,2,3,7, -1)
        arr.Heapsort()
        assertEquals(true, arrayEqual(res, arr))
    }


    Test fun maxWayTest01()
    {
        val tree = Node(3,Node(-1, Leaf(2), Leaf(5)), Leaf(9))
        assertEquals(12, maxWay({x,y -> x+y}, 0, tree))
    }


    Test fun maxWayTest02()
    {
        val tree = Node(0, Leaf(2), Leaf(4))
        assertEquals(4, maxWay({x,y -> x+y}, 0, tree))
    }


    Test fun maxWayTest03()
    {
        val tree = Node(-3,Node(-1, Leaf(2), Leaf(5)), Leaf(3))
        assertEquals(1, maxWay({x,y -> x+y}, 0, tree))
    }

    Test fun foldTest01()
    {
        val tree = Node(-3,Node(-1, Leaf(2), Leaf(5)), Leaf(3))
        assertEquals(6, fold({x,y ->  x + y}, {x,y -> x+y}, 0, tree))
    }

    Test fun foldTest02()
    {
        val tree = Node(-3,Node(-1, Leaf(2), Leaf(5)), Leaf(3))
        assertEquals(1, fold( {x,y -> x + y}, {x,y ->  Math.max(x,y)}, 0, tree))
    }


    Test fun sumTest()
    {
        val a = genPeano(4)
        val b = genPeano(3)
        assertEquals(7,sum(a,b).toInt())
    }

    Test fun subTest()
    {
        val a = genPeano(5)
        val b = genPeano(2)
        assertEquals(3, sub(a,b).toInt())
    }

    Test fun multTest()
    {
        val a = genPeano(5)
        val b = genPeano(2)
        assertEquals(10, mult(a,b).toInt())
    }

    Test fun powTest()
    {
        val a = genPeano(5)
        val b = genPeano(2)
        assertEquals(25, pow(a,b).toInt())
    }
}