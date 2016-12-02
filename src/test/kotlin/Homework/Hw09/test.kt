package Homework.Hw09

import kotlin.test.assertEquals
import org.junit.Test
import homework.Hw09.Interpreter
import java.io.Reader

public class Hw09Test{
    @Test fun Test1(){
        val res = Interpreter().Interpret("-[------->+<]>-.-[->+++++<]>++.+++++++" +
                "..+++.[--->+<]>-----.---[->+++<]>.-" +
                "[--->+<]>---.+++.------.---" +
                "-----.-[--->+<]>.",StringReader(""))
        val text = "Hello World!"
        assertEquals(res, text)
    }

    @Test fun Test2(){
        val res = Interpreter().Interpret("-[------->+<]>++.-[-->+++<]>.+++++.--------" +
                ".---.+++++.-[->+++++<]>-.[-->+++++++<]>.++.---.--------.+++++" +
                "++++++.+++[->+++<]>++.++++++++++++..----.+++++.-------.-[--->+<]>" +
                "--.++[--->++<]>.-----------.+++++++++++++.--" +
                "-----.--[--->+<]>--.[->+++<]>++.++++++.--.", StringReader(""))
        val text = "Kotlin programming language"
        assertEquals(res, text)
    }

    @Test fun TestInput(){
        val res = Interpreter().Interpret(",.>,.>,.>,.>,.>,.", StringReader("Kotlin"))
        val text = "Kotlin"
        assertEquals(text,res)
    }

    class StringReader(private val str : String) : Reader() {
        var ptr = 0
        override fun read() = if (ptr < str.length) str[ptr++].toInt() else -1
        override fun read(cbuf : CharArray?, off : Int, len : Int) : Int {
            throw UnsupportedOperationException()
        }

        override fun close() {
        }
    }
}