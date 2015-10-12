package homework.hw04

import org.junit.Test
import kotlin.test.assertEquals

public class HashTable_deletion
{
    var table1 : HashTable = HashTable(100)
    @Test fun Test1(){
        table1.insertion(10)
        table1.insertion(15)
        table1.insertion(20)

        table1.deletion(20)
        assertEquals(listOf(10,15), table1.toList())

        table1.deletion(15)
        assertEquals(listOf(10), table1.toList())

        table1.deletion(10)
        assertEquals(listOf<Int>(), table1.toList())
    }
}
public class HashTable_union
{
    var table1 : Set = HashTable(10)
    var table2 : Set = HashTable(10)

    @Test fun Test1(){
        table1.insertion(10)
        table1.insertion(15)
        table1.insertion(20)

        table2.insertion(1)
        table2.insertion(2)
        table2.insertion(3)

        table1 = table1.union(table2)
        assertEquals(listOf(10, 20, 1, 2, 3, 15), table1.toList())
    }
}

public class HashTable_intersection
{
    var table1 : Set = HashTable(10)
    var table2 : Set = HashTable(10)
    var res    : Set = HashTable(10)

    @Test fun Test1(){
        table1.insertion(10)
        table1.insertion(15)
        table1.insertion(20)

        table2.insertion(10)
        table2.insertion(2)
        table2.insertion(3)

        res = table1.intersection(table2)
        assertEquals(listOf(10), res.toList())

        table2.insertion(15)
        table1 = table1.intersection(table2)
        assertEquals(listOf(10,15),table1.toList())
    }
}