package homework5

import org.junit.Test
import kotlin.test.assertEquals

public class HW05 {
    //Test increment
    @Test
    fun testIncWithoutSync1(){
       assert(100000 > incWithoutSync(100000))
    }
    fun arrToStr(arr:Array<Int>):String{
        var s = ""
        arr.forEach {
            s += "$it"+" "
        }
        return s
    }
    //Test MergeSort
    @Test
    fun testMergeSort1(){
        val arr1 = arrayOf(1,3,4,6,5,7,8,9,2)
        assertEquals(arrToStr(sorting(arr1, 4)), "1 2 3 4 5 6 7 8 9 ")
    }
    @Test
    fun testMergeSort2(){
        val arr2 = emptyArray<Int>()
        assertEquals(arrToStr(sorting(arr2,2)), "")
    }
    @Test
    fun testMergeSort3(){
        val arr3 = arrayOf(1,2,1,2,1,2)
        assertEquals(arrToStr(sorting(arr3, 1)), "1 1 1 2 2 2 ")
    }
    @Test
    fun testMergeSort4(){
        val arr4 = arrayOf(1,2,3, -1,-2,-3)
        assertEquals(arrToStr(sorting(arr4, 3)), "-3 -2 -1 1 2 3 ")
    }
}

