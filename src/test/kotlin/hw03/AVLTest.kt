package hw03

import org.junit.Test
import kotlin.test.assertEquals

public class AVLTest {
    /*private fun AVLtree.clrstr() : String {
        fun Node.print() : String {
            val lText = left?.print() ?: ""
            val rText = right?.print() ?: ""
            val vText = key.toString() + " "
            return vText + lText + rText
        }
        
        return root?.print() ?: "TIE"
    }
*/

    @Test fun insertion() {
        val t = AVLtree()
        assertEquals("TIE", t.toString())

        t.insert(4)
        assertEquals("4 ", t.toString())

        t.insert(2); t.insert(3)
        assertEquals("3 2 4 ", t.toString())

        t.insert(1)
        assertEquals("3 2 1 4 ", t.toString())
    }

    @Test fun deletion() {
        val t = AVLtree()
        t.delete(3)
        assertEquals("TIE", t.toString())

        t.insert(2); t.insert(10); t.insert(14); t.insert(6); t.insert(12); t.insert(100); t.insert(15); t.insert(17)
        t.delete(10)
        assertEquals("12 2 6 15 14 17 100 ", t.toString())

        t.delete(14)
        assertEquals("12 2 6 17 15 100 ", t.toString())
    }

    @Test fun searching() {
        val t = AVLtree()
        assertEquals(-1, t.search(5))

        t.insert(2); t.insert(10); t.insert(14); t.insert(6); t.insert(12); t.insert(100); t.insert(15); t.insert(17)
        assertEquals(10, t.search(10))
        assertEquals(6, t.search(6))
        assertEquals(2, t.search(2))
    }

    @Test fun printer() {
        val t = AVLtree()
        assertEquals("_\n", t.toText())

        t.insert(7); t.insert(14); t.insert(9)
        assertEquals("|  |  _\n|  (14)\n|  |  _\n(9)\n|  |  _\n|  (7)\n|  |  _\n", t.toText())
    }
}