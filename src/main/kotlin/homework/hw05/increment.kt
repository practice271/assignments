// Homework 5
// Alekseev Aleksei, group 271.

package homework.hw05

public fun newThread(block : () -> Unit) : Thread {
    val thread = object : Thread() {
        public override fun run() {
            block()
        }
    }
    thread.start()
    thread.join()
    return thread
}

public fun increment(x : Int, applyNum : Int, threadNum : Int) : Int {
    var count = x
    val threads = Math.min(applyNum, threadNum)
    val step = applyNum / threads
    for (i in 1..threads) {
        newThread(fun() {
            for (j in 1..step)
                count++
        })
    }
    val temp = applyNum - step * threads
    if (temp != 0) for (k in 1..temp) {count++}
//  println("result = " + count)
    return count
}

/*
public fun measureTimeMillis(block : () -> Unit) : Long {
    val start = System.currentTimeMillis()
    block()
    return System.currentTimeMillis() - start
}

fun main (args : Array<String>){
    println("Elapsed Time: " + measureTimeMillis(fun(){println(increment(0,10000,1))}))
    println("Elapsed Time: " + measureTimeMillis(fun(){println(increment(0,10000,2))}))
    println("Elapsed Time: " + measureTimeMillis(fun(){println(increment(0,10000,3))}))
    println("Elapsed Time: " + measureTimeMillis(fun(){println(increment(0,10000,1000))}))
}
*/