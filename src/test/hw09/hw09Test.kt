package hw09

import org.junit.Test
import kotlin.test.assertEquals

class hw09Test{
    @Test fun HelloWorld_interpritator(){
        val program = ">++++++++[<++++++++>-]<++++++++."+
                ">+++++[<+++++>-]<++++.>++[<++>-]<+++..+++."+
                ">++++++++[<-------->-]<---.>+++[<--->-]<---."+
                ">+++++++[<+++++++>-]<++++++.>++++[<++++>-]<++++++++.+++."+
                ">++[<-->-]<--.>++[<-->-]<----.>++++++++[<-------->-]<---."
        val in_str = ""
        val result = Interpritator_brainfuck(program, in_str).run()
        assertEquals("Hello, World!",result)
    }
    @Test fun Same_symbols_interpritator(){
        val program = ">+++++++++[<+++++++++>-]<+++++++++++++++++.+++.."
        val in_str = ""
        val result = Interpritator_brainfuck(program, in_str).run()
        assertEquals("bee",result)
    }
    @Test fun Insert_interpritator(){
        var program = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++"+
                ".>+.+++++++..,."
        val in_str = "o"
        val result = Interpritator_brainfuck(program, in_str).run()
        assertEquals("Hello",result)
    }
    @Test fun HelloWorld_ASCII(){
        val program = ">++++++++[<++++++++>-]<++++++++."+
                ">+++++[<+++++>-]<++++.>++[<++>-]<+++..+++."+
                ">++++++++[<-------->-]<---.>+++[<--->-]<---."+
                ">+++++++[<+++++++>-]<++++++.>++++[<++++>-]<++++++++.+++."+
                ">++[<-->-]<--.>++[<-->-]<----.>++++++++[<-------->-]<---."
        val text = "Hello, World!"
        val result = Convertor_to_brainfuck(text).run()
        assertEquals(program,result)
    }
    @Test fun To_be_ASCII(){
        var program = ">+++++++++[<+++++++++>-]<+++."+
                ">+++++[<+++++>-]<++.>++++++++[<-------->-]<---------------."+
                ">++++++++[<++++++++>-]<++.+++.>++++++++[<-------->-]<-----."+
                ">++++++++[<++++++++>-]<+++++++++++++++.+++.>+++++++++[<--------->-]<-."+
                ">++++++++[<++++++++>-]<++++++++++++++.+.>++[<++>-]<+.>+++++++++[<--------->-]<---."+
                ">+++++++++[<+++++++++>-]<+++.>++[<-->-]<-.>++++++++[<-------->-]<---------------."+
                ">++++++++[<++++++++>-]<++.+++."
        val text = "To be or not to be"
        val result = Convertor_to_brainfuck(text).run()
        assertEquals(program,result)
    }
    @Test fun bee_ASCII(){
        var program = ">+++++++++[<+++++++++>-]<+++++++++++++++++.+++.."
        val text = "bee"
        val result = Convertor_to_brainfuck(text).run()
        assertEquals(program,result)
    }


}
