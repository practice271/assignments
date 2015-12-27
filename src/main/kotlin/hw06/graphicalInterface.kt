package homework.hw06

import java.awt.*
import javax.swing.*

public class TicTacToeGame : JFrame() {
    private val WIDTH = 800
    private val HEIGHT = 650
    private val newGameBtn = JButton("New game")
    private val exitBtn = JButton("Exit")
    private val upBtn = JButton("Up")
    private val downBtn = JButton("Down")
    private val leftBtn = JButton("Left")
    private val rightBtn = JButton("Right")
    private val status = JLabel("")
    private val pane = contentPane
    private val temp = GridBagConstraints()
    private var tmp = ""
    private val fieldSize = 15
    private val fieldBtn = Array(fieldSize,{Array(fieldSize,{JButton()})})
    private val symbolFont = Font("Arial", Font.BOLD, 12)
    private var centerX = 0
    private var centerY = 0
    init {
        title = "The tic-tac-toe game"
        pane.layout = GridBagLayout()
        newGameBtn.addActionListener({restart()})
        exitBtn.addActionListener({System.exit(0)})
        upBtn.addActionListener({centerX--;refresh()})
        downBtn.addActionListener({centerX++;refresh()})
        leftBtn.addActionListener({centerY--;refresh()})
        rightBtn.addActionListener({centerY++;refresh()})
        for (i in 0..fieldSize - 1) {
            for (j in 0..fieldSize - 1) {
                tmp = (centerX - 7 + i).toString() + "," + (centerY - 7 + j).toString()
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
        // up down left right
        temp.gridwidth = 3; temp.gridx = 0; temp.gridy = fieldSize + 1
        upBtn.preferredSize = Dimension(75,25); upBtn.isFocusable = false
        pane.add(upBtn, temp)
        temp.gridwidth = 3; temp.gridx = 4; temp.gridy = fieldSize + 1
        downBtn.preferredSize = Dimension(75,25); downBtn.isFocusable = false
        pane.add(downBtn, temp)
        temp.gridwidth = 3; temp.gridx = 8; temp.gridy = fieldSize + 1
        leftBtn.preferredSize = Dimension(75,25); leftBtn.isFocusable = false
        pane.add(leftBtn, temp)
        temp.gridwidth = 3; temp.gridx = 12; temp.gridy = fieldSize + 1
        rightBtn.preferredSize = Dimension(75,25); rightBtn.isFocusable = false
        pane.add(rightBtn, temp)
        // status
        temp.gridwidth = 7; temp.gridx = 4; temp.gridy = fieldSize + 2
        pane.add(status, temp)
        // newGame
        temp.gridwidth = 4; temp.gridx = 0; temp.gridy = fieldSize + 2
        newGameBtn.preferredSize = Dimension(100,25); newGameBtn.isFocusable = false
        pane.add(newGameBtn, temp)
        // exit
        temp.gridwidth = 4; temp.gridx = fieldSize - 4; temp.gridy = fieldSize + 2
        exitBtn.preferredSize = Dimension(100,25); exitBtn.isFocusable = false
        pane.add(exitBtn, temp)
        setSize(WIDTH, HEIGHT)
        isResizable = false
        isVisible = true
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    }
    private fun setMark(x : Int, y : Int) {
        tmp = (centerX - 7 + x).toString() + "," + (centerY - 7 + y).toString()
        placeMark(tmp)
        fieldBtn[x][y].isEnabled = false
        for (i in 0..fieldSize - 1) {
            for (j in 0..fieldSize - 1) {
                tmp = (centerX - 7 + i).toString() + "," + (centerY - 7 + j).toString()
                fieldBtn[i][j].text = board[tmp]?.toString()
                if (gameOver) fieldBtn[i][j].isEnabled = false
            }
        }
        if (gameOver) {
            changePlayer()
            status.text = "Player " + playerMark.toString() + " wins"
        }
    }
    private fun restart() {
        newGame()
        status.text = ""
        for (i in 0..fieldSize - 1) {
            for (j in 0..fieldSize - 1) {
                tmp = (centerX - 7 + i).toString() + "," + (centerY - 7 + j).toString()
                fieldBtn[i][j].text = board[tmp]?.toString()
                fieldBtn[i][j].isEnabled = true
            }
        }
    }
    private fun refresh() {
        for (i in 0..fieldSize - 1) {
            for (j in 0..fieldSize - 1) {
                tmp = (centerX - 7 + i).toString() + "," + (centerY - 7 + j).toString()
                fieldBtn[i][j].text = board[tmp]?.toString()
                if (fieldBtn[i][j].text == "X" || fieldBtn[i][j].text == "O") fieldBtn[i][j].isEnabled = false
                else fieldBtn[i][j].isEnabled = true
            }
        }
    }
}

fun main(args:Array<String>) {
    TicTacToeGame()
}