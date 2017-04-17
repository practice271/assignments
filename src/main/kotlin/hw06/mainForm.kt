/* The tic-tac-toe game. Console and GUI interfaces.
(expectation: 5 h; reality: 8 h)
by Sokolova Polina */

package hw06

import javax.swing.*
import java.awt.event.*
import java.awt.*

class MainForm : JFrame(), ActionListener {
    private object GUI : Logic() {}
    internal val buttons : Array<Array<JButton>> = Array(3, { Array(3, { JButton() }) })
    private val playButton = JButton("Play")
    private val statusLabel = JLabel("")
    init {
        title = "Tic-Tac-Toe game"
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        isResizable = true
        val centerPanel = JPanel(GridLayout(3, 3))
        val font = Font("Arial", Font.BOLD, 32)
        buttons.forEach { JButton("")  }
        for (i in 0..2)
            for (j in 0..2) {
                buttons[i][j].font = font
                buttons[i][j].addActionListener(this)
                buttons[i][j].isFocusable = false
                centerPanel.add(buttons[i][j])
            }
        playButton.addActionListener(this);
        val northPanel = JPanel()
        northPanel.add(statusLabel)
        val southPanel = JPanel()
        add(northPanel, "North")
        add(centerPanel, "Center")
        add(southPanel, "South")
        northPanel.background = Color.cyan
        southPanel.background = Color.cyan
        centerPanel.background = Color.cyan
        southPanel.add(playButton)
        setSize(300, 350)
        setLocationRelativeTo(null) //to center
    }
    private fun setButtonsEnabled(enabled : Boolean) {
        for(i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].isEnabled = enabled;
                if (enabled) { buttons[i][j].text = " " }
            }
        }
    }
    override fun actionPerformed(event: ActionEvent) {
        if (event.source == playButton) {
            GUI.clear()
            statusLabel.text = ""
            setButtonsEnabled(true)
            remove(playButton)
        }
        else {
            for (i in 0..2) {
                for (j in 0..2) {
                    if (event.source == buttons[i][j]) {
                        val add = GUI.add(i, j)
                        val sign =
                                when (GUI.array[i][j]) {
                                    1 -> "X"
                                    2 -> "O"
                                    else -> ""
                                }

                        when (add) {
                            0 -> {
                                buttons[i][j].text = sign
                                if (GUI.turn == 9) {
                                    statusLabel.text = "Draw!"
                                    setButtonsEnabled(false)
                                }
                            }
                            1 -> {
                                buttons[i][j].text = sign
                                statusLabel.text = "Tic won!"
                                setButtonsEnabled(false)
                            }
                            2 -> {
                                buttons[i][j].text = sign
                                statusLabel.text = "Toe won!"
                                setButtonsEnabled(false)
                            }
                        }
                    }
                }
            }
        }
    }
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            GUI.clear()
            MainForm().isVisible = true
        }
    }
}
