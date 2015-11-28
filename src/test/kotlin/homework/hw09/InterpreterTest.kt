package homework.hw09

import org.junit.Test
import kotlin.test.assertEquals


class InterpreterTest {

    //the differencies are in way of output


    @Test fun helloWorldTest(){
        val interpreter = Brainfuck("++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.")
        assertEquals("Hello World!\n",interpreter.interpret(""))
    }
    //simple input test
    @Test fun InputTest(){
        val interpreter = Brainfuck(",.")
        assertEquals("0", interpreter.interpret("0"))

    }
}