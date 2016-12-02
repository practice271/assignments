package hw9

import java.io.File
import java.io.StringReader
import java.io.StringWriter
import java.net.URLClassLoader

class BrainRunner: CodeRunner {

    public override fun runCode(source: String, input: String): String {
        val g = BrainGenerator("TestClass", source)
        g.generate()
        g.save()

        val classUrl = arrayOf(File(".").toURI().toURL())
        val classLoader = URLClassLoader(classUrl)
        val testClass = classLoader.loadClass("TestClass")
        val run = testClass.getMethod(
                "run",
                Class.forName("java.io.Reader"),
                Class.forName("java.io.Writer")
        )

        val r = StringReader(input + '\u0000')
        val w = StringWriter()
        run.invoke(null, r, w)
        r.close()
        w.close()

        return w.toString()
    }

}