package homeworks.hw09

import org.junit.Test
import kotlin.test.assertEquals
import java.io.PrintStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream

/**
 * Created by Ilya on 28.11.2015.
 */

class BrainfuckToJVMTest {
    private fun brainfuckCompiler(program: String, input: String, output: String) {
        val className = "Test"
        val classByteArray = BrainfuckToJVM().compile(program, "Test")

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

    class ByteArrayClassLoader() : ClassLoader() {
        fun loadClass(name : String?, buf : ByteArray) : Class<*>? {
            return super.defineClass(name, buf, 0, buf.size)
        }
    }

    class StringInputStream(private val str : String) : InputStream() {
        var ptr = 0
        override fun read() = if (ptr < str.length) str[ptr++].toInt() else -1
    }

    class StringPrintStream() : PrintStream(EmptyStream()) {
        private var sb = StringBuilder()
        override fun print(c : Char) { sb.append(c) }
        override fun toString() = sb.toString()
    }

    class EmptyStream() : OutputStream() {
        override fun write(b : Int) { }
    }

    @Test fun HelloWorld() {
        brainfuckCompiler("++++++++++[>+++++++>++++++++++>+++>++++<<<<-]>++.>+.+++++++..+++.>>++++.<++.<+++" +
                "+++++.--------.+++.------.--------.>+.", "", "Hello, world!")
    }

    @Test fun HelloWorld2() {
        brainfuckCompiler("[-]>[-]<>++++++++[<++++++++>-]<++++++++.>+++++[<+++++>-]<++++.>+++[<+++>-]<--..+++.>+++++++++" +
                "[<--------->-]<++.>+++++++[<+++++++>-]<++++++.>+++++[<+++++>-]<-.+++.>++[<-->-]<--.>+++[<--->-]" +
                "<+.>++++++++[<-------->-]<---.-.>++++++++[<++++++++>-]<+.>++[<++>-]<++.>++[<-->-]<--.>+++[<+++>-]" +
                "<-.>++[<++>-]<+.", "", "Hello World! again")
    }

    @Test fun Test3() {
        brainfuckCompiler("[-]>[-]<>+++++++++[<+++++++++>-]<--------.>+++++++[<+++++++>-]<------.>+++++++++[<--------->-]" +
                "<---.>+++++++++[<+++++++++>-]<--------.>+++[<+++>-]<+.>+++++++++[<--------->-]<--.>+++++++++[<+++++++++>-]" +
                "<----.>+++[<+++>-]<+++.>+++++++++[<--------->-]<--------.>++++[<++++>-]<+++.>++++[<---->-]<---.>+++++++++" +
                "[<+++++++++>-]<+++.>++++[<---->-]<+.>++++[<++++>-]<--.+.", "", "It is my 3 test")
    }
}
