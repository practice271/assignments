package homework.hw09

import org.junit.Test
import kotlin.test.assertEquals

public class HW09Test {
    private var code: String = ""
    private var input: String = ""
    private val arraySize: Int = 30000
    private val temp = BFInterpreter(arraySize)
    @Test fun TestInterpreter1() {
        code = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++" +
                "..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>."
        temp.handle(code, input)
        assertEquals("Hello World!\n", temp.output)
    }
    @Test fun TestInterpreter2() {
        code = ">++,+<--,++,+i can write comment++."
        input = "abc"
        temp.handle(code, input)
        assertEquals("f", temp.output)
    }
    @Test fun TestInterpreter3() {
        code = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++]."
        temp.handle(code, input)
        assertEquals("Incorrect data", temp.output)
    }
}