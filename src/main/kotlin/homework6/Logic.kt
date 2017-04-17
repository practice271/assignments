package homework6

public class LogicGames() {
    public enum class State {Player1, Player2, Win, Standoff }
    public enum class Mark {X, O, I }
    public var field: Array<Array<Mark>> = Array(3,{Array(3, {Mark.I}) })
    public var state: State = State.Player1
    public var winner: State = State.Player1
    private var counter = 0

    fun checkState(): State {
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
        if ((state != State.Win) && (counter == 9)) state = State.Standoff

        if (state == State.Player1)  {
            winner = State.Player2
            state = State.Player2
        }
        else if (state == State.Player2) {
            winner = State.Player1
            state = State.Player1
        }
        return state
    }
    //conditionally correct coordinates
    fun move(x: Int, y: Int) {
        if (state == State.Player1) field[x][y] = Mark.X
        else field[x][y] = Mark.O
        counter++
    }

}

