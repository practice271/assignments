/*
 * Homework 9 (17.11.2015)
 * Compiler for PETOOH language.
 *
 * Author: Mikhail Kita, group 271
 */

package homework.hw09

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.*
import java.util.*

public class PetoohCompiler {
    /** Contains instructions of PETOOH. */
    public class Instructions {
        public val INCPTR = "Kudah"
        public val DECPTR = "kudah"
        public val INC = "Ko"
        public val DEC = "kO"
        public val PRINT = "Kukarek"
        public val JMP = "Kud"
        public val RET = "kud"

        /** Checks that given [elem] is an instruction. */
        public fun contains(elem : String) : Boolean {
            val arr = arrayOf(INCPTR, DECPTR, INC, DEC, PRINT, JMP, RET)
            return arr.contains(elem)
        }
    }

    /** Converts given [program] to list of instructions. */
    private fun parse(program : String) : LinkedList<String> {
        val tokens = LinkedList<String>()
        var temp   = ""
        for (i in 0 .. program.length - 1) {
            if ("adehkoruKO".contains(program[i])) temp += program[i]
            if ((temp == "Kud" || temp == "kud") && i < program.length - 2) {
                if (program[i + 1] != 'a') {
                    tokens.add(temp)
                    temp = ""
                }
            }
            else if (Instructions().contains(temp)) {
                tokens.add(temp)
                temp = ""
            }
        }
        return tokens
    }

    /** Generates bytecode of given PETOOH [program]. */
    public fun compile(program: String) : ByteArray {
        val cw = ClassWriter(0)
        cw.visit(V1_7, ACC_PUBLIC, "PetoohCompiler", null, "java/lang/Object", null)
        val mv = cw.visitMethod(ACC_PUBLIC or ACC_STATIC, "main",
                "([Ljava/lang/String;)V", null, null)

        // new int array with size of 30000 cells
        mv.visitIntInsn(SIPUSH, 30000)
        mv.visitIntInsn(NEWARRAY, T_INT)
        mv.visitIntInsn(ASTORE, 1)

        // index = 0
        mv.visitInsn(ICONST_0)
        mv.visitIntInsn(ISTORE, 2)

        val op     = Instructions()
        val tokens = parse(program)
        val stack  = Stack<Label>()
        mv.visitFrame(F_APPEND, 2, arrayOf("[I", INTEGER), 0, null)

        for (t in tokens) {
            when (t) {
                op.INCPTR -> mv.visitIincInsn(2, 1)
                op.DECPTR -> mv.visitIincInsn(2, -1)
                op.INC    -> mv.visitChangeValueInsn(IADD)
                op.DEC    -> mv.visitChangeValueInsn(ISUB)
                op.PRINT  -> mv.visitWriteInsn()
                op.JMP    -> mv.visitLoopStartInsn(stack)
                op.RET    -> mv.visitLoopEndInsn(stack)
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