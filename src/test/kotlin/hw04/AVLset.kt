package hw04

/* Tests for realization of abstract
   set interface for AVL trees
   made by Guzel Garifullina
   Estimated time 30 minutes
   real time      1 hour
*/
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

public class AVLset {
    fun compareLists(list1:  ArrayList<Int>, list2 :  ArrayList<Int>): Boolean {
        if (list1.lastIndex != list2.lastIndex){
            return false
        }
        var i2 = 0
        for (elem in list1){
            if (elem != list2.get(i2)){
                return false
            }
            i2++
        }
        return  true
    }
    @Test fun Find1(){
        var set= setAVL()
        assertFalse (set.search<Int>(1))
    }
    @Test fun FindAndAdd(){
        var set= setAVL()
        set.insert<Int>(1)
        assertTrue (set.search<Int>(1))
    }
    @Test fun FindAddRemove(){
        var set= setAVL()
        set.insert<Int>(1)
        set.delete<Int>(1)
        assertFalse (set.search<Int>(1))
    }
    @Test fun AddManyTimes(){
        var set= setAVL()
        set.insert<Int>(1)
        set.insert<Int>(1)
        set.insert<Int>(1)
        set.insert<Int>(1)
        set.delete<Int>(1)
        assertFalse (set.search<Int>(1))
    }
    @Test fun ToList(){
        var set= setAVL()
        set.insert<Int>(1)
        set.insert<Int>(2)
        set.insert<Int>(7)
        set.insert<Int>(0)
        val expectedList = ArrayList<Int>(
                Arrays.asList(0, 1, 2, 7))
        assertTrue (compareLists(expectedList, set.toList<Int>()))
    }
    @Test fun Remove(){
        var set= setAVL()
        set.insert<Int>(1)
        set.insert<Int>(2)
        set.insert<Int>(7)
        set.insert<Int>(0)
        set.delete<Int>(2)
        val expectedList = ArrayList<Int>(
                Arrays.asList(0, 1, 7))
        assertTrue (compareLists(expectedList, set.toList<Int>()))
    }
    @Test fun Remove2(){
        var set= setAVL()
        set.insert<Int>(1)
        set.insert<Int>(2)
        set.insert<Int>(7)
        set.insert<Int>(0)
        set.delete<Int>(2)
        set.delete<Int>(0)
        val expectedList = ArrayList<Int>(
                Arrays.asList(1, 7))
        assertTrue (compareLists(expectedList, set.toList<Int>()))
    }
    @Test fun Union(){
        var set= setAVL()
        set.insert<Int>(1)
        set.insert<Int>(2)
        set.insert<Int>(7)
        set.insert<Int>(0)

        var set2 = setAVL()
        set2.insert<Int>(8)
        set2.insert<Int>(0)
        set2.insert<Int>(6)
        set2.insert<Int>(7)
        val expectedList = ArrayList<Int>(
                Arrays.asList(0, 1, 2, 6, 7, 8))
        assertTrue (compareLists(expectedList, set.union(set2).toList<Int>()))
    }
    @Test fun Intersection(){
        var set= setAVL()
        set.insert<Int>(1)
        set.insert<Int>(2)
        set.insert<Int>(7)
        set.insert<Int>(0)

        var set2 = setAVL()
        set2.insert<Int>(8)
        set2.insert<Int>(0)
        set2.insert<Int>(6)
        set2.insert<Int>(7)
        val expectedList = ArrayList<Int>(
                Arrays.asList(0,7))
        assertTrue (compareLists(expectedList, set.intersection(set2).toList<Int>()))
    }
}