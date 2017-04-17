package hw06

/* Console game  made by Guzel Garifullina
   Estimated time  1 hour
   real time       1 hour
*/

open public class LogicalButton(){
    private var isSel = false
    public fun isSelected (): Boolean{
        return isSel
    }
    private var label = '_'
    public fun getLabel(): Char{
        return label
    }
    public fun setLabel(ch : Char){
        label = ch
        isSel = true
    }
    public fun clean(){
        label = '_'
        isSel = false
    }
}
open public class LogicalButtonField (protected val size : Int ) {
    private var players = 'X'
    public fun getPlayer() : Char {
        return  players
    }
    private val size2 = size * size
    protected val map = Array(size2, { LogicalButton()})
    private var place  = size2

    public  fun isTie(): Boolean {
        return place == 0
    }
    protected  fun nextPlayer(){
        when (players){
            'X' -> players = 'O'
            'O' -> players = 'X'
            else -> throw Exception("System is broken")
        }
    }
    // only for 3 * 3
    public  fun isVictory() : Boolean{
        fun isVictoryCombination (a : Int, b : Int, c : Int) : Boolean{
            val first = map[a].getLabel()
            return (first != '_') &&
                    (first == map[b].getLabel()) && (first == map[c].getLabel())
        }
        for (i in 0 .. (size - 1)){
            if (isVictoryCombination(i * 3, i * 3 + 1, i * 3 + 2)
                    || isVictoryCombination(i, i + 3 , i + 6)){
                return true
            }
        }
        return isVictoryCombination(0, 4, 8) || isVictoryCombination(2, 4, 6)
    }
    public fun isGameOver(): Boolean{
        return isTie() || isVictory()
    }
    public fun isSelected (i : Int): Boolean{
        return map[i].isSelected()
    }
    public fun setLabel (i : Int, ch : Char){
        map[i].setLabel(ch)
        nextPlayer()
        place --
    }
    public fun inRange (i : Int): Boolean {
        return (i >= 0 ) && (i < size2)
    }
    public fun clear (){
        for (i in 0 .. (size2 - 1)){
            map[i].clean()
        }
        place = size2
    }
}