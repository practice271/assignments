package hw09

public object Converter {
    public fun notOptimalConverte(input: String): String {
        var res = ""
        for (i in input) {
            val factor = i.toInt()
            res += "+".repeat(factor) + ".[-]"
        }
        return res
    }

    public fun optimalConverte(input: String): String {
        var res = ""
        for (i in input) {
            val ascii = i.toInt()
            val factor = ascii / 10
            val remaining = ascii % 10
            res += "+".repeat(10) + "[>" + "+".repeat(factor) +
                    "<-]>" + "+".repeat(remaining) + ".[-]"
        }
        return res
    }
}
//
//fun main(args: Array<String>) {
//    val o = "Helloo"
//    print(Converter.optimalConverte("Hello, world!"))
//}
