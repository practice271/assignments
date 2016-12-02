package hw04


public fun <A> List<Pair<Int, A>>.hasSecondValueInPair(value : A) : Boolean {
    for (i in this) {
        if (i == value) return true
    }
    return false
}

public fun <A> List<Pair<Int, A>>.findByKeyInPair(key : Int) : Pair<Int, A>? {
    for (i in this) {
        if (i.first == key) return i
    }
    return null
}

public fun Int.toPair() : Pair<Int, Int> {
    return Pair(this, this)
}

public fun <A> MutableList<Pair<Int, A>>.addIfNotAlready(value : Pair<Int, A>) {
    for (i in this)
        if (i == value) return

    add(value)
}
public fun <A> MutableList<Pair<Int, A>>.delByKeyIfExists(key : Int) {
    for (i in this)
        if (i.first == key) {
            this.remove(i)
            return
        }
}