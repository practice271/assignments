package utilities

import java.util.*

public fun <A> ArrayListInit(elemNum : Int, foo : (Int) -> A) : ArrayList<A> {
    var res = arrayListOf<A>()
    for (i in 0.. elemNum - 1) {
        res.add(foo(i))
    }
    return res
}