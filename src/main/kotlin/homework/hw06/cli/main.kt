package homework.hw06.cli

import homework.hw06.Core
import homework.hw06.Core.ErrorCode
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.Reader
import java.io.Writer

/**
 * TicTacToe command-line interface.
 * @author Kirill Smirenko, group 271
 */

internal fun loop(reader : BufferedReader, writer : Writer) {
    var crossesMove = true
    while (true) {
        printGrid(writer)
        //print("${if (crossesMove) "Crosses" else "Noughts"} move: ")
        writer.write("${if (crossesMove) "Crosses" else "Noughts"} move: ")
        writer.flush()
        val input = reader.readLine() ?: ""
        if (input.equals("")) continue
        try {
            val parsedInput = input.split(' ')
            val x = Integer.parseInt(parsedInput[0])
            val y = Integer.parseInt(parsedInput[1])
            when (Core.instance.makeMove(x, y)) {
                ErrorCode.OK -> {
                    // the game continues
                    crossesMove = !crossesMove
                }
                ErrorCode.WIN -> {
                    // one of the players wins
                    printGrid(writer)
                    //println("${if (crossesMove) "Crosses" else "Noughts"} win!")
                    writer.write("${if (crossesMove) "Crosses" else "Noughts"} win!\n")
                    writer.flush()
                    return
                }
                ErrorCode.DRAW -> {
                    // a draw
                    printGrid(writer)
                    //println("The game is a draw!")
                    writer.write("The game is a draw!\n")
                    writer.flush()
                    return
                }
                ErrorCode.ERROR -> {
                    // incorrect coors were sent to core
                    //println("Incorrect coors! Please try again.")
                    writer.write("Incorrect coors! Please try again.\n")
                    writer.flush()
                }
            }
        } catch (e : Exception) {
            //println("Wrong format! Please type the coors like this: x y")
            writer.write("Wrong format! Please type the coors like this: x y\n")
            writer.flush()
        }
    }
}

fun printGrid(writer : Writer) {
    val gridSize = Core.gridSize
    for (i in 0..gridSize - 1) {
        for (j in 0..gridSize - 1) {
            when (Core.instance[i, j]) {
                Core.cellNone -> writer.write(". ")
                Core.cellX    -> writer.write("X ")
                Core.cellO    -> writer.write("O ")
                else -> throw Exception("Core is surely drunk.")
            }
        }
        writer.write("\n")
        //println()
    }
    writer.flush()
}

fun main(args: Array<String>) {
    loop(System.`in`.bufferedReader("UTF-8"), System.out.bufferedWriter("UTF-8"))
}