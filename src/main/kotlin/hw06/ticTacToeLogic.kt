package hw06

public enum class Player {
    FIRST, SECOND
}

public enum class Cell {
    EMPTY, NOUGHT, CROSS
}

public enum class MoveResult {
    OK, NON_EXISTENT_CELL, NON_EMPTY_CELL, GAME_ENDED
}

public open class TicTacToe() {
    protected var field = Array(3, { Array(3, { Cell.EMPTY }) })
    private var moveOf = Player.FIRST
    private var winner: Player? = null

    public fun getMovingPlayer() = moveOf
    public fun getWinner() = winner

    private fun switchMove() {
        moveOf = if (moveOf == Player.FIRST) Player.SECOND else Player.FIRST
    }

    protected fun clear() {
        for (i in 0..2)
            for (j in 0..2)
                field[i][j] = Cell.EMPTY
        moveOf = Player.FIRST
        winner = null
    }

    public fun containEmptyCell(): Boolean {
        var emptyCellExist = false
        for (i in 0..2)
            for (j in 0..2)
                if (field[i][j] == Cell.EMPTY) emptyCellExist = true
        return emptyCellExist
    }

    public fun tryMakeMove(x: Int, y: Int): MoveResult {
        if (winner != null) return MoveResult.GAME_ENDED
        if (x < 0 || x > 2 || y < 0 || y > 2) return MoveResult.NON_EXISTENT_CELL
        if (field[x][y] == Cell.EMPTY) {
            field[x][y] = if (moveOf == Player.FIRST) Cell.CROSS else Cell.NOUGHT
            if (checkGameEnd(x, y)) winner = moveOf
            switchMove()
            return MoveResult.OK
        } else return MoveResult.NON_EMPTY_CELL
    }

    protected fun checkGameEnd(x: Int, y: Int): Boolean =
            checkLine(x) || checkColumn(y) ||
                    if (x == y) checkDiagonal1() else false ||
                            if (x == (2 - y)) checkDiagonal2() else false

    protected fun checkLine(number: Int): Boolean {
        if (number < 0 || number > 2) return false
        return (field[number][0] == field[number][1] && field[number][0] == field[number][2])
    }

    protected fun checkColumn(number: Int): Boolean {
        if (number < 0 || number > 2) return false
        return (field[0][number] == field[1][number] && field[0][number] == field[2][number])
    }

    protected fun checkDiagonal1(): Boolean =
            field[0][0] == field[1][1] && field[0][0] == field[2][2]

    protected fun checkDiagonal2(): Boolean =
            field[2][0] == field[1][1] && field[2][0] == field[0][2]
}