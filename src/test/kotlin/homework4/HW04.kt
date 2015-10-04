package homework4
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
    //Tests for AvlSet
    var set1 = AvlSet(arrayOf(2,5,10))
    @Test
    fun testInsert1(){
        set1.insert(8)
        set1.insert(3)
        set1.insert(12)
        set1.insert(2)
        assertEquals(" 2 3 4 5 8 10 12", set1.insert(4).toArray().arrToString())
    }
    var set2 = AvlSet(arrayOf(2,5,8,4,10,34,5,6))
    @Test
    fun testDelete1(){
        set2.delete(5)
        set2.delete(1)
        set2.delete(4)
        set2.delete(2)
        assertEquals(" 6 8 10 34", set2.delete(11).toArray().arrToString())
    }
    @Test
    fun testSearch1(){
        assertEquals(false, set1.search(8))
        assertEquals(true, set1.search(2))
        assertEquals(false, set2.search(1))
        assertEquals(true, set2.search(5))
    }
    var set3 = AvlSet(arrayOf(5,1,8,3,12,9))
    @Test
    fun testUnion1(){
        assertEquals(" 1 2 3 4 5 6 8 9 10 12 34", set2.union(set3).toArray().arrToString())
        assertEquals(" 1 2 3 5 8 9 10 12", set1.union(set3).toArray().arrToString())
        assertEquals(" 2 4 5 6 8 10 34", set2.union(set1).toArray().arrToString())
    }
    @Test
    fun testIntersect1(){
        assertEquals(" 5 8", set2.intersect(set3).toArray().arrToString())
        assertEquals(" 5", set1.intersect(set3).toArray().arrToString())
        assertEquals(" 2 5 10", set2.intersect(set1).toArray().arrToString())
    }
    //Tests for HashSet
    var set4 = HashSet(10).insert(3).insert(2).insert(5).insert(6).insert(1).insert(8)
    @Test
    fun testInsert2(){
        assertEquals(" 1 2 3 5 6 8", set4.toArray().arrToString())
    }
    @Test
    fun testDelete2(){
        assertEquals(" 2 3 5 8", set4.delete(10).delete(6).delete(12).delete(1).toArray().arrToString())
    }
    var set5 = HashSet(12).insert(2).insert(10).insert(7).insert(8).insert(1)
    @Test
    fun testSearch2(){
        assertEquals(false, set5.search(5))
        assertEquals(true, set4.search(5))
    }
    @Test
    fun testUnion2(){
        assertEquals(" 1 2 3 5 6 7 8 10", set4.union(set5).toArray().arrToString())
    }
    @Test
    fun testIntersect2(){
        assertEquals(" 1 2 8", set4.intersect(set5).toArray().arrToString())
    }
}