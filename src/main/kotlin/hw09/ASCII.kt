package hw09

public class Convertor_to_brainfuck(program: String){
    private var pointer_memory = 0
    private var pointer_symbol = 0
    private var memory = Array(program.length, { 0.0 })
    private var out = ""
    private val text = program

    private fun symbol(){
        while (pointer_symbol < text.length){
            memory[pointer_memory] = text[pointer_symbol].toDouble()
            pointer_memory++
            pointer_symbol++
        }
    }

    private fun toBF (memo: Double,symbol_: Char){
        var step = 0
        if (memo.toInt() < 4){
            while (step < memo){
                out += symbol_
                step++
            }
            out += '.'
        }
        else{
            out += ">"
            var sqrtVal = (Math.sqrt(memo)).toInt()
            while (step < sqrtVal) {
                out += '+'
                step++
            }
            step = 0
            out += "[<"
            while (step < sqrtVal) {
                if (symbol_ == '+') out += '+'
                else out += '-'
                step++
            }
            out += ">-]<"
            step = 0
            var residue = memo.toInt() - sqrtVal * sqrtVal
            while (step < residue) {
                if (symbol_ == '+') out += '+'
                else out += '-'
                step++
            }
            out += '.'
        }
    }

    private  fun print(){
        toBF(memory[0],'+')
        for (i in 1..memory.size - 1){
            var count = memory[i] - memory[i - 1]
            if (count > 0) toBF(count,'+')
            if (count < 0) toBF(Math.abs(count),'-')
            if (count == 0.0) out += '.'
        }
    }

    public fun run(): String{
        symbol()
        print()
        return out
    }
}
fun main (args : Array<String>){
    val text = "bee"
    val result = Convertor_to_brainfuck(text).run()
    println("res: "+ result)
}


