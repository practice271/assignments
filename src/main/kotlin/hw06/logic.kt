// Homework 6
// Alekseev Aleksei, group 271.

package homework.hw06

public var playerMark = "X"
public var fieldSize = 3
public var symbolNumber = 3
public var field = Array(fieldSize, {Array(fieldSize, {" "})})
public var gameOver = false
public var result = ""
public var output = ""

public fun newGame() {
    field = Array(fieldSize, {Array(fieldSize, {" "})})
    gameOver = false
    playerMark = "X"
    result = ""
}

public fun changePlayer() {
    when(playerMark) {
        "O" -> playerMark = "X"
        else -> playerMark = "O"
    }
}

public fun changeRules(size: Int, number : Int) {
    fieldSize = size
    symbolNumber = number
}

public fun checkForWin() : Boolean {
    var counter = 0
    var x : Int
    var y : Int
    for (i in 0..fieldSize - symbolNumber) {
        for (j in 0..fieldSize - 1) {
            if (field[i][j] != " ") {
                x = i; y = j;
                for (k in 0..symbolNumber - 2) {
                    x++
                    if (field[i][j] == field[x][y]) counter++
                    else break
                }
                if (counter == symbolNumber - 1) return true
                else counter = 0
            }
        }
    }
    for (i in 0..fieldSize - 1) {
        for (j in 0..fieldSize - symbolNumber) {
            if (field[i][j] != " ") {
                x = i; y = j;
                for (k in 0..symbolNumber - 2) {
                    y++
                    if (field[i][j] == field[x][y]) counter++
                    else break
                }
                if (counter == symbolNumber - 1) return true
                else counter = 0
            }
        }
    }
    for (i in 0..fieldSize - symbolNumber) {
        for (j in 0..fieldSize - symbolNumber) {
            if (field[i][j] != " ") {
                x = i; y = j;
                for (k in 0..symbolNumber - 2) {
                    x++; y++
                    if (field[i][j] == field[x][y]) counter++
                    else break
                }
                if (counter == symbolNumber - 1) return true
                else counter = 0
            }
        }
    }
    for (i in 0..fieldSize - symbolNumber) {
        for (j in (symbolNumber - 1)..fieldSize - 1) {
            if (field[i][j] != " ") {
                x = i; y = j;
                for (k in 0..symbolNumber - 2) {
                    x++; y--
                    if (field[i][j] == field[x][y]) counter++
                    else break
                }
                if (counter == symbolNumber - 1) return true
                else counter = 0
            }
        }
    }
    return false
}


public fun boardIsFull() : Boolean {
    var isFull = true
    for (i in 0..fieldSize - 1) {
        for (j in 0..fieldSize - 1) {if (field[i][j] == " ") isFull = false}
    }
    return isFull
}

public fun placeMark(i: Int, j: Int) {
    if (!gameOver) {
        if ((field[i][j] == " ") && (i >= 0) && (i < fieldSize)
            && (j >= 0) && (j < fieldSize)) {
            field[i][j] = playerMark
            output = "Player" + playerMark.toString() + " marked (" + i + "," + j + ")"
            if (checkForWin()) {
                result = "Player" + playerMark.toString() + " wins!"
                gameOver = true
            }
            else {
                    if (boardIsFull()) {
                        result = "Draw!"
                        gameOver = true
                    } else changePlayer()
                }
        }
        else {
            output = "Incorrect field"
        }
    }
    else {
        output = "GAME OVER"
    }
}
