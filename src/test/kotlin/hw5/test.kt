package hw5

import org.junit.Test

public class Test {

    val cases = arrayOf(arrayOf(3,1,2),
                        arrayOf(5,1,4,3,2),
                        arrayOf(7,5,1,3,6,2,4))

    val results = arrayOf(arrayOf(1,2,3),
                          arrayOf(1,2,3,4,5),
                          arrayOf(1,2,3,4,5,6,7))

    @Test fun test() {
        cases.forEach { a -> a.mergesort() }
        cases.zip(results).forEach { p -> assert(equalArrays(p.first, p.second)) }
    }

    fun <T>equalArrays(a: Array<T>, b: Array<T>): Boolean {
        return (a.size == b.size) &&
                a.zip(b).fold(true) { r, p -> p.first == p.second }
    }
}