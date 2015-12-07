package homework.hw06

private var exit = false
public var input : String = ""
private var x = ""
private var y = ""
private var i = 0
private var flag = false

public fun checkInput(input : String) : Boolean {
    x = ""; y = ""; i = 0; flag = false
    if (input.length <= (fieldSize.toString().length * 2) + 1) {
        while (i < input.length) {
            if ((input[i] in '0'..'9') && (!flag)) {
                x += input[i]
                i++
            }
            else {
                if (input[i] == ',') {
                    i++
                    flag = true
                }
                else {
                    if (flag) {
                        if (input[i] in '0'..'9') {
                            y += input[i]
                            i++
                        }
                        else return false
                    }
                    else return false
                }
            }
        }
        if ((x.length > 0) && (y.length > 0) && (x.toInt() < fieldSize) && (y.toInt() < fieldSize)) {
            if ((x.length > 1 && x[0] == '0') || (y.length > 1 && y[0] == '0')) return false
            return true
        }
    }
    return false
}

public fun mark() {
    placeMark(x.toInt(),y.toInt())
    printBoard()
    println(output)
    if (gameOver) println(result)
}

public fun printBoard() {
    println()
    for (i in 0..fieldSize - 1) {
        print("|")
        for (j in 0..fieldSize - 1) print(" " + field[i][j] + " |")
        println()
    }
    println()
}

public fun handle(input : String) {
    when(input) {
        "start" -> {newGame(); printBoard()}
        "exit" -> exit = true
        else -> if (checkInput(input)) {mark()}
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
    println("Enter size of field (3 <= size : Integer <= infinity):")
    var start = false
    var f = false
    var size = 3
    var number : Int
    while (!start) {
        input = readLine().toString()
        if (input == "") println("Incorrect data")
        else {
            if (!f) {
                if (input.toInt() < 3) println("Incorrect data")
                else {
                    f = true
                    size = input.toInt()
                    println("Enter number of symbols to win (3 <= number <= size of field):")
                }
            }
            else {
                if (input.toInt() < 3 || input.toInt() > size) println("Incorrect data")
                else {
                    start = true
                    number = input.toInt()
                    changeRules(size, number)
                    newGame()
                }
            }
        }
    }
    println("Instructions:")
    println()
    println("i,j - to mark field (i,j), 0 <= i <= ${fieldSize - 1}, 0 <= j <= ${fieldSize - 1}")
    println("exit - to exit")
    println("start - to start a new game")
    println("=====================")
    while (!exit) {
        input = readLine().toString()
        handle(input)
    }
}