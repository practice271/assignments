package homework.hw02

fun <T> Array<T>.heapsort()
        where T: Comparable<T> {

    fun shiftDown(i: Int, j: Int) {
        var done = false
        var counter = i

        while ((counter * 2 + 1 < j) && (!done)) {
            var child = counter * 2 + 1

            if ((child + 1 != j) && (this[child] <= this[child + 1]))
                child++

            if (this[counter] < this[child]) {
                var temp = this[counter]
                this[counter] = this[child]
                this[child] = temp
                counter = child
            } else
                done = true
        }
    }

    for (i in this.size() / 2 downTo 0) {
        shiftDown(i, this.size())
    }

    for (i in this.size() - 1 downTo 1) {
        var temp = this[0]
        this[0] = this[i]
        this[i] = temp
        shiftDown(0, i)
    }
}


