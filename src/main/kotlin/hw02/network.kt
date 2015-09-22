package hw02

import java.util.Random

class Comp(private val sysname: String, internal var inf: Boolean) {
    private fun InfProb(sys: String): Double = when(sys) {
        "Shindows" -> 0.52
        "Linuh"    -> 0.267
        "OS Pex"   -> 0.29
        "Vedroid"  -> 0.371
        else -> throw Exception("Nope, unknown system")
    }

    internal fun tryInfect(r: Random): Unit = if(r.nextDouble() < InfProb(sysname)) inf = true
}


class Network(private val clist: List<Comp>, private val connects: List<List<Int>>, private val rnd: Random) {

    private val size = clist.size()

    internal fun NextStep() {
        val inflist = Array(size, {i -> i}).filter { k -> clist[k].inf }
        inflist.forEach { i ->
            connects[i].forEach { j -> clist[j].tryInfect(rnd) }
        }
    }

    internal fun Makers() = clist.map { c: Comp -> if(c.inf) "!" else " " }

}

class Rand(private val pr: Double): Random() {
    override fun nextDouble() = pr
}



fun main(args: Array<String>) { }