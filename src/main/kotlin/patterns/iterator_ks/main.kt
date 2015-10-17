package patterns.iterator_ks

import java.util.ArrayList

fun main(args: Array<String>) {
    val boxes = ArrayList<IBox<Int>>()
    // creating first box (list)
    val box1 : IBox<Int> = ListBox<Int>()
    box1.add(1)
    box1.add(4)
    box1.add(8)
    box1.add(7)
    box1.add(4)
    box1.add(1)
    boxes.add(box1)
    // creating second box (set)
    val box2 : IBox<Int> = SetBox<Int>()
    box2.add(1)
    box2.add(4)
    box2.add(8)
    box2.add(7)
    box2.add(4)
    box2.add(1)
    boxes.add(box2)
    // testing box iterators
    for (box in boxes) { // unified access
        print("Box contents:")
        for (item in box) {
            print(" $item")
        }
        println()
    }
}