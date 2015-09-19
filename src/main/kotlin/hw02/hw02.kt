package hw02

/**
 * Created by Mikhail on 19.09.2015.
 */
fun getInfProb (OS: String): Double{
    when (OS) {
        "Windows" -> return 0.6
        "Linux"   -> return 0.2
        "Android" -> return 0.15
        "Mac OS"     -> return 0.1
        else      -> throw Exception("Unknown OS")
    }
}

class Computer (num: Int , OS: String) {
    var inf = false

    val number = num
    val OS = OS
    fun isInfected(): Boolean {return inf}
    fun infect(prob: Double) { if (prob <= getInfProb(OS)) {inf = true}}
    fun info() {
        if (inf) println("$number is infected")
        else println("$number is not infected")
    }
}

class GraphComputer (val OSList: Array<String>, val tags: Array<Boolean>, val list: Array<List<Int>>) {
    val n = OSList.size()
    val nodes = Array(n, { i -> Computer(i, OSList[i]) })
    val nodesNumber = n
    fun hasEdge(n1: Computer, n2: Computer): Boolean {
        return list[n1.number].contains(n2.number)
    }
}

class LAN(val graph: GraphComputer) {
    var m = 0
    val n = graph.nodesNumber - 1

    init {
        for (i in 0..n) {
            if (graph.tags[i]) graph.nodes[i].infect(0.0)
        }
    }

    fun infectedNumber(): Int {
        var ans = 0
        for (i in graph.nodes) {
            if (i.isInfected()) ans++
        }
        return ans
    }
    fun start() {
        m++
        for (i in 0..n) {
            if (graph.tags[i]) {
                for (j in 0..n) {
                    if (graph.hasEdge(graph.nodes[i], graph.nodes[j])) {
                        val rnd = java.util.Random().nextDouble()
                        graph.nodes[j].infect(rnd)
                    }
                }
            }
        }
        for (i in 0..n) {
            if (graph.nodes[i].isInfected()) graph.tags[i] = true
        }
    }
    fun printStatus() {
        print("\n\nMove: ${m}\nStatus:\n")
        var c = ""
        for (i in graph.nodes) {
            if (i.isInfected()) c += '!' else c += ' '
        }

        println("(1. Windows)${c[1]} -- (2. Android)${c[2]} -- (3. Mac OS  )${c[3]}")
        println(" |                |")
        println(" |                |")
        println("(0. Linux  )${c[0]} -- (4. Linux  )${c[4]} -- (5. Windows)${c[5]}")
        println("\n")
        println("(6. Linux  )${c[6]} -- (7. Windows)${c[7]}")
        println(" |                |")
        println(" |                |")
        println("(8. OS X   )${c[8]} -- (9. Android)${c[9]}\n")

        for (i in graph.nodes) i.info()
        print("\nPress any key to continue . . . ")
        System.`in`.read()
    }
}

fun main(args : Array<String>) {
    val OSList = arrayOf("Linux", "Windows", "Android", "Mac OS", "Linux", "Windows",
            "Linux", "Windows", "Mac OS", "Android")
    val tags = arrayOf(false, true, false, false, false, false, false, true, false, false)
    val list = arrayOf(listOf(1, 4), listOf(0, 2), listOf(1, 3, 4), listOf(2), listOf(0, 2, 5),
            listOf(4), listOf(7, 8), listOf(6, 9), listOf(6, 9), listOf(7, 8))

    val graph   = GraphComputer (OSList, tags, list)
    val network = LAN(graph)

    println("Press any key to continue . . . ")
    System.`in`.read()

    print("\nDemonstration of work of local network")
    network.printStatus()

    while (network.infectedNumber() < OSList.size()) {
        network.start()
        network.printStatus()
    }
}