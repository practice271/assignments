package hw09

/**
 * Created by Antropov Igor on 21.11.2015.
 */
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.OutputStream
import java.io.PrintStream
import kotlin.test.assertEquals


public class CompilerTest() {

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

    public fun compile(program: String, input: String): String {
        val arr = BrainfuckCompiler().generateClassByteArray(program)
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
        return res.toString()
    }

    @Test fun helloWorldTest() {
        assertEquals("Hello World!\n",
                compile("++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>" +
                        "+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.", ""))
    }

    @Test fun readTest() {
        assertEquals("Made by Antropov Igor", compile("+++++++++++++++++++++[>,.<-]", "Made by Antropov Igor"))
    }

    @Test fun helloWorldTest2() {
        assertEquals("Hello World!\n",
                compile("+++++++++++++++++++++++++++++++++++++++++++++" +
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