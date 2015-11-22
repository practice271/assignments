package homework.hw09

import org.junit.Test
import kotlin.test.assertEquals

public class HW09Test {
    @Test fun TestInterpreter() {
        var code : String
        var input : String = ""
        var result : String
        val arraySize : Int = 30000
        val temp = BFInterpreter(arraySize)

        code =  "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++" +
                "..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>."
        result = temp.handle(code, input)
        assertEquals("Hello World!\n", result)

        code = ">++,+<--,++,+i can write comment++."
        input = "abc"
        result = temp.handle(code, input)
        assertEquals("f", result)

        code = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++]."
        result = temp.handle(code, input)
        assertEquals("Incorrect data", result)
    }
}