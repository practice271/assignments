package hw06

import java.util.Scanner

/*
* Tic-Tac-Toe implementation using console as interface
* */

public class Console() {
    private val gameLogic = TLogic()

    public fun play() {
        var i = 1
        val sc = Scanner(System.`in`)
        var cx: Int
        var cy: Int
        var moved: Boolean
        //var cw = ""
        println("To exit the game enter 11.\nTo restart the game enter 10.\nMake a move, player 1!")
        while(i <= 9) {
            cx = sc.nextInt()
            if(cx == 11) return
            if(cx == 10) {
                i = 1
                gameLogic.reset()
                println("To exit the game enter 11.\nTo restart the game enter 10.\nMake a move, player 1!")
                cx = sc.nextInt()
            }
            cy = sc.nextInt()
            moved = gameLogic.move(cx, cy)
            if(i >= 5 && moved) {
                if(gameLogic.checkWin()) {
                    println("${if(gameLogic.getPlayer() == 2) "X" else "O"}s won!")
                    return
                }
            }
            if(moved) {
                i++
                println("Make a move, player ${gameLogic.getPlayer()}!")
            }
            else
                println("No, try again")
            println(gameLogic.fieldToString())
        }
        println("It's a tie!")
        return
    }
}


/*fun main(args: Array<String>) {
    Console().play()
}*/
