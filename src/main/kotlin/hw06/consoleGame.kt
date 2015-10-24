package hw06

/* Console game  made by Guzel Garifullina
   Estimated time  1 hour
   real time       1 hour
*/
import java.util.Scanner

public class CButtonField() : LogicalButtonField(size = 3) { //client
    public fun select (i : Int, j : Int ){
        val index = (i - 1) * 3 + j - 1
        when {
            ! inRange(index) -> println ("Indexes unknown, please write again")
            isSelected(index)  -> println("This cell's already chosen")
            else -> {
                setLabel(index, getPlayer())
                showField()
            }
        }
    }
    private fun showField(){
        for (i in 0 ..(size - 1)){
            for (j in 0.. (size - 1)){
                print(map[3 * i + j].getLabel())
            }
            println()
        }
       when {
            isVictory() -> {
                nextPlayer()
                println ("Player ${getPlayer()} wins")
            }
            isTie()     -> println ("Congratulations!\nIt's a tie")
       }
    }
}

public class consoleGame() {
    val field = CButtonField()
    init {
        while (!field.isGameOver()) {
            println()
            println("Player ${field.getPlayer()} move")
            val s = Scanner(System.`in`)
            val a = s.nextInt()
            val b = s.nextInt()
            field.select(a, b)
        }
    }
}
/*fun main(args : Array<String>) {
    consoleGame()
}*/
