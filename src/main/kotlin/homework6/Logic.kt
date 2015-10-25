package homework6

public class LogicGames(){
    internal  enum class State{Player1, Player2, Win, Standoff}
    internal  enum class Mark{X, O, *}
    internal  var field: Array<Array<Mark>> = Array(3, {i -> Array(3, {e -> Mark.*})})
    internal  var state: State = State.Player1
    internal  var winner: State? = null

    internal fun endOfGame(){
        when (state){
            State.Win -> println("$winner win!")
            State.Standoff -> println("Standoff!")
            else -> {}
        }
    }

    internal  fun checkState() {
        var flag = true
        field.forEach {
            for (x in 0..2) {
                if (it[x] == Mark.*) flag = false
            }
        }
        var tmp = state
        for (x in 0..2) {
            if ((field[x][0] == field[x][1]) && (field[x][1] == field[x][2]) && (field[x][0] != Mark.*)) {
                state = State.Win
                winner = tmp
            }
            if ((field[0][x] == field[1][x]) && (field[1][x] == field[2][x]) && (field[0][x] != Mark.*)) {
                state = State.Win
                winner = tmp

            }
        }
        if ((field[0][0] == field[1][1]) && (field[1][1] == field[2][2]) && (field[0][0] != Mark.*)) {
            state = State.Win
            winner = tmp

        }
        if ((field[0][2] == field[1][1]) && (field[1][1] == field[2][0]) && (field[0][2] != Mark.*)) {
            state = State.Win
            winner = tmp
        }
        if ((state != State.Win) && (flag)) state = State.Standoff

        when (state) {
            State.Player1 -> state = State.Player2
            State.Player2 -> state = State.Player1
            State.Win -> endOfGame()
            State.Standoff -> endOfGame()
        }
    }
}

