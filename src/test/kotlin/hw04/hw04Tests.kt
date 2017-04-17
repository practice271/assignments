package hw04

import java.util.ArrayList
import org.junit.Test
import kotlin.test.assertEquals

public class hw04Test {
   val tree = AVLtree (22, AVLtree(5, null, null), AVLtree(75, null, null))
   val treeInsert6 = AVLtree (22, AVLtree(5, null, AVLtree(6,null,null)),AVLtree(75, null, null))
   val treeDelete5 = AVLtree (22, null, AVLtree(75, null, null))
   @Test fun insertTree () {
      assertEquals(tree.insert(6)?.toList() ?: null, treeInsert6.toList())
   }
   @Test fun deleteTree () {
      assertEquals(tree.delete(5)?.toList() ?: null, treeDelete5.toList())
   }
   @Test fun searchTree11 () {
      assertEquals(tree.search(22), true)
   }
   @Test fun searchTree4 () {
      assertEquals(tree.search (4), false)
   }

   val u : Array<ArrayList<Int>> = arrayOf(arrayListOf(0), arrayListOf(),arrayListOf(22,62),arrayListOf(),arrayListOf(),
           arrayListOf(75),arrayListOf(),arrayListOf(),arrayListOf(),arrayListOf(999))
   val table = HashTable(u)
   val tableInsert1 = listOf(0,1,22,62,75,999)
   val tableDelete62 = listOf(0,22,75,999)
   @Test fun insertTable () {
      assertEquals((table.insert (1))?.toList() ?: null, tableInsert1)
   }
   @Test fun deleteTable () {
      assertEquals((table.delete (62))?.toList() ?: null, tableDelete62)
   }
   @Test fun searchTable0 () {
      assertEquals(table.search (0), true)
   }
   @Test fun searchTable100 () {
      assertEquals(table.search (100), false)
   }

   val treeUtable = listOf(0, 22, 62, 75, 5, 999)
   val tableUtree = listOf(0, 5, 22, 62, 75, 999)
   val treeItable = listOf(22, 75)
   val tableItree = listOf(22, 75)
   @Test fun treeUnionTable () {
      assertEquals((tree.union (table))?.toList() ?: null, treeUtable)
   }
   @Test fun tableUnionTree () {
      assertEquals((table.union (tree))?.toList() ?: null, tableUtree)
   }
   @Test fun treeIntersectionTable () {
      assertEquals((tree.intersection (table))?.toList() ?: null, treeItable)
   }
   @Test fun tableIntersectionTree () {
      assertEquals((table.intersection (tree))?.toList() ?: null, tableItree)
   }
}