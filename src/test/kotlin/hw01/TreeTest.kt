/* Tests for heap sort made by Guzel Garifullina
   Estimated time 15 minutes
   real time      30 minutes
*/

package hw01

import org.junit.Test
import kotlin.test.assertEquals

public class TreeTest {
    @Test fun Test1() {
      val tree = generateTree(0, 4)
      assertEquals(9, tree.maxWay(0))
    }

    @Test fun Test2() {
      val tree = generateTree(-7, 0)
      assertEquals(-7, tree.maxWay(-100))
    }

    @Test fun Test3() {
      val tree = generateTree(0, 16)
      assertEquals(65, tree.maxWay(0))
    }
}