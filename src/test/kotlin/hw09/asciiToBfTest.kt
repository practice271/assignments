package hw09

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Alexander on 20.11.2015.
 */
class asciiToBfTest {
    @Test fun helloWorldTest() {
        val hw = "Hello World!"
        val br_hw = asciiToBrainfuck().translate(hw)
        val res = brainfuckInterpeter().interpete(br_hw, false)
        assertEquals(hw, res)
    }

    @Test fun emptyInput() {
        val bf = asciiToBrainfuck().translate("")
        val res = brainfuckInterpeter().interpete(bf, false)
        assertEquals("", res)
    }

    @Test fun oneSymbolInput() {
        val bf = asciiToBrainfuck().translate("!")
        val res = brainfuckInterpeter().interpete(bf, false)
        assertEquals("!", res)
    }

    @Test fun longVariousInput() {
        val input = "'Cause the dead don't shuffle - they run! Rush through streets of slaughter clutching your gun!"
        val bf = asciiToBrainfuck().translate(input)
        val res = brainfuckInterpeter().interpete(bf, false)
        assertEquals(input, res)
    }
}