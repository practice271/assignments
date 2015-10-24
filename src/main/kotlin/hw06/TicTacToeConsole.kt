package hw06

import java.util.*

public class TicTacToeConsole : TicTacToeLogic() {
    private val marksLook = arrayOf('x', 'o', ' ')
    public fun gameStart() {
        println("Greetings, player.\n")
        gameOnFlag = true
        while (gameOnFlag) {
            val input = getMark()
            setMark(input[0], input[1], input[2])
        }
    }

    fun drawField() {
        for (i in 0..size - 1) {
            for (j in 0..size - 1)
                print("${marksLook[field[i][j]]} ")
            println()
        }
    }

    fun getMark(): Array<Int> {
        val input = Scanner(System.`in`);
        lastScanned %= 2
        if (!input.hasNextInt()) return arrayOf(-1, -1, 2) //negative indices mean that there's been a mistake
        return arrayOf(input.nextInt(), input.nextInt(), lastScanned++)
    }

    override fun gameOver (mark : Int) {
        gameOnFlag = false

        println("${marksLook[mark]} wins!")
    }

    fun setMark(i: Int, j: Int, mark: Int) : String {
        if (i < 0 || j < 0 || i >= size || j >= size) {
            val  error = "Incorrect input, try again!"
            println(error)
            return error
        }
        if (field[i][j] != 2) {
            val error = "You can't overwrite marks!"
            lastScanned = (lastScanned + 1) % 2
                //next move should be for the same mark as this one
                //it means lastScanned--, but I do it this way so we don't go negative.
            println(error)
            return error
        }
        field[i][j] = mark
        drawField()
        checkVictory(i, j, mark)
        return ""
    }
}
