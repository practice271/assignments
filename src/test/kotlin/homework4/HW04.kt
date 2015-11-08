package homework4
import homework3.AvlTree
import org.junit.Test
import kotlin.test.assertEquals

public class HW04 {
    fun Array<Int>.arrToString():String{
        var str = ""
        this.forEach {
            str = str + " " + "$it"
        }
    return str
    }
    //Tests for Iterator
    var set01 = Tree(AvlTree(AvlTree(null, 2, null), 5, AvlTree(null, 10, null)))
    @Test
    fun testIterator01(){
        var s = ""
        for (i in set01) s += "$i "
        assertEquals("2 5 10 ", s)
    }
    var set02 = Tree(AvlTree(AvlTree(null, 2, AvlTree(null, 3, null)), 5, AvlTree(AvlTree(null, 7, null) , 10, null)))
    @Test
    fun testIterator02(){
        var s = ""
        for (i in set02) s += "$i "
        assertEquals("2 3 5 7 10 ", s)
    }
    var set03 = Tree(null)
    @Test
    fun testIterator03(){
        var s = ""
        for (i in set03) s += "$i "
        assertEquals("", s)
    }
    var set11 = Hash(10).insert(3).insert(2).insert(5).insert(6).insert(1).insert(8)
    @Test
    fun testIterator11(){
        var s = ""
        for (i in set11) s += "$i "
        assertEquals("1 2 3 5 6 8 ", s)
    }
    var set12 = Hash(10).insert(3).insert(2).insert(5).insert(6).insert(1).insert(8). insert(11).insert(21)
    @Test
    fun testIterator12(){
        var s = ""
        for (i in set12) s += "$i "
        assertEquals("1 11 21 2 3 5 6 8 ", s)
    }
    var set13 = Hash(10)
    @Test
    fun testIterator13(){
        var s = ""
        for (i in set13) s += "$i "
        assertEquals("", s)
    }
    //Tests for Tree
    var set1 = Tree(AvlTree(AvlTree(null, 2, null), 5, AvlTree(null, 10, null)))
    @Test
    fun testInsert1(){
        set1.insert(8)
        set1.insert(3)
        set1.insert(12)
        set1.insert(2)
        assertEquals(" 2 3 4 5 8 10 12", set1.insert(4).toArray().arrToString())
    }
    var set2 = Tree(AvlTree(AvlTree(AvlTree(null, 2, null), 4, AvlTree(null, 5, null)), 6, AvlTree(null ,7, AvlTree(null, 10, null))))
    @Test
    fun testDelete1(){
        assertEquals(" 6 7 10", set2.delete(5).delete(1).delete(4).delete(2).toArray().arrToString())
    }
    @Test
    fun testSearch1(){
        assertEquals(false, set1.search(8))
        assertEquals(true, set1.search(2))
        assertEquals(false, set2.search(1))
        assertEquals(true, set2.search(5))
    }
    var set3 = Tree(AvlTree(AvlTree(AvlTree(null, 1, null), 3, null), 5, AvlTree(AvlTree(null, 8, null), 9, AvlTree(null, 12, null))))
    @Test
    fun testUnion1() {
        assertEquals(" 1 2 3 4 5 6 7 8 9 10 12", set2.union(set3).toArray().arrToString())
    }
    @Test
    fun testUnion2() {
        assertEquals(" 1 2 3 5 8 9 10 12", set1.union(set3).toArray().arrToString())
    }
    @Test
    fun testUnion3(){
        assertEquals(" 2 4 5 6 7 10", set2.union(set1).toArray().arrToString())
    }
    @Test
    fun testIntersect1() {
        assertEquals(" 5", set2.intersect(set3).toArray().arrToString())
    }
    @Test
    fun testIntersect2() {
        assertEquals(" 5", set1.intersect(set3).toArray().arrToString())
    }
    @Test
    fun testIntersect3(){
        assertEquals(" 2 5 10", set2.intersect(set1).toArray().arrToString())
    }
    //Tests for Hash
    var set4 = Hash(10).insert(3).insert(2).insert(5).insert(6).insert(1).insert(8)
    @Test
    fun testInsert2(){
        assertEquals(" 1 2 3 5 6 8", set4.toArray().arrToString())
    }
    @Test
    fun testDelete2(){
        assertEquals(" 2 3 5 8", set4.delete(10).delete(6).delete(12).delete(1).toArray().arrToString())
    }
    var set5 = Hash(12).insert(2).insert(10).insert(7).insert(8).insert(1).insert(13)
    @Test
    fun testSearch2(){
        assertEquals(false, set5.search(5))
        assertEquals(true, set4.search(5))
    }
    @Test
    fun testUnion4(){
        assertEquals(" 10 1 2 13 3 5 6 7 8", set4.union(set5).toArray().arrToString())
    }
    @Test
    fun testIntersect4(){
        assertEquals(" 1 2 8", set4.intersect(set5).toArray().arrToString())
    }
    //Tests for Tree with Hash
    @Test
    fun testUnion5(){
        assertEquals(" 1 12 2 3 5 6 8 9", set4.union(set3).toArray().arrToString())
    }
    @Test
    fun testIntersect5(){
        assertEquals(" 1 3 5 8", set4.intersect(set3).toArray().arrToString())
    }
}
