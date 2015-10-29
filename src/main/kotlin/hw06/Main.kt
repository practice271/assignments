package hw06

import java.awt.Color
import java.awt.Font
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

/**
 * Created by Mikhail on 24.10.2015.
 */
object Console {

    public fun playConsole() {

        val game = TicTacToe()

        println("Welcome! Tic Tac Toe is a two player game.")

        var continuePlaying = true

        while (continuePlaying) {

            game.init()
            println()
            println(game.drawBoard())
            println()

            var player: String = " "
            while (!game.winner() && game.plays < 9) {
                player = if (game.currentPlayer == 1) game.player1 else game.player2
                var validPick = false
                while (!validPick) {
                    print("It is $player's turn. Pick a square: ")
                    val square = game.prompt
                    if (square.length == 1 && Character.isDigit(square.toCharArray()[0])) {
                        var pick: Int
                        pick = Integer.parseInt(square)
                        validPick = game.placeMarker(pick)
                    }
                    if (!validPick) {
                        println("Square can not be selected. Retry")
                    }
                }
                game.switchPlayers()
                println()
                println(game.drawBoard())
                println()
            }

            if (game.winner()) {
                println("Game Over - $player WINS!!!")
            } else {
                println("Game Over - Draw")
            }

            println()
            print("Play again? (Y/N): ")
            val choice = game.prompt
            if (!choice.equals("Y")) {
                continuePlaying = false
            }
        }
    }
}

object GUI {

    val game = TicTacToe()
    val buttons = Array(3, { Array(3, { JButton() }) })

    public fun gamePanel() {

        val frame = JFrame("Tic Tac Toe")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        game.init()
        val panel = JPanel()
        panel.layout = GridLayout(3, 3)
        panel.border = BorderFactory.createLineBorder(Color.gray)
        panel.background = Color.white
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j] = MyButton()
                panel.add(buttons[i][j])
            }
        }
        frame.contentPane.add(panel)
        frame.isVisible = true
        frame.setSize(500, 500)
    }

    private class MyButton: JButton(), ActionListener {
        internal var again = 1000
        internal var letter: String

        init {
            letter = " "
            font = Font("Dialog", 1, 60)
            text = letter
            addActionListener(this)
        }

        override fun actionPerformed(e: ActionEvent) {

            if ((game.currentPlayer == 1 && text == " " && !game.winner())) {
                letter = "X"
                game.switchPlayers()
            } else if (game.currentPlayer == 2 && text == " " && !game.winner()) {
                letter = "O"
                game.switchPlayers()
            }

            text = letter
            for (i in 0..2) {
                for (j in 0..2) {
                    game.board[i][j] = buttons[i][j].text.toCharArray() [0]
                }
            }

            if (game.winner()) {
                again = JOptionPane.showConfirmDialog(null, letter + " wins the game!  Do you want to play again?", letter + "won!", JOptionPane.YES_NO_OPTION)
                clearButtons()
            } else if (game.plays == 9 && !game.winner()) {
                again = JOptionPane.showConfirmDialog(null, "The game was tie!  Do you want to play again?", "Tie game!", JOptionPane.YES_NO_OPTION)
                clearButtons()
            }
            if (again == JOptionPane.NO_OPTION) {
                System.exit(0)
            }
        }
    }
    private fun clearButtons() {
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].text = " "
            }
        }
        game.init()
    }
}

fun main(args: Array<String>) {
    //val main = Console
    //main.playConsole()
    val main = GUI
    main.gamePanel()
}