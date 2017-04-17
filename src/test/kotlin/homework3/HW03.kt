package homework3

import org.junit.Test
import kotlin.test.assertEquals

public class HW03 {
    var tree = avlTree(avlTree(avlTree(null, 3, null ), 4, avlTree(null, 5, null)), 6, avlTree(null, 7, null))
/*
| | _
| 7
| | _
6
| | | _
| | 5
| | | _
| 4
| | | _
| | 3
| | | _
*/

    @Test
    fun testSearch1(){assertEquals(search(3, tree), true)}
    @Test
    fun testSearch2(){assertEquals(search(8, tree), false)}

    var treeAfterInsert2 = avlTree(avlTree(avlTree(null, 2, null), 3, null), 4, avlTree(avlTree(null, 5, null), 6, avlTree(null, 7, null)))
/*
| | | _
| | 7
| | | _
| 6
| | | _
| | 5
| | | _
4
| | _
| 3
| | | _
| | 2
| | | _
 */
    @Test
    fun testInserted1(){assertEquals(insert(tree, 2).toText(), treeAfterInsert2.toText())}

    var treeAfterDelete3 = avlTree(avlTree(null, 2, null), 4, avlTree(avlTree(null, 5, null), 6, avlTree(null, 7, null)))
    /*
| | | _
| | 7
| | | _
| 6
| | | _
| | 5
| | | _
4
| | _
| 2
| | _
     */

    @Test
    fun testDeleted1(){assertEquals(delete(treeAfterInsert2, 3).toText(), treeAfterDelete3.toText())}

    var treeAfterInsert10 = avlTree(avlTree(avlTree(null, 2, null), 4, avlTree(null, 5, null)), 6, avlTree(null ,7, avlTree(null, 10, null)))

    /*
| | | _
| | 10
| | | _
| 7
| | _
6
| | | _
| | 5
| | | _
| 4
| | | _
| | 2
| | | _
     */

    @Test
    fun testInsert2(){assertEquals(insert(treeAfterDelete3, 10).toText(), treeAfterInsert10.toText())}

    var treeAfterDelete6 = avlTree(avlTree(avlTree(null, 2, null), 4, avlTree(null, 5, null)), 7, avlTree(null, 10, null))

    /*
| | _
| 10
| | _
7
| | | _
| | 5
| | | _
| 4
| | | _
| | 2
| | | _
     */

    @Test
    fun testDelete2(){assertEquals(delete(treeAfterInsert10, 6).toText(), treeAfterDelete6.toText())}
}
