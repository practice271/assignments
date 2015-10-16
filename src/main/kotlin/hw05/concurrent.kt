package hw05

/* Concurrent programming
   (buggy increment and parallel mergesort)
   made by Guzel Garifullina
   Increment
   Estimated time 2 hours
   real time      2 hours
   Mergesort
   Estimated time 2 hours
   real time       hours
*/
import java.util.Arrays
class IncrementParallel(private  val threadnum: Int ) {
    private  class Value (private val v : Int){
        private var value = v
        public fun get() : Int{
            return value
        }
        public fun set(v : Int){
            value = v
        }
    }
    private var value = Value(0)

    public fun incBuggy(times : Int) : Int {
        private class IncBuggy(private val v: Value, private val times : Int) : Thread(){
            override public fun run () {
                for (i in 1..times) {
                    v.set(v.get() + 1)
                }
            }
        }
        value.set(0)
        var threads = Array(threadnum, {IncBuggy(value, times)})
        for (t in threads){ t.start() }
        for (t in threads){ t.join() }
        return (value.get())
    }

    public fun inc (times : Int) : Int {
        private class Inc(private val v: Value, private val times : Int) : Thread(){
            override public fun run () {
                synchronized(v){
                    for (i in 1..times) {
                        v.set(v.get() + 1)
                    }
                }
            }
        }
        value.set(0)
        var threads = Array(threadnum, {Inc (value, times)})
        for (t in threads){ t.start() }
        for (t in threads){ t.join() }
        return (value.get())
    }
}



class MergeParallel(private  val array: Array<Int> ) {
    private fun copy (arr : Array<Int>) : Array<Int>{
        val a  = Array (arr.size() , {i -> arr[i]})
        return a
    }
    val arr = copy(array)
    public fun mergesort() :  Array<Int>{
        fun copy (l : Int , r : Int) : Array<Int>{
            val a  = Array (r - l + 1, {i -> arr[l + i]})
            return a
        }
        fun sort(l : Int, r : Int): Array<Int>{
            fun swap ( i : Int, j : Int){
                val t = arr[i]
                arr[i] = arr[j]
                arr[j] = t
            }
            if (arr[l] > arr[r]){
                swap(l,r)
            }
            return copy(l, r)
        }
        fun merge(l : Int, r : Int) : Array<Int>{
            val mid = (r - l) / 2 + l
            val elems1 = mid - l + 1
            val elems2  = r - mid
            // first thread
            var a = Array (elems1, {0})
            val thread = Merge(a, l , mid )
            thread.start()
            thread.join()
            // second thread
            var b = Array (elems2, {0})
             when {
                (elems2 < 3) ->{
                    b = sort (mid + 1, r)
                }
                else ->{
                    b = merge(mid + 1 , r )
                }
            }
            val n = a.size()
            val m = b.size()
            val c = Array (n + m  ,{0} )
            var i = 0
            var j = 0
            while ((i < n) && (j < m)){
                if (a[i] < b[j]){
                    c[i + j] = a[i]
                    i++
                }
                else {
                    c[i + j] = b[j]
                    j ++
                }
            }
            while (i < n){
                c[i + j] = a[i]
                i++
            }
            while  (j < m) {
                c[i + j] = b[j]
                j ++
            }
            return c
        }
        class Merge(var newArr : Array<Int>, var  l : Int, var  r : Int) : Thread(){
            override public fun run () {
                when {
                    (r - l - 1 < 3) -> {
                        newArr = sort (l, r)
                    }
                    else -> {
                        newArr = merge(l, r)
                    }
                }
            }
        }
        return merge(0, arr.size() - 1)
    }
}

fun main(args: Array<String>) {
    var incs = IncrementParallel(8)
    val num = 10000000
    val result = incs.incBuggy(num)
    println("Buggy increment result = $result")

    val result1 = incs.inc(num)
    println("Correct increment result = $result1")
    val m = MergeParallel(Array (100, {i -> 100 - i}))
    val a = m.mergesort() //(Array (100, {i -> 100 - i}))
    for (i in a)
        print("$i ")
}
