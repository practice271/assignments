package patterns.Strategy

public interface IHasher {
    public fun computeHash(password : String) : String
}

public class Context() {
    private var strategy : IHasher = MD5()
    public fun setStrategy(str : IHasher){
        strategy = str
    }
    public fun executeStrategy(input : String) : String {
        return strategy.computeHash(input);
    }
}

fun main(args: Array<String>) {
    var context = Context()
    println("MD5     is ${context.executeStrategy("Web Biscuit")}")
    context.setStrategy(SHA256())
    println("SHA-256 is ${context.executeStrategy("Web Biscuit")}")
}
