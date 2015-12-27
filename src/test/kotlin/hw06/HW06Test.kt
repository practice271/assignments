package homework.hw06

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse


public class HW06Test {
    @Test fun placeMarkTest() {
        newGame()
        placeMark("500,-1267")
        assertEquals(board.getOrDefault("500,-1267", ' '), 'X')
        placeMark("0,0")
        assertEquals(board.getOrDefault("0,0", ' '), 'O')
    }
    @Test fun checkForWinTest() {
        newGame()
        board.put("0,0", 'X')
        board.put("3,3", 'X')
        board.put("1,1", 'X')
        checkForWin("1,1")
        assertFalse(gameOver)
        board.put("2,2", 'X')
        board.put("-1,-1", 'X')
        checkForWin("-1,-1")
        assertTrue(gameOver)
    }
    @Test fun resultTest() {
        newGame()
        mark("1000,1"); mark("1000,-6");
        mark("299,-8"); mark("1000,-7");
        mark("25,164"); mark("1000,-10");
        mark("-20,-1"); mark("1000,-8");
        mark("15,259"); mark("1000,-9");
        assertEquals(playerMark, 'O')
    }
    @Test fun consoleInterfaceTest() {
        newGame()
        input = "0,0"; handle(input)
        assertEquals(board.getOrDefault("0,0", ' '), 'X')
        assertEquals(output, "Player X marked (0,0)")
        input = "0,0"; handle(input)
        assertEquals(output, "Incorrect field")
        input = "test"; handle(input)
        assertEquals(output, "Unknown command")
        input = "233,,545"; handle(input)
        assertEquals(output, "Unknown command")
        input = ",29,"; handle(input)
        assertEquals(output, "Unknown command")
        input = "23,ee"; handle(input)
        assertEquals(output, "Unknown command")
    }
}