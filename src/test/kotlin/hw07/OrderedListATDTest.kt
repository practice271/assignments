package hw07

import org.junit.Assert
import org.junit.Test

class OrderedListATDKotlinTest {

    @Test
    fun testGetValFromEmpty() {
        val a = OrderedListATDKotlin<Int>(null, true)
        Assert.assertNull(a.getVal(0))
        Assert.assertNull(a.getVal(12))
    }

    @Test
    fun testGetValFromNotEmptyRigthIndices() {
        val b = arrayOf(1, 2, 4)
        val a = OrderedListATDKotlin(b, true)
        Assert.assertEquals(1, (a.getVal(0) as Int).toLong())
        Assert.assertEquals(4, (a.getVal(2) as Int).toLong())
    }

    @Test
    fun testGetValFromNotEmptyWrongIndices() {
        val b = arrayOf(1, 2, 4)
        val a = OrderedListATDKotlin(b, true)
        Assert.assertNull(a.getVal(-1))
        Assert.assertNull(a.getVal(3))
    }

    @Test
    fun testAddValToEmpty() {
        val a = OrderedListATDKotlin<Int>(null, true)
        a.addVal(1)
        Assert.assertEquals((a.getVal(0) as Int).toLong(), 1)
        Assert.assertNull(a.getVal(1))
    }

    @Test
    fun testAddValToNotEmpty() {
        val b = arrayOf(1, 2, 4)
        val res = arrayOf(1, 2, 3, 4)
        val a = OrderedListATDKotlin(b, true)
        a.addVal(3)
        val resList = OrderedListATDKotlin(res, true)
        Assert.assertEquals(a, resList)
    }

    @Test
    fun testAddValToFullArray() {
        val b = arrayOf(1, 4)
        val res = arrayOf(-1, 1, 3, 4, 6)
        val a = OrderedListATDKotlin(b, true)
        a.addVal(3)
        a.addVal(6)
        //actual testing is in the line below.
        a.addVal(-1)
        val resList = OrderedListATDKotlin(res, true)
        Assert.assertEquals(a, resList)
    }

    @Test
    fun testDelValFromEmpty() {
        //we basically test that we don't crash with some exception
        val a = OrderedListATDKotlin<Int>(null, true)
        a.delVal(0)
        a.delVal(-1)
        a.delVal(4)
    }

    @Test
    fun testDelValFromNotEmptyRightIndices() {
        val b = arrayOf(1, 2, 4)
        val res = arrayOf(1)
        val a = OrderedListATDKotlin(b, true)
        val resList = OrderedListATDKotlin(res, true)
        a.delVal(1)
        a.delVal(1)
        Assert.assertTrue(a == resList)
    }

    @Test
    fun testDelValFromNotEmptyWrongIndices() {
        val b = arrayOf(1, 2, 4)
        val res = arrayOf(1, 2, 4)
        val a = OrderedListATDKotlin(b, true)
        val resList = OrderedListATDKotlin(res, true)
        a.delVal(-1)
        a.delVal(4)
        a.delVal(17)
        Assert.assertTrue(a == resList)
    }

    @Test
    fun testEquals1() {
        val b = arrayOf(1, 2, 4)
        val a = OrderedListATDKotlin(b, true)
        val c = OrderedListATDKotlin(b, true)
        Assert.assertTrue(a == c)
        a.addVal(12)
        Assert.assertFalse(a == c)
        c.addVal(12)
        Assert.assertTrue(a == c)
    }

    @Test
    fun testEqualsSameValsDifferentOrders() {
        val a = arrayOf(1, 2, 4)
        val b = arrayOf(4, 2, 1)
        val c = OrderedListATDKotlin(a, true)
        val descending = OrderedListATDKotlin(b, false)
        val ascending = OrderedListATDKotlin(b, true)
        Assert.assertFalse(c == descending)
        Assert.assertTrue(c == ascending)
    }

    @Test
    fun testEqualsDifferentVals() {
        val a = arrayOf(16, 2, -18)
        val b = arrayOf(4, -1, 1)
        val c = OrderedListATDKotlin(a, true)
        val descending = OrderedListATDKotlin(b, false)
        val ascending = OrderedListATDKotlin(b, true)
        Assert.assertFalse(c == descending)
        Assert.assertFalse(c == ascending)
    }

    @Test
    fun testCompareSameSizeNotEqual() {
        val a = arrayOf(-1, 1, 2)
        val b = arrayOf(-1, 1, 4)
        val aList = OrderedListATDKotlin(a, true)
        val bList = OrderedListATDKotlin(b, true)
        Assert.assertEquals(-1, aList.compareTo(bList).toLong())
    }

    @Test
    fun testCompareSameSizeEqual() {
        val a = arrayOf(-1, 1, 2)
        val b = arrayOf(-1, 1, 2)
        val aList = OrderedListATDKotlin(a, true)
        val bList = OrderedListATDKotlin(b, true)
        Assert.assertEquals(0, aList.compareTo(bList).toLong())
    }

    @Test
    fun testCompareDiffSize() {
        val a = arrayOf(-1, 1, 2)
        val b = arrayOf(-1, 1)
        val aList = OrderedListATDKotlin(a, true)
        val bList = OrderedListATDKotlin(b, true)
        Assert.assertEquals(1, aList.compareTo(bList).toLong())
    }
}
