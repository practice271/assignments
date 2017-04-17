package hw03

import org.junit.Test
import kotlin.test.assertEquals

public class hw03Test {
   val single = AVLtree(1, null, null)
   val singleInsert = AVLtree (1, null, AVLtree(5, null, null))
   val singleDelete : AVLtree? = null
   @Test fun insertSingle () {
      assertEquals(toText(balance(insertion (single, 5))), toText(singleInsert))
   }
   @Test fun deleteSingle () {
      assertEquals(toText(balance(deletion (single, 1))), toText(singleDelete))
   }
   @Test fun singleSearch1 () {
      assertEquals(search (single, 1), true)
   }
   @Test fun singleSearch2 () {
      assertEquals(search (single, 2), false)
   }


   val example2 = AVLtree (5, AVLtree(3, AVLtree(1, null,null), null), AVLtree(7, null,null))
   /*
   | | _
   | 7
   | | _
   5
   | | _
   | 3
   | | | _
   | | 1
   | | | _
   */
   val example2Insert0 = AVLtree (5, AVLtree(1, AVLtree(0, null,null), AVLtree(3, null,null)), AVLtree(7, null,null))
   val example2Delete5 = AVLtree (3, AVLtree(1, null, null), AVLtree(7, null,null))
   @Test fun insertExample2 () {
      assertEquals(toText(balance(insertion ( example2, 0))), toText(example2Insert0))
   }
   @Test fun deleteExample2 () {
      assertEquals(toText(balance(deletion ( example2, 5))), toText(example2Delete5))
   }
   @Test fun example2Search3 () {
      assertEquals(search ( example2, 1), true)
   }
   @Test fun example2Search2 () {
      assertEquals(search ( example2, 2), false)
   }


   val example3 = AVLtree (5, AVLtree(3, AVLtree(2, null,null),
           AVLtree(4,null,null)), AVLtree(6, null, AVLtree(7,null,null)))
   /*
   | | | _
   | | 7
   | | | _
   | 6
   | | _
   5
   | | | _
   | | 4
   | | | _
   | 3
   | | | _
   | | 2
   | | | _
   */
   val example3Insert8 = AVLtree (5, AVLtree(3, AVLtree(2, null,null),
           AVLtree(4,null,null)), AVLtree(7, AVLtree(6,null,null), AVLtree(8,null,null)))
   val example3Delete6 = AVLtree (5, AVLtree(3, AVLtree(2, null,null),
           AVLtree(4,null,null)), AVLtree(7,null ,null))
   @Test fun insertExample3 () {
      assertEquals(toText(balance(insertion ( example3, 8))), toText(example3Insert8))
   }
   @Test fun deleteExample3 () {
      assertEquals(toText(balance(deletion ( example3, 6))), toText(example3Delete6))
   }
   @Test fun example3Search3 () {
      assertEquals(search ( example3, 1), false)
   }
   @Test fun example3Search2 () {
      assertEquals(search (example3, 2), true)
   }


   val big = AVLtree (10, AVLtree(3, AVLtree(2, null,null), AVLtree(7,AVLtree(5,null,null),null)),
           AVLtree(12, AVLtree(11,null,null), AVLtree(13,null,null)))
   /*
   | | | _
   | | 13
   | | | _
   | 12
   | | | _
   | | 11
   | | | _
   10
   | | | _
   | | 7
   | | | | _
   | | | 5
   | | | | _
   | 3
   | | | _
   | | 2
   | | | _
    */
   val bigInsert6 = AVLtree (10, AVLtree(3, AVLtree(2, null,null), AVLtree(6,AVLtree(5,null,null),AVLtree(7, null, null))),
           AVLtree(12, AVLtree(11,null,null), AVLtree(13,null,null)))
   val bigDelete3 = AVLtree (10, AVLtree(5, AVLtree(2, null,null),AVLtree(7, null, null)),
           AVLtree(12, AVLtree(11,null,null), AVLtree(13,null,null)))
   @Test fun insertBig () {
      assertEquals(toText(balance(insertion ( big, 6))), toText(bigInsert6))
   }
   @Test fun deleteBig () {
      assertEquals(toText(balance(deletion ( big, 3))), toText(bigDelete3))
   }
   @Test fun bigSearch3 () {
      assertEquals(search ( big, 1), false)
   }
   @Test fun bigSearch2 () {
      assertEquals(search (big, 2), true)
   }
}