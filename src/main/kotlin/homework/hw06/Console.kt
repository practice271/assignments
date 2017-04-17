/*
 * Homework 6 (20.10.2015)
 * Console version of the game.
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw06

import java.io.PrintStream

/** Implementation of console version of the game. */
public class Console(private val stream : PrintStream) {

    /** Parser for input strings. */
    private class Parser(private val str : String) {
        private var x = 0
        private var y = 0
        private var correct = true

        init {
            when (str.length) {
                3 -> {
                    x = Character.getNumericValue(str[0])
                    y = Character.getNumericValue(str[2])
                    if (str[1] != ' ' || x > 3 || x < 1 || y > 3 || y < 1) correct = false
                }
                else -> correct = false
            }
        }

        /** Returns true if input data is correct. */
        public fun isCorrect() : Boolean {
            return correct
        }

        /** Returns first digit. */
        public fun getX() : Int {
            return x
        }

        /** Returns second digit. */
        public fun getY() : Int {
            return y
        }
    }

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
        println("Enter two numbers in the range from 1 to 3 through one space -")
        println("coordinates of cell, where do you want to put your symbol. Wins player")
        println("who will collect three his symbols in any row, column or diagonal.\n")
    }

    /** Draws game field. */
    private fun drawField() {
        val f = game.getField()
        for (i in 0 .. 2) {
            stream.print(" ${f[i * 3]} | ${f[i * 3 + 1]} | ${f[i * 3 + 2]} \n")
            if (i < 2) stream.print("===+===+===\n")
        }
    }

    /** Makes next move and prints current state. */
    public fun nextMove(str : String) {
        val parser = Parser(str)
        val state  =
            if (parser.isCorrect()) game.move(symbol, parser.getX(), parser.getY())
            else "Error: incorrect data"

        if (!state.startsWith("Error")) {
            drawField()
            println()

            if (state == "ok") {
                when (symbol) {
                    'x' -> {
                        symbol = 'o'
                        println("Your move, player2")
                    }
                    else -> {
                        symbol = 'x'
                        println("Your move, player1")
                    }
                }
            }
            else {
                stream.print("$state\n")
                println("\nEnter '/start' to start new game or '/exit' to exit")
            }
        }
        else stream.print("$state\n")
    }
}

public fun main(args : Array<String>) {
    var game = Console(System.out)
    game.help()

    var data = ""
    println("Your move, player1")
    loop@ while (data != "/exit") {
        data = readLine() ?: "/exit"
        when(data) {
            "/exit"  -> break@loop
            "/start" -> {
                game = Console(System.out)
                println("Your move, player1")
            }
            else -> game.nextMove(data)
        }
    }
}