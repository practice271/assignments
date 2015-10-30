package hw6

class Term {
    private val game = TicTacToe()

    public fun run() {
        println("Game starts")
        while (game.run) {
            printField()
            printWhichMove()
            getMove()
        }
        printField()
        printWhichMove()
    }

    private fun printField() {
        print(' ')
        for (i in 0..2)
            print(i.toString())
        println()
        for (i in 0..2) {
            print(i.toString())
            for (j in 0..2)
                print(when(game[i, j]) {
                    Player.EMPTY -> '.'
                    Player.CROSS -> 'x'
                    Player.CIRCLE -> 'o'
                })
            println()
        }
        println()
    }

    private fun printWhichMove() {
        println(when {
            game.run -> when (game.next) {
                Player.CROSS -> "X move"
                else -> "O move"
            }
            else -> when (game.winner) {
                Player.CROSS -> "X win"
                Player.CIRCLE -> "O Win"
                Player.EMPTY -> "Draw"
            }
        })
        println()
    }

    private fun getMove() {
        var a: Int
        var b: Int
        while (true) {
            print("Input row and column to move there: ")
            val s = readLine()
            if (s != null && s.length == 2 &&
                    s[0].isDigit() && s[1].isDigit()) {
                a = s[0] - '0'
                b = s[1] - '0'
                if (a < 3 && b < 3)
                    break
            }
        }
        game.move(a, b)
        println()
    }
}
