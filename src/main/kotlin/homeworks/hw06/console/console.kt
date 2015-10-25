package homeworks.hw06.console

import homeworks.hw06.logic.Logic
import java.util.*

/**
 * Created by Ilya on 23.10.2015.
 */
public class TicTacToe: Logic() {

    public fun startFromConsole() {
        var input: String?
        var maxCount = 0 // Counter for cells
        println("Please, start the game!")
        while (maxCount < 9) {
            input = readLine()
            if (input == null || input == "") {
                println("Please, try again!")
                continue
            }
            val temp = input.split(" ")
            // temp[0] - x, temp[1] - y, temp[2] - symbol
            array[temp[0].toInt()][temp[1].toInt()] = temp[2]
            if (checkSuccess(temp[2])) {
                println("Player with ${temp[2]} Win!!!")
                break
            }
            maxCount++
            if (maxCount == 9) {
                println("Tie!")
            }
        }
    }

    public fun startFromArray(input: Array<Array<String>>): String {
        fun arrayToPairs(array: Array<Array<String>>): ArrayList<Pair<Pair<Int, Int>, String>> {
            var result = arrayListOf(Pair(Pair(-1, -1), "null"))
            for (i in 0..2) {
                for (j in 0..2) {
                    result.add(Pair(Pair(i, j), array[i][j]))
                }
            }
            result.remove(Pair(Pair(-1, -1), "null"))
            return result
        }

        var listOfPairs = arrayToPairs(input)
        var counter = 0 // Counter for cells
        println("Please, start the game!")
        while (counter < 9) {
            val x = listOfPairs[counter].first.first
            val y = listOfPairs[counter].first.second
            val symbol = listOfPairs[counter].second
            if (symbol != "null") {
                array[x][y] = symbol
                if (checkSuccess(symbol)) {
                    return "Player with $symbol Win!!!"
                }
            }
            counter++
            if (counter == 9) {
                return "Tie!"
            }
        }
        return ""
    }


}

fun main(args: Array<String>) {
    print(TicTacToe().startFromConsole())
}