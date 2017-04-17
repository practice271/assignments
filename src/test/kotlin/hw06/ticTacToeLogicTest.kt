package hw06

import org.junit.Test
import kotlin.test.assertEquals

public class ticTacToeLogicTest() {
    @Test fun getMovingPlayerTest() {
        val game = TicTacToe()
        assertEquals(Player.FIRST, game.moveOf)
        game.tryMakeMove(1, 1)
        assertEquals(Player.SECOND, game.moveOf)
        game.tryMakeMove(1, 1)
        assertEquals(Player.SECOND, game.moveOf)
        game.tryMakeMove(10, 10)
        assertEquals(Player.SECOND, game.moveOf)
        game.tryMakeMove(0, 0)
        assertEquals(Player.FIRST, game.moveOf)
    }

    @Test fun getWinnerTest() {
        val game = TicTacToe()
        assertEquals(null, game.winner)
        game.tryMakeMove(0, 0)
        game.tryMakeMove(0, 0)
        game.tryMakeMove(1, 1)
        game.tryMakeMove(0, 1)
        game.tryMakeMove(1, 0)
        game.tryMakeMove(0, 2)
        game.tryMakeMove(2, 2)
        assertEquals(Player.FIRST, game.winner)
    }

    @Test fun movesNumberTest() {
        val game = TicTacToe()
        assertEquals(0, game.movesNumber)
        game.tryMakeMove(0, 0)
        assertEquals(1, game.movesNumber)
        game.tryMakeMove(0, 1)
        game.tryMakeMove(0, 2)
        game.tryMakeMove(1, 0)
        game.tryMakeMove(1, 2)
        game.tryMakeMove(1, 1)
        game.tryMakeMove(2, 0)
        game.tryMakeMove(2, 2)
        assertEquals(8, game.movesNumber)
        var x = game.tryMakeMove(2, 1)
        assertEquals(9, game.movesNumber)
    }

    @Test fun tryMakeMoveTest() {
        val game = TicTacToe()
        assertEquals(MoveResult.OK, game.tryMakeMove(0, 0))
        assertEquals(MoveResult.NON_EMPTY_CELL, game.tryMakeMove(0, 0))
        assertEquals(MoveResult.NON_EXISTENT_CELL, game.tryMakeMove(100, 0))
        assertEquals(MoveResult.OK, game.tryMakeMove(1, 1))
        game.tryMakeMove(0, 1)
        game.tryMakeMove(1, 0)
        assertEquals(MoveResult.OK, game.tryMakeMove(0, 2))
        assertEquals(MoveResult.GAME_ENDED, game.tryMakeMove(2, 2))
    }
}
