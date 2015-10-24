/*
 * Homework 6 (19.10.2015)
 * Tests for game interface
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw06

import org.junit.Test
import kotlin.test.assertEquals
import org.junit.Assert.assertArrayEquals

public class hw06GameLogicTest {
    /** Creates game situation using given array. */
    private fun createSituation(arr : Array<Char>) : GameLogic {
        val game = GameLogic()
        for (i in 0 .. 8) {
            val temp = (i.div(3) + 1).toString() + " " + (i.mod(3) + 1).toString()
            game.move(arr[i], temp)
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
        val game   = GameLogic()
        val state  = game.move('x', "1 2")
        val field  = game.getField()
        val result = arrayOf(' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ')

        assertEquals("ok", state)
        assertArrayEquals(result, field)
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
        val game  = createSituation(arrayOf(' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' '))
        val state = game.move('o', "1 2")
        assertEquals("Error: incorrect move", state)
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
        val game   = createSituation(arrayOf(' ', 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' '))
        val state  = game.move('o', "2 2")
        val field  = game.getField()
        val result = arrayOf(' ', 'x', ' ', ' ', 'o', ' ', ' ', ' ', ' ')

        assertEquals("ok", state)
        assertArrayEquals(result, field)
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
        val game  = createSituation(arrayOf(' ', 'x', ' ', ' ', 'o', ' ', ' ', ' ', ' '))
        val state = game.move('x', "4 1")
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
        val game  = createSituation(arrayOf(' ', 'x', ' ', ' ', 'o', ' ', ' ', ' ', ' '))
        val state = game.move('x', "1 three")
        assertEquals("Error: incorrect data", state)
    }

    /** First player puts 'x' into 3 2
     *
     *   x | x | o        x | x | o
     *  ===+===+===      ===+===+===
     *   o | o | x   ->   o | o | x
     *  ===+===+===      ===+===+===
     *   x |   | o        x | x | o
     */
    @Test fun test06() {
        val game   = createSituation(arrayOf('x', 'x', 'o', 'o', 'o', 'x', 'x', ' ', 'o'))
        val state  = game.move('x', "3 2")
        val field  = game.getField()
        val result = arrayOf('x', 'x', 'o', 'o', 'o', 'x', 'x', 'x', 'o')

        assertEquals("Draw", state)
        assertArrayEquals(result, field)
    }

    /** Second player puts 'o' into 2 3
     *
     *   x | x | o        x | x | o
     *  ===+===+===      ===+===+===
     *     | o |     ->     | o | o
     *  ===+===+===      ===+===+===
     *   x | x | o        x | x | o
     */
    @Test fun test07() {
        val game   = createSituation(arrayOf('x', 'x', 'o', ' ', 'o', ' ', 'x', 'x', 'o'))
        val state  = game.move('o', "2 3")
        val field  = game.getField()
        val result = arrayOf('x', 'x', 'o', ' ', 'o', 'o', 'x', 'x', 'o')

        assertEquals("Ouths wins", state)
        assertArrayEquals(result, field)
    }

    /** First player puts 'x' into 1 1
     *
     *     | o | x        x | o | x
     *  ===+===+===      ===+===+===
     *     | x | o   ->     | x | o
     *  ===+===+===      ===+===+===
     *   o |   | x        o |   | x
     */
    @Test fun test08() {
        val game   = createSituation(arrayOf(' ', 'o', 'x', ' ', 'x', 'o', 'o', ' ', 'x'))
        val state  = game.move('x', "1 1")
        val field  = game.getField()
        val result = arrayOf('x', 'o', 'x', ' ', 'x', 'o', 'o', ' ', 'x')

        assertEquals("Crosses wins", state)
        assertArrayEquals(result, field)
    }
}