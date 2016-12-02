package hw09

import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import java.io.PrintStream
import java.io.PrintWriter
import kotlin.test.assertEquals

class InterpreterTests
{
    @Test fun Test1() {
        val out = PrintWriter("helloWorld.bf")
        val code =  "++++++++++[>+++++++>++++++++++>+++>++++<" +
                    "<<<-]>++.>+.+++++++..+++.>>++++.<++.<+++" +
                    "+++++.--------.+++.------.--------.>+."
        out.println(code)
        out.close()

        val inter : Interpreter = Interpreter("helloWorld.bf")
        inter.start()

        val res = "Hello, world!"

        assertEquals(res, inter.output)
    }

    /*@Test fun Test2() {
        val out = PrintWriter("fib.bf")
        val code =  "+++++++++++"+
                ">+>>>>++++++++++++++++++++++++++++++++++++++++++++"+
                ">++++++++++++++++++++++++++++++++<<<<<<[>[>>>>>>+>"+
                "+<<<<<<<-]>>>>>>>[<<<<<<<+>>>>>>>-]<[>++++++++++[-"+
                "<-[>>+>+<<<-]>>>[<<<+>>>-]+<[>[-]<[-]]>[<<[>>>+<<<"+
                "-]>>[-]]<<]>>>[>>+>+<<<-]>>>[<<<+>>>-]+<[>[-]<[-]]"+
                ">[<<+>>[-]]<<<<<<<]>>>>>[+++++++++++++++++++++++++"+
                "+++++++++++++++++++++++.[-]]++++++++++<[->-<]>++++"+
                "++++++++++++++++++++++++++++++++++++++++++++.[-]<<"+
                "<<<<<<<<<<[>>>+>+<<<<-]>>>>[<<<<+>>>>-]<-[>>.>.<<<"+
                "[-]]<<[>>+>+<<<-]>>>[<<<+>>>-]<<[<+>-]>[<+>-]<<<-]"
        out.println(code)
        out.close()

        val inter : Interpreter = Interpreter("fib.bf")
        inter.start()

        val res = "1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89"

        assertEquals(res, inter.output)
    }*/
}