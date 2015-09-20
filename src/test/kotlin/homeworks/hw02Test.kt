package homeworks

import org.junit.Test
import kotlin.test.assertEquals

public class hw02Test {
    fun createNetWork(linux: Double, windowsXP: Double, windows8: Double, embox: Double): List<List<Computer>> {
        var network = listOf(
                listOf(Computer(0, windowsXP), Computer(1, windows8), Computer(4, linux)),
                listOf(Computer(1, windows8), Computer(0, windowsXP), Computer(2, embox), Computer(3, embox)),
                listOf(Computer(2, embox), Computer(1, windows8), Computer(3, embox), Computer(4, linux), Computer(5, windows8), Computer(6, linux)),
                listOf(Computer(3, embox), Computer(1, windows8), Computer(2, embox), Computer(4, linux), Computer(7, embox)),
                listOf(Computer(4, linux), Computer(0, windowsXP), Computer(2, embox), Computer(3, embox)),
                listOf(Computer(5, windows8), Computer(2, embox)),
                listOf(Computer(6, linux), Computer(2, embox), Computer(7, embox)),
                listOf(Computer(7, embox), Computer(3, embox), Computer(6, linux), Computer(8, linux)),
                listOf(Computer(8, linux), Computer(7, embox)),
                listOf(Computer(9, linux)),
                listOf(Computer(10, embox))
        )
        return network
    }

    @Test
    fun Test1() {
        var network = createNetWork(0.2, 0.3, 0.4, 0.1)
        var result = infection(network)
        var count = 0
        for (i in network) {
            if (i.first().isInfected == true) {
                count++
            }
        }
        assertEquals(9, count)
        assertEquals("Success!", result)
    }

    @Test
    fun Test2() {
        var network = createNetWork(0.2, 0.3, 0.4, 0.0)
        var result = infection(network)
        var count = 0
        for (i in network) {
            if (i.first().isInfected == true) {
                count++
            }
        }
        assertEquals("Fail. Error Probability", result)
    }

    @Test
    fun Test3() {
        var network = createNetWork(1.0, 1.0, 1.1, 1.0)
        var result = infection(network)
        var count = 0
        for (i in network) {
            if (i.first().isInfected == true) {
                count++
            }
        }
        assertEquals("Fail. Error Probability", result)
    }

    @Test
    fun Test4() {
        var network = createNetWork(1.0, 1.0, 1.0, 1.0)
        var result = infection(network)
        var count = 0
        for (i in network) {
            if (i.first().isInfected == true) {
                count++
            }
        }
        assertEquals(9, count)
        assertEquals("Success!", result)
    }

}