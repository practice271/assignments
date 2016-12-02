/*
 * Homework 9 (03.11.2015)
 * Class for loading and running of bytecode.
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw09

import java.io.File
import java.io.PrintStream

public class ClassRunner {
    private class ByteArrayClassLoader(): ClassLoader() {
        fun loadClass(name: String?, buf: ByteArray): Class<*>? {
            return super.defineClass(name, buf, 0, buf.size)
        }
    }

    public fun loadClassAndRun(classByteArray: ByteArray, className : String) {
        val out = PrintStream(File("programOutput.out"))
        System.setOut(out)

        val cl = ByteArrayClassLoader()
        val compilerClass = cl.loadClass(className, classByteArray)
        val methods = compilerClass?.methods
        if (methods == null || methods.isEmpty()) throw Exception()
        for (method in methods) {
            if (method.name != "main") continue
            method.invoke(null, arrayOf<String>())
        }
    }
}