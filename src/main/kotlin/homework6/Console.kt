package homework6


public class Console() {
    var game = LogicGames()

    internal fun output(){
        game.field.forEach {
            for (x in 0..2) print(it[x].toString() +  " ")
            println()
        }
    }
    internal  fun move(x: Int, y: Int){
        if (game.field[x][y] == LogicGames.Mark._) {
            if (game.state == LogicGames.State.Player1) game.field[x][y] = LogicGames.Mark.X
            else game.field[x][y] = LogicGames.Mark.O
            output()
            game.checkState()
            if ((game.state != LogicGames.State.Win)&&(game.state != LogicGames.State.Standoff)) read()
        }
        else {
            println("Field busy")
            read()
        }
    }

    internal fun read() {
        val line: String? = readLine()
        if ((line?.length() != 3) || (line == null)) {
            println("Wrong input")
            read()
        }
        else {
            var x = line[0].toInt() - 48
            var y = line[2].toInt() - 48
            if (!((x in 0..2) && (y in 0..2))) {
                println("Field 3*3")
                read()
            }
            else move(x, y)
        }
    }
}

