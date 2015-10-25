package hw06

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Mikhail on 25.10.2015.
 */
public class HW06Test {
    val t = TicTacToe()
    @Test fun TicTacToeWinTest() {
        t.board = arrayOf(charArrayOf('X', 'X', 'O'), charArrayOf('O', 'X', 'O'), charArrayOf('X', 'O', 'X'))
        assertEquals(t.winner(), true)
    }
    @Test fun TicTacToeDrawTest() {
        t.board = arrayOf(charArrayOf('X', 'X', 'O'), charArrayOf('O', 'O', 'X'), charArrayOf('X', 'O', 'X'))
        assertEquals(t.winner(), false)
    }
    @Test fun TicTacToeDrawBoardTest() {
        t.board = arrayOf(charArrayOf('X', 'X', 'O'), charArrayOf('O', 'X', 'O'), charArrayOf('X', 'O', 'X'))
        assertEquals(t.drawBoard(), "Game board: \n[X][X][O]\n[O][X][O]\n[X][O][X]\n")
    }
}
