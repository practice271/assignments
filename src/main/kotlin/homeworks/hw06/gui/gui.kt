package homeworks.hw06.gui

import homeworks.hw06.logic.Logic
import javax.swing.*
import java.awt.*

/**
 * Created by Ilya on 24.10.2015.
 */

private class Frame: JFrame {
    private val ticTacToe = TicTacToe()
    constructor() {
        createGUI()
    }
    private val WIDTH = 500
    private val HEIGHT = 400

    private fun createGUI() {
        var box = Box.createVerticalBox()
        //component label
        var head = JLabel("STEP OF TIC")
        head.font = Font("Serief", Font.BOLD, 17)
        box.add(head)
        //component table
        var panel = JPanel()
        panel.layout = GridLayout(3, 3)
        var buttons = Array(3, { Array(3, { JButton() }) })
        var maxCount = 0
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].font = Font("Serief", Font.BOLD, 17)
                buttons[i][j].addActionListener({
                    fun offAll() {
                        for (i in 0..2) {
                            for (j in 0..2) {
                                buttons[i][j].isEnabled = false
                            }
                        }
                    }
                    if (ticTacToe.player == 1) {
                        ticTacToe.player = 2
                        buttons[i][j].text = "X"
                        ticTacToe.array[i][j] = "X"
                        buttons[i][j].isEnabled = false
                        if (ticTacToe.checkResult(1)) {
                            head.text = "Player 1 with tic win!!! Please, start new game!"
                            offAll()
                        } else {
                            head.text = "STEP OF TAC"
                        }
                    } else {
                        ticTacToe.player = 1
                        buttons[i][j].text = "O"
                        ticTacToe.array[i][j] = "O"
                        buttons[i][j].isEnabled = false
                        if (ticTacToe.checkResult(2)) {
                            head.text = "Player 2 with tac win!!! Please, start new game!"
                            offAll()
                        } else {
                            head.text = "STEP OF TIC"
                        }
                    }
                    maxCount++
                    if (maxCount == 9) {
                        head.text = "It's tie! Please, start new game!"
                    }
                })
                panel.add(buttons[i][j])
            }
        }
        box.add(panel)
        //component button "restart"
        val restart = JButton("Start new game")
        restart.preferredSize
        restart.addActionListener({
            ticTacToe.gameStart()
            head.text = "STEP OF TIC"
            for (i in 0..2) {
                for (j in 0..2) {
                    buttons[i][j].text = ""
                    buttons[i][j].isEnabled = true
                }
            }

        })
        box.add(restart)

        setSize(WIDTH, HEIGHT)
        title = "Tic Tac Toe"
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        isVisible = true
        contentPane = box
    }

    private class TicTacToe: Logic() {
        internal var player = 1
        public fun gameStart() {
            for (i in 0..2) {
                for (j in 0..2) {
                    array[i][j] = "null"
                }
            }
        }
        public fun checkResult(player: Int): Boolean {
            var char: String
            if (player == 1) {
                char = "X"
            } else {
                char = "O"
            }
            return checkSuccess(char)
        }
    }
}

fun main(args: Array<String>) {
    Frame()
}
