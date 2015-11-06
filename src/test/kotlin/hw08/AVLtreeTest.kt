package hw08

import org.junit.Test
import kotlin.test.assertEquals

public class AVLtreeTest {

    fun getTestingTree() : AVLtree<Int, Int> {
        val tree = AVLtree<Int, Int>()
        tree.insert(1, 100); tree.insert(5, 500); tree.insert(9, 900); tree.insert(6, 600); tree.insert(4, 400);
        tree.insert(3, 300);  tree.insert(10, 1000); tree.insert(18, 1800); tree.insert(8, 800);
        tree.insert(23, 2300); tree.insert(21, 2100); tree.insert(24, 2400); tree.insert(27, 2700);
        tree.insert(7, 700); tree.insert(13, 1300); tree.insert(12, 1200); tree.insert(16, 1600);
        tree.insert(15, 1500); tree.insert(28, 2800); tree.insert(22, 2200); tree.insert(26, 2600);
        tree.insert(25, 2500); tree.insert(2, 200); tree.insert(20, 2000); tree.insert(11, 1100);
        tree.insert(14, 1400); tree.insert(19,1900); tree.insert(17, 1700);
        return tree
    }

    fun getTestingTreeAsString() : String {
        return "{{{{null,100,{null,200,null}},300,{null,400,null}},500,{{null,600,null},700,{null,800,null}}},900," +
                "{{{null,1000,null},1100,{null,1200,null}},1300,{{null,1400,null},1500," +
                "{null,1600,{null,1700,null}}}}},1800,{{{{null,1900,null},2000,null},2100,{null,2200,null}},2300," +
                "{{{null,2400,null},2500,{null,2600,null}},2700,{null,2800,null}}}"
    }

    @Test fun emptyTreeTest() {
        val tree = AVLtree<Int, Int>()
        assertEquals("null", tree.toText())
    }

    @Test fun insertKeyTreeTest() {
        var tree = AVLtree<Int, Int>()
        tree.insert(1, 100)
        assertEquals("null,100,null", tree.toText())

        tree = getTestingTree()
        assertEquals(getTestingTreeAsString(), tree.toText())

        tree.insert(1, 200); tree.insert(1, 300); tree.insert(1, 1000);  tree.insert(1, 10)
        val ans = "{{{{null,10,{null,200,null}},300,{null,400,null}},500,{{null,600,null},700,{null,800,null}}}," +
                "900,{{{null,1000,null},1100,{null,1200,null}},1300,{{null,1400,null},1500," +
                "{null,1600,{null,1700,null}}}}},1800,{{{{null,1900,null},2000,null},2100,{null,2200,null}},2300," +
                "{{{null,2400,null},2500,{null,2600,null}},2700,{null,2800,null}}}"
        assertEquals(ans, tree.toText())
    }

    @Test fun findKeyEmptyTreeTest() {
        val tree = AVLtree<Int, Int>()
        assertEquals(null, tree.search(1))
    }

    @Test fun findNonExistentTreeTest() {
        val tree = AVLtree<Int, Int>()
        tree.insert(3, 300); tree.insert(7, 700); tree.insert(5, 500)
        assertEquals(null, tree.search(1))
        assertEquals(null, tree.search(4))
        assertEquals(null, tree.search(6))
        assertEquals(null, tree.search(8))
    }

    @Test fun findKeyTreeTest() {

        var tree = AVLtree<Int, Int>()
        tree.insert(1, 100); tree.insert(5, 500); tree.insert(9, 900);
        assertEquals(100, tree.search(1))
        assertEquals(500, tree.search(5))
        assertEquals(900, tree.search(9))

        tree = getTestingTree()
        assertEquals(600, tree.search(6))
        assertEquals(1300, tree.search(13))
        assertEquals(2000, tree.search(20))
        assertEquals(1400, tree.search(14))
        assertEquals(1900, tree.search(19))
    }

    @Test fun removeKeyEmptyTreeTest() {
        val tree = AVLtree<Int, Int>()
        tree.delete(1)
        assertEquals("null", tree.toText())
    }

    @Test fun removeKeyNonExistentTreeTest() {
        val tree = AVLtree<Int, Int>()
        tree.insert(3, 300); tree.insert(7, 700); tree.insert(5, 500)
        tree.delete(1); tree.delete(4); tree.delete(6); tree.delete(8);
        assertEquals("{null,300,null},500,{null,700,null}", tree.toText())
    }

    @Test fun removeKeyTreeTest() {
        var tree = AVLtree<Int, Int>()
        tree.insert(1, 100)
        tree.delete(1)
        assertEquals("null", tree.toText())

        tree = getTestingTree()
        tree.delete(1); tree.delete(25); tree.delete(23);
        tree.delete(21); tree.delete(19); tree.delete(17);
        tree.delete(15); tree.delete(13); tree.delete(11); tree.delete(9); tree.delete(7); tree.delete(5);
        tree.delete(3); tree.delete(27);
        assertEquals("{{null,200,null},400,{{null,600,null},800,{null,1000,null}}},1200," +
                "{{null,1400,{null,1600,null}},1800,{{{null,2000,null},2200,null},2400," +
                "{null,2600,{null,2800,null}}}}", tree.toText())
    }

    @Test fun treeUnionTreeTest() {
        var tree1 = AVLtree<Int, Int>()
        var tree2 = AVLtree<Int, Int>()
        assertEquals("null", tree1.union(tree2).toText())

        tree1.insert(3, 300); tree1.insert(7, 700); tree1.insert(5, 500)
        assertEquals("{null,300,null},500,{null,700,null}", tree1.union(tree2).toText())
        assertEquals("{null,300,null},500,{null,700,null}", tree2.union(tree1).toText())

        tree2.insert(3, 300); tree2.insert(7, 700); tree2.insert(10, 1000)
        assertEquals("{null,300,null},500,{null,700,{null,1000,null}}", tree1.union(tree2).toText())
    }

    @Test fun treeUnionHashMapTest() {
        var tree = AVLtree<Int, Int>()
        var hashMap = HashMap<Int, Int>()
        assertEquals("null", tree.union(hashMap).toText())

        tree.insert(3, 300); tree.insert(7, 700); tree.insert(5, 500)
        assertEquals("{null,300,null},500,{null,700,null}", tree.union(hashMap).toText())

        hashMap.insert(3, 300); hashMap.insert(7, 700); hashMap.insert(10, 1000)
        assertEquals("{null,300,null},500,{null,700,{null,1000,null}}", tree.union(hashMap).toText())

        tree = AVLtree<Int, Int>()
        assertEquals("{null,300,null},700,{null,1000,null}", tree.union(hashMap).toText())
    }

    @Test fun treeIntersectionTreeTest() {
        var tree1 = AVLtree<Int, Int>()
        var tree2 = AVLtree<Int, Int>()
        assertEquals("null", tree1.intersection(tree2).toText())

        tree1.insert(3, 300); tree1.insert(7, 700); tree1.insert(5, 500)
        assertEquals("null", tree1.intersection(tree2).toText())
        assertEquals("null", tree2.intersection(tree1).toText())

        tree2.insert(3, 300); tree2.insert(7, 700); tree2.insert(10, 1000)
        assertEquals("null,300,{null,700,null}", tree1.intersection(tree2).toText())
    }

    @Test fun treeIntersectionHashMapTest() {
        var tree = AVLtree<Int, Int>()
        var hashMap = HashMap<Int, Int>()
        assertEquals("null", tree.intersection(hashMap).toText())

        tree.insert(3, 300); tree.insert(7, 700); tree.insert(5, 500)
        assertEquals("null", tree.intersection(hashMap).toText())

        hashMap.insert(3, 300); hashMap.insert(7, 700); hashMap.insert(10, 1000)
        assertEquals("null,300,{null,700,null}", tree.intersection(hashMap).toText())
    }
}