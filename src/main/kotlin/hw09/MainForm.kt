/* GUI for Brainfuck interpreter and converter
(expectation: 2 h; reality: 2,5 h)
by Sokolova Polina */

package hw09

import javax.swing.*
import java.awt.event.*

public class MainForm : JFrame(), ActionListener {
    private val interpretButton = JButton("Interpret")
    private val convertButton = JButton("Convert")
    private val textInField = JTextArea(20, 50)
    init {
        title = "Brainfuck interpreter and converter"
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        isResizable = true
        val southPanel = JPanel()
        val centerPanel = JPanel()
        add(southPanel, "South")
        add(centerPanel, "Center")
        centerPanel.add(JScrollPane(textInField))
        southPanel.add(interpretButton)
        southPanel.add(convertButton)
        interpretButton.addActionListener(this)
        convertButton.addActionListener(this)
        setSize(650, 400)
        setLocationRelativeTo(null)
    }
    override fun actionPerformed(event : ActionEvent) {
        if (event.source == interpretButton) {
            Interpreter().interpret(textInField.text)
        }
        else { println (Converter(textInField.text).convert()) }
    }
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            MainForm().isVisible = true
        }
    }
}