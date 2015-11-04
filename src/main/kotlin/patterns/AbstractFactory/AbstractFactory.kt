package AbstractFactory

/**
 * Simple example of abstract factory
 * Antropov Igor 20.09.2015
 */


abstract class langFactory{
    abstract fun sayFirstPhrase()
    abstract fun sayFirstSecond()
    abstract fun sayFirstThird()
    abstract fun sayFirstFourth()
}

open class italianFactory(): langFactory() {
    override fun sayFirstPhrase(){println("Ciao")}
    override fun sayFirstSecond(){println("Io Abstract Factory")}
    override fun sayFirstThird (){println("e il resto del programma ")}
    override fun sayFirstFourth(){println(" non sa che ho scritto")}
}

open class englishFactory(): langFactory() {
    override fun sayFirstPhrase(){println("Hi")}
    override fun sayFirstSecond(){println("i'm abstract factory")}
    override fun sayFirstThird (){println("and rest part of program")}
    override fun sayFirstFourth(){println("don't know what i'm writing")}
}

open class germanFactory(): langFactory() {
    override fun sayFirstPhrase(){println("Hallo")}
    override fun sayFirstSecond(){println("Ich abstrakte Fabrik")}
    override fun sayFirstThird (){println(" und der Rest des Programms nicht wei?")}
    override fun sayFirstFourth(){println("dass ich schreibe")}
}

open class frenchFactory(): langFactory() {
    override fun sayFirstPhrase(){println("salut")}
    override fun sayFirstSecond(){println("Je abstract factory")}
    override fun sayFirstThird (){println("et le reste du programme")}
    override fun sayFirstFourth(){println("ne sais pas ce que je vais ?crire ici")}
}

fun sayAllPhrases(factory : langFactory){
    factory.sayFirstPhrase()
    factory.sayFirstSecond()
    factory.sayFirstThird()
    factory.sayFirstFourth()
}

fun main(args: Array<String>) {
    println("Choose your language")
    println("italian - 1, german - 2, french - 3,  english as default")
    var a : Int = readLine()!!.toInt()
    when (a){
        1    -> sayAllPhrases(italianFactory())
        2    -> sayAllPhrases(germanFactory())
        3    -> sayAllPhrases(frenchFactory ())
        else -> sayAllPhrases(englishFactory())
    }
}