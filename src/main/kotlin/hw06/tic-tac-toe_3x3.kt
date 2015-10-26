package hw06

import javax.swing.JFrame
import java.awt.*
import java.util.*


public class Console(){
    private val logic = Logic()
    private var state = 0

    public fun nextStroke(player: Int,playerStroke: Int): String{
        val state = logic.reader(player,playerStroke)
        val win = logic.findWiner()
        if (state == 0 && win != 0){
            println("Player ${win} wins!")
            return "Player wins!"
        }
        if (logic.availableStroke()){
            println("Dead heat!")
            return "Dead heat!"
        }
        if (state == -2) {
            println("Cell is not available.Try again")
            return "try again"
        }
        if (state == -1) {
            println("Cell is not exist")
            return "try again"
        }
        return ""
    }
}

/**fun main (args : Array<String>) {
    var game = Console()
    var player = 1
    var state = ""
    var playerStroke = 0
    while (state != "Player wins!" && state != "Dead heat!") {
        println("Player $player, your stroke")
        println("Enter the number of cell")
        playerStroke = readLine()?.toInt()?: -3
        state = game.nextStroke(player,playerStroke)
        if (state != "try again") player = 3 - player
    }
}*/
