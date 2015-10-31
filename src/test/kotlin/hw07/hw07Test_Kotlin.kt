package hw07

import org.junit.Test

import org.junit.Assert.assertEquals

class hw07Test_Kotlin {
    private val arr = ArrayOrdList(1, "zebra")
    private val arr1 = ArrayOrdList(1, "zebra")

    @Test
    fun get() {
        arr.add("lion")
        arr.add("monkey")
        arr.add("panda")
        assertEquals("lion", arr.get(1))
    }

    @Test
    fun size() {
        arr.add("lion")
        arr.add("monkey")
        arr.add("panda")
        assertEquals(4, arr.size().toLong())
    }

    @Test
    fun removeBegin() {
        arr.add("cat")
        arr.print()
        arr.remove(0)
        println("remove from begin")
        arr.print()
    }

    @Test
    fun removeEnd() {
        arr.add("lion")
        arr.add("monkey")
        arr.add("panda")
        arr.print()
        arr.remove(3)
        println("remove from end")
        arr.print()
    }

    @Test
    fun compareTo1() {
        assertEquals(0, arr.compareTo(arr1).toLong())
    }

    @Test
    fun compareTo2() {
        arr1.add("cat")
        arr1.add("elephant")
        arr1.add("cow")
        arr1.add("horse")
        arr1.add("lion")
        arr1.add("monkey")
        assertEquals(-1, arr.compareTo(arr1).toLong())
    }

    @Test
    fun equals1() {
        assertEquals(true, arr.equals(arr1))
    }

    @Test
    fun equals2() {
        arr1.add("elephant")
        assertEquals(false, arr.equals(arr1))
    }

    @Test
    fun hashcode() {
        arr.add("monkey")
        arr.add("panda")
        arr.add("mockingbird")
        assertEquals(1170318980, arr.hashcode().toLong())
    }
}
