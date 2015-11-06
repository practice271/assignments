package hw08

import org.junit.Test
import org.junit.Assert.*
import java.util.LinkedList
import java.util.Arrays

public class HashMapIteratorTest {
    @Test fun hasNextTest() {
        var hashMap = HashMap<Int, Int>()
        assertTrue(!hashMap.iterator().hasNext())
        hashMap.insert(1, 1)
        assertTrue(hashMap.iterator().hasNext())
        hashMap.delete(1)
        assertTrue(!hashMap.iterator().hasNext())
    }

    @Test fun testIterator() {
        var hashMap = HashMap<String, Int>()
        for (i in 0..1001)
            hashMap.insert(i.toString(), i)
        var resultList = LinkedList<Int>()
        for (i in 0..1001)
            resultList.add(i)
        var hashMapToList = LinkedList<Int>()
        for (elem in hashMap)
            hashMapToList.add(elem.second)
        val array = hashMapToList.toArray()
        Arrays.sort(array)
        assertArrayEquals(resultList.toArray(), array)
    }
}