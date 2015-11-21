package hw09

import org.junit.Test
import kotlin.test.assertEquals

public class hw09Test3() : OutputTest() {
    @Test fun OptimalConverterHelloWorld() {
        val input = "Hello, world!"
        val res = Converter.optimalConverte(input)
        Interpreter.interpret(res)
        assertEquals(input, output.toString())
    }
}