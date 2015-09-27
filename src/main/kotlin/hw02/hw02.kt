package hw02

/**
 * hw 02
 * Antropov Igor 20.09.2015
 */

fun osTypeValue (type: String) : Double {
    when (type){
        "window"    -> return 0.5
        "mac"    -> return 0.3
        "linux"    -> return 0.7
        else -> return 0.0
    }
}

open class Computer(val type: String, val connections: Array<Boolean>, var inf: Boolean) {
    fun isInfected():Boolean { return inf}
    fun infection(random: Double) {
        if ( random >= osTypeValue(type)) inf = true
    }
    fun connected():Array<Boolean> {return connections}
}

fun oneStep(computers: Array<Computer>, probability: Double) {
    computers.forEach {
        if (it.isInfected()) for (i in it.connected().indices)
            if (it.connected()[i]) {
                computers[i].infection(probability)
            }
    }
}

fun situation(comps: Array<Computer>) : String {
    var lanSituation = ""
    comps.forEach {
        print(it.type)
        if (it.isInfected()) {
            lanSituation += "Infected "
            print("     ")
        }
        else {
            lanSituation += "Alive! "
            print("     ")
        }
        println(it.isInfected())
    }
    return  lanSituation
}

/**
 * 0----1----3     5
 * |    |    |
 * |    |    |
 * 2    4----|
 */

fun start(probability: Double) : String{
    val comps: Array<Computer> = arrayOf(
            Computer("windows", arrayOf(false, true,  true,  false, false, false), true ),
            Computer("mac",     arrayOf(true,  false, false, true,  true,  false), false),
            Computer("linux",   arrayOf(true,  false, false, false, false, false), false),
            Computer("mac",     arrayOf(false, true,  false, false, true,  false), false),
            Computer("windows", arrayOf(false, true,  false, true,  false, false), false),
            Computer("linux",   arrayOf(false, false, false, false, false, false), false)
    )
    var result: String
    for(i in 1..5)
        oneStep(comps, probability)
    result = situation(comps)
    return result
}