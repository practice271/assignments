package hw05

import java.util.ArrayList

public fun increment (numberOfThreads : Int, incr : Int) : Int{
   var result = 0
   val number = incr / numberOfThreads
   val lastPart = incr - number * (numberOfThreads-1)
   fun increase () { result++ }
   var threadArray : Array<Thread> = Array(numberOfThreads, { i ->
      Thread {
         for (k in 1..number) increase()
         if (i == numberOfThreads-1) {
            for (k in number+1..lastPart) increase()
         }
      }
   })
   for (i in 0..numberOfThreads-1) {
      threadArray[i].start()
   }
   for (i in 0..numberOfThreads-1) {
      threadArray[i].join()
   }
   return result
}

private fun insert (array : ArrayList<Int>, value : Int, begin : Int, end : Int)
        : ArrayList<Int> {
   var newArr = array
   for (i in begin..end) {
      if (value <= array[i]) {
         newArr.add(i,value)
         return newArr
      }
   }
   newArr.add(end + 1, value)
   return newArr
}

private fun merge (array : ArrayList<Int>, beg1 : Int, end1 : Int,
                   beg2 : Int, end2 : Int) : ArrayList<Int> {
   var newArr = array
   for (i in beg2..end2) {
      val v = newArr[i]
      newArr.removeAt(i)
      newArr = insert(array, v, beg1, end1 + i - beg2)
   }
   return newArr
}

private fun mergesort (array : ArrayList<Int>,
                       begin : Int, end : Int) : ArrayList<Int> {
   var newArr = array
   var size = 2
   while (size <= end - begin + 1) {
      for (i in 0..(end - begin + 1)/size - 1) {
         newArr = merge (newArr, begin + i*size,  begin + i*size + size/2 - 1,
                 begin + i*size + size/2,begin + (i+1)*size - 1)
      }
      if (end - begin + 1 mod size != 0) {
         val lastPart = (end - begin + 1) mod size
         newArr = merge (newArr, begin, end - lastPart, end - lastPart + 1, end)
      }
      size = size * 2
   }
   return newArr
}

public fun mergesortThreads (array : ArrayList<Int>, numberOfThreads : Int)
        : ArrayList<Int> {
   var result = array
   val number = array.size() / numberOfThreads
   for (i in 0..numberOfThreads-1) {
      val thread = Thread {result = mergesort(result, i*number, (i+1)*number - 1)}
      thread.start()
      thread.join()
   }
   for (i in 1..numberOfThreads - 1){
      result = merge (result, 0, number * i - 1, number * i, number * (i+1) - 1)
   }
   if (array.size() - number * numberOfThreads != 0) {
      result = merge(result,0, number * numberOfThreads - 1,
              number * numberOfThreads, result.size() - 1)
   }
   return result
}