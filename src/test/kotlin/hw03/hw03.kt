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
   private  fun compare<T : Comparable<T>> (tree1 : AVL<T>, tree2 : AVL<T>): Boolean{
      when (tree1){
         is Empty -> { return (tree2 is Empty)}
         is Node -> {
            if (tree2 is Empty){ return false }
            val t =  unWrap(tree2)
            return (tree1.key ==t.key) &&
                  compare(tree1.leftChild, t.leftChild) && compare(tree1.rightChild, t.rightChild)
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
      val value = unWrap(unWrap(unWrap(tree).leftChild).leftChild).key
      assertEquals(-1, value)
   }
   @Test fun Add2(){
      var tree = generateTree(1, 4)
      tree = addToAVL(tree, -1)
      val value = unWrap(unWrap(unWrap(tree).leftChild).leftChild).leftChild
      assertTrue(value is Empty)
   }
   @Test fun AddSmallRightRotation(){
      var tree = generateTree(1, 4)
      tree = addToAVL(tree, -1)
      tree = addToAVL(tree, -2)
      val expectedTree = Node(-1, Node(-2, Empty(), Empty() ),
              Node(1, Empty(), Empty() ))
      assertTrue(compare(expectedTree,unWrap(tree).leftChild))
   }
   @Test fun AddSmallLeftRotation(){
      var tree = generateTree(1, 4)
      tree = addToAVL(tree, -1)
      tree = addToAVL(tree, -2)
      tree = addToAVL(tree, 5)
      val expectedTree = Node(4,Node(3,Empty(), Empty() ), Node(5,Empty(), Empty() ) )
      assertTrue(compare(expectedTree,unWrap(tree).rightChild))
   }
   @Test fun AddBigLeftRotation(){
      var tree = generateTree(1, 4)
      tree = addToAVL(tree, -1)
      tree = addToAVL(tree, -2)
      tree = addToAVL(tree, 5)
      tree = addToAVL(tree, 8)
      tree = addToAVL(tree, 7)
      tree = addToAVL(tree, 6)
      val expectedTree = Node(5,Node(4,Node(3,Empty(), Empty() ), Empty() )
              , Node(7,Node(6,Empty(), Empty() ), Node(8,Empty(), Empty() ) ) )
      assertTrue(compare(expectedTree, unWrap(tree).rightChild))
   }
   @Test fun AddToEmpty(){
      var tree : AVL<Int> = Empty()
      tree = addToAVL(tree, -1)
      val expectedTree = Node(-1, Empty(), Empty() )
      assertTrue(compare(expectedTree, tree))
   }
   @Test fun AddBigRightRotation(){
      var tree : AVL<Int> = Empty()
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

      val expectedTree = Node(35,Node(30,Node(20,Empty(), Empty() ),
              Node(34,Empty(), Empty() ) ), Node(40,Empty(), Node(45,Empty(), Empty() ) ) )
      assertTrue(compare(expectedTree, unWrap(tree).leftChild))
   }
   @Test fun RemoveFromEmpty(){
      var tree : AVL<Int> = Empty()
      tree = removeInAVL(tree, -1)
      val expectedTree : AVL<Int> =  Empty()
      assertTrue(compare(expectedTree, tree))
   }
   @Test fun RemoveToEmpty(){
      var tree : AVL<Int> = Empty()
      tree = addToAVL(tree, -1)
      tree = removeInAVL(tree, -1)
      val expectedTree : AVL<Int> =  Empty()
      assertTrue(compare(expectedTree, tree))
   }
   @Test fun RemoveLeaf(){
      var tree : AVL<Int> = Empty()
      tree = addToAVL(tree, -1)
      tree = addToAVL(tree, 2)
      tree = removeInAVL(tree, 2)
      val expectedTree : AVL<Int> =  Node(-1,Empty(), Empty() )
      assertTrue(compare(expectedTree, tree))
   }
   @Test fun RemoveRoot(){
      var tree : AVL<Int> = Empty()
      tree = addToAVL(tree, 50)
      tree = addToAVL(tree, 30)
      tree = addToAVL(tree, 100)
      tree = addToAVL(tree, 80)
      tree = removeInAVL(tree, 50)
      val expectedTree : AVL<Int> = Node(80,Node(30,Empty(), Empty() ), Node(100,Empty(), Empty() ) )
      assertTrue(compare(expectedTree, tree))
   }
   @Test fun Remove(){
      var tree : AVL<Int> = Empty()
      tree = addToAVL(tree, 3)
      tree = addToAVL(tree, -1)
      tree = addToAVL(tree, 6)
      tree = addToAVL(tree, 8)
      tree = addToAVL(tree, -2)
      tree = addToAVL(tree, 4)
      tree = addToAVL(tree, 5)
      tree= removeInAVL(tree, 3)
      val expectedTree = Node(4,Node(-1,Node(-2,Empty(), Empty() ), Empty() ),
              Node(6,Node(5,Empty(), Empty() ), Node(8,Empty(), Empty() ) ) )
      assertTrue(compare(expectedTree, tree))
   }
   @Test fun RemoveWithBalancing(){
      var tree : AVL<Int> = Empty()
      tree = addToAVL(tree, 3)
      tree = addToAVL(tree, -1)
      tree = addToAVL(tree, 6)
      tree = addToAVL(tree, 8)
      tree = addToAVL(tree, -2)
      tree = addToAVL(tree, 4)
      tree = addToAVL(tree, 5)
      tree= removeInAVL(tree, 6)
      val expectedTree = Node(3,Node(-1,Node(-2,Empty(), Empty() ), Empty() ),
              Node(5,Node(4,Empty(), Empty() ), Node(8,Empty(), Empty() ) ) )
      assertTrue(compare(expectedTree, tree))
   }
}