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

open class russianFactory(): langFactory() {
    override fun sayFirstPhrase(){println("привет")}
    override fun sayFirstSecond(){println("я абстрактная фабрика")}
    override fun sayFirstThird (){println("и остальная часть программы")}
    override fun sayFirstFourth(){println("не знает, что я тут пишу")}
}

open class englishFactory(): langFactory() {
    override fun sayFirstPhrase(){println("Hi")}
    override fun sayFirstSecond(){println("i'm abstract factory")}
    override fun sayFirstThird (){println("and rest part of program")}
    override fun sayFirstFourth(){println("don't know what i'm writing")}
}

open class chineseFactory(): langFactory() {
    override fun sayFirstPhrase(){println("??")}
    override fun sayFirstSecond(){println("??????")}
    override fun sayFirstThird (){println("?????????")}
    override fun sayFirstFourth(){println("?????????")}
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
    println("russian - 1, chinese - 2, french - 3,  english as default")
    var a : Int = readLine()!!.toInt()
    when (a){
        1    -> sayAllPhrases(russianFactory())
        2    -> sayAllPhrases(chineseFactory())
        3    -> sayAllPhrases(frenchFactory ())
        else -> sayAllPhrases(englishFactory())
    }
}