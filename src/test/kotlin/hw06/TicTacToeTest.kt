package hw06

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

public class TicTacToeTest {
    @Test fun checkColumnTest() {
        val ttt = TicTacToeConsole()
        ttt.field[0][0] = 1
        ttt.field[1][0] = 1
        ttt.field[2][0] = 1
        assertTrue(ttt.checkColumn(0, 1), "Checking for the right mark in the right column")
        assertFalse(ttt.checkColumn(0, 0), "Checking for the wrong mark in the right column")
        assertFalse(ttt.checkColumn(1, 1), "Checking for the right mark in the wrong column")
    }

    @Test fun checkRowTest() {
        val ttt = TicTacToeConsole()
        ttt.field[0][0] = 0
        ttt.field[0][1] = 0
        ttt.field[0][2] = 0
        assertTrue(ttt.checkRow(0, 0), "Checking for the right mark in the right row")
        assertFalse(ttt.checkRow(0, 1), "Checking for the wrong mark in the right row")
        assertFalse(ttt.checkRow(1, 0), "Checking for the right mark in the wrong row")
    }

    @Test fun checkDiagonalNW_SETest() {
        val ttt = TicTacToeConsole()
        ttt.field[0][0] = 0
        ttt.field[1][1] = 0
        ttt.field[2][2] = 0
        assertTrue(ttt.checkDiagonalNW_SE(0), "Checking for the right mark")
        assertFalse(ttt.checkDiagonalNW_SE(1), "Checking for the wrong mark")
    }

    @Test fun checkDiagonalNE_SWTest() {
        val ttt = TicTacToeConsole()
        ttt.field[0][2] = 0
        ttt.field[1][1] = 0
        ttt.field[2][0] = 0
        assertTrue(ttt.checkDiagonalNE_SW(0), "Checking for the right mark")
        assertFalse(ttt.checkDiagonalNE_SW(1), "Checking for the wrong mark")
    }

    @Test fun checkVictoryTest() {
        val ttt = TicTacToeConsole()
        ttt.field[0][2] = 0
        ttt.field[1][1] = 0
        ttt.field[2][0] = 0
        assertTrue (ttt.checkVictory(0, 2, 0), "Checking for the right mark")
        assertTrue (ttt.checkVictory(1, 1, 0), "Checking for the right mark")
        assertTrue (ttt.checkVictory(2, 0, 0), "Checking for the right mark")

        assertFalse(ttt.checkVictory(0, 2, 1), "Checking for the wrong mark")
        assertFalse(ttt.checkVictory(2, 2, 0), "Checking in the wrong place")
    }

    @Test fun setMarkTest() {
        val ttt = TicTacToeConsole()
        assertEquals(ttt.setMark(1, 1, 0), "")
    }

    @Test fun setMarkIncorrectInputTest() {
        val ttt = TicTacToeConsole()
        assertEquals(ttt.setMark(-1, -1, 0), "Incorrect input, try again!")
    }

    @Test fun setMarkOverwritingMarkTest() {
        val ttt = TicTacToeConsole()
        ttt.setMark(1, 1, 0)
        assertEquals(ttt.setMark(1, 1, 0), "You can't overwrite marks!", "Setting the same mark")
        assertEquals(ttt.setMark(1, 1, 1), "You can't overwrite marks!", "Setting other mark")
    }
}