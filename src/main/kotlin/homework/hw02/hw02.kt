package homework.hw02

import kotlin.test.assertEquals

//Peano
open class Peano {}
class S(val value:Peano): Peano() {}
class Zero():Peano() {}

fun plus (a:Peano, b:Peano):Peano{
    when (a) {
        is Zero -> return b
        is S -> {
            when (b) {
                is Zero -> return a
                is S    -> return plus(a.value, S(b))
                else    -> throw Exception("Unknown class")
            }
        }
        else -> throw Exception("Unknown class")
    }
}


fun sub (a:Peano, b:Peano):Peano{
    when (a){
        is Zero -> return Zero()
        is S    -> {
            when (b){
                is S    -> return sub(a , b)
                is Zero -> return a
                else -> throw Exception("Unknown class")
            }
        }
        else -> throw Exception("Unknown class")
    }
}


fun mult(a:Peano, b:Peano):Peano{
    when(a){
        is Zero-> return Zero()
        is S -> return plus(b, (mult(a.value, b)))
        else -> throw Exception("Unknown class")
    }
}

fun exp(a:Peano, b: Peano):Peano{
    when(b){
        is Zero -> return S(Zero())
        is S    -> return mult(a, exp(a , b.value ))
        else    -> throw Exception("Unknown class")
    }
}

fun toInt(p:Peano) : Int {
    when (p) {
        is Zero -> return 0
        is S    -> return 1 + toInt(p.value)
        else    -> throw Exception("Error")
    }
}
//find Max sum in Tree:
abstract class Tree {}
open class Empty() : Tree() {}
open class Leaf(val value : Int) : Tree() {}
open class Node(val value : Int, val left : Tree, val right : Tree) : Tree() {}


fun findMax(f: (Int,Int)->Int, acc:Int, tree:Tree ) : Int {
    when (tree) {
        is Empty -> return acc
        is Leaf -> return f (acc, tree.value)
        is Node -> {
            val tmp = f(acc, tree.value)
            return Math.max(findMax(f, tmp, tree.left), findMax(f, tmp, tree.right))
        }
        else -> throw Exception("Unknown class")
    }
}

fun fold(f:(Int, Int)->Int, acc:Int, tree:Tree ) : Int {
    when (tree) {
        is Empty -> return acc
        is Leaf -> return f (acc , tree.value)
        is Node -> {
            val tmp = f(acc , tree.value)
            return fold(f,fold(f, tmp, tree.left), tree.right)
        }
        else -> throw Exception("Unknown class")
    }
}
//HeapSort for Array<Int>
fun Heapify(arr:Array<Int>, i:Int, heapSize:Int){
    var max = Int.MIN_VALUE
    if ( 2 * i + 1 <= heapSize && arr[2*i + 1]>arr[i] ){
        max = 2 * i + 1
    }
    else {
        max = i
    }
    if ( 2 * i + 2 <= heapSize && arr[2*i+2]>arr[max] ){
        max = 2 * i + 2
    }
    if (max!=i){
        var el = arr[max]
        arr[max]  = arr[i]
        arr[i] = el
        Heapify (arr, max, heapSize)
    }
}

fun HeapSort (arr:Array<Int>):Array<Int>{
    var size = arr.size() - 1
    for (i in 0..size) {
        Heapify (arr, size - i, size)
    }
    //arr.printArray()
    for (i in 1..arr.size()- 1){
        var el = arr[arr.size()  - i]
        arr[arr.size()  - i] = arr[0]
        arr[0] = el
        size--
        Heapify(arr, 0, size)
    }
    return arr
}



fun main(args: Array<String>){

}
