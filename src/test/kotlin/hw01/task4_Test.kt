/**
 * Created by z on 19.09.2015.
 */

package hw01
import org.junit.Test
import kotlin.test.assertEquals

public class task4_Test {
    val x = S(S(S(Zero())))
    val y = S(S(S(S(Zero()))))

    @Test fun sumTest() {
        assertEquals(7,toInt(sum(x,y)))
    }

    @Test fun multTest() {
        assertEquals(12, toInt(mult(x,y)))
    }

    @Test fun subTest() {
        assertEquals(1, toInt(sub(y,x)))
    }

    @Test fun degTest() {
        assertEquals(81, toInt(deg(x,y)))
    }
}