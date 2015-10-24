package hw6

enum class Player {
    EMPTY, CROSS, CIRCLE
}

class TicTacToe {
    private val fd = Array(3, { Array(3, { i -> Player.EMPTY }) })
    var moves = 0; private set
    var winner = Player.EMPTY; private set
    var next = Player.CROSS; private set
    var run = true; private set
    private val w = IntArray(8)

    operator fun get(i: Int, j: Int) = fd[i][j]

    fun move(i: Int, j: Int): Boolean {
        if (fd[i][j] == Player.EMPTY && run) {
            fd[i][j] = next
            var d = when (next) {
                Player.CROSS -> 1
                else -> -1
            }

            w[i] += d
            w[j + 3] += d
            if (i == j) w[6] += d
            if (i + j == 2) w[7] += d
            moves++

            updateState()

            return true
        }
        return false
    }

    private fun updateState() {
        when {
            w.any { v -> v == 3 } -> {
                run = false
                winner = Player.CROSS
            }
            w.any { v -> v == -3 } -> {
                run = false
                winner = Player.CIRCLE
            }
            moves == 9 -> {
                run = false
                winner = Player.EMPTY
            }
            else -> when (next) {
                Player.CROSS -> next = Player.CIRCLE
                else -> next = Player.CROSS
            }
        }
    }
}