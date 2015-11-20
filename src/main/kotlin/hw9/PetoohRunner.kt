package hw9

import java.util.regex.Pattern

class PetoohRunner : CodeRunner {

    val dictionary = arrayOf(
            Pair("Kudah", ">"),
            Pair("kudah", "<"),
            Pair("Ko", "+"),
            Pair("kO", "-"),
            Pair("Kukarek", "."),
            Pair("Kud", "["),
            Pair("kud", "]")
    ).toMap()

    fun toBrainfuck(s: String): String {
        val regex = "(" + dictionary.keys.joinToString("|") + ")"
        val pattern = Pattern.compile(regex)
        val m = pattern.matcher(s)
        var res = ""
        while (m.find())
            res += dictionary[m.group()]
        return res
    }

    override fun runCode(source: String, input: String): String =
            BrainRunner().runCode(toBrainfuck(source), input)

}