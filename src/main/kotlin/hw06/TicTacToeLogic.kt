package hw06

public abstract class TicTacToeLogic {
    internal var lastScanned = 0
    internal val size  = 3
        //field will always be square

    //'x' is 0, 'o' is 1, nothing is 2
    internal val field = Array(size, {Array(size, {2})})
    internal var gameOnFlag = false

    //North-West to South-east diagonal line
    internal fun checkDiagonalNW_SE(mark : Int) : Boolean {
        for (i in 0..size - 1)
            if (field[i][i] != mark) return false
        return true
    }

    //North-East to South-west diagonal line
    internal fun checkDiagonalNE_SW(mark : Int) : Boolean {
        for (i in 0..size - 1)
            if (field[i][size - 1 - i] != mark) return false
        return true
    }

    internal fun checkColumn(j : Int, mark: Int) : Boolean {
        for (i in 0..size - 1)
            if (field[i][j] != mark) return false
        return true
    }

    internal fun checkRow(i : Int, mark: Int) : Boolean {
        for (j in 0..size - 1)
            if (field[i][j] != mark) return false
        return true
    }

    abstract internal fun gameOver(mark : Int)

    internal fun checkVictory(i : Int, j : Int, mark : Int) : Boolean {
        var res = false

        if (i == j)
            res = checkDiagonalNW_SE(mark)
        if (i == size - j - 1)
            res = res  || checkDiagonalNE_SW(mark)

        res = res || checkColumn(j, mark) || checkRow(i, mark)
        if (res) gameOver(mark)
        return res
    }

//    internal abstract fun drawField()
//
//    //returning string is required only for testing
//    abstract internal fun setMark(i : Int, j : Int, mark : Int) : String
//
//    //returns array with 3 elements: indexes of line and column; kind of mark
//    abstract internal fun getMark() : Array<Int>
//
//    open public fun gameStart() {
//        gameOnFlag = true
//        while (gameOnFlag) {
//            val input = getMark()
//            setMark(input[0], input[1], input[2])
//        }
//    }
}