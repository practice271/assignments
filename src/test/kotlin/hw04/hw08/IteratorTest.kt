package hw04.hw08

/**
 * Created by Guzel Garifullina on 08.11.15.
 */

import hw04.AbstractSet
import hw04.hashTable.hashTable
import hw04.setAVL.setAVL
import org.junit.Test
import java.util.ArrayList
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

public class IteratorTest {
    fun walkToString<E : Comparable<E>>(set: AbstractSet<E>): String {
        val sb = StringBuilder()
        for (elem in set){
            sb.append("$elem ")
        }
        return sb.toString()
    }
    fun listToString <E : Comparable<E>>(list: ArrayList<E>): String {
        val sb = StringBuilder()
        for (elem in list){
            sb.append("$elem ")
        }
        return sb.toString()
    }
    var tSet : setAVL<Int> = setAVL()
    var hSet : hashTable<Int> = hashTable()
    fun initial (){
        tSet.insert(1)
        tSet.insert(2)
        tSet.insert(7)
        tSet.insert(0)

        hSet.insert(1)
        hSet.insert(2)
        hSet.insert(7)
        hSet.insert(0)
    }
    @Test fun AVLToString(){
        initial()
        assertEquals(listToString(tSet.toList()), walkToString(tSet))
    }
    @Test fun AVLSum(){
        initial()
        var sum = 0
        for (elem in tSet){
             sum += elem
        }
        assertEquals(10 , sum )
    }

    @Test fun hTableToString(){
        initial()
        assertEquals(listToString(hSet.toList()), walkToString(hSet))
    }
    @Test fun hTableSum(){
        initial()
        var sum = 0
        for (elem in hSet){
            sum += elem
        }
        assertEquals(10 , sum )
    }
    @Test fun hTableWithCollisionToString(){
        initial()
        hSet.insert(101)
        hSet.insert(1007)
        hSet.insert(207)
        val result = "0 1 101 2 7 1007 207 "
        assertEquals(result,walkToString(hSet))
    }

}