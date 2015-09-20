package homeworks


class Computer(val name: Int, val probability: Double, var isInfected: Boolean = false) {}
fun computerToString(comp: Computer): String {
    var result    = ""
    val linux     = 0.2
    val windowsXP = 0.3
    val windows8  = 0.4
    val embox     = 0.1
    when (comp.probability) {
        linux     -> result = "Computer" + comp.name + " with Linux     "
        windowsXP -> result = "Computer" + comp.name + " with Windows XP"
        embox     -> result = "Computer" + comp.name + " with Embox     "
        windows8  -> result = "Computer" + comp.name + " with Windows 8 "
    }
    if (comp.isInfected) { result += " is     Infected" }
    else                 { result += " is not Infected" }

    return result
}

fun infection(network: List<List<Computer>>) : String {
    fun infectThisComputer(comp: Computer) {
        for (i in network) {
            for (j in i) {
                if (j.name == comp.name) {
                    j.isInfected = true
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
            //взяли из очереди компьютер, его номер в листе соответствует его имени(для удобства), иначе можно еще один цикл, для поиска нужного листа
            for (i in network[temp.name]) {
                if (!i.isInfected) {
                    if ((i.probability == 0.0) || (i.probability > 1.0)){
                        return "Fail. Error Probability"
                    }
                    if (Math.random() <= i.probability) {
                        queue.add(i)
                        //i.isInfected = true
                        infectThisComputer(i)
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