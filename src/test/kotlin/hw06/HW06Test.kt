package homework.hw06

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse


public class HW06Test {
    @Test fun placeMarkTest() {
        newGame()
        placeMark(1,1)
        assertEquals(field[1][1], "X")
        placeMark(2,1)
        assertEquals(field[2][1], "O")
        assertEquals(field[0][1], " ")
        placeMark(1,1)
        assertEquals(field[1][1], "X")
    }
    @Test fun checkForWinTest() {
        changeRules(3,3)
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
    @Test fun bigFieldTest() {
        changeRules(19,5)
        newGame()
        placeMark(15,8)
        assertEquals(field[15][8], "X")
        newGame()
        field[5][1] = "X";field[5][2] = "X";field[5][3] = "X";field[5][4] = "X";field[5][5] = "X"
        assertTrue(checkForWin())
        newGame()
        field[0][18] = "O";field[1][18] = "O";field[2][18] = "O";field[3][18] = "O";field[4][18] = "O"
        assertTrue(checkForWin())
        newGame()
        field[7][8] = "X";field[8][9] = "X";field[9][10] = "X";field[10][11] = "X";field[11][12] = "X"
        assertTrue(checkForWin())
        newGame()
        field[10][4] = "X";field[9][5] = "X";field[8][6] = "X";field[7][7] = "X";field[6][8] = "X"
        assertTrue(checkForWin())
    }
}