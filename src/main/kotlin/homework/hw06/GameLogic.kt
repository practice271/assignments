/*
 * Homework 6 (19.10.2015)
 * Logical part of the tic-tac-toe game.
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw06

public class GameLogic() {
    /** Parser for input strings. */
    private class Parser(private val str : String) {
        private var x = 0
        private var y = 0
        private var correct = true

        init {
            when (str.length) {
                3 -> {
                    x = str[0].toInt() - 49
                    y = str[2].toInt() - 49
                    if (str[1] != ' ' || x > 2 || x < 0 || y > 2 || y < 0) correct = false
                }
                else -> correct = false
            }
        }

        /** Returns true if input data is correct. */
        public fun isCorrect() : Boolean {
            return correct
        }

        /** Returns first digit. */
        public fun getX() : Int {
            return x
        }

        /** Returns second digit. */
        public fun getY() : Int {
            return y
        }
    }

    private val field = Array(9, { i -> ' ' })

    /** Checks that given cell is empty. */
    private fun isEmpty(x : Int, y : Int) : Boolean {
        return (field[x * 3 + y] == ' ')
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
    public fun move(symbol : Char, str : String) : String {
        val p = Parser(str)
        val x = p.getX()
        val y = p.getY()

        if (!p.isCorrect() || (symbol != 'x' && symbol != 'o'))
            return "Error: incorrect data"
        if (isEmpty(x, y) && !isFinished())
            field[x * 3 + y] = symbol
        else
            return "Error: incorrect move"

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