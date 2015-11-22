package Homework.Hw09

import kotlin.test.assertEquals
import org.junit.Test
import homework.Hw09.Interpreter


public class Hw09Test{
    @Test fun Test1(){
        val obj = Interpreter("-[------->+<]>-.-[->+++++<]>++.+++++++" +
                "..+++.[--->+<]>-----.---[->+++<]>.-" +
                "[--->+<]>---.+++.------.---" +
                "-----.-[--->+<]>.")
        val res = obj.Interpret()
        val text = "Hello World!"
        assertEquals(res, text)
    }

    @Test fun Test2(){
        val obj = Interpreter("-[------->+<]>++.-[-->+++<]>.+++++.--------" +
                ".---.+++++.-[->+++++<]>-.[-->+++++++<]>.++.---.--------.+++++" +
                "++++++.+++[->+++<]>++.++++++++++++..----.+++++.-------.-[--->+<]>" +
                "--.++[--->++<]>.-----------.+++++++++++++.--" +
                "-----.--[--->+<]>--.[->+++<]>++.++++++.--.")
        val res = obj.Interpret()
        val text = "Kotlin programming language"
        assertEquals(res, text)
    }
}