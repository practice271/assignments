/*
 * Homework 9 (17.11.2015)
 * Compiler for Brainfuck language.
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw09

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.*
import java.util.*

public class BrainfuckCompiler {
    /** Generates bytecode of given Brainfuck [program] using [input] string. */
    public fun compile(program : String, input : String) : ByteArray {
        val cw = ClassWriter(0)
        cw.visit(V1_7, ACC_PUBLIC, "BrainfuckCompiler", null, "java/lang/Object", null)
        val mv = cw.visitMethod(ACC_PUBLIC or ACC_STATIC, "main",
                "([Ljava/lang/String;)V", null, null)

        // new int array with size of 30000 cells
        mv.visitIntInsn(SIPUSH, 30000)
        mv.visitIntInsn(NEWARRAY, T_INT)
        mv.visitIntInsn(ASTORE, 1)

        // index = 0
        mv.visitInsn(ICONST_0)
        mv.visitIntInsn(ISTORE, 2)

        val inputStream = input.iterator()
        val stack       = Stack<Label>()
        mv.visitFrame(F_APPEND, 2, arrayOf("[I", INTEGER), 0, null)

        for (c in program) {
            when (c) {
                '>' -> mv.visitIincInsn(2, 1)
                '<' -> mv.visitIincInsn(2, -1)
                '+' -> mv.visitChangeValueInsn(IADD)
                '-' -> mv.visitChangeValueInsn(ISUB)
                '.' -> mv.visitWriteInsn()
                ',' -> mv.visitReadInsn(inputStream.next())
                '[' -> mv.visitLoopStartInsn(stack)
                ']' -> mv.visitLoopEndInsn(stack)
            }
        }

        mv.visitInsn(RETURN)
        mv.visitMaxs(4, 3)
        mv.visitEnd()
        cw.visitEnd()

        return cw.toByteArray()
    }

    /**
     * Generates bytecode for instructions which change value of a cell.
     * @param op is opcode of appropriate instruction.
     */
    private fun MethodVisitor.visitChangeValueInsn(op : Int) {
        visitIntInsn(ALOAD, 1)
        visitIntInsn(ILOAD, 2)
        visitInsn(DUP2)
        visitInsn(IALOAD)
        visitInsn(ICONST_1)
        visitInsn(op)
        visitInsn(IASTORE)
    }

    /** Generates bytecode for write instruction. */
    private fun MethodVisitor.visitWriteInsn() {
        visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
        visitIntInsn(ALOAD, 1)
        visitIntInsn(ILOAD, 2)
        visitInsn(IALOAD)
        visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(C)V", false)
    }

    /**
     * Generates bytecode for read instruction.
     * @param symbol is a char which should be written in memory.
     */
    private fun MethodVisitor.visitReadInsn(symbol : Char) {
        visitIntInsn(ALOAD, 1)
        visitIntInsn(ILOAD, 2)
        visitIntInsn(SIPUSH, symbol.toInt())
        visitInsn(IASTORE)
    }

    /** Generates bytecode for instruction of start of loop */
    private fun MethodVisitor.visitLoopStartInsn(stack : Stack<Label>) {
        val startLabel = Label()
        val finishLabel = Label()

        stack.push(finishLabel)
        stack.push(startLabel)
        visitLabel(startLabel)
        visitFrame(F_SAME, 0, null, 0, null)

        visitIntInsn(ALOAD, 1)
        visitIntInsn(ILOAD, 2)
        visitInsn(IALOAD)
        visitJumpInsn(IFEQ, finishLabel)
    }

    /** Generates bytecode for instruction of ending of loop. */
    private fun MethodVisitor.visitLoopEndInsn(stack : Stack<Label>) {
        visitJumpInsn(GOTO, stack.pop())
        visitLabel(stack.pop())
        visitFrame(F_SAME, 0, null, 0, null)
    }
}