package homework6

import org.junit.Test
import kotlin.test.assertEquals

public class HW06 {
    fun toStr(f: Array<Array<LogicGames.Mark>>):String{
        var s = ""
        f.forEach {
            it.forEach { s += it }
        }
        return s
    }
    val newgame = LogicGames()
    @Test
    fun Test1(){
        newgame.field[0][0] = LogicGames.Mark.X
        newgame.field[1][1] = LogicGames.Mark.X
        newgame.field[2][2] = LogicGames.Mark.X
        newgame.checkState()
        assertEquals(newgame.state, LogicGames.State.Win)
    }
    fun Test2(){
        newgame.field[0][0] = LogicGames.Mark.O
        newgame.field[0][1] = LogicGames.Mark.X
        newgame.field[0][2] = LogicGames.Mark.O
        newgame.field[1][0] = LogicGames.Mark.X
        newgame.field[1][1] = LogicGames.Mark.X
        newgame.field[1][2] = LogicGames.Mark.O
        newgame.field[2][0] = LogicGames.Mark.X
        newgame.field[2][1] = LogicGames.Mark.O
        newgame.field[2][2] = LogicGames.Mark.X
        newgame.checkState()
        assertEquals(newgame.state, LogicGames.State.Standoff)
    }
    val newgame1 = LogicGames()
    fun Test3(){
        newgame1.move(0,0)
        newgame1.checkState()
        newgame1.move(0,2)
        newgame1.checkState()
        newgame1.move(1,0)
        newgame1.checkState()
        newgame1.move(2,2)
        newgame1.checkState()
        assertEquals(toStr(newgame1.field), "XIOXIIIIO")
    }
    fun Test4(){
        assertEquals(newgame1.state, LogicGames.State.Player1)
    }
    fun Test5(){
        newgame1.move(2,0)
        assertEquals(newgame1.state, LogicGames.State.Win)
        assertEquals(newgame1.winner, "Player1")
    }
    val newgame2 = LogicGames()
    fun Test6(){
        newgame2.move(1,2)
        newgame2.checkState()
        newgame2.move(1,1)
        newgame2.checkState()
        newgame2.move(2,2)
        newgame2.checkState()
        newgame2.move(0,2)
        newgame2.checkState()
        newgame2.move(2,0)
        newgame2.checkState()
        newgame2.move(2,1)
        newgame2.checkState()
        newgame2.move(0,0)
        newgame2.checkState()
        newgame2.move(0,1)
        newgame2.checkState()
        assertEquals(toStr(newgame2.field), "XOOIOXXOX")
        assertEquals(newgame2.state, LogicGames.State.Win)
        assertEquals(newgame2.winner, "Player2")
    }
}