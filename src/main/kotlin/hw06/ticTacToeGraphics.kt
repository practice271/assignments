package hw06

import java.awt.*
import javax.swing.*
import java.awt.event.*
import java.util.LinkedList

class ticTacToeGraphics : TicTacToe () {
    private var jframe = JFrame()
    private val buttons = Array(3, { Array(3, { JButton("") }) })
    private val restartButton = JButton("Reset")
    private var statusLabel = JLabel("Game started!")
    private val SIZE = 512
    private val cellFont = Font("Arial", Font.BOLD, 80)

    init {
        jframe.setTitle("Tic Tac Toe")
        val pane = jframe.getContentPane()
        pane.setLayout(GridLayout(4, 3))

        for (i in 0..2)
            for (j in 0..2) {
                val handler = CellPressedHandler(i, j)
                buttons[i][j].addActionListener(handler)
                buttons[i][j].font = cellFont
                pane.add(buttons[i][j])
            }

        val restart = RestartHandler()
        restartButton.addActionListener(restart)
        restartButton.font = Font("Arial", Font.BOLD, 30)
        pane.add(restartButton)

        statusLabel.font = Font("Arial", Font.BOLD, 20)
        pane.add(statusLabel)

        jframe.isResizable = false
        jframe.setSize(SIZE, SIZE)
        jframe.setVisible(true)
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        jframe.setLocationRelativeTo(null)
    }

    private inner class CellPressedHandler(private val x: Int, private val y: Int) : ActionListener {
        public override fun actionPerformed(e: ActionEvent) {
            val movingPlayer = getMovingPlayer()
            val tryMove = tryMakeMove(x, y)
            when (tryMove) {
                MoveResult.NON_EMPTY_CELL -> statusLabel.setText("Cell is not empty!")
                MoveResult.GAME_ENDED -> statusLabel.setText("Start new game!")
                MoveResult.OK -> {
                    buttons[x][y].setText(if (movingPlayer == Player.FIRST) "X" else "O")
                    statusLabel.setText("Set " + (if (movingPlayer == Player.FIRST) "O" else "X") + "!")
                    val winner = getWinner()
                    if (winner != null) {
                        statusLabel.setText("Player " + (if (winner == Player.FIRST) "I " else "II ") + "won!")
                        var winningLine = LinkedList<Pair<Int, Int>>()
                        for (i in 0..2) {
                            if (checkLine(i)) winningLine.addAll(listOf(Pair(i, 0), Pair(i, 1), Pair(i, 2)))
                            if (checkColumn(i)) winningLine.addAll(listOf(Pair(0, i), Pair(1, i), Pair(2, i)))
                            if (checkDiagonal1()) winningLine.addAll(listOf(Pair(0, 0), Pair(1, 1), Pair(2, 2)))
                            if (checkDiagonal2()) winningLine.addAll(listOf(Pair(0, 2), Pair(1, 1), Pair(2, 0)))
                        }
                        for (pair in winningLine) buttons[pair.first][pair.second].setForeground(Color.red)
                    } else if (!containEmptyCell()) statusLabel.setText("Draw!")
                }
            }

        }
    }

    private inner class RestartHandler() : ActionListener {
        public override fun actionPerformed(e: ActionEvent) {
            clear()
            for (i in 0..2)
                for (j in 0..2) {
                    buttons[i][j].text = ""
                    buttons[i][j].setForeground(Color.black)
                }
            statusLabel.setText("Game started!")
        }
    }
}

/*
fun main(args:Array<String>) {
    ticTacToeGraphics()
*/
