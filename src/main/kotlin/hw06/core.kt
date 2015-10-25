package hw06

/**
 * Created by Jinx on 23.10.2015.
 */
public class Core() {

    private var stateArray = Array(3, { Array(3, {x -> ' '}) })

    public fun getMark(a: Int, b: Int, type: Boolean){
        if (type) stateArray[a][b] = 'x'
        else stateArray[a][b] = 'o'
    }

    public fun test(): Char {
        var counter = 0
        for (i in stateArray){
            if ((i[0] == i[1]) && (i[1] == i[2])) return i[0]
            if (i[0]!= ' ' && i[1] != ' ' && i[2] != ' ') counter += 1
        }
        for (i in 0..2)
            if ((stateArray[0][i] == stateArray[1][i]) && stateArray[1][i] == stateArray[2][i])
                return stateArray[0][i]
        if(stateArray[0][0] == stateArray[1][1] && stateArray[1][1] == stateArray[2][2])
            return stateArray[0][0];
        if(stateArray[0][2] == stateArray[1][1] && stateArray[1][1] == stateArray[2][0])
            return stateArray[0][2];
        if (counter == 3) return '3'
        return ' '
    }
    public fun stateArray(): Array<Array<Char>> {
        return stateArray
    }
}