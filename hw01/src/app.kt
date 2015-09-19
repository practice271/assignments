
//Exercise ?1: HeapSort
fun Array<Int>.Heapsort()
{
    var i = this.size()/2 - 1
    while (i >= 0)
    {
        shiftDown(this, i, this.size())
        i--

    }
    i = this.size() - 1;
    while(i >= 1)
    {

        var temp : Int;
        temp = this[0]
        this[0] = this[i]
        this[i] = temp
        shiftDown(this, 0, i)
        i--

    }
}

fun shiftDown (array : Array<Int>,  p: Int, j: Int )
{
    var i = p
    var flag = false
    var temp: Int;
    var maxChild : Int


    while ((i * 2 + 1 < j) && (!flag))
    {
        if (i * 2 + 1 == j - 1) { maxChild = i * 2 + 1 };
        else {
            if (array[i * 2 + 1] > array[i * 2 + 2])
            {
                maxChild = i * 2 + 1
            };
            else
            {
                maxChild = i * 2 + 2
            }
        }

        if (array[i] < array[maxChild])
        {
            temp = array[i];
            array[i] = array[maxChild];
            array[maxChild] = temp;
            i = maxChild;
        }
        else
        {
            flag = true;
        }
    }
}

fun Array<out Any>.printArray()
{
    this.forEach{
        print ("$it, ")
    }
    println()
}



abstract class Tree() {}

class Empty : Tree()
class Leaf(val value : Int) : Tree(){}
class Node(val value : Int, val l : Tree, val r: Tree) : Tree() {}



fun Tree.toText() : String
{
    fun Tree.toText_() : List<String>
    {
        when (this)
        {
            is Empty -> return listOf("_\n")
            is Leaf  -> return listOf("${this.value}\n")
            is Node  ->
            {
                val lText = l.toText_().map { "  $it"}
                val rText = r.toText_().map { "  $it"}
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


//Exercise ?2:

fun  maxWay( f : (Int,Int) -> Int, acc : Int, tree : Tree) : Int
{

    var  temp = 0
    when(tree)
    {
        is Empty -> return acc
        is Leaf -> return  f (acc, tree.value)
        is Node ->
        {
            temp = f(acc, tree.value)
            return Math.max(maxWay(f, temp , tree.l),maxWay(f, temp, tree.r))
        }
        else -> throw Exception("Error")
    }
}

//Exercise ?3:

fun fold(f : (Int, Int) -> Int, g : (Int, Int) -> Int, acc : Int, tree : Tree) : Int
{
    when (tree)
    {
        is Empty -> return acc
        is Leaf  -> return f(acc, tree.value)
        is Node  ->
        {
            val temp = f(acc, tree.value)
            return f(temp, g(fold(f, g, acc, tree.l), fold(f, g, acc, tree.r)))
        }
        else -> throw Exception("Error")
    }
}


//Exercise ?4:
abstract class Peano() {}
class Zero : Peano(){}
class S(val value : Peano) : Peano(){}

fun genPeano (value : Int) : Peano
{
    if(value == 0)
    {
        return Zero()
    }
    else
    {
        return S(genPeano(value - 1))
    }
}

fun Peano.toInt():Int
{

    when(this)
    {
        is Zero -> return 0
        is S -> return 1 + this.value.toInt()
        else -> throw Exception("Error")
    }
}

fun Peano.print()
{
    when (this)
    {
        is Zero -> print("Zero")
        is S    ->
        {
            print("S(")
            (this.value).print()
            print(")")
        }
    }
}

fun sum (a: Peano, b: Peano): Peano
{
    when (a)
    {
        is Zero -> return b
        is S -> return S(sum(a.value,b))
        else -> throw Exception("Error")
    }
}

fun sub (a: Peano, b: Peano) : Peano
{

    when(a)
    {
        is Zero -> return Zero()
        is S -> when(b)
        {
            is Zero -> return a
            is S ->  return sub(a.value, b.value)
            else -> throw Exception("Error")
        }
        else -> throw Exception("Error")
    }
}

fun mult(a: Peano, b:Peano) : Peano
{
    when(a)
    {
        is Zero -> return Zero()
        is S -> when(b)
        {
            is Zero -> return Zero()
            is S -> return sum (mult(a,b.value), a )
            else -> throw Exception("Error")
        }
        else -> throw Exception("Error")
    }

}

fun pow(a: Peano, b: Peano) : Peano
{
    when(a)
    {
        is Zero -> return Zero()
        is S ->
            when(b)
            {
                is Zero -> return  S(Zero())
                is S -> return mult(pow(a,b.value), a)
                else -> throw Exception("Error")
            }
        else -> throw Exception("Error")
    }
}




fun main (args: Array<String>) {}

