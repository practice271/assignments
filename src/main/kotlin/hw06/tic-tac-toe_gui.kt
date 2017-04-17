package hw06

import java.awt.*
import javax.swing.*
import java.awt.event.*

class GUI_game:JFrame() {
    var logic = Logic()
    var player = 1
    var s = 0
    var w = 0

    private var arrLock = Array(9,{0})
    private val WIDTH = 270
    private val HEIGHT = 400
    private val exitB:JButton
    private val again:JButton
    private var arrCell = Array(9,{i -> JButton()})
    private var label: JLabel
    private val ebHandler:ExitButtonHandler
    private val agHandler:AgainButtonHandler

    init{
        setTitle("Tic-tac-toe game")
        val pane = getContentPane()
        pane.setLayout(null)

        label = JLabel("Player 1, your stroke", SwingConstants.CENTER)
        label.setBounds(20, 250, 210, 30)
        label.setFont(Font("Arial", Font.BOLD, 20))
        label.setForeground(Color(0xCC0000))
        pane.add(label)

        exitB = JButton("Exit")
        exitB.setBounds(155, 300, 75, 30)
        pane.add(exitB)
        ebHandler = ExitButtonHandler()
        exitB.addActionListener(ebHandler)

        again = JButton("Try again")
        again.setBounds(20, 300, 100, 30)
        pane.add(again)
        agHandler = AgainButtonHandler()
        again.addActionListener(agHandler)

        for (i in 0..8){
            arrCell[i].setActionCommand("change${i}")
            if (i < 3){arrCell[i].setBounds(20 + 70 * i, 20, 70, 70)}
            if (i >= 3 && i <= 5) {arrCell[i].setBounds(20 + 70 * (i - 3), 90, 70, 70)}
            if (i > 5) {arrCell[i].setBounds(20 + 70 * (i - 6), 160, 70, 70)}
            arrCell[i].addActionListener(cellAction())
            pane.add(arrCell[i])
        }

        setSize(WIDTH, HEIGHT)
        setVisible(true)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    }
    public fun check(state: Int,win: Int): String{
        if (state == 0){
            if (win != 0) return "Player $win wins!"
        }
        if (logic.availableStroke()){
            return "Dead heat!"
        }
        if (state == -2) {
            return "Cell is not available"
        }
        return "Your stroke"
    }

    inner class ExitButtonHandler:ActionListener {
        public override fun actionPerformed(e:ActionEvent) {
            System.exit(0)
        }
    }
    inner class AgainButtonHandler:ActionListener {
        public override fun actionPerformed(e:ActionEvent) {
            logic = Logic()
            player = 1
            s = 0
            w = 0
            arrLock = Array(9,{0})
            for (i in 0..8){
                arrCell[i].setText("")
            }
        }
    }
    inner class cellAction():ActionListener {
        public override fun actionPerformed(e:ActionEvent) {
            for (i in 0..8) {
                if ("change${i}".equals(e.getActionCommand()) && arrLock[i] == 0) {
                    arrCell[i].setFont(Font("Arial", Font.BOLD, 40))
                    s = logic.reader(player, i)
                    w = logic.findWiner()
                    val str = check(s, w)
                    label.setText(str)
                    arrLock[i] = 1
                    if (player == 1) {
                        arrCell[i].setForeground(Color(0x0066FF))
                        arrCell[i].setText("X")
                        player = 2
                    }
                    else {
                        arrCell[i].setForeground(Color(0xFF3300))
                        arrCell[i].setText("O")
                        player = 1
                    }
                    if (str == "Player $w wins!") arrLock = Array(9, { 1 })
                }
            }
        }
    }
}

fun main(args:Array<String>) {
    GUI_game()
}
