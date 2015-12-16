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
        readValue(7, 1, 'X') // default size: 5x5
        assertEquals(gameTest.fieldSizeHorizontal, 8)
    }

    @Test fun testResize2() {
        readValue(4, 10, 'X') // default size: 5x5
        assertEquals(gameTest.fieldSizeVertical, 16)
    }

    @Test fun testResize3() {
        readValue(9, 19, 'X') // default size: 5x5
        assertEquals(gameTest.fieldSizeHorizontal, 16)
        assertEquals(gameTest.fieldSizeVertical, 32)
    }

    public fun readValue(coordX: Int, coordY: Int, symbol: Char) {
        var coordX_ = coordX
        var coordY_ = coordY

        if (coordX > gameTest.fieldSizeHorizontal - 1) { gameTest.resize(coordX, 1)}
        if (coordY > gameTest.fieldSizeVertical - 1)   { gameTest.resize(coordY, 0)}

        if (coordY < 0) { gameTest.shiftUp(Math.abs(coordY));   coordY_ = 0 }
        if (coordX < 0) { gameTest.shiftLeft(Math.abs(coordX)); coordX_ = 0 }

        gameTest.field[coordX_][coordY_] = symbol
    }

    @Test fun testShiftUp1() {
        readValue(4, -3, 'O') // horizontal shift
        assertEquals(gameTest.field[4][0] , 'O')
    }

    @Test fun testShiftUp2() {
        readValue(1, 1, 'O')
        readValue(3, -2, 'X')
        assertEquals(gameTest.field[1][3], 'O')
    }

    @Test fun testShiftLeft1() {
        readValue(-10, 20, 'X') //vertical shift
        assertEquals(gameTest.field[0][20], 'X')
    }

    @Test fun testShiftLeft2() {
        readValue(4, 1, 'O')
        readValue(-5, 4, 'X')
        assertEquals(gameTest.field[9][1], 'O')
    }

    @Test fun testShiftUpAndLeft1() {
        readValue(-1, -1, 'X') //horizontal shift and vertical shift; 'X' ---> [0][0]
        assertEquals(gameTest.field[0][0], 'X')
    }

    @Test fun testShiftAndLeft2() {
        readValue(-7, -9, 'O') //horizontal shift and vertical shift; 'O' ---> [0][0]
        assertEquals(gameTest.field[0][0], 'O')
    }
}