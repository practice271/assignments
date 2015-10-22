package homework.hw06

/**
 * TicTacToe logic.
 * expected time: 4 h
 * actual time: (19:30-20:00, 23:00-)
 * @author Kirill Smirenko, group 271
 */
class Core private constructor () {
    private val gridSize = 3
    private val cellNone = 0
    private val cellX = 1
    private val cellO = -1

    private var gridContent = Array(gridSize, { IntArray(gridSize) })
    private var crossesMove = true

    companion object {
        /**
         * A Core singleton.
         */
        public val instance = Core()
    }

    public fun getGridSize() = gridSize

    /**
     * Saves info about last move, and returns whether the game has finished, or the input info was erroneous.
     */
    public fun makeMove(x : Int, y : Int) : ErrorCode {
        if (gridContent[x][y] == cellNone) {
            gridContent[x][y] = if (crossesMove) cellX else cellO
            return if (isGameFinished(x, y)) ErrorCode.WIN else ErrorCode.OK
        }
        else {
            return ErrorCode.ERROR
        }
    }

    /**
     * Restarts the game.
     */
    public fun restart() {
        gridContent = Array(gridSize, { IntArray(gridSize) })
        crossesMove = true
    }

    /**
     * Checks whether the game was finished after a symbol was put into cell [[x], [y]].
     */
    private fun isGameFinished(x : Int, y : Int) : Boolean {
        var cellValue = gridContent[x][y]
        var bingoRow = true
        for (j in 0..gridSize - 1)
            bingoRow = bingoRow && (cellValue == gridContent[x][j])
        var bingoColumn = true;
        for (i in 0..gridSize - 1)
            bingoColumn = bingoColumn && (cellValue == gridContent[i][y])
        var bingoDiag1 = false
        if (x == y) {
            bingoDiag1 = true;
            for (i in 0..gridSize - 1)
                bingoDiag1 = bingoDiag1 && (cellValue == gridContent[i][i])
        }
        var bingoDiag2 = false
        if (x == gridSize - y - 1) {
            bingoDiag2 = true;
            for (i in 0..gridSize - 1)
                bingoDiag2 = bingoDiag2 && (cellValue == gridContent[i][gridSize - i - 1])
        }
        return bingoRow || bingoColumn || bingoDiag1 || bingoDiag2
    }

    public enum class ErrorCode {
        OK, WIN, ERROR
    }
}