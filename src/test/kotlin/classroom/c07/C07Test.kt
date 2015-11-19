package classroom.c07

import org.junit.Test
import kotlin.test.assertEquals

class C07Test {
    private fun testBody(expr: Expr, expectedResult: Int) {
        val classByteArray = expr.generateClassByteArray()
        val result = loadClassAndRun(classByteArray)
        assertEquals(expectedResult, result)
    }

    private val const10  = Expr.Const(10)
    private val const239 = Expr.Const(239)

    @Test fun t239plus10() =
            Expr.BinOp(Expr.Operation.Plus, const239, const10).let {
                testBody(it, 249)
            }

    @Test fun t239minus10() =
            Expr.BinOp(Expr.Operation.Minus, const239, const10).let {
                testBody(it, 229)
            }

    @Test fun t239multiply10() =
            Expr.BinOp(Expr.Operation.Multiply, const239, const10).let {
                testBody(it, 2390)
            }

    @Test fun t239divide10() =
            Expr.BinOp(Expr.Operation.Divide, const239, const10).let {
                testBody(it, 23)
            }
}