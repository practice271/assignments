package hw09

import jdk.internal.org.objectweb.asm.ClassWriter
import jdk.internal.org.objectweb.asm.tree.*
import org.objectweb.asm.Opcodes.*
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

/**
 * Created by Alexander on 19.11.2015.
 */
class BFToJvm(code : String) {
    class Opcode(val type : Opcode.Type) {
        enum class Type {
            SHL,
            SHR,
            ADD,
            SUB,
            OUT,
            IN,
            WHILE,
            END
        }
    }

    fun tokenize(code: String): List<Opcode> {
        val res = ArrayList<Opcode>()

        for (c in code) {
            when (c) {
                '>' -> res.add(Opcode(Opcode.Type.SHR))
                '<' -> res.add(Opcode(Opcode.Type.SHL))

                '+' -> res.add(Opcode(Opcode.Type.ADD))
                '-' -> res.add(Opcode(Opcode.Type.SUB))

                '.' -> res.add(Opcode(Opcode.Type.OUT))
                ',' -> res.add(Opcode(Opcode.Type.IN))
                '[' -> res.add(Opcode(Opcode.Type.WHILE))
                ']' -> res.add(Opcode(Opcode.Type.END))
            }
        }

        return res
    }
    val opcodes : List<Opcode> = tokenize(code)

    fun toByteCode(className: String, memorySize: Int): ByteArray {
        val cn = ClassNode()
        cn.version = V1_7
        cn.access = ACC_PUBLIC + ACC_STATIC
        cn.name = className
        cn.superName = "java/lang/Object"

        val mn0 = MethodNode(ACC_PUBLIC, "<init>", "()V", null, null)
        val il0 = mn0.instructions
        il0.add(VarInsnNode(ALOAD, 0))
        il0.add(MethodInsnNode(INVOKESPECIAL, cn.superName, "<init>", "()V", false))
        il0.add(InsnNode(RETURN))
        cn.methods.add(mn0)

        val mn = MethodNode(ACC_PUBLIC or ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null)
        val il = mn.instructions
        val lbls = Stack<LabelNode>()

        il.add(LdcInsnNode(memorySize))
        il.add(IntInsnNode(NEWARRAY, T_CHAR))
        il.add(VarInsnNode(ASTORE, 1))

        il.add(LdcInsnNode(memorySize / 2))
        il.add(VarInsnNode(ISTORE, 2))

        for (opcode in opcodes) {
            when (opcode.type) {
                Opcode.Type.SHL -> il.add(IincInsnNode(2, -1))
                Opcode.Type.SHR -> il.add(IincInsnNode(2, 1))
                Opcode.Type.ADD -> {
                    il.add(VarInsnNode(ALOAD, 1))
                    il.add(VarInsnNode(ILOAD, 2))
                    il.add(InsnNode(DUP2))
                    il.add(InsnNode(CALOAD))
                    il.add(LdcInsnNode(1))
                    il.add(InsnNode(IADD))
                    il.add(InsnNode(CASTORE))
                }
                Opcode.Type.SUB -> {
                    il.add(VarInsnNode(ALOAD, 1))
                    il.add(VarInsnNode(ILOAD, 2))
                    il.add(InsnNode(DUP2))
                    il.add(InsnNode(CALOAD))
                    il.add(LdcInsnNode(-1))
                    il.add(InsnNode(IADD))
                    il.add(InsnNode(CASTORE))
                }
                Opcode.Type.OUT -> {
                    il.add(FieldInsnNode(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"))
                    il.add(VarInsnNode(ALOAD, 1))
                    il.add(VarInsnNode(ILOAD, 2))
                    il.add(InsnNode(CALOAD))
                    il.add(MethodInsnNode(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(C)V", false))
                }
                Opcode.Type.IN -> {
                    il.add(VarInsnNode(ALOAD, 1))
                    il.add(VarInsnNode(ILOAD, 2))
                    il.add(FieldInsnNode(GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;"))

                    il.add(MethodInsnNode(INVOKEVIRTUAL, "java/io/InputStream", "read", "()I", false))
                    il.add(InsnNode(I2C))
                    il.add(InsnNode(CASTORE))
                }
                Opcode.Type.WHILE -> {
                    val begin = LabelNode()
                    val end = LabelNode()

                    lbls.push(end)
                    lbls.push(begin)

                    il.add(begin)
                    il.add(VarInsnNode(ALOAD, 1))
                    il.add(VarInsnNode(ILOAD, 2))
                    il.add(InsnNode(CALOAD))
                    il.add(JumpInsnNode(IFEQ, end))
                }
                Opcode.Type.END -> {
                    il.add(JumpInsnNode(GOTO, lbls.pop()))
                    il.add(lbls.pop())
                }
            }
        }
        il.add(InsnNode(RETURN))
        cn.methods.add(mn)

        val cw = ClassWriter(ClassWriter.COMPUTE_FRAMES)
        cn.accept(cw)

        return cw.toByteArray()
    }
}

internal class ByteArrayClassLoader(): ClassLoader() {
    fun loadClass(name: String?, buf: ByteArray): Class<*>? {
        return super.defineClass(name, buf, 0, buf.size)
    }
}

public fun saveToDisk(classByteArray: ByteArray, className: String) {
    val targetFile = Paths.get("$className.class")
    Files.write(targetFile, classByteArray)
}

public fun loadClassAndRun(classByteArray: ByteArray, className: String) {
    val cl = ByteArrayClassLoader()
    val exprClass = cl.loadClass("$className", classByteArray)
    val methods = exprClass?.methods
    if (methods == null || methods.isEmpty()) { throw Exception() }
    for (method in methods) {
        if (method.name != "main") { continue }
        method.invoke(null, null)
    }
}

//public fun main(args : Array<String>) {
//    val helloWorld = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++."
//    val simple = "+++[>+<-]>."
//    val theSimplest = ",.[[>]++-.[<]>-]"
//    val hw = "Hello World!"
//    val io = ",++."
//    val size = 100000
//    val name = "ClassTest"
//    val res = BFToJvm(theSimplest).toByteCode(name, size)
//    hw09.loadClassAndRun(res, name)
//    saveToDisk(res, name)
//}