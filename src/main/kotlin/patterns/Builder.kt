package patterns
/*
// Abstract Product class
*/
class Computer(var CP:String="", var OS:String="" , var RAM:String = "", var GPU:String = "" ) {
}
//Abstract product Builder
abstract class ComputerBuilder(){
    protected var computer:Computer = Computer()

    public fun build(){
        buildCP()
        buildGPU()
        buildOS()
        buildRAM()
    }
    //Build parts
    protected abstract fun buildCP()
    protected abstract fun buildOS()
    protected abstract fun buildRAM()
    protected abstract fun buildGPU()

    public fun getProduct():Computer { return computer }
}
//Concrete Builder1
class TopGamePCBuilder():ComputerBuilder(){
    override protected fun buildCP() {
        computer.CP = "Intel Core i7-6700K"
    }

    override protected fun buildOS() {
        computer.OS = "Microsoft Windows 7"
    }

    override protected fun buildRAM() {
        computer.RAM = "DDR4-4x"
    }

    override protected fun buildGPU() {
        computer.GPU = "GeForce930"
    }
}
//Concrete Builder 2
class HomePC():ComputerBuilder(){
    override protected  fun buildCP() {
        computer.CP = "Intel Core i3"
    }

    override protected fun buildOS() {
        computer.OS = "Linux Debian"
    }

    override protected fun buildRAM() {
        computer.RAM = "DDR2-1024Mb"
    }

    override protected fun buildGPU() {
        computer.GPU = "Radeon-HD5650"
    }
}
//Creating object using abstract interface Builder
class Director(val builder:ComputerBuilder){
    public fun getComputer():Computer{ return builder.getProduct() }
    public fun constuctComputer(){
        builder.build()
    }

}

fun main(argv:Array<String>){
    //Simple example 1
    var topGamingComplucter = TopGamePCBuilder()
    val director1 =  Director(topGamingComplucter)
    director1.constuctComputer()
    var result1 = director1.getComputer()
    println(result1.CP + " " + result1.OS + " "
            + result1.GPU + " " + result1.RAM)
    //Example 2
    var SimpleHome = HomePC()
    val director2 =  Director(SimpleHome)
    director2.constuctComputer()
    var result2 = director2.getComputer()
    println(result2.CP + " " + result2.OS + " "
            + result2.GPU + " " + result2.RAM)
}
