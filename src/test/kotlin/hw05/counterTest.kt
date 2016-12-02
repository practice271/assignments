package hw05

import org.junit.Test
import kotlin.test.assertEquals

public class counterTest {
    @Test fun wrongCounterTest() {
        var sum = 0
        for (i in 0..4)
            sum += parallelCounterWrong(10000)
        assert(sum < 5 * 10000)
    }
    @Test fun rightCounterTest() {
        var sum = 0
        for (i in 0..4)
            sum += parallelCounter(10000)
        assertEquals(sum, 5 * 10000)
    }
}