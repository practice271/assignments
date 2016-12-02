package hw09

/**
 * Created by Antropov Igor on 20.11.2015.
 */

public class BrainfuckInterpreter() {

    private val LENGTH = 30000
    private var cpu: Array<Char> = Array(LENGTH, { 0.toChar() })

    public fun interpret(program: String) {
        val chars = program.toCharArray()
        var currentElem: Int = 0
        var brc = 0
        var currentChar: Char
        var num = 0

        for (char in chars) {
            if (char == '[') brc++
            if (char == ']') brc--
        }
        if (brc != 0) print("There is a problem in your code")
        else {
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
                        if (currentElem == 0) currentElem = LENGTH - 1 else currentElem--

                    }
                    '>' -> {
                        if (currentElem == LENGTH - 1) currentElem = 0 else currentElem++
                    }
                    '[' -> {
                        if (cpu[currentElem] == 0.toChar()) {
                            ++brc
                            while (brc > 0 && num < chars.size) {
                                ++num
                                if (chars[num] == '[') ++brc
                                if (chars[num] == ']') --brc
                            }
                        }
                    }
                    ']' -> {
                        if (cpu[currentElem] != 0.toChar()) {
                            brc++
                            while (brc > 0 && num > 0) {
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
}

