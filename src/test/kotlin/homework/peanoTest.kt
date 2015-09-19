package homework

import org.junit.Test
import kotlin.test.assertEquals

public class peanoTest {
    Test fun peanoToIntTest() {
        assertEquals(0, peanoToInt(Zero()))
        assertEquals(1, peanoToInt(S(Zero())))
        assertEquals(5, peanoToInt(S(S(S(S(S(Zero())))))))
    }

    Test fun peanoPlusTest() {
        assertEquals(0, peanoToInt(plus(Zero(), Zero())))
        assertEquals(1, peanoToInt(plus(S(Zero()), Zero())))
        assertEquals(1, peanoToInt(plus(Zero(), S(Zero()))))
        assertEquals(2, peanoToInt(plus(S(Zero()), S(Zero()))))
        assertEquals(5, peanoToInt(plus(S(S(S(Zero()))), S(S(Zero())))))
    }

    Test fun peanoMinusTest() {
        assertEquals(0, peanoToInt(minus(Zero(), Zero())))
        assertEquals(0, peanoToInt(minus(Zero(), S(Zero()))))
        assertEquals(1, peanoToInt(minus(S(Zero()), Zero())))
        assertEquals(2, peanoToInt(minus(S(S(S(S(Zero())))), S(S(Zero())))))
    }

    Test fun peanoMultiplyTest() {
        assertEquals(0, peanoToInt(multiply(Zero(), Zero())))
        assertEquals(0, peanoToInt(multiply(S(Zero()), Zero())))
        assertEquals(0, peanoToInt(multiply(Zero(), S(Zero()))))
        assertEquals(1, peanoToInt(multiply(S(Zero()), S(Zero()))))
        assertEquals(6, peanoToInt(multiply(S(S(S(Zero()))), S(S(Zero())))))
    }

    Test fun peanoRaisingTest() {
        assertEquals(0, peanoToInt(raising(Zero(), S(Zero()))))
        assertEquals(1, peanoToInt(raising(S(S(Zero())), Zero())))
        assertEquals(8, peanoToInt(raising(S(S(Zero())), S(S(S(Zero()))))))
    }
}
