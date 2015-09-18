package Homework

import org.junit.Test
import kotlin.test.assertEquals

public class tests
{
    Test fun TestC1P1()
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
}