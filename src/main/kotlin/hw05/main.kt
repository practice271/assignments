package hw05

import java.util.ArrayList

public fun increment (numberOfThreads : Int, incr : Int) : Int{
   var result = 0
   var threadArray : Array<Thread> = Array(numberOfThreads, {i -> Thread ()})
   fun increase () { result++ }
   val number = incr / numberOfThreads
   val lastPart = incr - number * (numberOfThreads-1)
   for (i in 0..numberOfThreads-1) {
      threadArray[i] = Thread {
         for (k in 1..number) increase()
         if (i == numberOfThreads-1) {
            for (k in number+1..lastPart) increase()
         }
      }
   }
   for (i in 0..numberOfThreads-1) {
      threadArray[i].start()
   }
   for (i in 0..numberOfThreads-1) {
      threadArray[i].join()
   }
   return result
}

private fun insert (array : ArrayList<Int>, value : Int) : ArrayList<Int> {
   var newArr = array
   for (i in 0..array.size() - 1) {
      if (value <= array[i]) {
         newArr.add(i,value)
         return newArr
      }
   }
   newArr.add(array.size(), value)
   return newArr
}

private fun merge (array1 : ArrayList<Int>, array2 : ArrayList<Int>) : ArrayList<Int> {
   var newArr = array1
   for (i in 0..array2.size() - 1) {
      newArr = insert(array1, array2[i])
   }
   return newArr
}

private fun mergesort (array : ArrayList<Int>) : ArrayList<Int> {
   var newArr = arrayListOf(arrayListOf (array[0]))
   for (i in 1..array.size() - 1) { newArr.add(i, arrayListOf(array[i])) }
   while (newArr.size() != 1) {
      val size = newArr.size() / 2
      for (i in 1..size) {
         val k = (-i + size + 1) * 2 - 1
         newArr[k - 1] = merge (newArr[k], newArr[k - 1])
         newArr.remove(k)
      }
   }
   return newArr[0]
}

public fun mergesortThreads (array : ArrayList<Int>, numberOfThreads : Int) : ArrayList<Int> {
   var result : Array<ArrayList<Int>> = Array(numberOfThreads, {i -> arrayListOf<Int> ()})
   var threadArray : Array<Thread> = Array(numberOfThreads, {i -> Thread ()})
   val number = array.size() / numberOfThreads
   val lastPart = array.size() - number * (numberOfThreads-1)
   for (i in 0..numberOfThreads-1) {
      for (k in 0..number-1) {
         result[i].add(array[i*number + k])
      }
      if (i == numberOfThreads - 1) {
         for (k in number..lastPart - 1) {
            result[i].add(array[i * number + k])
         }
      }
      threadArray[i] = Thread {result[i] = mergesort(result[i])}
   }
   for (i in 0..numberOfThreads-1) {
      threadArray[i].start()
   }
   for (i in 0..numberOfThreads-1) {
      threadArray[i].join()
   }
   for (i in 1..numberOfThreads-1) {
      result[0] = merge (result[0], result[-i + numberOfThreads])
   }
   return result[0]
}