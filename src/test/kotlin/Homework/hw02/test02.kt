package Homework

import org.junit.Test
import kotlin.test.assertEquals

public class tests
{
    Test fun TestHeapSort()
    {
        var arr = arrayOf(5, 4, 16, 2, 75, 0, 67, 22, 63)
        var res = arrayOf(0, 2, 4, 5, 16, 22, 63, 67, 75)

        var bool = true
        arr.HeapSort()

        for (i in 0..(arr.count() - 1))
        {
            if (arr[i] != res[i])
            {
                bool = false
                break
            }

        }

        assert(bool)
    }

    Test fun TestFindMaxInLine()
    {
        val tree = genTree(0, 6)

        assertEquals(tree.FindMaxInLine(), 14)
    }

    Test fun TestFoldTopDown()
    {
        val tree = genTree(0, 6)

        val res = tree.MyFoldTopDown(0, { a, b -> a + b}, { a, b, c -> a + b + c})

        assertEquals(res, 21)
    }

    Test fun TestPeano()
    {
        var bar = Some(Peano.Zero)


        bar = bar.Plus(Some(Peano.Zero)) as Some
        assertEquals(bar.ToInt(), 2)

        bar = bar.Mult(Some(Some(Peano.Zero))) as Some
        assertEquals(bar.ToInt(), 4)

        bar = bar.Minus(Some(Peano.Zero)) as Some
        assertEquals(bar.ToInt(), 3)

        bar = bar.Pow(bar) as Some
        assertEquals(bar.ToInt(), 27)

        bar = bar.Pow(Peano.Zero) as Some
        assertEquals(bar.ToInt(), 1)
    }
}