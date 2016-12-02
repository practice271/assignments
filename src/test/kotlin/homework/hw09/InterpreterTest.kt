package homework.hw09

import org.junit.Test
import kotlin.test.assertEquals


class InterpreterTest {

    //the differencies are in way of output


    @Test fun helloWorldTest(){
        val interpreter = Brainfuck("++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++." +
                "                    .+++.>>.<-.<.+++.------.--------.>>+.>++.")
        assertEquals("Hello World!\n",interpreter.interpret(null))
    }

    @Test fun incorrectCodeTest() {
        try {
            Brainfuck("[+]].").interpret(null)
        } catch (e: Parser.IncorrectBracketsSequence) {
            assert(true)
        }catch(e :Exception){
            assert(false)
        }
    }
        //simple input test
    @Test fun InputTest(){
        val interpreter = Brainfuck(",.")
        assertEquals("0", interpreter.interpret("0"))
    }

    @Test fun fibTest()
    {
        val  program = Brainfuck("+++++++++++"+
                                ">+>>>>++++++++++++++++++++++++++++++++++++++++++++"+
                                ">++++++++++++++++++++++++++++++++<<<<<<[>[>>>>>>+>"+
                                "+<<<<<<<-]>>>>>>>[<<<<<<<+>>>>>>>-]<[>++++++++++[-"+
                                "<-[>>+>+<<<-]>>>[<<<+>>>-]+<[>[-]<[-]]>[<<[>>>+<<<"+
                                "-]>>[-]]<<]>>>[>>+>+<<<-]>>>[<<<+>>>-]+<[>[-]<[-]]"+
                                ">[<<+>>[-]]<<<<<<<]>>>>>[+++++++++++++++++++++++++"+
                                "+++++++++++++++++++++++.[-]]++++++++++<[->-<]>++++"+
                                "++++++++++++++++++++++++++++++++++++++++++++.[-]<<"+
                                "<<<<<<<<<<[>>>+>+<<<<-]>>>>[<<<<+>>>>-]<-[>>.>.<<<"+
                                "[-]]<<[>>+>+<<<-]>>>[<<<+>>>-]<<[<+>-]>[<+>-]<<<-]")
               val res = "1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89"
        assertEquals(res, program.interpret(null))
    }
}
