package homework.hw06

/**
 * TicTacToe logic.
 * @author Kirill Smirenko, group 271
 */
class Core private constructor () {
    private var gridContent = Array(gridSize, { IntArray(gridSize) })
    private var crossesMove = true
    private var freeCellsNumber = gridSize * gridSize

    companion object {
        /**
         * A Core singleton.
         */
        public val instance = Core()
        // constants
        public val gridSize = 3
        public val cellNone = 0
        public val cellX = 1
        public val cellO = -1
    }

    init {
        for (i in 0..gridSize - 1) {
            for (j in 0..gridSize - 1) {
                gridContent[i][j] = cellNone
            }
        }
    }

    public operator fun get(i : Int, j : Int) = gridContent[i][j]

    /**
     * Saves info about last move, and returns whether the game has finished, or the input info was erroneous.
     */
    public fun makeMove(x : Int, y : Int) : ErrorCode {
        if ((x >= 0) && (x < gridSize) && (y >= 0) && (y < gridSize)
                && (gridContent[x][y] == cellNone)) {
            gridContent[x][y] = if (crossesMove) cellX else cellO
            crossesMove = !crossesMove
            val hasWinner = isGameFinished(x, y)
            return when {
                hasWinner -> ErrorCode.WIN
                --freeCellsNumber == 0 -> ErrorCode.DRAW
                else -> ErrorCode.OK
            }
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
        freeCellsNumber = gridSize * gridSize
    }

    /**
     * Checks whether the game someone won after a symbol was put into cell [[x], [y]].
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
        OK, WIN, DRAW, ERROR
    }
}