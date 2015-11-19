package hw09

import java.util.ArrayList

/**
 * Created by Alexander on 19.11.2015.
 */
object Tokenizer {
    fun tokenize(code: String): List<Opcode> {
        //Создаем массив лексем (которые уже являются опкодами и готовы к исполнению)
        val retValue = ArrayList<Opcode>()

        for (c in code) {
            when (c) {
                '>' -> retValue.add(Opcode(Opcode.Type.SHR))
                '<' -> retValue.add(Opcode(Opcode.Type.SHL))

                '+' -> retValue.add(Opcode(Opcode.Type.ADD))
                '-' -> retValue.add(Opcode(Opcode.Type.SUB))

                '.' -> retValue.add(Opcode(Opcode.Type.OUT))
                ',' -> retValue.add(Opcode(Opcode.Type.IN))
                '[' -> retValue.add(Opcode(Opcode.Type.WHILE))
                ']' -> retValue.add(Opcode(Opcode.Type.END))
            }
        }

        return retValue
    }
}