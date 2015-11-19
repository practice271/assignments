package classroom.c07

import org.objectweb.asm.ClassWriter
import java.nio.file.Files
import java.nio.file.Paths
import org.objectweb.asm.Opcodes.*

fun main(args: Array<String>) {
    val cw = ClassWriter(0)
    cw.visit(V1_7, ACC_PUBLIC, "HelloWorld", null, "java/lang/Object", null)

    val mw = cw.visitMethod(
            ACC_PUBLIC or ACC_STATIC, "main",
            "([Ljava/lang/String;)V", null, null)
    mw.visitFieldInsn(GETSTATIC, "java/lang/System",
            "out", "Ljava/io/PrintStream;")
    mw.visitLdcInsn("Hello world!")

    mw.visitMethodInsn(INVOKEVIRTUAL,
            "java/io/PrintStream", "println",
            "(Ljava/lang/String;)V")
    mw.visitInsn(RETURN)
    mw.visitMaxs(2, 2)
    mw.visitEnd()
    cw.visitEnd()

    val bytes = cw.toByteArray()
    val targetFile = Paths.get("HelloWorld.class")
    Files.write(targetFile, bytes)
}
