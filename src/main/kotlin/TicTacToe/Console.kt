package TicTacToe
import java.util.*

/**
 * The tic-tac-toe on infinity field.
 */

public class Console: Logic() {

    public fun readValue(coordX: Int, coordY:Int,  number: Int) {
        if (coordX > fieldSizeHorizontal - 1) { resize(coordX, 1)}
        if (coordY > fieldSizeVertical - 1)   { resize(coordY, 0)}
        if (number % 2 == 0) { field[coordX][coordY] = 'X'} else {field[coordX][coordY] = 'O'}
    }

    public fun printMessage(n: Int) {
        if(n % 2 == 0) { println("===================Your turn, X===================\n") }
        else { println("===================Your turn, O===================\n") }
    }

    public fun startGame(game: Console) {
        var dataX: Int
        var dataY: Int
        var numberTurn: Int = 0 // 0, 1, 2..
        var sc: Scanner = Scanner(System.`in`)

        while(!gameOver) {
            game.printMessage(numberTurn)
            println("Coord on X: ")
            dataX = sc.nextInt()
            println("Coord on Y: ")
            dataY = sc.nextInt()
            game.readValue(dataX, dataY, numberTurn)
            game.checkWin()
            if (gameOver) { println("Congratulations, " + winner +
                    "!\n" + "===================GAME OVER===================") }
            numberTurn++
        }
    }
}