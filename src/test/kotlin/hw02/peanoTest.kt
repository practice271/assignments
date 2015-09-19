package hw02

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Alexander on 17.09.2015.
 */
public class peanoTest {
    @Test fun SubtractZeroTest () {
        val input = 2
        var a = input.ToPeano().Subtract(0.ToPeano())
        assertEquals(2, a.ToInt())
    }
    @Test fun SubtractLesserNumberTest () {
        val input = 13
        var a = input.ToPeano().Subtract(6.ToPeano())
        assertEquals(7, a.ToInt())
    }
    @Test fun SubtractBiggerNumberTest () {
        val input = 13
        var a = input.ToPeano().Subtract(19.ToPeano())
        assertEquals(0, a.ToInt())
    }
    @Test fun AddZeroTest () {
        val input = 2
        var a = input.ToPeano().Add(0.ToPeano())
        assertEquals(2, a.ToInt())
    }
    @Test fun AddToZeroTest () {
        val input = 0
        var a = input.ToPeano().Add(5.ToPeano())
        assertEquals(5, a.ToInt())
    }
    @Test fun GeneralAddTest () {
        val input = 14
        var a = input.ToPeano().Add(199.ToPeano())
        assertEquals(213, a.ToInt())
    }
    @Test fun MulZeroTest () {
        val input = 0
        var a = input.ToPeano().Mul(12.ToPeano())
        assertEquals(0, a.ToInt())
    }
    @Test fun MulToZeroTest () {
        val input = 4
        var a = input.ToPeano().Mul(0.ToPeano())
        assertEquals(0, a.ToInt())
    }
    @Test fun GeneralMulTest () {
        val input = 12
        var a = input.ToPeano().Mul(17.ToPeano())
        assertEquals(204, a.ToInt())
    }
    @Test fun GeneralPowTest () {
        val input = 2
        var a = input.ToPeano().Pow(8.ToPeano())
        assertEquals(256, a.ToInt())
    }
    @Test fun ZeroToPowTest () {
        val input = 0
        var a = input.ToPeano().Pow(3.ToPeano())
        assertEquals(0, a.ToInt())
    }
    @Test fun ZeroPowTest () {
        val input = 11
        var a = input.ToPeano().Pow(0.ToPeano())
        assertEquals(1, a.ToInt())
    }
}