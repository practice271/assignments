package classroom.c05

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class C05Test {
    @Test fun insertTreeTest01() {
        val tree = Tree<Int>()
        val range = 1..5
        for (i in range) {
            tree.insert(i)
        }
        for (i in range) {
            if (!tree.contains(i)) { fail() }
        }
    }

    @Test fun removeTreeTest02() {
        val tree = Tree<Int>()
        val range = 1..5
        for (i in range) {
            tree.insert(i)
        }
        tree.remove(4)
        assertEquals(4, tree.size())
    }

    @Test fun removeTreeTest03() {
        val tree = Tree<Int>()
        val range = 1..5
        for (i in range) {
            tree.insert(i)
        }

        for (i in tree) {
            if (i < range.start || i > range.end) { fail() }
        }
    }
}
