package hw06

import javax.swing.*
import java.awt.*
import javax.swing.border.BevelBorder

/**
 * Created by Antropov Igor on 23.10.2015.
 */

class TicTacToeGUI : JFrame() {

    private val buttonsPane: JPanel

    private val menuStrip: JMenuBar
    private val gameMenu: JMenu
    private val newGame: JMenuItem

    private val statusPanel: JPanel
    private val statusLabel: JLabel

    private var isNotOver = false
    private var counter = true

    private var core = Core()

    init {
        title = "TicTacToe"
        setSize(350, 350)
        defaultCloseOperation = EXIT_ON_CLOSE
        contentPane.layout = BorderLayout()
        statusPanel = JPanel()
        //init strip label
        statusPanel.border = BevelBorder(BevelBorder.LOWERED)
        contentPane.add(statusPanel, BorderLayout.SOUTH)
        statusPanel.preferredSize = Dimension(contentPane.width, 16)
        statusPanel.layout = BoxLayout(statusPanel, BoxLayout.X_AXIS)
        statusLabel = JLabel("first player's turn")
        statusLabel.horizontalAlignment = SwingConstants.LEFT
        statusPanel.add(statusLabel)
        //init Menu
        menuStrip = JMenuBar()
        gameMenu = JMenu("game")
        newGame = JMenuItem("NewGame")
        newGame.addActionListener({ newGame() })
        gameMenu.add(newGame)
        menuStrip.add(gameMenu)
        jMenuBar = menuStrip
        //init buttons
        buttonsPane = JPanel()
        buttonsPane.layout = GridLayout(3, 3)
        buttonsPane.preferredSize = Dimension(300, 300)
        for (a in 0..2) {
            for (b in 0..2) {
                val btn = JButton("")
                btn.background = Color.GRAY
                btn.addActionListener({ buttonClick(btn, a, b) })
                buttonsPane.add(btn)
            }
        }
        contentPane.add(buttonsPane)
        //init parameters
        isVisible = true
        isNotOver = true
    }

    private fun buttonClick(btn: JButton, a: Int, b: Int) {
        if (isNotOver) {
            val error = core.getMark(a, b, counter)
            if (!error) {
                if (counter)
                    btn.background = Color.GREEN
                else
                    btn.background = Color.RED
                counter = !counter

                when (core.test()) {
                    'x' -> {
                        statusLabel.text = ("1st won")
                        isNotOver = false
                    }
                    'o' -> {
                        statusLabel.text = ("2nd won")
                        isNotOver = false
                    }
                    '3' -> {
                        statusLabel.text = ("Draw")
                        isNotOver = false
                    }
                    else -> if (counter) statusLabel.text = ("first player's turn")
                    else statusLabel.text = ("second player's turn")
                }
            } else {
                statusLabel.text = ("This cell is not empty. Chose another one.")
            }
        }
    }

    private fun newGame() {
        for (btn in buttonsPane.components)
            btn.background = Color.GRAY
        statusLabel.text = ("first player's turn")
        isNotOver = true
        core = Core()
        counter = true
    }
}

fun main(args: Array<String>) {
    TicTacToeGUI()
}