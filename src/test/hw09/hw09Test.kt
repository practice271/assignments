package hw09

import org.junit.Test
import kotlin.test.assertEquals

class hw09Test{
    @Test fun HelloWorld_Interpreter(){
        val program = ">++++++++[<++++++++>-]<++++++++."+
                ">+++++[<+++++>-]<++++.>++[<++>-]<+++..+++."+
                ">++++++++[<-------->-]<---.>+++[<--->-]<---."+
                ">+++++++[<+++++++>-]<++++++.>++++[<++++>-]<++++++++.+++."+
                ">++[<-->-]<--.>++[<-->-]<----.>++++++++[<-------->-]<---."
        val in_str = ""
        val result = Interpreter_brainfuck(program, in_str).run()
        assertEquals("Hello, World!",result)
    }
    @Test fun Same_symbols_Interpreter(){
        val program = ">+++++++++[<+++++++++>-]<+++++++++++++++++.+++.."
        val in_str = ""
        val result = Interpreter_brainfuck(program, in_str).run()
        assertEquals("bee",result)
    }
    @Test fun incorrect_input_Interpreter(){
        val program = "98>+++++++++[<+++++++++>-]<+++++++++++++++++.+++.."
        val in_str = ""
        val result = Interpreter_brainfuck(program, in_str).run()
        assertEquals("Input error",result)
    }
    @Test fun memory_Interpreter(){
        val program = "<>+++++++++[<+++++++++>-]<+++++++++++++++++.+++.."
        val in_str = ""
        val result = Interpreter_brainfuck(program, in_str).run()
        assertEquals("Going beyond memory",result)
    }
    @Test fun Insert_Interpreter(){
        var program = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++"+
                ".>+.+++++++..,."
        val in_str = "o"
        val result = Interpreter_brainfuck(program, in_str).run()
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
    @Test fun HelloWorld_compiler(){
        val program = ">++++++++[<++++++++>-]<++++++++."+
                ">+++++[<+++++>-]<++++.>++[<++>-]<+++..+++."+
                ">++++++++[<-------->-]<---.>+++[<--->-]<---."+
                ">+++++++[<+++++++>-]<++++++.>++++[<++++>-]<++++++++.+++."+
                ">++[<-->-]<--.>++[<-->-]<----.>++++++++[<-------->-]<---."
        val compiler = Compiler_brainfuck()
        val result = compiler.compile(program,"")
        assertEquals("Hello, World!",result)
    }
    @Test fun bee_compiler(){
        val program = ">+++++++++[<+++++++++>-]<+++++++++++++++++.+++.."
        val compiler = Compiler_brainfuck()
        val result = compiler.compile(program,"")
        assertEquals("bee",result)
    }
}
