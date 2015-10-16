package hw05

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

public class parallelIncrementionTest {
    @Test
    fun oneThreadParallelIncrement() {
        assertEquals(0, notSynchronizedParallelIncrement(0, 1))
        assertEquals(1, notSynchronizedParallelIncrement(1, 1))
        assertEquals(9999999, notSynchronizedParallelIncrement(9999999, 1))
    }

    @Test
    fun twoThreadsParallelIncrement() {
        assertEquals(0, notSynchronizedParallelIncrement(0, 2))
        assertEquals(1, notSynchronizedParallelIncrement(1, 2))
        var res = notSynchronizedParallelIncrement(1024, 2)
        assertTrue(res > 0 && res <= 1024)
        res = notSynchronizedParallelIncrement(9999999, 2)
        assertTrue(res > 0 && res <= 9999999)
    }

    @Test
    fun sixteenThreadsParallelIncrement() {
        assertEquals(0, notSynchronizedParallelIncrement(0, 16))
        assertEquals(1, notSynchronizedParallelIncrement(1, 16))
        var res = notSynchronizedParallelIncrement(256, 16)
        assertTrue(res > 0 && res <= 256)
        res = notSynchronizedParallelIncrement(9999999, 16)
        assertTrue(res > 0 && res <= 9999999)
    }
}