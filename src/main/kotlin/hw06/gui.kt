package hw06

import java.util.ArrayList

import java.awt.*
import javax.swing.*
import java.awt.event.*

public class GameFrame : JFrame ()
{
    private val WIDTH = 350;
    private val HEIGHT = 350;

    public fun createGUI()
    {
        val areaTF = JTextField("  Player 'x' to go  ")
        val array : ArrayList<ArrayList<Boolean?>> =
                arrayListOf(arrayListOf<Boolean?>(null,null,null),
                        arrayListOf<Boolean?>(null,null,null),
                        arrayListOf<Boolean?>(null,null,null))
        var buttons = Array(9,{i -> JButton("")})
        val game = Game(array,0,null)
        var player = false
        val panel = JPanel()
        val frame = JFrame("Tic-tac-toe")
        frame.preferredSize = Dimension(WIDTH, HEIGHT)
        for (i in 0..8) {
            buttons[i].addActionListener(object : ActionListener {
                override fun actionPerformed(e: ActionEvent) {
                    if(buttons[i].text == "" && game.filling < 9) {
                        game.move (i/3, i mod 3, player)
                        when (game.filling mod 2) {
                            1 -> buttons[i].text = "x"
                            0 -> buttons[i].text = "0"
                        }
                        player = !player
                        if (game.winner != null) {
                            areaTF.text = "  PLAYER '${game.winner.toChar()}' WON! \n" +
                                    " Player 'x' to go"
                            game.filling = 9
                        }
                        if (game.filling == 9) {
                            game.map = arrayListOf(arrayListOf<Boolean?>(null,null,null),
                                    arrayListOf<Boolean?>(null,null,null),
                                    arrayListOf<Boolean?>(null,null,null))
                            game.winner = null
                            player = false
                        }
                        else {areaTF.text = "  Player '${(player).toChar()}' to go  "}
                    }
                    else {
                        if (game.filling == 9) {
                            game.move (i / 3, i mod 3, player)
                            game.filling = 1
                            for (k in 0..8) {
                                if (k != i) {
                                    buttons[k].text = ""
                                }
                            }
                            buttons[i].text = "x"
                            player = !player
                            areaTF.text = "  Player '${(player).toChar()}' to go  "
                        }
                    }
                    println(player)
                }
            })
            buttons[i].preferredSize = Dimension(80,80)
            panel.add(buttons[i])
        }
        panel.add(areaTF)
        frame.layout = BorderLayout()
        frame.contentPane.add(panel, BorderLayout.CENTER)
        frame.pack()
        frame.isVisible = true
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    }
}

/*fun main(args: Array<String>) {
    GameFrame().createGUI()
}*/
