package patterns.State

interface State {
    fun on()
    fun off()
    fun print()
    fun addPaper(count: Int)
}

class PowerOffState(var printer: Printer) : State {

    public override fun on() {
        println("Принтер включен")
        printer.state = printer.waitingState
    }

    public override fun off() {
        println("Принтер и так выключен")
    }

    public override fun print() {
        println("Принтер отключен, печать невозможна")
    }

    public override fun addPaper(count: Int) {
        printer.addPaper(count)
        println("Бумага добавлена")
    }
}

class WaitingState(var printer: Printer) : State {

    public override fun on() {
        println("Принтер уже и так включен")
    }

    public override fun off() {
        println("Принтер выключен")
    }

    public override fun print() {
        if (printer.countPaper > 0) {
            println("Сейчас всё распечатаем")
            printer.addPaper(-1)
        } else {
            printer.state = printer.paperOffState
            printer.printDocument()
        }
    }

    public override fun addPaper(count: Int) {
        printer.addPaper(count)
        println("Бумага добавлена")
    }
}

class PaperOffState(var printer: Printer) : State {

    public override fun on() {
        println("Принтер уже и так включен")
    }

    public override fun off() {
        println("Принтер выключен")
        printer.state = printer.powerOffState
    }

    public override fun print() {
        if (printer.countPaper > 0) {
            printer.state = printer.printState
            printer.printDocument()
        } else {
            println("Бумаги нет, печатать не буду")
        }

    }

    public override fun addPaper(count: Int) {
        println("Добавляем бумагу")
        printer.addPaper(count)
        if (printer.countPaper > 0)
            printer.state = printer.waitingState
    }
}

class PrintState(var printer: Printer) : State {

    public override fun on() {
        println("Принтер уже и так включен")
    }

    public override fun off() {
        println("Принтер выключен")
    }

    public override fun print() {
        if (printer.countPaper > 0) {
            println("Идёт печать...")
            printer.addPaper(-1)
            printer.state = printer.waitingState
        } else {
            printer.state = printer.paperOffState
            printer.printDocument()
        }

    }

    public override fun addPaper(count: Int) {
        printer.addPaper(count)
        println("Бумага добавлена")
    }
}

class Printer {
    public var countPaper = 0; private set

    public val paperOffState = PaperOffState(this)
    public val powerOffState = PowerOffState(this)
    public val printState = PrintState(this)
    public val waitingState = WaitingState(this)

    public var state: State = waitingState

    public fun printDocument() = state.print()
    public fun powerOff() = state.off()
    public fun powerOn() = state.on()
    public fun addPaper(count: Int) {
        countPaper += count
    }

}

fun main(args: Array<String>) {
    var printer = Printer()
    printer.powerOn()
    printer.printDocument()
    printer.addPaper(3)
    printer.printDocument()
    printer.printDocument()
    printer.printDocument()
    printer.printDocument()
    printer.powerOff()
}
