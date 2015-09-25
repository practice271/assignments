package Graphs

import java.util.*

interface Graph {
    fun addEdge(s: Int, t: Int)
    fun removeEdge(s: Int, t: Int)
    fun hasEdge(s: Int, t: Int): Boolean
    fun outByEdge(v: Int): List<Int>
    fun size(): Int
}

class MatrixGraph(val n: Int) : Graph {
    val mat = Array(n, { BooleanArray(n) })

    override fun addEdge(s: Int, t: Int) {
        mat[s][t] = true;
    }

    override fun removeEdge(s: Int, t: Int) {
        mat[s][t] = false
    }

    override fun hasEdge(s: Int, t: Int): Boolean = mat[s][t]

    override fun outByEdge(v: Int): List<Int> {
        val l = ArrayList<Int>()
        for ((u, x) in mat[v].withIndex())
            if (x)
                l.add(u)
        return l
    }

    override fun size(): Int = n

}

class MarkedGraph<T>(val g: Graph) : Graph {
    val mr = HashMap<Int, T>()

    override fun addEdge(s: Int, t: Int) {
        g.addEdge(s, t)
    }

    override fun removeEdge(s: Int, t: Int) {
        g.removeEdge(s, t)
    }

    override fun hasEdge(s: Int, t: Int): Boolean = g.hasEdge(s, t)
    override fun outByEdge(v: Int): List<Int> = g.outByEdge(v)
    override fun size(): Int = g.size()

    fun setMark(v: Int, t: T) {
        mr[v] = t
    }

    fun getMark(v: Int) = mr[v]
}

class Network(val g: MarkedGraph<Pair<String, Boolean>>) {
    val prob = mapOf(Pair("Windows", 0.9f),
            Pair("MacOS", 0.4f),
            Pair("Linux", 0.1f))
    val r = Random()
    var all = 0..g.size() - 1
    fun nameOf(v: Int) = g.getMark(v)!!.first
    fun infected(v: Int) = g.getMark(v)!!.second
    fun infect(v: Int) = g.setMark(v, Pair(nameOf(v), true))
    fun probabilisticInfect(v: Int, p: Float) = if (r.nextFloat() < p) infect(v)
    fun infectByOS(v: Int) = probabilisticInfect(v, prob[nameOf(v)]!!)
    fun allOfInfected(l: Iterable<Int>): Boolean = l.all { v -> infected(v) }
    fun oneOfInfected(l: Iterable<Int>): Boolean = l.any { v -> infected(v) }
    fun neighbourInfected(v: Int) = oneOfInfected(g.outByEdge(v))
    fun allInfected() = allOfInfected(all)
    fun canInfect(v: Int) = !infected(v) && neighbourInfected(v)
    fun step() = all.filter { v -> canInfect(v) }.forEach { v -> infectByOS(v }
}
