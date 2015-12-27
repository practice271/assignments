package homework.hw06

private var exit = false
public var input : String = ""
public var output : String = ""

public fun checkInput(input : String) : Boolean {
    var flag = 0
    for (i in 0..input.length - 1) {
        if (!input[i].isDigit() && input[i] != '-') {
            if ((input[i] == ',') && (i != 0) && (i != input.length - 1)) flag++
            else return false
        }
    }
    if (flag != 1) return false
    else return true
}

public fun mark(field : String) {
    if (gameOver) println("start - to start a new game")
    if (board[field] == 'X' || board[field] == 'O') {
        output = "Incorrect field"
        println(output)
    }
    else {
        if (!gameOver) {
            output = "Player " + playerMark.toString() + " marked (" + field + ")"
            println(output)
            placeMark(field)
        }
        if (gameOver) {
            changePlayer()
            output = "Player " + playerMark.toString() + " wins"
            println(output)
        }
    }
}

public fun handle(input : String) {
    when(input) {
        "start" -> {newGame(); println("=====New game=====")}
        "exit" -> exit = true
        else -> if (checkInput(input)) {mark(input)}
        else {
            output = "Unknown command"
            println(output)
        }
    }
}

fun main (args : Array<String>){
    println("The tic-tac-toe game.")
    println("=====================")
    println("Instructions:")
    println("x,y - to mark field (x,y)")
    println("exit - to exit")
    println("start - to start a new game")
    println("=====================")
    while (!exit) {
        input = readLine().toString()
        handle(input)
    }
}