package hw09

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Label
import org.objectweb.asm.Opcodes.*
import org.objectweb.asm.MethodVisitor;
import java.util.*

public class  PetoohCompiler(private val prog: String, private val name: String) {
    public  var  memorySize: Int = 30000
    private val  labelsStack = Stack<Label>()
    private var  firstLoop = true
    var program = prepare(prog)

    public fun generateByteArray(): ByteArray {
        val cw = ClassWriter(0)
        cw.visit(V1_7, ACC_PUBLIC, name, null, "java/lang/Object", null)
        val mv = cw.visitMethod(ACC_PUBLIC or ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null)
        mv.visitCode()
        val lStart = Label()
        mv.visitLabel(lStart)

        mv.visitIntInsn(SIPUSH, memorySize)
        mv.visitIntInsn(NEWARRAY, T_INT)
        mv.visitIntInsn(ASTORE, 1)

        mv.visitInsn(ICONST_0)
        mv.visitIntInsn(ISTORE, 2)

        var chars = program.toCharArray()
        var instruction: StringBuilder = StringBuilder()
        for (i in chars.indices) {
            var c: Char = chars[i]
            instruction.append(c)
            var buf: String = instruction.toString()
            if (("Kud".equals(buf) || "kud".equals(buf)) &&
                    (i != chars.size - 1 && chars[i + 1] == 'a')) {
                continue
            }

            when (buf) {
                "kudah" -> {
                    mv.petoohShiftL()
                    instruction = StringBuilder()
                           }
                "Kudah" -> {
                    mv.petoohShiftR()
                    instruction = StringBuilder()
                }
                "Ko" -> {
                    mv.petoohInc()
                    instruction = StringBuilder()
                }
                "kO" -> {
                    mv.petoohDec()
                    instruction = StringBuilder()
                }
                "Kukarek" -> {
                    mv.petoohWrite()
                    instruction = StringBuilder()
                }
                "Kud" -> {
                    mv.petoohStartLoop()
                    instruction = StringBuilder()
                }
                "kud" -> {
                    mv.petoohEndLoop()
                    instruction = StringBuilder()
                }
            }
        }

        mv.visitInsn(RETURN)
        val lEnd = Label()
        mv.visitLabel(lEnd)
        mv.visitMaxs(4, 3)
        cw.visitEnd()

        return cw.toByteArray()
    }

    public  fun MethodVisitor.petoohShiftL() {
        visitIincInsn(2, -1)
    }

    public fun MethodVisitor.petoohShiftR() {
        visitIincInsn(2, 1)
    }

    public fun MethodVisitor.petoohInc() {
        visitVarInsn(ALOAD, 1)
        visitVarInsn(ILOAD, 2)
        visitInsn(DUP2)
        visitInsn(IALOAD)
        visitInsn(ICONST_1)
        visitInsn(IADD)
        visitInsn(IASTORE)
    }

    public fun MethodVisitor.petoohDec() {
        visitVarInsn(ALOAD, 1)
        visitVarInsn(ILOAD, 2)
        visitInsn(DUP2)
        visitInsn(IALOAD)
        visitInsn(ICONST_1)
        visitInsn(ISUB)
        visitInsn(IASTORE)
    }

    public fun MethodVisitor.petoohWrite() {
        visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
        visitVarInsn(ALOAD, 1)
        visitVarInsn(ILOAD, 2)
        visitInsn(IALOAD)
        visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(C)V", false)
    }

    public fun MethodVisitor.petoohStartLoop() {
        val start = Label()
        val end = Label()
        labelsStack.push(end)
        labelsStack.push(start)
        visitLabel(start)

        if (firstLoop) {
            visitFrame(F_APPEND, 2, arrayOf("[I", INTEGER), 0, null)
            firstLoop = false
        }
        else
            visitFrame(F_SAME, 0, null, 0, null)
        visitVarInsn(ALOAD, 1)
        visitVarInsn(ILOAD, 2)
        visitInsn(IALOAD)
        visitJumpInsn(IFEQ, end)
    }

    public fun MethodVisitor.petoohEndLoop() {
        visitJumpInsn(GOTO, labelsStack.pop())
        visitLabel(labelsStack.pop())
        visitFrame(F_SAME, 0, null, 0, null)
    }

    private fun prepare(program: String): String {
        return program.replace("[^adehkKoOru]".toRegex(), "");
    }
}