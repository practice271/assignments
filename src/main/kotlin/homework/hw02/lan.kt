package homework.hw02

import java.util.*

class Computer(val number : Int, val os : String, val infectedChance : Int, var isInfected : Boolean) {}

open class LAN(var computers: Array<Computer>, val adjMatrix : Array<Array<Boolean>>) {
    val size = computers.size()

    fun show() {
        var i = 0
        while(i < this.size){
            print("Computer ${computers[i].number} : ")
            if (computers[i].isInfected) println("infected") else println("not infected")
        }
    }

    fun getInfected(): LinkedList<Computer> {
        var infected = LinkedList<Computer>()
        for (comp in this.computers) if (comp.isInfected) infected.addFirst(comp)
        return infected
    }

    fun getInfectedIndex(): LinkedList<Int> {
        var infected = LinkedList<Int>()
        var i = 0
        while (i < size) {
            if (this.computers[i].isInfected) infected.addFirst(i)
            i++
        }
        return infected
    }

    open fun getRandom(): Int {
        val random = Random()
        return random.nextInt(100)
    }

    fun tryInfect() {
        fun f(i: Int, j: Int, list: LinkedList<Int>): LinkedList<Int> {
            if (i >= size) return list
            else if (j >= size) return f(i + 1, 0, list)
            else
                if ((this.adjMatrix[i][j] == true) && (computers[i].isInfected) &&
                        (this.getRandom() > 100 - computers[j].infectedChance)) {
                    val l = f(i, j + 1, list)
                    l.addFirst(j)
                    return l
                } else return f(i, j + 1, list)
        }

        val infected = f(0, 0, LinkedList<Int>())
        for (i in infected) computers[i].isInfected = true
    }
}

fun modelLan() {
    val computers = arrayOf(Computer(0, "Ubuntu", 30, true), Computer(1, "WindowsXP", 40, false),
            Computer(2, "FreeBSD", 5, false), Computer(3, "WindowsXP", 40, false), Computer(4, "WindowsXP", 40, false),
            Computer(5, "WindowsXP", 40, false), Computer(6, "Windows98", 100, false),
            Computer(7, "WindowsXP", 40, false), Computer(8, "Ubuntu", 30, false), Computer(9, "FreeBSD", 5, false))
    val matrix = Array(10, { _ -> Array(10, { _ -> false }) })
    matrix[0][1] = true; matrix[1][0] = true; matrix[1][2] = true; matrix[2][1] = true; matrix[2][3] = true;
    matrix[3][2] = true; matrix[3][4] = true; matrix[4][3] = true; matrix[4][5] = true; matrix[5][4] = true;
    matrix[5][6] = true; matrix[6][5] = true; matrix[6][7] = true; matrix[7][6] = true; matrix[0][7] = true;
    matrix[7][0] = true; matrix[7][8] = true; matrix[8][7] = true; matrix[8][9] = true; matrix[9][8] = true;
    val lan = LAN(computers, matrix)
    println("""
    (0) — (7) — (8) — (9)
     |      |
    (1)    (6) — (5)
     |             |
    (2) — (3) — (4)
    """)
    lan.show()
    var i = 0
    while (i < 10) {
        lan.tryInfect()
        lan.show()
        i++
    }
}