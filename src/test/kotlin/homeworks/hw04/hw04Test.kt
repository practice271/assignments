package homeworks.hw04

import org.junit.Test
import kotlin.test.assertEquals

public class hw04Test {
    private fun generateTestTree(): AVLTree<Int> {
        var tree = AVLTree<Int>()
        tree.insert(5)
        tree.insert(3)
        tree.insert(7)
        tree.insert(2)
        tree.insert(4)
        tree.insert(6)
        tree.insert(8)
        return tree
    }

    @Test
    fun TestGeneratorTree() {
        var tree = generateTestTree()
        var expected = "null 2 null 3 null 4 null 5 null 6 null 7 null 8 null"
        assertEquals(expected, tree.toString())
    }

    @Test
    fun TestInsertAVLTree() {
        var tree = generateTestTree()
        tree.insert(10)
        var expected = "null 2 null 3 null 4 null 5 null 6 null 7 null 8 null 10 null"
        assertEquals(expected, tree.toString())
    }

    @Test
    fun TestDeleteAVLTree() {
        var tree = generateTestTree()
        tree.delete(6)
        var expected = "null 2 null 3 null 4 null 5 null 7 null 8 null"
        assertEquals(expected, tree.toString())
    }

    @Test
    fun TestDeleteAVLTree2() {
        var tree = generateTestTree()
        tree.delete(5)
        var expected = "null 2 null 3 null 4 null 6 null 7 null 8 null"
        assertEquals(expected, tree.toString())
    }

    @Test
    fun TestSearchAVLTree() {
        var tree = generateTestTree()
        assertEquals(true, tree.search(7))
    }

    @Test
    fun TestSearchAVLTree2() {
        var tree = generateTestTree()
        assertEquals(false, tree.search(11))
    }

    @Test
    fun TestUnionAVLTree() {
        var tree1 = generateTestTree()
        var tree2 = generateTestTree()
        tree2.delete(5)
        tree2.insert(11)
        var expected = "null 2 null 3 null 4 null 5 null 6 null 7 null 8 null 11 null"

        assertEquals(expected, tree1.union(tree2).toString())
    }

    @Test
    fun TestUnionAVLTree2() {
        var tree1 = generateTestTree()
        var tree2 = AVLTree<Int>()
        var expected = "null 2 null 3 null 4 null 5 null 6 null 7 null 8 null"

        assertEquals(expected, tree1.union(tree2).toString())
    }

    @Test
    fun TestIntersectionAVLTree() {
        var tree1 = generateTestTree()
        var tree2 = AVLTree<Int>()
        tree2.insert(6)
        tree2.insert(8)
        var expected = "null 6 null 8 null"

        assertEquals(expected, tree1.intersection(tree2).toString())
    }

    @Test
    fun TestIntersectionAVLTree2() {
        var tree1 = generateTestTree()
        var tree2 = AVLTree<Int>()
        var expected = "null"

        assertEquals(expected, tree1.intersection(tree2).toString())
    }

    private fun generateTestTable(): HashTable<Int> {
        var table = HashTable<Int>(10)
        table.insert(57)
        table.insert(33)
        table.insert(77)
        table.insert(22)
        table.insert(44)
        table.insert(64)
        table.insert(87)
        return table
    }

    @Test
    fun TestGeneratorTable() {
        var table = generateTestTable()
        var expected = " 22 33 44 64 57 77 87"
        assertEquals(expected, table.toString())
    }

    @Test
    fun TestInsertTable() {
        var table = generateTestTable()
        table.insert(100)
        table.insert(57)
        var expected = " 100 22 33 44 64 57 77 87 57"
        assertEquals(expected, table.toString())
    }

    @Test
    fun TestDeleteTable() {
        var table = generateTestTable()
        table.delete(44)
        var expected = " 22 33 64 57 77 87"
        assertEquals(expected, table.toString())
    }

    @Test
    fun TestDeleteTable2() {
        var table = generateTestTable()
        table.delete(43)
        var expected = " 22 33 44 64 57 77 87"
        assertEquals(expected, table.toString())
    }

    @Test
    fun TestSearchTable() {
        var table = generateTestTable()
        assertEquals(true, table.search(33))
    }

    @Test
    fun TestSearchTable2() {
        var table = generateTestTable()
        assertEquals(false, table.search(5455))
    }

    @Test
    fun TestUnionTable() {
        var table1 = generateTestTable()
        var table2 = generateTestTable()
        table2.insert(11)
        table2.insert(97)
        table2.delete(22)
        var expected = " 11 22 33 44 64 57 77 87 97"
        assertEquals(expected, table1.union(table2).toString())
    }


    @Test
    fun TestUnionTable2() {
        var table1 = HashTable<Int>(10)
        var table2 = HashTable<Int>(10)
        table1.insert(11)
        table2.insert(22)
        var expected = " 11 22"
        assertEquals(expected, table1.union(table2).toString())
    }

    @Test
    fun TestIntersectionTable1() {
        var table1 = generateTestTable()
        var table2 = generateTestTable()
        table2.insert(11)
        table2.insert(97)
        table1.delete(22)
        table1.delete(33)
        table1.delete(44)
        var expected = " 64 57 77 87"
        assertEquals(expected, table1.intersection(table2).toString())
    }

    @Test
    fun TestIntersectionTable2() {
        var table1 = HashTable<Int>(10)
        var table2 = HashTable<Int>(10)
        table2.insert(11)
        table2.insert(22)
        var expected = ""
        assertEquals(expected, table1.intersection(table2).toString())
    }
}