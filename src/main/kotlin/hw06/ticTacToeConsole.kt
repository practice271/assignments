package hw06

import java.io.*
import java.nio.charset.Charset

public class TicTacToeConsole : TicTacToe() {
    private fun display(outStream: Writer) {
        fun printCell(cell: Cell): String {
            when (cell) {
                Cell.EMPTY -> return "-"
                Cell.NOUGHT -> return "O"
                Cell.CROSS -> return "X"
            }
        }

        val str = "${printCell(field[0][0])} ${printCell(field[0][1])} ${printCell(field[0][2])}\n" +
                "${printCell(field[1][0])} ${printCell(field[1][1])} ${printCell(field[1][2])}\n" +
                "${printCell(field[2][0])} ${printCell(field[2][1])} ${printCell(field[2][2])}\n"
        outStream.write(str)
        outStream.flush()
    }

    public fun startGame(inputStream: Reader = InputStreamReader(System.`in`, Charset.forName("UTF-8")),
                         outStream: Writer = OutputStreamWriter(System.`out`)) {
        clear()
        val help = "To make a move write string in format 'x y' where x and y are equal 0, 1 or 2. " +
                "Write 'EXIT' to exit."
        outStream.write(help + "\n")
        outStream.flush()
        var bf = BufferedReader(inputStream)
        while (getWinner() == null) {
            val str = bf.readLine()
            if (str == "EXIT") break
            else if (str.length() != 3 || str[1] != ' ' ||
                    !Character.isDigit(str[0]) || !Character.isDigit(str[2])) {
                outStream.write("Wrong input format. " + help + "\n")
                outStream.flush()
            } else {
                var tryMove = tryMakeMove(Character.getNumericValue(str[0]), Character.getNumericValue(str[2]))
                if (tryMove == MoveResult.NON_EMPTY_CELL) {
                    outStream.write("Cell is not empty!\n")
                    outStream.flush()
                } else if (tryMove == MoveResult.NON_EXISTENT_CELL) {
                    outStream.write("This cell does not exist. " + help + "\n")
                    outStream.flush()
                } else {
                    display(outStream)
                    val winner = getWinner()
                    if (winner != null) {
                        outStream.write("Player " + (if (winner == Player.FIRST) "I" else "II") + " won!\n")
                        outStream.flush()
                    } else if (!containEmptyCell()) {
                        outStream.write("Draw!\n")
                        outStream.flush()
                        break
                    }
                }
            }
        }
    }
}

/*
fun main(args: Array<String>) {
    val game = TicTacToeConsole()

    game.startGame()
}
*/