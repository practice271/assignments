package patterns.prototype_ks

fun main(args : Array<String>) {
    val editor = Editor()
    println(editor.getCurrentElemInfo())

    editor.addCurrentElem()

    editor.selectElem("text")
    editor.setText("TRIFORCE POWER")
    editor.addCurrentElem()

    editor.setText("Glory to Kotlin")
    editor.addCurrentElem()

    editor.selectElem("smile")
    editor.addCurrentElem()
    editor.drawAll()
}