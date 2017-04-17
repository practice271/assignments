package homework.hw06

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse


public class HW06Test {
    @Test fun placeMarkTest() {
        newGame()
        placeMark(1,1)
        assertEquals(field[1][1], "O")
        placeMark(2,1)
        assertEquals(field[2][1], "X")
        assertEquals(field[0][1], " ")
        placeMark(1,1)
        assertEquals(field[1][1], "O")
    }
    @Test fun checkForWinTest() {
        newGame()
        field[0][0] = "X";field[0][1] = "X";field[0][2] = "X"
        assertTrue(checkForWin())
        newGame()
        field[0][0] = "O";field[1][0] = "O";field[2][0] = "O"
        assertTrue(checkForWin())
        newGame()
        field[0][0] = "X";field[1][1] = "X";field[2][2] = "X"
        assertTrue(checkForWin())
        newGame()
        field[2][0] = "X";field[1][1] = "X";field[0][2] = "X"
        assertTrue(checkForWin())
        newGame()
        field[0][0] = "X";field[0][1] = "X";field[1][2] = "X"
        assertFalse(checkForWin())
    }
    @Test fun resultTest() {
        newGame()
        placeMark(1,1);placeMark(0,1)
        placeMark(2,0);placeMark(0,2)
        placeMark(0,0);placeMark(1,0)
        placeMark(2,2)
        assertEquals(result, "Player" + playerMark.toString() + " wins!")
        newGame()
        placeMark(1,1);placeMark(0,0)
        placeMark(2,2);placeMark(2,0)
        placeMark(1,0);placeMark(1,2)
        placeMark(0,1);placeMark(2,1)
        placeMark(0,2)
        assertEquals(result, "Draw!")
    }
    @Test fun consoleInterfaceTest() {
        newGame()
        input = "0,0";handle(input)
        assertEquals(field[0][0], "X")
        input = "0,0";handle(input)
        assertEquals(output, "Incorrect field")
        input = "test";handle(input)
        assertEquals(output, "Unknown command")
        input = "5,6";handle(input)
        assertEquals(output, "Unknown command")
    }
}