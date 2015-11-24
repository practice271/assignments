// Example for pattern "Bridge"
// Alekseev Aleksei, group 271

package patterns.bridge

import java.util.*

/* Abstraction */
abstract class Image(internal var imageImp: ImageImp) {
    init {
        this.imageImp = imageImp
    }
    fun getFormat() {
        imageImp.format()
    }
}

/* Implementor */
class Photo(imageImp: ImageImp) : Image(imageImp)

interface ImageImp {
    fun format()
}

/* ConcreteImplementor */
class Png : ImageImp {
    public override fun format() {
        println("Image format: Png")
    }
}

/* ConcreteImplementor */
class Jpg : ImageImp {
    public override fun format() {
        println("Image format: Jpg")
    }
}

/* Client */
fun main(args : Array<String>) {
    val album = ArrayList<Photo>()
    album.add(Photo(Png()))
    album.add(Photo(Jpg()))
    for (photo in album) {photo.getFormat()}
}
