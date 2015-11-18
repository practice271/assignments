package patterns.prototype_ks

import java.util.ArrayList

public class Editor {
    private var currentElem : ILayoutElement = Triforce()
    private val panel = ArrayList<ILayoutElement>()

    fun getCurrentElemInfo() : String = currentElem.info()

    fun selectElem(name : String) {
        currentElem = when (name) {
            "text" -> TextElement("")
            "triforce" -> Triforce()
            "smile" -> Smile()
            else -> throw Exception("No such element!")
        }
    }

    fun setText(str : String) {
        if (currentElem is TextElement) {
            (currentElem as TextElement).setContent(str)
        }
    }

    fun addCurrentElem() {
        panel.add(currentElem.clone())
    }

    fun drawAll() {
        println("------------ PANEL ------------")
        for (elem in panel) {
            elem.draw()
        }
        println("------------/PANEL ------------")
    }
}