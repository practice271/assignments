/*
 * Homework 9 (17.11.2015)
 * Compiler for Brainfuck language
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw09

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.*
import java.io.File
import java.io.PrintStream
import java.util.*

public class BrainfuckCompiler() {
    val stack   = Stack<Label>()
    var isFirst = true

    /** Generates byte array of this class. */
    public fun generateByteArray(program: String, input : String) : ByteArray {
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

        stack.clear()
        isFirst = true
        compile(program, input, mv)

        mv.visitInsn(RETURN)
        mv.visitMaxs(5, 3)
        mv.visitEnd()
        cw.visitEnd()

        return cw.toByteArray()
    }

    /** Generates bytecode of given Brainfuck [program] using [input] string.. */
    private fun compile(program: String, input : String, mv : MethodVisitor) {
        val inputStream = input.iterator()
        for (c in program) {
            when (c) {
                '>' -> mv.visitIincInsn(2, 1)
                '<' -> mv.visitIincInsn(2, -1)
                '+' -> mv.visitChangeValueInsn(IADD)
                '-' -> mv.visitChangeValueInsn(ISUB)
                '.' -> mv.visitWriteInsn()
                ',' -> mv.visitReadInsn(inputStream.next())
                '[' -> mv.visitLoopStartInsn()
                ']' -> mv.visitLoopEndInsn()
            }
        }
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
    private fun MethodVisitor.visitLoopStartInsn() {
        val finishLabel = Label()
        stack.push(finishLabel)

        val startLabel = Label()
        stack.push(startLabel)
        visitLabel(startLabel)

        if (isFirst) {
            visitFrame(F_APPEND, 2, arrayOf("[I", INTEGER), 0, null)
            isFirst = false
        }
        else visitFrame(F_SAME, 0, null, 0, null)

        visitIntInsn(ALOAD, 1)
        visitIntInsn(ILOAD, 2)
        visitInsn(IALOAD)
        visitJumpInsn(IFEQ, finishLabel)
    }

    /** Generates bytecode for instruction of ending of loop. */
    private fun MethodVisitor.visitLoopEndInsn() {
        visitJumpInsn(GOTO, stack.pop())
        visitLabel(stack.pop())
        visitFrame(F_SAME, 0, null, 0, null)
    }
}

private class ByteArrayClassLoader(): ClassLoader() {
    fun loadClass(name: String?, buf: ByteArray): Class<*>? {
        return super.defineClass(name, buf, 0, buf.size)
    }
}

public fun loadClassAndRun(classByteArray: ByteArray) {
    val out = PrintStream(File("programOutput.out"))
    System.setOut(out)

    val cl = ByteArrayClassLoader()
    val compilerClass = cl.loadClass("BrainfuckCompiler", classByteArray)
    val methods = compilerClass?.methods
    if (methods == null || methods.isEmpty()) throw Exception()
    for (method in methods) {
        if (method.name != "main") continue
        method.invoke(null, arrayOf<String>())
    }
}