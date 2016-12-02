package hw09

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.*
import java.util.*

public class BrainfuckCompiler(private val program: String, private val name: String) {
    private val MAX_MEM = 32767
    private val ARRAY_REF = 1
    private val PTR_REF = 2
    private val labelsStack = Stack<Label>()
    private var isFirstCycle = true

    private fun MethodVisitor.brainfuckIncPtr() = visitIincInsn(2, 1)

    private fun MethodVisitor.brainfuckDecPtr() = visitIincInsn(2, -1)

    private fun MethodVisitor.brainfuckInc() {
        visitVarInsn(ALOAD, ARRAY_REF)
        visitVarInsn(ILOAD, PTR_REF)
        visitInsn(DUP2)
        visitInsn(IALOAD)
        visitInsn(ICONST_1)
        visitInsn(IADD)
        visitInsn(IASTORE)
    }

    private fun MethodVisitor.brainfuckDec() {
        visitVarInsn(ALOAD, ARRAY_REF)
        visitVarInsn(ILOAD, PTR_REF)
        visitInsn(DUP2)
        visitInsn(IALOAD)
        visitInsn(ICONST_1)
        visitInsn(ISUB)
        visitInsn(IASTORE)
    }

    private fun MethodVisitor.brainfuckPrint() {
        visitFieldInsn(GETSTATIC, "java/lang/System",
                "out", "Ljava/io/PrintStream;")
        visitVarInsn(ALOAD, ARRAY_REF)
        visitVarInsn(ILOAD, PTR_REF)
        visitInsn(IALOAD)
        visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream",
                "print", "(C)V", false)
    }

    private fun MethodVisitor.brainfuckScan() {
        visitVarInsn(ALOAD, ARRAY_REF)
        visitVarInsn(ILOAD, PTR_REF)
        visitFieldInsn(GETSTATIC, "java/lang/System",
                "in", "Ljava/io/InputStream;")
        visitMethodInsn(INVOKEVIRTUAL, "java/io/InputStream",
                "read", "()I")
        visitInsn(IASTORE)
    }

    private fun MethodVisitor.brainfuckStartCycle() {
        val startLabel = Label()
        val endLabel = Label()
        labelsStack.push(endLabel)
        labelsStack.push(startLabel)
        visitLabel(startLabel)
        if (isFirstCycle) {
            visitFrame(F_APPEND, 2, arrayOf("[I", INTEGER), 0, null)
            isFirstCycle = false
        }
        else
            visitFrame(F_SAME, 0, null, 0, null)
        visitVarInsn(ALOAD, ARRAY_REF)
        visitVarInsn(ILOAD, PTR_REF)
        visitInsn(IALOAD)
        visitJumpInsn(IFEQ, endLabel)
    }

    private fun MethodVisitor.brainfuckEndCycle() {
        visitJumpInsn(GOTO, labelsStack.pop())
        visitLabel(labelsStack.pop())
        visitFrame(F_SAME, 0, null, 0, null)
    }

    public fun compile() : ByteArray {
        val cw = ClassWriter(0)
        cw.visit(V1_7, ACC_PUBLIC, name, null, "java/lang/Object", null)

        val mv = cw.visitMethod(ACC_PUBLIC or ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null)
        mv.visitCode()
        val lStart = Label()
        mv.visitLabel(lStart)

        mv.visitIntInsn(SIPUSH, MAX_MEM)
        mv.visitIntInsn(NEWARRAY, T_INT)
        mv.visitIntInsn(ASTORE, ARRAY_REF)

        mv.visitInsn(ICONST_0)
        mv.visitIntInsn(ISTORE, PTR_REF)

        isFirstCycle = true
        for (index in 0..program.length - 1) {
            when (program[index]) {
                '>' -> mv.brainfuckIncPtr()
                '<' -> mv.brainfuckDecPtr()
                '+' -> mv.brainfuckInc()
                '-' -> mv.brainfuckDec()
                '.' -> mv.brainfuckPrint()
                ',' -> mv.brainfuckScan()
                '[' -> mv.brainfuckStartCycle()
                ']' -> mv.brainfuckEndCycle()
            }
        }
        mv.visitInsn(RETURN)
        val lEnd = Label()
        mv.visitLabel(lEnd)
        mv.visitMaxs(4, 3)
        mv.visitEnd()
        cw.visitEnd()

        return cw.toByteArray()
    }
}
