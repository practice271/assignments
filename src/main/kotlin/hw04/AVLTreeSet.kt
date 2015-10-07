package hw04

import java.util.*

public class AVLTreeSet() : Set() {

    public var root: Node? = null

    override public fun search (value: Int): Boolean {
        return root?.search(value) ?: false
    }

    override public fun insert( value: Int) {
        if (root == null) root = Node(value, null, null)
        else root?.insertNode(value)
    }
    override public fun delete( value: Int) {
        if (root != null) root?.removeNode(value)
    }

    override public fun toList(): ArrayList<Int> {
        return root?.toList() ?: ArrayList()
    }

    override public fun union(set: Set): Set {
        val result: Set = AVLTreeSet()
        val firstList = this.toList()
        val secondList = set.toList()

        for (elem in firstList) result.insert(elem)
        for (elem in secondList) if(result.search(elem) == false) result.insert(elem)
        return result
    }

    override public fun insersection(set: Set): Set {
        val result = AVLTreeSet()
        val firstList = this.toList()
        val secondList = set.toList()
        for (elem in firstList) if (secondList.contains(elem)) result.insert(elem)
        return result
    }
}
