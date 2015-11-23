package hw09

/* Tests for BrainFuck  made by Guzel Garifullina
   Estimated time  30 minutes
   real time       30 minutes
*/

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class GeneralTests {
    private val outContent = ByteArrayOutputStream()
    private val errContent = ByteArrayOutputStream()

    @Before
    public fun setUpStreams() {
        System.setOut(PrintStream(outContent))
        System.setErr(PrintStream(errContent))
    }

    @After
    public fun cleanUpStreams() {
        System.setOut(null)
        System.setErr(null)
    }
    private val strHello = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++" +
            ".>+.+++++++..+++.>++.<<+++++++++++++++.>.+++." +
            "------.--------.>+.>."
    private val codeHello = BrainFuckCode(strHello)

    @Test fun GetCodeBrainFuck() {
        val str = "[+]>>><+++[[+-]]>----+"
        val code = BrainFuckCode(str)
        val res = code.toString()
        val expectedResult ="0= 1\n>= 2\n+= 3\nwhile= 1\n" +
                "while= 1\n+= 0\n]= 1\n]= 1\n>= 1\n+= -3\n"
        assertEquals (expectedResult, res)
    }
    @Test fun GetCodePetooh() {
        val str = "KoKoKoKoKoKoKoKoKoKo Kud-Kudah" +
                "KoKoKoKoKoKoKoKo kudah kO kud-Kudah Kukarek kudah" +
                "kOkOkOkO kukarek"
        val code = PetoohCode(str)
        val res = code.toString()
        val expectedResult ="+= 10\nwhile= 1\n>= 1\n+= 8\n>= -1\n+= -1\n" +
        "]= 1\n>= 1\n.= 1\n>= -1\n+= -4\n,= 1\n"
        assertEquals (expectedResult, res)
    }
    @Test fun TestInterpreter() {
        Interpreter(codeHello).interpret()
        val expectedResult ="Hello World!${10.toChar()}"
        assertEquals (expectedResult, outContent.toString())
    }
    @Test fun TestInterpreterRead() {
        val data = "a"
        System.setIn(ByteArrayInputStream(data.toByteArray()))

        val scanner = Scanner(System.`in`)
        val code = BrainFuckCode(", +++.")
        Interpreter(code).interpret()
        val expectedResult ="d"
        assertEquals (expectedResult, outContent.toString())
    }
    @Test fun TestInterpreterRead2() {
        val data = "abc"
        System.setIn(ByteArrayInputStream(data.toByteArray()))

        val code = BrainFuckCode(",>,>,<<.>.>.")
        Interpreter(code).interpret()
        val expectedResult ="abc"
        assertEquals (expectedResult, outContent.toString())
    }
    @Test fun TestInterpreterNotCorrectCode() {
        val code = BrainFuckCode(",>,>,[.>.>.")
        Interpreter(code).interpret()
        val expectedResult ="Unbalanced brackets!\n"
        assertEquals (expectedResult, outContent.toString())
    }
    @Test fun TestInterpreterMod256(){
        var str = ""
        for (i in 1..257){
            str += '+'
        }
        Interpreter(BrainFuckCode(str + '.')).interpret()
        val expectedResult ="${1.toChar()}"
        assertEquals(expectedResult, outContent.toString())
    }
    @Test fun TestCompiler() {
        val com = Compiler(codeHello, "Brainfuck")
        val expectedResult ="Hello World!${10.toChar()}"
        val classByteArray = com.generateClassByteArray()
        com.saveToDisk(classByteArray)
        com.loadClassAndRun(classByteArray)
        assertEquals(expectedResult, outContent.toString())
    }
    @Test fun TestCompilerMod256(){
        var str = ""
        for (i in 1..255){
            str += '+'
        }
        val com = Compiler(BrainFuckCode(str + "><" +str + '.'), "Brainfuck")
        val classByteArray = com.generateClassByteArray()
        com.saveToDisk(classByteArray)
        com.loadClassAndRun(classByteArray)
        val expectedResult = 254.toByte()
        assertEquals(expectedResult, outContent.toString()[0].toByte())
    }
    @Test fun TestCompilerNotCorrectCode() {
        val code = BrainFuckCode(",>,>,[]][.>.>.")
        val com = Compiler(code, "Brainfuck")
        val classByteArray = com.generateClassByteArray()
        com.saveToDisk(classByteArray)
        com.loadClassAndRun(classByteArray)
        val expectedResult ="Unbalanced brackets!\n"
        assertEquals (expectedResult, outContent.toString())
    }
    @Test fun TestCompilerRead() {
        val data = "abc"
        System.setIn(ByteArrayInputStream(data.toByteArray()))

        val code = BrainFuckCode(",>,>,<<.>.>.")
        val com = Compiler(code, "Brainfuck")
        val classByteArray = com.generateClassByteArray()
        com.saveToDisk(classByteArray)
        com.loadClassAndRun(classByteArray)
        val expectedResult ="abc"
        assertEquals (expectedResult, outContent.toString())
    }
    @Test fun TestCompilerFull() {
        val data = "gfedcba"
        System.setIn(ByteArrayInputStream(data.toByteArray()))
        val str = "Here is a Brainf*** program that bubblesorts its input and spits it out:"+
                ">>>>>,+[>>>,+]<<<[<<<"+
                "[>>>[-<<<-<+>[>]>>]<<<[<]>>"+
                "[>>>+<<<-]<[>+>>>+<<<<-]"+
                "<<]>>>[-.[-]]>>>[>>>]<<<]"
        val code = BrainFuckCode(str)
        val com = Compiler(code, "Brainfuck")
        val expectedResult ="abcdefg"
        val classByteArray = com.generateClassByteArray()
        com.saveToDisk(classByteArray)
        com.loadClassAndRun(classByteArray)
        assertEquals(expectedResult, outContent.toString())
    }
    @Test fun TestCompilerFullWithModule256() {
        val str = "++++[++++>---<]>-.---[----->+<]>-.+++[->+++<]>++.++.++++++++." +
                "------.[--->+<]>---.-.+[---->+<]>+++.---[->++++<]>+.-------.----" +
                "--------.-.+++++++++++.-----------.+++++++++++++.-----------.++.-."
        val code = BrainFuckCode(str)
        val com = Compiler(code, "Brainfuck")
        val expectedResult ="Brackets unbalanced"
        val classByteArray = com.generateClassByteArray()
        com.saveToDisk(classByteArray)
        com.loadClassAndRun(classByteArray)
        assertEquals(expectedResult, outContent.toString())
    }
}