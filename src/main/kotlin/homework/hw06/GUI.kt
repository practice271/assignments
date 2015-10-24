/*
 * Homework 6 (19.10.2015)
 * Graphic version of the game.
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw06

import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

/** Implementation of graphic version of the game.*/
public class Game() : JFrame() {
    public fun createGUI() {
        var game   = GameLogic()
        var symbol = "x"

        // initializing grid
        val grid    = JPanel()
        grid.layout = GridLayout(3, 3)

        // initializing label
        val label  = JLabel("Your move, player1")
        label.font = Font("Calibri", 2, 27)
        label.border = BorderFactory.createEmptyBorder(20, 20, 20, 20)
        label.alignmentX = CENTER_ALIGNMENT
        label.alignmentY = CENTER_ALIGNMENT

        // initializing restart button
        val restart  = JButton("Restart")
        restart.font = Font("Calibri", 0, 23)
        restart.isEnabled = false
        restart.alignmentX = CENTER_ALIGNMENT
        restart.alignmentY = CENTER_ALIGNMENT

        restart.addActionListener(object : ActionListener {
            override fun actionPerformed(e: ActionEvent) {
                game = GameLogic()
                for (i in 0 .. 8) {
                    val elem = grid.getComponent(i)
                    if (elem is JButton) elem.text = " "
                }
                label.text = "Your move, player1"
                restart.isEnabled = false
            }
        })

        // adding buttons on grid
        for (i in 0..8) {
            val button = JButton(" ")
            button.font = Font("Calibri", 1, 42)

            button.addActionListener(object : ActionListener {
                override fun actionPerformed(e: ActionEvent) {
                    if (button.text == " ") {
                        val state = game.move(symbol[0], i.div(3) + 1, i.mod(3) + 1)
                        if (state == "ok") {
                            button.text = symbol
                            when (symbol) {
                                "x" -> {
                                    symbol = "o"
                                    label.text = "Your move, player2"
                                }
                                else -> {
                                    symbol = "x"
                                    label.text = "Your move, player1"
                                }
                            }
                        }
                        else if (!state.startsWith("Error")) {
                            button.text = symbol
                            label.text = state
                            restart.isEnabled = true
                        }
                    }
                }
            })
            grid.add(button)
        }

        // initializing panel
        val panel = JPanel()
        panel.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        panel.add(grid)
        panel.add(label)
        panel.add(restart)

        // initializing frame
        val frame  = JFrame("Tic-Tac-Toe Game")
        val width  = 400
        val height = 500

        frame.layout = BorderLayout()
        frame.contentPane.add(panel, BorderLayout.CENTER)
        frame.preferredSize = Dimension(width, height)
        frame.pack()
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
        frame.preferredSize = Dimension(width, height)
        frame.maximumSize = Dimension(width, height)
        frame.minimumSize = Dimension(width, height)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    }
}

fun main (args : Array<String>) {
    val game = Game()
    game.createGUI()
}