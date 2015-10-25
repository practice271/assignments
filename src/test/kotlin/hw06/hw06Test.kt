package hw06

/**
 * Created by Jinx on 25.10.2015.
 */
import org.junit.Test
import kotlin.test.assertEquals

public class hw06Test(){
    @Test fun tictaktoeTest1(){
        val game = Core()
        game.getMark(0, 0, true)
        assertEquals(game.test(), ' ')
        game.getMark(1, 1, false)
        assertEquals(game.test(), ' ')
        game.getMark(2, 2, true)
        assertEquals(game.test(), ' ')
        game.getMark(0, 2, false)
        assertEquals(game.test(), ' ')
        game.getMark(2, 0, true)
        assertEquals(game.test(), ' ')
        game.getMark(1, 0, false)
        assertEquals(game.test(), ' ')
        game.getMark(2, 1, true)
        assertEquals(game.test(), 'x')
    }
    @Test fun tictaktoeTest2(){
        val game = Core()
        game.getMark(0, 0, true)
        assertEquals(game.test(), ' ')
        game.getMark(0, 2, false)
        assertEquals(game.test(), ' ')
        game.getMark(1, 1, true)
        assertEquals(game.test(), ' ')
        game.getMark(1, 2, false)
        assertEquals(game.test(), ' ')
        game.getMark(2, 0, true)
        assertEquals(game.test(), ' ')
        game.getMark(2, 2, false)
        assertEquals(game.test(), 'o')
    }
    @Test fun tictaktoeTest3(){
        val game = Core()
        game.getMark(0, 0, true)
        assertEquals(game.test(), ' ')
        game.getMark(0, 1, false)
        assertEquals(game.test(), ' ')
        game.getMark(0, 2, true)
        assertEquals(game.test(), ' ')
        game.getMark(1, 1, false)
        assertEquals(game.test(), ' ')
        game.getMark(1, 0, true)
        assertEquals(game.test(), ' ')
        game.getMark(1, 2, false)
        assertEquals(game.test(), ' ')
        game.getMark(2, 1, true)
        assertEquals(game.test(), ' ')
        game.getMark(2, 0, false)
        assertEquals(game.test(), ' ')
        game.getMark(2, 2, true)
        assertEquals(game.test(), '3')
    }
    @Test fun tictaktoeTest4(){
        val game = Core()
        game.getMark(0, 0, true)
        assertEquals(game.test(), ' ')
        game.getMark(0, 1, false)
        assertEquals(game.test(), ' ')
        game.getMark(2, 2, true)
        assertEquals(game.test(), ' ')
        game.getMark(0, 2, false)
        assertEquals(game.test(), ' ')
        game.getMark(1, 1, true)
        assertEquals(game.test(), 'x')
    }
}