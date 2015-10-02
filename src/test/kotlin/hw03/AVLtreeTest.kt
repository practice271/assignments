package hw03

import org.junit.Test
import kotlin.test.assertEquals
import java.util.LinkedList

public class AVLtreeTest {

    fun getTestingTree() : AVLtree<Int> {
        val tree = AVLtree<Int>()
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

    Test fun emptyTreeTest() {
        val tree = AVLtree<Int>()
        assertEquals(linkedListOf<Int>(), tree.toList())
    }

    Test fun insertKeyTreeTest() {
        var tree = AVLtree<Int>()
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

    Test fun findKeyEmptyTreeTest() {
        val tree = AVLtree<Int>()
        assertEquals(null, tree.find(1))
    }

    Test fun findNonExistentTreeTest() {
        val tree = AVLtree<Int>()
        tree.insert(3, 300); tree.insert(7, 700); tree.insert(5, 500)
        assertEquals(null, tree.find(1))
        assertEquals(null, tree.find(4))
        assertEquals(null, tree.find(6))
        assertEquals(null, tree.find(8))
    }

    Test fun findKeyTreeTest() {

        var tree = AVLtree<Int>()
        tree.insert(1, 100); tree.insert(5, 500); tree.insert(9, 900);
        assertEquals(100, tree.find(1))
        assertEquals(500, tree.find(5))
        assertEquals(900, tree.find(9))

        tree = getTestingTree()
        assertEquals(600, tree.find(6))
        assertEquals(1300, tree.find(13))
        assertEquals(2000, tree.find(20))
        assertEquals(1400, tree.find(14))
        assertEquals(1900, tree.find(19))
    }

    Test fun removeKeyEmptyTreeTest() {
        val tree = AVLtree<Int>()
        tree.remove(1)
        assertEquals("null", tree.toText())
    }

    Test fun removeKeyNonExistentTreeTest() {
        val tree = AVLtree<Int>()
        tree.insert(3, 300); tree.insert(7, 700); tree.insert(5, 500)
        tree.remove(1); tree.remove(4); tree.remove(6); tree.remove(8);
        assertEquals("{null,300,null},500,{null,700,null}", tree.toText())
    }

    Test fun removeKeyTreeTest() {
        var tree = AVLtree<Int>()
        tree.insert(1, 100)
        tree.remove(1)
        assertEquals("null", tree.toText())

        tree = getTestingTree()
        tree.remove(1); tree.remove(25); tree.remove(23);
        tree.remove(21); tree.remove(19); tree.remove(17);
        tree.remove(15); tree.remove(13); tree.remove(11); tree.remove(9); tree.remove(7); tree.remove(5);
        tree.remove(3); tree.remove(27);
        assertEquals("{{null,200,null},400,{{null,600,null},800,{null,1000,null}}},1200," +
                "{{null,1400,{null,1600,null}},1800,{{{null,2000,null},2200,null},2400," +
                "{null,2600,{null,2800,null}}}}", tree.toText())
    }
}