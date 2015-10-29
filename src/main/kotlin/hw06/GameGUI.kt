package hw06
import java.awt.*
import javax.swing.*

public class GameGUI():JFrame() {
    private var frame = JFrame()
    private var panel = JPanel()
    private var buttons = Array(9, { JButton(" ") })
    public var arrayMove = Console()
    public var step = 0
    public var flag:Boolean = false

    init {
        frame.title = "Tic-tac-toe"
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setSize(350, 350)
        frame.setResizable(true)
        frame.add(panel)
        panel.setLayout(GridLayout(3, 3))
        frame.setVisible(true)
        frame.setLocationRelativeTo(null)

        for (i in 0..8) {
            panel.add(buttons[i])
            buttons[i].font = Font("Courier New", Font.PLAIN, 40)
            buttons[i].setBackground(Color.BLACK)
            buttons[i].addActionListener {
                buttons[i].isEnabled = false
                arrayMove.readValue(i.toString(), step)
                buttons[i].text = arrayMove.arr[i].toString()
                flag = arrayMove.checkWin()
                step++
            }
        }
    }
}

public fun printCongratulations(player: String) {
    JOptionPane.showMessageDialog(null, "YOU WIN, ${player}!")
}

public fun main(args:Array<String>) {
    GameGUI()
}



