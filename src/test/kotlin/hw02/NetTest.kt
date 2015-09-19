package hw02

import org.junit.Test
import kotlin.test.assertEquals


public class NetTest {

    private fun nospread(stnum: Int): String {
        val nw = Network(listOf(Comp("Shindows", false), Comp("Vedroid", false),
                         Comp("OS Pex", true), Comp("Linuh", false)),
                         listOf(listOf(1), listOf(0, 2), listOf(1, 3), listOf(2)),
                         Rand(1.0))
        for(i in 1..stnum) nw.NextStep()
        val ims = nw.Makers()
        return ("S"+ims[0]+"-- V"+ims[1]+"-- P"+ims[2]+"-- L"+ims[3])
    }
    @Test fun nsstep1() = assertEquals("S -- V -- P!-- L ", nospread(1))
    @Test fun nsstep100() = assertEquals("S -- V -- P!-- L ", nospread(100))


    private fun spreadguaranteed(stnum: Int): String {
        var net = Network(listOf(Comp("Linuh", false), Comp("Shindows", false),
                          Comp("Vedroid", false), Comp("OS Pex", true),
                          Comp("Linuh", false)), listOf(listOf(1), listOf(0, 2),
                          listOf(1, 3), listOf(2, 4), listOf(3)),
                          Rand(0.0))
        for(i in 1..stnum) net.NextStep()
        val ims = net.Makers()
        return ("L"+ims[0]+"-- S"+ims[1]+"-- V"+ims[2]+"-- P"+ims[3]+"-- L"+ims[4])
    }
    @Test fun gstep1() = assertEquals("L -- S -- V!-- P!-- L!", spreadguaranteed(1))
    @Test fun gstep2() = assertEquals("L -- S!-- V!-- P!-- L!", spreadguaranteed(2))
    @Test fun gstep3() = assertEquals("L!-- S!-- V!-- P!-- L!", spreadguaranteed(3))


    private fun noconnect(stnum: Int): String {
        val net = Network(listOf(Comp("Linuh", true),   Comp("Shindows", false),
                          Comp("Vedroid", false), Comp("OS Pex", true),
                          Comp("Linuh", false)), listOf(listOf(), listOf(), listOf(), listOf(), listOf()), Rand(0.0))
        for(i in 1..stnum) net.NextStep()
        val ims = net.Makers()
        return ("L"+ims[0]+"   S"+ims[1]+"   V"+ims[2]+"   P"+ims[3]+"   L"+ims[4])
    }
    @Test fun ncstep1() = assertEquals("L!   S    V    P!   L ", noconnect(1))
    @Test fun ncstep100() = assertEquals("L!   S    V    P!   L ", noconnect(100))
}