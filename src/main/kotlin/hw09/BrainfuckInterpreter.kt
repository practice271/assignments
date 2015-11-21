package hw09

/**
 * Created by Antropov Igor on 20.11.2015.
 */

public class BrainfuckInterpreter() {

    private var cpu: Array<Char> = Array(30000, { 0.toChar() })

    public fun interpratate(program: String) {
        val chars = program.toCharArray()
        var currentElem: Int = 0
        var brc = 0
        var currentChar: Char
        var num = 0

        while (num < chars.size) {
            currentChar = chars[num]
            when (currentChar) {
                '+' -> {
                    cpu[currentElem]++
                }
                '-' -> {
                    cpu[currentElem]--
                }
                '.' -> {
                    print(cpu[currentElem].toChar())
                }
                ',' -> {
                        cpu[currentElem] = System.`in`.read().toChar()
                }
                '<' -> {
                    currentElem--
                }
                '>' -> {
                    currentElem++
                }
                '[' -> {
                    if (cpu[currentElem] == 0.toChar()) {
                        ++brc
                        while (brc > 0) {
                            ++num
                            if (chars[num] == '[') ++brc
                            if (chars[num] == ']') --brc
                        }
                    }
                }
                ']' -> {
                    if (cpu[currentElem] != 0.toChar()) {
                        if (chars[num] == ']') brc++
                        while (brc > 0) {
                            --num
                            if (chars[num] == '[') brc--
                            if (chars[num] == ']') brc++
                        }
                        --num
                    }
                }
            }
            num++
        }
    }
}

