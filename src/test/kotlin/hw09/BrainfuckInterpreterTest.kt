package hw09
import org.junit.Test
import kotlin.test.assertEquals

class BrainfuckInterpreterTest {

    @Test fun testHelloWorld() {
        var program = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++\n .>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.\n ------.--------.>+.>."
        check(program, "Hello World!\n")
    }

    @Test fun test2() {
        var program ="++++++++."
        check(program, 8)
    }

    @Test fun test3() {
        var program = "+++++++.>+++.>++++-++."
        check(program, 735)
    }

    private fun check(program: String, result: Any) {
        var bf = BrainfuckInterpreter()
        var output = bf.interpret(program)
        var s = ""

        when(result) {
            is Int      -> for (i in output) { s += i.toInt().toString() }
            is String   -> s = output
        }
        assertEquals(result.toString(), s)
    }
}
