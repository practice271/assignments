package Homework

import javax.swing.plaf.ComponentUI

class osName (name : String) {
    var nameOs : String = name

    fun Protect (): Double {
        if (nameOs == "Windows") return 0.6
        else if (nameOs == "Linux") return 0.2
        else if (nameOs == "OS_X") return 0.15
        else if (nameOs == "Android") return 0.3

        return 0.0
    }
}


class Computer(os : osName, infect : Boolean)
{
    val OS = os
    var Infected = infect

    fun IsInfected () = Infected

    fun IsOS () = OS

    fun TryInfect () {
        if (OS.Protect() < 1) {
            Infected = true
        }
    }
}

fun InitComps (comps : List<String>, infect : List<Boolean>): List<Computer> {
    var res : List<Computer> = listOf()

    for (i in 0..(comps.count() - 1))
    {
        res.plus(Computer(osName(comps[i]), infect[i]))
    }

    return res
}

class Network (val comps : List<Computer>, val edges : List<List<Int>>)
{
    var countComps = comps.count()

    fun NextStep ()
    {
        val infoList = Array(countComps, {i -> i}).filter { x -> comps[x].IsInfected() }

        infoList.forEach { x ->  edges[x].forEach { y -> comps[y].TryInfect() }}

    }

    fun CountInfected() : Int
    {
        val infoList = Array(countComps, {i -> i}).filter { x -> comps[x].IsInfected() }
        return infoList.count()
    }
}
