package homework.hw08

import java.util.*

class AVLTreeSet(val set :Array<Int>) : setInterface
{
    override fun iterator(): Iterator<Int> {
        return tree.iterator()
    }

    private var tree = Empty() as Tree

    init{
        for(item in set) tree = tree.Insert(item)
    }

    override fun Insert(value: Int) {
        tree = tree.Insert(value)
    }

    override fun Delete(value: Int) {
        tree = tree.Delete(value)
    }

    override fun Search(value: Int): Boolean {
        return tree.Search(value)
    }

    override fun Union(set1: setInterface) : setInterface {
        val finalSet = toArrayList().copyWithoutRepeate(set1.toArrayList())
        return AVLTreeSet(finalSet.toTypedArray())
    }

    override fun Intersect(set1: setInterface): setInterface {
        val temp = set1.toArrayList()
        val temp2 = toArrayList()
        val finalSet = ArrayList<Int>()
        for (item in temp2) if(temp.contains(item)) finalSet.add(item)
        return AVLTreeSet(finalSet.toTypedArray())
    }

    override fun toArrayList() : ArrayList<Int> {
        val temp = ArrayList<Int>()
        val res = arrayOf(Int)
        fun CLR(tree: Tree) {
            when (tree) {
                is Empty -> 0
                is Node -> {
                    temp.add(0, tree.value)
                    CLR(tree.l)
                    CLR(tree.r)
                }
                else -> throw Exception("Errro")
            }
        }
        CLR(tree)
        return temp
    }
}