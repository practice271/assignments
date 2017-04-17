package hw05_Tests
import hw05.sort
import org.junit.Test

class mergeSort_Test {
    @Test fun mergeSortTest1() {
        var array: Array<Int>
        var resultArray: Array<Int>
            for (i in 1..10) {
                array = arrayOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 11, 12, 13, 14)
                resultArray = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14)
                sort(array, i)
                org.junit.Assert.assertArrayEquals(resultArray, array)
            }
    }

    @Test fun mergeSortTest2() {
        var array: Array<Int>
        var resultArray: Array<Int>
        for (i in 1..20) {
            array = arrayOf(7, 2, 4, 11, 60, 54, 23, 72, 42)
            resultArray = arrayOf(2, 4, 7, 11, 23, 42, 54, 60, 72)
            sort(array, i)
            org.junit.Assert.assertArrayEquals(resultArray, array)
        }
    }

    @Test fun mergeSortTest3() {
        var array: Array<Int>
        var resultArray: Array<Int>
        for (i in 1..30) {
            array = arrayOf(90, 23, 1, 3, 5, 6, 43, 26, 76, 7, 25, 17, 84, 100, 0)
            resultArray = arrayOf(0, 1, 3, 5, 6, 7, 17, 23, 25, 26, 43, 76, 84, 90, 100)
            sort(array, i)
            org.junit.Assert.assertArrayEquals(resultArray, array)
        }
    }

    @Test fun mergeSortTest4() {
        var array: Array<Int>
        var resultArray: Array<Int>
        for (i in 1..40) {
            array = arrayOf(10, 23, 1, 25, 65, 47, 89, 74, 3, 19, 0, 34, 16, 5)
            resultArray = arrayOf(0, 1, 3, 5, 10, 16, 19, 23, 25, 34, 47, 65,74, 89)
            sort(array, i)
            org.junit.Assert.assertArrayEquals(resultArray, array)
        }
    }
}