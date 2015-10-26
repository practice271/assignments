package homework.hw06.gui

import homework.hw06.Core
import java.awt.*
import javax.swing.*
import javax.swing.JFrame.CENTER_ALIGNMENT

class TicTacToeGUI : JFrame() {
    private val windowWidth = 350
    private val windowHeight = 450
    private val gridSize = Core.gridSize

    private val buttonRestart : JButton
    private val labelStatus : JLabel
    private val buttonsPane : JPanel

    private var crossesMove = true
    private var isGameActive = false

    init {
        // overall window settings
        title = "TicTacToe GUI"
        setSize(windowWidth, windowHeight)
        isResizable = false
        setLocationRelativeTo(null)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        // preparing content pane
        contentPane.layout = GridBagLayout()
        val constraints = GridBagConstraints()

        // setting up restart button
        buttonRestart = JButton("Restart")
        buttonRestart.addActionListener({ restart() })
        buttonRestart.font = Font("Serif", Font.PLAIN, 18)
        constraints.gridx = 0
        constraints.gridy = 0
        buttonRestart.alignmentX = CENTER_ALIGNMENT
        contentPane.add(buttonRestart, constraints)
        // setting up status label
        labelStatus = JLabel("Crosses move", SwingConstants.CENTER)
        labelStatus.font = Font("Serif", Font.BOLD, 20)
        constraints.gridx = 0
        constraints.gridy = 1
        constraints.ipady = 20
        contentPane.add(labelStatus, constraints)
        // setting up grid buttons
        buttonsPane = JPanel()
        buttonsPane.layout = GridLayout(gridSize, gridSize)
        buttonsPane.preferredSize = Dimension(300, 300)
        val font = Font("Monospace", Font.PLAIN, 54)
        for (i in 0..gridSize - 1) {
            for (j in 0..gridSize - 1) {
                val b = JButton("")
                b.font = font
                b.addActionListener({ buttonClick(i, j) })
                buttonsPane.add(b)
            }
        }
        constraints.gridx = 0
        constraints.gridy = 2
        constraints.ipadx = 10
        constraints.ipady = 10
        contentPane.add(buttonsPane, constraints)
        // showing the window
        isVisible = true
        // starting the game
        isGameActive = true
    }

    /**
     * Restarts the game.
     */
    private fun restart() {
        Core.instance.restart()
        labelStatus.text = "Crosses move"
        for (b in buttonsPane.components) {
            (b as JButton).text = ""
        }
        crossesMove = true
        isGameActive = true
    }

    /**
     * Event handler for one of the main buttons with coordinates [[x], [y]].
     */
    private fun buttonClick(x : Int, y : Int) {
        if (!isGameActive) return
        when (Core.instance.makeMove(x, y)) {
            Core.ErrorCode.OK -> {
                // the game continues
                updateButtonContent(x, y)
                crossesMove = !crossesMove
            }
            Core.ErrorCode.WIN -> {
                // one of the players wins
                updateButtonContent(x, y)
                labelStatus.text = "${if (crossesMove) "Crosses" else "Noughts"} win!"
                isGameActive = false
                return
            }
            Core.ErrorCode.DRAW -> {
                // a draw
                updateButtonContent(x, y)
                labelStatus.text = "The game is a draw!"
                isGameActive = false
                return
            }
            Core.ErrorCode.ERROR -> {
            }
        }
    }

    /**
     * Marks the button [[x], [y]] with proper sign the player has 'put' there.
     */
    private fun updateButtonContent(x : Int, y : Int) {
        (buttonsPane.getComponent(x * gridSize + y) as JButton).text = if (crossesMove) "X" else "O"
    }
}

fun main(args : Array<String>) {
    TicTacToeGUI()
}