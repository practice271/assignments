package hw01

fun Array<Int>.heapsort() {
    var n = this.size()
    var par = n/2; var ind: Int; var chd: Int; var t: Int
    if(n == 0)
        return
    while(true) {
        if(par > 0) {
            par--
            t = this[par]
        }
        else {
            n--
            if(n == 0)
                return
            t = this[n]
            this[n] = this[0]
        }
        ind = par
        chd = ind * 2 + 1
        while(chd < n) {
            if(chd + 1 < n && this[chd + 1] > this[chd])
                chd++
            if(this[chd] > t) {
                this[ind] = this[chd]
                ind = chd
                chd = ind * 2 + 1
            }
            else
                break
        }
        this[ind] = t
    }
}

fun main(args: Array<String>) {

}

