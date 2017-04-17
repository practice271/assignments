package hw06

import kotlin.internal.getProgressionFinalElement

public class consoleGame()
{
    val game : Game = Game()
    var field : Array<Array<Char>> = Array(3, {i -> Array(3, {j -> '+'})})

    private fun startGame()
    {
        game.newGame()
    }

    private fun playerTurn(numb : Int)
    {
        var x : String
        var y : String
        print("--- Turn Player $numb ---\nEnter x: ")
        x = readLine() ?: ""
        print("Enter y: ")
        y = readLine() ?: ""
        if (x == "" || y == "")
            throw Exception("Wrong coordination")
        game.makeTurn(x.toInt(), y.toInt())
        field = game.getField()
    }

    fun consoleGame()
    {
        var numbPlayer : Int = 1
        startGame()

        while(!game.endOfGame())
        {
            try {
                playerTurn(numbPlayer)

                printField()

                if (numbPlayer == 1)
                    numbPlayer++
                else
                    numbPlayer--
            }
            catch(e : Exception)
            {
                println("Wrong input. Repeat you choose\n")
            }
        }

        println("Player $numbPlayer lose=(")
    }

    private fun printField()
    {
        for (i in 0..2)
        {
            for (j in 0..2)
                print(field[i][j])
            println()
        }
        println()
    }
}

/*fun main(args : Array<String>)
{
    val gameInterface : consoleGame = consoleGame()
    gameInterface.consoleGame()
}*/