package hw03

open class AVLtree (
        var key : Int,
        var heigh : Int,
        var left : AVLtree?,
        var right : AVLtree?
)

fun max (a : Int, b : Int) : Int {
   if (a > b) { return a }
   else {return b}
}

fun toText(tree : AVLtree?) : String {
   fun toText_(tree : AVLtree?) : List<String> {
      if (tree == null) { return listOf("_\n")}
      else {
         val lText = toText_(tree.left).map { "| $it"}
         val rText = toText_(tree.right).map { "| $it"}
         val a = tree.key
         val vText = listOf("$a \n")
         return rText + vText + lText
      }
   }
   val builder = StringBuilder()
   val lines = toText_(tree)
   lines.forEach { builder.append(it) }
   return builder.toString()
}

fun insertion (tree : AVLtree?, newKey : Int) : AVLtree? {
   var acc : AVLtree?
   if (tree != null) {
      var h = 0
      if (newKey < tree.key) {
         val l = tree.left
         if (l != null) { h = l.heigh }
         if (tree.heigh - h == 2) {h = tree.heigh}
         else {h = tree.heigh + 1}
         acc = AVLtree (tree.key, h, insertion (tree.left, newKey), tree.right)
         return acc
      } else {
         if (newKey > tree.key) {
            val r = tree.right
            if (r != null) { h = r.heigh }
            if (tree.heigh - h == 2) {h = tree.heigh}
            else {h = tree.heigh + 1}
            acc = AVLtree (tree.key, h, tree.left, insertion (tree.right, newKey))
            return acc
         } else {
            throw Exception("This key already exists")
         }
      }
   }
   else {
      acc = AVLtree (newKey, 1, null, null)
      return acc
   }
}

fun rightRotation (tree : AVLtree) : AVLtree {
   var new = AVLtree (0, 0, null, null)
   val l = tree.left
   val r = tree.right
   if (l != null) {
      if (r != null) {
         val lr = l.right
         val ll = l.left
         if (lr != null && ll != null) {
            if (ll.heigh >= lr.heigh) {
               new = AVLtree(l.key, lr.heigh + 2,
                       l.left, AVLtree(tree.key, lr.heigh + 1, lr, r.right))
            }
            else {
               new = AVLtree(lr.key, tree.heigh - 1, AVLtree(l.key, l.heigh - 1, ll, lr.left),
                       AVLtree(tree.key, r.heigh + 1, lr.right, tree.right))
            }
         }
         else {
            if (lr == null && ll != null) {
               new = AVLtree(tree.key, 3, rightRotation (l), r)
            }
            if (lr != null && ll == null) {
               if (lr.left != null) {
                  new = AVLtree(lr.key, 3,
                          AVLtree(l.key, 2, null, lr.left), AVLtree(tree.key, 1, lr.right, null))
               } else {
                  new = AVLtree(lr.key, 3,
                          AVLtree(l.key, 1, null, lr.left), AVLtree(tree.key, 2, lr.right, null))
               }
            }
         }
      }
      else {
         if (l.left != null) {
            new = AVLtree(l.key, 2, l.left, AVLtree(tree.key, 1, null, null))
         }
         val lr = l.right
         if (lr != null) {
            new = AVLtree(lr.key, 2,
                    AVLtree (l.key, 1, null, null), AVLtree(tree.key, 1, null, null))
         }
      }
   }
   return new
}

fun leftRotation (tree : AVLtree) : AVLtree {
   var new = AVLtree (0, 0, null, null)
   val l = tree.left
   val r = tree.right
   if (r != null) {
      if (l != null) {
         val rl = r.left
         val rr = r.right
         if (rl != null && rr != null) {
            if (rr.heigh >= rl.heigh) {
               new = AVLtree(r.key, rl.heigh + 2, AVLtree(tree.key, rl.heigh + 1,l.left, rl), r.right)
            }
            else {
               new = AVLtree(rl.key, tree.heigh - 1, AVLtree(tree.key, l.heigh + 1, tree.left, rl.left), AVLtree(l.key, l.heigh - 1, rl.left, rr))
            }
         }
         else {
            if (rl == null && rr != null) {
               new = AVLtree(tree.key, 3, l, leftRotation (r))
            }
            if (rr == null && rl != null ) {
               if (rl.right != null) {
                  new = AVLtree(rl.key, 3, AVLtree(tree.key, 1, null, rl.left), AVLtree(r.key, 2, rl.right, null))
               } else {
                  new = AVLtree(rl.key, 3, AVLtree(tree.key, 2, null, rl.left), AVLtree(r.key, 1, rl.right, null))
               }
            }

         }
      }
      else {
         if (r.right != null) {
            new = AVLtree(r.key, 2, AVLtree(tree.key, 1, null, null), r.right)
         }
         val rl = r.left
         if (rl != null) {
            new = AVLtree(rl.key, 2, AVLtree (tree.key, 1, null, null), AVLtree(r.key, 1, null, null))
         }
      }
   }
   return new
}

fun balance (tree : AVLtree?) : AVLtree? {
   var acc = tree
   if (tree!= null) {
      val l = tree.left
      val r = tree.right
      var lHeigh = 0
      var rHeigh = 0
      if (l != null) {lHeigh = l.heigh}
      if (r != null) {rHeigh = r.heigh}
      if (lHeigh - rHeigh == 2) {
         if (l != null) {
            val ll = l.left
            val lr = l.right
            var llHeigh = 0
            var lrHeigh = 0
            if (ll != null) { llHeigh = ll.heigh }
            if (lr != null) { lrHeigh = lr.heigh }
            if (llHeigh - lrHeigh == 2 || llHeigh - lrHeigh == -2) {
               acc = AVLtree (tree.key, tree.heigh, balance(l), r)
            } else {
               acc = rightRotation (tree)
            }
         }
      }
      else {
         if (rHeigh - lHeigh == 2) {
            if (r != null) {
               val rr = r.right
               val rl = r.left
               var rrHeigh = 0
               var rlHeigh = 0
               if (rr != null) { rrHeigh = rr.heigh }
               if (rl != null) { rlHeigh = rl.heigh }
               if (rrHeigh - rlHeigh == 2 || rrHeigh - rlHeigh == -2) {
                  acc = AVLtree (tree.key, tree.heigh, l, balance(r))
               } else {
                  acc = leftRotation (tree)
               }
            }
         }
         else {
            acc = AVLtree (tree.key, tree.heigh, balance(l), balance(r))
         }
      }
   }
   return acc
}

fun findMin (tree : AVLtree?) : Int {
   if (tree != null ) {
      val l = tree.left
      if (l != null) {
         val ll = l.left
         if (ll != null) {
            findMin(ll)
         }
         else return l.key
      }
      else {return tree.key}
   }
   return 0
}

fun findMax (tree : AVLtree?) : Int {
   if (tree != null ) {
      val r = tree.right
      if (r != null) {
         val rr = r.right
         if (rr != null) {
            findMax(rr)
         }
         else return r.key
      }
      else {return tree.key}
   }
   return 0
}

fun deletion (tree : AVLtree?, delKey : Int) : AVLtree? {
   var acc = tree
   if (tree != null) {
      val l = tree.left
      val r = tree.right
      if (tree.key == delKey) {
         if (l != null && r != null) {
            if (l.heigh > r.heigh) {acc = AVLtree (findMax(l), l.heigh, deletion (l, findMax(l)), r)}
            else {acc = AVLtree (findMin(r), l.heigh + 1, l, deletion (r, findMin(r)))}
         }
         if (l != null && r == null) {acc = AVLtree (l.key, 1, null, null)}
         if (l == null && r != null) {acc = AVLtree (r.key, 1, null, null)}
         if (l == null && r == null) {acc = null}
      }
      if (tree.key > delKey) {acc = AVLtree (tree.key, tree.heigh, deletion (l, delKey), r)}
      if (tree.key < delKey) {acc = AVLtree (tree.key, tree.heigh, l, deletion (r, delKey))}
   }
   else {throw Exception ("No such key")}
   return acc
}

fun search (tree : AVLtree?, newKey : Int) : Boolean {
   if (tree != null) {
      if (tree.key == newKey) { return true }
      if (tree.key > newKey) { return (search (tree.left, newKey))}
      else { return (search (tree.right, newKey))}
   }
   else {return false}
}

fun correctHeigh (tree : AVLtree?) : AVLtree? {
   var acc = tree
   if (acc != null) {
      val l = acc.left
      val r = acc.right
      var lHeigh = 0
      var rHeigh = 0
      if (l != null) {lHeigh = l.heigh}
      if (r != null) {rHeigh = r.heigh}
      if (acc.heigh - lHeigh > 1 && acc.heigh - rHeigh > 1 ||
              (acc.heigh - lHeigh > 2) ||
              (acc.heigh - rHeigh > 2)) {
         acc = AVLtree(acc.key, max (lHeigh, rHeigh) + 1, correctHeigh(l), correctHeigh(r))
      }
      else {
         acc = AVLtree(acc.key, acc.heigh, correctHeigh(l), correctHeigh(r))
      }
   }
   return acc
}

fun needToCorrect (tree : AVLtree?) : Boolean {
   if (tree != null) {
      val l = tree.left
      val r = tree.right
      var lHeigh = 0
      var rHeigh = 0
      if (l != null) {lHeigh = l.heigh}
      if (r != null) {rHeigh = r.heigh}
      if ((tree.heigh - lHeigh > 1 && tree.heigh - rHeigh > 1) ||
              (tree.heigh - lHeigh > 2) ||
              (tree.heigh - rHeigh > 2)) {return true}
      else {return needToCorrect(l) && needToCorrect(r)}
   }
   return false
}

fun correction (tree : AVLtree?) : AVLtree? {
   var acc = tree
   if (needToCorrect(tree)) {acc = correction (correctHeigh (tree))}
   return acc
}
/* fun main(args: Array<String>){
   val b = AVLtree (10, 4, AVLtree(3, 3, AVLtree(2, 1, null,null), AVLtree(7,2,AVLtree(5,1,null,null),null)), AVLtree(12, 2, AVLtree(11,1,null,null), AVLtree(13,1,null,null)))
   var bi = insertion (x,6)
   var bb = balance (bi)
   var bc = correction(bb)
   var bd = deletion (b, 10)
   print (toText(bc))
}*/
