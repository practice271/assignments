package hw09

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Label
import org.objectweb.asm.Opcodes
import java.util.*

/**
 * Created by Antropov Igor on 21.11.2015.
 */

public class BrainfuckCompiler() {

    var memorySize = 30000
    var lbls = Stack<Label>()
    var isFirst = true

    public fun getClassWriter(instructions: String): ClassWriter {



        val cw = ClassWriter(0)
        cw.visit(Opcodes.V1_7, Opcodes.ACC_PUBLIC, "Compiler", null, "java/lang/Object", null)

        val mw = cw.visitMethod(Opcodes.ACC_PUBLIC or Opcodes.ACC_STATIC, "main",
                "([Ljava/lang/String;)V", null, null)
        mw.visitCode()


        mw.visitIntInsn(Opcodes.SIPUSH, memorySize)
        mw.visitIntInsn(Opcodes.NEWARRAY, Opcodes.T_CHAR)
        mw.visitVarInsn(Opcodes.ASTORE, 1)

        mw.visitLdcInsn(Opcodes.ICONST_0)
        mw.visitVarInsn(Opcodes.ISTORE, 2)

        isFirst = true
        for (instruction in instructions) {
            when (instruction) {

                '>' -> {
                    mw.visitIincInsn(2, 1)
                    mw.visitVarInsn(Opcodes.ILOAD, 2)
                    mw.visitIntInsn(Opcodes.SIPUSH, memorySize)
                    mw.visitInsn(Opcodes.IREM)
                    mw.visitIntInsn(Opcodes.ISTORE, 2)
                }
                '<' -> {
                    mw.visitIincInsn(2, memorySize - 1)
                    mw.visitVarInsn(Opcodes.ILOAD, 2)
                    mw.visitIntInsn(Opcodes.SIPUSH, memorySize)
                    mw.visitInsn(Opcodes.IREM)
                    mw.visitIntInsn(Opcodes.ISTORE, 2)
                }
                '+' -> {
                    mw.visitVarInsn(Opcodes.ALOAD, 1)
                    mw.visitVarInsn(Opcodes.ILOAD, 2)
                    mw.visitInsn(Opcodes.DUP2)
                    mw.visitInsn(Opcodes.CALOAD)
                    mw.visitLdcInsn(1)
                    mw.visitInsn(Opcodes.IADD)
                    mw.visitInsn(Opcodes.I2C)
                    mw.visitInsn(Opcodes.CASTORE)
                }
                '-' -> {
                    mw.visitVarInsn(Opcodes.ALOAD, 1)
                    mw.visitVarInsn(Opcodes.ILOAD, 2)
                    mw.visitInsn(Opcodes.DUP2)
                    mw.visitInsn(Opcodes.CALOAD)
                    mw.visitLdcInsn(-1)
                    mw.visitInsn(Opcodes.IADD)
                    mw.visitInsn(Opcodes.I2C)
                    mw.visitInsn(Opcodes.CASTORE)
                }
                '.' -> {
                    mw.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System",
                            "out", "Ljava/io/PrintStream;")
                    mw.visitVarInsn(Opcodes.ALOAD, 1)
                    mw.visitVarInsn(Opcodes.ILOAD, 2)
                    mw.visitInsn(Opcodes.CALOAD)
                    mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "print", "(C)V", false)
                }
                ',' -> {
                    mw.visitVarInsn(Opcodes.ALOAD, 1)
                    mw.visitVarInsn(Opcodes.ILOAD, 2)
                    mw.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;")
                    mw.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/InputStream", "read", "()I", false)
                    mw.visitInsn(Opcodes.CASTORE)
                }
                '[' -> {
                    val begin = Label()
                    val end = Label()
                    lbls.push(end)
                    lbls.push(begin)
                    mw.visitLabel(begin)
                    if (isFirst) {
                        mw.visitFrame(Opcodes.F_APPEND, 2, arrayOf("[C", Opcodes.INTEGER), 0, null)
                        isFirst = false
                    } else mw.visitFrame(Opcodes.F_SAME, 0, null, 0, null)
                    mw.visitVarInsn(Opcodes.ALOAD, 1)
                    mw.visitVarInsn(Opcodes.ILOAD, 2)
                    mw.visitInsn(Opcodes.CALOAD)
                    mw.visitJumpInsn(Opcodes.IFEQ, end)
                }
                ']' -> {
                    mw.visitJumpInsn(Opcodes.GOTO, lbls.pop())
                    mw.visitLabel(lbls.pop())
                    mw.visitFrame(Opcodes.F_SAME, 0, null, 0, null)
                }
            }
        }

        mw.visitInsn(Opcodes.RETURN)

        mw.visitMaxs(4, 3)
        mw.visitEnd()
        cw.visitEnd()

        return cw
    }
    public fun generateClassByteArray(operations: String): ByteArray =
            this.getClassWriter(operations).toByteArray()

}

class ByteArrayClassLoader() : ClassLoader() {
    fun loadClass(name: String?, buf: ByteArray): Class<*>? {
        return super.defineClass(name, buf, 0, buf.size)
    }
}

