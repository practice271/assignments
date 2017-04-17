package hw06


import org.junit.Test
import kotlin.test.assertEquals

class gameLogicTest {
    private val logic = TLogic()

    @Test
    public fun moveGeneral() {
        logic.move(0, 0)
        logic.move(0, 1)
        logic.move(2, 2)
        assertEquals("X . .\nO . .\n. . X", logic.fieldToString())
    }

    @Test
    public fun checkWinWhenFalse() {
        logic.move(0, 0)
        logic.move(1, 1)
        logic.move(2, 2)
        assertEquals(false, logic.checkWin())
    }

    /*
    * X . O
    * O X .
    * . . X
    * */
    @Test
    public fun checkWinWhenTrue() {
        logic.move(0, 0)
        logic.move(0, 1)
        logic.move(2, 2)
        logic.move(2, 0)
        logic.move(1, 1)
        assertEquals(true, logic.checkWin())
    }

    /*
    * X O X
    * O O X
    * X X O
    * */
    @Test
    public fun tieTest() {
        logic.move(0, 0)
        logic.move(0, 1)
        logic.move(2, 0)
        logic.move(2, 2)
        logic.move(1, 2)
        logic.move(1, 1)
        logic.move(0, 2)
        logic.move(1, 0)
        logic.move(2, 1)
        assertEquals(false, logic.checkWin())
    }
}
