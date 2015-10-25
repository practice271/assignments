package hw06

/**
 * Created by Jinx on 23.10.2015.
 */


fun correntScreen(stateArray: Array<Array<Char>>){
    for (i in 0..2){
        println("${stateArray[i][0]}|${stateArray[i][1]}|${stateArray[i][2]}")
        if (i != 2) println("-|-|-")
    }
}

fun main(args: Array<String>) {
    var core = Core()
    var line: String?
    var a : Int
    var b: Int
    var counter : Boolean = true
    do {
        line = readLine()
        if (line != null) {
            a = line[0]?.toInt() - 49
            b = line[2]?.toInt() - 49
            core.getMark(a, b, counter)
            counter = !counter
            correntScreen(core.stateArray())
            when (core.test()){
                'x' -> println("1st won")
                'o' -> println("2nd won")
                '3' -> println("draw")
                else -> println("next turn")
            }
        }
        else println("man its your turn")
    } while(core.test() == ' ')
}