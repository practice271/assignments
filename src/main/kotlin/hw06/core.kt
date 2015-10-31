package hw06

/**
 * Created by Antropov Igor on 23.10.2015.
 */

public class Core() {

    private var stateArray = Array(3, { Array(3, {' '}) })

    public fun getMark(a: Int, b: Int, type: Boolean): Boolean{
        if (stateArray[a][b] != ' ') return true
        if (type) stateArray[a][b] = 'x'
        else stateArray[a][b] = 'o'
        return false
    }

    public fun test(): Char {
        var counter = 0
        //checking lines
        for (i in stateArray){
            if (i[0] == i[1] && i[1] == i[2] && i[0] != ' ') return i[0]
            if (i[0]!= ' ' && i[1] != ' ' && i[2] != ' ') counter++
        }
        //checking columns
        for (i in 0..2)
            if ((stateArray[0][i] == stateArray[1][i])
                    && stateArray[1][i] == stateArray[2][i]
                    && stateArray[0][i] != ' ')
                return stateArray[0][i]
        //checking diagonal
        if(stateArray[0][0] == stateArray[1][1] && stateArray[1][1] == stateArray[2][2] && stateArray[0][0] != ' ')
            return stateArray[0][0];
        if(stateArray[0][2] == stateArray[1][1] && stateArray[1][1] == stateArray[2][0] && stateArray[1][1] != ' ')
            return stateArray[0][2];
        //checking draw
        if (counter == 3) return '3'
        return ' '
    }
    public fun stateArray(): Array<Array<Char>> {
        return stateArray
    }
}