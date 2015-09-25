package hw02

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Mikhail on 20.09.2015.
 */

public class hw02Test {
    val OSList = arrayOf("Linux", "Windows", "Android", "Mac OS", "Linux", "Windows",
            "Linux", "Windows", "Mac OS", "Android")

    val tags = arrayOf(false, true, false, false, false, false, false, true, false, false)

    val list = arrayOf(listOf(1, 4), listOf(0, 2), listOf(1, 3, 4), listOf(2), listOf(0, 2, 5),
            listOf(4), listOf(7, 8), listOf(6, 9), listOf(6, 9), listOf(7, 8))

    val graph   = GraphComputer (OSList, tags, list)
    val network = LAN(graph)

    Test fun lanTest01()
    {
        for(i in 0..100)
            network.start()
        assertEquals(10, network.infectedNumber())
    }
    Test fun lanTest02(){
        assertEquals(2, network.infectedNumber())
    }
}