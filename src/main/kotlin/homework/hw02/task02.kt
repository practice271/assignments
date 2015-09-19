// Задание 02 от 15.09.2015
// Автор: Кирилл Смиренко, группа 271
package homework.hw02

import java.util.Random
import java.util.LinkedList

fun getInfectProb(osName : String) : Double = when (osName) {
    "Windows" -> 0.4
    "Linux"   -> 0.15
    "Mac OS"  -> 0.05
    "Android" -> 0.3
    else      -> 0.0
}

class Computer(os : String) {
    private val osName = os
    private var infected = false

    fun infect() {
        this.infected = true
    }

    fun isInfected() : Boolean = this.infected

    fun tryInfect(rnd : Random) {
        if ((!infected) && (rnd.nextDouble() < getInfectProb(osName))) {
            this.infected = true
        }
    }

    override fun toString() = "${osName[0]}${if (this.infected) "!" else " "}"
}

class Network(comps : Array<String>, e : List<Pair<Int, Int>>, infected : List<Int>, r : Random) {
    private val computers = comps.map { x -> Computer(x) }
    private val sz = computers.size()
    private val edges = Array(sz, { LinkedList<Int>() })
    private val rnd = r
    private var time = 0

    init {
        // filling edge array
        for ((u, v) in e) {
            edges[u].add(v)
            edges[v].add(u)
        }
        for (i in infected) {
            computers[i].infect()
        }
    }

    fun printStats() {
        println("Time = $time")
        for (c in computers) {
            print("${c.toString()} ")
        }
        println()
    }

    fun tick() {
        time++
        val q = computers.indices.filter { computers[it].isInfected() }
        for (i in q)
            for (v in edges[i])
                computers[v].tryInfect(rnd)
    }

    fun getInfectionMap() : String {
        val sb : StringBuilder = StringBuilder {}
        for (c in computers)
            sb.append(if (c.isInfected()) "1" else "0")
        return sb.toString()
    }

    // get computer info
    fun ci(index : Int) : String = computers[index].toString()
}
