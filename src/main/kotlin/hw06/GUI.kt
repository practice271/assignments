package hw06

import java.awt.*
import javax.swing.*

public class GUI : JFrame()
{
    private val newGameBtn : JButton
    private val btnPanel : JPanel
    private val label : JLabel

    private val game : Game

    init
    {
        game = Game()
        game.newGame()

        title = "Tic-Tac-Toe"
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setSize(350, 350)

        contentPane.layout = GridBagLayout()
        val constraints = GridBagConstraints()

        newGameBtn = JButton("New Game")
        newGameBtn.addActionListener({newGame()})
        constraints.gridx = 0
        constraints.gridy = 0
        contentPane.add(newGameBtn, constraints)

        btnPanel = JPanel()
        btnPanel.layout = GridLayout(3, 3)
        btnPanel.preferredSize = Dimension(200, 200)
        for (i in 0..2)
            for (j in 0..2)
            {
                val btn = JButton("")
                btn.addActionListener({ makeTurn(i, j)})
                btn.isEnabled = false
                btnPanel.add(btn)
            }
        constraints.gridy = 1
        contentPane.add(btnPanel, constraints)

        label = JLabel()
        constraints.gridy = 2
        contentPane.add(label, constraints)

        isVisible = true
    }

    private fun newGame()
    {
        game.newGame()
        updateField()
        for (i in 0..2)
            for (j in 0..2)
            {
                val btn : JButton = btnPanel.getComponent(i * 3 + j) as JButton
                btn.isEnabled = true
            }
        label.text = ""
    }

    private fun makeTurn(x : Int, y : Int)
    {
        game.makeTurn(x, y)
        updateField()
    }

    private fun updateField()
    {
        val field = game.getField()

        for (i in 0..2)
            for (j in 0..2)
            {
                val btn : JButton = btnPanel.getComponent(i * 3 + j) as JButton
                btn.text =
                        if (field[i][j] == '*') "" else field[i][j].toString()
                if (game.endOfGame()) btn.isEnabled = false
            }

        if (game.endOfGame()) label.text = "Game Over!"
    }
}

fun main (args : Array<String>)
{
    GUI()
}