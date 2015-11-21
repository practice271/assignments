package hw09

import org.junit.Test
import java.util.Stack
import kotlin.test.assertEquals

class InterpreterTest {
    @Test
    fun helloWorld() {
        test("++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.",
                Stack<Char>(), "Hello World!\n")
    }

    @Test
    fun simpleCycle() {
        test("[+].", Stack<Char>(), "${0.toByte().toChar()}")
    }

    @Test
    fun inOut() {
        val inp = Stack<Char>()
        "bat".forEach { inp.push(it) }
        test(",>,>,.<.<.", inp, "bat")
    }

    @Test
    fun bugTriggerHelloWorld() {
        test(">++++++++[-<+++++++++>]<.>>+>-[+]++>++>+++[>[->+++<<+++>]<<]>-----.>->+++..+++.>"+
                "-.<<+[>[+>+]>>]<--------------.>>.+++.------.--------.>+.>+.",
                Stack<Char>(), "Hello World!\n")
    }
    @Test
    fun moveTwoCellsRight() {
        test("+++++>>[-]<<[->>+<<]>>.", Stack<Char>(), "${5.toByte().toChar()}")
    }

    private fun test(code: String, input: Stack<Char>, expected: String) {
        val interp = BrainFInterpreter(30000)
        val res = interp.interpret(code, input)
        assertEquals(expected, res)
    }
}