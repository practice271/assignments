package hw02

/**
 * Created by z on 19.09.2015.
 */

fun probability(OS: String): Double {
    when(OS) {
        "Windows"  -> return 0.9
        "Linux"    -> return 0.1
        "Solaris"  -> return 0.8
        "Mac OS X" -> return 0.6
        else -> throw Exception ("Error")
    }
}

fun virus(OS: Array<String>, inf:Array<Boolean>, nei:Array<List<Int>>) {
    var size = inf.size() - 1
    var i = 0
    var j = 0
    while(size >= 0) {
        if (inf[i]) {
                j = nei[i].size() - 1
            while (j >= 0) {
                if ((probability(OS[nei[i][j]]) > 0.5)) {
                    inf[nei[i][j]] = true
                }
                j--
            }
        }
        size--
        i++
        j = 0
    }
}

fun status(OS: Array<String>, inf: Array<Boolean>) {
    var size = inf.size()
    while (size > 0) {
        if (inf[size - 1]) {
            print("Computer number "+size+" with OS "+OS[size-1]+" is infected"+"\n")
        }
        else {print("Computer number "+size+" with OS "+OS[size-1]+" is clear"+"\n")}
        size--

    }

}

fun main(args: Array<String>) {
    val OS = arrayOf("Linux", "Linux", "Windows", "Solaris", "Windows", "Mac OS X")
    var infected = arrayOf(false, false, true, false, true, false)
    val neighbors = arrayOf(listOf(1,5), listOf(0,4), listOf(0,3), listOf(2,5), listOf(1,3), listOf(0,3))
    print("Before"+"\n")
    status(OS, infected)
    print("After"+"\n")
    virus(OS,infected, neighbors)
    status(OS, infected)

}