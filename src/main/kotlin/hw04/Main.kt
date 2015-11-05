package hw04

import hw04.hashTable.hashTable
import hw04.setAVL.setAVL

/**
 * Created by GuzelGarifullina on 05.11.15.
 * Made Iterator - 1 hour
 */

fun main(args: Array<String>){
    var tSet : setAVL<Int> = setAVL()
    tSet.insert(1)
    tSet.insert(1)
    tSet.insert(2)
    tSet.insert(7)
    tSet.insert(0)
    println("AVL tree set : ")
    for (elem in tSet){
        print("$elem ")
    }
    println()

    var hSet : hashTable<Int> = hashTable()
    hSet.insert(1)
    hSet.insert(101)
    hSet.insert(2)
    hSet.insert(7)
    hSet.insert(1007)
    hSet.insert(207)
    hSet.insert(0)
    println("Hash table set : ")
    for (elem in hSet){
        print("$elem ")
    }
    println()
}
