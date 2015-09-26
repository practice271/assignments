package hw03

/* Tests for AVL tree made by Guzel Garifullina
   Estimated time 1 hour
   real time      1  hour
*/

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

public class hw03 {
   private  fun compare (tree1 : AVL, tree2 : AVL): Boolean{
      when (tree1){
         is Empty -> {
            if (tree2 is Empty){
               return true
            }
            else return false
         }
         is Node -> {
            if (tree2 is Empty){
               return false
            }
            else {
               val t =  unWrap(tree2)
               return (tree1.keys.value ==t.keys.value) &&
                       compare(tree1.left, t.left) && compare(tree1.right, t.right)
            }
         }
         else -> throw  Exception("Unknown class")
      }
   }
   @Test fun Find1(){
      var t = generateTree(1, 4)
      assertTrue(t.find(1))
   }
   @Test fun FindRoot(){
      var t = generateTree(1, 4)
      assertTrue(t.find(2))
   }
   @Test fun Find2(){
      var t = generateTree(1, 4)
      assertFalse(t.find(10))
   }
   @Test fun Add(){
      var t = generateTree(1, 4)
      t = addToAVL(t, -1)
      val value = unWrap(unWrap(unWrap(t).left).left).keys.value
      assertEquals(-1, value)
   }
   @Test fun Add2(){
      var t = generateTree(1, 4)
      t = addToAVL(t, -1)
      val value = unWrap(unWrap(unWrap(t).left).left).left
      assertTrue(value is Empty)
   }
   @Test fun AddSmallRightRotation(){
      var t = generateTree(1, 4)
      t = addToAVL(t, -1)
      t = addToAVL(t, -2)
      assertTrue(compare(Node(Keys(-1), Node(Keys(-2), Empty(), Empty() ), Node(Keys(1), Empty(), Empty() )),unWrap(t).left))
   }
   @Test fun AddSmallLeftRotation(){
      var t = generateTree(1, 4)
      t = addToAVL(t, -1)
      t = addToAVL(t, -2)
      t = addToAVL(t, 5)
      val tree = Node(Keys(4),Node(Keys(3),Empty(), Empty() ), Node(Keys(5),Empty(), Empty() ) )
      assertTrue(compare(tree,unWrap(t).right))
   }
   @Test fun AddBigLeftRotation(){
      var t = generateTree(1, 4)
      t = addToAVL(t, -1)
      t = addToAVL(t, -2)
      t = addToAVL(t, 5)
      t = addToAVL(t, 8)
      t = addToAVL(t, 7)
      t = addToAVL(t, 6)
      val tree = Node(Keys(5),Node(Keys(4),Node(Keys(3),Empty(), Empty() ), Empty() ), Node(Keys(7),Node(Keys(6),Empty(), Empty() ), Node(Keys(8),Empty(), Empty() ) ) )
      assertTrue(compare(tree,unWrap(t).right))
   }
   @Test fun AddToEmpty(){
      var t : AVL = Empty()
      t = addToAVL(t, -1)
      val tree = Node(Keys(-1), Empty(), Empty() )
      assertTrue(compare(tree,t))
   }
   @Test fun AddBigRightRotation(){
      var t : AVL = Empty()
      t = addToAVL(t, 50)
      t = addToAVL(t, 30)
      t = addToAVL(t, 100)
      t = addToAVL(t, 80)
      t = addToAVL(t, 110)
      t = addToAVL(t, 20)
      t = addToAVL(t, 40)
      t = addToAVL(t, 35)
      t = addToAVL(t, 45)
      t = addToAVL(t, 34)

      val tree = Node(Keys(35),Node(Keys(30),Node(Keys(20),Empty(), Empty() ),
              Node(Keys(34),Empty(), Empty() ) ), Node(Keys(40),Empty(), Node(Keys(45),Empty(), Empty() ) ) )
      assertTrue(compare(tree, unWrap(t).left))
   }
   @Test fun RemoveFromEmpty(){
      var t : AVL = Empty()
      t = removeInAVL(t, -1)
      val tree : AVL =  Empty()
      assertTrue(compare(tree,t))
   }
   @Test fun RemoveToEmpty(){
      var t : AVL = Empty()
      t = addToAVL(t, -1)
      t = removeInAVL(t, -1)
      val tree : AVL =  Empty()
      assertTrue(compare(tree,t))
   }
   @Test fun RemoveLeaf(){
      var t : AVL = Empty()
      t = addToAVL(t, -1)
      t = addToAVL(t, 2)
      t = removeInAVL(t, 2)
      val tree : AVL =  Node(Keys(-1),Empty(), Empty() )
      assertTrue(compare(tree,t))
   }
   @Test fun RemoveRoot(){
      var t : AVL = Empty()
      t = addToAVL(t, 50)
      t = addToAVL(t, 30)
      t = addToAVL(t, 100)
      t = addToAVL(t, 80)
      t = removeInAVL(t, 50)
      val tree : AVL = Node(Keys(80),Node(Keys(30),Empty(), Empty() ), Node(Keys(100),Empty(), Empty() ) )
      assertTrue(compare(tree,t))
   }
   @Test fun Remove(){
      var t : AVL = Empty()
      t = addToAVL(t, 3)
      t = addToAVL(t, -1)
      t = addToAVL(t, 6)
      t = addToAVL(t, 8)
      t = addToAVL(t, -2)
      t = addToAVL(t, 4)
      t = addToAVL(t, 5)
      t= removeInAVL(t, 3)
      val tree = Node(Keys(4),Node(Keys(-1),Node(Keys(-2),Empty(), Empty() ), Empty() ),
              Node(Keys(6),Node(Keys(5),Empty(), Empty() ), Node(Keys(8),Empty(), Empty() ) ) )
      assertTrue(compare(tree,t))
   }
   @Test fun RemoveWithBalancing(){
      var t : AVL = Empty()
      t = addToAVL(t, 3)
      t = addToAVL(t, -1)
      t = addToAVL(t, 6)
      t = addToAVL(t, 8)
      t = addToAVL(t, -2)
      t = addToAVL(t, 4)
      t = addToAVL(t, 5)
      t= removeInAVL(t, 6)
      val tree = Node(Keys(3),Node(Keys(-1),Node(Keys(-2),Empty(), Empty() ), Empty() ),
              Node(Keys(5),Node(Keys(4),Empty(), Empty() ), Node(Keys(8),Empty(), Empty() ) ) )
      assertTrue(compare(tree,t))
   }
}