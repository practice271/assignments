package homework2

import org.junit.Test
import kotlin.test.assertEquals

public class HW02 {
    val a = Computer("Linux", false, 0)
    val b = Computer("Windows", false, 1)
    val c = Computer("Unix", false, 2)
    val d = Computer("MacOS", true, 3)
    val gr = arrayOf(arrayOf(false, true, false, true ),
                     arrayOf(true, false, false, false ),
                     arrayOf(false, false, false, false ),
                     arrayOf(true, false, false, false ))
    /* 0--1
       |
       3  2
    */
    @Test
    fun test1(){
        assertEquals(status(Net(arrayOf(a, b, c, d), gr), 1.0), "true false false true ")
    }
    @Test
    fun test2(){
        assertEquals(status(Net(arrayOf(a, b, c, d), gr), 0.0), "false false false true ")
    }

    val e = Computer("Unix", true, 0)
    val f = Computer("Windows", false, 1)
    val g = Computer("Unix", true, 2)
    val h = Computer("Linux", false, 3)
    val i = Computer("MacOS", true, 4)
    val j = Computer("Windows", true, 5)
    val k = Computer("MacOS", false, 6)
    val l = Computer("Linux", false, 7)
    val gr2 = arrayOf(arrayOf(false, false, false, true ,false, false, true, false),
                      arrayOf(false, false, false, false, false, true, false, false),
                      arrayOf(false, false, false, false, false, false, true, true),
                      arrayOf(true, false, false, false, false, false, false, false),
                      arrayOf(false, false, false, false, false, false, false, false),
                      arrayOf(false, true, false, false, false, false, false, false),
                      arrayOf(true, false, true, false, false, false, false, false),
                      arrayOf(false, false, true, false, false, false, false, false))

    /* 0(U)--3(L)        5(W)  4(M)
       |                 |
       6(M)--2(U)--7(L)  1(W)
    */
    @Test
    fun test3(){
        assertEquals(status(Net(arrayOf(e,f,g,h,i,j,k,l), gr2), 0.6), "true true true true true true true true ")
    }

    @Test
    fun test4(){
        assertEquals(status(Net(arrayOf(e,f,g,h,i,j,k,l), gr2), 0.29), "true true true false true true true false ")
    }
}