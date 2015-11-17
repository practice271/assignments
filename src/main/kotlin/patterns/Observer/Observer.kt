package patterns.Observer

import java.util.ArrayList

 public class Subject {

    private var  observers: ArrayList<Observer> = ArrayList<Observer>()
    private var state: Int = 0

    public fun getState(): Int {
        return state
    }

    public fun setState(state: Int) {
        this.state = state
        notifyAllObservers()
    }

    public fun attach(observer: Observer) {
        observers.add(observer)
    }

    public fun notifyAllObservers() {
        for (observer in observers) {
            observer.update()
        }
    }
}

public abstract class Observer {
    public var subject: Subject = Subject()
    public abstract fun update()
}

public class BinaryObserver(): Observer() {

    public fun BinaryObserver(subject: Subject) {
    this.subject = subject
    this.subject.attach(this)
}

    override public fun update() {
        println("Binary String: " + Integer.toBinaryString(subject.getState()))
    }
}

public  class OctalObserver(): Observer(){

    public fun  OctalObserver(subject: Subject) {
        this.subject = subject
        this.subject.attach(this)
    }

    override public fun update() {
        println("Octal String: " + Integer.toOctalString(subject.getState()))
    }
}

public  class HexaObserver(): Observer() {
    public fun HexaObserver(subject: Subject) {
        this.subject = subject
        this.subject.attach(this)
    }

    override public fun update() {
        println("Hex String: " + Integer.toHexString(subject.getState()).toUpperCase())
    }
}

    public fun main(args: Array<String>) {

        var subject: Subject = Subject()
        var Bin: BinaryObserver = BinaryObserver()
        Bin.BinaryObserver(subject)
        var Oct: OctalObserver = OctalObserver()
        Oct.OctalObserver(subject)
        var Hex: HexaObserver = HexaObserver()
        Hex.HexaObserver(subject)


        println("First state change: 8")
        subject.setState(8)
        println("\n")
        println("Second state change: 19")
        subject.setState(19)
    }

