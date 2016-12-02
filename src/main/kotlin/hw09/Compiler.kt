package hw09

/* Compiler for languages like BrainFuck  made by Guzel Garifullina
   Estimated time  3 hours
   real time       3 hours
*/

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.*
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

public class Compiler(private val codeG : Code, private val className : String){
    private var code = codeG.getCode()
    private val memSize = 30000

    private fun getClassWriter() : ClassWriter {
        val cw = ClassWriter(ClassWriter.COMPUTE_FRAMES)
        cw.visit(V1_7, ACC_PUBLIC or ACC_FINAL or ACC_SUPER,
                className, null, "java/lang/Object", null)

        val constructor = cw.visitMethod(ACC_PUBLIC , "<init>", "()V", null, null)
        init(constructor)

        val run = cw.visitMethod(ACC_PUBLIC or ACC_STATIC, "main", "()V", null, null)
        initArray(run)
        initIndexOfArray(run)

        if (! codeG.isCorrect()){
            printError (run)
            cw.visitEnd()
            return cw
        }
        val lbls = Stack<Label>()
        for (elem in code) {
            when (elem.getType()) {
                Commands.SHIFT -> shift(run, elem.getAmt())
                Commands.ADD   -> add (run, elem.getAmt())
                Commands.ZERO  -> toZero(run)
                Commands.OUT   -> sysOut(run, elem.getAmt())
                Commands.IN    -> sysIn(run, elem.getAmt())
                Commands.WHILE -> cycle (run, lbls)
                Commands.END   -> endCycle(run, lbls)
            }
        }
        run.visitInsn(RETURN)
        run.visitMaxs(8, 3)
        run.visitEnd()

        cw.visitEnd()
        return cw
    }

    public fun generateClassByteArray(): ByteArray {
        return getClassWriter().toByteArray()
    }
    private class ByteArrayClassLoader(): ClassLoader() {
        fun loadClass(name: String?, byteCode: ByteArray): Class<*>? {
            return super.defineClass(name, byteCode, 0, byteCode.size)
        }
    }
    public fun saveToDisk(classByteArray: ByteArray) {
        val targetFile = Paths.get("$className.class")
        Files.write(targetFile, classByteArray)
    }

    public fun loadClassAndRun(classByteArray: ByteArray): Any? {
        val cl = ByteArrayClassLoader()
        val exprClass = cl.loadClass(className, classByteArray)
        val methods = exprClass?.methods
        if (methods == null || methods.isEmpty()) {
            throw Exception()
        }
        for (method in methods) {
            if (method.name != "main") { continue }
            return method.invoke(null)
        }
        return null
    }
    private fun init (constructor : MethodVisitor){
        constructor.visitVarInsn(ALOAD, 0)
        constructor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false)
        constructor.visitMaxs(2, 1)
        constructor.visitInsn(RETURN)
        constructor.visitEnd()
    }
    private fun initArray (run : MethodVisitor){
        run.visitCode()
        run.visitIntInsn(SIPUSH, memSize)
        run.visitIntInsn(NEWARRAY, T_BYTE)
        run.visitVarInsn(ASTORE, 1)
    }
    private fun initIndexOfArray (run : MethodVisitor){
        run.visitIntInsn(SIPUSH, memSize / 2)
        run.visitVarInsn(ISTORE, 2)
    }
    private fun printError (run : MethodVisitor){
        run.visitFieldInsn(GETSTATIC, "java/lang/System",
                "out", "Ljava/io/PrintStream;")
        run.visitLdcInsn("Unbalanced brackets!")
        run.visitMethodInsn(INVOKEVIRTUAL,
                "java/io/PrintStream", "println",
                "(Ljava/lang/String;)V", false)
        run.visitInsn(RETURN)
        run.visitMaxs(2, 2)
        run.visitEnd()
    }
    private fun shift (run : MethodVisitor, amt : Int){
        run.visitVarInsn(ILOAD, 2)
        run.visitLdcInsn(amt)
        run.visitInsn(IADD)
        run.visitVarInsn(ISTORE, 2)
    }
    private fun add (run : MethodVisitor, amt : Int) {
        run.visitVarInsn(ALOAD, 1)
        run.visitVarInsn(ILOAD, 2)
        run.visitInsn(DUP2)
        run.visitInsn(BALOAD)
        run.visitLdcInsn(amt)
        run.visitInsn(IADD)
        run.visitInsn(BASTORE)
    }
    private fun toZero (run : MethodVisitor){
        run.visitVarInsn(ALOAD, 1)
        run.visitVarInsn(ILOAD, 2)
        run.visitInsn(ICONST_0)
        run.visitInsn(BASTORE)
    }
    private fun sysOut (run : MethodVisitor, amt : Int) {
        for (i in 1..amt) {
            run.visitVarInsn(ALOAD, 1)
            run.visitVarInsn(ILOAD, 2)
            run.visitInsn(BALOAD)
            run.visitInsn(I2C)
            run.visitMethodInsn(INVOKESTATIC,
                    "kotlin/io/ConsoleKt", "print", "(C)V", false)
        }
    }
    private fun sysIn (run : MethodVisitor, amt : Int) {
        for (i in 1..amt) {
            run.visitVarInsn(ALOAD, 1)
            run.visitVarInsn(ILOAD, 2)
            run.visitFieldInsn(GETSTATIC,
                    "java/lang/System", "in", "Ljava/io/InputStream;")
            run.visitMethodInsn(INVOKEVIRTUAL,
                    "java/io/InputStream", "read", "()I", false)
            run.visitInsn(BASTORE)
        }
    }
    private fun cycle (run : MethodVisitor, lbls : Stack<Label>){
        val begin = Label()
        val end = Label()
        lbls.push(end)
        lbls.push(begin)
        run.visitLabel(begin)
        run.visitIntInsn(ALOAD, 1)
        run.visitIntInsn(ILOAD, 2)
        run.visitInsn(BALOAD)
        run.visitJumpInsn(IFEQ, end)
    }
    private fun endCycle (run : MethodVisitor, lbls : Stack<Label>){
        run.visitJumpInsn(GOTO, lbls.pop())
        run.visitLabel(lbls.pop())
    }
}