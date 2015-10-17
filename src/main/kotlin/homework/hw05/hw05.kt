package homework.hw05

fun increment(threadNumber : Int, totalCounter: Int){
   var result = 0
   val step = totalCounter / threadNumber
   val threadArray = Array(threadNumber,{Thread(fun(){ for (x in 1..step) result++})})
   for (x in threadArray) x.start()
   for (x in threadArray) x.join()
   println(result)

}

//MergeSort
fun merge(arr:Array<Int>, leftPointer:Int, middlePointer:Int, rightPointer:Int){
    var temp1    = middlePointer - leftPointer + 2
    var temp2    = rightPointer - middlePointer + 1
    var leftArr  = Array(temp1,{i -> 0})
    var rightArr = Array(temp2,{i -> 0})
    temp1--
    temp2--
    for (x in 0..temp1-1){
        leftArr[x] = arr[x+leftPointer]
    }
    for (x in 0..temp2-1){
        rightArr[x] = arr[x+middlePointer+1]
    }
    leftArr[temp1]  = Int.MAX_VALUE
    rightArr[temp2] = Int.MAX_VALUE
    var i=0; var j=0

    for (x in leftPointer..rightPointer){
        when{
            leftArr[i] < rightArr[j] -> {arr[x] = leftArr[i];  i++}
            else                     -> {arr[x] = rightArr[j]; j++}
        }
    }
}

fun sort(threadNumber: Int, arr:Array<Int>, left:Int, right:Int){
    val middle = left + (right-left)/2
    when{
        threadNumber > 1 && left < right -> {
            val t = Thread(fun(){sort(threadNumber/2, arr, left, middle)})
            t.start()
            sort(threadNumber/2, arr, middle+1,right)
            t.join()
            merge(arr, left, middle, right)
        }
        left < right -> {
            sort(threadNumber/2,arr,left,middle)
            sort(threadNumber/2, arr, middle+1,right)
            merge(arr,left,middle,right)
        }
    }
}

public fun measureTimeMillis(block : () -> Unit) : Long {
    val start = System.currentTimeMillis()
    block()
    return System.currentTimeMillis() - start
}


fun main(argv:Array<String>){
    //increment(8, 100000) //Always different results

    var arr = Array(1000000, {i -> -i})
    println("Elapsed Time: " + measureTimeMillis(fun(){sort(16, arr, 0 , arr.size()-1)}))
    //Elapsed Time: 421
    //println("Elapsed Time: " + measureTimeMillis(fun(){sort(2, arr, 0 , arr.size()-1)}))
    //Elapsed Time: 339
    //println("Elapsed Time: " + measureTimeMillis(fun(){sort(4, arr, 0 , arr.size()-1)}))
    //Elapsed Time: 283
    //println("Elapsed Time: " + measureTimeMillis(fun(){sort(8, arr, 0 , arr.size()-1)}))
    //Elapsed Time: 279
    //println("Elapsed Time: " + measureTimeMillis(fun(){sort(16, arr, 0 , arr.size()-1)}))
    //Elapsed Time: 281
}