package homework6

public class LogicGames() {
    internal enum class State {Player1, Player2, Win, Standoff }
    internal enum class Mark {X, O, I }
    internal var field: Array<Array<Mark>> = Array(3, { i -> Array(3, { i -> Mark.I }) })
    internal var state: State = State.Player1
    internal var winner: String = "Player1"

    internal fun checkState(): State {
        //check for empty fields
        var flag = true
        field.forEach {
            for (x in 0..2) {
                if (it[x] == Mark.I) flag = false
            }
        }
        //check in columns and rows
        for (x in 0..2) {
            if ((field[x][0] == field[x][1]) && (field[x][1] == field[x][2]) && (field[x][0] != Mark.I)) {
                state = State.Win
            }
            if ((field[0][x] == field[1][x]) && (field[1][x] == field[2][x]) && (field[0][x] != Mark.I)) {
                state = State.Win
            }
        }
        //check the main diagonal
        if ((field[0][0] == field[1][1]) && (field[1][1] == field[2][2]) && (field[0][0] != Mark.I)) {
            state = State.Win
        }
        //check the secondary diagonal
        if ((field[0][2] == field[1][1]) && (field[1][1] == field[2][0]) && (field[0][2] != Mark.I)) {
            state = State.Win
        }
        //check tie
        if ((state != State.Win) && (flag)) state = State.Standoff

        if (state == State.Player1)  {
            winner = "Player2"
            state = State.Player2
        }
        else if (state == State.Player2) {
            winner = "Player1"
            state = State.Player1
        }
        return state
    }
    //conditionally correct coordinates
    internal  fun move(x: Int, y: Int) {
        if (state == State.Player1) field[x][y] = Mark.X
        else field[x][y] = Mark.O
    }

}

