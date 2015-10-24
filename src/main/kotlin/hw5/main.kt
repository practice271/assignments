package hw5

fun main(args: Array<String>) {
    var i = Counter(0)
    i.runThreads(10000)
    var x = arrayOf(5, 7, 2, 1, 4, 3)
    x.mergesort()
    println(i.current)
    println(x.fold("") { s, v -> s + v.toString() + ' '})
}