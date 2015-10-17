package hw5

import java.util.*
import kotlin.concurrent.thread

class Counter(val initial: Int) {
    private var current = initial

    fun get() = current

    fun inc(v: Int) {
        current += v
    }

    fun runThreads(count: Int) {
        var l = LinkedList<Thread>()
        (1..count).forEach { l.add(thread(true) { inc(1) }) }
        l.forEach { t -> t.join() }
    }
}

