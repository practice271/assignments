package hw06

import java.util.*

/**
 * Created by Antropov Igor on 23.10.2015.
 */


fun currentScreen(stateArray: Array<Array<Char>>) {
    for (i in 0..2) {
        println("${stateArray[i][0]}|${stateArray[i][1]}|${stateArray[i][2]}")
        if (i != 2) println("-|-|-")
    }
}

fun main(args: Array<String>) {
    var core = Core()
    var a: Int
    var b: Int
    var test: Char = ' '
    var error: Boolean
    val s = Scanner(System.`in`)
    var counter: Boolean = true
    println("Input numbers like a b, where a and b can be 1 2 3.")
    do {
        a = s.nextInt() - 1
        b = s.nextInt() - 1
        if (a <= 2 && a >= 0 && b >= 0 && b <= 2) {
            error = core.getMark(a, b, counter)
            if (!error) {
                counter = !counter
                currentScreen(core.stateArray())
                test = core.test()
                when (test) {
                    'x' -> println("1st won")
                    'o' -> println("2nd won")
                    '3' -> println("draw")
                    else -> println("next turn")
                }
            } else println("This cell is not empty. Chose another one.")
        } else println("Input numbers like a b, where a and b can be 1 2 3.")
    } while (test == ' ')
}