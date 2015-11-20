package patterns.prototype_ks

/**
 * Interface of a layout element.
 */
interface ILayoutElement {
    public fun draw()

    public fun info() : String

    public fun clone() : ILayoutElement
}

/**
 * Text layout element.
 */
class TextElement(private var content : String) : ILayoutElement {
    fun setContent(str : String) {
        content = str;
    }

    override fun draw() {
        println("[ $content ]")
    }

    override fun info() = "text"

    override fun clone() : ILayoutElement = TextElement(content)
}

/**
 * Abstract graphical layout element.
 */
abstract class GraphicElement() : ILayoutElement {
    abstract fun getHeight() : Int
}

/**
 * A Triforce.
 */
class Triforce() : GraphicElement() {
    private val height = 3

    override fun getHeight() = height

    override fun draw() {
        println("  ▲\n ▲▲")
    }

    override fun info() = "triforce"

    override fun clone() : ILayoutElement = Triforce()
}

/**
 * A Smile.
 */
class Smile() : GraphicElement() {
    private val height = 1

    override fun getHeight() = height

    override fun draw() {
        println(" =)")
    }

    override fun info() = "smile"

    override fun clone() : ILayoutElement = Smile()
}