package hw06

/* Gui game  made by Guzel Garifullina
   Estimated time  3 hours
   real time       3 hours
*/
import java.awt.*
import java.awt.event.*
import javax.swing.*
import java.awt.EventQueue
import java.util.*
import javax.swing.JFrame

public class GeneralField (private val size : Int, frame: JFrame)  {
    private val guiField = Array(size , {i ->JButton("")})
    private val logField = LogicalButtonField(size = 3)
    init {
        for (i in 0 .. (size - 1)) {
            guiField[i].verticalTextPosition = AbstractButton.CENTER
            guiField[i].horizontalTextPosition = AbstractButton.LEADING
            guiField[i].addActionListener(object : ActionListener {
                override fun actionPerformed(e: ActionEvent) {
                    if (logField.isSelected(i)) {
                            JOptionPane.showMessageDialog(frame,
                                    "This cell's already chosen")
                            return
                    }

                    val player = logField.getPlayer()
                    guiField[i].text = "$player"
                    logField.setLabel(i, player)

                    var text = ""
                    when {
                        logField.isVictory() ->{
                            text = "Payer $player win!\nRestart?\n"

                        }
                        logField.isTie() -> {
                            text = "Congratulations!\nIt's a tie\n"
                        }
                        else -> return
                    }
                    val n = JOptionPane.showConfirmDialog(
                            frame,
                             text +
                                    "If you click No - you exit a game",
                            "Message",
                            JOptionPane.YES_NO_OPTION)
                    if (n == JOptionPane.YES_OPTION ){
                        restart()
                    }
                    else {
                        System.exit(0)
                    }
                }
            })
        }
    }
    public fun getGuiButton(i : Int) : JButton{
        return guiField[i]
    }
    private fun restart(){
        logField.clear()
        for (i in 0 .. (size - 1)){
            guiField[i].text = ""
        }
    }
}

public class Gui() : JFrame(){
    val size = 9
    private  val gField = GeneralField(size, this)
    init {
        title = "Tic Tac Toe"
        setSize (600, 600)
        setLocationRelativeTo(null)

        this.layout =GridLayout(3,3)
        for (i in 0.. (size - 1)){
            this.add(gField.getGuiButton(i))
        }
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        this.isVisible = true
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            val game = Gui()
        }
    }
}