package patterns.strategy


public interface IStrategy {
    public fun doOperation(n1: Int, n2: Int): Int
}

public class OperationAdd(): IStrategy {
    override public fun doOperation(n1: Int, n2: Int) = n1 + n2
}
public class OperationSub(): IStrategy {
    override public fun doOperation(n1: Int, n2: Int) = n1 - n2
}
public class OperationMult(): IStrategy {
    override public fun doOperation(n1: Int, n2: Int) = n1 * n2
}

public class Context(private val strategy: IStrategy) {
    public fun executeStrat(n1: Int, n2: Int): Int =
        strategy.doOperation(n1, n2)
}


fun main(args: Array<String>) {
    var context = Context(OperationAdd())
    println("15 + 10 = ${context.executeStrat(15, 10)}")

    context = Context(OperationSub())
    println("15 - 10 = ${context.executeStrat(15, 10)}")

    context = Context(OperationMult())
    println("15 * 10 = ${context.executeStrat(15, 10)}")
}