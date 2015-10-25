/**Я сочувствую тому, кто будет это проверять.
 * Постараюсь сделать код более оптимальным и читабельным, но не раньше среды.
 * И тестов нет, я не знаю как в них можно впихнуть этот код*/

package hw06

import java.awt.*
import javax.swing.*
import java.awt.event.*


class GUI_game:JFrame() {
    var logic = Logic()
    var player = 1
    var s = 0
    var w = 0
    private var arrLock = Array(9,{i -> 0})
    private val WIDTH = 270
    private val HEIGHT = 400

    private val exitB:JButton
    private val again:JButton
    private var cell1:JButton
    private var cell2:JButton
    private var cell3:JButton
    private var cell4:JButton
    private var cell5:JButton
    private var cell6:JButton
    private var cell7:JButton
    private var cell8:JButton
    private var cell9:JButton

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

        cell1 = JButton()
        cell1.setActionCommand("change1")
        cell1.setBounds(20, 20, 70, 70)
        cell1.addActionListener(cellAction())
        pane.add(cell1)
        cell2 = JButton()
        cell2.setActionCommand("change2")
        cell2.setBounds(90, 20, 70, 70)
        cell2.addActionListener(cellAction())
        pane.add(cell2)
        cell3 = JButton()
        cell3.setActionCommand("change3")
        cell3.setBounds(160, 20, 70, 70)
        cell3.addActionListener(cellAction())
        pane.add(cell3)
        cell4 = JButton()
        cell4.setActionCommand("change4")
        cell4.setBounds(20, 90, 70, 70)
        cell4.addActionListener(cellAction())
        pane.add(cell4)
        cell5 = JButton()
        cell5.setActionCommand("change5")
        cell5.setBounds(90, 90, 70, 70)
        cell5.addActionListener(cellAction())
        pane.add(cell5)
        cell6 = JButton()
        cell6.setActionCommand("change6")
        cell6.setBounds(160, 90, 70, 70)
        cell6.addActionListener(cellAction())
        pane.add(cell6)
        cell7 = JButton()
        cell7.setActionCommand("change7")
        cell7.setBounds(20, 160, 70, 70)
        cell7.addActionListener(cellAction())
        pane.add(cell7)
        cell8 = JButton()
        cell8.setActionCommand("change8")
        cell8.setBounds(90, 160, 70, 70)
        cell8.addActionListener(cellAction())
        pane.add(cell8)
        cell9 = JButton()
        cell9.setActionCommand("change9")
        cell9.setBounds(160, 160, 70, 70)
        cell9.addActionListener(cellAction())
        pane.add(cell9)

        setSize(WIDTH, HEIGHT)
        setVisible(true)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    }
    public fun check(state: Int,win: Int): String{
        if (state == 0){
            if (win == 1){
                return "1st player wins!"
            }
            if (win == 2){
                return "2nd player wins!"
            }
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
            arrLock = Array(9,{i -> 0})
            cell1.setText("")
            cell2.setText("")
            cell3.setText("")
            cell4.setText("")
            cell5.setText("")
            cell6.setText("")
            cell7.setText("")
            cell8.setText("")
            cell9.setText("")
        }
    }
    inner class cellAction:ActionListener {
        public override fun actionPerformed(e:ActionEvent) {
            if ("change1".equals(e.getActionCommand())&& player == 1 && arrLock[0] == 0) {
                player = 2
                cell1.setText("X")
                cell1.setFont(Font("Arial", Font.BOLD, 40))
                cell1.setForeground(Color(0x0066FF))
                s = logic.reader(1,0)
                w = logic.winer()
                val str = check(s, w)
                label.setText(str)
                arrLock[0] = 1
            }
            else if ("change1".equals(e.getActionCommand())&& player == 2 && arrLock[0] == 0) {
                player = 1
                cell1.setText("O")
                cell1.setFont(Font("Arial", Font.BOLD, 40))
                cell1.setForeground(Color(0xFF3300))
                s = logic.reader(2,0)
                w = logic.winer()
                val str = check(s, w)
                label.setText(str)
                arrLock[0] = 1
            }
            if ("change2".equals(e.getActionCommand())&& player == 1 && arrLock[1] == 0) {
                player = 2
                cell2.setText("X")
                cell2.setFont(Font("Arial", Font.BOLD, 40))
                cell2.setForeground(Color(0x0066FF))
                s = logic.reader(1,1)
                w = logic.winer()
                val str = check(s, w)
                label.setText(str)
                arrLock[1] = 1
            }
            else if ("change2".equals(e.getActionCommand())&& player == 2 && arrLock[1] == 0) {
                player = 1
                cell2.setText("O")
                cell2.setFont(Font("Arial", Font.BOLD, 40))
                cell2.setForeground(Color(0xFF3300))
                s = logic.reader(2,1)
                w = logic.winer()
                val str = check(s, w)
                label.setText(str)
                arrLock[1] = 1
            }
            if ("change3".equals(e.getActionCommand())&& player == 1 && arrLock[2] == 0) {
                player = 2
                cell3.setText("X")
                cell3.setFont(Font("Arial", Font.BOLD, 40))
                cell3.setForeground(Color(0x0066FF))
                s = logic.reader(1,2)
                w = logic.winer()
                val str = check(s, w)
                label.setText(str)
                arrLock[2] = 1
            }
            else if ("change3".equals(e.getActionCommand())&& player == 2 && arrLock[2] == 0) {
                player = 1
                cell3.setText("O")
                cell3.setFont(Font("Arial", Font.BOLD, 40))
                cell3.setForeground(Color(0xFF3300))
                s = logic.reader(2,2)
                w = logic.winer()
                val str = check(s, w)
                label.setText(str)
                arrLock[2] = 1
            }
            if ("change4".equals(e.getActionCommand())&& player == 1 && arrLock[3] == 0) {
                player = 2
                cell4.setText("X")
                cell4.setFont(Font("Arial", Font.BOLD, 40))
                cell4.setForeground(Color(0x0066FF))
                s = logic.reader(1,3)
                w = logic.winer()
                val str = check(s, w)
                label.setText(str)
                arrLock[3] = 1
            }
            else if ("change4".equals(e.getActionCommand())&& player == 2 && arrLock[3] == 0) {
                player = 1
                cell4.setText("O")
                cell4.setFont(Font("Arial", Font.BOLD, 40))
                cell4.setForeground(Color(0xFF3300))
                s = logic.reader(2,3)
                w = logic.winer()
                val str = check(s, w)
                label.setText(str)
                arrLock[3] = 1
            }
            if ("change5".equals(e.getActionCommand())&& player == 1 && arrLock[4] == 0) {
                player = 2
                cell5.setText("X")
                cell5.setFont(Font("Arial", Font.BOLD, 40))
                cell5.setForeground(Color(0x0066FF))
                s = logic.reader(1,4)
                w = logic.winer()
                val str = check(s, w)
                label.setText(str)
                arrLock[4] = 1
            }
            else if ("change5".equals(e.getActionCommand())&& player == 2 && arrLock[4] == 0) {
                player = 1
                cell5.setText("O")
                cell5.setFont(Font("Arial", Font.BOLD, 40))
                cell5.setForeground(Color(0xFF3300))
                s = logic.reader(2,4)
                w = logic.winer()
                val str = check(s, w)
                label.setText(str)
                arrLock[4] = 1
            }
            if ("change6".equals(e.getActionCommand())&& player == 1 && arrLock[5] == 0) {
                player = 2
                cell6.setText("X")
                cell6.setFont(Font("Arial", Font.BOLD, 40))
                cell6.setForeground(Color(0x0066FF))
                s = logic.reader(1,5)
                w = logic.winer()
                val str = check(s, w)
                label.setText(str)
                arrLock[5] = 1
            }
            else if ("change6".equals(e.getActionCommand())&& player == 2 && arrLock[5] == 0) {
                player = 1
                cell6.setText("O")
                cell6.setFont(Font("Arial", Font.BOLD, 40))
                cell6.setForeground(Color(0xFF3300))
                s = logic.reader(2,5)
                w = logic.winer()
                val str = check(s, w)
                label.setText(str)
                arrLock[5] = 1
            }
            if ("change7".equals(e.getActionCommand())&& player == 1 && arrLock[6] == 0) {
                player = 2
                cell7.setText("X")
                cell7.setFont(Font("Arial", Font.BOLD, 40))
                cell7.setForeground(Color(0x0066FF))
                s = logic.reader(1,6)
                w = logic.winer()
                val str = check(s, w)
                label.setText(str)
                arrLock[6] = 1
            }
            else if ("change7".equals(e.getActionCommand())&& player == 2 && arrLock[6] == 0) {
                player = 1
                cell7.setText("O")
                cell7.setFont(Font("Arial", Font.BOLD, 40))
                cell7.setForeground(Color(0xFF3300))
                s = logic.reader(2,6)
                w = logic.winer()
                val str = check(s, w)
                label.setText(str)
                arrLock[6] = 1
            }
            if ("change8".equals(e.getActionCommand())&& player == 1 && arrLock[7] == 0) {
                player = 2
                cell8.setText("X")
                cell8.setFont(Font("Arial", Font.BOLD, 40))
                cell8.setForeground(Color(0x0066FF))
                s = logic.reader(1,7)
                w = logic.winer()
                val str = check(s, w)
                label.setText(str)
                arrLock[7] = 1
            }
            else if ("change8".equals(e.getActionCommand())&& player == 2 && arrLock[7] == 0) {
                player = 1
                cell8.setText("O")
                cell8.setFont(Font("Arial", Font.BOLD, 40))
                cell8.setForeground(Color(0xFF3300))
                s = logic.reader(2,7)
                w = logic.winer()
                val str = check(s, w)
                label.setText(str)
                arrLock[7] = 1
            }
            if ("change9".equals(e.getActionCommand())&& player == 1 && arrLock[8] == 0) {
                player = 2
                cell9.setText("X")
                cell9.setFont(Font("Arial", Font.BOLD, 40))
                cell9.setForeground(Color(0x0066FF))
                s = logic.reader(1,8)
                w = logic.winer()
                val str = check(s, w)
                label.setText(str)
                arrLock[8] = 1
            }
            else if ("change9".equals(e.getActionCommand())&& player == 2 && arrLock[8] == 0) {
                player = 1
                cell9.setText("O")
                cell9.setFont(Font("Arial", Font.BOLD, 40))
                cell9.setForeground(Color(0xFF3300))
                s = logic.reader(2,8)
                w = logic.winer()
                val str = check(s, w)
                label.setText(str)
                arrLock[8] = 1
            }
        }
    }
}

fun main(args:Array<String>) {
    GUI_game()
}
