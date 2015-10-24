package homework.hw06

import org.junit.Test
import kotlin.test.assertEquals

class HW06Test {

    val logic = GameLogic()
    //testing logic
    @Test fun isEndTest1(){
        logic.setArr(arrayOf('o','o','o','x','o','o','o','x','x'))
        assertEquals("o wins", logic.isEnd())
    }
    @Test fun isEndTest2(){
        logic.setArr(arrayOf('o','x','x','o','o','o','x','x','o'))
        assertEquals("o wins", logic.isEnd())
    }
    @Test fun isEndTest3(){
        logic.setArr(arrayOf('x','x','o','x','o','x','o','o','o'))
        assertEquals("o wins", logic.isEnd())
    }
    @Test fun isEndTest4(){
        logic.setArr(arrayOf('o','x','o','o','x','x','o','x','o'))
        assertEquals("o wins", logic.isEnd())
    }
    @Test fun isEndTest5(){
        logic.setArr(arrayOf('x','o','o','x','o','x','o','o','x'))
        assertEquals("o wins", logic.isEnd())
    }
    @Test fun isEndTest6(){
        logic.setArr(arrayOf('x','o','o','o','x','o','x','x','o'))
        assertEquals("o wins", logic.isEnd())
    }
    @Test fun isEndTest7(){
        logic.setArr(arrayOf('o','x','o','x','o','x','x','o','o'))
        assertEquals("o wins", logic.isEnd())
    }
    @Test fun isEndTest8(){
        logic.setArr(arrayOf('o','x','o','x','o','x','o','x','x'))
        assertEquals("o wins", logic.isEnd())
    }
    @Test fun isEndTest9(){
        logic.setArr(arrayOf('o','x','o','o','x','x','x','o','o'))
        assertEquals("Draw", logic.isEnd())
    }

}