package homework.hw08

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

val set1 = AVLTreeSet(arrayOf(2,3,4))
val set2 = hashTableSet(arrayOf(3,6,7))

class HW08Test {

    @Test fun insertTest() {
        set1.Insert(5)
        set2.Insert(10)
        org.junit.Assert.assertArrayEquals(arrayOf(3, 2, 4, 5), set1.toArrayList().toTypedArray().reversedArray())
        org.junit.Assert.assertArrayEquals(arrayOf(3, 6, 7, 10), set2.toArrayList().toTypedArray())
    }

    val set3 = AVLTreeSet(arrayOf(2, 3, 4))
    val set4 = hashTableSet(arrayOf(3, 6, 7))

    @Test fun deleteTest() {
        set3.Delete(3)
        set4.Delete(7)
        org.junit.Assert.assertArrayEquals(arrayOf(4, 2), set3.toArrayList().toTypedArray().reversedArray())
        org.junit.Assert.assertArrayEquals(arrayOf(3, 6), set4.toArrayList().toTypedArray())
    }

    @Test fun searchTest() {
        org.junit.Assert.assertEquals(true, set1.Search(2))
        org.junit.Assert.assertEquals(false, set2.Search(10))
    }

    val set5 = AVLTreeSet(arrayOf(2, 3, 7, 5, 9))
    val set6 = hashTableSet(arrayOf(3, 5, 14, 7, 17))

    @Test fun unionTest1() {
        val res = set5.Union(set6)
        org.junit.Assert.assertArrayEquals(arrayOf(17, 14, 7, 9, 3, 2, 5), res.toArrayList().toTypedArray())

    }

    @Test fun intersectTest1() {
        val res = set5.Intersect(set6)
        org.junit.Assert.assertArrayEquals(arrayOf(7, 3, 5), res.toArrayList().toTypedArray())
    }

    val set7 = AVLTreeSet(arrayOf(2, 3, 7, 5, 9))
    val set8 = hashTableSet(arrayOf())

    @Test fun unionTest2() {
        val res = set7.Union(set8)
        org.junit.Assert.assertArrayEquals(arrayOf(7, 9, 3, 2, 5), res.toArrayList().toTypedArray())
    }

    @Test fun intersectTest2() {
        val res = set7.Intersect(set8)
        org.junit.Assert.assertArrayEquals(arrayOf(), res.toArrayList().toTypedArray())
    }

    @Test fun iteratorTest1()
    {
        val treeSet = AVLTreeSet(arrayOf(1,2,3,4,9))
        val temp = ArrayList<Int>()
        val res  = arrayOf(1,2,3,4,9)
        for(item in treeSet) temp.add(item)
        org.junit.Assert.assertArrayEquals(res, temp.toArray())
    }

    @Test fun iteratorTest2()
    {
        val treeSet = hashTableSet(arrayOf(1,2,3,4,9))
        val temp = ArrayList<Int>()
        val res  = arrayOf(1,2,3,4,9)
        for(item in treeSet) temp.add(item)
        org.junit.Assert.assertArrayEquals(res, temp.toArray())
    }
}