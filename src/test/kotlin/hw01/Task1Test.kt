package hw01

import org.junit.Assert
import org.junit.Test


public class Task1Test {
    @Test fun general() {
        var arr = Array(11, {i -> 11 - i})
        arr.heapsort()
        var a = Array(11, {i -> i + 1})
        Assert.assertArrayEquals(arr, a)
    }

    @Test fun oneelm() {
        var arr = Array(1, {12})
        arr.heapsort()
        var a = Array(1, {12})
        Assert.assertArrayEquals(arr, a)
    }

    @Test fun zeroelm() {
        var arr = Array(0, {2})
        arr.heapsort()
        var a = Array(0, {4})
        Assert.assertArrayEquals(arr, a)
    }
}