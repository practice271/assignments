package hw09

/* Tests for BrainFuck  made by Guzel Garifullina
   Estimated time  30 minutes
   real time       30 minutes
*/

import hw09.Code
import hw09.Interpreter
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class GeneralTests {
    private val outContent = ByteArrayOutputStream()
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
    private val codeHello = Code(BrainFuckCode(strHello))

    @Test fun GetCodeBrainFuck(){
        val str = "[+]>>><+++[[+-]]>----+"
        val code = Code (BrainFuckCode(str))
        val res = code.toString()
        val expectedResult ="0= 1\n>= 2\n+= 3\nwhile= 1\n" +
                "while= 1\n+= 0\n]= 1\n]= 1\n>= 1\n+= -3\n"
        assertEquals(expectedResult, res)
    }
    @Test fun GetCodePetooh(){
        val str = "KoKoKoKoKoKoKoKoKoKo Kud-Kudah" +
                "KoKoKoKoKoKoKoKo kudah kO kud-Kudah Kukarek kudah" +
                "kOkOkOkO kukarek"
        val code = Code (PetoohCode(str))
        val res = code.toString()
        val expectedResult ="+= 10\nwhile= 1\n>= 1\n+= 8\n>= -1\n+= -1\n" +
        "]= 1\n>= 1\n.= 1\n>= -1\n+= -4\n,= 1\n"
        assertEquals(expectedResult, res)
    }
    @Test fun TestInterpreter(){
        Interpreter(codeHello).interpret()
        val expectedResult ="Hello World!${10.toChar()}"
        assertEquals(expectedResult, outContent.toString())
    }
    @Test fun Compiler(){
        val com = Compiler(codeHello, "Brainfuck")
        val expectedResult ="Hello World!${10.toChar()}"
        val classByteArray = com.generateClassByteArray()
        com.saveToDisk(classByteArray)
        com.loadClassAndRun(classByteArray)
        assertEquals(expectedResult, outContent.toString())
    }
}