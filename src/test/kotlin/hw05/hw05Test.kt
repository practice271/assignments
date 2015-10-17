package hw05

import org.junit.Test
import kotlin.test.assertEquals

public class hw05Test {
    Test fun mergeTest1() { // with one thread
        val a = Array(5,{i -> i})
        val res = mergeSort(a,1).print()
        assertEquals(res,"[ 0 1 2 3 4 ]")
    }
    Test fun mergeTest2() {
        val a = arrayOf(9,0,1,2,5)
        val res = mergeSort(a,1).print()
        assertEquals(res,"[ 0 1 2 5 9 ]")
    }
    Test fun mergeTest3() {
        val a = arrayOf(9,0,1,2,5,10,9,8,5,33,4,7,8,9)
        val res = mergeSort(a,1).print()
        assertEquals(res,"[ 0 1 2 4 5 5 7 8 8 9 9 9 10 33 ]")
    }
    Test fun mergeTest4() { // with several threads
        val a = Array(5,{i -> i})
        val res = mergeSort(a,2).print()
        assertEquals(res,"[ 0 1 2 3 4 ]")
    }
    Test fun mergeTest5() {
        val a = arrayOf(9,0,1,2,5)
        val res = mergeSort(a,3).print()
        assertEquals(res,"[ 0 1 2 5 9 ]")
    }
    Test fun mergeTest6() {
        val a = arrayOf(9,0,1,2,5,10,9,8,5,33,4,7,8,9)
        val res = mergeSort(a,4).print()
        assertEquals(res,"[ 0 1 2 4 5 5 7 8 8 9 9 9 10 33 ]")
    }
}