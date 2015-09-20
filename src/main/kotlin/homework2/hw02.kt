package homework2

open class Computer(val OS: String, var infected: Boolean, val num:Int){}

open class Net(val comps: Array<Computer>, val graph: Array<Array<Boolean>>){}

fun chance(comp:Computer):Double{
    when (comp.OS){
        "Windows" -> return 0.213
        "Unix"    -> return 0.594
        "MacOS"   -> return 0.13
        "Linux"   -> return 0.3
        else -> throw Exception("Unknown class")
    }
}

fun status(network:Net, number:Double): String{
    network.comps.forEach {
        if (it.infected == true) {
            for (i in 0..network.comps.size() - 1){
                if ((((network.graph)[it.num])[i] == true)&&(chance((network.comps)[i]) < number)){
                    (network.comps)[i].infected = true
                }
            }
        }
    }
    var str = ""
    network.comps.forEach {
        str += it.infected.toString() + " "
    }
    return str
}

fun main(args: Array<String>) {
}