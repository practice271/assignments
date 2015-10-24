package hw06

/*
* Tic-Tac-Toe game. Implementation of the internal logic.
*
* */


public class TLogic() {
    private val field = Array(9, { '.' })
    private var player = true // true - Xs; false - Os

    public fun getPlayer(): Int = if(player) 1 else 2

    public fun fieldToString(): String =
        "${field[0]} ${field[1]} ${field[2]}\n" +
        "${field[3]} ${field[4]} ${field[5]}\n" +
        "${field[6]} ${field[7]} ${field[8]}"

    public fun move(x: Int, y: Int): Boolean = when(field[x + y*3]) {
        '.' -> {
            field[x + y * 3] = if (player) 'X' else 'O'
            player = !player
            true
        }
        else -> false
    }

    public fun checkWin(): Boolean {
        fun check(c: Char): Boolean = when(c) {
            'X', 'O' -> true
            else -> false
        }
        var con = field[4]
        if(con == field[0] && con == field[8] || con == field[2] && con == field[6] ||
           con == field[1] && con == field[7] || con == field[3] && con == field[5])
            return check(con)
        con = field[0]
        if(con == field[1] && con == field[2] || con == field[3] && con == field[6])
            return check(con)
        con = field[8]
        if(con == field[2] && con == field[5] || con == field[6] && con == field[7])
            return check(con)
        return false
    }

    public fun reset() {
        for(i in 0..8) field[i] = '.'
        player = true
    }
}
