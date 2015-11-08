package patterns.Memento

import java.util.ArrayList

class Memento(val state: String) {}
class Originator {
    public var state: String = ""

    fun saveStateToMemento(): Memento {
        println("Save ${state}")
        return Memento(state)
    }

    fun getStateFromMemento(Memento: Memento) {
        state = Memento.state
        println("Load ${state}")
    }
}

class CareTaker {
    private val mementoList = ArrayList<Memento>()

    public fun add(state: Memento) {
        mementoList.add(state)
    }

    public operator fun get(index: Int): Memento {
        return mementoList[index]
    }
}

fun main(args: Array<String>) {
        val originator = Originator()
        val careTaker = CareTaker()

        originator.state = "Red background"
        originator.state = "Blue background"
        careTaker.add(originator.saveStateToMemento())
        originator.state = "Purple background"
        println("Background now: " + originator.state)
        originator.getStateFromMemento(careTaker[0])
        println("Background now: " + originator.state)

}

