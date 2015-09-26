package homework2

class Computer(val OS: String, var infected: Boolean, val num:Int){
    fun chanceOfInfection():Double{
        when (OS){
            "Windows" -> return 0.213
            "Unix"    -> return 0.594
            "MacOS"   -> return 0.13
            "Linux"   -> return 0.3
            else -> throw Exception("Unknown class")
        }
    }
}

class Net(val comps: Array<Computer>, val graph: Array<Array<Boolean>>){
    fun status(number:Double): String{
        comps.forEach {
            if (it.infected) {
                for (i in 0..comps.size() - 1){
                    if ((((graph)[it.num])[i])&&(comps[i].chanceOfInfection() > number)){
                        (comps)[i].infected = true
                    }
                }
            }
        }
        var str = ""
        comps.forEach {
            str += it.infected.toString() + " "
        }
        return str
    }
}

fun main(args: Array<String>) {
}