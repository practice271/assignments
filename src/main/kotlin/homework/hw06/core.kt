package homework.hw06

/**
 * TicTacToe logic.
 * expected time: 4 h
 * actual time: (19:30-)
 * @author Kirill Smirenko, group 271
 */
class Core {
    private val gridSize = 3
    private val cellNone = 0
    private val cellX = 1
    private val cellO = -1

    private var gridContent = Array(gridSize, { IntArray(gridSize) })
    private var crossesMove = true

    public fun getGridSize() = gridSize

    public fun makeMove(x : Int, y : Int) : ErrorCode {
        if (gridContent[x][y] == cellNone) {
            gridContent[x][y] = if (crossesMove) cellX else cellO
            return if (isGameFinished()) ErrorCode.WIN else ErrorCode.OK
        }
        else {
            return ErrorCode.ERROR
        }
    }

    public fun restart() {
        gridContent = Array(gridSize, { IntArray(gridSize) })
        crossesMove = true
    }

    private fun isGameFinished() : Boolean {
        return false
        // TODO
    }

    public enum class ErrorCode {
        OK, WIN, ERROR
    }
}