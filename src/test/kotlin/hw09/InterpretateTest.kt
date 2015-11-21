package hw09

/**
 * Created by Antropov Igor on 21.11.2015.
 */
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.OutputStream
import java.io.PrintStream
import kotlin.test.assertEquals


public class InterpretateTest(){


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

    public fun interpretate(program: String, input: String): String {
        val interpreteter = BrainfuckInterpreter()
        if (input != "") System.setIn(ByteArrayInputStream(input.toByteArray()))
        res.delete(0, res.length)
        interpreteter.interpratate(program)
        return res.toString()
    }

    @Test fun helloWorldTest() {
        assertEquals("Hello World!\n",
                interpretate("++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>" +
                        "+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.", ""))
    }

    @Test fun readTest() {
        assertEquals("Made by Antropov Igor",
                interpretate("+++++++++++++++++++++[>,.<-]", "Made by Antropov Igor"))
    }

    @Test fun helloWorldTest2(){
            assertEquals("Hello World!\n", interpretate("+++++++++++++++++++++++++++++++++++++++++++++" +
                    "+++++++++++++++++++++++++++.+++++++++++++++++" +
                    "++++++++++++.+++++++..+++.-------------------" +
                    "---------------------------------------------" +
                    "---------------.+++++++++++++++++++++++++++++" +
                    "++++++++++++++++++++++++++.++++++++++++++++++" +
                    "++++++.+++.------.--------.------------------" +
                    "---------------------------------------------" +
                    "----.-----------------------.", ""))
    }



}