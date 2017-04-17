package homeworks

import org.junit.Test
import kotlin.test.assertEquals

public class hw03Test {

    @Test
    fun TestInsert1() {
        var tree: Node? = Node(5,
                Node(3, Node(2, null, null), Node(4, null, null), 2),
                Node(7, Node(6, null, null), Node(8, null, null), 2),
                3)
        tree?.insert(10)
        var expected =  "null 2 null 3 null 4 null 5 null 6 null 7 null 8 null 10 null"
        assertEquals(expected, tree.toString())
    }

    @Test
    fun TestInsert2() {
        var tree: Node? = Node(5,
                Node(3, Node(2, null, null), Node(4, null, null), 2),
                Node(7, Node(6, null, null), Node(8, null, null), 2),
                3)
        tree?.insert(10)
        tree?.insert(12)
        tree?.insert(9)
        var expected =  "null 2 null 3 null 4 null 5 null 6 null 7 null 8 null 9 null 10 null 12 null"
        assertEquals(expected, tree.toString())
    }

    @Test
    fun TestRemove1() {
        var tree: Node? = Node(5,
                Node(3, Node(2, null, null), Node(4, null, null), 2),
                Node(7, Node(6, null, null), Node(8, null, null), 2),
                3)
        tree?.insert(10)
        tree?.insert(12)
        tree?.insert(9)
        tree?.remove(7)
        var expected = "null 2 null 3 null 4 null 5 null 6 null 8 null 9 null 10 null 12 null"
        assertEquals(expected, tree.toString())
    }

    @Test
    fun TestRemove2() {
        var tree: Node? = Node(5,
                Node(3, Node(2, null, null), Node(4, null, null), 2),
                Node(7, Node(6, null, null), Node(8, null, null), 2),
                3)
        tree?.insert(10)
        tree?.insert(12)
        tree?.insert(9)
        tree?.remove(5)
        var expected = "null 2 null 3 null 4 null 6 null 7 null 8 null 9 null 10 null 12 null"
        assertEquals(expected, tree.toString())
    }

    @Test
    fun TestSearch1() {
        var tree: Node? = Node(5,
                Node(3, Node(2, null, null), Node(4, null, null), 2),
                Node(7, Node(6, null, null), Node(8, null, null), 2),
                3)
        tree?.insert(10)
        tree?.insert(12)
        tree?.insert(9)
        var expected = tree?.Search(12)
        assertEquals(expected, true)
    }

    @Test
    fun TestSearch2() {
        var tree: Node? = Node(5,
                Node(3, Node(2, null, null), Node(4, null, null), 2),
                Node(7, Node(6, null, null), Node(8, null, null), 2),
                3)
        tree?.insert(10)
        tree?.insert(12)
        tree?.insert(9)
        var expected = tree?.Search(13)
        assertEquals(expected, false)
    }

    @Test
    fun TestSearch3() {
        var tree: Node? = Node(5,
                Node(3, Node(2, null, null), Node(4, null, null), 2),
                Node(7, Node(6, null, null), Node(8, null, null), 2),
                3)
        tree?.insert(10)
        tree?.insert(12)
        tree?.insert(9)
        var expected = tree?.Search(7)
        assertEquals(expected, true)
    }

}