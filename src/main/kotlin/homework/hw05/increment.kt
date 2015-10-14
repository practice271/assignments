// Homework 5
// Alekseev Aleksei, group 271.

package homework.hw05

public fun increment(x : Int, applyNum : Int, threadNum : Int) : Int {
    var count = x
    val threads = Math.min(applyNum, threadNum)
    val step : Int = applyNum / threads
    val threadArray = Array<Thread>(threads, { _ ->  Thread(fun() {for (j in 1..step) count++})})
    for (t in threadArray) {
        t.start()
    }
    for (t in threadArray) {
        t.join()
    }
    val temp = applyNum - step * threads
    if (temp != 0) for (k in 1..temp) {count++}
    return count
}


public fun measureTimeMillis(block : () -> Unit) : Long {
    val start = System.currentTimeMillis()
    block()
    return System.currentTimeMillis() - start
}

/*
fun main (args : Array<String>){
    println("Elapsed Time: " + measureTimeMillis(fun(){println(increment(0,100000,1))}))
    println("Elapsed Time: " + measureTimeMillis(fun(){println(increment(0,100000,2))}))
    println("Elapsed Time: " + measureTimeMillis(fun(){println(increment(0,100000,3))}))
    println("Elapsed Time: " + measureTimeMillis(fun(){println(increment(0,100000,4))}))
}
*/