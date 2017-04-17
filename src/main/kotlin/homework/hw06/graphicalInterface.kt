package homework.hw06

import java.awt.*
import javax.swing.*

public class TicTacToeGame : JFrame() {
    private val WIDTH = 500
    private val HEIGHT = 400
    private val newGameBtn = JButton("New game")
    private val exitBtn = JButton("Exit")
    private val status = JLabel("")
    private val pane = contentPane
    private val temp = GridBagConstraints()
    private val fieldBtn = Array(3,{Array(3,{JButton()})})
    init {
        title = "The tic-tac-toe game"
        pane.layout = GridBagLayout()
        newGameBtn.addActionListener({restart()})
        exitBtn.addActionListener({System.exit(0)})
        for (i in 0..2) {
            for (j in 0..2) {
                fieldBtn[i][j] = JButton()
                fieldBtn[i][j].addActionListener({setMark(i,j)})
                fieldBtn[i][j].isFocusable = false
                fieldBtn[i][j].preferredSize = Dimension(60, 60)
                temp.gridx = j
                temp.gridy = i
                pane.add(fieldBtn[i][j], temp)
            }
        }
        temp.insets = Insets(30,0,0,0)
        temp.gridwidth = 3
        temp.gridx = 0
        temp.gridy = 3
        pane.add(status, temp)
        temp.gridwidth = 2
        temp.gridx = 0
        temp.gridy = 4
        pane.add(newGameBtn, temp)
        temp.gridwidth = 1
        temp.gridx = 2
        temp.gridy = 4
        pane.add(exitBtn, temp)
        setSize(WIDTH, HEIGHT)
        isResizable = false
        isVisible = true
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    }
    private fun setMark(x : Int, y : Int) {
        placeMark(x,y)
        fieldBtn[x][y].isEnabled = false
        for (i in 0..2) {
            for (j in 0..2) {
                fieldBtn[i][j].text = field[i][j]
                if (gameOver) fieldBtn[i][j].isEnabled = false
            }
        }
        if (checkForWin()) status.text = "Player" + playerMark.toString() + " wins!"
        else if (boardIsFull()) status.text = "Draw!"
    }
    private fun restart() {
        newGame()
        status.text = ""
        for (i in 0..2) {
            for (j in 0..2) {
                fieldBtn[i][j].text = field[i][j]
                fieldBtn[i][j].isEnabled = true
            }
        }
    }
}

fun main(args:Array<String>) {
    TicTacToeGame()
}