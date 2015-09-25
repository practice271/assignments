package hw03

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Mikhail on 25.09.2015.
 */
public class Hw03Test {
    Test fun TreeInsertTest() {
        val avt1 = AvlTree()
        avt1.insert(15)
        avt1.insert(12)
        avt1.insert(122)
        val res = avt1.inorder().toString()
        assertEquals("[12, 15, 122]", res)
    }
    Test fun TreeRemoveTest() {
        val avt1 = AvlTree()
        avt1.insert(15)
        avt1.insert(12)
        avt1.insert(122)
        avt1.insert(22)
        avt1.insert(56)
        avt1.insert(25)
        avt1.insert(16)
        avt1.delete(22)
        avt1.delete(25)
        avt1.delete(16)
        val res = avt1.inorder().toString()
        assertEquals("[12, 15, 56, 122]", res)
    }
    Test fun TreeSearchTest() {
        val avt1 = AvlTree()
        avt1.insert(15)
        avt1.insert(12)
        avt1.insert(122)
        avt1.insert(22)
        avt1.insert(56)
        avt1.insert(25)
        avt1.insert(16)
        val t = avt1.search(25)
        val t1 = avt1.search(100)
        assertEquals(t1, 0)
        assertEquals(t, 25)
    }
}