package HomeWork.Hw06

import homework.Hw06.Game
import org.junit.Test
import kotlin.test.assertEquals
import java.io.Reader

public class Hw06Test{
    @Test fun Test1(){
        val game = Game()
        game.writeMark(Pair(1,1))
        game.writeMark(Pair(2,5))
        game.writeMark(Pair(1,2))
        game.writeMark(Pair(2,6))
        game.writeMark(Pair(5,1))
        game.writeMark(Pair(2,7))
        game.writeMark(Pair(11,2))
        game.writeMark(Pair(2,8))
        game.writeMark(Pair(1,10))
        game.writeMark(Pair(2,9))
        val res = game.winCheck(Pair(2,9))
        assertEquals(res, "0") //means 0 is winner
    }

    @Test fun Test2(){
        val game = Game()
        game.writeMark(Pair(1,1))
        game.writeMark(Pair(2,5))
        game.writeMark(Pair(1,2))
        game.writeMark(Pair(2,9))
        game.writeMark(Pair(1,3))
        game.writeMark(Pair(2,10))
        game.writeMark(Pair(1,4))
        game.writeMark(Pair(2,11))
        game.writeMark(Pair(1,5))
        val res = game.winCheck(Pair(1,5))
        assertEquals(res, "X") //means X is winner
    }
}
