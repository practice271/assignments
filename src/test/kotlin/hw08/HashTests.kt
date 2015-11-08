package hw08

import org.junit.Test
import kotlin.test.assertEquals

public class HashTable_insert
{
    var table : HashTable = HashTable(512)

    @Test fun Test0()
    {
        assertEquals(emptyList<Int>(), table.toList())
    }

    @Test fun Test1()
    {
        table.insert(2)

        assertEquals(listOf(2), table.toList())
    }

    @Test fun Test2()
    {
        table.insert(4)
        table.insert(2)
        table.insert(3)
        table.insert(5)

        assertEquals(listOf(2, 3, 4, 5), table.toList())
    }
}

public class HashTable_delete
{
    var table : HashTable = HashTable(512)

    @Test fun Test0()
    {
        assertEquals(emptyList<Int>(), table.toList())
    }

    @Test fun Test1()
    {
        table.insert(2)
        table.delete(2)

        assertEquals(emptyList<Int>(), table.toList())
    }

    @Test fun Test2()
    {
        table.insert(2)
        table.delete(3)

        assertEquals(listOf(2), table.toList())
    }

    @Test fun Test3()
    {
        table.insert(4)
        table.insert(2)
        table.insert(3)
        table.insert(5)

        table.delete(2)

        assertEquals(listOf(3, 4, 5), table.toList())
    }
}

public class HashTable_search
{
    var table : HashTable = HashTable(512)

    @Test fun Test0()
    {
        assertEquals(false, table.search(2))
    }

    @Test fun Test1()
    {
        table.insert(2)

        assertEquals(true, table.search(2))
    }

    @Test fun Test2()
    {
        table.insert(4)
        table.insert(2)
        table.insert(3)
        table.insert(5)

        assertEquals(true, table.search(3))
    }
}

public class HashTable_intersection
{
    var table1 : HashTable = HashTable(512)
    var table2 : HashTable = HashTable(512)

    @Test fun Test0()
    {
        table2.insert(2)

        table1 = table1.intersection(table2)

        assertEquals(emptyList<Int>(), table1.toList())
    }

    @Test fun Test1()
    {
        table2.insert(2)
        table1.insert(2)

        table1 = table1.intersection(table2)

        assertEquals(listOf(2), table1.toList())
    }
}

public class HashTable_union
{
    var table1 : HashTable = HashTable(512)
    var table2 : HashTable = HashTable(512)

    @Test fun Test0()
    {
        table2.insert(2)

        table1 = table1.union(table2)

        assertEquals(listOf(2), table1.toList())
    }

    @Test fun Test1()
    {
        table2.insert(2)
        table1.insert(4)

        table1 = table1.union(table2)

        assertEquals(listOf(2, 4), table1.toList())
    }
}
