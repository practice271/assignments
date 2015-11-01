package hw06

import java.awt.*
import javax.swing.*
import java.awt.event.*
import javax.swing.border.EmptyBorder

class TicTacToeGUI : JFrame(){
    private val marksLook = arrayOf("assets/cross.png", "assets/circle.png")
    private val marksLookSymbol = arrayOf('x', 'o')
    private val WIDTH  = 500
    private val HEIGHT = 500
    val text = JLabel()
    val btns : Array<FieldButton>

    class FieldButton(val i : Int, val j : Int) : JButton()

    inner class TicTacToeGUILogic : TicTacToeLogic() {

        override fun gameOver(mark : Int) {
            gameOnFlag = false
            for (btn in btns) btn.isEnabled = false
            if (mark == 2) text.text = "It's a draw!"
            else
                text.text = "${marksLookSymbol[mark]} wins!"
        }

        fun setMark(i: Int, j: Int, mark: Int) : String {
            text.text = "'${marksLookSymbol[lastScanned % 2]}' move next"
            field[i][j] = mark
            if (checkDraw()) gameOver(2)
            checkVictory(i, j)
            return ""
        }

        public fun gameStart() {
            gameOnFlag = true
            for (btn in btns) {
                btn.isEnabled = true
                btn.icon = null //in case of relaunch crosses and circles should be erased
            }
            for (i in 0..size - 1)
                for (j in 0..size - 1)
                    field[i][j] = 2
        }
    }

    val game = TicTacToeGUILogic()

    init {
        btns = Array(game.size * game.size, {i -> FieldButton(i / 3, i % 3)})
        for (btn in btns) {
            btn.addActionListener(FieldButtonHandler(btn))
            btn.isEnabled = false
            btn.preferredSize = Dimension(100, 100)
        }

        title = "Tic-Tac-Toe"

        val newGameBtn = JButton("Start new game")
        newGameBtn.addActionListener({
            game.gameStart()
            text.text = "'x' move next"
        })

        text.font = Font("Serif", Font.PLAIN, 25)

        val pane = JPanel()
        pane.border = EmptyBorder(20, 20, 20, 20)
        pane.layout = GridBagLayout()
        val c = GridBagConstraints()
        for (i in 0..game.size * game.size - 1) {
            c.gridx = i / game.size
            c.gridy = i % game.size
            pane.add(btns[i], c)
        }
        c.gridy++
        c.gridx = 0
        c.gridwidth = 3
        pane.add(text, c)
        c.gridy++
        c.fill = GridBagConstraints.HORIZONTAL;
        pane.add(newGameBtn, c)

        contentPane = pane
        isVisible = true
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        preferredSize = Dimension(HEIGHT, WIDTH)
        pack()
    }

    private inner class FieldButtonHandler (val btn : FieldButton) : ActionListener {
        override fun actionPerformed(e: ActionEvent) {
            game.lastScanned %= 2
            btn.isEnabled = false
            //unfortunately, setting only `disabledIcon` doesn't work.
            btn.icon = ImageIcon("${marksLook[game.lastScanned]}")
            btn.disabledIcon = ImageIcon("${marksLook[game.lastScanned]}")
            game.setMark(btn.i, btn.j, game.lastScanned++)
        }
    }
}

public fun main(args: Array<String>) {
    TicTacToeGUI()
}