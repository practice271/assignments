package hw07

import org.junit.Test
import kotlin.test.assertEquals
import org.junit.Assert.assertArrayEquals

public class arrayTests {

    @Test fun pushTest0()
    {
        val arr = MyArray<Int>()

        arr.push(4)
        arr.push(3)
        arr.push(9)
        arr.push(1)

        assertArrayEquals(arrayOf(9, 4, 3, 1), Array(4, { i -> arr.getAt(i) }))
    }

    @Test fun pushTest1()
    {
        val arr = MyArray<Int>()

        arr.push(1)
        arr.push(2)
        arr.push(3)
        arr.push(4)

        assertArrayEquals(arrayOf(4, 3, 2, 1), Array(4, { i -> arr.getAt(i) }))
    }

    @Test fun removeAtTest0()
    {
        val arr = MyArray<Int>()

        arr.push(4)
        arr.push(3)
        arr.push(9)
        arr.push(1)
        arr.removeAt(2)

        assertArrayEquals(arrayOf(9, 4, 1), Array(3, { i -> arr.getAt(i) }))
    }

    @Test fun removeAtTest1()
    {
        val arr = MyArray<Int>()

        arr.push(4)
        arr.push(3)
        arr.push(9)
        arr.push(1)
        arr.removeAt(5)

        assertArrayEquals(arrayOf(9, 4, 3, 1), Array(4, { i -> arr.getAt(i) }))
    }

    @Test fun removeTest0()
    {
        val arr = MyArray<Int>()

        arr.push(4)
        arr.push(3)
        arr.push(9)
        arr.push(1)

        arr.remove(3)

        assertArrayEquals(arrayOf(9, 4, 1), Array(3, { i -> arr.getAt(i) }))
    }

    @Test fun removeTest1()
    {
        val arr = MyArray<Int>()

        arr.push(4)
        arr.push(3)
        arr.push(9)
        arr.push(1)

        arr.remove(2)

        assertArrayEquals(arrayOf(9, 4, 3, 1), Array(4, { i -> arr.getAt(i) }))
    }

    @Test fun equalsTest0()
    {
        val arr1 = MyArray<Int>()

        arr1.push(3)
        arr1.push(4)

        val arr2 = MyArray<Int>()

        arr2.push(4)
        arr2.push(3)


        assertEquals(arr1, arr2)
    }
}
