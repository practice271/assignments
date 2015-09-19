package homework.hw02

import org.junit.Test
import kotlin.test.assertEquals

public class hw02Test{
    Test fun HeapSortTest1(){
        var arr = arrayOf(3,1,0,-2,1,-2,-555,220,11,75,0,11111,-5221,7,125,-66)
        var arr_res = arrayOf(-5221,-555,-66,-2,-2,0,0,1,1,3,7,11,75,125,220,11111)
        HeapSortTest(arr, arr_res)
    }

    Test fun findMaxTest(){
        val tree = Node(7, Node(10, Leaf(3), Leaf(2)), Node(2, Leaf(5), Empty()))
        /* Tree:
              7
           10  2
          3  2  5 _
       */
        assertEquals(20,findMax({x , y -> x + y}, 0, tree))
    }

    Test fun PeanoTest(){
        var a = S(S(S(Zero())))
        var b = S(S(Zero()))
        var c = Zero()

        assertEquals(5, toInt(plus(a,b)))
        assertEquals(3, toInt(sub(a,c)))
        assertEquals(0, toInt(mult(b,c)))
        assertEquals(8, toInt(exp(b,a)))
    }

    fun HeapSortTest(arr:Array<Int>, arr_res:Array<Int>){
        HeapSort(arr)
        org.junit.Assert.assertArrayEquals(arr, arr_res)
    }
}