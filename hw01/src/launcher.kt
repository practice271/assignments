fun Array<out Any>.printArray() {
    this.forEach {
        print("$it ")
    }
    println()
}

fun testAll() {
    // testing task 01
    println("TASK 1:")
    val a = arrayOf(1, 4, 8, 7, 5, 2, 6, 10, 3, 9)
    print("Before sorting: ")
    a.printArray()
    a.heapsort()
    print("After sorting:  ")
    a.printArray()
    println()

    // testing task 02
    println("TASK 2:")
    val tree = Node(4, Node(3, intLeaf(1), intLeaf(2)), intLeaf(5))
    print(tree.toText())
    println("Max path in this tree = ${tree.maxPath()}")
    println()

    // testing task 03
    println("TASK 3:")
    println("Sum of all nodes in this tree = ${fold({a, b -> a + b}, 0, tree)}")
    println()

    // testing task 04
    println("TASK 4:")
    println("Peano: 2 ^ 3 - 3 = ${peanoSub(peanoPow(2.toPeano(), 3.toPeano()), 3.toPeano()).toInt()}")
}

fun main(args: Array<String>) {
    testAll()
}