/*
 * Homework 6 (20.10.2015)
 * Tests for console application
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw06

import org.junit.Test
import java.io.File
import java.io.PrintStream
import kotlin.test.assertEquals

public class hw06ConsoleTest {
    var file   = File("src/test/kotlin/homework/hw06/input.in")
    val stream = PrintStream(file)

    /** Creates game situation using given array. */
    private fun createSituation(arr : Array<String>) : Console {
        val game = Console(stream)
        for (i in arr) {
            game.nextMove(i)
        }
        stream.print("#") // after this symbol begins part which we want to check
        return game
    }

    /** First player puts 'x' into 1 2
     *
     *     |   |            | x |
     *  ===+===+===      ===+===+===
     *     |   |     ->     |   |
     *  ===+===+===      ===+===+===
     *     |   |            |   |
     */
    @Test fun test01() {
        Console(stream).nextMove("1 2")
        val result = "   | x |   \n===+===+===\n   |   |   \n===+===+===\n   |   |   \n"
        assertEquals(result, file.readText())
    }

    /** Second player puts 'o' into 1 2
     *
     *     | x |
     *  ===+===+===
     *     |   |     ->  Error: incorrect move
     *  ===+===+===
     *     |   |
     */
    @Test fun test02() {
        createSituation(arrayOf("1 2")).nextMove("1 2")
        val state = file.readText()
        val result = "Error: incorrect move\n"
        assertEquals(result, state.drop(state.lastIndexOf('#') + 1))
    }

    /** Second player puts 'o' into 2 2
     *
     *     | x |            | x |
     *  ===+===+===      ===+===+===
     *     |   |     ->     | o |
     *  ===+===+===      ===+===+===
     *     |   |            |   |
     */
    @Test fun test03() {
        createSituation(arrayOf("1 2")).nextMove("2 2")
        val state = file.readText()
        val result = "   | x |   \n===+===+===\n   | o |   \n===+===+===\n   |   |   \n"
        assertEquals(result, state.drop(state.lastIndexOf('#') + 1))
    }

    /** First player puts 'x' into 4 1
     *
     *     | x |
     *  ===+===+===
     *     | o |     ->  Error: incorrect data
     *  ===+===+===
     *     |   |
     */
    @Test fun test04() {
        createSituation(arrayOf("1 2", "2 2")).nextMove("4 1")
        val state  = file.readText()
        val result = "Error: incorrect data\n"
        assertEquals(result, state.drop(state.lastIndexOf('#') + 1))
    }

    /** First player puts 'x' into 1 three
     *
     *     | x |
     *  ===+===+===
     *     | o |     ->  Error: incorrect data
     *  ===+===+===
     *     |   |
     */
    @Test fun test05() {
        createSituation(arrayOf("1 2", "2 2")).nextMove("1 three")
        val state  = file.readText()
        val result = "Error: incorrect data\n"
        assertEquals(result, state.drop(state.lastIndexOf('#') + 1))
    }

    /** First player puts 'x' into 3 2
     *
     *   x | x | o        x | x | o
     *  ===+===+===      ===+===+===
     *   o | o | x   ->   o | o | x
     *  ===+===+===      ===+===+===
     *   x |   | o        x | x | o
     *
     *                   Draw
     */
    @Test fun test06() {
        createSituation(arrayOf("1 2", "2 2", "1 1", "1 3", "3 1", "2 1", "2 3", "3 3")).nextMove("3 2")
        val state = file.readText()
        val result = " x | x | o \n===+===+===\n o | o | x \n===+===+===\n x | x | o \nDraw\n"
        assertEquals(result, state.drop(state.lastIndexOf('#') + 1))
    }

    /** Second player puts 'o' into 2 3
     *
     *   x | x | o        x | x | o
     *  ===+===+===      ===+===+===
     *     | o |    ->      | o | o
     *  ===+===+===      ===+===+===
     *   x | x | o        x | x | o
     *
     *                   Ouths wins
     */
    @Test fun test07() {
        createSituation(arrayOf("1 2", "1 3", "3 2", "2 2", "3 1", "3 3", "1 1")).nextMove("2 3")
        val state = file.readText()
        val result = " x | x | o \n===+===+===\n   | o | o \n===+===+===\n x | x | o \nOuths wins\n"
        assertEquals(result, state.drop(state.lastIndexOf('#') + 1))
    }

    /** First player puts 'x' into 1 1
     *
     *     | o | x        x | o | x
     *  ===+===+===      ===+===+===
     *     | x | o  ->      | x | o
     *  ===+===+===      ===+===+===
     *   o |   | x        o |   | x
     *
     *                   Crosses wins
     */
    @Test fun test08() {
        createSituation(arrayOf("2 2", "1 2", "1 3", "3 1", "3 3", "2 3")).nextMove("1 1")
        val state = file.readText()
        val result = " x | o | x \n===+===+===\n   | x | o \n===+===+===\n o |   | x \nCrosses wins\n"
        assertEquals(result, state.drop(state.lastIndexOf('#') + 1))
    }
}