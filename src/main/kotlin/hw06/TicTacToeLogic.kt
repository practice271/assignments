package hw06

public abstract class TicTacToeLogic {
    internal var lastScanned = 0
    internal val size  = 3
        //field will always be square

    //'x' is 0, 'o' is 1, nothing is 2
    val field = Array(size, {Array(size, {2})})
    internal var gameOnFlag = false

    //North-West to South-east diagonal line
    fun checkDiagonalNW_SE() : Boolean {
        val mark = field[0][0]
        for (i in 1..size - 1)
            if (field[i][i] != mark) return false
        return true
    }

    //North-East to South-west diagonal line
    fun checkDiagonalNE_SW() : Boolean {
        val mark = field[0][2]
        for (i in 1..size - 1)
            if (field[i][size - 1 - i] != mark) return false
        return true
    }

    fun checkColumn(j : Int) : Boolean {
        val mark = field[0][j]
        for (i in 1..size - 1)
            if (field[i][j] != mark) return false
        return true
    }

    fun checkRow(i : Int) : Boolean {
        val mark = field[i][0]
        for (j in 1..size - 1)
            if (field[i][j] != mark) return false
        return true
    }

    abstract internal fun gameOver(mark : Int)

    fun checkVictory(i : Int, j : Int) : Boolean {
        val mark = field[i][j]
        var res = false

        if (i == j)
            res = checkDiagonalNW_SE()
        if (i == size - j - 1)
            res = res  || checkDiagonalNE_SW()

        res = res || checkColumn(j) || checkRow(i)
        if (res) gameOver(mark)
        return res
    }

    fun checkDraw() : Boolean {
        for (i in field)
            for (j in i)
                if (j == 2) return false
        return true
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