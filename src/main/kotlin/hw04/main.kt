package hw04

import java.util.ArrayList
abstract class AbstractSet {
   abstract public fun insert (value : Int) : AbstractSet?
   abstract public fun delete (value : Int) : AbstractSet?
   abstract public fun search (value : Int) : Boolean
   abstract public fun toList () : List<Int?>
   abstract public fun union (set : AbstractSet?) : AbstractSet?
   abstract public fun intersection (set : AbstractSet?) : AbstractSet?
}

public class AVLtree(public var value : Int, public var left : AVLtree?, public
                     var right : AVLtree?): AbstractSet() {
   private fun AVLtree?.height(): Int {
      when (this) {
         null -> return 0
         else -> return Math.max(left.height(), right.height()) + 1
      }
   }

   private fun AVLtree?.findMin(): Int {
      if (this == null) { return 0 }
      val l = this.left
      if (l == null) {
         return this.value
      }
      return l.left?.findMin() ?: l.value
   }

   private fun AVLtree?.findMax(): Int {
      if (this == null) { return 0 }
      val r = this.right
      if (r == null) {
         return this.value
      }
      return r.right?.findMax() ?: r.value
   }

   private fun rightRotation(): AVLtree {
      val l = this.left
      val r = this.right
      if (l == null) {
         return this
      }
      if (r == null) {
         if (l.left != null) {
            return AVLtree(l.value, l.left, AVLtree(this.value, null, null))
         }
         val lr = l.right
         if (lr != null) {
            return AVLtree(lr.value, AVLtree (l.value, null, null),
                    AVLtree(this.value, null, null))
         }
         return this
      }
      val lr = l.right
      val ll = l.left
      when (lr) {
         null -> when (ll) {
            null -> return this
            else -> return AVLtree(this.value, l.rightRotation(), r)
         }
         else -> when (ll) {
            null -> return AVLtree(lr.value, AVLtree(l.value, null, lr.left),
                    AVLtree(this.value, lr.right, null))
            else -> {
               if (ll.height() >= lr.height()) {
                  return AVLtree(l.value, l.left, AVLtree(this.value, lr, r.right))
               }
               return AVLtree(lr.value, AVLtree(l.value, ll, lr.left),
                       AVLtree(this.value, lr.right, this.right))
            }
         }
      }
   }

   private fun leftRotation(): AVLtree {
      val l = this.left
      val r = this.right
      if (r == null) {
         return this
      }
      if (l == null) {
         if (r.right != null) {
            return AVLtree(r.value, AVLtree(this.value, null, null), r.right)
         }
         val rl = r.left
         if (rl != null) {
            return AVLtree(rl.value, AVLtree (this.value, null, null),
                    AVLtree(r.value, null, null))
         }
         return this
      }
      val rl = r.left
      val rr = r.right
      when (rl) {
         null -> when (rr) {
            null -> return this
            else -> return AVLtree(this.value, l, r.leftRotation())
         }
         else -> when (rr) {
            null -> return AVLtree(rl.value, AVLtree(this.value, null, rl.left),
                    AVLtree(r.value, rl.right, null))
            else -> {
               if (rr.height() >= rl.height()) {
                  return AVLtree(r.value, AVLtree(this.value, l.left, rl), r.right)
               }
               return AVLtree(rl.value, AVLtree(this.value, this.left, rl.left),
                       AVLtree(l.value, rl.left, rr))
            }
         }
      }
   }

   private fun AVLtree?.balance(): AVLtree? {
      if (this == null) {
         return this
      }
      val l = this.left
      val r = this.right
      if (l.height() - r.height() == 2) {
         if (l == null) {
            return l
         }
         val ll = l.left
         val lr = l.right
         if (ll.height() - lr.height() == 2 || ll.height() - lr.height() == -2) {
            return AVLtree (this.value, l.balance(), r)
         } else {
            return this.rightRotation ()
         }
      } else {
         if (r.height() - l.height() == 2) {
            if (r == null) {
               return r
            }
            val rr = r.right
            val rl = r.left
            if (rr.height() - rl.height() == 2 || rr.height() - rl.height() == -2) {
               return AVLtree (this.value, l, r.balance())
            } else {
               return this.leftRotation ()
            }
         } else {
            return AVLtree (this.value, l.balance(), r.balance())
         }
      }
   }

   private fun AVLtree?.insert_(value: Int?): AVLtree? {
      if (value == null) {
         return this
      }
      if (this == null) {
         return AVLtree (value, null, null)
      }
      when {
         value < this.value -> {
            return AVLtree (this.value, this.left.insert_ (value), this.right)
         }
         value > this.value -> {
            return AVLtree (this.value, this.left, this.right.insert_ (value))
         }
         else -> return AVLtree (value, null, null)
      }
   }

   override public fun insert(value: Int): AVLtree? {
      return (this.insert_(value)).balance()
   }

   private fun AVLtree?.delete_(value: Int): AVLtree? {
      if (this == null) {return this}
      val l = this.left
      val r = this.right
      if (this.value == value) {
         if (l != null && r != null) {
            if (l.height() > r.height()) {
               return AVLtree (l.findMax(), l.delete_ (l.findMax()), r)
            } else {
               return AVLtree (r.findMin(), l, r.delete_ (r.findMin()))
            }
         }
         if (l != null && r == null) {
            return AVLtree (l.value, null, null)
         }
         if (l == null && r != null) {
            return AVLtree (r.value, null, null)
         }
         if (l == null && r == null) {
            return null
         }
      }
      if (this.value > value) {
         return AVLtree (this.value, l.delete_ (value), r)
      }
      if (this.value < value) {
         return AVLtree (this.value, l, r.delete_ (value))
      }
      return this
   }

   override public fun delete(value: Int): AVLtree? {
      return (this.delete_ (value)).balance()
   }

   override public fun search(value: Int): Boolean {
      if (this.value == value) {
         return true
      }
      if (this.value > value) {
         return (this.left?.search (value) ?: false)
      } else {
         return (this.right?.search (value) ?: false)
      }
   }

   override public fun toList(): List<Int?> {
      val lList = this.left?.toList() ?: listOf()
      val v = listOf(this.value)
      val rList = this.right?.toList() ?: listOf()
      return lList + v + rList
   }

   private fun AVLtree?.union_ (set: AbstractSet?, acc: AbstractSet?)
           : AbstractSet?{
      if (this == null){return acc}
      if (set == null) {return this}
      var new = acc
      if (!set.search (this.value)) {
         new = new?.insert(this.value) ?: set
      }
      new = this.left.union_ (set, new)
      new = this.right.union_ (set, new)
      return new
   }
   override public fun union(set: AbstractSet?): AbstractSet? {
      return this.union_(set, set)
   }

   private fun AVLtree?.intersection_(t: AbstractSet?, acc: AVLtree?)
           : AVLtree? {
      if (t == null) {
         return null
      }
      if (this == null) {
         return acc
      }
      if (t.search (this.value)) {
         return this.right.intersection_ (t, this.left.intersection_ (t,
                 acc.insert_ (this.value)))
      }
      return this.right.intersection_ (t, this.left.intersection_ (t, acc))
   }

   override public fun intersection(set: AbstractSet?): AbstractSet? {
      val acc: AVLtree? = null
      return this.intersection_ (set, acc)
   }
}

public class HashTable (private var values : Array<ArrayList<Int>>) : AbstractSet(){
   private fun hash(v : Int): Int {
      return v mod values.size();
   }

   override public fun insert (value : Int) : HashTable? {
      val newHash = hash(value)
      var newArray = values
      newArray[newHash].add(newArray[newHash].size(), value)
      return HashTable(newArray)
   }

   override public fun delete (value : Int) : HashTable? {
      val delHash = hash(value)
      var newArray = values
      for (i in 0..newArray.size() - 1) {
         if (newArray[delHash][i] == value) {
            newArray[delHash].remove(i)
            return HashTable(newArray)
         }
      }
      return HashTable(newArray)
   }

   override public fun search (value : Int) : Boolean {
      val serHash = hash(value)
      for (i in 0..values[serHash].size() - 1) {
         if (values[serHash][i] == value) {return true}
      }
      return false
   }

   override public fun toList () : List<Int> {
      var list : List<Int> = values[0]
      for (i in 1..values.size() - 1) {
         list = list + values[i]
      }
      return list
   }

   override public fun union (set : AbstractSet?) : AbstractSet? {
      if (set == null) {
         return this
      }
      var newSet = set
      for (i in 0..values.size() - 1) {
         for (j in 0..this.values[i].size() - 1) {
            if (!set.search (this.values[i][j])) {
               newSet = newSet?.insert (this.values[i][j]) ?: this
            }
         }
      }
      return newSet
   }

   override public fun intersection (set : AbstractSet?) : AbstractSet?{
      if (set == null) {
         return this
      }
      var newSet = this
      for (i in 0..values.size() - 1) {
         for (j in 0..this.values[i].size() - 1) {
            if (!set.search (this.values[i][j])) {
               newSet.delete (this.values[i][j])
            }
         }
      }
      return newSet
   }
}
