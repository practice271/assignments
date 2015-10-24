/*
 * Homework 6 (19.10.2015)
 * Console version of the game.
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw06

/** Implementation of console version of the game. */
public class Console() {
    private val game = GameLogic()
    private var symbol = 'x'

    /** Prints useful information for user. */
    public fun help() {
        println("################################")
        println("#                              #")
        println("#       TIC-TAC-TOE GAME       #")
        println("#                              #")
        println("################################\n")
        println("Commands:")
        println("/start -- start new game")
        println("/exit  -- exit the game")
        println("\nHow to play:")
        println("Enter two numbers through one space - coordinates of cell,")
        println("where do you want to put your symbol. Wins player who will")
        println("collect three his symbols in any row, column or diagonal.\n")
    }

    /** Returns string which contains game field. */
    private fun drawField() : String {
        val f = game.getField()
        var result = ""
        for (i in 0 .. 2) {
            result += " ${f[i * 3]} | ${f[i * 3 + 1]} | ${f[i * 3 + 2]} \n"
            if (i < 2) result  += "===+===+===\n"
        }
        return result
    }

    /** Makes next move and prints current state. */
    public fun nextMove(str : String) : String {
        val state = game.move(symbol, str)
        var result = ""

        if (!state.startsWith("Error")) {
            result += drawField()

            if (state == "ok") {
                when (symbol) {
                    'x' -> {
                        symbol = 'o'
                        result += "\nYour move, player2"
                    }
                    else -> {
                        symbol = 'x'
                        result += "\nYour move, player1"
                    }
                }
            }
            else {
                result += "\n$state"
                result += "\n\nEnter '/start' to start new game or '/exit' to exit"
            }
        }
        else result = state
        return result
    }
}

fun main(args : Array<String>) {
    var game = Console()
    game.help()

    var data = ""
    println("Your move, player1")
    loop@ while (data != "/exit") {
        data = System.`in`.bufferedReader().readLine()
        when(data) {
            "/exit"  -> break@loop
            "/start" -> {
                game = Console()
                println("Your move, player1")
            }
            else -> println(game.nextMove(data))
        }
    }
}