package homework9

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Label
import org.objectweb.asm.Opcodes.*
import java.io.ByteArrayInputStream
import java.io.OutputStream
import java.io.PrintStream
import java.util.*

public class BFCompiler() {
    private fun getClassWriter(input: String): ClassWriter {
        val cw = ClassWriter(0)
        cw.visit(V1_7, ACC_PUBLIC, "BFCompiler", null, "java/lang/Object", null)
        val mv = cw.visitMethod(ACC_PUBLIC or ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null)
        mv.visitCode()
        mv.visitIntInsn(SIPUSH, 30000)
        mv.visitIntInsn(NEWARRAY, T_INT)
        mv.visitIntInsn(ASTORE, 1)
        mv.visitInsn(ICONST_0)
        mv.visitIntInsn(ISTORE, 2)
        mv.visitFrame(F_APPEND, 2, arrayOf("[I", INTEGER), 0, null)
        val labels = Stack<Label>()

        for (ch in input) {
            when (ch) {
                '+' -> {
                    mv.visitIntInsn(ALOAD, 1)
                    mv.visitIntInsn(ILOAD, 2)
                    mv.visitInsn(DUP2)
                    mv.visitInsn(IALOAD)
                    mv.visitInsn(ICONST_1)
                    mv.visitInsn(IADD)
                    mv.visitInsn(IASTORE)
                }
                '-' -> {
                    mv.visitIntInsn(ALOAD, 1)
                    mv.visitIntInsn(ILOAD, 2)
                    mv.visitInsn(DUP2)
                    mv.visitInsn(IALOAD)
                    mv.visitInsn(ICONST_1)
                    mv.visitInsn(ISUB)
                    mv.visitInsn(IASTORE)
                }
                '<' -> {
                    mv.visitIincInsn(2, -1)
                }
                '>' -> {
                    mv.visitIincInsn(2, 1)
                }
                '.' -> {
                    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
                    mv.visitIntInsn(ALOAD, 1)
                    mv.visitIntInsn(ILOAD, 2)
                    mv.visitInsn(IALOAD)
                    mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(C)V", false)
                }
                ',' -> {
                    mv.visitIntInsn(ALOAD, 1)
                    mv.visitIntInsn(ILOAD, 2)
                    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;")
                    mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/InputStream", "read", "()I", false)
                    mv.visitInsn(IASTORE)
                }
                '[' -> {
                    val begin = Label()
                    val end = Label()
                    labels.push(end)
                    labels.push(begin)
                    mv.visitLabel(begin)
                    mv.visitFrame(F_SAME, 0, null, 0, null)
                    mv.visitVarInsn(ALOAD, 1)
                    mv.visitVarInsn(ILOAD, 2)
                    mv.visitInsn(IALOAD)
                    mv.visitJumpInsn(IFEQ, end)
                }
                ']' -> {
                    mv.visitJumpInsn(GOTO, labels.pop())
                    mv.visitLabel(labels.pop())
                    mv.visitFrame(F_SAME, 0, null, 0, null)
                }
            }
        }
        mv.visitInsn(RETURN)
        mv.visitMaxs(4, 3)
        mv.visitEnd()
        cw.visitEnd()
        return cw
    }

    private fun generateClassByteArray(input: String): ByteArray {
        return getClassWriter(input).toByteArray()
    }

    private class ByteArrayClassLoader() : ClassLoader() {
        fun loadClass(name: String?, buf: ByteArray): Class<*>? {
            return super.defineClass(name, buf, 0, buf.size)
        }
    }

    private var out = ""
    private var printStr: PrintStream
    init {
        printStr = PrintStream(object : OutputStream() {
            override fun write(ch: Int) {
                out += (ch.toChar())
            }
        })
        System.setOut(printStr)
    }

    private fun loadClassAndRun(classByteArray: ByteArray) {
        val cl = ByteArrayClassLoader()
        val exprClass = cl.loadClass("BFCompiler", classByteArray)
        val methods = exprClass?.methods
        if (methods == null || methods.isEmpty()) {
            throw Exception()
        }
        for (method in methods) {
            if (method.name != "main") {
                continue
            }
            method.invoke(null, arrayOf<String>())
        }
    }

    public fun compile(input: String, ch: String): String {
        val classByteArray = generateClassByteArray(input)
        if (input != "") System.setIn(ByteArrayInputStream(ch.toByteArray()))
        loadClassAndRun(classByteArray)
        return out.toString()
    }
}

