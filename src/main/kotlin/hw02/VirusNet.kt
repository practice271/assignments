/* Net with viruses made by Guzel Garifullina
   Estimated time  3 hours
   real time       3 hours
*/

package hw02

import kotlin.test.assertEquals

class  Computer ( val os : String){
    val osDefence =
            when (os){
                "Windows" -> 0.38
                "Linux"   -> 0.72
                "Solaris" -> 0.66
                "Android" -> 0.68
                else      -> 0.10
            }
    var  infected = false
}

class LAN (val net : Array<BooleanArray>, val oSes : Array<String>) {
   // private :
    private val n: Int = net.size()
    val computers = Array(n, { i -> Computer(oSes[i]) })
    val victims = Array(n, { false })
    val nextVictims = Array (n - 1, { -1 })
    var nextVictimsNum = 0
    val currentVictims = Array (n - 1, { -1 })
    var currentVictimsNum = 0

    var firstInfected = -1
    var defaultAttack = -10.0


    fun copyNextToCurrentVictims(){
        for (i in 0.. nextVictimsNum - 1){
            currentVictims[i] = nextVictims[i]
        }
        currentVictimsNum = nextVictimsNum
        nextVictimsNum = 0
    }
    fun init() {
        if (firstInfected == -1) {
            firstInfected = Math.round(Math.random() * (n - 1)).toInt()
        }
      //  else firstInfected /= n

        computers[firstInfected].infected = true
        currentVictimsNum ++
        this.helthyNeigh(firstInfected)

    }

    val num = n
    var systemAlive = true

    fun isWay(i: Int, j: Int) = net[i][j]

    fun helthyNeigh(i: Int) {

        for (j in (n - 1) downTo  0) {
            if ( this.isWay(i, j) && (!computers[j].infected ) && (!victims[j])) {
                // not victims need to not add the same computers to nextVictims
                nextVictims [nextVictimsNum] = j
                nextVictimsNum++
                victims[j] = true

            }
        }
    }

    fun virusAttack (){

        this.copyNextToCurrentVictims()
        var attack : Double
        var x : Int
        for (i in 0 ..(currentVictimsNum - 1)){
            x = currentVictims[i]
            attack = if (defaultAttack < 0.0)  Math.random()  else defaultAttack

            if (attack  > computers[x].osDefence){

                computers[x].infected =  true
                this.helthyNeigh(x)

            }
            else {
                nextVictims [nextVictimsNum] = x
                nextVictimsNum++
            }
        }

        if (nextVictimsNum == 0){
            systemAlive = false
        }

    }


    fun printState () {
        fun infectedToStr(i : Int): String {
           if (computers[i].infected){
               return( oSes[i][0] + "!")
           }
           else {
               return (oSes[i][0] + " ")
           }
        }

        println("System state")

        val format = """
                ${infectedToStr(0)}------${infectedToStr(1)}      ${infectedToStr(2)}------ ${infectedToStr(3)}
                |       |       |        |
                |       |       |        |
                ${infectedToStr(4)}------${infectedToStr(5)}------${infectedToStr(6)}       ${infectedToStr(7)}
                        / \    /
                       /   \  /
                       ${infectedToStr(8)}---${infectedToStr(9)}"""
        println(format)

    }
}





fun main(args: Array<String>) {
    val n = 10

    val net = Array(n, { BooleanArray(n) })

    // make sure that net not connected
    for (i in net.indices){
        for (j in net.indices){
            net[i][j] = false
        }
    }

    fun  ex ( i: Int, j : Int){
        net[i][j] = true
        net[j][i] = true
    }
    //net[0] [1] = true
    //net[1][0] = true

    ex(0, 1)
    ex(0, 4)
    ex(1, 5)
    ex(2, 3)
    ex(2, 6)
    ex(3, 7)
    ex(4, 5)
    ex(5, 6)
    ex(5, 8)
    ex(5, 9)
    ex(6, 9)

    val  oSes = arrayOf("Android", "Windows", "Solaris", "Other",
            "Solaris", "Linux",  "Linux", "Linux",
            "Windows", "Android")

    val lan = LAN (net, oSes)
    lan.init ()
    lan.printState ()

    while (lan.systemAlive){
        lan.virusAttack()
        lan.printState ()

    }

}