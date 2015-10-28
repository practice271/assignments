package hw06


/*
* Tic-Tac-Toe implementation using console as interface
* */

public class Console() {
    private val gameLogic = TLogic()

    public fun play() {
        var i = 1
        var userInp: String
        val sc = MyParser()
        var cx: Int
        var cy: Int
        var moved = false
        println("To exit the game enter 3.\nTo restart the game enter 4.\nMake a move, player 1!")
        while(i <= 9) {
            userInp = readLine() ?: return
            if(sc.isCorrect(userInp)) {
                cx = sc.x
                cy = sc.y
                if (cx == 3 || cy == 3) return
                if (cx == 4 || cx == 4) {
                    i = 1
                    gameLogic.reset()
                    println("To exit the game enter 11.\nTo restart the game enter 10.\nMake a move, player 1!")
                    continue
                }
                moved = gameLogic.move(cx, cy)
            }
            else {
                println("Incorrect input data, try again.")
                continue
            }
            if(i >= 5 && moved) {
                if(gameLogic.checkWin()) {
                    println("${if(gameLogic.getPlayer() == 2) "X" else "O"}s won!")
                    return
                }
            }
            if(moved) {
                i++
                println("Make a move, player ${gameLogic.getPlayer()}!")
            }
            else
                println("No, try again")
            println(gameLogic.fieldToString())
        }
        println("It's a tie!")
        return
    }

    private inner class MyParser() {
        var x = -1
        private set(newX: Int) {
            field = newX
        }
        var y = -1
        private set(newY: Int) {
            field = newY
        }

        public fun isCorrect(input: String): Boolean {
            val len = input.length
            return when(len) {
                1 -> {
                    x = input[0].toInt() - '0'.toInt()
                    x == 3 || x == 4
                }
                3 -> {
                    x = input[0].toInt() - '0'.toInt()
                    y = input[2].toInt() - '0'.toInt()
                    x <= 4 && x >= 0 && y <= 4 && y >= 0
                }
                else -> false
            }
        }
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            Console().play()
        }
    }
}

