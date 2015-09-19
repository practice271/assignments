// Визуализатор работы задания о компьютерной сети
// Автор: Кирилл Смиренко, группа 271
package homework.hw02

import java.util.Random

fun Network.drawNet() {
    //   W ------- W     L         L!-- W
    //   |         |     | \       |    |
    //   |         |     |  \      |    |
    //   W!------- A --- L -- L    W -- L
    //   |         |
    //   |         |
    //   A -- A -- M     M!-- M
    println("   ${ci(1)}------- ${ci(2)}    ${ci(4)}        ${ci(13)}-- ${ci(14)}")
    println("   |         |     | \\       |    |")
    println("   |         |     |  \\      |    |")
    println("   ${ci(0)}------- ${ci(6)}--- ${ci(3)}-- ${ci(5)}   ${ci(12)}-- ${ci(15)}")
    println("   |         |")
    println("   |         |")
    println("   ${ci(7)}-- ${ci(8)}-- ${ci(9)}    ${ci(10)}-- ${ci(11)}")
}

fun loop(net : Network, cnt : Int) {
    for (i in 1..cnt) net.tick()
    net.drawNet()
    when (readLine()) {
        "f" -> loop(net, 1)
        "g" -> loop(net, 5)
        "h" -> loop(net, 10)
        else -> return
    }
}

fun main(args: Array<String>) {
    val net = Network(
        arrayOf("Windows", "Windows", "Windows", "Linux", "Linux", "Linux",
            "Android", "Android", "Android", "Mac OS", "Mac OS", "Mac OS",
            "Windows", "Linux", "Windows", "Linux"),
        listOf(Pair(0, 1), Pair(0, 6), Pair(0, 7), Pair(1, 2), Pair(2, 6), Pair(3, 4), Pair(4, 5),
            Pair(3, 5), Pair(3, 6), Pair(6, 9), Pair(7, 8), Pair(8, 9), Pair(10, 11),
            Pair(12, 13), Pair(12, 15), Pair(13, 14), Pair(14, 15)),
        listOf(0, 10, 13),
        Random()
    )
    println("f = one tick, g = 5 ticks, h = 10 ticks, else = exit")
    loop(net, 0)
}