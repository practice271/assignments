package hw03

class AVLtree (
        var key : Int,
        var left : AVLtree?,
        var right : AVLtree?
)

fun AVLtree?.height () : Int {
   when (this) {
      null -> return 0
      else -> return Math.max(left.height(), right.height()) + 1
   }
}

fun toText(tree : AVLtree?) : String {
   fun toText_(tree : AVLtree?) : List<String> {
      if (tree == null) { return listOf("_\n")}
      val lText = toText_(tree.left).map { "| $it"}
      val rText = toText_(tree.right).map { "| $it"}
      val a = tree.key
      val vText = listOf("$a \n")
      return rText + vText + lText
   }
   val builder = StringBuilder()
   val lines = toText_(tree)
   lines.forEach { builder.append(it) }
   return builder.toString()
}

fun insertion (tree : AVLtree?, newKey : Int) : AVLtree? {
   if (tree == null) {
      return AVLtree(newKey, null, null)
   }
   when {
      newKey < tree.key -> {
         return AVLtree (tree.key, insertion (tree.left, newKey), tree.right)
      }
      newKey > tree.key -> {
         return AVLtree (tree.key, tree.left, insertion (tree.right, newKey))
      }
      else -> return AVLtree (newKey, null, null)
   }
}

fun rightRotation (tree : AVLtree) : AVLtree {
   val l = tree.left
   val r = tree.right
   if (l == null) { return tree}
   if (r == null) {
      if (l.left != null) {
         return AVLtree(l.key, l.left, AVLtree(tree.key, null, null))
      }
      val lr = l.right
      if (lr != null) {
         return AVLtree(lr.key, AVLtree (l.key, null, null), AVLtree(tree.key, null, null))
      }
      return tree
   }
   val lr = l.right
   val ll = l.left
   when (lr) {
      null -> when (ll) {
         null -> return tree
         else -> return AVLtree(tree.key, rightRotation (l), r)
      }
      else -> when (ll) {
         null -> return AVLtree(lr.key, AVLtree(l.key, null, lr.left), AVLtree(tree.key, lr.right, null))
         else -> {
            if (ll.height() >= lr.height()) {
               return AVLtree(l.key, l.left, AVLtree(tree.key, lr, r.right))
            }
            return AVLtree(lr.key, AVLtree(l.key, ll, lr.left), AVLtree(tree.key, lr.right, tree.right))
         }
      }
   }
}

fun leftRotation (tree : AVLtree) : AVLtree {
   val l = tree.left
   val r = tree.right
   if (r == null) { return tree}
   if (l == null) {
      if (r.right != null) {
         return AVLtree(r.key, AVLtree(tree.key, null, null), r.right)
      }
      val rl = r.left
      if (rl != null) {
         return AVLtree(rl.key, AVLtree (tree.key, null, null), AVLtree(r.key, null, null))
      }
      return tree
   }
   val rl = r.left
   val rr = r.right
   when (rl) {
      null -> when (rr) {
         null -> return tree
         else -> return AVLtree(tree.key, l, leftRotation (r))
      }
      else -> when (rr) {
         null -> return AVLtree(rl.key, AVLtree(tree.key, null, rl.left), AVLtree(r.key, rl.right, null))
         else -> {
            if (rr.height() >= rl.height()) {
               return AVLtree(r.key, AVLtree(tree.key,l.left, rl), r.right)
            }
            return AVLtree(rl.key, AVLtree(tree.key, tree.left, rl.left), AVLtree(l.key, rl.left, rr))
         }
      }
   }
}

fun balance (tree : AVLtree?) : AVLtree? {
   if (tree == null) { return tree }
   val l = tree.left
   val r = tree.right
   if (l.height() - r.height() == 2) {
      if (l == null) { return l }
      val ll = l.left
      val lr = l.right
      if (ll.height() - lr.height() == 2 || ll.height() - lr.height() == -2) {
         return AVLtree (tree.key, balance(l), r)
      } else {
         return rightRotation (tree)
      }
   }
   else {
      if (r.height() - l.height() == 2) {
         if (r == null) { return r }
         val rr = r.right
         val rl = r.left
         if (rr.height() - rl.height() == 2 || rr.height() - rl.height() == -2) {
            return AVLtree (tree.key, l, balance(r))
         } else {
            return leftRotation (tree)
         }
      }
      else {
         return AVLtree (tree.key, balance(l), balance(r))
      }
   }
}

fun findMin (tree : AVLtree?) : Int {
   if (tree != null ) {
      val l = tree.left
      if (l == null) { return tree.key}
      val ll = l.left
      if (ll == null) { return l.key }
      findMin(ll)
   }
   return 0
}

fun findMax (tree : AVLtree?) : Int {
   if (tree != null ) {
      val r = tree.right
      if (r == null) { return tree.key }
      val rr = r.right
      if (rr == null) { return r.key }
      findMax(rr)
   }
   return 0
}

fun deletion (tree : AVLtree?, delKey : Int) : AVLtree? {
   if (tree == null) { return tree}
   val l = tree.left
   val r = tree.right
   if (tree.key == delKey) {
      if (l != null && r != null) {
         if (l.height() > r.height()) {return AVLtree (findMax(l), deletion (l, findMax(l)), r)}
         else {return AVLtree (findMin(r), l, deletion (r, findMin(r)))}
      }
      if (l != null && r == null) {return AVLtree (l.key, null, null)}
      if (l == null && r != null) {return AVLtree (r.key, null, null)}
      if (l == null && r == null) {return null}
   }
   if (tree.key > delKey) {return AVLtree (tree.key, deletion (l, delKey), r)}
   if (tree.key < delKey) {return AVLtree (tree.key, l, deletion (r, delKey))}
   return tree
}

fun search (tree : AVLtree?, newKey : Int) : Boolean {
   if (tree != null) {
      if (tree.key == newKey) { return true }
      if (tree.key > newKey) { return (search (tree.left, newKey))}
      else { return (search (tree.right, newKey))}
   }
   else {return false}
}

/*fun main(args: Array<String>){
   val b = AVLtree (10, AVLtree(3, AVLtree(2, null,null), AVLtree(7,AVLtree(5,null,null),null)), AVLtree(12, AVLtree(11,null,null), AVLtree(13,null,null)))
   var bi = insertion (b,6)
   var bb = balance (bi)
   var bd = deletion (b, 10)
   print (toText(bb))
}*/