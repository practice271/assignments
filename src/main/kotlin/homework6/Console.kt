package homework6

public class Console() {
    private var game = LogicGames()

    private  fun output(){
        game.field.forEach {
            for (x in 0..2) print(it[x].toString() +  " ")
            println()
        }
    }
    private  fun endOfGame() {
        when (game.state) {
            LogicGames.State.Win -> println("${game.winner} win!")
            else -> println("Standoff!")
        }
    }
    public fun read() {
        val line: String? = readLine()
        if ((line?.length() != 3) || (line == null)) {
            println("Wrong input")
            read()
        }
        else {
            var x = Character.getNumericValue(line[0])
            var y = Character.getNumericValue(line[2])
            if (!((x in 0..2) && (y in 0..2))) {
                println("Wrong input")
                read()
            }
            else {
                if (game.field[x][y] != LogicGames.Mark.I) {
                    println("Field is already occupied")
                    read()
                }
                else {
                    game.move(x, y)
                    output()
                    val state = game.checkState()
                    if ((state == LogicGames.State.Win) || (state == LogicGames.State.Standoff)) endOfGame()
                    else read()
                }
            }
        }
    }
}
/*
fun main(args: Array<String>) {
    val c = Console()
    c.read()
}
*/

