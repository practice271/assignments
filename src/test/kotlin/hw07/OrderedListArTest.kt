package hw07

import org.junit.Assert
import org.junit.Test

class OrderedListArKotlinTest {

    @Test
    fun testGetValFromEmpty() {
        val a = OrderedListArKotlin<Int>(null, true)
        Assert.assertNull(a.getVal(0))
        Assert.assertNull(a.getVal(12))
    }

    @Test
    fun testGetValFromNotEmptyRigthIndices() {
        val b = arrayOf(1, 2, 4)
        val a = OrderedListArKotlin(b, true)
        Assert.assertEquals(1, (a.getVal(0) as Int).toLong())
        Assert.assertEquals(4, (a.getVal(2) as Int).toLong())
    }

    @Test
    fun testGetValFromNotEmptyWrongIndices() {
        val b = arrayOf(1, 2, 4)
        val a = OrderedListArKotlin(b, true)
        Assert.assertNull(a.getVal(-1))
        Assert.assertNull(a.getVal(3))
    }

    @Test
    fun testAddValToEmpty() {
        val a = OrderedListArKotlin<Int>(null, true)
        a.addVal(1)
        val size = a.minArraySize
        val res = arrayOf(1)
        val resList = OrderedListArKotlin(res, true)
        Assert.assertTrue(a == resList)
    }

    @Test
    fun testAddValToNotEmpty() {
        val b = arrayOf(1, 2, 4)
        val res = arrayOf(1, 2, 3, 4)
        val a = OrderedListArKotlin(b, true)
        val resList = OrderedListArKotlin(res, true)
        a.addVal(3)
        Assert.assertTrue(a == resList)
    }

    @Test
    fun testAddValToFullArray() {
        val b = arrayOf(1, 4)
        val res = arrayOf(-1, 1, 3, 4, 6)
        val a = OrderedListArKotlin(b, true)
        val resList = OrderedListArKotlin(res, true)
        a.addVal(3)
        a.addVal(6)
        //actual testing is in the line below.
        a.addVal(-1)
        Assert.assertTrue(a == resList)
    }

    @Test
    fun testDelValFromEmpty() {
        //we basically test that we don't crash with some exception
        val a = OrderedListArKotlin<Int>(null, true)
        a.delVal(0)
        a.delVal(-1)
        a.delVal(4)
    }

    @Test
    fun testDelValFromNotEmptyRightIndices() {
        val b = arrayOf(1, 2, 4)
        val res = arrayOf(1)
        val a = OrderedListArKotlin(b, true)
        val resList = OrderedListArKotlin(res, true)
        a.delVal(1)
        a.delVal(1)
        Assert.assertTrue(a == resList)
    }

    @Test
    fun testDelValFromNotEmptyWrongIndices() {
        val b = arrayOf(1, 2, 4)
        val res = arrayOf(1, 2, 4)
        val a = OrderedListArKotlin(b, true)
        val resList = OrderedListArKotlin(res, true)
        a.delVal(-1)
        a.delVal(4)
        a.delVal(17)
        Assert.assertTrue(a == resList)
    }
    
    @Test
    fun testHashCodeForEqual() {
        val in1 = arrayOf(1, 2, 4)
        val in2 = arrayOf(1, 2, 4)
        val a = OrderedListAr(in1, true)
        val b = OrderedListAr(in2, true)
        Assert.assertEquals(a.hashCode(), b.hashCode())
    }

    @Test
    fun testHashCodeForInequal() {
        val in1 = arrayOf(1, 2, 4)
        val in2 = arrayOf(4, 3, 1)
        val a = OrderedListAr(in1, true)
        val b = OrderedListAr(in2, true)
        Assert.assertNotEquals(a.hashCode(), b.hashCode())
    }

    @Test
    fun testHashCodeDifferentImplementations() {
        val in1 = arrayOf(1, 2, 4)
        val in2 = arrayOf(1, 2, 4)
        val a = OrderedListAr(in1, true)
        val b = OrderedListATD(in2, true)
        Assert.assertEquals(a.hashCode(), b.hashCode())
    }

    @Test
    fun testEquals1() {
        val b = arrayOf(1, 2, 4)
        val a = OrderedListArKotlin(b, true)
        val c = OrderedListArKotlin(b, true)
        Assert.assertTrue(a == c)
        a.addVal(12)
        Assert.assertFalse(a == c)
        c.addVal(12)
        Assert.assertTrue(a == c)
    }

    @Test
    fun testEqualsDifferentImplementations() {
        val b = arrayOf(1, 2, 4)
        val a = OrderedListArKotlin(b, true)
        val c = OrderedListATDKotlin(b, true)
        //Kotlin and Idea doesn't allow to do a==c, though declaration of == IS equals()
        Assert.assertTrue(a.equals(c))
        a.addVal(12)
        Assert.assertFalse(a.equals(c))
        c.addVal(12)
        Assert.assertTrue(a.equals(c))
    }

    @Test
    fun testEqualsSameValsDifferentOrders() {
        val a = arrayOf(1, 2, 4)
        val b = arrayOf(4, 2, 1)
        val c = OrderedListArKotlin(a, true)
        val descending = OrderedListArKotlin(b, false)
        val ascending = OrderedListArKotlin(b, true)
        Assert.assertFalse(c.equals(descending))
        Assert.assertTrue(c.equals(ascending))
    }

    @Test
    fun testEqualsDifferentVals() {
        val a = arrayOf(16, 2, -18)
        val b = arrayOf(4, -1, 1)
        val c = OrderedListArKotlin(a, true)
        val descending = OrderedListArKotlin(b, false)
        val ascending = OrderedListArKotlin(b, true)
        Assert.assertFalse(c == descending)
        Assert.assertFalse(c == ascending)
    }

    @Test
    fun testCompareSameSizeNotEqual() {
        val a = arrayOf(-1, 1, 2)
        val b = arrayOf(-1, 1, 4)
        val aList = OrderedListArKotlin(a, true)
        val bList = OrderedListArKotlin(b, true)
        Assert.assertEquals(-1, aList.compareTo(bList))
    }

    @Test
    fun testCompareSameSizeEqual() {
        val a = arrayOf(-1, 1, 2)
        val b = arrayOf(-1, 1, 2)
        val aList = OrderedListArKotlin(a, true)
        val bList = OrderedListArKotlin(b, true)
        Assert.assertEquals(0, aList.compareTo(bList))
    }

    @Test
    fun testCompareSameSizeEqualDifferentImplementations() {
        val a = arrayOf(-1, 1, 2)
        val b = arrayOf(-1, 1, 2)
        val aList = OrderedListATD(a, true)
        val bList = OrderedListAr(b, true)
        Assert.assertEquals(0, bList.compareTo(aList))
    }

    @Test
    fun testCompareDiffSize() {
        val a = arrayOf(-1, 1, 2)
        val b = arrayOf(-1, 1)
        val aList = OrderedListArKotlin(a, true)
        val bList = OrderedListArKotlin(b, true)
        Assert.assertEquals(1, aList.compareTo(bList))
    }
}
