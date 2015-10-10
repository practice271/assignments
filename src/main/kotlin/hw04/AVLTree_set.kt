package hw04

import java.util.*

class AVLTree_set(tree: Tree): SetInterface{
    private var tree = tree
    public override fun insert(key: Int): SetInterface{
        return AVLTree_set(tree.insertAVL(key))
    }
    public override fun toList(): List<Int> {
        return tree.toList()
    }
    public override  fun search(key: Int): Boolean{
        return tree.searchAVL(key)
    }
    public override  fun remove(key: Int): SetInterface{
        return AVLTree_set(tree.removeAVL(key))
    }
    public override fun union(set: SetInterface): SetInterface{
        var tree = Empty() as Tree
        val set1 = set.toList()
        val set2 = this.toList()
        for (i in set2) tree = tree.insertAVL(i)
        for (i in set1) tree = tree.insertAVL(i)
        return AVLTree_set(tree)
    }

    public override fun intersection(set: SetInterface): SetInterface{
        var tree = Empty() as Tree
        val set1 = set.toList()
        val set2 = this.toList()
        for (i in set2) if (set1.contains(i)){
            tree = tree.insertAVL(i)
        }
        return AVLTree_set(tree)
    }
}

/**fun main (args : Array<String>) {
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
}*/

