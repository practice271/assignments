package homework.hw02

import org.junit.Test
import kotlin.test.assertEquals

public class hw02Test
{
    val OSList = listOf<String>("Windows", "Linux", "OS X", "Linux",
            "Linux", "Linux","OS X", "Windows",
            "OS X", "Linux")
    val edges = listOf<Pair<Int,Int>>(Pair(0,1),Pair(0,4),Pair(1,2),Pair(1,0),
            Pair(2,4),Pair(2,3),Pair(4,5),Pair(6,7),
            Pair(6,8), Pair(7,6), Pair(7,9), Pair(8,6),
            Pair(8,9), Pair(9,7), Pair(9,8))
    val labels  = arrayOf( false, true, false, false, false,
            false, false, true, false, false )
    val graph =  computerNet (OSList, labels, edges)
    val localNet =  Net (graph)


    Test fun netTest01()// 0% of infetion
    {
        for(i in 0..10000)
            localNet.start(11)
        assertEquals(2, localNet.NumberOfInf())

    }


    Test fun netTest02()// 100% of infetion
    {
        for(i in 0..3)
            localNet.start(-1)
        assertEquals(10, localNet.NumberOfInf())

    }


}