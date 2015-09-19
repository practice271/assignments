/*
Homework 2 (15.09.2015)
Tests for task 2

Author: Mikhail Kita, group 271
*/

package homework.hw02

import org.junit.Test
import kotlin.test.assertEquals

public class hw02Test {

    Test fun networkTest01() {
        val OSList = arrayOf("Linux", "Windows", "FreeBSD", "OS X", "Linux", "Windows",
                        "Linux", "Windows", "OS X", "FreeBSD")

        var labels = arrayOf(false, true, false, false, false, false, false, true, false, false)

        val aList =
            arrayOf(listOf(1, 4), listOf(0, 2), listOf(1, 3, 4), listOf(2), listOf(0, 2, 5), listOf(4),
                listOf(7, 8), listOf(6, 9), listOf(6, 9), listOf(7, 8))

        val graph   = ComputerGraph (OSList, labels, aList)
        val network = LocalNetwork (graph)

        for (i in 0 .. 3) {
            network.start(-1.0) //probability of infection = 1
        }
        assertEquals(10, network.infectedNumber())
    }

    Test fun networkTest02() {
        val OSList = arrayOf("Linux", "Windows", "FreeBSD", "OS X", "Linux", "Windows",
                "Linux", "Windows", "OS X", "FreeBSD")

        var labels = arrayOf(false, true, false, false, false, false, false, true, false, false)

        val aList =
                arrayOf(listOf(1, 4), listOf(0, 2), listOf(1, 3, 4), listOf(2), listOf(0, 2, 5), listOf(4),
                        listOf(7, 8), listOf(6, 9), listOf(6, 9), listOf(7, 8))

        val graph = ComputerGraph (OSList, labels, aList)
        val network = LocalNetwork (graph)

        for (i in 0..100000) {
            network.start(1.0) //probability of infection = 0
        }
        assertEquals(2, network.infectedNumber())
    }

    Test fun networkTest03() {
        val OSList = arrayOf("Linux", "Windows", "FreeBSD", "OS X", "Linux", "Windows",
                "Linux", "Windows", "OS X", "FreeBSD")

        var labels = arrayOf(true, false, false, false, false, false, false, false, false, false)

        val aList =
                arrayOf(listOf(1), listOf(2), listOf(3), listOf(4), listOf(5), listOf(6),
                        listOf(7), listOf(8), listOf(9), listOf())

        val graph = ComputerGraph (OSList, labels, aList)
        val network = LocalNetwork (graph)

        for (i in 0 .. 9) {
            network.start(-1.0) //probability of infection = 1
        }
        assertEquals(10, network.infectedNumber())
    }

    Test fun networkTest04() {
        val OSList = arrayOf("Linux", "Windows", "FreeBSD", "OS X", "Linux", "Windows",
                "Linux", "Windows", "OS X", "FreeBSD")

        var labels = arrayOf(true, false, false, false, false, false, false, false, false, false)

        val aList =
                arrayOf(listOf(1), listOf(2), listOf(3), listOf(4), listOf(5), listOf(6),
                        listOf(7), listOf(8), listOf(9), listOf())

        val graph = ComputerGraph (OSList, labels, aList)
        val network = LocalNetwork (graph)

        for (i in 0 .. 100000) {
            network.start(1.0) //probability of infection = 1
        }
        assertEquals(1, network.infectedNumber())
    }
}