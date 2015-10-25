package homework.hw06

public class ConsoleGame() {

    inner private class Parser() {
        fun pars(value: String): Pair<Int, Int> {

            if (!(value.length == 3 && value[1] == ' ' && value[0].isDigit()
                    && value[2].isDigit() && value[0].toInt() - 48 < 4 && value[2].toInt() - 48 < 4)) {
                return Pair(-1, -1)
            } else return Pair(value[0].toInt() - 48, value[2].toInt() - 48)
        }
    }

    private var parser = Parser()
    private val logic = GameLogic()

    public fun start() {
        println("Game starts now")
        println("First step of crosses")
        nextStep()
    }

    private fun nextStep() {

        print(logic.getArr())
        val input = System.`in`.bufferedReader().readLine()
        val coor = parser.pars(input)
        val num = 3 * (coor.first - 1) + coor.second - 1

        if (coor.first == -1) {
            println("Wrong input")
            nextStep()
        } else
            when (logic.crossTurn) {
                true -> {

                    logic.crossTurn = false
                    if (logic.insert('x', num)) {
                        val answer = logic.isEnd()

                        when (answer) {
                            "Draw" -> println("Draw")
                            "" -> nextStep()
                            else -> println(answer)
                        }
                    } else {
                        println("Cell is busy")
                        nextStep()
                    }
                }

                false -> {
                    logic.crossTurn = true
                    if (logic.insert('o', num)) {

                        val answer = logic.isEnd()
                        when (answer) {
                            "Draw" -> println("Draw")
                            "" -> nextStep()
                            else -> println(answer)
                        }
                    } else {
                        println("Cell is busy")
                        nextStep()
                    }
                }
            }
    }

    private fun print(arr: Array<Char>) {
        println("${arr[0]}  ${arr[1]}  ${arr[2]}")
        println("${arr[3]}  ${arr[4]}  ${arr[5]}")
        println("${arr[6]}  ${arr[7]}  ${arr[8]}")
    }
}

