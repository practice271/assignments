package hw02

import org.junit.Test
import kotlin.test.assertEquals

public class HW02Test {
    Test fun probabilityWindows() {
        assertEquals(0.6, probability("Windows"))
    }

    Test fun probabilityLinux() {
        assertEquals(0.3, probability("Linux"))
    }

    Test fun probabilityOSX() {
        assertEquals(0.1, probability("OS X"))
    }

    val c1 = Computer("Windows", true, 8)
    Test fun statusOfInfected() {
        assertEquals(c1.isInfected(), true)
    }

    Test fun signOfInfected() {
        assertEquals(c1.state(), "+")
    }

    val c2 = Computer("Linux", false, 8)
    Test fun statusOfUninfected() {
        assertEquals(c2.isInfected(), false)
    }

    Test fun signOfBecomingInfected() {
        c2.infection(0.2)
        assertEquals(c2.state(), "+")
    }

    val c3 = Computer("Windows", false, 8)
    Test fun signOfUninfected() {
        c3.infection(0.7)
        assertEquals(c3.state(), "-")
    }

    val OS = arrayOf("OS X", "OS X", "OS X", "Linux", "Linux", "Windows", "Windows")
    val infect = arrayOf(false, false, false, true, false, false, true)
    val array1 = arrayOf(
            arrayOf(false, true, false, false, false, false, true),
            arrayOf(true, false, false, false, true, false, false),
            arrayOf(false, false, false, false, true, true, true),
            arrayOf(false, false, false, false, true, true, false),
            arrayOf(false, true, true, true, false, false, false),
            arrayOf(false, false, true, true, false, false, true),
            arrayOf(true, false, true, false, false, true, false))
    val myNetwork1 = LocalNetwork(OS, array1, infect)

    Test fun locNetIn5Steps1() {
        for (j in 0..4) { myNetwork1.letsInfect(1.0) }
        assertEquals(myNetwork1.smallStatus(), "---+--+")
    }

    Test fun locNetIn5Steps2() {
        for (i in 0..4) { myNetwork1.letsInfect(0.0) }
        assertEquals(myNetwork1.smallStatus(), "+++++++")
    }

    val line = arrayOf(false, false, false, false, false, false, false)
    val array2 = arrayOf(line, line, line, line, line, line, line)
    val myNetwork2 = LocalNetwork(OS, array2, infect)

    Test fun notConnectedIn5Steps1() {
        for (j in 0..4) { myNetwork2.letsInfect(1.0) }
        assertEquals(myNetwork2.smallStatus(), "---+--+")
    }

    Test fun notConnectedIn5Steps2() {
        for (j in 0..4) { myNetwork2.letsInfect(0.0) }
        assertEquals(myNetwork2.smallStatus(), "---+--+")
    }

    Test fun notConnectedIn5Steps3() {
        for (j in 0..4) { myNetwork2.letsInfect(0.5) }
        assertEquals(myNetwork2.smallStatus(), "---+--+")
    }

    val array3 = arrayOf(
            arrayOf(false, true, true, false, false, false, false),
            arrayOf(true, false, true, false, false, false, false),
            arrayOf(true, true, false, false, false, false, false),
            arrayOf(false, false, false, false, true, false, false),
            arrayOf(false, false, false, true, false, false, false),
            arrayOf(false, false, false, false, false, false, true),
            arrayOf(false, false, false, false, false, true, false))
    val myNetwork3 = LocalNetwork(OS, array3, infect)

    Test fun similarConnected1() {
        for (j in 0..4) { myNetwork3.letsInfect(1.0) }
        assertEquals(myNetwork3.smallStatus(), "---+--+")
    }

    Test fun similarConnected2() {
        for (j in 0..4) { myNetwork3.letsInfect(0.5) }
        assertEquals(myNetwork3.smallStatus(), "---+-++")
    }

    Test fun similarConnected3() {
        for (j in 0..4) { myNetwork3.letsInfect(0.2) }
        assertEquals(myNetwork3.smallStatus(), "---++++")
    }

    Test fun similarConnected4() {
        for (j in 0..4) { myNetwork3.letsInfect(0.0) }
        assertEquals(myNetwork3.smallStatus(), "---++++")
    }
}