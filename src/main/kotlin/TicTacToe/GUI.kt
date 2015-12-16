package TicTacToe

import java.awt.*
import javax.swing.*

public class GUI: JFrame() {
    private var game  = Logic()
    private var frame = JFrame()
    private var panelField = JPanel()
    private var panelShiftBut = JPanel()
    private var buttons: Array<Array<JButton>> = Array(game.fieldSizeHorizontal,{Array(game.fieldSizeVertical, { JButton(" ") })})
    var scroll:JScrollPane = JScrollPane(panelField)
    var step = 0
    var shiftRightBut: JButton = JButton("Right")
    var shiftLeftBut: JButton = JButton("Left")
    var shiftUpBut: JButton = JButton("Up")
    var shiftDownBut: JButton = JButton("Down")

    init {
        frame.title = "Tic-tac-toe"
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setSize(350, 350)
        frame.setResizable(true)
        frame.setVisible(true)
        frame.setLocationRelativeTo(null)
        frame.add(scroll, BorderLayout.WEST)
        frame.add(panelShiftBut)
        frame.add(panelShiftBut, BorderLayout.EAST)

        panelShiftBut.add(shiftRightBut)
        panelShiftBut.add(shiftLeftBut)
        panelShiftBut.add(shiftUpBut)
        panelShiftBut.add(shiftDownBut)

        panelField.setSize(350, 350)
        panelField.setLayout(GridLayout(game.fieldSizeHorizontal, game.fieldSizeVertical))


        shiftRightBut.addActionListener {
            game.resize(game.fieldSizeHorizontal + 1, 1)
        }

        shiftDownBut.addActionListener {
            game.resize(game.fieldSizeVertical + 1, 0)
        }

        shiftUpBut.addActionListener {
            game.shiftUp(5)
        }

        shiftLeftBut.addActionListener {
            game.shiftLeft(5)
        }

        for (x in 0..game.fieldSizeHorizontal - 1) {
            for (y in 0..game.fieldSizeVertical - 1) {
               panelField.add(buttons[x][y])
               buttons[x][y].font = Font("Dialog", Font.PLAIN, 40)
               buttons[x][y].addActionListener {
                   game.readValue(x, y, step)
                   if(buttons[x][y].text == " ")
                   {buttons[x][y].text = game.field[x][y].toString()}
                   game.checkWin()
                   buttons[x][y].isEnabled = false
                   if (game.gameOver) {printCongratulations(step)}
                   step++
               }
            }
        }
    }
}

public fun printCongratulations(step: Int) {
    var player: String = "O"
    if (step % 2 == 0) {player = "X"}
    JOptionPane.showMessageDialog(null, "YOU WIN, ${player}!")
}

public fun main(args: Array<String>) {
    GUI()
}