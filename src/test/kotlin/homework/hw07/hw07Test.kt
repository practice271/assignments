/*
 * Homework 7 (27.10.2015)
 * Tests for realisations of ordered list
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw07

import org.junit.Test
import kotlin.test.assertEquals
import org.junit.Assert.assertArrayEquals

public class hw07Test() {

    private class test(private val list: IOrderedList<Int>) {

        init {
            isEmptyTest01()
            isEmptyTest02()
            lengthTest01()
            pushTest01()
            pushTest02()
            takeTest01()
            removeTest01()
            popTest01()
            concatTest01()
            concatTest02()
            equalsTest01()
            equalsTest02()
            equalsTest03()
            equalsTest04()
        }

        /** Tests for isEmpty(). */
        private fun isEmptyTest01() {
            assertEquals(true, list.isEmpty)
        }

        private fun isEmptyTest02() {
            list.push(1)
            assertEquals(false, list.isEmpty)
        }

        /** Test for length(). */
        private fun lengthTest01() {
            list.push(4)
            assertEquals(2, list.length())
        }

        /** Tests for push(). */
        private fun pushTest01() {
            list.push(2)
            list.push(3)
            assertArrayEquals(arrayOf(4, 3, 2, 1), Array(4, { i -> list.take(i) }))
        }

        private fun pushTest02() {
            for (i in 0 .. 7) list.push(0)
            val result = arrayOf(4, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0)
            assertArrayEquals(result, Array(12, { i -> list.take(i) }))
        }

        /** Test for take(). */
        private fun takeTest01() {
            assertEquals(4, list.take(0))
        }

        /** Test for remove(). */
        private fun removeTest01() {
            for (i in 4 .. 10) list.remove(5)
            assertArrayEquals(arrayOf(4, 3, 2, 1, 0), Array(5, { i -> list.take(i) }))
        }

        /** Test for pop(). */
        private fun popTest01() {
            assertEquals(1, list.pop(3))
            assertArrayEquals(arrayOf(4, 3, 2, 0), Array(4, { i -> list.take(i) }))
        }

        /** Tests for concat(). */
        private fun concatTest01() {
            val newArray = ArrayList<Int>()
            for (i in 6 .. 7) newArray.push(i)
            list.concat(newArray)
            assertArrayEquals(arrayOf(7, 6, 4, 3, 2, 0), Array(6, { i -> list.take(i) }))
        }

        private fun concatTest02() {
            val newArray = ArrayList<Int>()
            for (i in 7 .. 11) newArray.push(i)
            list.concat(newArray)
            val result = arrayOf(11, 10, 9, 8, 7, 7, 6, 4, 3, 2, 0)
            assertArrayEquals(result, Array(11, { i -> list.take(i) }))
        }

        /** Tests for equals(). */
        private fun createList(lst: IOrderedList<Int>) {
            for (i in 7 .. 11) lst.push(i)
            for (i in 2 .. 7) lst.push(i)
            lst.push(0)
            lst.remove(7)
        }

        private fun equalsTest01() {
            val lst = ArrayList<Int>()
            createList(lst);
            assertEquals(true, list.equals(lst))
        }

        private fun equalsTest02() {
            val lst = ADTList<Int>()
            createList(lst);
            assertEquals(true, list.equals(lst))
        }

        private fun equalsTest03() {
            val lst = ADTList<Int>()
            createList(lst)
            lst.push(3);
            lst.remove(2);
            assertEquals(false, list.equals(lst))
        }

        private fun equalsTest04() {
            assertEquals(false, list.equals(1))
        }
    }

    @Test fun testArrayList() {
        test(ArrayList<Int>())
    }

    @Test fun testADTList() {
        test(ADTList<Int>())
    }

    @Test fun testKotlinArrayList() {
        test(KotlinArrayList<Int>())
    }

    @Test fun testKotlinADTList() {
        test(KotlinADTList<Int>())
    }
}