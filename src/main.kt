//Task 1. Heapsort.
fun swap(array:Array<Int>, i:Int, j:Int){
    val tmp = array[i]
    array[i] = array[j]
    array[j] = tmp
}
//Search maximum child
fun child(arr:Array<Int>, par:Int, n:Int):Int {
    if (n - 1 >= par * 2 + 2) {
        if (arr[2 * par + 1] > arr[2 * par + 2]) return (2 * par + 1)
        else return (2 * par + 2)
    }
    else if (n - 1 == par * 2 + 1) return (2 * par + 1)
    else return par
}
//Build binary heap
fun buildTree(arr:Array<Int>){
    val n = arr.size()
    var i = arr.size()/2 -1
    while (i >= 0){
        val tmp = i
        var ch = child(arr, i, n)
        while (arr[i] < arr[ch]){
            swap(arr, i, ch)
            i = ch
            ch = child(arr, i, n)
        }
        i = tmp - 1
    }
}

fun heapsort(arr:Array<Int>){
    var n = arr.size() - 1
    while (n > 0){
        swap(arr, 0, n)
        var i = 0
        var ch = child(arr, i, n )
        while (arr[i] < arr[ch]){
            swap(arr, i, ch)
            i = ch
            ch = child(arr, i, n)
        }
        n--
    }
}
//Task 2. Search maximum sum in tree.
abstract class Tree {}
open class Empty() : Tree() {}
open class Leaf(val value : Int) : Tree() {}
open class Node(val l : Tree, val value : Int, val r : Tree) : Tree() {}

fun genTree(l:Int, r:Int):Tree{
    if (l > r) return Empty()
    else if (l == r) return Leaf(l)
    else return Node(genTree(l, l + (r - l)/2 - 1),l + (r - l)/2, genTree(l + (r - l)/2 + 1, r ))
}

fun Tree.toText() : String {
    fun Tree.toText_() : List<String> {
        when (this) {
            is Empty -> return listOf("_\n")
            is Leaf  -> return listOf("${this.value}\n")
            is Node  -> {
                val lText = l.toText_().map { "| $it"}
                val rText = r.toText_().map { "| $it"}
                val vText = listOf("$value\n")
                return lText + vText + rText
            }
            else -> throw Exception("Unknown class")
        }
    }
    val builder = StringBuilder()
    val lines = this.toText_()
    lines.forEach { builder.append(it) }
    return builder.toString()
}

fun foldMax(f:(Int, Int)-> Int, acc:Int, tree:Tree):Int{
    when (tree){
        is Empty -> return acc
        is Leaf -> return (acc + tree.value)
        is Node -> return f(foldMax(f, acc+tree.value, tree.r), foldMax(f, acc + tree.value, tree.l))
        else -> throw Exception("Unknown class")
    }
}
fun maxWay(tree:Tree):Int{
    return foldMax({a,b -> Math.max(a,b)}, 0, tree)
}

//Task 3. Implementation fold.
fun fold(f:(Int, Int) -> Int, acc: Int, tree: Tree):Int {
    when (tree) {
        is Empty -> return acc
        is Leaf  -> return f(acc, tree.value)
        is Node  -> return fold(f, fold(f, f(acc, tree.value), tree.l), tree.r)
        else -> throw Exception("Unknown class")
    }
}

//Task 4. Numbers Peano.
abstract class Peano{}
open class Zero(): Peano(){}
open class S(val p:Peano): Peano(){}

fun printPeano(a:Peano){
    when (a){
        is Zero -> print("Zero")
        is S -> {
            print("S(")
            printPeano(a.p)
            print(")")
        }
    }
}

fun sum(a:Peano, b:Peano):Peano{
    when (a){
        is Zero -> return b
        is S -> return(S(sum(a.p, b)))
        else -> throw Exception("Unknown class")
    }
}

fun sub(a:Peano, b:Peano):Peano{
    when (b){
        is Zero -> return a
        is S -> {
            when (a){
                is Zero -> return Zero()
                is S -> return(sub(a.p, b.p))
                else -> throw Exception("Unknown class")
            }
        }
        else -> throw Exception("Unknown class")
    }
}

fun mult(a:Peano, b:Peano):Peano{
    when (a){
        is Zero -> return Zero()
        is S -> return sum(mult(a.p, b),b)
        else -> throw Exception("Unknown class")
    }
}

fun pow(a:Peano, b:Peano):Peano{
    when (a){
        is Zero -> return Zero()
        is S -> {
            when (b){
                is Zero -> return S(Zero())
                is S -> return mult(pow(a, b.p), a)
                else -> throw Exception("Unknown class")
            }
        }
        else -> throw Exception("Unknown class")
    }
}

fun main(args:Array<String>){
    println("1.Heapsort")
    val b = arrayOf(3,6,-1,0,8,11,5,9,7)
    println("Array:")
    b.forEach{
        print("$it ")
    }
    println()
    println("Binary heap:")
    buildTree(b)
    b.forEach {
        print("$it ")
    }
    println()
    println("Sorted array:")
    heapsort(b)
    b.forEach {
        print("$it ")
    }
    println()

    println("2.Max sum in tree")
    println("Tree:")
    val tr = Node(Leaf(10), 5, Node (Node(Leaf (4), 0, Leaf(100)), 1, Leaf(2)))
    print(tr.toText())
    println("Max sum:")
    print(maxWay(tr))
    println()

    println("3.Implementation fold")
    val c = genTree(0,5)
    println("Tree:")
    print(c.toText())
    println("Max in tree:")
    println(fold({x, y -> Math.max(x, y)}, 0, c))

    println("4.Numbers Peano")
    println("x = S(S(S(S(Zero())))), y = Zero(),z = S(S(Zero()))")
    val x = S(S(S(S(Zero()))))
    val y = Zero()
    val z = S(S(Zero()))
    println("x + y:")
    printPeano(sum(x,y))
    println()
    println("x - z:")
    printPeano(sub(x, z))
    println()
    println("y * z")
    printPeano(mult(y,z))
    println()
    println("y ^ x:")
    printPeano(pow (y,x))
    println()
    println("z ^ y:")
    printPeano(pow (z,y))
    println()
}