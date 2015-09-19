/* Tests for Peano numbers made by Guzel Garifullina
   Estimated time 15 minutes
   real time      30 minutes
*/
package hw01

import org.junit.Test
import kotlin.test.assertEquals

public class PeanoTest {
    val a: Peano = S (S (S(Zero())))
    val b: Peano = Zero()
    val c: Peano = S(Zero())
    val d: Peano = peanoCreate(5)

    @Test fun SumToZero() {
        assertEquals(3, peanoToNum(peanoSum(a, b)))
    }

    @Test fun Sum() {
        assertEquals(8, peanoToNum(peanoSum(a, d)))
    }


    @Test fun SubZero() {
        assertEquals(3, peanoToNum(peanoSub(a,b)))
    }
    @Test fun SubNeg() {
        assertEquals(0, peanoToNum(peanoSub(c,a)))
    }
    @Test fun Sub() {
        assertEquals(2, peanoToNum(peanoSub(d,a)))
    }

    @Test fun MultZero() {
        assertEquals(0, peanoToNum(peanoMult(a,b)))
    }
    @Test fun MultZero2() {
        assertEquals(0, peanoToNum(peanoPower(b,a)))
    }
    @Test fun Mult() {
        assertEquals(15, peanoToNum(peanoMult(a,d)))
    }

    @Test fun Power03() {
        assertEquals(0, peanoToNum(peanoMult(b,d)))
    }

    @Test fun Power31() {
        assertEquals(3, peanoToNum(peanoPower(a,c)))
    }

    @Test fun Power50() {
        assertEquals(1, peanoToNum(peanoPower(d,b)))
    }

    @Test fun Power53() {
        assertEquals(125, peanoToNum(peanoPower(d,a)))
    }

}
