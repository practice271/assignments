/* Tests for net made by Guzel Garifullina
   Estimated time 1 hour
   real time      1  hour
*/
package hw02
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

public class hw02 {
    val comp = Computer("Linux")

    @Test fun ComputerOs(){
        assertEquals("Linux",comp.os)
    }

    @Test fun ComputerOsDefence1(){
        assertEquals(0.72, comp.osDefence)
    }

    @Test fun ComputerOsDefence2(){
        val comp2 = Computer("L")
        assertEquals(0.1, comp2.osDefence)
    }

    @Test fun ComputerNotInfected(){
        assertFalse(comp.infected)
    }

    @Test fun ComputerInfected(){
        comp.infected = true
        assertTrue(comp.infected)
    }

    val net = Array(6, { BooleanArray(6) })
    fun  ex ( i: Int, j : Int){
        net[i][j] = true
        net[j][i] = true
    }
    val  oSes = arrayOf("Android", "Windows", "Solaris", "Windows", "Linux", "Other")

    @Test fun Num() {
        ex (0,1)
        val lan = LAN (net, oSes)
        assertEquals(6, lan.num )
    }

    @Test fun IsWay1() {
        ex (0,1)
        val lan = LAN (net, oSes)
        assertTrue(lan.isWay( 0, 1) )
    }

    @Test fun IsWay2() {
        ex (0,1)
        val lan = LAN (net, oSes)
        assertFalse(lan.isWay( 2, 0) )
    }


    @Test fun nextNeighbours1() {
        ex (0,1)
        ex (0,3)
        ex (2,3)
        val lan = LAN (net, oSes)
        lan.firstInfected = 2
        lan.helthyNeigh(2)
        assertEquals(1, lan.nextVictimsNum)
    }

    @Test fun nextNeighbours2() {
        ex (0,1)
        ex (0,3)
        ex (2,3)
        val lan = LAN (net, oSes)
        lan.firstInfected = 3
        lan.helthyNeigh(3)
        assertEquals(2, lan.nextVictimsNum)
    }

    @Test fun nextNeighbours3() {
        ex (0,1)
        ex (0,3)
        ex (2,3)
        val lan = LAN (net, oSes)
        lan.firstInfected = 3
        lan.computers[0].infected = true
        lan.helthyNeigh(3)
        assertEquals(1, lan.nextVictimsNum)
    }

    @Test fun nextNeighbours4() {
        ex (0,1)
        ex (0,3)
        ex (2,3)
        val lan = LAN (net, oSes)
        lan.firstInfected = 0
        lan.computers[3].infected = true
        lan.helthyNeigh(0)
        assertEquals(1, lan.nextVictimsNum)
    }

    @Test fun nextNeighbours5() {
        ex (0,1)
        ex (0,3)
        ex (2,3)
        val lan = LAN (net, oSes)
        lan.firstInfected = 0
        lan.computers[3].infected = true
        lan.helthyNeigh(0)
        assertEquals(1, lan.nextVictims[0])
    }

    @Test fun initFirstInfected() {
        ex (0,1)
        ex (0,3)
        ex (2,3)
        val lan = LAN (net, oSes)
        lan.init()
        assertTrue(lan.computers[lan.firstInfected].infected)
    }
    @Test fun initOthersNotInfected() {
        ex (0,1)
        ex (0,3)
        ex (2,3)
        val lan = LAN (net, oSes)
        lan.firstInfected = 2
        lan.init()
        assertFalse(lan.computers[3].infected)
    }

    @Test fun initNextNeighbours1() {
        ex (0,1)
        ex (0,3)
        ex (2,3)
        val lan = LAN (net, oSes)
        lan.firstInfected = 3
        lan.computers[0].infected = true
        lan.init()
        assertEquals(1, lan.nextVictimsNum)
    }

    @Test fun initNextNeighbours2() {
        ex (0,1)
        ex (0,3)
        ex (2,3)
        val lan = LAN (net, oSes)
        lan.firstInfected = 1
        lan.computers[0].infected = true
        lan.init()
        assertEquals(0, lan.nextVictimsNum)
    }

    @Test fun initNextNeighbours3() {
        ex (0,1)
        ex (0,3)
        ex (2,3)
        val lan = LAN (net, oSes)
        lan.firstInfected = 3
        lan.init()
        assertEquals(2, lan.nextVictimsNum)
    }

    @Test fun attack1() {
        ex (0,1)
        ex (0,3)
        ex (2,3)
        val lan = LAN (net, oSes)
        lan.firstInfected = 3
        lan.init()
        lan.defaultAttack = 0.5
        lan.virusAttack()

        assertEquals(2, lan.nextVictimsNum)
    }

    @Test fun attack2() {
        ex (0,1)
        ex (0,3)
        ex (2,3)
        val lan = LAN (net, oSes)
        lan.firstInfected = 3
        lan.init()
        lan.defaultAttack = 0.5
        lan.virusAttack()

        assertTrue((lan.nextVictims[0] == 0) || (lan.nextVictims[0] == 2))
    }

    @Test fun attack3() {
        ex (0,1)
        ex (0,3)
        ex (2,3)
        val lan = LAN (net, oSes)
        lan.firstInfected = 3
        lan.init()
        lan.defaultAttack = 0.5
        lan.virusAttack()

        assertTrue((lan.nextVictims[1] == 0) || (lan.nextVictims[1] == 2))
    }

    @Test fun attack4() {
        ex (0,1)
        ex (0,3)
        ex (2,3)
        val lan = LAN (net, oSes)
        lan.firstInfected = 3
        lan.init()
        lan.defaultAttack = 0.7
        lan.virusAttack()

        assertEquals(1, lan.nextVictimsNum)
    }

    @Test fun attack5() {
        ex (0,1)
        ex (0,3)
        ex (2,3)
        val lan = LAN (net, oSes)
        lan.firstInfected = 3
        lan.init()
        lan.defaultAttack = 0.7
        lan.virusAttack()

        assertEquals(1, lan.nextVictims[0])
    }

    @Test fun attack6() {
        ex (0,1)
        ex (0,3)
        ex (2,3)
        ex (2, 4)
        ex (0, 5)
        val lan = LAN (net, oSes)
        lan.firstInfected = 3
        lan.init()
        lan.defaultAttack = 0.7
        lan.virusAttack()

        assertEquals(3, lan.nextVictimsNum)
    }

    @Test fun attack7() {
        ex (0,1)
        ex (0,3)
        ex (2,3)
        ex (2, 4)
        ex (0, 5)
        val lan = LAN (net, oSes)
        lan.firstInfected = 3
        lan.init()
        lan.defaultAttack = 0.67
        lan.virusAttack()

        assertEquals(2, lan.nextVictimsNum)
    }
}