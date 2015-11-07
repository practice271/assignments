package hw07

import org.junit.Test
import kotlin.test.assertEquals
import org.junit.Assert.assertArrayEquals

public class listTests {

    @Test fun pushTest0()
    {
        val list = MyList<Int>()

        list.push(4)
        list.push(3)
        list.push(9)
        list.push(1)

        assertArrayEquals(arrayOf(9, 4, 3, 1), Array(4, { i -> list.getAt(i) }))
    }

    @Test fun pushTest1()
    {
        val list = MyList<Int>()

        list.push(1)
        list.push(2)
        list.push(3)
        list.push(4)

        assertArrayEquals(arrayOf(4, 3, 2, 1), Array(4, { i -> list.getAt(i) }))
    }

    @Test fun removeAtTest0()
    {
        val list = MyList<Int>()

        list.push(4)
        list.push(3)
        list.push(9)
        list.push(1)
        list.removeAt(2)

        assertArrayEquals(arrayOf(9, 4, 1), Array(3, { i -> list.getAt(i) }))
    }

    @Test fun removeAtTest1()
    {
        val list = MyList<Int>()

        list.push(4)
        list.push(3)
        list.push(9)
        list.push(1)
        list.removeAt(5)

        assertArrayEquals(arrayOf(9, 4, 3, 1), Array(4, { i -> list.getAt(i) }))
    }

    @Test fun removeTest0()
    {
        val list = MyList<Int>()

        list.push(4)
        list.push(3)
        list.push(9)
        list.push(1)

        list.remove(3)

        assertArrayEquals(arrayOf(9, 4, 1), Array(3, { i -> list.getAt(i) }))
    }

    @Test fun removeTest1()
    {
        val list = MyList<Int>()

        list.push(4)
        list.push(3)
        list.push(9)
        list.push(1)

        list.remove(2)

        assertArrayEquals(arrayOf(9, 4, 3, 1), Array(4, { i -> list.getAt(i) }))
    }

    @Test fun equalsTest0()
    {
        val list1 = MyList<Int>()

        list1.push(3)
        list1.push(4)

        val list2 = MyList<Int>()

        list2.push(4)
        list2.push(3)


        assertEquals(list1, list2)
    }
}
