/* The tic-tac-toe game. Console and GUI interfaces.
(expectation: 5 h; reality: 8 h)
by Sokolova Polina */

package hw06

open internal class Logic() {
    internal var array  = arrayOf(arrayOf(0, 0, 0), arrayOf(0, 0, 0), arrayOf(0, 0, 0))
    internal var turn = 0
    public fun clear() {
        array = arrayOf(arrayOf(0, 0, 0), arrayOf(0, 0, 0), arrayOf(0, 0, 0))
        turn = 0
    }
    public fun add(a : Int, b : Int) : Int {
        if (array[a][b] != 0) { return 3 }
        array[a][b] = (turn mod 2) + 1
        val line   = array[a][0] * array[a][1] * array[a][2]
        val column = array[0][b] * array[1][b] * array[2][b]
        if (line == 1 || column == 1) { return 1 }
        if (line == 8 || column == 8) { return 2 }
        if (a == b) {
            val diagonal = array[0][0] * array[1][1] * array[2][2]
            when (diagonal) {
                1 -> { return 1 }
                8 -> { return 2 }
            }
        }
        if (a + b == 2) {
            val diagonal = array[2][0] * array[1][1] * array[0][2]
            when (diagonal) {
                1 -> { return 1 }
                8 -> { return 2 }
            }
        }
        turn++
        return 0
    }
    open public fun startGame() {}
}

public object Console : Logic() {
    private fun print() {
        println("-------------")
        for (i in 0..2) {
            print("| ")
            for (j in 0..2) {
                when (array[i][j]) {
                    0 -> print("  | ")
                    1 -> print("X | ")
                    2 -> print("0 | ")
                }
            }
            println("\n-------------")
        }
    }
    private fun parse(s : String) : Array<Int> {
        var a = arrayOf(-1, -1)
        val length = s.length()
        fun find(start : Int, index : Int) : Int {
            var i = start
            while (i < length && a[index] == -1) {
                when (s[i]) {
                    '0', '1', '2' -> { a[index] = s[i].toInt() - 48
                        return i + 1 }
                }
                i++
            }
            return -1
        }
        var first = find(0, 0)
        if (first != -1) { find(first, 1) }

        return a
    }
    public fun fill() : Int {
        clear()
        while (turn < 10) {
            val s = readLine()
            if (s != null) {
                val resArray = parse(s)
                if (resArray[0] == -1 || resArray[1] == -1) {
                    println("Wrong command, try again")
                }
                else {
                    val add = add(resArray[0], resArray[1])
                    print()
                    when (add) {
                        1 -> { return 1 }
                        2 -> { return 2 }
                    }
                }
            }
        }
        return 0
    }
    override public fun startGame() {
        val result = fill()
        when (result) {
            1    -> print("Tic won!\n")
            2    -> print("Toe won!\n")
            else -> print("Draw!\n")
        }
    }
}


fun main(args : Array<String>) {
    Console.startGame()
}
