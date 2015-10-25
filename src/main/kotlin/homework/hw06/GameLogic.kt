package homework.hw06

public class GameLogic() {

    fun areEqual(a: Char, b: Char, c: Char): Boolean {
        return (a == b) && (b == c) && (a == c)
    }

    private var arr = Array(9, { ' ' })

    var crossTurn = true

    fun insert(value: Char, i: Int): Boolean {

        if (arr[i] != ' ') {
            crossTurn = !crossTurn
            return false
        } else {
            arr[i] = value
            return true
        }
    }

    fun isEnd(): String {

        when {

            arr[0] != ' ' && areEqual(arr[0], arr[1], arr[2]) -> return ("${arr[0]} wins")
            arr[3] != ' ' && areEqual(arr[3], arr[4], arr[5]) -> return ("${arr[3]} wins")
            arr[6] != ' ' && areEqual(arr[6], arr[7], arr[8]) -> return ("${arr[6]} wins")
            arr[0] != ' ' && areEqual(arr[0], arr[3], arr[6]) -> return ("${arr[0]} wins")
            arr[1] != ' ' && areEqual(arr[1], arr[4], arr[7]) -> return ("${arr[1]} wins")
            arr[2] != ' ' && areEqual(arr[2], arr[5], arr[8]) -> return ("${arr[2]} wins")
            arr[0] != ' ' && areEqual(arr[0], arr[4], arr[8]) -> return ("${arr[0]} wins")
            arr[2] != ' ' && areEqual(arr[2], arr[4], arr[6]) -> return ("${arr[2]} wins")
            else -> {
                if (!arr.contains(' ')) return "Draw"
                else return ""
            }
        }
    }

    fun getArr(): Array<Char> {
        return arr
    }

    fun setArr(array: Array<Char>) {
        arr = array
    } // only for testing

    fun restart() {
        for (i in 0..8) arr[i] = ' '
        crossTurn = true
    }
}

