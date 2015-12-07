package homework.hw06

import java.awt.*
import javax.swing.*

public class TicTacToeGame : JFrame() {
    private val WIDTH = 800
    private val HEIGHT = 650
    private val newGameBtn = JButton("New game")
    private val exitBtn = JButton("Exit")
    private val status = JLabel("")
    private val pane = contentPane
    private val temp = GridBagConstraints()
    private val fieldBtn = Array(fieldSize,{Array(fieldSize,{JButton()})})
    private val symbolFont = Font("Arial", Font.BOLD, 12)
    init {
        title = "The tic-tac-toe game"
        pane.layout = GridBagLayout()
        newGameBtn.addActionListener({restart()})
        exitBtn.addActionListener({System.exit(0)})
        for (i in 0..fieldSize - 1) {
            for (j in 0..fieldSize - 1) {
                fieldBtn[i][j] = JButton()
                fieldBtn[i][j].font = symbolFont
                fieldBtn[i][j].addActionListener({setMark(i,j)})
                fieldBtn[i][j].isFocusable = false
                fieldBtn[i][j].preferredSize = Dimension(25, 25)
                fieldBtn[i][j].margin = Insets(0,0,0,0)
                temp.gridx = j
                temp.gridy = i
                pane.add(fieldBtn[i][j], temp)
            }
        }
        temp.insets = Insets(10,0,0,0)
        temp.gridwidth = fieldSize; temp.gridx = 0; temp.gridy = fieldSize
        pane.add(status, temp)
        temp.gridwidth = fieldSize - 1; temp.gridx = 0; temp.gridy = fieldSize + 1
        pane.add(newGameBtn, temp)
        temp.gridwidth = fieldSize - 1; temp.gridx = 0; temp.gridy = fieldSize + 2
        pane.add(exitBtn, temp)
        setSize(WIDTH, HEIGHT)
        isResizable = false
        isVisible = true
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    }
    private fun setMark(x : Int, y : Int) {
        placeMark(x,y)
        fieldBtn[x][y].isEnabled = false
        for (i in 0..fieldSize - 1) {
            for (j in 0..fieldSize - 1) {
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
        for (i in 0..fieldSize - 1) {
            for (j in 0..fieldSize - 1) {
                fieldBtn[i][j].text = field[i][j]
                fieldBtn[i][j].isEnabled = true
            }
        }
    }
}

fun main(args:Array<String>) {
    changeRules(19,5)
    newGame()
    TicTacToeGame()
}