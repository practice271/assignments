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
         is Empty -> { return (tree2 is Empty)}
         is Node -> {
            if (tree2 is Empty){ return false }
            val t =  unWrap(tree2)
            return (tree1.keys.value ==t.keys.value) &&
                  compare(tree1.left, t.left) && compare(tree1.right, t.right)
         }
         else -> throw  Exception("Unknown class")
      }
   }
   @Test fun Find1(){
      var tree = generateTree(1, 4)
      assertTrue(tree.find(1))
   }
   @Test fun FindRoot(){
      var tree = generateTree(1, 4)
      assertTrue(tree.find(2))
   }
   @Test fun Find2(){
      var tree = generateTree(1, 4)
      assertFalse(tree.find(10))
   }
   @Test fun Add(){
      var tree = generateTree(1, 4)
      tree = addToAVL(tree, -1)
      val value = unWrap(unWrap(unWrap(tree).left).left).keys.value
      assertEquals(-1, value)
   }
   @Test fun Add2(){
      var tree = generateTree(1, 4)
      tree = addToAVL(tree, -1)
      val value = unWrap(unWrap(unWrap(tree).left).left).left
      assertTrue(value is Empty)
   }
   @Test fun AddSmallRightRotation(){
      var tree = generateTree(1, 4)
      tree = addToAVL(tree, -1)
      tree = addToAVL(tree, -2)
      val expectedTree = Node(Keys(-1), Node(Keys(-2), Empty(), Empty() ),
              Node(Keys(1), Empty(), Empty() ))
      assertTrue(compare(expectedTree,unWrap(tree).left))
   }
   @Test fun AddSmallLeftRotation(){
      var tree = generateTree(1, 4)
      tree = addToAVL(tree, -1)
      tree = addToAVL(tree, -2)
      tree = addToAVL(tree, 5)
      val expectedTree = Node(Keys(4),Node(Keys(3),Empty(), Empty() ), Node(Keys(5),Empty(), Empty() ) )
      assertTrue(compare(expectedTree,unWrap(tree).right))
   }
   @Test fun AddBigLeftRotation(){
      var tree = generateTree(1, 4)
      tree = addToAVL(tree, -1)
      tree = addToAVL(tree, -2)
      tree = addToAVL(tree, 5)
      tree = addToAVL(tree, 8)
      tree = addToAVL(tree, 7)
      tree = addToAVL(tree, 6)
      val expectedTree = Node(Keys(5),Node(Keys(4),Node(Keys(3),Empty(), Empty() ), Empty() )
              , Node(Keys(7),Node(Keys(6),Empty(), Empty() ), Node(Keys(8),Empty(), Empty() ) ) )
      assertTrue(compare(expectedTree, unWrap(tree).right))
   }
   @Test fun AddToEmpty(){
      var tree : AVL = Empty()
      tree = addToAVL(tree, -1)
      val expectedTree = Node(Keys(-1), Empty(), Empty() )
      assertTrue(compare(expectedTree, tree))
   }
   @Test fun AddBigRightRotation(){
      var tree : AVL = Empty()
      tree = addToAVL(tree, 50)
      tree = addToAVL(tree, 30)
      tree = addToAVL(tree, 100)
      tree = addToAVL(tree, 80)
      tree = addToAVL(tree, 110)
      tree = addToAVL(tree, 20)
      tree = addToAVL(tree, 40)
      tree = addToAVL(tree, 35)
      tree = addToAVL(tree, 45)
      tree = addToAVL(tree, 34)

      val expectedTree = Node(Keys(35),Node(Keys(30),Node(Keys(20),Empty(), Empty() ),
              Node(Keys(34),Empty(), Empty() ) ), Node(Keys(40),Empty(), Node(Keys(45),Empty(), Empty() ) ) )
      assertTrue(compare(expectedTree, unWrap(tree).left))
   }
   @Test fun RemoveFromEmpty(){
      var tree : AVL = Empty()
      tree = removeInAVL(tree, -1)
      val expectedTree : AVL =  Empty()
      assertTrue(compare(expectedTree, tree))
   }
   @Test fun RemoveToEmpty(){
      var tree : AVL = Empty()
      tree = addToAVL(tree, -1)
      tree = removeInAVL(tree, -1)
      val expectedTree : AVL =  Empty()
      assertTrue(compare(expectedTree, tree))
   }
   @Test fun RemoveLeaf(){
      var tree : AVL = Empty()
      tree = addToAVL(tree, -1)
      tree = addToAVL(tree, 2)
      tree = removeInAVL(tree, 2)
      val expectedTree : AVL =  Node(Keys(-1),Empty(), Empty() )
      assertTrue(compare(expectedTree, tree))
   }
   @Test fun RemoveRoot(){
      var tree : AVL = Empty()
      tree = addToAVL(tree, 50)
      tree = addToAVL(tree, 30)
      tree = addToAVL(tree, 100)
      tree = addToAVL(tree, 80)
      tree = removeInAVL(tree, 50)
      val expectedTree : AVL = Node(Keys(80),Node(Keys(30),Empty(), Empty() ), Node(Keys(100),Empty(), Empty() ) )
      assertTrue(compare(expectedTree, tree))
   }
   @Test fun Remove(){
      var tree : AVL = Empty()
      tree = addToAVL(tree, 3)
      tree = addToAVL(tree, -1)
      tree = addToAVL(tree, 6)
      tree = addToAVL(tree, 8)
      tree = addToAVL(tree, -2)
      tree = addToAVL(tree, 4)
      tree = addToAVL(tree, 5)
      tree= removeInAVL(tree, 3)
      val expectedTree = Node(Keys(4),Node(Keys(-1),Node(Keys(-2),Empty(), Empty() ), Empty() ),
              Node(Keys(6),Node(Keys(5),Empty(), Empty() ), Node(Keys(8),Empty(), Empty() ) ) )
      assertTrue(compare(expectedTree, tree))
   }
   @Test fun RemoveWithBalancing(){
      var tree : AVL = Empty()
      tree = addToAVL(tree, 3)
      tree = addToAVL(tree, -1)
      tree = addToAVL(tree, 6)
      tree = addToAVL(tree, 8)
      tree = addToAVL(tree, -2)
      tree = addToAVL(tree, 4)
      tree = addToAVL(tree, 5)
      tree= removeInAVL(tree, 6)
      val expectedTree = Node(Keys(3),Node(Keys(-1),Node(Keys(-2),Empty(), Empty() ), Empty() ),
              Node(Keys(5),Node(Keys(4),Empty(), Empty() ), Node(Keys(8),Empty(), Empty() ) ) )
      assertTrue(compare(expectedTree, tree))
   }
}