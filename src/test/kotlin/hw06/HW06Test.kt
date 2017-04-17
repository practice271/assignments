package hw06

import org.junit.Test
import kotlin.test.assertEquals

public class HW06Test {
    @Test fun tic1() {
        Console.clear()
        Console.add(0, 0)
        Console.add(1, 1)
        Console.add(0, 1)
        Console.add(2, 0)
        val res = Console.add(0, 2)
        assertEquals(1, res)
    }
    @Test fun tic2() {
        Console.clear()
        Console.add(0, 0)
        Console.add(0, 1)
        Console.add(1, 1)
        Console.add(2, 0)
        val res = Console.add(2, 2)
        assertEquals(1, res)
    }
    @Test fun tic3() {
        Console.clear()
        Console.add(0, 0)
        Console.add(1, 1)
        Console.add(1, 0)
        Console.add(2, 2)
        val res = Console.add(2, 0)
        assertEquals(1, res)
    }
    @Test fun toe1() {
        Console.clear()
        Console.add(1, 1)
        Console.add(0, 0)
        Console.add(2, 0)
        Console.add(0, 1)
        Console.add(2, 2)
        val res = Console.add(0, 2)
        assertEquals(2, res)
    }
    @Test fun toe2() {
        Console.clear()
        Console.add(0, 1)
        Console.add(0, 0)
        Console.add(1, 1)
        Console.add(1, 0)
        Console.add(0, 2)
        val res = Console.add(2, 0)
        assertEquals(2, res)
    }
    @Test fun toe3() {
        Console.clear()
        Console.add(0, 1)
        Console.add(0, 0)
        Console.add(1, 0)
        Console.add(2, 2)
        Console.add(2, 1)
        val res = Console.add(1, 1)
        assertEquals(2, res)
    }
    @Test fun dramInGame() {
        Console.clear()
        Console.add(1, 1)
        Console.add(0, 0)
        Console.add(2, 2)
        Console.add(2, 0)
        Console.add(1, 0)
        Console.add(1, 2)
        Console.add(0, 1)
        Console.add(2, 1)
        val res = Console.add(0, 2)
        assertEquals(0, res)
    }
}
