/* Realization of heap sort made by Guzel Garifullina
   Estimated time 30 minutes
   real time      2 hours
*/
package hw01

/*fun main(args: Array<String>){
    // array in order ( here maximum shifts)
    val array = Array(10, {i -> (i )})
    println( "Initial array")
    printArray(array)

    heapSort(array)
    println( "Resulted array")
    printArray(array)

    // random array
    val array1 = arrayOf("", "cat", "roof", "home", "penny", "lollipop", "kandy", "cat", "dog")
    println( "Initial array")
    printArray(array1)

    heapSort(array1)
    println( "Resulted array")
    printArray(array1)
}*/

fun printArray<T : Comparable< T>>(array: Array<T>){
    for (i in array.indices) print ("${array[i]}, ")
    println()
}

fun heapSort<T : Comparable<T>>(array: Array<T>){
    val length = array.size()

    fun swap( i : Int, j : Int){
        val temp : T = array[i]
        array[i] = array[j]
        array[j] = temp
    }

    fun sift( l : Int, r : Int){ // l and r are left and right bounds respectively
        var i : Int = l
        var j : Int = 2 * l + 1
        val x   = array[i] // highest node

        if ((j < r) && (array[j + 1] > array[j])) j ++ // right child bigger than left one

        while ((j <= r) && (array[j] > x)){
            array[i] = array[j]
            i = j
            j = 2 * j + 1

            if ((j < r) && (array[j + 1] > array[j])) j ++
        }
        array[i] = x
    }

    var r = length - 1
    var l : Int = length / 2 - 1 // make pyramid
    while (l >= 0) {
        sift(l , r)
        l--
    }

    while (r > 0){
        swap(0, r)
        r--
        sift(0, r)
    }
}
