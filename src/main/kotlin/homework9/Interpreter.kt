package homework9

public class BFInterpeter() {
    val memory: Array<Int> = Array(30000, { 0 })
    var indicator = 0
    var brackets = 0
    var output = ""
    public fun interperet(input: String): String {
        var i = 0
        while (i < input.length) {
            when (input[i]) {
                '+' -> memory[indicator]++
                '-' -> memory[indicator]--
                '>' -> indicator++
                '<' -> indicator--
                '.' -> output += memory[indicator].toChar()
                ',' -> {
                    var ch = readLine()
                    if (ch != null) {
                        memory[indicator] = ch[0].toInt()
                    }
                }
                '[' -> {
                    if (memory[indicator] == 0) {
                        brackets++
                        while (brackets > 0){
                            i++
                            if (input[i] == '[') brackets++
                            if (input[i] == ']') brackets--
                        }
                    }
                }
                ']' -> {
                    if (memory[indicator] != 0) {
                        brackets++
                        while (brackets > 0) {
                            i--
                            if (input[i] == '[') brackets--
                            if (input[i] == ']') brackets++
                        }
                        i--
                    }
                }
                else -> return "Incorrect character"
            }
            i++
        }
        return output
    }

}

/*
fun main(args: Array<String>) {
    var input: String? = readLine()
    while (input != "exit") {
        var c = BFInterpeter()
        if (input != null) {
            println(c.interperet(input))
        }
        input = readLine()
    }
}
*/

