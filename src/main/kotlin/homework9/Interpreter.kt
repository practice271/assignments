package homework9

public class BFInterpreter() {
    val memory = Array(30000, { 0 })
    var indicator = 0
    var brackets = 0
    var output = ""
    public fun interpret(input: String, ch: String): String {
        var i = 0
        var count = 0
        while (i < input.length) {
            when (input[i]) {
                '+' -> memory[indicator]++
                '-' -> memory[indicator]--
                '>' -> if (indicator + 1 == 30000) indicator = 0 else indicator++
                '<' -> if (indicator - 1 == -1) indicator = 29999 else indicator--
                '.' -> output += memory[indicator].toChar()
                ',' -> {
                    if (ch != "") {
                        memory[indicator] = ch[count].toInt()
                        count++
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
                else -> return "Invalid character"
            }
            i++
        }
        if (brackets != 0) return "Incorrectly entered brackets"
        return output
    }

}

/*
fun main(args: Array<String>) {
    var input: String? = readLine()
    while (input != "exit") {
        var c = BFInterpreter()
        if (input != null) {
            println(c.interpret(input,"Hello World"))
        }
        input = readLine()
    }
}
*/

