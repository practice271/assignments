package hw02

import org.junit.Test
import kotlin.test.assertEquals
/**
 * Created by Alexander Chebykin on 17.09.2015.
 */
public class treeFoldTest {

    @Test fun SumTreeGeneralTest () {
        val tree = Node(4, Node (2, Leaf (1), Leaf(3)), Node (6, Leaf(5), Empty()))
        /*
                         4
                2                 6
              1   3             5
         */
        assertEquals(tree.findMaxSum(), 15)
    }
    @Test fun SumTreeEmptyTest () {
        val tree = Empty()
        assertEquals(tree.findMaxSum(), 0)
    }
    @Test fun SumTreeNegativeNumTest () {
        val tree = Node(-1, Node (-45, Leaf (12), Empty()), Leaf(-3))
        /*
                  -1
              -45    3
             12
         */
        assertEquals(tree.findMaxSum(), -4)
    }

    @Test fun FoldFindMinNumberTest () {
        val tree = Node(4, Node (2, Leaf (1), Leaf(3)), Node (6, Leaf(5), Empty()))
        /*
                         4
                2                 6
              1   3             5
         */
        val res = tree.fold(Int.MAX_VALUE, {num : Int, acc : Int -> if (acc > num) num else acc},
                {l : Int, r : Int-> if (l < r) l else r})
        assertEquals(res, 1)
    }
}