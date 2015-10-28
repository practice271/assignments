package homework6

import java.awt.*
import javax.swing.*

public class GUI() {

    private val buttons = Array(3, { Array(3, { JButton("") }) })
    private val frame = JFrame("Tic-Tac-Toe")
    private val menubar = JMenuBar()
    private val menu = JMenu("Menu")
    private val newgame = JMenuItem("New Game")
    private val panel = JPanel()
    private var game = LogicGames()

    init {
        menubar.add(menu)
        menu.add(newgame)
        newgame.addActionListener { newGame() }
        frame.jMenuBar = menubar

        frame.add(panel)
        panel.setLayout(GridLayout(3, 3))

        for (i in 0..2) {
            for (j in 0..2) {
                panel.add(buttons[i][j])
                buttons[i][j].font = Font("Dialog", Font.PLAIN, 50)
                buttons[i][j].addActionListener {
                    if (buttons[i][j].text == "") {
                        game.move(i, j)
                        game.checkState()
                        buttons[i][j].text = game.field[i][j].toString()
                        checkWin()
                        buttons[i][j].isEnabled = false
                    }
                }
            }
        }
        frame.setSize(400, 400)
        frame.setVisible(true)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    }

    private fun checkWin() {
        var message: String
        if (!(game.state == LogicGames.State.Win) && !(game.state == LogicGames.State.Standoff)) {
        } else {
            if (game.state == LogicGames.State.Win) message = game.winner + " win!"
            else message = "Standoff!"
            JOptionPane.showMessageDialog(null, message)
            for (i in 0..2) {
                for (j in 0..2) {
                    buttons[i][j].setEnabled(false)
                }
            }
        }
    }

    private fun newGame() {
        game = LogicGames()
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].text = ""
                buttons[i][j].isEnabled = true
            }
        }
    }
}
/*
fun main(args: Array<String>) {
    GUI()
}
*/