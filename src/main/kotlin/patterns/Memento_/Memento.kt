package patterns.Memento_

public interface IOriginator {
    fun getMemento(): Memento
    fun setMemento(memento: Memento)
}

public  class Memento {
    private var _helth: Int = 0

    constructor(health: Int) {
        _helth = health
    }

    public fun getState(): Int {
        return _helth
    }
}

public class Player: IOriginator {
    private var _helth: Int = 0
    constructor() {
        _helth = 100
    }

    public fun getHurt( hurt: Int) {
        _helth -= hurt
    }

    public fun getCure(cure: Int) {
        _helth += cure
    }

    public fun printPulse() {
        when {
            (_helth > 70)                 ->   return println("Health status indicator: green")
            (_helth <= 70 && _helth > 40) ->   return println("Health status indicator: yellow")
            else                          ->   return println("Health status indicator: red")
        }
    }

    override  public fun setMemento(memento: Memento) {
        _helth = memento.getState()
    }

    override public fun getMemento(): Memento {
        var tmp = Memento(_helth)
        return tmp
    }
}

public class gameUtils {
    private var _memento = Memento(0)
    public fun saveState(originator: IOriginator) {
        if (originator == null) { return println("Originator is null") }

        _memento = originator.getMemento()
        println("Save state")
    }

    public fun loadState(originator: IOriginator) {
        if (originator == null) { return println("Originator is null") }
        if (_memento == null)   { return println("Memento is null") }

        originator.setMemento(_memento)
        println("Load state")
    }
}

public fun main(args: Array<String>) {
    var gameUtils = gameUtils()
    var player = Player()

    player.getHurt(20)
    player.getHurt(30)
    player.getHurt(20)
    player.printPulse()

    gameUtils.saveState(player)

    player.getCure(50)
    player.printPulse()

    gameUtils.loadState(player)

    player.printPulse()
}