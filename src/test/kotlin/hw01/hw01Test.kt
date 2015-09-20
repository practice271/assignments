package hw01

import org.junit.Test
import kotlin.test.assertEquals

public class hw01Test {
   //heapsort
   @Test fun heapsortTest0() {
      var a = arrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0)
      a.heapsort()
      val b = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
      assertEquals(a.toList(), b.toList())
   }
   @Test fun heapsortTest1() {
      var a = arrayOf(1, 5, -100, 23)
      a.heapsort()
      val b = arrayOf(-100, 1, 5, 23)
      assertEquals(a.toList(), b.toList())
   }

   //maxSum
   @Test fun treeMaxSumTest0() {
      val tree0 = Empty()
      assertEquals(tree0.maxSum(), 0)
   }
   @Test fun treeMaxSumTest1() {
      val tree1 = Leaf(1)
      assertEquals(tree1.maxSum(), 1)
   }
   @Test fun treeMaxSumTest2() {
      val tree2 = Node(11, Node(0, Leaf(0), Leaf(0)), Node(0, Leaf(99999), Leaf(0)))
      assertEquals(tree2.maxSum(), 100010)
   }

   //maxSum with fold
   @Test fun treeFoldTest0() {
      val tree0 = Empty()
      assertEquals(tree0.fold({ a, b -> a + b }, { a, b -> if (a > b) a else b }, 0), 0)
   }
   @Test fun treeFoldTest1() {
      val tree1 = Leaf(1)
      assertEquals(tree1.fold({ a, b -> a + b }, { a, b -> if (a > b) a else b }, 0), 1)
   }
   @Test fun treeFoldTest2() {
      val tree2 = Node(11, Node(0, Leaf(0), Leaf(0)), Node(0, Leaf(99999), Leaf(0)))
      assertEquals(tree2.fold({ a, b -> a + b }, { a, b -> if (a > b) a else b }, 0),100010 )
   }

   //peano
   @Test fun peanoPlusTest0() {
      assertEquals(plus (S(Zero()), Zero()).toInt(), 1)
   }
   @Test fun peanoMinusTest1() {
      assertEquals(minus (S(S(Zero())), S(S(S(Zero())))).toInt(), 0)
   }
   @Test fun peanoMultiTest0() {
      assertEquals(multi (S(Zero()), Zero()).toInt(), 0)
   }
   @Test fun peanoDegTest1() {
      assertEquals(deg (S(S(Zero())), S(S(S(Zero())))).toInt(), 8)
   }
}
