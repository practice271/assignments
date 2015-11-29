package homeworks.hw09

/**
 * Created by Ilya on 22.11.2015.
 */

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.*
import java.util.*


public class BrainfuckToJVM {
    public val MAX_MEMORY = 32767
    private val labels = Stack<Label>()
    private var firstLoop = true

    private class Opcode(val type: Opcode.Type) {
        enum class Type {
            SHIFTR,
            SHIFTL,
            ADD,
            SUB,
            IN,
            OUT,
            WHILE,
            END
        }
    }

    private fun tokenizer(input: String): ArrayList<Opcode> {
        val result = ArrayList<Opcode>()
        input.forEach {
            when (it) {
                '>' -> result.add(Opcode(Opcode.Type.SHIFTR))
                '<' -> result.add(Opcode(Opcode.Type.SHIFTL))

                '+' -> result.add(Opcode(Opcode.Type.ADD))
                '-' -> result.add(Opcode(Opcode.Type.SUB))

                ',' -> result.add(Opcode(Opcode.Type.IN))
                '.' -> result.add(Opcode(Opcode.Type.OUT))
                '[' -> result.add(Opcode(Opcode.Type.WHILE))
                ']' -> result.add(Opcode(Opcode.Type.END))
            }
        }
        return result
    }

    public fun compile(program: String, className: String): ByteArray {
        val cw = ClassWriter(0)
        cw.visit(V1_7, ACC_PUBLIC, className, null, "java/lang/Object", null)

        val mv = cw.visitMethod(ACC_PUBLIC or ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null)
        mv.visitCode()
        val lStart = Label()
        mv.visitLabel(lStart)
        mv.visitIntInsn(SIPUSH, MAX_MEMORY)
        mv.visitIntInsn(NEWARRAY, T_INT)
        mv.visitIntInsn(ASTORE, 1)

        mv.visitInsn(ICONST_0)
        mv.visitIntInsn(ISTORE, 2)

        firstLoop = true
        val opcodes = tokenizer(program)
        opcodes.forEach {
            when (it.type) {
                Opcode.Type.SHIFTR -> mv.visitIincInsn(2, 1)
                Opcode.Type.SHIFTL -> mv.visitIincInsn(2, -1)
                Opcode.Type.ADD    -> mv.visitChangeValueInsn(IADD)
                Opcode.Type.SUB    -> mv.visitChangeValueInsn(ISUB)
                Opcode.Type.IN     -> mv.visitWriteInsn()
                Opcode.Type.OUT    -> mv.visitReadInsn()
                Opcode.Type.WHILE  -> mv.visitWhileInsn()
                Opcode.Type.END    -> mv.visitEndInsn()
            }
        }

        mv.visitInsn(RETURN)
        val lFinish = Label()
        mv.visitLabel(lFinish)
        mv.visitMaxs(4, 3)
        mv.visitEnd()
        cw.visitEnd()
        return cw.toByteArray()
    }

    private fun MethodVisitor.visitChangeValueInsn(operation: Int) {
        visitVarInsn(ALOAD, 1)
        visitVarInsn(ILOAD, 2)
        visitInsn(DUP2)
        visitInsn(IALOAD)
        visitInsn(ICONST_1)
        visitInsn(operation)
        visitInsn(IASTORE)
    }

    private fun MethodVisitor.visitWriteInsn() {
        visitVarInsn(ALOAD, 1)
        visitVarInsn(ILOAD, 2)
        visitFieldInsn(GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;")
        visitMethodInsn(INVOKEVIRTUAL, "java/io/InputStream", "read", "()I", false)
        visitInsn(IASTORE)
    }

    private fun MethodVisitor.visitReadInsn() {
        visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
        visitVarInsn(ALOAD, 1)
        visitVarInsn(ILOAD, 2)
        visitInsn(IALOAD)
        visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(C)V", false)
    }

    private fun MethodVisitor.visitWhileInsn() {
        val start  = Label()
        val finish = Label()
        labels.push(finish)
        labels.push(start)
        visitLabel(start)
        if (firstLoop) {
            visitFrame(F_APPEND, 2, arrayOf("[I", INTEGER), 0, null)
            firstLoop = false
        } else {
            visitFrame(F_SAME, 0, null, 0, null)
        }
        visitVarInsn(ALOAD, 1)
        visitVarInsn(ILOAD, 2)
        visitInsn(IALOAD)
        visitJumpInsn(IFEQ, finish)
    }

    private fun MethodVisitor.visitEndInsn() {
        visitJumpInsn(GOTO, labels.pop())
        visitLabel(labels.pop())
        visitFrame(F_SAME, 0, null, 0, null)
    }
}
