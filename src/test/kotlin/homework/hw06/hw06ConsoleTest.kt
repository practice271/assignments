/*
 * Homework 6 (19.10.2015)
 * Tests for console application
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw06

import org.junit.Test
import kotlin.test.assertEquals

public class hw06ConsoleTest {
    /** Creates game situation using given array. */
    private fun createSituation(arr : Array<String>) : Console {
        val game = Console()
        for (i in arr) {
            game.nextMove(i)
        }
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
        val game   = Console()
        val state  = game.nextMove("1 2").removeSuffix("\nYour move, player2")
        val result = "   | x |   \n===+===+===\n   |   |   \n===+===+===\n   |   |   \n"
        assertEquals(result, state)
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
        val game   = createSituation(arrayOf("1 2"))
        val state  = game.nextMove("1 2")
        val result = "Error: incorrect move"
        assertEquals(result, state)
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
        val game   = createSituation(arrayOf("1 2"))
        val state  = game.nextMove("2 2").removeSuffix("\nYour move, player1")
        val result = "   | x |   \n===+===+===\n   | o |   \n===+===+===\n   |   |   \n"
        assertEquals(result, state)
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
        val game  = createSituation(arrayOf("1 2", "2 2"))
        val state = game.nextMove("4 1")
        assertEquals("Error: incorrect data", state)
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
        val game = createSituation(arrayOf("1 2", "2 2"))
        val state  = game.nextMove("1 three")
        assertEquals("Error: incorrect data", state)
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
        val game = createSituation(arrayOf("1 2", "2 2", "1 1", "1 3", "3 1", "2 1", "2 3", "3 3"))
        val state  = game.nextMove("3 2").removeSuffix("\n\nEnter '/start' to start new game or '/exit' to exit")
        val result = " x | x | o \n===+===+===\n o | o | x \n===+===+===\n x | x | o \n\nDraw"
        assertEquals(result, state)
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
        val game   = createSituation(arrayOf("1 2", "1 3", "3 2", "2 2", "3 1", "3 3", "1 1"))
        val state  = game.nextMove("2 3").removeSuffix("\n\nEnter '/start' to start new game or '/exit' to exit")
        val result = " x | x | o \n===+===+===\n   | o | o \n===+===+===\n x | x | o \n\nOuths wins"
        assertEquals(result, state)
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
        val game   = createSituation(arrayOf("2 2", "1 2", "1 3", "3 1", "3 3", "2 3"))
        val state  = game.nextMove("1 1").removeSuffix("\n\nEnter '/start' to start new game or '/exit' to exit")
        val result = " x | o | x \n===+===+===\n   | x | o \n===+===+===\n o |   | x \n\nCrosses wins"
        assertEquals(result, state)
    }
}