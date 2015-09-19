package hw01

import org.junit.Test
import kotlin.test.assertEquals

public class Task4Test {

    @Test fun psum() {
        assertEquals(pnSum(6.ToPn(), 2.ToPn()).ToInt(), 8)
    }

    @Test fun psub() {
        assertEquals(pnSub(6.ToPn(), 2.ToPn()).ToInt(), 4)
    }

    @Test fun pmult() {
        assertEquals(pnMult(6.ToPn(), 2.ToPn()).ToInt(), 12)
    }

    @Test fun ppower() {
        assertEquals(pnPow(6.ToPn(), 2.ToPn()).ToInt(), 36)
    }
}