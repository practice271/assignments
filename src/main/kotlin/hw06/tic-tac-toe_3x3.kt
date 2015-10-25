package hw06

import javax.swing.JFrame
import java.awt.*
import java.util.*


public class Console(){
    private val logic = Logic()
    private var state = 0

    public fun nextStroke(player: Int,playerStroke: Int): String{
        val state = logic.reader(player,playerStroke)
        val win = logic.winer()
        if (state == 0){
            if (win == 1){
                println("1st player wins!")
                return "end"
            }
            if (win == 2){
                println("2nd player wins!")
                return "end"
            }
        }
        if (logic.availableStroke()){
            println("Dead heat!")
            return "end"
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
    while (state != "end") {
        if (player == 1){
            println("Player 1, your stroke")
            playerStroke = readLine()?.toInt()?: -3
            state = game.nextStroke(1,playerStroke)
            when (state){
                "try again" -> player = 1
                else -> player = 2
            }
        }
        else{
            println("Player 2, your stroke")
            playerStroke = readLine()?.toInt()?: -3
            state = game.nextStroke(2,playerStroke)
            when (state){
                "try again" -> player = 2
                else -> player = 1
            }
        }
    }
}*/
