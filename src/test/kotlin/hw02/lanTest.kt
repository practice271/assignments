package hw02

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

public class lanTest {
    Test fun LANTest01() {
        val computers = arrayOf(Computer(1, "1", 100, false), Computer(2, "2", 100, false), Computer(3, "3", 100, false),
                Computer(4, "4", 100, true), Computer(5, "5", 100, false))
        val matrix = Array(5, { _ -> Array(5, { _ -> true }) })

        class TestingLan(vertices: Array<Computer>, adjMatrix: Array<Array<Boolean>>) : LAN(vertices, adjMatrix) {
            override fun getRandom(): Int {
                return 0
            }
        }

        val lan = TestingLan(computers, matrix)
        var i = 0
        while (i < 10) {
            lan.tryInfect()
            i++
        }
        var ans = LinkedList<Int>()
        ans.addFirst(3)
        assertEquals(ans, lan.getInfectedIndex())
    }

    Test fun LANTest02() {
        val computers = arrayOf(Computer(1, "1", 0, false), Computer(2, "2", 0, false), Computer(3, "3", 0, false),
                Computer(4, "4", 0, true), Computer(5, "5", 0, false))
        val matrix = Array(5, { _ -> Array(5, { _ -> true }) })

        class TestingLan(vertices: Array<Computer>, adjMatrix: Array<Array<Boolean>>) : LAN(vertices, adjMatrix) {
            override fun getRandom(): Int {
                return 100
            }
        }

        val lan = TestingLan(computers, matrix)
        var i = 0
        while (i < 10) {
            lan.tryInfect()
            i++
        }
        var ans = LinkedList<Int>()
        ans.addFirst(3);
        assertEquals(ans, lan.getInfectedIndex())
    }

    Test fun LANTest03() {
        val computers = arrayOf(Computer(1, "1", 100, false), Computer(2, "2", 100, false), Computer(3, "3", 100, false),
                Computer(4, "4", 100, false), Computer(5, "5", 100, false))
        val matrix = Array(5, { _ -> Array(5, { _ -> true }) })

        class TestingLan(vertices: Array<Computer>, adjMatrix: Array<Array<Boolean>>) : LAN(vertices, adjMatrix) {
            override fun getRandom(): Int {
                return 100
            }
        }

        val lan = TestingLan(computers, matrix)
        var i = 0
        while (i < 10) {
            lan.tryInfect()
            i++
        }
        var ans = LinkedList<Int>()
        assertEquals(ans, lan.getInfectedIndex())
    }

    Test fun LANTest04() {
        val computers = arrayOf(Computer(1, "1", 1, true), Computer(2, "2", 1, false), Computer(3, "3", 1, false),
                Computer(4, "4", 1, false), Computer(5, "5", 1, false), Computer(6, "6", 1, false),
                Computer(7, "7", 1, false), Computer(8, "8", 1, false), Computer(9, "9", 1, false),
                Computer(10, "10", 1, false))

        val matrix = Array(10, { _ -> Array(10, { _ -> false }) })
        matrix[0][1] = true; matrix[1][2] = true; matrix[2][3] = true; matrix[3][4] = true; matrix[4][5] = true;
        matrix[5][6] = true; matrix[6][7] = true; matrix[7][8] = true; matrix[8][9] = true; matrix[9][0] = true;

        class TestingLan(vertices: Array<Computer>, adjMatrix: Array<Array<Boolean>>) : LAN(vertices, adjMatrix) {
            override fun getRandom(): Int {
                return 100
            }
        }

        val lan = TestingLan(computers, matrix)
        var i = 0
        while (i < 9) {
            lan.tryInfect()
            i++
        }
        assertEquals(computers.toList().reverse(), lan.getInfected())
    }

    Test fun LANTest05() {
        val computers = arrayOf(Computer(1, "1", 100, true), Computer(2, "2", 100, false), Computer(3, "3", 100, false),
                Computer(4, "4", 100, false), Computer(5, "5", 100, false))
        val matrix = Array(5, { _ -> Array(5, { _ -> false }) })
        matrix[1][2] = true; matrix[2][3] = true; matrix[3][4] = true; matrix[4][1] = true;

        class TestingLan(vertices: Array<Computer>, adjMatrix: Array<Array<Boolean>>) : LAN(vertices, adjMatrix) {
            override fun getRandom(): Int {
                return 100
            }
        }

        val lan = TestingLan(computers, matrix)
        var i = 0
        while (i < 10) {
            lan.tryInfect()
            i++
        }
        var ans = LinkedList<Int>()
        ans.addFirst(0)
        assertEquals(ans, lan.getInfectedIndex())
    }

    Test fun LANTest06() {
        val computers = arrayOf<Computer>()
        val matrix = Array(0, { _ -> Array(0, { _ -> false }) })

        class TestingLan(vertices: Array<Computer>, adjMatrix: Array<Array<Boolean>>) : LAN(vertices, adjMatrix) {
            override fun getRandom(): Int {
                return 100
            }
        }

        val lan = TestingLan(computers, matrix)
        var i = 0
        while (i < 10) {
            lan.tryInfect()
            i++
        }
        var ans = LinkedList<Int>()
        assertEquals(ans, lan.getInfectedIndex())
    }
}