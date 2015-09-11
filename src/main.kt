import java.util.*

fun main(args: Array<String>) {

    println("1. Heapsort")

    println("Sorting Array<Int>: 9, 2, 3, 2, 8, 7, 6")
    var intArray: Array<Int> = arrayOf(9, 2, 3, 2, 8, 7, 6)
    intArray.heapsort()
    for (elem in intArray) print("$elem ")
    println()

    println("Sorting Array<Char>: 'z', 'a', 'y', 'b', 'x', 'c'")
    var charArray: Array<Char> = arrayOf('z', 'a', 'y', 'b', 'x', 'c')
    charArray.heapsort()
    for (elem in charArray) print("$elem ")
    println()

    println("Sorting Array<String>: \"Abc\", \"abc\", \"ab\", \"ba\", \"bA\"")
    var strArray: Array<String> = arrayOf("Abc", "abc", "ab", "ba", "bA")
    strArray.heapsort()
    for (elem in strArray) print("$elem ")
    println()

    println("2. Find maximum sum")

    val tree = Node(1, Node(8, Leaf(7), Leaf(5)), Node(5, Empty(), Node(4, Leaf(3), Leaf(2))))
    println("tree: Node(1, Node(8, Leaf(7), Leaf(5)), Node(5, Empty(), Node(4, Leaf(3), Leaf(2))))")
    var max = maxPath(tree)
    println("maximum sum: $max")

    println("3. Tree traverse")

    println("tree: Node(1, Node(8, Leaf(7), Leaf(5)), Node(5, Empty(), Node(4, Leaf(3), Leaf(2))))")

    println("Example: tree to list")
    val list = traverse(LinkedList(), { acc: LinkedList<Int>, elem: Int -> acc.addFirst(elem); acc }, tree)
    println(list)

    println("Example: sum of tree elements")
    var x = traverse(0, { acc: Int, elem: Int -> acc + elem }, tree)
    println(x)

    println("4. Peano numbers")

    println("a = S(S(S(Zero()))), b = S(S(Zero()))")
    var a: Peano = S(S(S(Zero())))
    var b: Peano = S(S(Zero()))
    print("a + b = ")
    println(peanoToInt(plus(a, b)))
    print("a - b = ")
    println(peanoToInt(minus(a, b)))
    print("a * b = ")
    println(peanoToInt(multiply(a, b)))
    print("a ^ b = ")
    println(peanoToInt(raising(a, b)))
}
