package homework.hw06

import java.awt.EventQueue
import java.awt.GridLayout
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel

public class DesktopGame() {

    private var screen = JFrame()
    private val buttons = Array(9, { JButton(" ") })
    private var status = JLabel("")
    private val logic = GameLogic()

    fun start() {
        EventQueue.invokeLater(object : Runnable {

            override fun run() {
                draw()
            }
        })
    }

    private fun draw() {

        val newGameButton = JButton("New Game")

        for (i in 0..8) {
            buttons[i].addActionListener() {
                if (logic.crossTurn) {
                    logic.crossTurn = false
                    buttons[i].text = "x"
                    logic.insert('x', i)

                } else {
                    logic.crossTurn = true
                    buttons[i].text = "o"
                    logic.insert('o', i)

                }

                buttons[i].isEnabled = false

                val answer = logic.isEnd()
                when (answer) {
                    "Draw" -> status.text = "Draw"
                    "" -> null
                    else -> {
                        status.text = answer
                        for (but in buttons) but.isEnabled = false
                    }
                }
            }

        }

        newGameButton.addActionListener() { startState() }

        screen.title = "Tic - Tac - Toe"
        screen.setSize(310, 310)
        screen.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        screen.setLocationRelativeTo(null)

        screen.contentPane.layout = GridLayout(0, 3)
        for (button in buttons) screen.contentPane.add(button)
        screen.add(status)
        screen.add(JLabel(" ")) // Empty cell
        screen.add(newGameButton)

        screen.isVisible = true

    }

    private fun startState() {
        status.text = " "
        logic.restart()
        for (but in buttons) {
            but.isEnabled = true
            but.text = " "
        }
    }
}

fun main(args : Array<String>){
    val x = DesktopGame()
    x.start()
}
