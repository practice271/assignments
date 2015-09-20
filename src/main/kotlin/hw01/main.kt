package hw01

// task 1
fun Array<Int>.stoke(first : Int, last : Int) {
   val middle : Int
   if (first == 0) { middle = (last - first + 1) div 2}
   else { middle = this.size() div 2 }
   var i = first
   var max : Int
   while (i != middle) {
      if ((2 * i + 2) <= last) {
         if (this[2 * i + 1] < this[2 * i + 2]) {
            max = 2 * i + 2
         } else {
            max = 2 * i + 1
         }
      } else {
         max = 2 * i + 1
      }
      if (this[i] < this[max]) {
         val ch = this[i]
         this[i] = this[max]
         this[max] = ch
      }
      i++
   }
}

fun Array<Int>.heapsort() {
   val middle = this.size() div 2
   var beg = middle - 1
   while (beg != -1) {
      this.stoke(beg, this.size() - 1)
      beg--
   }
   var s = this.size() - 1
   while (s != 0) {
      val ch = this[s]
      this[s] = this[0]
      this[0] = ch
      this.stoke(0, s - 1)
      s--
   }
}

//tasks 2-3
abstract class Tree {}
open class Empty() : Tree() {}
open class Leaf(val value : Int) : Tree() {}
open class Node(val value : Int, val l : Tree, val r : Tree) : Tree() {}

fun Tree.maxSum() : Int {
   var sum = 0
   when(this) {
      is Empty -> return sum
      is Leaf -> return sum + this.value
      is Node -> {
         val max: Int
         val lMax = l.maxSum()
         val rMax = r.maxSum()
         if (lMax > rMax) {max = lMax}
         else {max = rMax}
         return sum + this.value + max
      }
      else -> throw Exception("Unknown class")
   }
}

fun <B> Tree.fold(fLeaf : (Int, B) -> B, fNode : (B, B) -> B, acc : B) : B {
   when (this) {
      is Empty -> return acc
      is Leaf  -> return fLeaf(value, acc)
      is Node  -> {
         val newAcc = fLeaf(value, acc)
         return fNode(l.fold(fLeaf, fNode, newAcc),
                 r.fold(fLeaf, fNode, newAcc))
      }
      else -> throw Exception("Unknown class")
   }
}

//task4
abstract class Peano {}
open class Zero() : Peano() {}
open class S(val value : Peano) : Peano() {}

fun plus(a : Peano, b : Peano) : Peano {
   when (a) {
      is Zero -> return b
      is S -> return S (plus (a.value, b))
      else -> throw Exception("Unknown class")
   }
}

fun minus(a : Peano, b : Peano) : Peano {
   when (a) {
      is Zero -> return Zero()
   }
   when (b) {
      is Zero -> return a
      is S -> when (a) {
         is S -> return minus (a.value, b.value)
         else -> throw Exception("Unknown class")
      }
      else -> throw Exception("Unknown class")
   }
}

fun multi(a : Peano, b : Peano) : Peano {
   when (a) {
      is Zero -> return Zero()
   }
   when (b) {
      is Zero -> return Zero()
      is S -> return plus (multi (a, b.value), a)
      else -> throw Exception("Unknown class")
   }
}

fun deg(a : Peano, b : Peano) : Peano {
   when (a) {
      is Zero -> return Zero()
   }
   when (b) {
      is Zero -> return S (Zero())
      is S -> return multi (deg (a, b.value), a)
      else -> throw Exception("Unknown class")
   }
}

fun Peano.toInt() : Int {
   when (this) {
      is Zero -> return 0
      is S -> return 1 + this.value.toInt()
      else -> throw Exception("Unknown class")
   }
}

