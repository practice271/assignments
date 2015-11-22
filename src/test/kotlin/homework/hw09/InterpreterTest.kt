package homework.hw09

import org.junit.Test
import kotlin.test.assertEquals


class InterpreterTest {

    //the differencies are in way of output
    public class TestInterpreter(private val str : String) {

        private var arr = ByteArray(100)
        private var pointer = 0
        private val tokens = Parser().parse(str)
        private var level = 0
        private var i = 0

        public fun interpret() : String {

            var answer = ""
            while (i < tokens.size) {
                when (tokens[i]) {
                    Parser.Tokens.rshift -> pointer++
                    Parser.Tokens.lshift -> pointer--
                    Parser.Tokens.INC -> arr[pointer]++
                    Parser.Tokens.DEC -> arr[pointer]--
                    Parser.Tokens.write  -> arr[pointer] = System.`in`.read().toByte()
                    Parser.Tokens.print  -> answer += arr[pointer].toChar()
                    Parser.Tokens.lb ->
                        if (arr[pointer].toInt() == 0) {
                            i++
                            while (level > 0 || tokens[i] != Parser.Tokens.rb) {
                                if (tokens[i] == Parser.Tokens.lb) level++
                                if (tokens[i] == Parser.Tokens.rb) level--
                                i++
                            }
                        }
                    Parser.Tokens.rb ->
                        if (arr[pointer].toInt() != 0) {
                            i--
                            while (level > 0 || tokens[i] != Parser.Tokens.lb) {
                                if (tokens[i] == Parser.Tokens.rb) level++
                                if (tokens[i] == Parser.Tokens.lb) level--
                                i--
                            }
                            i--
                        }
                }
                i++
            }
            return answer
        }
    }

    @Test fun helloworldTest1(){
        val interpreter = TestInterpreter("++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.")
        assertEquals("Hello World!\n",interpreter.interpret())
    }

    @Test fun helloworldTest2(){
        val program = "++++[>+++++<-]>[<+++++>-]+<+[" +
                "        >[>+>+<<-]++>>[<<+>>-]>>>[-]++>[-]+" +
                "        >>>+[[-]++++++>>>]<<<[[<++++++++<++>>-]+<.<[>----<-]<]" +
                "        <<[>>>>>[>>>[-]+++++++++<[>-<-]+++++++++>[-[<->-]+[<<<]]<[>+<-]>]<<-]<<-]"

        val interpreter = TestInterpreter(program)
        assertEquals("Hello World!\n",interpreter.interpret())
    }
}