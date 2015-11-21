package hw09

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import kotlin.test.assertEquals
/**
 * Created by Mikhail on 21.11.2015.
 */

public class HW09Test1(): OutputTest() {

    @Test fun InterpreterFibonacci() {
        var code = "+++++++++++"+
                ">+>>>>++++++++++++++++++++++++++++++++++++++++++++"+
                ">++++++++++++++++++++++++++++++++<<<<<<[>[>>>>>>+>"+
                "+<<<<<<<-]>>>>>>>[<<<<<<<+>>>>>>>-]<[>++++++++++[-"+
                "<-[>>+>+<<<-]>>>[<<<+>>>-]+<[>[-]<[-]]>[<<[>>>+<<<"+
                "-]>>[-]]<<]>>>[>>+>+<<<-]>>>[<<<+>>>-]+<[>[-]<[-]]"+
                ">[<<+>>[-]]<<<<<<<]>>>>>[+++++++++++++++++++++++++"+
                "+++++++++++++++++++++++.[-]]++++++++++<[->-<]>++++"+
                "++++++++++++++++++++++++++++++++++++++++++++.[-]<<"+
                "<<<<<<<<<<[>>>+>+<<<<-]>>>>[<<<<+>>>>-]<-[>>.>.<<<"+
                "[-]]<<[>>+>+<<<-]>>>[<<<+>>>-]<<[<+>-]>[<+>-]<<<-]"
        val res = "1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89"
        Interpreter.interpret(code)
        assertEquals(res, output.toString())
    }

    @Test fun NotOptimalConverterHelloWorld() {
        val input = "Hello, world!"
        val res = Converter.notOptimalConverte(input)
        Interpreter.interpret(res)
        assertEquals(input, output.toString())
    }

    @Test fun BrainfuckCompilerHelloWorld() = BrainfuckCompiler(
            "++++++++++[>+++++++>++++++++++>+++>++++<" +
                    "<<<-]>++.>+.+++++++..+++.>>++++.<++.<+++"+
                    "+++++.--------.+++.------.--------.>+.",
            "", "Hello, world!"
    )

    @Test fun BrainfuckCompileFibonacci() = BrainfuckCompiler(
            "+++++++++++"+
                    ">+>>>>++++++++++++++++++++++++++++++++++++++++++++"+
                    ">++++++++++++++++++++++++++++++++<<<<<<[>[>>>>>>+>"+
                    "+<<<<<<<-]>>>>>>>[<<<<<<<+>>>>>>>-]<[>++++++++++[-"+
                    "<-[>>+>+<<<-]>>>[<<<+>>>-]+<[>[-]<[-]]>[<<[>>>+<<<"+
                    "-]>>[-]]<<]>>>[>>+>+<<<-]>>>[<<<+>>>-]+<[>[-]<[-]]"+
                    ">[<<+>>[-]]<<<<<<<]>>>>>[+++++++++++++++++++++++++"+
                    "+++++++++++++++++++++++.[-]]++++++++++<[->-<]>++++"+
                    "++++++++++++++++++++++++++++++++++++++++++++.[-]<<"+
                    "<<<<<<<<<<[>>>+>+<<<<-]>>>>[<<<<+>>>>-]<-[>>.>.<<<"+
                    "[-]]<<[>>+>+<<<-]>>>[<<<+>>>-]<<[<+>-]>[<+>-]<<<-]",
            "", "1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89"

    )
    @Test fun BrainfuckCompilerInput() = BrainfuckCompiler(
            ",.,.,.,.", "game", "game"
    )

    @Test fun PetoohCompiler() = PetoohCompiler(
             "KoKoKoKoKoKoKoKoKoKo KudKudah"+
    "KoKoKoKoKoKoKoKo kudah kO kudKudah Kukarek kudah"+
    "KoKoKo KudKudah"+
    "kOkOkOkO kudah kO kudKudah Ko Kukarek kudah"+
    "KoKoKoKo KudKudah KoKoKoKo kudah kO kudKudah kO Kukarek"+
    "kOkOkOkOkO Kukarek Kukarek kOkOkOkOkOkOkO"+
    "Kukarek", "", "PETOOH"
    )

    public fun BrainfuckCompiler(program : String, input : String, output : String) {
        val className = "Test"
        val classByteArray = Brainfuck.compile(program, "Test")

        val out = StringPrintStream()
        System.setIn(StringInputStream(input))
        System.setOut(out)

        val cl = ByteArrayClassLoader()
        val exprClass = cl.loadClass(className, classByteArray)
        val methods = exprClass?.methods
        if (methods == null || methods.isEmpty()) {
            throw AssertionError("No main method was found.")
        }
        for (method in methods) {
            if (method.name != "main") {
                continue
            }
            method.invoke(null, arrayOf<String>())
            assertEquals(output, out.toString())
            return
        }
    }

    public fun PetoohCompiler(program : String, input : String, output : String) {
        val className = "Test"
        val classByteArray = Petooh.compile(program, "Test")

        val out = StringPrintStream()
        System.setIn(StringInputStream(input))
        System.setOut(out)

        val cl = ByteArrayClassLoader()
        val exprClass = cl.loadClass(className, classByteArray)
        val methods = exprClass?.methods
        if (methods == null || methods.isEmpty()) {
            throw AssertionError("No main method was found.")
        }
        for (method in methods) {
            if (method.name != "main") {
                continue
            }
            method.invoke(null, arrayOf<String>())
            assertEquals(output, out.toString())
            return
        }
    }

    internal class ByteArrayClassLoader() : ClassLoader() {
        fun loadClass(name : String?, buf : ByteArray) : Class<*>? {
            return super.defineClass(name, buf, 0, buf.size)
        }
    }

    internal class StringInputStream(private val str : String) : InputStream() {
        var ptr = 0

        override fun read() = if (ptr < str.length) str[ptr++].toInt() else -1
    }

    internal class StringPrintStream() : PrintStream(EmptyStream()) {
        private var sb = StringBuilder()

        override fun print(c : Char) {
            sb.append(c)
        }

        override fun toString() = sb.toString()
    }

    internal class EmptyStream() : OutputStream() {
        override fun write(b : Int) {
        }
    }
}