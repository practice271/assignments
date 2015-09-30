package hw04.hashTable

/* Tests for realization of abstract
   set interface for AVL trees
   made by Guzel Garifullina
   Estimated time 30 minutes
   real time      1 hour
*/

import org.junit.Test
import java.util.*
import kotlin.test.assertFalse
import kotlin.test.assertTrue

public class hashTab {
    fun compareLists(list1: ArrayList<Int>, list2 : ArrayList<Int>): Boolean {
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
    var set : hashTable = hashTable()
    @Test fun Find1(){
        assertFalse (set.search<Int>(1))
    }
    @Test fun FindAndAdd(){
        set.insert<Int>(1)
        assertTrue (set.search<Int>(1))
    }
    @Test fun FindAndAdd2(){
        set.insert<Int>(1)
        set.insert<Int>(2)
        assertTrue (set.search<Int>(1))
    }
    @Test fun FindAddRemove(){
        set.insert<Int>(1)
        set.delete<Int>(1)
        assertFalse (set.search<Int>(1))
    }
    @Test fun AddManyTimes(){
        set.insert<Int>(1)
        set.insert<Int>(1)
        set.insert<Int>(1)
        set.insert<Int>(1)
        set.delete<Int>(1)
        assertFalse (set.search<Int>(1))
    }
    @Test fun ToList(){
        set.insert<Int>(1)
        set.insert<Int>(2)
        set.insert<Int>(7)
        set.insert<Int>(0)
        val expectedList = ArrayList<Int>(
                Arrays.asList(0, 1, 2, 7))
        assertTrue (compareLists(expectedList, set.toList<Int>()))
    }
    @Test fun Remove(){
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
    var set2 = hashTable()
    @Test fun Union(){
        set.insert<Int>(1)
        set.insert<Int>(2)
        set.insert<Int>(7)
        set.insert<Int>(0)

        set2.insert<Int>(8)
        set2.insert<Int>(0)
        set2.insert<Int>(6)
        set2.insert<Int>(7)
        val expectedList = ArrayList<Int>(
                Arrays.asList(0, 1, 2, 6, 7, 8))
        assertTrue (compareLists(expectedList, set.union(set2).toList<Int>()))
    }
    @Test fun Intersection(){
        set.insert<Int>(1)
        set.insert<Int>(2)
        set.insert<Int>(7)
        set.insert<Int>(0)

        set2.insert<Int>(8)
        set2.insert<Int>(0)
        set2.insert<Int>(6)
        set2.insert<Int>(7)
        val expectedList = ArrayList<Int>(
                Arrays.asList(0, 7))
        assertTrue (compareLists(expectedList, set.intersection(set2).toList<Int>()))
    }
}
