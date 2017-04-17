package homework.hw06

private var exit = false
public var input : String = ""

public fun checkInput(input : String) : Boolean {
    if ((input.length == 3) && (input[1] == ',')
            && (input[0] in '0'..'2') && (input[2] in '0'..'2')) {
        return true
    }
    return false
}

public fun mark(input : String) {
    placeMark(input[0].toInt() - '0'.toInt(),input[2].toInt() - '0'.toInt())
}

public fun handle(input : String) {
    when(input) {
        "start" -> newGame()
        "exit" -> exit = true
        else -> if (checkInput(input)) {mark(input)}
        else {
            output = "Unknown command"
            println(output)
        }
    }
}

fun main (args : Array<String>){
    println("=====================")
    println("The tic-tac-toe game.")
    println("=====================")
    println("Instructions:")
    println()
    println("i,j - to mark field (i,j), 0 <= i <= 2, 0 <= j <= 2,")
    println("exit - to exit")
    println("start - to start a new game")
    println("=====================")
    while (!exit) {
        input = readLine().toString()
        handle(input)
    }
}