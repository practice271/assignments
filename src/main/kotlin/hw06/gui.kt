package hw06

import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

/*
* Tic-Tac-Toe GUI
* */

public class GameGUI(): JFrame() {
    private val gameLogic = TLogic()
    private val frame = JFrame("Tic-Tac-Toe")
    private val panel = JPanel()
    private val bTable = JPanel()
    private val label = JLabel("Star the game, player 1!")
    private val buttons = Array(9, { JButton("") })
    private val restart = JButton("Restart")
    private val wdt = 300
    private val hght = 350
    private var turnCount = 0
    private var isOver = false

    init {
        frame.contentPane.add(panel, BorderLayout.CENTER)
        frame.preferredSize = Dimension(wdt, hght)
        frame.pack()
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        label.font = Font("Calibri", Font.ITALIC, 22)
        label.alignmentX = CENTER_ALIGNMENT

        bTable.layout = GridLayout(3, 3)

        panel.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        panel.add(label)
        panel.add(bTable)
        panel.add(restart)

        restart.alignmentX = CENTER_ALIGNMENT
        restart.addActionListener(object: ActionListener {
            override fun actionPerformed(e: ActionEvent?) {
                gameLogic.reset()
                turnCount = 0
                buttons.forEach { it.text = "" }
                label.text = "Start the game, player 1!"
                if(isOver) {
                    buttons.forEach { it.isEnabled = true }
                    isOver = false
                }
            }
        })

        // buttons configuration
        for (i in 0..8) {
            buttons[i].addActionListener(object : ActionListener {
                override fun actionPerformed(e: ActionEvent?) {
                    val idX = i / 3 // "coordinates" of the current button on the field
                    val idY = i % 3
                    var symbol = if(gameLogic.getPlayer() == 1) "X" else "O"
                    var moved = gameLogic.move(idX, idY)
                    if (turnCount >= 4 && moved) { // it takes min 5 moves to win
                        if (gameLogic.checkWin()) { // game is over
                            label.text = "Congratulations! ${symbol}s Won!"
                            buttons[i].text = symbol
                            buttons.forEach { it.isEnabled = false }
                            isOver = true
                            return
                        }
                    }
                    if (moved) {
                        buttons[i].text = symbol
                        turnCount++
                        label.text = "Make a move, player ${gameLogic.getPlayer()}!"
                    } else
                        label.text = "No, try again, player ${gameLogic.getPlayer()}"
                    if(turnCount == 9) {
                        label.text = "It's a tie!"
                        buttons.forEach { it.isEnabled = false }
                    }
                }
            })

            buttons[i].font = Font("Calibri", Font.PLAIN, 33)
            bTable.add(buttons[i])
        }
    }


    public fun play() {
        frame.isVisible = true
    }
}

fun main(args: Array<String>) {
    GameGUI().play()
}
