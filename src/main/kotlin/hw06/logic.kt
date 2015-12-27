// Homework 6
// Alekseev Aleksei, group 271.

package homework.hw06

import java.util.*

public var playerMark = 'X'
public var gameOver = false
public var coordX : Int = 0
public var coordY : Int = 0
public var board = Hashtable<String,Char>()

public fun newGame() {
    board.clear()
    playerMark = 'X'
    gameOver = false
}

public fun changePlayer() {
    when(playerMark) {
        'O' -> playerMark = 'X'
        else -> playerMark = 'O'
    }
}

public fun placeMark(field : String) {
    if (!gameOver) {
        board.put(field, playerMark)
        checkForWin(field)
        changePlayer()
    }
}

public fun setCoords(field : String) {
    var x : String = ""
    var y : String = ""
    var comma = false
    for (i in field) {
        if (i == ',') {comma = true}
        else {
            if (!comma) x += i
            else y += i
        }
    }
    coordX = x.toInt()
    coordY = y.toInt()
}

public fun checkForWin(field : String) {
    var symbolNumber = 5
    var count1 = 0
    var count2 = 0
    var x : String
    var y : String
    var temp : String
    fun isGameOver() {
        if (count1 + count2 > symbolNumber - 2) gameOver = true
        else {
            count1 = 0
            count2 = 0
        }
    }
    setCoords(field)
    // up & down
    x = coordX.toString();
    for (i in 1..(symbolNumber - 1)) {
        y = (coordY + i).toString();
        temp = x + "," + y
        if (board[temp] == playerMark) count1++
        else break
    }
    for (i in 1..(symbolNumber - 1)) {
        y = (coordY - i).toString();
        temp = x + "," + y
        if (board[temp] == playerMark) count2++
        else break
    }
    isGameOver()
    // left & right
    y = coordY.toString();
    for (i in 1..(symbolNumber - 1)) {
        x = (coordX + i).toString();
        temp = x + "," + y
        if (board[temp] == playerMark) count1++
        else break
    }
    for (i in 1..(symbolNumber - 1)) {
        x = (coordX - i).toString();
        temp = x + "," + y
        if (board[temp] == playerMark) count1++
        else break
    }
    isGameOver()
    // left up & right down
    for (i in 1..(symbolNumber - 1)) {
        x = (coordX - i).toString();
        y = (coordY + i).toString();
        temp = x + "," + y
        if (board[temp] == playerMark) count1++
        else break
    }
    for (i in 1..(symbolNumber - 1)) {
        x = (coordX + i).toString();
        y = (coordY - i).toString();
        temp = x + "," + y
        if (board[temp] == playerMark) count1++
        else break
    }
    isGameOver()
    // right up & left down
    for (i in 1..(symbolNumber - 1)) {
        x = (coordX + i).toString();
        y = (coordY + i).toString();
        temp = x + "," + y
        if (board[temp] == playerMark) count1++
        else break
    }
    for (i in 1..(symbolNumber - 1)) {
        x = (coordX - i).toString();
        y = (coordY - i).toString();
        temp = x + "," + y
        if (board[temp] == playerMark) count1++
        else break
    }
    isGameOver()
}