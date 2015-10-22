package hw06

public class Game
{
    private var field : Array<Array<Char>> = Array(3, {i -> Array(3, {j -> '+'})})
    private var playerTurn : Boolean = true
    private val checker : WinnerChecker = WinnerChecker()
    private var endGame : Boolean = false

    fun newGame()
    {
        field = Array(3, {i -> Array(3, {j -> '+'})})
        playerTurn = true
        endGame = false
    }

    private fun mark(x : Int, y : Int)
    {
        if (field[x][y] != '+') {
           throw Exception("Bad choose")
        }

        if (playerTurn)
            field[x][y] = 'X'
        else
            field[x][y] = 'O'

        playerTurn = !playerTurn
    }

    fun makeTurn(x : Int, y: Int) : Array<Array<Char>>
    {
        mark(x, y)
        endGame = checker.goCheck(field)
        return field
    }

    fun endOfGame() : Boolean
    {
        return endGame
    }
}

public class WinnerChecker()
{
    var field : Array<Array<Char>> =   Array(3, {i -> Array(3, {j -> '+'})})

    private fun Horizontal(x : Int, y : Int) : Boolean
    {
        val lead : Char = field[x][y]
        return (field[x + 1][y] == lead && field[x + 2][y] == lead && lead != '+')
    }

    private fun Vertical(x : Int, y : Int) : Boolean
    {
        val lead : Char = field[x][y]
        return (field[x][y + 1] == lead && field[x][y + 2] == lead && lead != '+')
    }

    private  fun LDiagonal(x : Int, y : Int) : Boolean
    {
        val lead : Char = field[x][y]
        return (field[x + 1][y + 1] == lead && field[x + 2][y + 2] == lead && lead != '+')
    }

    private  fun RDiagonal(x : Int, y : Int) : Boolean
    {
        val lead : Char = field[x][y]
        return (field[x + 1][y - 1] == lead && field[x + 2][y - 2] == lead && lead != '+')
    }

    public fun goCheck(fieldG :  Array<Array<Char>>) : Boolean
    {
        field = fieldG

        return (Horizontal(0, 0) || Horizontal(0, 1) || Horizontal(0, 2) ||
                Vertical(0, 0) || Vertical(1, 0) || Vertical(2, 0) ||
                LDiagonal(0, 0) || RDiagonal(0, 2))
    }
}