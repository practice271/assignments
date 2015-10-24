package hw06

import org.junit.Test
import kotlin.test.assertEquals
import java.io.*

public class ticTacToeConsoleTest() {

    val help = "To make a move write string in format 'x y' where x and y are equal 0, 1 or 2. " +
            "Write 'EXIT' to exit.\n"
    val wrongInputError = "Wrong input format. " + help
    val notEmptyCellError = "Cell is not empty!\n"
    val notExistentCellError = "This cell does not exist. " + help
    val firstPlayerWon = "Player I won!\n"
    val draw = "Draw!\n"

    class TestWriter : Writer() {

        var lastUpdate = String()       //contains only last update

        override fun write(arr: CharArray, off: Int, len: Int) {
        }

        override fun write(str: String) {
            lastUpdate = str
        }

        override fun flush() {
        }

        override fun close() {
        }
    }

    class TestReader : Reader() {

        public var str = String()
        override fun read(cbuf: CharArray, off: Int, len: Int): Int {
            var n = 0
            for (i in off..off + len - 1)
                if (str.length() > i) {
                    cbuf[i] = str[i]
                    n++
                } else break
            return n
        }

        override fun close() {
        }
    }

    @Test fun consoleTest() {
        val game = TicTacToeConsole()
        val testReader = TestReader()
        val testWriter = TestWriter()

        testReader.str = "WRONG INPUT\nEXIT\n"
        game.startGame(testReader, testWriter)
        assertEquals(wrongInputError, testWriter.lastUpdate)

        testReader.str = "EXIT\n"
        game.startGame(testReader, testWriter)
        assertEquals(help, testWriter.lastUpdate)

        testReader.str = "1 1\n1 1\nEXIT\n"
        game.startGame(testReader, testWriter)
        assertEquals(notEmptyCellError, testWriter.lastUpdate)

        testReader.str = "5 1\nEXIT\n"
        game.startGame(testReader, testWriter)
        assertEquals(notExistentCellError, testWriter.lastUpdate)
    }

    @Test fun firstPlayerWonTest() {
        val game = TicTacToeConsole()
        val testReader = TestReader()
        val testWriter = TestWriter()
        var field = String()

        field = "- - -\n" +
                "- X -\n" +
                "- - -\n"
        testReader.str = "1 1\nEXIT\n"
        game.startGame(testReader, testWriter)
        assertEquals(field, testWriter.lastUpdate)

        field = "- - -\n" +
                "- X -\n" +
                "- - O\n"
        testReader.str = "1 1\n2 2\nEXIT\n"
        game.startGame(testReader, testWriter)
        assertEquals(field, testWriter.lastUpdate)

        field = "- - -\n" +
                "X X -\n" +
                "- - O\n"
        testReader.str = "1 1\n2 2\n1 0\nEXIT\n"
        game.startGame(testReader, testWriter)
        assertEquals(field, testWriter.lastUpdate)

        field = "- - -\n" +
                "X X -\n" +
                "- O O\n"
        testReader.str = "1 1\n2 2\n1 0\n2 1\nEXIT\n"
        game.startGame(testReader, testWriter)
        assertEquals(field, testWriter.lastUpdate)

        testReader.str = "1 1\n2 2\n1 0\n2 1\n1 2\nEXIT\n"
        game.startGame(testReader, testWriter)
        assertEquals(firstPlayerWon, testWriter.lastUpdate)
    }

    @Test fun drawTest() {
        val game = TicTacToeConsole()
        val testReader = TestReader()
        val testWriter = TestWriter()

        testReader.str = "0 0\n0 2\n0 1\n1 1\n1 2\n1 0\n2 0\n2 2\n2 1\nEXIT\n"
        game.startGame(testReader, testWriter)
        assertEquals(draw, testWriter.lastUpdate)
    }
}