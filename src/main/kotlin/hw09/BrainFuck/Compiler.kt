package hw09.BrainFuck

/* Compiler for BrainFuck  made by Guzel Garifullina
   Estimated time  3 hours
   real time       3 hours
*/

import jdk.internal.org.objectweb.asm.ClassWriter
import jdk.internal.org.objectweb.asm.Label
import jdk.internal.org.objectweb.asm.Opcodes.*
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

public class Compiler(private val arr :Code, private val className : String){
    private val code = arr.getCode()
    private val memSize = 30000
    private fun getClassWriter() : ClassWriter {
        val cw = ClassWriter(ClassWriter.COMPUTE_FRAMES)
        cw.visit(V1_7, ACC_PUBLIC or ACC_FINAL or ACC_SUPER,
                className, null, "java/lang/Object", null)

        val constructor = cw.visitMethod(ACC_PUBLIC , "<init>", "()V", null, null)
        constructor.visitVarInsn(ALOAD, 0)
        constructor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false)
        constructor.visitMaxs(2, 1)
        constructor.visitInsn(RETURN)
        constructor.visitEnd()

        val run = cw.visitMethod(ACC_PUBLIC or ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null)
        //init array
        run.visitCode()
        run.visitIntInsn(SIPUSH, memSize)
        run.visitIntInsn(NEWARRAY, T_INT)
        run.visitVarInsn(ASTORE, 1)

        //init index of array
        run.visitIntInsn(SIPUSH, memSize / 2)
        run.visitVarInsn(ISTORE, 2)

        val lbls = Stack<Label>();
        for (elem in code) {
            when (elem.getType()) {
                Commands.SHIFT -> {
                    run.visitVarInsn(ILOAD, 2)
                    run.visitLdcInsn(elem.getAmt())
                    run.visitInsn(IADD)
                    run.visitVarInsn(ISTORE, 2)
                }
                Commands.ADD -> {
                    run.visitVarInsn(ALOAD, 1)
                    run.visitVarInsn(ILOAD, 2)
                    run.visitInsn(DUP2)
                    run.visitInsn(IALOAD)
                    run.visitLdcInsn(elem.getAmt())
                    run.visitInsn(IADD)
                    run.visitInsn(IASTORE)
                }
                Commands.ZERO -> {
                    run.visitVarInsn(ALOAD, 1)
                    run.visitVarInsn(ILOAD, 2)
                    run.visitInsn(ICONST_0)
                    run.visitInsn(IASTORE)
                }
                Commands.OUT -> {
                    for (i in 1..elem.getAmt()) {
                        run.visitVarInsn(ALOAD, 1)
                        run.visitVarInsn(ILOAD, 2)
                        run.visitInsn(IALOAD)
                        run.visitInsn(I2C)
                        run.visitMethodInsn(INVOKESTATIC,
                                "kotlin/io/ConsoleKt", "print", "(C)V", false)
                    }
                }
                Commands.IN -> {
                    for (i in 1..elem.getAmt()) {
                        run.visitVarInsn(ALOAD, 1)
                        run.visitVarInsn(ILOAD, 2)
                        run.visitFieldInsn(GETSTATIC,
                                "java/lang/System", "in", "Ljava/io/InputStream;")
                        run.visitMethodInsn(INVOKEVIRTUAL,
                                "java/io/InputStream", "read", "()I", false)
                        run.visitInsn(IASTORE)
                    }
                }
                Commands.WHILE-> {
                    val begin = Label()
                    val end =   Label()
                    lbls.push(end)
                    lbls.push(begin)
                    run.visitLabel(begin)
                    run.visitIntInsn(ALOAD, 1)
                    run.visitIntInsn(ILOAD, 2)
                    run.visitInsn(IALOAD)
                    run.visitJumpInsn(IFEQ, end)
                }
                Commands.END-> {
                    run.visitJumpInsn(GOTO, lbls.pop())
                    run.visitLabel(lbls.pop())
                }
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
            return method.invoke(null, null)
        }
        return null
    }

}

public fun main(args: Array<String>) {
    val expr = Code(",++-.")
    val com = Compiler(expr, "Brainfuck")
    val classByteArray = com.generateClassByteArray()
    com.loadClassAndRun(classByteArray)
    com.saveToDisk(classByteArray)
}