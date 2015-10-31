/*
 * Homework 6 (20.10.2015)
 * Logical part of the tic-tac-toe game.
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw06

/** Contains main logic of the game. */
public class GameLogic() {
    private val field = Array(9, { ' ' })

    /** Checks that input data is correct. */
    private fun isCorrect(symbol : Char, x : Int, y : Int) : Boolean {
        return (x > 0 && x < 4 && y > 0 && y < 4 && (symbol == 'x' || symbol == 'o'))
    }

    /** Checks that given cell is empty. */
    private fun isEmpty(x : Int, y : Int) : Boolean {
        return (field[(x - 1) * 3 + (y - 1)] == ' ')
    }

    /** Checks that field contains spaces. */
    private fun isFilled() : Boolean {
        return (!field.contains(' '))
    }

    /** Checks that game is finished. */
    private fun isFinished() : Boolean {
        return (win('x') || win('o') || isFilled())
    }

    /** Checks that given symbol won. */
    private fun win(symbol : Char) : Boolean {
        fun check(x : Int, y : Int, z : Int) : Boolean {
            return (field[x] == symbol && field[y] == symbol && field[z] == symbol)
        }

        var ok = false
        for (i in 0 .. 2) {
            ok = ok || check(i * 3, i * 3 + 1, i * 3 + 2) // checks all rows
                    || check(i, i + 3, i + 6) // checks all columns
        }
        return (ok || check(0,4,8) || check(2,4,6))
    }

    /** Tries to put symbol into given cell and processes errors. */
    public fun move(symbol : Char, x : Int, y : Int) : String {
        if (!isCorrect(symbol, x, y)) return "Error: incorrect data"

        if (isEmpty(x, y) && !isFinished()) field[(x - 1) * 3 + y - 1] = symbol
        else return "Error: incorrect move"

        if (win('x')) return "Crosses wins"
        if (win('o')) return "Ouths wins"
        if (isFilled()) return "Draw"
        return "ok"
    }

    /** Returns game field. */
    public fun getField() : Array<Char> {
        return field
    }
}