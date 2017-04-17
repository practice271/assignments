package hw05
/* Concurrent programming
   (buggy increment and parallel mergesort)
   made by Guzel Garifullina
   Increment
   Estimated time 2 hours
   real time      2 hours
   Mergesort
   Estimated time 2 hours
   real time      3 hours
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
/*  Sorted array of 5e6 elements
    with 1 thread for 4015 ms
    with 2 threads for 1629 ms*/
class MergeParallel(private  val arr: Array<Int>, private val threadNum : Int ){
    public fun parallelMergeSort() : Array<Int>{
        val size = arr.size()
        return parallelMergeSort(0, size - 1, threadNum)
    }
    private fun parallelMergeSort(l : Int, r : Int, threadCount : Int) : Array<Int>{
        if ((threadCount <= 1) || (r - l + 1 < 2)) {
            return mergeSort(l, r)
        }
        val mid = (r - l ) / 2 + l
        var a = Array (mid - l + 1, {0})
        val lThread = Thread {a = parallelMergeSort(l, mid,threadCount / 2 )}
        var b = Array (r - mid, {0})
        val rThread = Thread {b = parallelMergeSort(mid + 1, r, threadCount / 2 )}
        lThread.start()
        rThread.start()

        lThread.join()
        rThread.join()

        return merge(a , b)
    }
    private fun mergeSort(l : Int, r : Int) : Array<Int>{
        if ( r - l + 1 < 3){
            return sort(l, r)
        }
        val mid = (r - l) / 2 + l
        var a =   mergeSort (l, mid)
        var b = mergeSort(mid + 1 , r )
        return merge (a, b)
    }
    private fun sort(l : Int, r : Int): Array<Int>{
        fun swapArray (arr : Array<Int>, l : Int, r : Int) : Array<Int>{
            return Array(r - l + 1, {i -> arr[r - i]})
        }
        fun copyArray (arr : Array<Int>, l : Int, r : Int) : Array<Int>{
            return Array(r - l + 1, {i -> arr[l + i]})
        }
        if (arr[l] > arr[r]){
            return swapArray(arr, l, r)
        }
        return copyArray(arr, l, r)
    }
    private fun merge (a : Array<Int>, b : Array<Int>) : Array<Int>{
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
}

fun main(args: Array<String>) {
    var incs = IncrementParallel(8)
    val num = 10000000
    val result = incs.incBuggy(num)
    println("Buggy increment result = $result")

    val result1 = incs.inc(num)
    println("Correct increment result = $result1")
    val array = Array (5000000, {i -> 1000000 - i})
    val mergeClass1 = MergeParallel(array, 1)
    val startTime1 = System.currentTimeMillis()
    mergeClass1.parallelMergeSort()
    val endTime1 = System.currentTimeMillis()
    println("Sorted array of 5e6 elements ")
    println("with 1 thread for ${endTime1 - startTime1}")

    val mergeClass2 = MergeParallel(array, 2)
    val startTime2 = System.currentTimeMillis()
    mergeClass2.parallelMergeSort()
    val endTime2 = System.currentTimeMillis()
    println("with 2 threads for ${endTime2 - startTime2}")

}
