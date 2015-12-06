package TicTacToe

import org.junit.Test
import kotlin.test.assertEquals

class LogicTest {

    @Test fun testCheckWinRow1() {
        var game: Logic = Logic()
        game.field[3][0] = 'X'
        game.field[0][0] = 'O'
        game.field[3][1] = 'X'
        game.field[1][3] = 'O'
        game.field[3][2] = 'X'
        game.field[0][4] = 'O'
        game.field[3][3] = 'X'
        game.field[4][0] = 'O'
        game.field[3][4] = 'X'
        game.checkWinRow()
        assertEquals(game.winner, 'X')
    }

    @Test fun testCheckWinRow2() {
        var game: Logic = Logic()
        game.field[3][4] = 'X'
        game.field[0][0] = 'O'
        game.field[3][1] = 'X'
        game.field[1][3] = 'O'
        game.field[3][2] = 'X'
        game.field[0][4] = 'O'
        game.field[3][3] = 'X'
        game.field[4][0] = 'O'
        game.field[3][4] = 'X'
        game.checkWinRow()
        assertEquals(game.winner, ' ')
    }

    @Test fun testCheckWinColumn1() {
        var game: Logic = Logic()
        game.field[1][1] = 'X'
        game.field[0][0] = 'O'
        game.field[4][2] = 'X'
        game.field[1][0] = 'O'
        game.field[2][1] = 'X'
        game.field[2][0] = 'O'
        game.field[2][3] = 'X'
        game.field[3][0] = 'O'
        game.field[3][1] = 'X'
        game.field[4][0] = 'O'
        game.checkWinColumn()
        assertEquals(game.winner, 'O')
    }

    @Test fun testCheckWinColumn2() {
        var game: Logic = Logic()
        game.field[1][1] = 'X'
        game.field[0][4] = 'O'
        game.field[4][2] = 'X'
        game.field[1][0] = 'O'
        game.field[2][1] = 'X'
        game.field[2][0] = 'O'
        game.field[2][3] = 'X'
        game.field[3][0] = 'O'
        game.field[3][1] = 'X'
        game.field[4][0] = 'O'
        game.checkWinColumn()
        assertEquals(game.winner, ' ')
    }

    @Test fun testCheckWinLRDiagonal() {
        var game: Logic = Logic()
        game.field[0][0] = 'X'
        game.field[2][4] = 'O'
        game.field[1][1] = 'X'
        game.field[1][0] = 'O'
        game.field[2][2] = 'X'
        game.field[2][0] = 'O'
        game.field[3][3] = 'X'
        game.field[3][0] = 'O'
        game.field[4][4] = 'X'
        game.checkWinLRDiagonal()
        assertEquals(game.winner, 'X')
    }

    @Test fun testCheckWinLRDiagonal2() {
        var game: Logic = Logic()
        game.field[0][1] = 'X'
        game.field[2][4] = 'O'
        game.field[1][1] = 'X'
        game.field[1][0] = 'O'
        game.field[2][2] = 'X'
        game.field[2][0] = 'O'
        game.field[3][3] = 'X'
        game.field[3][0] = 'O'
        game.field[4][4] = 'X'
        game.checkWinLRDiagonal()
        assertEquals(game.winner, ' ')
    }

    @Test fun testCheckWinRLDiagonal1() {
        var game: Logic = Logic()
        game.field[0][1] = 'X'
        game.field[4][0] = 'O'
        game.field[1][1] = 'X'
        game.field[3][1] = 'O'
        game.field[2][2] = 'X'
        game.field[2][2] = 'O'
        game.field[3][3] = 'X'
        game.field[1][3] = 'O'
        game.field[4][4] = 'X'
        game.field[0][4] = 'O'
        game.checkWinRLDiagonal()
        assertEquals(game.winner, 'O')
    }


    @Test fun testCheckWinRLDiagonal2() {
        var game: Logic = Logic()
        game.field[0][1] = 'X'
        game.field[4][2] = 'O'
        game.field[1][1] = 'X'
        game.field[3][1] = 'O'
        game.field[2][2] = 'X'
        game.field[2][2] = 'O'
        game.field[3][3] = 'X'
        game.field[1][3] = 'O'
        game.field[4][4] = 'X'
        game.field[4][0] = 'O'
        game.checkWinRLDiagonal()
        assertEquals(game.winner, ' ')
    }

    var gameTest: Logic = Logic()

    @Test fun testResize1() {
        readvalue(7, 1, 'X') // default size: 5x5
        assertEquals(gameTest.fieldSizeHorizontal, 8)
    }

    @Test fun testResize2() {
        readvalue(4, 10, 'X') // default size: 5x5
        assertEquals(gameTest.fieldSizeVertical, 16)
    }

    @Test fun testResize3() {
        readvalue(9, 19, 'X') // default size: 5x5
        assertEquals(gameTest.fieldSizeHorizontal, 16)
        assertEquals(gameTest.fieldSizeVertical, 32)
    }

    public fun readvalue(coordX: Int, coordY:Int, symbol: Char) {
        if (coordX > gameTest.fieldSizeHorizontal - 1) { gameTest.resize(coordX, 1)}
        if (coordY > gameTest.fieldSizeVertical - 1)   { gameTest.resize(coordY, 0)}
        gameTest.field[coordX][coordY] = symbol
    }
}