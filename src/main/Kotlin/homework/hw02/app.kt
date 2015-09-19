package homework.hw02

fun prob(name: String) : Double
{
    if(name == "Windows") return 0.5
    else if(name == "Linux") return 0.2
    else if(name == "OS X") return 0.3
    else throw  Exception ("Unknow OS")
}

class Computer(val number : Int, val OS : String)
{
    var infected = false
    fun infect(probability : Double)
    {
        if (probability <= prob(OS)) infected = true
    }
    fun info ()
    {
        if (infected) print("$number is infected")
        else print ("$number is not infected")
    }
}

class computerNet(OSlist : List<String>, val labels : Array<Boolean>, val Edges : List<Pair<Int, Int>>) {
    val size = OSlist.size()
    val arr = Array(size, { i -> Computer(i, OSlist[i]) })
    val matrix = Array(size, { i -> Array(size, { i -> false }) })
    init
    {
        for (item in Edges) matrix[item.first][item.second] = true
    }
    val vertix = arr
    fun isWay (v1:Int,v2:Int) : Boolean = matrix[v1][v2]
}


class Net(val net : computerNet)
{
    var move = 0
    val n = net.size - 1
    init
    {
        for( i in 0 .. n)
            if(net.labels[i]) net.vertix[i].infect(0.0)
    }
    fun NumberOfInf() : Int
    {
        var res = 0
        for(item in net.vertix)
        {
            if(item.infected) res++
        }
        return res
    }
    fun start(v : Int) // v is just for test
    {
        move ++
        val inf = net.vertix.filter { i -> i.infected}
        for(i in 0 .. inf.size() - 1)
        {
            for (j in 0..n)
            {
                if(net.isWay(net.vertix[i].number, net.vertix[j].number))
                {
                    val rand = java.util.Random().nextDouble() + v
                    net.vertix[j].infect(rand)

                }

            }
        }
        for(i in 0..n)
        {
            if (net.vertix[i].infected) net.labels[i] = true
        }
    }
    fun status()
    {
        print("\n\nMove: $move \nStatus:\n")
        val stat = Array(net.vertix.size() - 1, {i -> if(net.vertix[i].infected) '!' else ' '})
        println("Windows ${stat[0]}  -  Linux ${stat[1]}  -  OS X ${stat[2]}")
        println("Linux ${stat[3]}    -  Linux ${stat[4]}  -  Linux ${stat[5]}")
        println("OS X ${stat[6]}    -  Windows ${stat[7]}")
        println("OS X ${stat[0]}     -  Linux ${stat[0]}")
    }
}


fun main(args : Array<String>)
{
    val OSList = listOf<String>("Windows", "Linux", "OS X", "Linux",
                                "Linux", "Linux","OS X", "Windows",
                                 "OS X", "Linux")
    val edges = listOf<Pair<Int,Int>>(Pair(0,1),Pair(0,4),Pair(1,2),Pair(1,0),
                                      Pair(2,4),Pair(2,3),Pair(4,5),Pair(6,7),
                                      Pair(6,8), Pair(7,6), Pair(7,9), Pair(8,6),
                                      Pair(8,9), Pair(9,7), Pair(9,8))
    val labels  = arrayOf( false, true, false, false, false,
                           false, false, true, false, false )
    val graph =  computerNet (OSList, labels, edges)
    val localNet =  Net (graph)
    localNet.status()
    while (localNet.NumberOfInf() < OSList.size())
    {
        localNet.start(0)
        localNet.status()
    }
}
