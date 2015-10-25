package homeworks.hw06.logic

import homeworks.hw06.console.TicTacToe
import org.junit.Test
import kotlin.test.assertEquals
import homeworks.hw06.logic.Logic

/**
 * Created by Ilya on 25.10.2015.
 */
public class hw06Test {
    @Test fun logicTest1() {
        val field = TicTacToe()
        field.array = arrayOf(arrayOf("null", "null", "null"), arrayOf("null", "null", "null"), arrayOf("null", "null", "null"))
        assertEquals(false, field.checkSuccess("X"))
        //In the empty field situation is not winning
    }

    @Test fun logicTest2() {
        val field = TicTacToe()
        field.array = arrayOf(arrayOf("X", "null", "null"), arrayOf("X", "null", "null"), arrayOf("X", "null", "null"))
        assertEquals(true, field.checkSuccess("X"))
    }

    @Test fun consoleTest1() {
        val field = TicTacToe()
        val array = arrayOf(arrayOf("X", "O", "X"), arrayOf("O", "X", "O"), arrayOf("X", "null", "null"))
        assertEquals("Player with X Win!!!", field.startFromArray(array))
    }

    @Test fun consoleTest2() {
        val field = TicTacToe()
        val array = arrayOf(arrayOf("X", "O", "X"), arrayOf("O", "O", "X"), arrayOf("O", "X", "O"))
        assertEquals("Tie!", field.startFromArray(array))
    }
}