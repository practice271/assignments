/**
 * The tie-tac-toe game
 * Console version
 */

package hw06

public class Console() {
    public var arr: Array<Char> = arrayOf(' ', ' ', ' ',
                                          ' ', ' ', ' ',
                                          ' ', ' ', ' ')
    public var flag: Boolean = false // need this variable for check win
    public fun rulesOfGame() {
        println("==================")
        println("Tie-tac-toe game!")
        println("================== \n")
        println("Rules:\n")
        println("1. Choose cell on field and write the number(0-8, left -> right, up -> down)\n")
        println("2. You will win if you collect three symbols(x) in any row, column or diagonal\n")
        println("***Good luck!***\n")
    }

/*
         x |   |
         =========
           | x |
         =========
           |   | x
 */

    public fun printField() {
        for (i in 0..2) {
            if (i == 2) {
                println("${arr[i * 3]} | ${arr[i * 3 + 1]} | ${arr[i * 3 + 2]}")
                return
            }
            println("${arr[i * 3]} | ${arr[i * 3 + 1]} | ${arr[i * 3 + 2]} \n=========")
        }
    }

    public fun readValue(data: String, n: Int) { // player points a data on keyboard(number; f.e. 0,1,2..)
        var k = data.toInt()                     // varible n saves steps (steps: 0 - x, 1 - o , 2 - x, ..)
        if (n % 2 == 0 ) {
            arr[k] = 'x'
        } else {
            arr[k] = 'o'
        }
    }

    public fun checkWin():Boolean {
        checkWinRow()
        checkWinColumn()
        checkWinDiagonal()
        if (flag == true) {return true}
        else {return false}
    }

    private fun checkWinRow() {
        for (i in 0..2) {
            var k = i * 3
            if ((arr[k] != ' ')&&(arr[k] == arr[k + 1]) && (arr[k + 1] == arr[k + 2])) {
                flag = true
                var z = arr[k]
                printCongrat(z)
                return
            }
        }
        return
    }

    private fun checkWinColumn() {
        for (i in 0..2) {
            if ((arr[i] != ' ')&&(arr[i] == arr[i + 3]) && (arr[i + 3] == arr[i + 6])) {
                flag = true
                var z = arr[i]
                printCongrat(z)
                return
            }
        }
        return
    }

    private fun checkWinDiagonal() {
        if ((arr[0] != ' ')&&(arr[0] == arr[4]) && (arr[4] == arr[8])) {
            flag = true
            var z = arr[0]
            printCongrat(z)
            return
        }

        if ((arr[2] != ' ')&&(arr[2] == arr[4]) && (arr[4] == arr[6])) {
            flag = true
            var z = arr[2]
            printCongrat(z)
            return
        }
        return
    }

    public fun printCongrat(pl: Char) {
        if (pl == ' ') {
            return
        }
        if (pl == 'x') {
            println("Congratulations, x!\n")
        } else {
            println("Congratulations, o!\n")
        }
    }

    public fun game(arr0: Console) {
        arr0.rulesOfGame()
        arr0.printField()
        var data = " "
        var n = 0

        for (i in 0..8) {
            if (arr0.flag == false) {
                data = System.`in`.bufferedReader().readLine()
                arr0.readValue(data, n)
                arr0.printField()
                arr0.checkWin()
                n++
            }
        }
    }
}


