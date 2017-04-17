package hw06

/* Console game  made by Guzel Garifullina
   Estimated time  30 minutes
   real time       30 minutes
*/
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class game {
    @Test fun TestLogButton() {
        val button = LogicalButton()
        button.setLabel('X')
        assertEquals ('X', button.getLabel())
    }
    @Test fun TestConsoleGameWin() {
        val buttons = CButtonField()
        buttons.select(1,1)
        buttons.select(2,1)
        buttons.select(1,2)
        buttons.select(2,2)
        buttons.select(1,3)
        assertTrue (buttons.isGameOver())
    }

    @Test fun TestConsoleGameWinO() {
        val buttons = CButtonField()
        buttons.select(1,1)
        buttons.select(2,2)
        buttons.select(2,1)
        buttons.select(3,1)
        buttons.select(1,2)
        buttons.select(1,3)
        assertTrue (buttons.isVictory() )
    }
    @Test fun TestConsoleGameTie() {
        val buttons = CButtonField()
        buttons.select(1,1)
        buttons.select(1,2)
        buttons.select(1,3)
        buttons.select(2,1)
        buttons.select(2,2)
        buttons.select(2,3)
        buttons.select(3,2)
        buttons.select(3,1)
        buttons.select(3,3)
        assertTrue (buttons.isTie() )
    }
}