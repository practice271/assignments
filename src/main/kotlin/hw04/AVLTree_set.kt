package hw04

import java.util.*

class AVLTree_set(tree: Tree): SetInterface{
    private var tree1 = tree
    override fun insert(key: Int): Tree{
        return tree1.insertAVL(key)
    }
    override fun toList(): List<Int> {
        return tree1.toList()
    }
    override  fun search(key: Int): Boolean{
        return tree1.searchAVL(key)
    }
    override  fun remove(key: Int): Tree{
        return tree1.removeAVL(key)
    }
    override fun union(set: SetInterface): Tree{
        var tree = Empty() as Tree
        val set1 = (set as AVLTree_set).toList()
        val set2 = this.toList()
        for (i in set2) tree = tree.insertAVL(i)
        for (i in set1){
            if (!this.search(i)) tree = tree.insertAVL(i)
        }
        return tree
    }

    override fun intersection(set: SetInterface): Tree{
        var tree = Empty() as Tree
        val set1 = (set as AVLTree_set).toList()
        val set2 = this.toList()
        for (i in set2) if (set1.contains(i)){
            tree = tree.insertAVL(i)
        }
        return tree
    }
}

fun main (args : Array<String>) {
    val tree1 = AVLTree_set(Node(9,Empty(),Empty()))
    val tree2 = AVLTree_set(Node(1,Empty(),Empty()))
    val res1 = tree1.insert(8).toList()
    println("${res1}")
    val res2 = tree1.search(9)
    println ("${res2}")
    val res3 = tree1.remove(9).toList()
    println("${res3}")
    val res4 = (tree1.union(tree2)).toList()
    println("${res4}")
    val res5 = (tree1.intersection(tree2)).toList()
    println("${res5}")
}

