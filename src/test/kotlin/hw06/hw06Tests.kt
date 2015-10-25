package hw06

import java.util.ArrayList
import org.junit.Test
import kotlin.test.assertEquals

class hw06Tests {
    val simpleArray : ArrayList<ArrayList<Boolean?>> = arrayListOf(
            arrayListOf<Boolean?>(false,false,null),
            arrayListOf<Boolean?>(true,true,null),
            arrayListOf<Boolean?>(null,null,null))
    var simpleGame : Game = Game(simpleArray, 4, null)
    @Test fun simpleGameTest1 () {
        simpleGame.move(0,2,false)
        assertEquals(simpleGame.winner, false)
    }

    @Test fun simpleGameTest2 () {
        simpleGame.move(0,2,true)
        assertEquals(simpleGame.winner, null)
    }

    val diagonalArray : ArrayList<ArrayList<Boolean?>> = arrayListOf(
            arrayListOf<Boolean?>(false,true,null),
            arrayListOf<Boolean?>(true,false,null),
            arrayListOf<Boolean?>(null,null,null))
    var diagonal : Game = Game(diagonalArray, 4, null)
    @Test fun diagonalGame1 () {
        diagonal.move(2,2,false)
        assertEquals(diagonal.winner, false)
    }
    @Test fun diagonalGame2 () {
        diagonal.move(0,2,true)
        assertEquals(diagonal.winner, null)
    }

    val array : ArrayList<ArrayList<Boolean?>> = arrayListOf(
            arrayListOf<Boolean?>(false,null,null),
            arrayListOf<Boolean?>(null,null,null),
            arrayListOf<Boolean?>(null,null,null))
    var filledCell : Game = Game(array, 1, null)
    @Test fun tryToFillFilledCell () {
        filledCell.move(0,0,true)
        assertEquals(filledCell.map[0][0], false)
    }

    var game = ConsoleGame()
    val map1 = println("|x | _ | _|\n|_ | _ | _|\n|_ | _ | _|\n")
    @Test fun gameStart () {
        game.move(0,0,false)
        assertEquals(game.printMap(), map1)
    }

    val map2 = println("|x | 0 | x|\n|0 | 0 | x|\n|x | x | x|\n")
    @Test fun fillLastCell () {
        game.move(0,0,false)
        game.move(0,1,true)
        game.move(0,2,false)
        game.move(1,0,true)
        game.move(2,0,false)
        game.move(1,1,true)
        game.move(1,2,false)
        game.move(2,2,true)
        game.move(2,1,false)
        assertEquals(game.printMap(), map2)
    }

    val map3 = println("|_ | _ | _|\n|_ | _ | _|\n|_ | x | _|\n")
    @Test fun filledAllCells () {
        game.move(0,0,false)
        game.move(0,1,true)
        game.move(0,2,false)
        game.move(1,0,true)
        game.move(2,0,false)
        game.move(1,1,true)
        game.move(1,2,false)
        game.move(2,2,true)
        game.move(2,1,false)
        game.move(2,1,false)
        assertEquals(game.printMap(), map3)
    }

    val map4 = println("|_ | _ | _|\n|_ | _ | _|\n|_ | x | _|\n")
    @Test fun afterGameIsOver () {
        game.move(0,0,false)
        game.move(0,1,true)
        game.move(1,0,false)
        game.move(0,2,true)
        game.move(2,0,false)
        game.move(2,0,false)
        assertEquals(game.printMap(), map4)
    }
}