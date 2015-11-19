package hw9

@Throws(Exception::class)
fun main(args: Array<String>) {
    val s = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++" +
            ".>+.+++++++..+++.>++.<<+++++++++++++++.>.+++." +
            "------.--------.>+.>."

    print(BrainRun().runCode(s, ""))
}