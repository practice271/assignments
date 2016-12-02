package hw09

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Label
import org.objectweb.asm.Opcodes.*
import org.objectweb.asm.MethodVisitor
import java.util.*


public class ByteArrayClassLoader(): ClassLoader() {
    fun loadClass(name: String?, buf: ByteArray): Class<*>? {
        return super.defineClass(name, buf, 0, buf.size)
    }
}

public class BrainFCompiler() {
    private val tapeLen = 30000
    private val labels = Stack<Label>()
    private var isFirstCycle = true


    public fun compile(code: String): ByteArray {
        val cw = ClassWriter(0)
        cw.visit(V1_7, ACC_PUBLIC, "Compiler", null, "java/lang/Object", null)

        val mv = cw.visitMethod(ACC_PUBLIC or ACC_STATIC, "main",
                "([Ljava/lang/String;)V", null, null)
        mv.visitCode()
        mv.visitIntInsn(SIPUSH, tapeLen)
        mv.visitIntInsn(NEWARRAY, T_CHAR)
        mv.visitVarInsn(ASTORE, 1)
        mv.visitLdcInsn(ICONST_0)
        mv.visitVarInsn(ISTORE, 2)

        isFirstCycle = true
        code.forEach {
            when (it) {
                BFTokens.INC.ch -> mv.changeValue(IADD)
                BFTokens.DEC.ch -> mv.changeValue(ISUB)
                BFTokens.RMOVE.ch -> mv.shift(1)
                BFTokens.LMOVE.ch -> mv.shift(-1)
                BFTokens.IN.ch -> mv.read()
                BFTokens.OUT.ch -> mv.write()
                BFTokens.LBRT.ch -> mv.cycleStart()
                BFTokens.RBRT.ch -> mv.cycleFinish()
            }
        }
        mv.visitInsn(RETURN)
        mv.visitMaxs(4, 3)

        mv.visitEnd()
        cw.visitEnd()
        return cw.toByteArray()

    }

    public fun loadClassAndRun(classByteArray: ByteArray): Any? {
        val cl = ByteArrayClassLoader()
        val exprClass = cl.loadClass("MyClass", classByteArray)
        val methods = exprClass?.methods
        if (methods == null || methods.isEmpty()) { throw Exception() }
        for (method in methods) {
            if (method.name != "main") continue
            return method.invoke(null, arrayOf<String>())
        }
        return null
    }

    public fun MethodVisitor.changeValue(oper: Int) {
        visitVarInsn(ALOAD, 1)
        visitVarInsn(ILOAD, 2)
        visitInsn(DUP2)
        visitInsn(CALOAD)
        visitLdcInsn(1)
        visitInsn(oper)
        visitInsn(I2C)
        visitInsn(CASTORE)
    }

    public fun MethodVisitor.shift(oper: Int) {
        if (oper == 1)
            visitIincInsn(2, 1)
        else
            visitIincInsn(2, tapeLen - 1)
        visitVarInsn(ILOAD, 2)
        visitIntInsn(SIPUSH, tapeLen)
        visitInsn(IREM)
        visitIntInsn(ISTORE, 2)
    }

    public fun MethodVisitor.write() {
        visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
        visitVarInsn(ALOAD, 1)
        visitVarInsn(ILOAD, 2)
        visitInsn(CALOAD)
        visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(C)V", false)
    }

    public fun MethodVisitor.read() {
        visitVarInsn(ALOAD, 1)
        visitVarInsn(ILOAD, 2)
        visitFieldInsn(GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;")
        visitMethodInsn(INVOKEVIRTUAL, "java/io/InputStream", "read", "()I", false)
        visitInsn(CASTORE)
    }

    public fun MethodVisitor.cycleStart() {
        val begin = Label()
        val end = Label()

        labels.push(end)
        labels.push(begin)
        visitLabel(begin)

        if (isFirstCycle) {
            visitFrame(F_APPEND, 2, arrayOf("[C", INTEGER), 0, null)
            isFirstCycle = false
        } else
            visitFrame(F_SAME, 0, null, 0, null)
        visitVarInsn(ALOAD, 1)
        visitVarInsn(ILOAD, 2)
        visitInsn(CALOAD)
        visitJumpInsn(IFEQ, end)
    }

    public fun MethodVisitor.cycleFinish() {
        visitJumpInsn(GOTO, labels.pop())
        visitLabel(labels.pop())
        visitFrame(F_SAME, 0, null, 0, null)
    }
}
