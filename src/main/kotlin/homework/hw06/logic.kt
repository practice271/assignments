// Homework 6
// Alekseev Aleksei, group 271.

package homework.hw06

public var playerMark = "X"
public var field = Array(3, {Array(3, {" "})})
public var gameOver = false
public var result = ""
public var output = ""

public fun newGame() {
    field = Array(3, {Array(3, {" "})})
    gameOver = false
    result = ""
    printBoard()
}

public fun changePlayer() {
    when(playerMark) {
        "O" -> playerMark = "X"
        else -> playerMark = "O"
    }
}

public fun printBoard() {
    println()
    for (i in 0..2) {
        print("|")
        for (j in 0..2) print(" " + field[i][j] + " |")
        println()
    }
    println()
}

public fun checkForWin() : Boolean {
    for (i in 0..2) {
        if ((field[i][0] != " ")
            && (field[i][0] == field[i][1])
            && (field[i][1] == field[i][2])) return true
    }
    for (j in 0..2) {
        if ((field[0][j] != " ")
            && (field[0][j] == field[1][j])
            && (field[1][j] == field[2][j])) return true
    }
    if ((field[0][0] != " ") && (field[0][0] == field[1][1])
        && (field[1][1] == field[2][2])) return true
    if ((field[0][2] != " ") && (field[0][2] == field[1][1])
        && (field[1][1] == field[2][0])) return true
    return false
}

public fun boardIsFull() : Boolean {
    var isFull = true
    for (i in 0..2) {
        for (j in 0..2) {if (field[i][j] == " ") isFull = false}
    }
    return isFull
}

public fun placeMark(i: Int, j: Int) {
    if (!gameOver) {
        if ((field[i][j] == " ") && (i >= 0) && (i < 3)
            && (j >= 0) && (j < 3)) {
            field[i][j] = playerMark
            println("Player" + playerMark.toString() + " marked (" + i + "," + j + ")")
            printBoard()
            if (checkForWin()) {
                result = "Player" + playerMark.toString() + " wins!"
                println(result)
                gameOver = true
            }
            else {
                    if (boardIsFull()) {
                        result = "Draw!"
                        println(result)
                        gameOver = true
                    } else changePlayer()
                }
        }
        else {
            output = "Incorrect field"
            println(output)
        }
    }
    else {
        output = "GAME OVER"
        println(output)
    }
}
