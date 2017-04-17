package hw06

/**
 * Created by Mikhail on 24.10.2015.
 */
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class TicTacToe {

    public var board = Array(3) { CharArray(3) }

    var player1: String = "Player1"
    var player2: String = "Player2"
    var currentPlayer: Int = 0
    var marker1: Char = 'X'
    var marker2: Char = 'O'
    var plays: Int = 0
    private val reader = BufferedReader(InputStreamReader(System.`in`))

    public fun init() {
        var counter = 0
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = Character.forDigit(++counter, 10)
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
            for (j in 0..2) {
                if (board[i][j] == Character.forDigit(play, 10)) {
                    board[i][j] = if ((currentPlayer == 1)) marker1 else marker2
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
            var j = 0
            while (j < 3) {
                if (!Character.isLetter(board[i][j])) {
                    break
                }
                if (j == 0) {
                    current = board[i][j]
                } else if (current != board[i][j]) {
                    break
                }
                if (j == 2) {
                    //Found winner
                    return true
                }
                j++
            }
        }
        //Checking columns
        for (i in 0..2) {
            current = ' '
            var j = 0
            while (j < 3) {
                if (!Character.isLetter(board[j][i])) {
                    break
                }
                if (j == 0) {
                    current = board[j][i]
                } else if (current != board[j][i]) {
                    break
                }
                if (j == 2) {
                    return true
                }
                j++
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
            for (j in 0..2) {
                builder.append("[" + board[i][j] + "]")
            }
            builder.append("\n")
        }
        return builder.toString()
    }
}
