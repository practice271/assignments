package hw03

import org.junit.Test
import kotlin.test.assertEquals

public class AVLTest {
    private fun Node.print(): String {
        //this ?: return "Tree is empty"
        return key.toString() + "  " + (right?.print()?:"") + "  " + (left?.print()?:"")
    }
    private fun printEmpty(n: Node): String = "TIE"

    private val t = AVLtree()
    init {
        t.insert(2)
        t.insert(10)
        t.insert(14)
        t.insert(6)
        t.insert(12)
        t.insert(100)
        t.insert(15)
        t.insert(1)
    }

    //@Test fun delete
}