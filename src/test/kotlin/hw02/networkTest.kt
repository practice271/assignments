package hw02

import junit.framework.TestCase
import org.junit.Test
import org.junit.runners.Parameterized
import java.util.*
import kotlin.test.assertEquals
import org.junit.runners.BlockJUnit4ClassRunner
/**
 * Created by Alexander on 18.09.2015.
 */
public class networkTest {
   // @Parameterized.Parameters("0")//({ "0, 1, 1, listOf(0; 2; 2; 0; 0; 0; 2; 0)"})

    /*
        (0. Windows) --> (1. Linux) --> (2. OS X) --> (3. Linux )
         |                  |                       |
         |                  |                       |
        \/                 \/                      \/
        (6. Windows) --> (4. Unknown) <---(5. Windows)
           |
           |
          \/
        (7. OS X)
    */

    @Test fun Infection100start1move1() {
        bigNetTest(0, 1, 1, linkedListOf(0, 2, 2, 0, 0, 0, 2, 0))
    }
    @Test fun Infection100start1move3() {
        bigNetTest(0, 1, 3, linkedListOf(0, 2, 2, 2, 2, 2, 2, 2))
    }
    @Test fun Infection100start4move100() {
        bigNetTest(0, 4, 100, linkedListOf(0, 0, 0, 0, 2, 0, 0, 0))
    }
    @Test fun Infection0start1move3() {
        bigNetTest(1, 1, 3, linkedListOf(0, 2, 0, 0, 0, 0, 0, 0))
    }
    @Test fun Infection0start6move100() {
        bigNetTest(1, 6, 100, linkedListOf(0, 0, 0, 0, 0, 0, 2, 0))
    }
    private fun bigNetTest(randVal : Int, start : Int, moves : Int, res : LinkedList<Int>) {
        val vers = arrayOf(listOf(1), listOf(2, 6), listOf(3, 4),
                listOf(5), listOf(), listOf(4), listOf(4, 7), listOf())
        val names = arrayOf("Windows", "Linux", "OS X", "Linux", "Unknown", "Windows", "Windows", "OS X")
        val net = Net(vers, names)
        net.comps[start].infection = 2
        for (i in 0..moves - 1)
            net.makeNextStep(randVal)
        assertEquals(res, net.infectionToList())
    }

    /*
    Windows    Linux         OS X
    */

    @Test fun NoVergesInfection100start1move100 () {

        val randVal = 0
        val start   = 1
        val moves   = 100
        val res     = linkedListOf(0, 2, 0)

        val vers = arrayOf(listOf(), listOf(), listOf()) : Array<List<Int>>
        val names = arrayOf("Windows", "Linux", "OS X")
        val net = Net(vers, names)
        net.comps[start].infection = 2
        for (i in 0..moves - 1)
            net.makeNextStep(randVal)
        assertEquals(res, net.infectionToList())
    }

    /*
    0.Unknown <---- 1. Unknown <---- 2.Unknown
                       /\
                       |
                      |
                    3. Unknown         4. Windows -----> 5.Linux
    */

    @Test fun TwoComponentsInfection100start2and3move1 () {
        smallNetTest(0, 2, 3, 1, linkedListOf(0, 2, 2, 2, 0, 0))
    }
    @Test fun TwoComponentsInfection0start2and3move100 () {
        smallNetTest(1, 2, 3, 100, linkedListOf(0, 0, 2, 2, 0, 0))
    }
    @Test fun TwoComponentsInfection100start4move100 () {
        smallNetTest(0, 4, 4, 100, linkedListOf(0, 0, 0, 0, 2, 2))
    }
    @Test fun TwoComponentsInfection0start4move100 () {
        smallNetTest(1, 4, 4, 100, linkedListOf(0, 0, 0, 0, 2, 0))
    }
    private fun smallNetTest (randVal : Int, start1 : Int, start2 : Int, moves : Int, res : LinkedList<Int>) {
        val vers = arrayOf(listOf(), listOf(0), listOf(1),
                listOf(1), listOf(5), listOf())
        val names = arrayOf("Unknown", "Unknown", "Unknown", "Unknown", "Windows", "Linux")
        val net = Net(vers, names)
        net.comps[start1].infection = 2
        net.comps[start2].infection = 2
        for (i in 0..moves - 1)
            net.makeNextStep(randVal)
        assertEquals(res, net.infectionToList())
    }
}

