package hw4

import org.junit.Test
import java.io.OutputStreamWriter
import kotlin.test.assertEquals

public class TestAVL {
    @Test fun helloTest() {
        var t: Tree<Int> = Nil()
        val w = OutputStreamWriter(System.out)
        val i = arrayOf(5, 4, 6, 2, 1, 3, 7)
        val r = arrayOf(4, 3, 2)
        val ie = arrayOf("[5,[],[]]",
                         "[5,[4,[],[]],[]]",
                         "[5,[4,[],[]],[6,[],[]]]",
                         "[5,[4,[2,[],[]],[]],[6,[],[]]]",
                         "[5,[2,[1,[],[]],[4,[],[]]],[6,[],[]]]",
                         "[4,[2,[1,[],[]],[3,[],[]]],[5,[],[6,[],[]]]]",
                         "[4,[2,[1,[],[]],[3,[],[]]],[6,[5,[],[]],[7,[],[]]]]")
        val re = arrayOf("[3,[2,[1,[],[]],[]],[6,[5,[],[]],[7,[],[]]]]",
                         "[2,[1,[],[]],[6,[5,[],[]],[7,[],[]]]]",
                         "[6,[1,[],[5,[],[]]],[7,[],[]]]")
        for ((j, x) in i.withIndex()) {
            t = t.insert(x)
            assertEquals(t.text(), ie[j])
        }
        for ((j, x) in r.withIndex()) {
            t = t.remove(x)
            assertEquals(t.text(), re[j])
        }
        w.close()
    }
}