package homeworks

class Computer(val name: Int, val operationSystem: String, val probability: Double, var isInfected: Boolean = false) {}

fun computerToString(comp: Computer): String {
    /*
    var result    = ""
    val linux     = 0.2
    val windowsXP = 0.3
    val windows8  = 0.4
    val embox     = 0.1
    when (comp.operationSystem) {
        "linux"     -> result = "Computer $linux     with Linux     "
        "windowsXP" -> result = "Computer $windowsXP with Windows XP"
        "embox"     -> result = "Computer $embox     with Embox     "
        "windows8"  -> result = "Computer $windows8  with Windows 8 "
    }
    */
    val name = comp.name
    val operationSystem = comp.operationSystem
    var result = "Computer $name with $operationSystem "

    if (comp.isInfected) { result += " is     Infected" }
    else                 { result += " is not Infected" }

    return result
}

fun infection(network: List<List<Computer>>) : String {
    fun infectThisComputer(comp: Computer) {
        for (listOfComputers in network) {
            for (computer in listOfComputers) {
                if (computer.name == comp.name) {
                    computer.isInfected = true
                }
            }
        }
    }
    fun bfs(comp: Computer) : String {
        //comp.isInfected = true
        infectThisComputer(comp)
        networkToPrint(network)
        var queue = linkedListOf(comp)
        while(queue.size() != 0) {
            var temp = queue.first()
            queue.remove()
            for (computer in network[temp.name]) {
                if (!computer.isInfected) {
                    if ((computer.probability == 0.0) || (computer.probability > 1.0)){
                        return "Fail. Error Probability"
                    }
                    if (Math.random() <= computer.probability) {
                        queue.add(computer)
                        //i.isInfected = true
                        infectThisComputer(computer)
                    }
                    else (queue.add(temp))
                }
                //networkToPrint(network)
            }
            networkToPrint(network)
        }
        return "Success!"
    }
    return bfs(network.first().first())
}

fun networkToPrint(network: List<List<Computer>>) {
    for (i in network) {
        println(computerToString(i.first()))
    }
    println("________________________________________")
}