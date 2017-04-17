package hw05

import java.util.*

public fun sum(array: Array<Int>, threadNumber: Int): Int{
    var threadArray1 = ArrayList<Int>().toArray()
    val arr = array
    var res = 0
    val size = arr.size()
    val step =  size / threadNumber
    val threadArray = Array(threadNumber - 1,{i -> Thread(fun(){
        for (j in i * step..(i+1) * step - 1){res += arr[j]}
    })})
    if (threadNumber != 1){
        threadArray1 = Array(size - step,{_ -> Thread(fun () {
            for (j in (threadNumber - 1) * step..size - 1) {res += arr[j]}
        })})
    }
    else {
        threadArray1 = Array(size,{_ -> Thread(fun() {
            for (i in 0..size - 1) {res += arr[i]}
        })})}
    val threadArrayRes = threadArray + threadArray1
    for (t in threadArrayRes) {t.start()}
    for (t in threadArrayRes) {t.join()}
    return res
}

fun main (args : Array<String>) {
    val array = Array(5, { i -> i })
    println("Array: [0,1,2,3,4]")
    val res1 = sum(array, 1)
    println("Result with one thread: ${res1}")
    val res2 = sum(array, 2)
    println("Result with two threads: ${res2}")
}