package hw09.BrainFuck

/* Tests for BrainFuck  made by Guzel Garifullina
   Estimated time  1 hour
   real time       1 hour
*/

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class BrainfuckTests {
    private val outContent =  ByteArrayOutputStream()
    private val errContent  = ByteArrayOutputStream()

    @Before
    public fun setUpStreams() {
        System.setOut(PrintStream(outContent));
        System.setErr(PrintStream(errContent));
    }

    @After
    public fun cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
    private val strHello = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++" +
            ".>+.+++++++..+++.>++.<<+++++++++++++++.>.+++." +
            "------.--------.>+.>."
    private val codeHello = Code(strHello)

    @Test fun GetCode(){
        val str = "[+]>>><+++[[+-]]>----+"
        val code = Code (str)
        val res = code.toString()
        val expectedResult ="0= 1\n>= 2\n+= 3\nwhile= 1\n" +
                "while= 1\n+= 0\n]= 1\n]= 1\n>= 1\n+= -3\n"
        assertEquals(expectedResult, res)
    }
    @Test fun TestInterpreter(){
        Interpreter(codeHello).interpret()
        val expectedResult ="Hello World!${10.toChar()}"
        assertEquals(expectedResult, outContent.toString())
    }
    @Test fun Compiler(){
        val com = Compiler(codeHello, "forTesting")
        val expectedResult ="Hello World!${10.toChar()}"
        val classByteArray = com.generateClassByteArray()
        com.saveToDisk(classByteArray)
        com.loadClassAndRun(classByteArray)
        assertEquals(expectedResult, outContent.toString())
    }
}