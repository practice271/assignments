package hw9

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.*
import org.objectweb.asm.util.CheckClassAdapter
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

class BrainGenerator(val className: String, val program: String) {

    var labelOpenStack: Stack<Label> = Stack()
    var labelCloseStack: Stack<Label> = Stack()
    var b: ByteArray= ByteArray(0)

    public fun generate() {
        if (!b.isEmpty())
            return
        val cv = ClassWriter(1)
        val cw = CheckClassAdapter(cv);

        cw.visit(
                V1_7,
                ACC_PUBLIC,
                className,
                null,
                "java/lang/Object",
                null
        )

        val mv = cw.visitMethod(
                ACC_PUBLIC or ACC_STATIC,
                "main",
                "([Ljava/lang/String;)V",
                null,
                null
        )
        genWrapper(mv)

        val rv = cw.visitMethod(
                ACC_PUBLIC or ACC_STATIC,
                "run",
                "(Ljava/io/Reader;Ljava/io/Writer;)V",
                null,
                null
        )

        genInstructions(rv)

        cw.visitEnd()

        b = cv.toByteArray()
    }

    private fun genWrapper(mv: MethodVisitor) {
        mv.visitCode()
        mv.visitTypeInsn(NEW, "java/io/InputStreamReader")
        mv.visitInsn(DUP)
        mv.visitFieldInsn(
                GETSTATIC,
                "java/lang/System",
                "in",
                "Ljava/io/InputStream;"
        )
        mv.visitMethodInsn(
                INVOKESPECIAL,
                "java/io/InputStreamReader",
                "<init>",
                "(Ljava/io/InputStream;)V",
                false)
        mv.visitVarInsn(ASTORE, 1)

        mv.visitTypeInsn(NEW, "java/io/OutputStreamWriter")
        mv.visitInsn(DUP)
        mv.visitFieldInsn(
                GETSTATIC,
                "java/lang/System",
                "out",
                "Ljava/io/PrintStream;"
        )
        mv.visitMethodInsn(
                INVOKESPECIAL,
                "java/io/OutputStreamWriter",
                "<init>",
                "(Ljava/io/OutputStream;)V",
                false)
        mv.visitVarInsn(ASTORE, 2)

        mv.visitVarInsn(ALOAD, 1)
        mv.visitVarInsn(ALOAD, 2)
        mv.visitMethodInsn(
                INVOKESTATIC,
                className,
                "run",
                "(Ljava/io/Reader;Ljava/io/Writer;)V",
                false
        )

        mv.visitVarInsn(ALOAD, 1)
        mv.visitMethodInsn(
                INVOKEVIRTUAL,
                "java/io/InputStreamReader",
                "close",
                "()V",
                false
        )

        mv.visitVarInsn(ALOAD, 2)
        mv.visitMethodInsn(
                INVOKEVIRTUAL,
                "java/io/OutputStreamWriter",
                "close",
                "()V",
                false
        )

        mv.visitInsn(RETURN)

        mv.visitMaxs(4, 3)
        mv.visitEnd()
    }

    private fun genInstructions(mv: MethodVisitor) {
        var first = true

        fun genIndex() {
            mv.visitVarInsn(ALOAD, 2)
            mv.visitVarInsn(ILOAD, 3)
        }

        fun genLoadStore() {
            genIndex()
            mv.visitInsn(DUP2)
        }

        fun genIncrement() {
            genLoadStore()
            mv.visitInsn(BALOAD)

            mv.visitInsn(ICONST_1)
            mv.visitInsn(IADD)
            mv.visitLdcInsn(Integer(0xFF))
            mv.visitInsn(IAND)

            mv.visitInsn(BASTORE)
        }

        fun genDecrement() {
            genLoadStore()
            mv.visitInsn(BALOAD)

            mv.visitInsn(ICONST_1)
            mv.visitInsn(ISUB)
            mv.visitLdcInsn(Integer(0xFF))
            mv.visitInsn(IAND)

            mv.visitInsn(BASTORE)
        }

        fun genCloseLoop() {
            val begin = labelOpenStack.pop()
            val end = labelCloseStack.pop()

            mv.visitJumpInsn(GOTO, begin)
            mv.visitLabel(end)
            mv.visitFrame(F_SAME, 0, null, 0, null)
        }

        fun genOpenLoop() {
            val begin = Label()
            val end = Label()

            labelOpenStack.push(begin)
            labelCloseStack.push(end)

            mv.visitLabel(begin)
            if (first) {
                mv.visitFrame(F_APPEND, 2, arrayOf("[B", INTEGER), 0, null)
                first = false
            } else {
                mv.visitFrame(F_SAME, 0, null, 0, null)
            }
            genIndex()
            mv.visitInsn(BALOAD)
            mv.visitJumpInsn(IFEQ, end)
        }

        fun genRead() {
            genIndex()

            mv.visitVarInsn(ALOAD, 0)

            mv.visitMethodInsn(
                    INVOKEVIRTUAL,
                    "java/io/Reader",
                    "read",
                    "()I",
                    false
            )

            mv.visitInsn(BASTORE)
        }

        fun genWrite() {
            mv.visitVarInsn(ALOAD, 1)

            genIndex()
            mv.visitInsn(BALOAD)

            mv.visitLdcInsn(Integer(0xFF))
            mv.visitInsn(IAND)

            mv.visitMethodInsn(
                    INVOKEVIRTUAL,
                    "java/io/Writer",
                    "write",
                    "(I)V",
                    false
            )
        }

        fun genMoveBackward() {
            mv.visitIincInsn(3, -1)
        }

        fun genMoveForward() {
            mv.visitIincInsn(3, 1)
        }

        fun genLocals() {
            mv.visitLdcInsn(Integer(1024 * 32))
            mv.visitIntInsn(NEWARRAY, T_BYTE)
            mv.visitVarInsn(ASTORE, 2)

            mv.visitInsn(ICONST_0)
            mv.visitVarInsn(ISTORE, 3)
        }

        mv.visitCode()

        genLocals()

        for (c in program)
            when (c) {
                '>' -> genMoveForward()
                '<' -> genMoveBackward()
                '+' -> genIncrement()
                '-' -> genDecrement()
                '.' -> genWrite()
                ',' -> genRead()
                '[' -> genOpenLoop()
                ']' -> genCloseLoop()
                else -> { }
            }

        mv.visitInsn(RETURN)
        mv.visitMaxs(4, 4)
        mv.visitEnd()
    }

    fun save() {
        val p = Paths.get("$className.class")
        Files.write(p, b)
    }

}