package hw03
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by z on 03.10.2015.
 */
public class avlTree_Tests {
    Test fun testSearch1() {
        var tree: Node? =  Node(13,
                Node(11,
                        Node(10, null, null),
                        Node(12, null, null), 10),
                Node(15,
                        Node(14, null, null),
                        Node(16, null, null), 10),
                1)
         var flag = tree?.search(13)
        assertEquals(flag, true)
    }

    Test fun testSearch2() {
        var tree: Node? =  Node(13,
                Node(11,
                        Node(10, null, null),
                        Node(12, null, null), 10),
                Node(15,
                        Node(14, null, null),
                        Node(16, null, null), 10),
                1)
        var flag = tree?.search(16)
        assertEquals(flag, true)
    }

    Test fun testInsert1() {
        var tree: Node? =  Node(13,
                Node(11,
                        Node(10, null, null),
                        Node(12, null, null), 10),
                Node(15,
                        Node(14, null, null),
                        Node(16, null, null), 10),
                1)

        tree?.insertNode(18)
        var result = "null 10 null 11 null 12 null 13 null 14 null 15 null 16 null 18 null"
        assertEquals(tree?.toString_(), result)
    }

    Test fun testInsert2() {
        var tree: Node? =  Node(3,
                Node(1,
                        Node(0, null, null),
                        Node(2, null, null), 0),
                Node(5,
                        Node(4, null, null),
                        Node(6, null, null), 0),
                1)
        tree?.insertNode(8)
        tree?.insertNode(10)
        tree?.insertNode(7)
        var result = "null 0 null 1 null 2 null 3 null 4 null 5 null 6 null 7 null 8 null 10 null"
        assertEquals(tree?.toString_(), result)
    }

    Test fun testRemove1() {
        var tree: Node? =  Node(12,
                Node(10,
                        Node(9, null, null),
                        Node(11, null, null), 9),
                Node(14,
                        Node(13, null, null),
                        Node(15, null, null), 9),
                10)
        tree?.insertNode(17)
        tree?.insertNode(19)
        tree?.insertNode(16)
        tree?.removeNode(14)
        var result = "null 9 null 10 null 11 null 12 null 13 null 15 null 16 null 17 null 19 null"
        assertEquals(tree?.toString_(), result)
    }

    Test fun testRemove2() {
        var tree: Node? =  Node(-8,
                Node(-10,
                        Node(-11, null, null),
                        Node(-9, null, null), -11),
                Node(-6,
                        Node(-7, null, null),
                        Node(-5, null, null), -11),
                10)
        tree?.insertNode(-3)
        tree?.insertNode(-1)
        tree?.insertNode(-4)
        tree?.removeNode(-6)
        var result = "null -11 null -10 null -9 null -8 null -7 null -5 null -4 null -3 null -1 null"
        assertEquals(tree?.toString_(), result)
    }

}