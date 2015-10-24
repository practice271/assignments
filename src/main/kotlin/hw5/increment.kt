package hw5

import java.util.*
import kotlin.concurrent.thread

class Counter(val initial: Int) {
    public var current = initial; private set

    private fun inc(v: Int) {
        current += v
    }

    /*
    * Example:
    * Run:
    * i.runThreads(10000)
    * Result:
    * i.current == 9995
    * */
    public fun runThreads(count: Int) {
        var l = LinkedList<Thread>()
        (1..count).forEach { l.add(thread(true) { inc(1) }) }
        l.forEach { t -> t.join() }
    }
}

