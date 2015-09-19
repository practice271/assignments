/* Model of local network
(expectation: 1 h; reality: 4 h)
by Sokolova Polina */

package hw02

abstract class IGraph(val array : Array<Array<Boolean>>) {
    fun isEdge(a : Int, b : Int) = array[a][b]
}

abstract class ArrayGraph(val arr : Array<Array<Boolean>>) : IGraph(arr) {}

fun probability(os : String) : Double {
    when (os) {
        "OS X"    -> { return 0.1 }
        "Linux"   -> { return 0.3 }
        "Windows" -> { return 0.6 }
    }
    return 1.0
}

class Computer(val os : String, val infected : Boolean, val number : Int) {
    var i = infected
    fun isInfected() : Boolean = i
    fun infection(v : Double) : Unit {
        if ((!i) && (v < probability(os))) { i = true }
    }
    fun state() : String {
        if (this.isInfected()) { return "+" }
        else { return "-"}
    }

}

class LocalNetwork(val os : Array<String>, val connected : Array<Array<Boolean>>,
                   val infection : Array<Boolean>) : ArrayGraph(connected) {
    var count = 0
    var c = arrayOfNulls(infection.size()) : Array<Computer?>
    //val c = arrayOf({ (i : kotlin.Int) -> Computer(os[i], infection[i], i)})

    fun letsInfect(random : Double) {
        val n = infection.size() - 1
        for (i in 0..n) {
            c[i] = Computer(os[i], infection[i], i)
        }
        val bad = arrayOfNulls(infection.size()) : Array<Int?>
        for (i in 0..n) {
            if (c[i]!!.isInfected()) {
                for (k in 0..n) {
                    if (connected[i][k]) {
                        c[k]!!.infection(random)
                    }
                }
            }
        }
        count = count + 1
    }

    fun smallStatus() : kotlin.String {
        var res = ""
        for (i in 0..os.size() - 1) { res += c[i]!!.state()}
        return res
    }

    fun status() {
        print("\n\n       %A.MyNetwork:\n $count")
        print("  OS X (0)")
        print(c[0]!!.state())
        print("  -  OS X (1)")
        print(c[1]!!.state())
        print("\n                   | ")
        print(" Linux (3)")
        print(c[3]!!.state())
        print("  -  Linux (4)")
        print(c[4]!!.state())
        print("\n    |              |")
        print("Windows (5)")
        print(c[5]!!.state())
        print(" -  OS X (2)")
        print(c[2]!!.state())
        print("\n             |")
        print("       Windows (6)")
        print(c[6]!!.state())
        print("\n")
    }
}
fun main(args : Unit) {

}