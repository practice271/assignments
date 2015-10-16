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
fun main(args: Array<String>) {
    var incs = IncrementParallel(8)
    val num = 10000000
    val result = incs.incBuggy(num)
    println("Buggy increment result = $result")

    val result1 = incs.inc(num)
    println("Correct increment result = $result1")
}
