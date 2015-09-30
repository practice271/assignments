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
        assertEquals(t1, -1)
        assertEquals(t, 25)
    }
    Test fun TreeBalanceSmallLeftTest() {
        val avt1 = AvlTree()
        val arr = arrayListOf(5, 3, 7, 6, 8)
        for (x in arr) {
            avt1.insert(x)
        }
        val res = avt1.inorder().toString()
        avt1.insert(9)
        val res_b = avt1.inorder().toString()
        assertEquals("[3, 5, 6, 7, 8]", res)
        assertEquals("[3, 5, 6, 7, 8, 9]", res_b)
    }
    Test fun TreeBalanceBigLeft02() {
        val avt1 = AvlTree()
        val arr = arrayListOf(5, 3, 8 ,6 ,9)
        for (x in arr) {
            avt1.insert(x)
        }
        val res = avt1.inorder().toString()
        avt1.insert(7)
        val res_b = avt1.inorder().toString()
        assertEquals("[3, 5, 6, 8, 9]", res)
        assertEquals("[3, 5, 6, 7, 8, 9]", res_b)
    }
    Test fun TreeBalanceTestSmallRight() {
        val avt1 = AvlTree()
        val arr = arrayListOf(10, 11, 7, 5, 8)
        for (x in arr) {
            avt1.insert(x)
        }
        val res = avt1.inorder().toString()
        avt1.delete(11)
        val res_b = avt1.inorder().toString()
        assertEquals("[5, 7, 8, 10, 11]", res)
        assertEquals("[5, 7, 8, 10]", res_b)
    }
    Test fun TreeBalanceBigRight04() {
        val avt1 = AvlTree()
        val arr = arrayListOf(10, 15, 5, 2, 7, 20, 6, 9)
        for (x in arr) {
            avt1.insert(x)
        }
        val res = avt1.inorder().toString()
        avt1.delete(20)
        val res_b = avt1.inorder().toString()
        assertEquals("[2, 5, 6, 7, 9, 10, 15, 20]", res)
        assertEquals("[2, 5, 6, 7, 9, 10, 15]", res_b)
    }
}