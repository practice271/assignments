package homework.hw06

import homework.hw06.cli.printGrid
import org.junit.Test
import java.io.Writer
import kotlin.test.assertEquals

/**
 * Tests for TicTacToe logic and console application.
 * @author Kirill Smirenko, group 271
 */
class Hw06Test {
    @Test fun testWinHorizX() {
        Core.instance.restart()
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(0, 0))
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(1, 1))
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(0, 2))
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(1, 2))
        assertEquals(Core.ErrorCode.WIN, Core.instance.makeMove(0, 1))
    }

    @Test fun testWinVertO() {
        Core.instance.restart()
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(1, 0))
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(0, 1))
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(1, 2))
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(1, 1))
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(2, 2))
        assertEquals(Core.ErrorCode.WIN, Core.instance.makeMove(2, 1))
    }

    @Test fun testWinDiag1X() {
        Core.instance.restart()
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(0, 0))
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(0, 1))
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(1, 1))
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(0, 2))
        assertEquals(Core.ErrorCode.WIN, Core.instance.makeMove(2, 2))
    }

    @Test fun testWinDiag2O() {
        Core.instance.restart()
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(0, 0))
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(2, 0))
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(2, 1))
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(1, 1))
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(1, 2))
        assertEquals(Core.ErrorCode.WIN, Core.instance.makeMove(0, 2))
    }

    @Test fun testDraw() {
        Core.instance.restart()
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(1, 1))
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(0, 0))
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(0, 1))
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(2, 1))
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(0, 2))
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(1, 0))
        assertEquals(Core.ErrorCode.OK, Core.instance.makeMove(1, 2))
        assertEquals(Core.ErrorCode.DRAW, Core.instance.makeMove(2, 2))
    }

    @Test fun testWrongMoves() {
        Core.instance.restart()
        assertEquals(Core.ErrorCode.ERROR, Core.instance.makeMove(0, 3))
        Core.instance.makeMove(1, 1)
        assertEquals(Core.ErrorCode.ERROR, Core.instance.makeMove(1, 1))
    }

    @Test fun testConsolePrinting() {
        Core.instance.restart()
        Core.instance.makeMove(0, 0)
        Core.instance.makeMove(0, 2)
        Core.instance.makeMove(2, 0)
        Core.instance.makeMove(1, 1)
        val writer = CustomWriter()
        printGrid(writer)
        assertEquals("X . O \n. O . \nX . . \n", writer.getBuffer())
    }

    @Test fun testConsoleNotOverwritingSymbols() {
        var writer = CustomWriter()
        Core.instance.restart()
        Core.instance.makeMove(1, 1)
        Core.instance.makeMove(1, 1)
        printGrid(writer)
        assertEquals(". . . \n. X . \n. . . \n", writer.getBuffer())
        writer = CustomWriter()
        assertEquals("", writer.getBuffer())
        Core.instance.makeMove(2, 2)
        Core.instance.makeMove(2, 2)
        printGrid(writer)
        assertEquals(". . . \n. X . \n. . O \n", writer.getBuffer())
    }

    class CustomWriter() : Writer() {
        private var buffer = ""

        override fun write(cbuf : CharArray?, off : Int, len : Int) {
        }

        override fun write(str : String) {
            buffer += str
        }

        override fun flush() {
        }

        override fun close() {
        }

        fun getBuffer() = buffer
    }
}