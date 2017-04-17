package hw06
import java.util.ArrayList

internal fun Boolean?.toChar () : Char {
    when (this) {
        null -> return '_'
        false -> return 'x'
        true -> return '0'
    }
}
open class Game {
    public var map : ArrayList<ArrayList<Boolean?>> = arrayListOf(
            arrayListOf<Boolean?>(null, null, null),
            arrayListOf<Boolean?>(null, null, null),
            arrayListOf<Boolean?>(null, null, null))
    public var filling : Int = 0
    public var winner : Boolean? = null

    public fun beginGame() {
        map = arrayListOf(
                arrayListOf<Boolean?>(null, null, null),
                arrayListOf<Boolean?>(null, null, null),
                arrayListOf<Boolean?>(null, null, null))
        filling = 0
        winner = null
    }

    public fun move (coord1 : Int, coord2 : Int, player : Boolean) {
        if ((map[coord1][coord2] == null || filling == 9) && winner == null) {
            map[coord1][coord2] = player
            filling++
            if (map[(coord1 + 1) mod 3][coord2] == player &&
                    map[(coord1 + 2) mod 3][coord2] == player) {
                winner = player
            }
            if (map[coord1][(coord2 + 1) mod 3] == player &&
                    map[coord1][(coord2 + 2) mod 3] == player) {
                winner = player
            }
            if (coord1 == coord2 &&
                    map[(coord1 + 1) mod 3][(coord2 + 1) mod 3] == player &&
                    map[(coord1 + 2) mod 3][(coord2 + 2) mod 3] == player) {
                winner = player
            }
            if ((coord1 + coord2) == 2 &&
                    map[(coord1 + 2) mod 3][(coord2 + 1) mod 3] == player &&
                    map[(coord1 + 1) mod 3][(coord2 + 2) mod 3] == player) {
                winner = player
            }
        }
    }
}

private fun parser(str : String?, array : ArrayList<Int>) : ArrayList<Int> {
    if (str == null) {
        val newStr = readLine()
        return parser(newStr, array)
    }
    for (i in 0..str.length - 1) {
        when (str.elementAt(i)) {
            '1' -> array.add(0)
            '2' -> array.add(1)
            '3' -> array.add(2)
        }
        if (array.size == 2) {return array}
    }
    if (array.size!= 2) {
        val newStr = readLine()
        return parser(newStr, array)
    }
    return array
}

private var array: ArrayList<ArrayList<Boolean?>> = arrayListOf(
        arrayListOf<Boolean?>(null, null, null),
        arrayListOf<Boolean?>(null, null, null),
        arrayListOf<Boolean?>(null, null, null))

public class ConsoleGame : Game() {
    fun printMap() {
        for (i in 0..2) {
            println("|${map[i][0].toChar()} |" +
                    " ${map[i][1].toChar()} | ${map[i][2].toChar()}|")
        }
    }
    public fun play () {
        println("Player 'x' to go. To start print two numbers :\n" +
                " 1 - line number, 2 - column number.")
        this.beginGame()
        var player = false
        while (winner == null && filling != 9) {
            var str = readLine()
            val coords = parser(str, arrayListOf())
            val fill = filling
            move(coords[0], coords[1], player)
            printMap()
            if (fill != filling) player = !player
        }
        when (winner) {
            null -> {
                println("Game over, no winners")
            }
            false -> {
                println("Game over, 'x' player won!")
            }
            true -> {
                println("Game over, '0' player won!")
            }
        }
        ConsoleGame().play()
    }
}

/*fun main(args: Array<String>) {
    ConsoleGame().play()
}*/

