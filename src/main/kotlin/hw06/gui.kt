package hw06


import javax.swing.*
import java.awt.*
import javax.swing.border.BevelBorder

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
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        contentPane.layout = BorderLayout()

        statusPanel = JPanel()

        statusPanel.border = BevelBorder(BevelBorder.LOWERED)
        contentPane.add(statusPanel, BorderLayout.SOUTH)
        statusPanel.preferredSize = Dimension(contentPane.width, 16)
        statusPanel.layout = BoxLayout(statusPanel, BoxLayout.X_AXIS)
        statusLabel = JLabel("first player's turn")
        statusLabel.horizontalAlignment = SwingConstants.LEFT
        statusPanel.add(statusLabel)

        menuStrip = JMenuBar()
        gameMenu = JMenu("game")
        newGame = JMenuItem("NewGame")
        newGame.addActionListener({ newGame() })
        gameMenu.add(newGame)
        menuStrip.add(gameMenu)
        jMenuBar = menuStrip

        buttonsPane = JPanel()
        buttonsPane.layout = GridLayout(3, 3)
        buttonsPane.preferredSize = Dimension(300, 300)

        for (a in 0..2) {
            for (b in 0..2) {
                val btn = JButton("")
                btn.background = Color.GRAY
                btn.addActionListener({ buttonClick(a, b) })
                buttonsPane.add(btn)
            }
        }
        contentPane.add(buttonsPane)

        isVisible = true

        isNotOver = true

    }

    private fun buttonClick(a: Int, b: Int) {
        if (isNotOver) {
            core.getMark(a, b, counter)
            if (counter)
                (buttonsPane.getComponent(a * 3 + b) as JButton).background = Color.GREEN
            else
                (buttonsPane.getComponent(a * 3 + b) as JButton).background = Color.RED
            counter = !counter
        }
        when (core.test()) {
            'x' -> {
                statusLabel.text = ("1st won"); isNotOver = false
            }
            'o' -> {
                statusLabel.text = ("2nd won"); isNotOver = false
            }
            '3' -> {
                statusLabel.text = ("draw"); isNotOver = false
            }
            else -> if (counter) statusLabel.text = ("first player's turn")
                    else statusLabel.text = ("second player's turn")
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