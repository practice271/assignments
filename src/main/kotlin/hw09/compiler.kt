package hw09

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Label
import org.objectweb.asm.Opcodes.*
import java.util.*

public class Compiler(private val str: String, private val name: String) {
    private var lbls = Stack<Label>()
    private var inBrackets = true

    public fun generateClassByteArray(): ByteArray {
        val cw = ClassWriter(0)
        cw.visit(V1_7, ACC_PUBLIC, name, null, "java/lang/Object", null)
        val mw = cw.visitMethod(ACC_PUBLIC or ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null)
        mw.visitIntInsn(SIPUSH, 30000)
        mw.visitIntInsn(NEWARRAY, T_INT)
        mw.visitIntInsn(ASTORE, 1)
        mw.visitInsn(ICONST_0)
        mw.visitIntInsn(ISTORE, 2)

        for (i in str) {
            when(i) {
                '>' -> mw.visitIincInsn(2, 1)
                '<' -> mw.visitIincInsn(2, -1)
                '+' -> {
                    mw.visitVarInsn(ALOAD, 1)
                    mw.visitVarInsn(ILOAD, 2)
                    mw.visitInsn(DUP2)
                    mw.visitInsn(IALOAD)
                    mw.visitInsn(ICONST_1)
                    mw.visitInsn(IADD)
                    mw.visitInsn(IASTORE)
                }
                '-' -> {
                    mw.visitVarInsn(ALOAD, 1)
                    mw.visitVarInsn(ILOAD, 2)
                    mw.visitInsn(DUP2)
                    mw.visitInsn(IALOAD)
                    mw.visitInsn(ICONST_1)
                    mw.visitInsn(ISUB)
                    mw.visitInsn(IASTORE)
                }
                '.' -> {
                    mw.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
                    mw.visitVarInsn(ALOAD, 1)
                    mw.visitVarInsn(ILOAD, 2)
                    mw.visitInsn(IALOAD)
                    mw.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(C)V", false)
                }
                ',' -> {
                    mw.visitVarInsn(ALOAD, 1)
                    mw.visitVarInsn(ILOAD, 2)
                    mw.visitFieldInsn(GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;")
                    mw.visitMethodInsn(INVOKEVIRTUAL, "java/io/InputStream", "read", "()I", false)
                    mw.visitInsn(IASTORE)
                }
                '[' -> {
                    val beginning = Label()
                    val end = Label()
                    lbls.push(end)
                    lbls.push(beginning)
                    mw.visitLabel(beginning)

                    if (inBrackets) {
                        mw.visitFrame(F_APPEND, 2, arrayOf("[I", INTEGER), 0, null)
                        inBrackets = false
                    }
                    else {
                        mw.visitFrame(F_SAME, 0, null, 0, null)
                    }
                    mw.visitVarInsn(ALOAD, 1)
                    mw.visitVarInsn(ILOAD, 2)
                    mw.visitInsn(IALOAD)
                    mw.visitJumpInsn(IFEQ, end)
                }
                ']' -> {
                    mw.visitJumpInsn(GOTO, lbls.pop())
                    mw.visitLabel(lbls.pop())
                    mw.visitFrame(F_SAME, 0, null, 0, null)
                }
            }
        }

        mw.visitInsn(RETURN)
        mw.visitMaxs(4, 3)
        cw.visitEnd()
        return cw.toByteArray()
    }
}
