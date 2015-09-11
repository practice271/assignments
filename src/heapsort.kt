fun <T> Array<T>.heapsort()
        where T: Comparable<T> {

    fun shiftDown(i: Int, j: Int) {
        var counter = i
        var done = false
        var maxChild: Int
        var temp: T
        while ((counter * 2 + 1 < j) && (!done)) {
            if (counter * 2 + 1 == j - 1)
                maxChild = counter * 2 + 1
            else if (this[counter * 2 + 1] > this[counter * 2 + 2])
                maxChild = counter * 2 + 1
            else
                maxChild = counter * 2 + 2

            if (this[counter] < this[maxChild]) {
                temp = this[counter]
                this[counter] = this[maxChild]
                this[maxChild] = temp
                counter = maxChild
            } else {
                done = true
            }
        }
    }

    var temp: T
    var i = this.size() / 2 - 1
    while (i >= 0) {
        shiftDown(i, this.size())
        i--
    }

    i = this.size() - 1
    while (i >= 1) {
        temp = this[0]
        this[0] = this[i]
        this[i] = temp
        shiftDown(0, i)
        i--
    }
}