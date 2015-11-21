package hw09

public class Interpritator_brainfuck(program: String, in_string: String){
    private var pointer_memory = 0
    private var pointer_instruction = 0
    private var pointer_in = 0
    private var memory = Array(30000,{0})
    private var out = ""
    private val program = program
    private val in_str = in_string

    private fun nextCeilMemory() {
        pointer_memory++
    }

    private fun prevCeilMemory() {
        pointer_memory--
        if (pointer_memory < 0) throw IndexOutOfBoundsException()
    }

    private fun inc() {
        memory[pointer_memory]++
        if (memory[pointer_memory] > 255){
            memory[pointer_memory] -= 256
        }
    }

    private fun dec() {
        memory[pointer_memory]--
        if (memory[pointer_memory] < 0) {
            memory[pointer_memory] = 256 - memory[pointer_memory]
        }
    }

    private fun openLoop() {
        val cur = memory[pointer_memory]
        var count = 1
        if (cur == 0) {
            var i = pointer_instruction + 1
            while (i < program.length) {
                if (program[i] == '[') count++
                if (program[i] == ']') count--
                if (count == 0) break
                i++
            }
            pointer_instruction = i
        }
    }

    private fun closeLoop() {
        val cur = memory[pointer_memory]
        var count = 1
        if (cur != 0) {
            var i = pointer_instruction - 1
            while (i >= 0) {
                if (program[i] == '[') count--
                if (program[i] == ']') count++
                if (count == 0) break
                i--
            }
            pointer_instruction = i
        }
    }

    fun print() {
        out += (memory[pointer_memory]).toChar()
    }

    fun read() {
        if (pointer_in < in_str.length){
            val value = in_str[pointer_in]
            nextCeilMemory()
            memory[pointer_memory] = value.toInt()
            pointer_in++
        }
    }

    fun run(): String {
        while (pointer_instruction < program.length) {
            when (program[pointer_instruction]) {
                '>' -> nextCeilMemory()
                '<' -> prevCeilMemory()
                '+' -> inc()
                '-' -> dec()
                '[' -> openLoop()
                ']' -> closeLoop()
                '.' -> print()
                ',' -> read()
                else -> println("Error")
            }
            pointer_instruction++
        }
        return out
    }
}

fun main (args : Array<String>) {
    var program = ""
    val in_str = ""
    val inter = Interpritator_brainfuck(program, in_str).run()
    println("${inter}")
}

