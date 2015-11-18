package hw09.BrainFuck

/**
 * Created by guzel on 19.11.15.
 */
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse
class BrainfuckTests {
    @Test fun TokenizeAndOptimize(){
        val str = "[+]>>><+++[[+-]]>----+"
        val res = optimize(tokenize(str))
        val expectedResult ="0= 1\n>= 2\n+= 3\nwhile= 1\n" +
                "while= 1\n+= 0\n]= 1\n]= 1\n>= 1\n+= -3\n"
        assertEquals(expectedResult, toString(res))
    }
}