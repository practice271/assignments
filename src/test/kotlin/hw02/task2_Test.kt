
/**
 * Created by z on 19.09.2015.
 */
package hw02
import org.junit.Test
import kotlin.test.assertEquals

public class task2_Test{
    @Test fun maxPathTest1(){
        val tree = Node(11,
                Node(19,
                        Node(7,
                                Leaf(8),
                                Empty()),
                        Leaf(9)),
                Node(28,
                        Leaf(10),
                        Node(0,
                                Leaf(7),
                                Leaf(5))))

    assertEquals(49,tree.maxPath())
    }

    @Test fun maxPathTest2(){
        val tree = Node(4,
                Node(23,
                        Node(8,
                                Leaf(9),
                                Empty()),
                        Leaf(10)),
                Node(29,
                        Leaf(11),
                        Node(1,
                                Leaf(8),
                                Leaf(2))))

        assertEquals(44,tree.maxPath())
    }
}