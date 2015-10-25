package hw06

/**
 * Created by Mikhail on 24.10.2015.
 */
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class TicTacToe {

    public var board = Array(3) { CharArray(3) }
    var player1: String = " "
    var player2: String = " "
    var currentPlayer: Int = 0
    var marker1: Char = ' '
    var marker2: Char = ' '
    var plays: Int = 0
    private val reader = BufferedReader(InputStreamReader(System.`in`))

    public fun init() {
        var counter = 0
        for (i in 0..2) {
            for (i1 in 0..2) {
                board[i][i1] = Character.forDigit(++counter, 10)
            }
        }
        currentPlayer = 1
        plays = 0
    }

    public fun switchPlayers() {
        if (currentPlayer == 1) {
            currentPlayer = 2
        } else {
            currentPlayer = 1
        }
        plays += 1
    }

    public fun placeMarker(play: Int): Boolean {
        for (i in 0..2) {
            for (i1 in 0..2) {
                if (board[i][i1] == Character.forDigit(play, 10)) {
                    board[i][i1] = if ((currentPlayer == 1)) marker1 else marker2
                    return true
                }
            }
        }
        return false
    }

    public fun winner(): Boolean {
        //Checking rows
        var current = ' '
        for (i in 0..2) {
            var i1 = 0
            while (i1 < 3) {
                if (!Character.isLetter(board[i][i1])) {
                    break
                }
                if (i1 == 0) {
                    current = board[i][i1]
                } else if (current != board[i][i1]) {
                    break
                }
                if (i1 == 2) {
                    //Found winner
                    return true
                }
                i1++
            }
        }
        //Checking columns
        for (i in 0..2) {
            current = ' '
            var i1 = 0
            while (i1 < 3) {
                if (!Character.isLetter(board[i1][i])) {
                    break
                }
                if (i1 == 0) {
                    current = board[i1][i]
                } else if (current != board[i1][i]) {
                    break
                }
                if (i1 == 2) {
                    return true
                }
                i1++
            }
        }
        current = board[0][0]
        if (Character.isLetter(current) && board[1][1] == current && board[2][2] == current) {
            return true
        }
        current = board[2][0]
        if (Character.isLetter(current) && board[1][1] == current && board[0][2] == current) {
            return true
        }
        return false
    }

    public val prompt: String
        get() {
            var prompt = reader.readLine()
            return prompt
        }

    public fun drawBoard(): String {
        val builder = StringBuilder("Game board: \n")
        for (i in 0..2) {
            for (i1 in 0..2) {
                builder.append("[" + board[i][i1] + "]")
            }
            builder.append("\n")
        }
        return builder.toString()
    }
}
