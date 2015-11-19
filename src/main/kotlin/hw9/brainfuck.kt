package hw9

fun main(args: Array<String>) {
    val s = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++" +
            ".>+.+++++++..+++.>++.<<+++++++++++++++.>.+++." +
            "------.--------.>+.>."

    print(BrainRunner().runCode(s, ""))
    print(BrainInterpreter().runCode(s, ""))
}