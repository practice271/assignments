/**
 * Tests for console version
 */

package hw06
import org.junit.Test
import kotlin.test.assertEquals

class Console_Test {
    @Test fun checkWin1() {
        //diagonal
       var ob = Console()
        ob.readValue("0",0)
        ob.readValue("1",1)
        ob.readValue("4",2)
        ob.readValue("2",3)
        ob.readValue("8",4)

        assertEquals(ob.checkWin(), true)
    }

    /*
         x |   |
         =========
           | x |
         =========
           |   | x
    */

    @Test fun checkWin2() {
        //row
        var ob = Console()
        ob.readValue("5",0)
        ob.readValue("0",1)
        ob.readValue("4",2)
        ob.readValue("1",3)
        ob.readValue("8",4)
        ob.readValue("2",5)

        assertEquals(ob.checkWin(), true)
    }

    /*
         o | o | o
         =========
           |   |
         =========
           |   |
   */

    @Test fun checkWin3() {
        //column
        var ob = Console()
        ob.readValue("1",0)
        ob.readValue("0",1)
        ob.readValue("4",2)
        ob.readValue("5",3)
        ob.readValue("7",4)
        ob.readValue("6",5)

        assertEquals(ob.checkWin(), true)
    }

    /*
           | x |
         =========
           | x |
         =========
           | x |
    */

}