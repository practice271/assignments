/*
Homework 2 (15.09.2015)
Task 2

Author: Mikhail Kita, group 271
*/

package homework.hw02

fun infectionProb(OS : String) : Double {
    when(OS) {
        "Windows" -> return 0.647
        "Linux"   -> return 0.310
        "FreeBSD" -> return 0.154
        "OS X"    -> return 0.281
        else      -> throw Exception ("Incorrect OS")
    }
}

class Computer(val number : Int, val OS : String) {
    var infected = false

    fun isInfected() : Boolean {
        return infected
    }

    fun infect(probability : Double) {
        if (probability <= infectionProb(OS)) infected = true
    }

    fun info() {
        if (infected) println("$number: Infected")
        else println("$number: Not infected")
    }
}

class ComputerGraph(val OSList : Array<String>, val labels : Array<Boolean>, val aList : Array<List<Int>>) {
    val n           = OSList.size()
    val nodes       = Array(n, {i -> Computer(i, OSList[i])})
    val nodesNumber = n

    fun hasEdge(node1 : Computer, node2 : Computer) : Boolean {
        return aList[node1.number].contains(node2.number)
    }
}

class LocalNetwork(val graph : ComputerGraph) {
    var move = 0
    val n    = graph.nodesNumber - 1

    init {
        for (i in 0 .. n) {
            if (graph.labels[i]) graph.nodes[i].infect(0.0)
        }
    }

    fun infectedNumber() : Int {
        var answer = 0
        for (i in graph.nodes) {
            if (i.isInfected()) answer++
        }
        return answer
    }

    fun status() {
        print("\n\nMove: ${move}\nStatus:\n")
        var c = ""
        for (i in graph.nodes) {
            if (i.isInfected()) c+='#' else c+=' '
        }

        println("(1. Windows)${c[1]} -- (2. FreeBSD)${c[2]} -- (3. OS X   )${c[3]}")
        println(" |                |")
        println(" |                |")
        println("(0. Linux  )${c[0]} -- (4. Linux  )${c[4]} -- (5. Windows)${c[5]}")
        println("\n")
        println("(6. Linux  )${c[6]} -- (7. Windows)${c[7]}")
        println(" |                |")
        println(" |                |")
        println("(8. OS X   )${c[8]} -- (9. FreeBSD)${c[9]}\n")

        for (i in graph.nodes) i.info()
        print("\nPress any key to continue . . . ")
        System.`in`.read()
    }

    fun start(correction : Double) {
        move++
        for (i in 0 .. n) {
            if (graph.labels[i]) {
                for (j in 0 .. n) {
                    if (graph.hasEdge(graph.nodes[i], graph.nodes[j])) {
                        val rand = java.util.Random().nextDouble() + correction
                        graph.nodes[j].infect(rand)
                    }
                }
            }
        }
        for (i in 0 .. n) {
            if (graph.nodes[i].isInfected()) graph.labels[i] = true
        }
    }
}

fun main(args : Array<String>) {
    val OSList = arrayOf("Linux", "Windows", "FreeBSD", "OS X", "Linux", "Windows",
                    "Linux", "Windows", "OS X", "FreeBSD")

    val labels = arrayOf(false, true, false, false, false, false, false, true, false, false)

    val aList = arrayOf(listOf(1, 4), listOf(0, 2), listOf(1, 3, 4), listOf(2), listOf(0, 2, 5),
                    listOf(4), listOf(7, 8), listOf(6, 9), listOf(6, 9), listOf(7, 8))

    val graph   = ComputerGraph (OSList, labels, aList)
    val network = LocalNetwork (graph)

    println("Press any key to continue . . . ")
    System.`in`.read()

    print("\nDemonstration of work of local network")
    network.status()

    while (network.infectedNumber() < OSList.size()) {
        network.start(0.0)
        network.status()
    }
}