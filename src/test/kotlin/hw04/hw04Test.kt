package hw04;

/**
 * Created by Antropov Igor 03.10.2015
 */


import org.junit.Test
import kotlin.test.assertEquals

public class hw04Test {
    /**
     * Tree tests
     */
    @Test fun insertionTreeTest(){
        val tree = AVLTree()
        val a = arrayOf(5, 4, 6, 1, 2)
        for (x in a) {
            tree.insertion(x)
        }
        assertEquals("((null,1,null),2,(null,4,null)),5,(null,6,null)", tree.toString())
    }

    @Test fun deletionTreeTest(){
        val tree = AVLTree()
        val a = arrayOf(5, 4, 6, 1, 2)
        for (x in a) {
            tree.insertion(x)
        }
        tree.deletion(5)
        tree.deletion(99)
        assertEquals("(null,1,null),2,((null,4,null),6,null)", tree.toString())
    }

    @Test fun searchingTreeTest(){
        val tree = AVLTree()
        val a = arrayOf(5, 4, 6, 1, 2)
        for (x in a) {
            tree.insertion(x)
        }
        assertEquals(true, tree.search(4))
        assertEquals(false, tree.search(11))
    }

    @Test fun unionTreeTest(){
        val tree1 = AVLTree()
        val a1 = arrayOf(5, 4, 8)
        for (x in a1) {
            tree1.insertion(x)
        }

        val tree2 = AVLTree()
        val a2 = arrayOf(3, 4, 6)
        for (x in a2) {
            tree2.insertion(x)
        }

        assertEquals("((null,3,null),4,null),5,((null,6,null),8,null)", tree1.union(tree2).toString())
        assertEquals("(null,3,null),4,((null,5,null),6,(null,8,null))", tree2.union(tree1).toString())
    }

    @Test fun intersectionTreeTest(){
        val tree1 = AVLTree()
        val a1 = arrayOf(5, 4, 8)
        for (x in a1) {
            tree1.insertion(x)
        }

        val tree2 = AVLTree()
        val a2 = arrayOf(3, 4, 6)
        for (x in a2) {
            tree2.insertion(x)
        }

        assertEquals("null,4,null", tree1.intersection(tree2).toString())
        assertEquals("null,4,null", tree2.intersection(tree1).toString())
    }

    /**
     * Hash Tests
     */
    @Test fun insertionHashTest(){
        val table = HashTable()
        val a = arrayOf(5, 4, 6, 1, 2)
        for (x in a) {
            table.insertion(x)
        }
        assertEquals("1 2 4 5 6 ", table.toString())
    }

    @Test fun deletionHashTest(){
        val table = HashTable()
        val a = arrayOf(5, 4, 6, 1, 2)
        for (x in a) {
            table.insertion(x)
        }
        table.deletion(5)
        table.deletion(99)
        assertEquals("1 2 4 6 ", table.toString())
    }

    @Test fun searchingHashTest(){
        val table = HashTable()
        val a = arrayOf(5, 4, 6, 1, 2)
        for (x in a) {
            table.insertion(x)
        }
        assertEquals(true, table.search(4))
        assertEquals(false, table.search(11))
    }

    @Test fun unionHashTest(){
        val table1 = HashTable()
        val a1 = arrayOf(5, 4, 8)
        for (x in a1) {
            table1.insertion(x)
        }

        val table2 = HashTable()
        val a2 = arrayOf(3, 4, 6)
        for (x in a2) {
            table2.insertion(x)
        }

        assertEquals("3 4 5 6 8 ", table1.union(table2).toString())
        assertEquals("3 4 5 6 8 ", table2.union(table1).toString())
    }

    @Test fun intersectionHashTest(){
        val table1 = HashTable()
        val a1 = arrayOf(5, 4, 8)
        for (x in a1) {
            table1.insertion(x)
        }

        val table2 = HashTable()
        val a2 = arrayOf(3, 4, 6)
        for (x in a2) {
            table2.insertion(x)
        }

        assertEquals("4 ", table1.intersection(table2).toString())
        assertEquals("4 ", table2.intersection(table1).toString())
    }


    /**
     * both tree and hash tests
     */
    @Test fun unionTest(){
        val table = HashTable()
        val a1 = arrayOf(5, 4, 8)
        for (x in a1) {
            table.insertion(x)
        }

        val tree = AVLTree()
        val a2 = arrayOf(3, 4, 6)
        for (x in a2) {
            table.insertion(x)
        }

        assertEquals("3 4 5 6 8 ", table.union(tree).toString())
        assertEquals("(null,3,null),4,((null,5,null),6,(null,8,null))", tree.union(table).toString())
    }
    @Test fun intersectionTest(){
        val table = HashTable()
        val a1 = arrayOf(5, 4, 8)
        for (x in a1) {
            table.insertion(x)
        }

        val tree = HashTable()
        val a2 = arrayOf(3, 4, 6)
        for (x in a2) {
            tree.insertion(x)
        }

        assertEquals("4 ", table.intersection(tree).toString())
        assertEquals("4 ", tree.intersection(table).toString())
    }
}
