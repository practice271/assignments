package homework.Hw06

import java.awt.*
import javax.swing.*
import homework.Hw06.Step
import java.util.*


public class TicTacToeGame : JFrame() {
    private var board = HashMap<Pair<Int, Int>, String> ()
    val step = Step("0")

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
        exitBtn.addActionListener({System.exit(0)})
        for (i in 0..fieldSize - 1) {
            for (j in 0..fieldSize - 1) {
                tmp = (centerX + i).toString() + "," + (centerY + j).toString()
                fieldBtn[i][j] = JButton()
                fieldBtn[i][j].font = symbolFont
                fieldBtn[i][j].addActionListener({writeMark(centerX + i,centerY + j)})
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
        upBtn.addActionListener({centerX--;writeBoard()})
        downBtn.addActionListener({centerX++;writeBoard()})
        leftBtn.addActionListener({centerY--;writeBoard()})
        rightBtn.addActionListener({centerY++;writeBoard()})
        newGameBtn.addActionListener({board = HashMap<Pair<Int, Int>, String> ();
            writeBoard();
            status.text = "";})
    }

    private fun writeMark(i:Int,j:Int) {
        if (status.text == "") {
            val mark = step.nextStep()
            fieldBtn[i + centerX*(-1)][j + centerY*(-1)].isEnabled = false
            fieldBtn[i + centerX*(-1)][j + centerY*(-1)].text = mark
            board.put(Pair(i, j), mark)
            val res = winCheck(Pair(i, j))
            if (res != "-") {
                status.text = "${res} Win!"
            }
        }
    }

    private fun winCheck(c:Pair<Int,Int>):String{
        val x = c.first
        val y = c.second
        var counters = arrayOf(1,1,1,1,1,1,1,1)
        var mark = board?.get(c) ?: ""
        for (i in 1..4){
            if (board.get(Pair(x,y-i)) == mark) counters[0]++
            if (board.get(Pair(x,y+i)) == mark) counters[1]++
            if (board.get(Pair(x-i,y)) == mark) counters[2]++
            if (board.get(Pair(x+i,y)) == mark) counters[3]++
            if (board.get(Pair(x+i,y+i)) == mark) counters[4]++
            if (board.get(Pair(x+i,y-i)) == mark) counters[5]++
            if (board.get(Pair(x-i,y-i)) == mark) counters[6]++
            if (board.get(Pair(x-i,y+i)) == mark) counters[7]++
        }
        if (counters.contains(5)) return mark
        return "-"
    }

    private fun writeBoard(){
        for (i in 0..fieldSize-1){
            for (j in 0..fieldSize-1){
                val key = Pair(centerX + i,centerY + j)
                fieldBtn[i][j].text = board?.get(key) ?: ""
                if (fieldBtn[i][j].text != "" && status.text == "") fieldBtn[i][j].isEnabled = false
                else fieldBtn[i][j].isEnabled = true
            }
        }
    }
}
/*
fun main(args:Array<String>) {
    TicTacToeGame()
}
*/