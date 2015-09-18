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

fun InitComps (comps : Array<String>, infect : Array<Boolean>): Array<Computer> {
    var res : Array<Computer> = arrayOf()

    for (i in 0..(comps.count() - 1))
    {
        res.plus(Computer(osName(comps[i]), infect[i]))
    }

    return res
}

class Network (comps : Array<Computer>, edges : List<Any>)
{
    var time = 0

    fun NextStep ()
    {
        
    }
}
