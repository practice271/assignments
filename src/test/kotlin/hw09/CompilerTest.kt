package hw09

import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.OutputStream
import java.io.PrintStream
import kotlin.test.assertEquals


public class CompilerTest {

    var res = StringBuilder()
    var printMethod: PrintStream

    init {
        printMethod = PrintStream(object : OutputStream() {
            override fun write(b: Int) {
                res.append(b.toChar())
            }
        })
        System.setOut(printMethod)
    }

    @Test
    fun helloWorld() {
        test("++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>" +
                ".>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.",
                "", "Hello World!\n")
    }

    @Test
    fun simpleCycle() {
        test("[+].", "", "${0.toChar()}")
    }

    @Test
    fun inOut() {
        test(",>,>,.<.<.", "tab", "bat")
    }

    @Test
    fun bugTriggerHelloWorld() {
        test(">++++++++[-<+++++++++>]<.>>+>-[+]++>++>+++[>[->+++<<+++>]<<]>-----.>->+++..+++.>"+
                "-.<<+[>[+>+]>>]<--------------.>>.+++.------.--------.>+.>+.",
                "", "Hello World!\n")
    }
    @Test
    fun moveTwoCellsRight() {
        test("+++++>>[-]<<[->>+<<]>>.", "", "${5.toChar()}")
    }

    public fun test(program: String, input: String, expected: String) {
        val arr = BrainFCompiler().compile(program)
        if (input != "") System.setIn(ByteArrayInputStream(input.toByteArray()))
        res.delete(0, res.length)
        val methods = ByteArrayClassLoader().loadClass("Compiler", arr)?.methods
        if (methods == null || methods.isEmpty()) {
            throw Exception()
        }
        for (method in methods) {
            if (method.name != "main") continue
            method.invoke(null, arrayOf<String>())
        }
        assertEquals(res.toString(), expected)
    }
}
