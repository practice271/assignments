package hw09

import org.junit.Test
import kotlin.test.assertEquals

public class hw09Test2() : OutputTest() {

    @Test fun OptimalConverterHelloWorld() {
        val input = "Hello, world!"
        val res = Converter.optimalConverte(input)
        Interpreter.interpret(res)
        assertEquals(input, output.toString())
    }
    @Test fun InterpreterHelloWorld() {
        val code = "++++++++++[>+++++++>++++++++++>+++>++++<"+
                "<<<-]>++.>+.+++++++..+++.>>++++.<++.<+++"+
                "+++++.--------.+++.------.--------.>+."
        val res = "Hello, world!"
        Interpreter.interpret(code)
        assertEquals(res, output.toString())
    }
}