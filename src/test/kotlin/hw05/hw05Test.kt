package hw05

/**
 * Created by Antropov Igor on 18.10.2015.
 */

import org.junit.Test
import kotlin.test.assertEquals


public class hw05Test{
    @Test fun incrementerTest(){
        assert(incrementer(100000) < 100000)
    }
    internal fun tempSet(temp: IntArray){ // [5,3,9,6,2]
        temp.set(0,5)
        temp.set(1,3)
        temp.set(2,9)
        temp.set(3,6)
        temp.set(4,2)
    }
    @Test fun mergeSortTest1_1(){
        var temp : IntArray = IntArray(5)
        tempSet(temp)
        parallelMergeSort(temp, 1)
        val res = listOf(2,3,5,6,9)
        assertEquals(temp.toList(), res)
    }
    @Test fun mergeSortTest1_2(){
        var temp : IntArray = IntArray(5)
        tempSet(temp)
        parallelMergeSort(temp, 2)
        val res = listOf(2,3,5,6,9)
        assertEquals(temp.toList(), res)
    }
    @Test fun mergeSortTest1_3(){
        var temp : IntArray = IntArray(5)
        tempSet(temp)
        parallelMergeSort(temp, 4)
        val res = listOf(2,3,5,6,9)
        assertEquals(temp.toList(), res)
    }
    @Test fun mergeSortTest1_4(){
        var temp : IntArray = IntArray(5)
        tempSet(temp)
        parallelMergeSort(temp, 8)
        val res = listOf(2,3,5,6,9)
        assertEquals(temp.toList(), res)
    }
    @Test fun mergeSortTest2_1(){
        var temp : IntArray = IntArray(1001)
        for (i in 0..1000)
            temp.set(i, (-1)*(i-499))
        parallelMergeSort(temp, 1)
        assertEquals(temp.toList(), temp.sorted())
    }
    @Test fun mergeSortTest2_2(){
        var temp : IntArray = IntArray(1001)
        for (i in 0..1000)
            temp.set(i, (-1)*(i-499))
        parallelMergeSort(temp, 2)
        assertEquals(temp.toList(), temp.sorted())
    }
    @Test fun mergeSortTest2_3(){
        var temp : IntArray = IntArray(1001)
        for (i in 0..1000)
            temp.set(i, (-1)*(i-499))
        parallelMergeSort(temp, 4)
        assertEquals(temp.toList(), temp.sorted())
    }
    @Test fun mergeSortTest2_4(){
        var temp : IntArray = IntArray(1001)
        for (i in 0..1000)
            temp.set(i, (-1)*(i-499))
        parallelMergeSort(temp, 1)
        assertEquals(temp.toList(), temp.sorted())
    }
}