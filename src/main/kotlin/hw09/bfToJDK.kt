package hw09

import classroom.c07.loadClassAndRun
import jdk.internal.org.objectweb.asm.ClassWriter
import jdk.internal.org.objectweb.asm.tree.*
import org.objectweb.asm.Opcodes.*
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.properties.Delegates

/**
 * Created by Alexander on 19.11.2015.
 */
class bfToJDK(code : String) {
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

        fun clone(): Opcode {
            return Opcode(type)
        }
    }

    fun tokenize(code: String): List<Opcode> {
        //Создаем массив лексем (которые уже являются опкодами и готовы к исполнению)
        val retValue = ArrayList<Opcode>()

        for (c in code) {
            when (c) {
                '>' -> retValue.add(Opcode(Opcode.Type.SHR))
                '<' -> retValue.add(Opcode(Opcode.Type.SHL))

                '+' -> retValue.add(Opcode(Opcode.Type.ADD))
                '-' -> retValue.add(Opcode(Opcode.Type.SUB))

                '.' -> retValue.add(Opcode(Opcode.Type.OUT))
                ',' -> retValue.add(Opcode(Opcode.Type.IN))
                '[' -> retValue.add(Opcode(Opcode.Type.WHILE))
                ']' -> retValue.add(Opcode(Opcode.Type.END))
            }
        }

        return retValue
    }
    val opcodes : List<Opcode> = tokenize(code)

    fun toByteCode(className: String, memorySize: Int): ByteArray {
        val cn = ClassNode()
        cn.version = V1_7
        cn.access = ACC_PUBLIC + ACC_STATIC
        cn.name = "ClassTest"
        cn.superName = "java/lang/Object"

        val mn0 = MethodNode(ACC_PUBLIC, "<init>", "()V", null, null)
        val il0 = mn0.instructions
        il0.add(VarInsnNode(ALOAD, 0))
        il0.add(MethodInsnNode(INVOKESPECIAL, cn.superName, "<init>", "()V", false))
        il0.add(InsnNode(RETURN))
        cn.methods.add(mn0)

        val mn = MethodNode(ACC_PUBLIC or ACC_STATIC, "main", "()V", null, null)
        val il = mn.instructions
        val lbls = Stack<LabelNode>()

        //объявляем массив размером memorySize
        il.add(LdcInsnNode(memorySize)) //заносим в стэк константу типа integer
        il.add(IntInsnNode(NEWARRAY, T_CHAR)) //создаем массив
        il.add(VarInsnNode(ASTORE, 1)) //кладем его в локальную переменную 1

        //объявляем позицию
        il.add(LdcInsnNode(memorySize / 2))
        il.add(VarInsnNode(ISTORE, 2)) //кладем его в локальную переменную 2

        //идем по массиву операций
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
                    il.add(VarInsnNode(ALOAD, 0))
                    il.add(FieldInsnNode(GETFIELD, cn.name, "out", "Ljava/io/PrintStream;"))
                    il.add(VarInsnNode(ALOAD, 1))
                    il.add(VarInsnNode(ILOAD, 2))
                    il.add(InsnNode(CALOAD))
                    il.add(MethodInsnNode(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(C)V", false))
                }
                Opcode.Type.IN -> {
                    il.add(VarInsnNode(ALOAD, 1))
                    il.add(VarInsnNode(ILOAD, 2))
                    il.add(VarInsnNode(ALOAD, 0))
                    il.add(FieldInsnNode(GETSTATIC, cn.name, "in", "Ljava/io/InputStream;"))
                    il.add(MethodInsnNode(INVOKEVIRTUAL, "java/io/InputStream", "read", "()I", false))
                    il.add(InsnNode(CASTORE))
                }
                Opcode.Type.WHILE -> {
                    //создаем сразу метки начала и конца
                    val begin = LabelNode()
                    val end = LabelNode()

                    //кладем их в стэк
                    lbls.push(end)
                    lbls.push(begin)

                    //указываем начало
                    il.add(begin)
                    //проверяем условие
                    il.add(VarInsnNode(ALOAD, 1))
                    il.add(VarInsnNode(ILOAD, 2))
                    il.add(InsnNode(CALOAD))
                    il.add(JumpInsnNode(IFEQ, end)) //переходим в конец при условии, что значение равно нулю
                }
                Opcode.Type.END -> {
                    //переходим в начало
                    il.add(JumpInsnNode(GOTO, lbls.pop()))
                    //прописываем конец
                    il.add(lbls.pop())
                }
              //  else -> {}
            }
        }

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

public fun saveToDisk(classByteArray: ByteArray) {
    val targetFile = Paths.get("bfToJDK.class")
    Files.write(targetFile, classByteArray)
}

public fun loadClassAndRun(classByteArray: ByteArray): Any? {
    val cl = ByteArrayClassLoader()
    val exprClass = cl.loadClass("ClassTest", classByteArray)
    val methods = exprClass?.methods
    if (methods == null || methods.isEmpty()) { throw Exception() }
    for (method in methods) {
        if (method.name != "main") { continue }
        return method.invoke(null)
    }
    return null
}

public fun main(args : Array<String>) {
    val helloWorld = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++."
    val simple = "+++[>+<-]>."
    val theSimplest = "+"
    val hw = "Hello World!"
    val size = 100
    val name = "bfToJDK"
    val res = bfToJDK(theSimplest).toByteCode(name, size)
    val output = hw09.loadClassAndRun(res)
    println("$output")
    saveToDisk(res)
}