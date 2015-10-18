package hw05

import java.util.ArrayList
import org.junit.Test
import kotlin.test.assertEquals

class hw05Tests {
   @Test fun incrementForOneThread () {
      assertEquals(increment(1, 100), 100)
   }
   @Test fun longIncrementForOneThread () {
      assertEquals(increment(1, 100000000), 100000000)
   }

   val array = arrayListOf (10, 9, 4, 7, 2, 1, 8, 3, 6 , 5)
   val arrayRes = arrayListOf (1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
   @Test fun mergesortThreads1 () {
      assertEquals(mergesortThreads(array,1), arrayRes)
   }
   @Test fun mergesortThreads2 () {
      assertEquals(mergesortThreads(array,2), arrayRes)
   }
   @Test fun mergesortThreads10 () {
      assertEquals(mergesortThreads(array,10), arrayRes)
   }

   fun longCreating () : ArrayList<Int> {
      val arrayLong = arrayListOf<Int> ()
      for (i in 1..1000) {
         arrayLong.add(i)
      }
      return arrayLong
   }

   val long = longCreating()
   @Test fun mergesortLong1 () {
      assertEquals(mergesortThreads(long,1), long)
   }
   @Test fun mergesortLong2 () {
      assertEquals(mergesortThreads(long,5), long)
   }
   @Test fun mergesortLong10 () {
      assertEquals(mergesortThreads(long,10), long)
   }
}