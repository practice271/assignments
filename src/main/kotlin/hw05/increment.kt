package hw05


fun incr(incNum: Int, threadNum: Int): Int {
    var res = 0
    val incPerThr = incNum / threadNum
    val thrArr = Array(threadNum-1, { Thread { for(i in 1..incPerThr) res++ } })
    thrArr.forEach { it.start() }
    for(i in 1..incPerThr + incNum % threadNum) res++
    thrArr.forEach { it.join() }
    return res
}

fun main(args: Array<String>) {
    println(incr(1000, 1))
    println(incr(1000, 2))
    println(incr(1000, 4))
    println(incr(1000, 8))
    println(incr(100000, 8))
    println(incr(100000, 16))
}
