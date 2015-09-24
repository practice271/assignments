package hw01

import org.junit.Test
import kotlin.test.assertEquals

public class HW01Test {
    Test fun heapsortTest1() {
        val array = arrayOf(4, 6, 78, 0, 14, 1, 56, 0, 19)
        val arrayRes = heapsort(array)
        assertEquals("[0, 0, 1, 4, 6, 14, 19, 56, 78]",
                arrayRes.joinToString(", ", "[", "]"))
    }

    Test fun heapsortTest2() {
        val array = arrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
        val arrayRes = heapsort(array)
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]",
                arrayRes.joinToString(", ", "[", "]"))
    }

    Test fun heapsortTest3() {
        val array = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
        val arrayRes = heapsort(array)
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]",
                arrayRes.joinToString(", ", "[", "]"))
    }


    val t1 = genTree(1, 10)
    val t2 = genTree(-4, 18)

    Test fun findMaxSumTest1() {
        val tRes = t1.findMaxSum(0)
        assertEquals(26, tRes)
    }

    Test fun findMaxSumTest2() {
        val tRes = t2.findMaxSum(0)
        assertEquals(65, tRes)
    }


    Test fun myFoldMaxSum() {
        val tRes = t1.myFold({(a, b) -> a + b }, 0,
                    {(a, b) -> if (a < b) b else a })
            assertEquals(26, tRes)
    }

    Test fun myFoldMaxElement() {
        val tRes = t1.myFold({(a, b) -> if (a < b) b else a }, 1,
                {(a, b) -> if (a < b) b else a })
        assertEquals(9, tRes)
    }

    Test fun myFoldMultiplication() {
        val tRes = t1.myFold({(a, b) -> a * b }, 1, {(a, b) -> a * b })
        assertEquals(362880, tRes)
    }


    Test fun myFoldMinElement() {
        val tRes = t2.myFold({(a, b) -> if (a > b) b else a }, 18,
                {(a, b) -> if (a > b) b else a })
        assertEquals(-4, tRes)
    }

    Test fun myFoldSum() {
        val tRes = t2.myFold({(a, b) -> a + b }, 0, {(a, b) -> a + b })
        assertEquals(143, tRes)
    }


    val p1 = S(S(Zero()))
    val p2 = S(S(S(Zero())))
    val p3 = Zero()

    Test fun peanoAdditionTest1() {
        val res = p1.addition(p2)
        assertEquals("S S S S S Zero", res.print(""))
    }

    Test fun peanoAdditionTest2() {
        val res = p2.addition(p1)
        assertEquals("S S S S S Zero", res.print(""))
    }

    Test fun peanoAdditionTest3() {
        val res = p1.addition(p3)
        assertEquals("S S Zero", res.print(""))
    }

    Test fun peanoAdditionTest4() {
        val res = p3.addition(p2)
        assertEquals("S S S Zero", res.print(""))
    }

    Test fun peanoAdditionTest5() {
        val res = p3.addition(p3)
        assertEquals("Zero", res.print(""))
    }

    Test fun peanoSubstractionTest1() {
        val res = p2.substraction(p1)
        assertEquals("S Zero", res.print(""))
    }

    Test fun peanoSubstractionTest2() {
        val res = p3.substraction(p2)
        assertEquals("Zero", res.print(""))
    }

    Test fun peanoSubstractionTest3() {
        val res = p1.substraction(p3)
        assertEquals("S S Zero", res.print(""))
    }

    Test fun peanoSubstractionTest4() {
        val res = p1.substraction(p2)
        assertEquals("Zero", res.print(""))
    }

    Test fun peanoSubstractionTest5() {
        val res = p3.substraction(p3)
        assertEquals("Zero", res.print(""))
    }

    Test fun peanoMultiplicationTest1() {
        val res = p2.multiplication(p1)
        assertEquals("S S S S S S Zero", res.print(""))
    }

    Test fun peanoMultiplicationTest2() {
        val res = p1.multiplication(p2)
        assertEquals("S S S S S S Zero", res.print(""))
    }

    Test fun peanoMultiplicationTest3() {
        val res = p3.multiplication(p2)
        assertEquals("Zero", res.print(""))
    }

    Test fun peanoMultiplicationTest4() {
        val res = p1.multiplication(p3)
        assertEquals("Zero", res.print(""))
    }

    Test fun peanoMultiplicationTest5() {
        val res = p3.multiplication(p3)
        assertEquals("Zero", res.print(""))
    }

    Test fun peanoPowerTest1() {
        val res = p2.power(p1)
        assertEquals("S S S S S S S S S Zero", res.print(""))
    }

    Test fun peanoPowerTest2() {
        val res = p1.power(p2)
        assertEquals("S S S S S S S S Zero", res.print(""))
    }

    Test fun peanoPowerTest3() {
        val res = p3.power(p2)
        assertEquals("Zero", res.print(""))
    }

    Test fun peanoPowerTest4() {
        val res = p1.power(p3)
        assertEquals("S Zero", res.print(""))
    }
}