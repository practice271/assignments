package homework.hw05

import org.junit.Test
import kotlin.test.assertEquals

class hw05test {

    @Test fun incrementTest()
    {
        assert(wrongIncrementor(100000,1000) < 100000)
    }

    val arr1 = Array(1000, {i -> 1000 - i})
    val res1 = Array(1000, {i -> i + 1})

    @Test fun mergesortTest01(){
        for(i in 0 .. 3)
        org.junit.Assert.assertArrayEquals(res1,arr1.parallelMergeSort(Math.pow(2.0,i.toDouble()).toInt()) )
    }

    @Test fun mergesortTest02(){

        org.junit.Assert.assertArrayEquals(res1, arr1.parallelMergeSort(100))
    }


    val arr2 = Array(70000, {i -> 70000 - i})
    val res2 = Array(70000, {i -> i + 1})

    @Test fun mergesortTest03(){
        for(i in 0 .. 3)
            org.junit.Assert.assertArrayEquals(res2,arr2.parallelMergeSort(Math.pow(2.0,i.toDouble()).toInt()) )
    }

    val arr3 = arrayOf(-4,7,2,-6,11,56)
    val res3 = arrayOf(-6,-4,2,7,11,56)

    @Test fun mergesortTest04(){
        for(i in 0 .. 3)
        org.junit.Assert.assertArrayEquals(res3, arr3.parallelMergeSort(Math.pow(2.0 ,i.toDouble()).toInt()))
    }

/*  size  | threads |  time
*   10^7  |    1    |  26s
*         |    2    |  22s
*         |    4    |  20s
 *
 * 5*10^6 |    1    |  12s
 *        |    2    |  10s
 *        |    4    |   9s
 *
* */

}